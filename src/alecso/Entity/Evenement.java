/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso.Entity;

import java.sql.Date;

/**
 *
 * @author Chaima
 */
public class Evenement {
public int id;  
public int id_categorie;
 public String titre;
 public String nom_image;

public String lieu;
 public String description;
 public Date  date_event;
  public int nbplace;

    public Evenement(int id, int id_categorie, String titre, String nom_image, String lieu, String description, Date date_event, int nbplace) {
        this.id = id;
        this.id_categorie = id_categorie;
        this.titre = titre;
        this.nom_image = nom_image;
        this.lieu = lieu;
        this.description = description;
        this.date_event = date_event;
        this.nbplace = nbplace;
    }

  
    

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_event() {
        return date_event;
    }

    public void setDate_event(Date date_event) {
        this.date_event = date_event;
    }

    public int getNbplace() {
        return nbplace;
    }

    public void setNbplace(int nbplace) {
        this.nbplace = nbplace;
    }

    public String getNom_image() {
        return nom_image;
    }

    public void setNom_image(String nom_image) {
        this.nom_image = nom_image;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

 
    

    
   
     
   
   
}
