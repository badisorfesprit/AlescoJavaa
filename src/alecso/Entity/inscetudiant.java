/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso.Entity;

import java.sql.Date;

/**
 *
 * @author Nawres
 */
public class inscetudiant {
    private int id,club_id;
    private Club club;
    private int user_id;
    private Date datenaissance;
    private String ecole,adresse,username;
    private int place,nbinsc;
    private Date dateinsc;

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDateinsc() {
        return dateinsc;
    }

    public void setDateinsc(Date dateinsc) {
        this.dateinsc = dateinsc;
    }

    public int getNbinsc() {
        return nbinsc;
    }

    public void setNbinsc(int nbinsc) {
        this.nbinsc = nbinsc;
    }
    
    

    public inscetudiant(int id, int club_id, int user_id, Date datenaissance, String ecole, String adresse, String username) {
        this.id = id;
        this.club_id = club_id;
        this.user_id = user_id;
        this.datenaissance = datenaissance;
        this.ecole = ecole;
        this.adresse = adresse;
        this.username = username;
    }
    public inscetudiant(Date datenaissance, String ecole, String adresse) {
        this.datenaissance = datenaissance;
        this.ecole = ecole;
        this.adresse = adresse;
    }

    
    public inscetudiant(int id, int club_id, int user_id, Date datenaissance, String ecole, String adresse,Date dateinsc,int place,int nbinsc) {
        this.id = id;
        this.club_id = club_id;
        this.user_id = user_id;
        this.datenaissance = datenaissance;
        this.ecole = ecole;
        this.adresse = adresse;
        this.dateinsc=dateinsc;
        this.place=place;
        this.nbinsc=nbinsc;
    }
    public inscetudiant(int id, int club_id, int user_id, Date datenaissance, String ecole, String adresse) {
        this.id = id;
        this.club_id = club_id;
        this.user_id = user_id;
        this.datenaissance = datenaissance;
        this.ecole = ecole;
        this.adresse = adresse;
        //this.place=place;
    }
    

    public inscetudiant(int club_id, int user_id, Date datenaissance, String ecole, String adresse) {
        this.club_id = club_id;
        this.user_id = user_id;
        this.datenaissance = datenaissance;
        this.ecole = ecole;
        this.adresse = adresse;
    }
    
    
    

    public int getClub_id() {
        return club_id;
    }

    public void setClub_id(int club_id) {
        this.club_id = club_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public String getEcole() {
        return ecole;
    }

    public void setEcole(String ecole) {
        this.ecole = ecole;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    
    
    
    
}
