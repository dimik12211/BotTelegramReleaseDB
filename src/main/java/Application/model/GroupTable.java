package Application.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_group")
public class GroupTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "hostChatId")
    private String chatId;

    @ManyToMany(mappedBy = "groupTable", fetch = FetchType.LAZY)
    private Set<UsersChat> usersChats;

    public long getId() {
        return id;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public Set<UsersChat> getUsersChats() {
        return usersChats;
    }

    public void setUsersChats(Set<UsersChat> usersChats) {
        this.usersChats = usersChats;
    }

    public void addUsersChats(UsersChat usersChat) {
        try {
            if (this.usersChats == null) {
                this.usersChats = new HashSet<>();
                this.usersChats.add(usersChat);
            } else {
                this.usersChats.add(usersChat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
