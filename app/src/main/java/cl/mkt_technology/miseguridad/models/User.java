package cl.mkt_technology.miseguridad.models;

/**
 * Created by Aldo Gallardo on 26-03-2017.
 */

public class User {

    private String name,email,uid;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
