/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso.Entity;

import java.util.Objects;

/**
 *
 * @author Nawres
 */
public class CategorieC {
    private int id;
    private String libelle,description;

    public CategorieC(int id, String libelle, String description) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
    }

    public CategorieC(String libelle) {
        this.libelle = libelle;
    }
    

    public CategorieC(String libelle, String description) {
        this.libelle = libelle;
        this.description = description;
    }

    public CategorieC() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public CategorieC(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final CategorieC other = (CategorieC) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }
    
    
    
}
