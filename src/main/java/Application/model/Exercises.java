package Application.model;

import javax.persistence.*;

@Entity
@Table(name = "t_exercises")
public class Exercises {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fileId")
    private String fileId;

    @Column(name = "chatId")
    private String chatId;

    @Column(name = "videoOrPhoto")
    private String videoOrPhoto;

    @Column(name = "nameExercises")
    private String nameExercises;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usersChatId")
    private UsersChat usersChatId;

    public Exercises(){

    }

    public Exercises(String fileId, String chatId, String videoOrPhoto, String nameExercises){
        this.fileId = fileId;
        this.chatId = chatId;
        this.videoOrPhoto = videoOrPhoto;
        this.nameExercises = nameExercises;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public long getId() {
        return id;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getVideoOrPhoto() {
        return videoOrPhoto;
    }

    public void setVideoOrPhoto(String videoOrPhoto) {
        this.videoOrPhoto = videoOrPhoto;
    }

    public String getNameExercises() {
        return nameExercises;
    }

    public void setNameExercises(String nameExercises) {
        this.nameExercises = nameExercises;
    }

    public UsersChat getUsersChatId() {
        return usersChatId;
    }

    public void setUsersChatId(UsersChat usersChatId) {
        this.usersChatId = usersChatId;
    }
}
