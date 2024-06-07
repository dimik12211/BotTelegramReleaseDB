package Application.controller;

import Application.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PhoneNumberController {

    @Autowired
    private PhoneNumberService phoneNumberService;

    @PostMapping(value = "ExistPhoneNumber")
    public boolean controllerExistPhoneNumber(String chatId) { //проверка есть ли уже такой chat id в базе и есть ли номер телефона
        try {
            return phoneNumberService.serviceExistPhoneNumber(chatId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping(value = "SavePhoneNumber")
    public boolean controllerSavePhoneNumber(String chatId, String phoneNumber) {
        try {
            return phoneNumberService.serviceSavePhoneNumber(chatId, phoneNumber);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
