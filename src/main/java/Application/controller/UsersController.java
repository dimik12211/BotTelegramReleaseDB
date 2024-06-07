package Application.controller;

import Application.service.UsersChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UsersChatService usersChatService;

    @PostMapping(value = "FindUsers")
    public String controllerFindUsers(String chatId) {
        try {
            return usersChatService.findChatIdUsersChatServiceText(chatId);
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка при запросе возможных пользователей для назначения тренировки" + e.getMessage();
        }
    }

    @PostMapping(value = "FindAllUsers")
    public String controllerFindAllUsers(){
        try {
            return usersChatService.findAllText();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping(value = "ActiveStatus")
    public String controllerActiveStatus(String chatId){
        try {
            return usersChatService.activeUser(chatId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
