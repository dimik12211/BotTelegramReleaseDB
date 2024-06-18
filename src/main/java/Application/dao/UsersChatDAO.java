package Application.dao;

import Application.model.UsersChat;
import Application.repository.DaoInterface;
import Application.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsersChatDAO implements DaoInterface<UsersChat> {
    private Session session = HibernateSessionFactoryUtil.getSessionFactory();

    @Override
    public String save(UsersChat user) {
        try {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            session.evict(user);
            transaction.commit();
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public String delete(UsersChat user) {
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public String update(UsersChat user) {
        try {
            Transaction transaction = session.beginTransaction();
            session.evict(user);
            session.flush();
            session.update(user);
            transaction.commit();
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public UsersChat findId(long id) {
        try {
            UsersChat usersChat = session.get(UsersChat.class, id);
            return usersChat;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UsersChat findIdGroup(long id) {
        try {
            Query query = session.createQuery("select u from GroupTable u where u.id = :id");
            query.setParameter("id", id);
            UsersChat usersChat = (UsersChat) query.getSingleResult();
            return usersChat;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UsersChat> findAll() {
        try {
            List<UsersChat> users = session.createQuery("select u from UsersChat u", UsersChat.class).getResultList();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String findAllText() {
        try {
            List<UsersChat> users = session.createQuery("select u from UsersChat u", UsersChat.class).getResultList();
            String returnText = "";
            for(int i = 0; i < users.size(); i++){
                returnText += users.get(i).getChatID() + " ";
            }
            return returnText;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*public String findChatIdBoolean(String chatId) {
        try {
            Query query = session.createQuery("select u from UsersChat u where u.chatID = :chatId");
            query.setParameter("chatId", chatId);
            return query.getResultList().size() == 1 ? "true" : "false";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }*/

    public UsersChat findChatIdUsersChat(String chatId) {
        try {
            Query query = session.createQuery("select u from UsersChat u where u.chatID = :chatId ORDER BY u.id");
            query.setParameter("chatId", chatId);
            UsersChat usersChat = (UsersChat) query.getSingleResult();
            return usersChat;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UsersChat findChatIdUsersChat(long id) {
        try {
            Query query = session.createQuery("select u from UsersChat u where u.id = :id ORDER BY u.id");
            query.setParameter("id", id);
            UsersChat usersChat = (UsersChat) query.getSingleResult();
            return usersChat;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String isRegistration(String chatId) {
        try {
            Query query = session.createQuery("select u from UsersChat u where u.chatID = :chatId");
            query.setParameter("chatId", chatId);
            return query.uniqueResult() != null ? "true" : "false";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public boolean isTrener(String chatId) {
        try {
            Query query = session.createQuery("select u from GroupTable u where u.chatId = :chatId");
            query.setParameter("chatId", chatId);
            return query.uniqueResult() != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*public String findPhoneNumber(String phoneNumber) {
        try {
            Query query = session.createQuery("select u from UsersChat u where u.phoneNumber = :phoneNumber");
            query.setParameter("phoneNumber", phoneNumber);
            return query.getResultList().size() == 1 ? "true" : "false";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }*/
}
