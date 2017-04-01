package model;

import java.sql.Date;

/**
 * Created by Егор on 01.04.2017.
 */
public class Theme {
    private int id;
    private String title;
    private String text;
    private Date date;

    public Theme(int id) {
        this.id = id;
    }

    public Theme(int id, String title, String text, Date date) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
    }

    public Theme(String title, String text, Date date) {
        this.title = title;
        this.text = text;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
