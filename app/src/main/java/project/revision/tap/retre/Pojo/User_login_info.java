package project.revision.tap.retre.Pojo;

import java.io.Serializable;

/**
 * Created by prakash on 11/14/2016.
 */
public class User_login_info implements Serializable {
    String sessionId;
    String sessionName;
    String token;
    String username;
String mail;
    public User_login_info(String sessionId, String sessionName, String token,String username,String mail) {
        this.sessionId = sessionId;
        this.sessionName = sessionName;
        this.token = token;
        this.username=username;
        this.mail=mail;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public String getMail() {
        return mail;
    }
}
