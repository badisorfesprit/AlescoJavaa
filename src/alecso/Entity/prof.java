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
public class prof {
      private SimpleIntegerProperty id;
    private SimpleStringProperty nom;
    private SimpleStringProperty prenom;
    private SimpleStringProperty statsociale;
    private SimpleStringProperty deregion;
    private SimpleStringProperty versregion;
    private SimpleStringProperty etablissement;
    private SimpleStringProperty email;
    private SimpleIntegerProperty user_id;

    public prof() {
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

    public String getStatsociale() {
        return statsociale.get();
    }

    public void setStatsociale(String statsociale) {
        this.statsociale = new SimpleStringProperty(statsociale);
    }

    public String getDeregion() {
       return deregion.get();
    }

    public void setDeregion(String deregion) {
       this.deregion = new SimpleStringProperty(deregion);
    }

    public String getVersregion() {
          return versregion.get();
    }

    public void setVersregion(String versregion) {
        this.versregion = new SimpleStringProperty(versregion);
    }

    public String getEtablissement() {
         return etablissement.get();
    }

    public void setEtablissement(String etablissement) {
         this.etablissement = new SimpleStringProperty(etablissement);
    }
   public String getEmail() {
         return email.get();
    }

    public void setEmail(String email) {
         this.email = new SimpleStringProperty(email);
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
     public SimpleStringProperty getstatsocialeProperty(){
        return statsociale;
    }
    public SimpleStringProperty getderegionProperty(){
        return deregion;
    }
    public SimpleStringProperty getversregionProperty(){
        return versregion;
    }
    public SimpleStringProperty getetablissementProperty(){
        return etablissement;
    }
      public SimpleStringProperty getemailProperty(){
        return email;
    }
     public SimpleIntegerProperty getUserIDProperty(){
        return user_id;
    }
    @Override
    public int hashCode() {
        int hash = 5;
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
        final prof other = (prof) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "prof{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", statsociale=" + statsociale + ", deregion=" + deregion + ", versregion=" + versregion + ", etablissement=" + etablissement + ", email=" + email + ", user_id=" + user_id + '}';
    }

   
    public prof(String nom, String prenom, String statsociale, String deregion, String versregion, String etablissement, String email, Integer user_id) {
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.statsociale = new SimpleStringProperty(statsociale);
        this.deregion = new SimpleStringProperty(deregion);
        this.versregion = new SimpleStringProperty(versregion);
        this.etablissement = new SimpleStringProperty(etablissement);
        this.email = new SimpleStringProperty(email);
        this.user_id = new SimpleIntegerProperty(user_id);
    }

  

 
}
