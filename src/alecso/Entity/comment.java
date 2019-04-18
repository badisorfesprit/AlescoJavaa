/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso.Entity;

import java.sql.Date;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class comment {
    private int id_document;
    private int id;
    private String text;
    private Date date;

    public comment( int id, int id_document,String text, Date date) {
        this.id_document = id_document;
        System.out.println(id);
        this.id = id;
        this.text = text;
        this.date = date;
    }

    public comment(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_document() {
        return id_document;
    }

    public void setId_document(int id_document) {
        this.id_document = id_document;
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

    public comment() {
    }

    @Override
    public String toString() {
        return "comment{" + "text=" + text + '}';
    }
   
    
}
