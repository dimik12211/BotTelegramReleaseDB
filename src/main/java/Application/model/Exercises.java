package Application.model;

import javax.persistence.*;

@Entity
@Table(name = "t_exercises")
public class Exercises {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "chatId", unique = true)
    private String chatId;

    @Column(name = "nameExercises")
    private String nameExercises;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usersChatId")
    private UsersChat usersChatId;

    public Exercises(){

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
