package com.buildswakfu.rodrigo.buildswakfu.Utils;

/**
 * Created by rodso on 10/03/2017.
 */

public class User{

    private String name;
    private String email;
    private String userID;
    private String userIdToken;
    private String photoUrl;
    private String lang;
    private int lastSync;

    public User(){
        //gets para o firebase
    }

    public User(String name, String email, String userID, String userIdToken, String photoUrl, String lang, int lastSync) {
        this.name = name;
        this.email = email;
        this.userID = userID;
        this.userIdToken = userIdToken;
        this.photoUrl = photoUrl;
        this.lang = lang;
        this.lastSync = lastSync;
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

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserIdToken() {
        return userIdToken;
    }

    public void setUserIdToken(String userIdToken) {
        this.userIdToken = userIdToken;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getLang() {        return lang;    }

    public void setLang(String lang) {        this.lang = lang;    }

    public int getLastSync() {        return lastSync;    }

    public void setLastSync(int lastSync) {        this.lastSync = lastSync;    }
}
