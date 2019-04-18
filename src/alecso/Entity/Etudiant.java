/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso.Entity;

import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;

/**
 *
 * @author BADIS
 */
public class Etudiant {
      private SimpleIntegerProperty id;
    private SimpleStringProperty nom;
    private SimpleStringProperty prenom;
    private SimpleStringProperty declass;
    private SimpleStringProperty versclass;
    private SimpleStringProperty etablissement;
    private SimpleIntegerProperty user_id;

    public Etudiant() {
        
    }

   @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final Etudiant other = (Etudiant) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        return "Etudiant{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", declass=" + declass + ", versclass=" + versclass + ", etablissement=" + etablissement + ", user_id=" + user_id + '}';
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }
    public String getNom() {
         return nom.get();
    }

    public void setNom(String nom) {
        this.nom = new SimpleStringProperty(nom);
    }

    public String getPrenom() {
         return prenom.get();
    }

    public void setPrenom(String prenom) {
        this.prenom = new SimpleStringProperty(prenom);
    }

    public String getDeclass() {
         return declass.get();
    }

    public void setDeclass(String declass) {
        this.declass = new SimpleStringProperty(declass);
    }

    public String getVersclass() {
         return versclass.get();
    }

    public void setVersclass(String versclass) {
        this.versclass = new SimpleStringProperty(versclass);
    }

    public String getEtablissement() {
         return etablissement.get();
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = new SimpleStringProperty(etablissement);
    }

    public Integer getUser_id() {
         return user_id.get();
    }

    public void setUser_id(Integer user_id) {
        this.user_id = new SimpleIntegerProperty(user_id);
    }
 public SimpleStringProperty getnomProperty(){
        return nom;
    }
    public SimpleStringProperty getprenomProperty(){
        return prenom;
    }
    public SimpleStringProperty getdeclassProperty(){
        return declass;
    }
    public SimpleStringProperty getVersclassProperty(){
        return versclass;
    }
    public SimpleStringProperty getetablissementProperty(){
        return etablissement;
    }
  
     public SimpleIntegerProperty getUserIDProperty(){
        return user_id;
    }
       public Etudiant(String nom, String prenom, String declass, String Versclass, String etablissement , Integer user_id) {
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.declass = new SimpleStringProperty(declass);
        this.versclass = new SimpleStringProperty(Versclass);
        this.etablissement = new SimpleStringProperty(etablissement);
        this.user_id = new SimpleIntegerProperty(user_id);
    }

 
}
