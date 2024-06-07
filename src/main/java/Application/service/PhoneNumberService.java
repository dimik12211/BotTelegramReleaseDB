package Application.service;

import Application.dao.PhoneNumberDAO;
import Application.dao.UsersChatDAO;
import Application.model.UsersChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberService {

    @Autowired
    private UsersChatDAO usersChatDAO;

    @Autowired
    private PhoneNumberDAO phoneNumberDAO;

    public boolean serviceExistPhoneNumber(String chatId) {
        if (!usersChatDAO.isRegistration(chatId).equals("true")) {
            UsersChat usersChat = new UsersChat(chatId, "", "", "", true);
            usersChatDAO.save(usersChat);
        }
        if (phoneNumberDAO.daoExistPhoneNumber(chatId)) {
            return true;
        }
        return false;
    }

    public boolean serviceSavePhoneNumber(String chatId, String phoneNumber) {
        try {
            UsersChat usersChat = usersChatDAO.findChatIdUsersChat(chatId);
            usersChat.setPhoneNumber(phoneNumber);
            phoneNumberDAO.daoSavePhoneNumber(usersChat);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
