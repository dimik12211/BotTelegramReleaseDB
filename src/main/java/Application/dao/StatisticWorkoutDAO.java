package Application.dao;

import Application.model.StatisticWorkout;
import Application.model.Workout;
import Application.repository.DaoInterface;
import Application.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatisticWorkoutDAO implements DaoInterface<StatisticWorkout> {

    private Session session = HibernateSessionFactoryUtil.getSessionFactory();

    @Override
    public String save(StatisticWorkout statisticWorkout) {
        try {
            Transaction transaction = session.beginTransaction();
            session.save(statisticWorkout);
            transaction.commit();
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public String delete(StatisticWorkout statisticWorkout) {
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(statisticWorkout);
            transaction.commit();
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public String update(StatisticWorkout statisticWorkout) {
        try {
            Transaction transaction = session.beginTransaction();
            session.update(statisticWorkout);
            transaction.commit();
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public StatisticWorkout findId(long id) {
        try {
            StatisticWorkout statisticWorkout = session.get(StatisticWorkout.class, id);
            return statisticWorkout;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<StatisticWorkout> findAll() {
        try {
            List<StatisticWorkout> statisticWorkouts = session.createQuery("select u from StatisticWorkout u", StatisticWorkout.class).getResultList();
            return statisticWorkouts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public StatisticWorkout findIdForWorkoutId(long workoutId, long chatId, String timeStart) {
        try {
            Query query = session.createQuery("select s from StatisticWorkout s where s.workoutId.id = :workoutId and s.usersChatId.id = :chatId and s.timeStart = :timeStart");
            query.setParameter("workoutId", workoutId);
            query.setParameter("chatId", chatId);
            query.setParameter("timeStart", timeStart);
            return (StatisticWorkout) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
