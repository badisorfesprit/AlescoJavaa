/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso.Entity;

import java.sql.Date;

/**
 *
 * @author dell
 */
public class Documents {
    private int id ;
    private String nom;
    private String type;
    private String corrige;
    private String matiere ;
    private String classe ;
    private String section;
    private Date date;
    private String nomfich;

    public Documents() {
    }

    @Override
    public String toString() {
        return "NewClass{" + "id=" + id + ", nom=" + nom + ", type=" + type + ", corrige=" + corrige + ", matiere=" + matiere + ", classe=" + classe + ", section=" + section + ", date=" + date + ", nomfich=" + nomfich + '}';
    }

    public Documents(String nom, String type, String corrige, String matiere, String classe, String section, Date date) {
        this.nom = nom;
        this.type = type;
        this.corrige = corrige;
        this.matiere = matiere;
        this.classe = classe;
        this.section = section;
        this.date = date;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCorrige() {
        return corrige;
    }

    public void setCorrige(String corrige) {
        this.corrige = corrige;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNomfich() {
        return nomfich;
    }

    public void setNomfich(String nomfich) {
        this.nomfich = nomfich;
    }
    

    public Documents(int id, String nom, String type, String corrige, String matiere, String classe, String section, Date date, String nomfich) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.corrige = corrige;
        this.matiere = matiere;
        this.classe = classe;
        this.section = section;
        this.date = date;
        this.nomfich = nomfich;
    }
    
}
