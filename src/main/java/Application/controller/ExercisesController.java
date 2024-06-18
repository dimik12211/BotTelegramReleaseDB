package Application.controller;

import Application.service.ExercisesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ExercisesController {

    @Autowired
    private ExercisesService exercisesService;

    @PostMapping(value = "FindAllExercises")
    public String controllerFindAllExercises() {
        try {
            return exercisesService.findAllText();
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка при запросе упражнений для пояснений" + e.getMessage();
        }
    }

    @PostMapping(value = "ExercisesSave")
    public String controllerExercisesSave(String videoOrPhoto, String fileId, String chatId, String name) {
        try {
            return exercisesService.saveService(videoOrPhoto, fileId, chatId, name);
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка при запросе упражнений для пояснений" + e.getMessage();
        }
    }

    @PostMapping(value = "VideoOrPhoto")
    public String controllerVideoOrPhoto(String fileId) {
        try {
            return exercisesService.videoOrPhoto(fileId);
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка при запросе информации видео или фото: " + e.getMessage();
        }
    }
}
