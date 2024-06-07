package Application.dao;

import Application.model.Workout;
import Application.repository.DaoInterface;
import Application.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkoutDAO implements DaoInterface<Workout> {

    private Session session = HibernateSessionFactoryUtil.getSessionFactory();

    @Override
    public String save(Workout workout) {
        try {
            Transaction transaction = session.beginTransaction();
            session.save(workout);
            transaction.commit();
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public String delete(Workout workout) {
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(workout);
            transaction.commit();
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public String update(Workout workout) {
        try {
            Transaction transaction = session.beginTransaction();
            session.update(workout);
            transaction.commit();
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public Workout findId(long id) {
        try {
            Workout workout = session.get(Workout.class, id);
            return workout;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Workout> findAll() {
        try {
            List<Workout> workouts = session.createQuery("select u from Workout u", Workout.class).getResultList();
            return workouts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
