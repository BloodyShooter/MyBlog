package model;

import java.sql.Date;

/**
 * Created by Егор on 01.04.2017.
 */
public class Comment {
    private int id;
    private String text;
    private Date date;
    private int id_user;
    private int id_theme;

    public Comment(int id) {
        this.id = id;
    }

    public Comment(int id, String text, Date date, int id_user, int id_theme) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.id_user = id_user;
        this.id_theme = id_theme;
    }

    public Comment(String text, Date date, int id_user, int id_theme) {
        this.text = text;
        this.date = date;
        this.id_user = id_user;
        this.id_theme = id_theme;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdUser() {
        return id_user;
    }

    public void setIdUser(int id_user) {
        this.id_user = id_user;
    }

    public int getIdTheme() {
        return id_theme;
    }

    public void setIdTheme(int id_theme) {
        this.id_theme = id_theme;
    }
}
