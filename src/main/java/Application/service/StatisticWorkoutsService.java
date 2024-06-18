package Application.service;

import Application.dao.StatisticWorkoutDAO;
import Application.model.StatisticWorkout;
import Application.model.UsersChat;
import Application.repository.DaoInterface;
import Application.repository.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticWorkoutsService implements ServiceInterface<StatisticWorkout> {

    @Autowired
    private StatisticWorkoutDAO statisticWorkoutDAO;

    @Autowired
    private UsersChatService usersChatService;

    @Override
    public String saveService(StatisticWorkout statisticWorkout) {
        try {
            return statisticWorkoutDAO.save(statisticWorkout);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public String updateService(StatisticWorkout statisticWorkout) {
        try {
            return statisticWorkoutDAO.update(statisticWorkout);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public String deleteService(StatisticWorkout statisticWorkout) {
        try {
            return statisticWorkoutDAO.delete(statisticWorkout);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public StatisticWorkout findId(long id) {
        try {
            return statisticWorkoutDAO.findId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<StatisticWorkout> findAll() {
        try {
            return statisticWorkoutDAO.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public StatisticWorkout findIdForWorkout(long workoutId, String chatId, String timeStart) {
        try {
            long chatIdLong = usersChatService.findChatIdUsersChatService(chatId).getId();
            return statisticWorkoutDAO.findIdForWorkoutId(workoutId, chatIdLong, timeStart);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getStatisticText(String chatId) {
        try {
            UsersChat usersChat = usersChatService.findChatIdUsersChatService(chatId);
            List<StatisticWorkout> statisticWorkouts = statisticWorkoutDAO.findStatisticChatId(usersChat.getId());
            String returnText = "Общее количество проведенных тренировок: " + statisticWorkouts.size() + "\n";
            return returnText;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
