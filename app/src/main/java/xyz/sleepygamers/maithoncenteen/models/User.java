package xyz.sleepygamers.maithoncenteen.models;

public class User {
    private String id, username, email, roomno, uid;

    public User() {
    }

    public User(String id, String username, String email, String roomno, String uid) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roomno = roomno;
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoomno() {
        return roomno;
    }

    public void setRoomno(String roomno) {
        this.roomno = roomno;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
