package Application.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_workout")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String Name;

    @Column(name = "repetitions")
    private String Repetitions;

    @Column(name = "quantity")
    private String Quantity;

    @Column(name = "comment")
    private String Comment;

    @Column(name = "ringWork")
    private String ringWork;

    @Column(name = "comment2")
    private String Comment2;

    @ManyToMany(mappedBy = "workouts")
    private Set<UsersChat> usersChat;

    @OneToMany(mappedBy = "workoutId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<StatisticWorkout> statisticWorkouts;

    public Workout() {

    }

    public Workout(String Name, String Repetitions, String Quantity, String Comment, String ringWork, String Comment2) {
        this.Name = Name;
        this.Repetitions = Repetitions;
        this.Quantity = Quantity;
        this.Comment = Comment;
        this.ringWork = ringWork;
        this.Comment2 = Comment2;
        this.usersChat = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRepetitions() {
        return Repetitions;
    }

    public void setRepetitions(String repetitions) {
        Repetitions = repetitions;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getRingWork() {
        return ringWork;
    }

    public void setRingWork(String ringWork) {
        this.ringWork = ringWork;
    }

    public String getComment2() {
        return Comment2;
    }

    public void setComment2(String comment2) {
        Comment2 = comment2;
    }

    public Set<UsersChat> getUsersChat() {
        return usersChat;
    }

    public void setUsersChat(Set<UsersChat> usersChats) {
        this.usersChat = usersChats;
    }

    public void addUserChat(UsersChat usersChat) {
        try {
            if (this.usersChat == null) {
                this.usersChat = new HashSet<>();
                this.usersChat.add(usersChat);
            } else {
                this.usersChat.add(usersChat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<StatisticWorkout> getStatisticWorkouts() {
        return statisticWorkouts;
    }

    public void setStatisticWorkouts(List<StatisticWorkout> statisticWorkouts) {
        this.statisticWorkouts = statisticWorkouts;
    }

    public void addStatisticWorkouts(StatisticWorkout statisticWorkout) {
        try {
            this.statisticWorkouts.add(statisticWorkout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
