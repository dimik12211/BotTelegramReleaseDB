package Application.service;

import Application.dao.ExercisesDAO;
import Application.model.Exercises;
import Application.repository.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ExercisesService implements ServiceInterface<Exercises> {

    @Autowired
    private ExercisesDAO exercisesDAO;


    @Override
    public String saveService(Exercises exercises) {
        try {
            return exercisesDAO.save(exercises);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String saveService(String videoOrPhoto, String fileId, String chatId, String name) {
        try {
            Exercises exercises = new Exercises(fileId, chatId, videoOrPhoto, name);
            exercisesDAO.save(exercises);
            String returnText = "SavedIsTrue";
            return returnText;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public String updateService(Exercises exercises) {
        try {
            return exercisesDAO.update(exercises);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public String deleteService(Exercises exercises) {
        try {
            return exercisesDAO.delete(exercises);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public Exercises findId(long id) {
        try {
            return exercisesDAO.findId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Exercises> findAll() {
        try {
            return exercisesDAO.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String findAllText() {
        try {
            String exercicesText = "";
            List<Exercises> exercises = exercisesDAO.findAll();
            for (int i = 0; i < exercises.size(); i++) {
                exercicesText += "id: " + exercises.get(i).getId() + "name: " + exercises.get(i).getNameExercises() + "\n";
            }
            return exercicesText;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String videoOrPhoto(String fileId) {
        try {
            Exercises exercises = exercisesDAO.findFileId(fileId);
            if (Objects.equals(exercises.getVideoOrPhoto(), "video")) {
                return "video";
            } else if (Objects.equals(exercises.getVideoOrPhoto(), "photo")){
                return "photo";
            } else {
                return "Ошибка определения фото или видео.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка в сервисе 'фото или видео'" + e.getMessage();
        }
    }
}
