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
public class Panier {
    private int id;
    private int user_id;
    private int total;
    private int quantite;
    private int idProd;
      private int idConn;
      private String Prod;

   
      
      

    public Panier(int id, int user_id, int total, int quantite, int idProd, int idConn, String Prod) {
        this.id = id;
        this.user_id = user_id;
        this.total = total;
        this.quantite = quantite;
        this.idProd = idProd;
        this.idConn = idConn;
        this.Prod = Prod;
    }
      
      

    public String getProd() {
        return Prod;
    }

    public void setProd(String Prod) {
        this.Prod = Prod;
    }

    public Panier() {
    }

    public int getIdConn() {
        return idConn;
    }

    public void setIdConn(int idConn) {
        this.idConn = idConn;
    }

    public Panier(int user_id, int total, int quantite, int idProd, int idConn) {
        this.user_id = user_id;
        this.total = total;
        this.quantite = quantite;
        this.idProd = idProd;
        this.idConn = idConn;
    }

    public Panier(int id, int total, int quantite) {
        this.id = id;
        this.total = total;
        this.quantite = quantite;
    }
    
    
    

    public Panier(int user_id, int total, int quantite, int idProd) {
        this.user_id = user_id;
        this.total = total;
        this.quantite = quantite;
        this.idProd = idProd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }



    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }
    
    
    
}
