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
public class Prod {
    private int idProd;
    private String nomImage;
    private int idUser;
    private String nom;
    private String description;
    private int prix;
    private int
           categorie;
    private int stockQty;

    public Prod() {
    }

    public Prod(String nom, String description, int prix, int stockQty) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.stockQty = stockQty;
    }
    

    public Prod(int idProd, String nomImage, String nom, String description, int prix, int categorie, int stockQty) {
        this.idProd = idProd;
        this.nomImage = nomImage;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.categorie = categorie;
        this.stockQty = stockQty;
    }

    public Prod(String nomImage, int idUser, String nom, String description, int prix, int categorie, int stockQty) {
        this.nomImage = nomImage;
        this.idUser = idUser;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.categorie = categorie;
        this.stockQty = stockQty;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public String getNomImage() {
        return nomImage;
    }

    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public int getStockQty() {
        return stockQty;
    }

    public void setStockQty(int stockQty) {
        this.stockQty = stockQty;
    }
    
    
    
    
    

}