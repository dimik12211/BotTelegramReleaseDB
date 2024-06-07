package Application.dao;

import Application.model.UsersChat;
import Application.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PhoneNumberDAO {
    @Autowired
    private UsersChatDAO usersChatDAO;

    private Session session = HibernateSessionFactoryUtil.getSessionFactory();

    public boolean daoExistPhoneNumber(String chatId) {
        try {
            Query query = session.createQuery("select u from UsersChat u where u.chatID = :chatId");
            query.setParameter("chatId", chatId);
            UsersChat usersChat = (UsersChat) query.getSingleResult();
            if(usersChat.getPhoneNumber() == null){
                return false;
            }
            if (!usersChat.getPhoneNumber().equals("")) {
                return true;
            } else {
                return false;
            }
        }catch (NullPointerException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean daoSavePhoneNumber(UsersChat usersChat) {
        try {
            usersChatDAO.update(usersChat);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
