package Application.model;

import javax.persistence.*;

@Entity
@Table(name = "t_statisticWorkout")
public class StatisticWorkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "timeStart")
    private String timeStart;

    @Column(name = "timeStop")
    private String timeStop;

    @Column(name = "timeTheBest")
    private String timeTheBest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workoutId")
    private Workout workoutId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usersChatId")
    private UsersChat usersChatId;

    public StatisticWorkout() {

    }

    public StatisticWorkout(String timeStart, String timeStop, String timeTheBest, Workout workoutId, UsersChat usersChatId){
        this.timeStart = timeStart;
        this.timeStop = timeStop;
        this.timeTheBest = timeTheBest;
        this.workoutId = workoutId;
        this.usersChatId = usersChatId;
    }

    public long getId() {
        return id;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeStop() {
        return timeStop;
    }

    public void setTimeStop(String timeStop) {
        this.timeStop = timeStop;
    }

    public String getTimeTheBest() {
        return timeTheBest;
    }

    public void setTimeTheBest(String timeTheBest) {
        this.timeTheBest = timeTheBest;
    }

    public Workout getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Workout workoutId) {
        this.workoutId = workoutId;
    }
}

