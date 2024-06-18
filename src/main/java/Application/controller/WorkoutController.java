package Application.controller;

import Application.service.StatisticWorkoutsService;
import Application.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private StatisticWorkoutsService statisticWorkoutsService;

    @PostMapping(value = "GetStatistic")
    public String controllerGetStatistic(String chatId) {
        try {
            return statisticWorkoutsService.getStatisticText(chatId);
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка запроса в сервис БД. " + e.getMessage();
        }
    }

    @PostMapping(value = "SaveWorkout")
    public boolean controllerSaveWorkout(String chatId, String workoutJson) {
        try {
            return workoutService.serviceSaveWorkout(chatId, workoutJson);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping(value = "GetWorkout")
    public String controllerGetWorkout(String chatId) {
        try {
            return workoutService.serviceGetWorkout(chatId);
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка запроса в сервис БД. " + e.getMessage();
        }
    }

    @PostMapping(value = "ChoosingWorkout")
    public String controllerChoosingWorkout(String workoutId, String timeStart, String chatId) {
        try {
            return workoutService.serviceChoosingWorkout(workoutId, timeStart, chatId);
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка запроса в сервис БД. " + e.getMessage();
        }
    }

    @PostMapping(value = "SetTimeWorkout")
    public String controllerSetTimeWorkout(String workoutId, String time, String chatId, String timeStart) {
        try {
            return workoutService.serviceSetTimeWorkout(workoutId, time, chatId, timeStart);
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка запроса в сервис БД. " + e.getMessage();
        }
    }

    @PostMapping(value = "FindChatId")
    public String controllerFindChatId(String userId){
        try {
            return workoutService.serviceFindChatId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка запроса на получение chatId (кому назначить тренировку) в сервис БД. " + e.getMessage();
        }
    }
}
