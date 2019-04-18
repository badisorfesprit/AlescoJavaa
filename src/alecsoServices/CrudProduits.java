/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecsoServices;

import alecso.Entity.LibCat;
import alecso.Entity.Panier;
import alecso.Entity.Prod;
import alecso.Entity.fos_user;
import alecso.ProdAllController;
import alecso.Utils.MyBDConnection;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author LINA
 */
public class CrudProduits {
     Connection mycon;
    Statement st;
    ResultSet rst;
      private String imageUpload;
    
    public CrudProduits() {
    mycon = MyBDConnection.getInstanceBD().getConnection();
    
  }
    
    public List<Prod> afficherProd() throws SQLException
{
    List<Prod> cat = new ArrayList<>();
     //String pattern = "yyyy-MM-dd";
    //SimpleDateFormat format = new SimpleDateFormat(pattern);
   String req = "SELECT * FROM `produits` WHERE `user_id`!="+fos_user.getIdcnct(); 
    PreparedStatement preparedStatement;
        try {
         preparedStatement = mycon.prepareStatement(req);
         ResultSet result = preparedStatement.executeQuery();
                     while (result.next()) {
                Prod p;
                p = new Prod();
                p.setNom(result.getString("nom"));
                p.setDescription(result.getString("description"));
                p.setIdProd(result.getInt("id"));
                p.setPrix(result.getInt("prix"));
                p.setStockQty(result.getInt("stock_qty"));
                p.setCategorie(result.getInt("id_categorie"));
                p.setNomImage(result.getString("nom_image"));
                
                cat.add(p);
                
            }
        }
           catch (SQLException ex) {
                        ex.printStackTrace();
            }
          return cat;
}
     public List<Prod> AfficherMesProd() throws SQLException
{
    List<Prod> cat = new ArrayList<>();
     //String pattern = "yyyy-MM-dd";
    //SimpleDateFormat format = new SimpleDateFormat(pattern);
   String req = "SELECT * FROM `produits` WHERE `user_id`="+fos_user.getIdcnct(); 
    PreparedStatement preparedStatement;
        try {
         preparedStatement = mycon.prepareStatement(req);
         ResultSet result = preparedStatement.executeQuery();
                     while (result.next()) {
                Prod p;
                p = new Prod();
                p.setNom(result.getString("nom"));
                p.setDescription(result.getString("description"));
                p.setIdProd(result.getInt("id"));
                p.setPrix(result.getInt("prix"));
                p.setStockQty(result.getInt("stock_qty"));
                p.setCategorie(result.getInt("id_categorie"));
                p.setNomImage(result.getString("nom_image"));
                
                cat.add(p);
                
            }
        }
           catch (SQLException ex) {
                        ex.printStackTrace();
            }
          return cat;
}
    
    public List<Prod> AfficherAdminProd() throws SQLException
{
    List<Prod> p = new ArrayList<>();
     //String pattern = "yyyy-MM-dd";
    //SimpleDateFormat format = new SimpleDateFormat(pattern);
    String req = "SELECT * FROM `produits` "; 
    PreparedStatement preparedStatement;
        try {
         preparedStatement = mycon.prepareStatement(req);
         ResultSet result = preparedStatement.executeQuery();
                     while (result.next()) {
                Prod c;
                c = new Prod();
                c.setIdUser(result.getInt("user_id"));
                
                c.setNom(result.getString("nom"));
                c.setDescription(result.getString("description"));
                c.setIdProd(result.getInt("id"));
                c.setPrix(result.getInt("prix"));
                c.setStockQty(result.getInt("stock_qty"));
                c.setCategorie(result.getInt("id_categorie"));
                c.setNomImage(result.getString("nom_image")); 
                
               
                p.add(c);
                
            }
        }
           catch (SQLException ex) {
                        ex.printStackTrace();
            }
          return p;
}
    
    
    
 public String getLibelle(int id) throws SQLException{
      String l=null;
    
    String req = "select libelle from categories  where id="+ id+ " limit 1"; 
     PreparedStatement preparedStatement;
        try {
         preparedStatement = mycon.prepareStatement(req);
         ResultSet result = preparedStatement.executeQuery();
                     while (result.next()) {
             l=result.getString("libelle");
                
            }
        }
           catch (SQLException ex) {
                        System.out.println(ex.getMessage());
            }
          return l;
}
 
