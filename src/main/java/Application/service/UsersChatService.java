package Application.service;

import Application.dao.GroupDAO;
import Application.dao.UsersChatDAO;
import Application.model.GroupTable;
import Application.model.UsersChat;
import Application.repository.ServiceInterface;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UsersChatService implements /*UserDetailsService, */ServiceInterface<UsersChat> {
    @Autowired
    private UsersChatDAO usersChatDAO;

    @Autowired
    private GroupDAO groupDAO;

    @Override
    public String saveService(UsersChat usersChat) {
        try {
            return usersChatDAO.save(usersChat);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public String updateService(UsersChat usersChat) {
        try {
            return usersChatDAO.update(usersChat);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public String deleteService(UsersChat usersChat) {
        try {
            return usersChatDAO.delete(usersChat);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public UsersChat findId(long id) {
        try {
            return usersChatDAO.findId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UsersChat findIdGroup(long id) {
        try {
            return usersChatDAO.findIdGroup(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UsersChat> findAll() {
        try {
            return usersChatDAO.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String findAllText() {
        try {
            return usersChatDAO.findAllText();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*public String findChatIdBooleanService(String chatId) {
        try {
            return usersChatDAO.findChatIdBoolean(chatId);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }*/

    public UsersChat findChatIdUsersChatService(String chatId) {
        try {
            return usersChatDAO.findChatIdUsersChat(chatId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String findChatIdUsersChatServiceText(String chatId) {
        try {
            GroupTable groupTable = groupDAO.findGroup(chatId);
            String returnText = "";
            List<UsersChat> usersChatsDublicats = new ArrayList<>(groupTable.getUsersChats());
            List<UsersChat> usersChats = usersChatsDublicats.stream().distinct().collect(Collectors.toList());
            for (int i = 0; i < usersChats.size(); i++) {
                if (usersChats.get(i).getActive()) {
                    if (!Objects.equals(usersChats.get(i).getChatID(), chatId)) {
                        returnText += "id: " + usersChats.get(i).getId() + " chat: " + usersChats.get(i).getChatID() + " name: " + usersChats.get(i).getNameUser() + " phone: " + usersChats.get(i).getPhoneNumber() + "\n";
                    }
                }
            }
            return returnText;
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка формирования списка пользователей: " + e.getMessage();
        }
    }

    public String findAllUsersAddGroup(String chatId) {
        try {
            String returnText = "";
            List<UsersChat> usersChats = usersChatDAO.findAll();
            for (int i = 0; i < usersChats.size(); i++) {
                if (!Objects.equals(usersChats.get(i).getChatID(), chatId)) {
                    returnText += "id: " + usersChats.get(i).getId() + " chat: " + usersChats.get(i).getChatID() + " name: " + usersChats.get(i).getNameUser() + " phone: " + usersChats.get(i).getPhoneNumber() + "\n";
                }
            }
            return returnText;
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка формирования списка пользователей: " + e.getMessage();
        }
    }

    /*@Override
    public UserDetails loadUserByUsername(String chatId) throws UsernameNotFoundException {
        try {
            UsersChat usersChat = findChatIdUsersChatService(chatId);
            return usersChat;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/

    public String isRegistration(String chatId) {
        try {
            return usersChatDAO.isRegistration(chatId);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String activeUser(String chatId) {
        try {
            UsersChat usersChat = usersChatDAO.findChatIdUsersChat(chatId);
            if (usersChat.getActive()) {
                usersChat.setActive(false);
            } else {
                usersChat.setActive(true);
            }
            usersChatDAO.update(usersChat);
            return "true " + usersChat.getActive();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public boolean isTrener(String chatId) {
        try {
            return usersChatDAO.isTrener(chatId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String AddUserInGroup(String chatIdTrener, String chatId) {
        try {
            UsersChat usersChat = usersChatDAO.findChatIdUsersChat(Long.parseLong(chatId));
            GroupTable groupTable = groupDAO.findGroup(chatIdTrener);
            groupTable.addUsersChats(usersChat);
            groupDAO.update(groupTable);
            usersChat.addGroupTable(groupTable);
            usersChatDAO.update(usersChat);
            return "Пользователь успешно добавлен в группу";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
