package Application.service;

import Application.dao.WorkoutDAO;
import Application.model.StatisticWorkout;
import Application.model.UsersChat;
import Application.model.Workout;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutDAO workoutDAO;

    @Autowired
    private UsersChatService usersChatService;

    @Autowired
    private StatisticWorkoutsService statisticWorkoutsService;

    public boolean serviceSaveWorkout(String chatId, String workoutJson) { //проверить правильный ли тут JSON и проверить ключи
        try {
            JSONObject jsonObject = new JSONObject(workoutJson);
            Workout workout = new Workout();
            workout.setName(jsonObject.getString("name"));
            workout.setRepetitions(jsonObject.getString("repetitions"));
            workout.setQuantity(jsonObject.getString("quantity"));
            workout.setComment(jsonObject.getString("comment"));
            workout.setRingWork(jsonObject.getString("ringWork"));
            workout.setComment2(jsonObject.getString("comment2"));

            UsersChat usersChat = usersChatService.findChatIdUsersChatService(chatId);
            workout.addUserChat(usersChat);
            usersChat.addWorkout(workout);

            workoutDAO.save(workout);
            usersChatService.updateService(usersChat);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String serviceGetWorkout(String chatId) {
        UsersChat usersChats = usersChatService.findChatIdUsersChatService(chatId);
        Set<Workout> workouts = usersChats.getWorkouts();

        List<Workout> workouts1 = new ArrayList<>(workouts);
        for (int i = 0; i < workouts1.size() - 1; i++) {
            for (int j = 0; j < workouts1.size() - i - 1; j++) {
                if (workouts1.get(j + 1).getId() < workouts1.get(j).getId()) {
                    Workout workout = workouts1.get(j);
                    workouts1.set(j, workouts1.get(j + 1));
                    workouts1.set(j + 1, workout);
                }
            }
        }

        String returnText = "";
        for (Workout workout : workouts1) {
            returnText += workout.getId() + " : " + workout.getName() + "\n";
        }
        return returnText;
    }

    public String serviceChoosingWorkout(String workoutId, String timeStart, String chatId) {
        Workout workout = workoutDAO.findId(Long.parseLong(workoutId));
        UsersChat usersChat = usersChatService.findChatIdUsersChatService(chatId);
        StatisticWorkout statisticWorkout = new StatisticWorkout(timeStart, "", "", workout, usersChat);
        workout.addStatisticWorkouts(statisticWorkout);
        usersChat.addStatisticWorkouts(statisticWorkout);
        statisticWorkoutsService.saveService(statisticWorkout);
        return workout.getName() + "\n" +
                workout.getRepetitions() + "\n" +
                workout.getQuantity() + "\n" +
                workout.getComment() + "\n" +
                workout.getRingWork() + "\n" +
                workout.getComment2();
    }

    public String serviceSetTimeWorkout(String workoutId, String time, String chatId, String timeStart) {
        try {
            StatisticWorkout statisticWorkout = statisticWorkoutsService.findIdForWorkout(Long.parseLong(workoutId), chatId, timeStart);
            statisticWorkout.setTimeStop(time);
            statisticWorkoutsService.updateService(statisticWorkout);
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String serviceFindChatId(String chatId) {
        try {
            return usersChatService.findId(Long.parseLong(chatId)).getChatID();
            //return usersChatService.findIdGroup(Long.parseLong(chatId)).getChatID();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