  public String getProp(int id) throws SQLException{
      String l=null;
    
    String req = "select nom from fos_user  where id="+ id+ " limit 1"; 
     PreparedStatement preparedStatement;
        try {
         preparedStatement = mycon.prepareStatement(req);
         ResultSet result = preparedStatement.executeQuery();
                     while (result.next()) {
             l=result.getString("nom");
                
            }
        }
           catch (SQLException ex) {
                        System.out.println(ex.getMessage());
            }
          return l;
}
 
    
public void AjouterProduit(Prod p) throws SQLException, IOException 
{
        
    int id = fos_user.getIdcnct();
 String req1 = "INSERT INTO `produits` " + "(`user_id`,`stock_qty`,`nom_image`,`nom`,`description`,`prix`,`id_categorie`)" + " VALUES ('"
                + id+ "','"+ p.getStockQty()+ "','" + p.getNomImage()+ "','" + p.getNom()+ "','" + p.getDescription()+ "','" + p.getPrix()+ "','" + p.getCategorie()+ "') ";
        try {
            PreparedStatement ste = mycon.prepareStatement(req1);
            int x = ste.executeUpdate(req1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

}
 public void addUser() throws SQLException, IOException 
{
        
    int id = fos_user.getIdcnct();
 String req1 = "UPDATE  `panier` " + "SET `idConn` = "+id + " ORDER BY `id` DESC LIMIT 1";
        try {
            PreparedStatement ste = mycon.prepareStatement(req1);
            int x = ste.executeUpdate(req1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

} 
 
 
  public void supprimerProd(int id) throws SQLException {
        MyBDConnection cnx = MyBDConnection.getInstanceBD();

        String req = "DELETE FROM `produits` WHERE `id`="+id;
        try 
        {
            PreparedStatement statement = cnx.getConnection().prepareStatement(req);
            statement.executeUpdate();
        } 
        catch (SQLException ex) {
        }} 


  
 public void ModifierProd(Prod obj,int id) {
        MyBDConnection cnx = MyBDConnection.getInstanceBD();

        String req = "UPDATE `produits` SET `nom`=?,`description`=? ,`prix`=?,`stock_qty`=? ,`id_categorie`=? WHERE id =?";

        try {
            PreparedStatement statement = cnx.getConnection().prepareStatement(req);
            
            statement.setString(1, obj.getNom());
            statement.setString(2,obj.getDescription());
            statement.setString(3,Integer.toString(obj.getPrix()));
             statement.setString(4,Integer.toString(obj.getStockQty()));
     statement.setInt(5, obj.getCategorie());
            statement.setInt(6, id);
          

            statement.executeUpdate();

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }}
 
public void Reserver(int idr) throws SQLException, IOException 
{
        
    int id = fos_user.getIdcnct();
    PanierService pa=new PanierService();
   List<Panier> p=pa.AfficherPanier();
   List<Panier> rsList=new ArrayList<>();
   for(int i=0;i<p.size();i++){
       if(p.get(i).getIdProd()==idr){
           rsList.add(p.get(i));
       }
   }
   
   String req1;
   if(rsList.size()==0){
  req1 = "INSERT INTO `panier` " + "(`user_id`,`quantite`,`total`,`idProd`)" + "SELECT `user_id`,1,`prix`,`id` FROM `produits` WHERE `id`="+idr ;
   }else{
  req1 = "Update `panier` " + "set quantite= quantite+1  WHERE `idProd`="+idr+" AND "+"idConn="+id;

   }
        try {
            PreparedStatement ste = mycon.prepareStatement(req1);
            int x = ste.executeUpdate(req1);
              System.out.println("ajout panier");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

} 
 
  public void quantiteReduce(int idr) throws SQLException, IOException 
{
        
   
 String req1 = "UPDATE `produits` " + "SET `stock_qty` = `stock_qty`-1" + " WHERE `id`="+idr+ " and `stock_qty`>"+ 0;
        try {
            PreparedStatement ste = mycon.prepareStatement(req1);
            int x = ste.executeUpdate(req1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

} 
  
    
   
  
    
}