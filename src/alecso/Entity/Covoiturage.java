/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso.Entity;

import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author wiemhjiri
 */
public class Covoiturage {
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty De;
    private SimpleStringProperty Vers;
    private SimpleIntegerProperty Escall;
    private SimpleStringProperty DateC;
    private SimpleIntegerProperty Nbplaces;
    private SimpleStringProperty Intere;
    private SimpleIntegerProperty user_id;

    public Covoiturage() {
    }
    
    
    

    public Covoiturage(String De, String Vers, Integer Escall, String DateC, Integer Nbplaces, String Intere, Integer user_id) {
        this.De = new SimpleStringProperty(De);
        this.Vers = new SimpleStringProperty(Vers);
        this.Escall = new SimpleIntegerProperty(Escall);
        this.DateC = new SimpleStringProperty(DateC);
        this.Nbplaces = new SimpleIntegerProperty(Nbplaces);
        this.Intere = new SimpleStringProperty(Intere);
        this.user_id = new SimpleIntegerProperty(user_id);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public String getDe() {
        return De.get();
    }

    public void setDe(String De) {
        this.De = new SimpleStringProperty(De);
    }

    public String getVers() {
        return Vers.get();
    }

    public void setVers(String Vers) {
        this.Vers = new SimpleStringProperty(Vers);
    }

    public int getEscall() {
        return Escall.get();
    }

    public void setEscall(int Escall) {
        this.Escall = new SimpleIntegerProperty(Escall);
    }

    public String getDateC() {
        return DateC.get();
    }

    public void setDateC(String DateC) {
        this.DateC = new SimpleStringProperty(DateC);
    }

    public int getNbplaces() {
        return Nbplaces.get();
    }

    public void setNbplaces(int Nbplaces) {
        this.Nbplaces = new SimpleIntegerProperty(Nbplaces);
    }

    public String getIntere() {
        return Intere.get();
    }

    public void setIntere(String Intere) {
        this.Intere = new SimpleStringProperty(Intere);
    }

    public int getUser_id() {
        return user_id.get();
    }

    public void setUser_id(int user_id) {
        this.user_id = new SimpleIntegerProperty(user_id);
    }
    
    public SimpleStringProperty getDeProperty(){
        return De;
    }
    public SimpleStringProperty getVersProperty(){
        return Vers;
    }
    public SimpleStringProperty getDateProperty(){
        return DateC;
    }
    public SimpleStringProperty getIntereProperty(){
        return Intere;
    }
    public SimpleIntegerProperty getEscallProperty(){
        return Escall;
    }
    public SimpleIntegerProperty getNBPProperty(){
        return Nbplaces;
    }
     public SimpleIntegerProperty getUserIDProperty(){
        return user_id;
    }


    @Override
    public String toString() {
        return "Covoiturage{" + "id=" + id + ", De=" + De.get() + ", Vers=" + Vers.get() + ", Escall=" + Escall.get() + ", DateC=" + DateC.get() + ", Nbplaces=" + Nbplaces.get() + ", Intere=" + Intere.get() + ", user_id=" + user_id.get() + '}';
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
        final Covoiturage other = (Covoiturage) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public void getEscallcoProperty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
   
    
}
