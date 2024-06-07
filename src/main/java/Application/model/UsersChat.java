package Application.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_userChat")
public class UsersChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "chatID", unique = true)
    private String chatID;
    @Column(name = "nameUser")
    private String nameUser;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "password")
    private String password;
    @Column(name = "active")
    private boolean active;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Workout> workouts;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<GroupTable> groupTable;
    @OneToMany(mappedBy = "usersChatId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<StatisticWorkout> statisticWorkouts;

    @OneToMany(mappedBy = "usersChatId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Exercises> exercises;

    public UsersChat() {

    }

    public UsersChat(String chatID, String nameUser, String phoneNumber, String password, boolean active) {
        this.chatID = chatID;
        this.nameUser = nameUser;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.active = active;
        this.statisticWorkouts = new ArrayList<>();
        this.exercises = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getChatID() {
        return chatID;
    }

    public void setChatID(String chatID) {
        this.chatID = chatID;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    //@Override

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /*public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRoles(Role role) {
        this.roles.add(role);
    }*/

    /*@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override*/

    public String getUsername() { //как Username используется ID чата
        return getChatID();
    }
    //@Override

    public boolean isAccountNonExpired() {
        return true;
    }
    //@Override

    public boolean isAccountNonLocked() {
        return true;
    }
    //@Override

    public boolean isCredentialsNonExpired() {
        return true;
    }
    //@Override

    public boolean isEnabled() {
        if (getActive()) {
            return true;
        } else {
            return false;
        }
    }
    public Set<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(Set<Workout> workouts) {
        this.workouts = workouts;
    }

    public void addWorkout(Workout workout) {
        try {
            workouts.add(workout);
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

    public void addStatisticWorkouts(StatisticWorkout statisticWorkout){
        try {
            if (this.statisticWorkouts == null) {
                this.statisticWorkouts = new ArrayList<>();
                this.statisticWorkouts.add(statisticWorkout);
            } else {
                this.statisticWorkouts.add(statisticWorkout);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Exercises> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercises> exercises) {
        this.exercises = exercises;
    }

    public void addExercises(Exercises exercises){
        try {
            if (this.exercises == null) {
                this.exercises = new ArrayList<>();
                this.exercises.add(exercises);
            } else {
                this.exercises.add(exercises);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
