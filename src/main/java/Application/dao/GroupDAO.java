package Application.dao;

import Application.model.GroupTable;
import Application.model.UsersChat;
import Application.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class GroupDAO {

    private Session session = HibernateSessionFactoryUtil.getSessionFactory();

    public GroupTable findGroup(String chatId) {
        Query query = session.createQuery("select u from GroupTable u where u.chatId = :chatId");
        query.setParameter("chatId", chatId);
        GroupTable groupTable = (GroupTable) query.getSingleResult();
        return groupTable;
    }

    public String save(GroupTable groupTable) {
        try {
            Transaction transaction = session.beginTransaction();
            session.save(groupTable);
            transaction.commit();
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
