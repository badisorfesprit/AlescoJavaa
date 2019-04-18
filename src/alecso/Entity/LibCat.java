/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso.Entity;

/**
 *
 * @author LINA
 */
public class LibCat {
    private int idC;
     private String libelle;
    private String description;

    public LibCat() {
    }

    public LibCat(int idC, String libelle, String description) {
        this.idC = idC;
        this.libelle = libelle;
        this.description = description;
    }
    
    

    public LibCat(String libelle, String description) {
        this.libelle = libelle;
        this.description = description;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
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
    
    
    
    
    
}
