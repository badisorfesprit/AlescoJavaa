/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso.Entity;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Nawres
 */
public class Club {
    private int id, nbinscriptions,place;
    private String nom, description, affiche,adresse,ville,telephone,etat;
    private Date delai,dajout;
    private int prolongation;
    
    private int user_id;
    private CategorieC cat;
    private String libelle;
    private int idCateg,nbinsc;

    public Club(String nom, String description, String adresse, String telephone, Date delai,int place) {
        this.nom = nom;
        this.description = description;
        this.adresse = adresse;
        this.telephone = telephone;
        this.delai = delai;
        this.place=place;
    }
    public Club(String nom, String description, String adresse, String telephone, Date delai,int place,String affiche) {
        this.nom = nom;
        this.description = description;
        this.adresse = adresse;
        this.telephone = telephone;
        this.delai = delai;
        this.place=place;
        this.affiche=affiche;
    }
    
    

    public Club(int id) {
        this.id=id;
    }
    

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getIdCateg() {
        return idCateg;
    }

    public void setIdCateg(int idCateg) {
        this.idCateg = idCateg;
    }

    public int getNbinsc() {
        return nbinsc;
    }

    public void setNbinsc(int nbinsc) {
        this.nbinsc = nbinsc;
    }

    public int getProlongation() {
        return prolongation;
    }

    public void setProlongation(int prolongation) {
        this.prolongation = prolongation;
    }
    

    public Club(String nom,Date delai,int place, String etat, int nbinsc,  String libelle) {
        this.place = place;
        this.nom = nom;
        this.etat = etat;
        this.delai = delai;
        this.libelle = libelle;
        this.nbinsc = nbinsc;
    }

    public Club(int id, int user_id,String nom, String description, String affiche, String adresse, String ville, String telephone, Date delai,  int place,  String etat,int nbinscriptions, Date dajout, int idCateg,String libelle) {
        this.id = id;
        this.nbinscriptions = nbinscriptions;
        this.place = place;
        this.nom = nom;
        this.description = description;
        this.affiche = affiche;
        this.adresse = adresse;
        this.ville = ville;
        this.telephone = telephone;
        this.etat = etat;
        this.delai = delai;
        this.dajout = dajout;
        this.user_id = user_id;
        this.libelle = libelle;
        this.idCateg = idCateg;
        this.nbinsc = nbinsc;
    }
    public Club(int id, int user_id,String nom, String description, String affiche, String adresse, String ville, String telephone, Date delai,  int place,  String etat,int nbinscriptions, Date dajout, int idCateg,int prolongation,String libelle) {
        this.id = id;
        this.nbinscriptions = nbinscriptions;
        this.place = place;
        this.nom = nom;
        this.description = description;
        this.affiche = affiche;
        this.adresse = adresse;
        this.ville = ville;
        this.telephone = telephone;
        this.etat = etat;
        this.delai = delai;
        this.dajout = dajout;
        this.user_id = user_id;
        this.libelle = libelle;
        this.idCateg = idCateg;
        this.nbinsc = nbinsc;
        this.prolongation=prolongation;
    }
    

    public Club(int id, int nbinscriptions, int place, String nom, String description, String affiche, String adresse, String ville, String telephone, String etat, Date delai, Date dajout, int user_id, int idCateg, String libelle) {
        this.id = id;
        this.nbinscriptions = nbinscriptions;
        this.place = place;
        this.nom = nom;
        this.description = description;
        this.affiche = affiche;
        this.adresse = adresse;
        this.ville = ville;
        this.telephone = telephone;
        this.etat = etat;
        this.delai = delai;
        this.dajout = dajout;
        this.user_id = user_id;
        this.libelle = libelle;
        this.idCateg = idCateg;
    }
   
    

    public Club(int user_id, String nom, String description, String affiche, String adresse, String ville, String telephone, Date delai,int place , CategorieC cat) {
        this.place = place;
        this.nom = nom;
        this.description = description;
        this.affiche = affiche;
        this.adresse = adresse;
        this.ville = ville;
        this.telephone = telephone;
        this.delai = delai;
        this.user_id = user_id;
        this.cat = cat;
    }
    public Club(int user_id, String nom, String description, String affiche, String adresse, String ville, String telephone, Date delai,int place , CategorieC cat,int prolongation) {
        this.place = place;
        this.nom = nom;
        this.description = description;
        this.affiche = affiche;
        this.adresse = adresse;
        this.ville = ville;
        this.telephone = telephone;
        this.delai = delai;
        this.user_id = user_id;
        this.cat = cat;
        this.prolongation=prolongation;
    }
    public Club(String nom, Date delai, int place,  String etat, int nbinscriptions,CategorieC cat ) {
        
        this.nbinscriptions = nbinscriptions;
        this.place = place;
        this.nom = nom;
       
        this.etat = etat;
        this.delai = delai;
       
    }
    

    public Club(int id, int user_id, String nom, String description,String affiche, String adresse, String ville, String telephone,Date delai, int place,  String etat, int nbinscriptions,  Date dajout, CategorieC cat) {
        this.id = id;
        this.nbinscriptions = nbinscriptions;
        this.place = place;
        this.nom = nom;
        this.description = description;
        this.affiche = affiche;
        this.adresse = adresse;
        this.ville = ville;
        this.telephone = telephone;
        this.etat = etat;
        this.delai = delai;
        this.dajout = dajout;
        this.user_id = user_id;
        this.cat = cat;
    }
    public Club(int id, int user_id, String nom, String description,String affiche, String adresse, String ville, String telephone,Date delai, int place,  String etat, int nbinscriptions,  Date dajout) {
        this.id = id;
        this.nbinscriptions = nbinscriptions;
        this.place = place;
        this.nom = nom;
        this.description = description;
        this.affiche = affiche;
        this.adresse = adresse;
        this.ville = ville;
        this.telephone = telephone;
        this.etat = etat;
        this.delai = delai;
        this.dajout = dajout;
        this.user_id = user_id;
        this.cat = cat;
    }
    public Club( String nom, String description,String affiche, String adresse, String ville, String telephone,Date delai, int place) {
        
        this.place = place;
        this.nom = nom;
        this.description = description;
        this.affiche = affiche;
        this.adresse = adresse;
        this.ville = ville;
        this.telephone = telephone;
        
        this.delai = delai;
        
       
    }
    

    public Club() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Club other = (Club) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbinscriptions() {
        return nbinscriptions;
    }

    public void setNbinscriptions(int nbinscriptions) {
        this.nbinscriptions = nbinscriptions;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAffiche() {
        return affiche;
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDelai() {
        return delai;
    }

    public void setDelai(Date delai) {
        this.delai = delai;
    }

    public Date getDajout() {
        return dajout;
    }

    public void setDajout(Date dajout) {
        this.dajout = dajout;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public CategorieC getCat() {
        return cat;
    }

    public void setCat(CategorieC cat) {
        this.cat = cat;
    }
    
    

  
    
    
}
