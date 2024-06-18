package Application.dao;

import Application.model.Exercises;
import Application.model.UsersChat;
import Application.repository.DaoInterface;
import Application.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public class ExercisesDAO implements DaoInterface<Exercises> {

    private Session session = HibernateSessionFactoryUtil.getSessionFactory();

    @Override
    public String save(Exercises exercises) {
        try {
            Transaction transaction = session.beginTransaction();
            session.save(exercises);
            transaction.commit();
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public String delete(Exercises exercises) {
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(exercises);
            transaction.commit();
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public String update(Exercises exercises) {
        try {
            Transaction transaction = session.beginTransaction();
            session.update(exercises);
            transaction.commit();
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public Exercises findId(long id) {
        try {
            Exercises exercises = session.get(Exercises.class, id);
            return exercises;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Exercises> findAll() {
        try {
            List<Exercises> exercises = session.createQuery("select u from Exercises u", Exercises.class).getResultList();
            return exercises;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Exercises findFileId(String fileId) {
        try {
            Query query = session.createQuery("select u from Exercises u where u.fileId = :fileId ORDER BY u.id");
            query.setParameter("fileId", fileId);
            Exercises exercises = (Exercises) query.getSingleResult();
            return exercises;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
