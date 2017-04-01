package model;

import java.sql.Date;

/**
 * Created by Егор on 01.04.2017.
 */
public class Comment {
    private int id;
    private String test;
    private Date date;
    private User iduser;
    private Theme idtheme;

    public Comment(int id) {
        this.id = id;
    }

    public Comment(int id, String test, Date date, User iduser, Theme idtheme) {
        this.id = id;
        this.test = test;
        this.date = date;
        this.iduser = iduser;
        this.idtheme = idtheme;
    }

    public Comment(String test, Date date, User iduser, Theme idtheme) {
        this.test = test;
        this.date = date;
        this.iduser = iduser;
        this.idtheme = idtheme;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
        this.iduser = iduser;
    }

    public Theme getIdtheme() {
        return idtheme;
    }

    public void setIdtheme(Theme idtheme) {
        this.idtheme = idtheme;
    }
}
