package com.rodrigoad.rodso.wakfustats.Utils;

/**
 * Created by rodso on 08/07/2017.
 */

public class Coment {

    private String coment;
    private String photoURL;
    private String name;

    public Coment(){}

    public Coment(String coment, String name) {
        this.coment = coment;
        this.name = name;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
