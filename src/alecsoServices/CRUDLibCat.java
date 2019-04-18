/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecsoServices;

import alecso.Utils.MyBDConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import alecso.Entity.LibCat;
import alecso.Entity.Prod;
import alecso.Entity.fos_user;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author LINA
 */
public class CRUDLibCat {
     Connection mycon;
    Statement st;
    ResultSet rst;
    
    
    public CRUDLibCat(){
     mycon = MyBDConnection.getInstanceBD().getConnection();
    }
    
   public  void ajouterCat(LibCat s){
  
                    try {
            String req = "INSERT INTO categories (libelle,description) VALUES (?,?)";
            PreparedStatement pst = mycon.prepareStatement(req);
            
            pst.setString(1, s.getLibelle());
            pst.setString(2, s.getDescription());
           
            
            int exec = pst.executeUpdate();
            System.out.println("Categorie ajoutée avec succès!");
        } catch (SQLException ex) {
            
        }
     }
   
  public void addToComboBox() throws SQLException, ClassNotFoundException
    {
    
    }
  
     
     
    
   
  public List<LibCat> afficherComb() throws SQLException
{
    List<LibCat> cat = new ArrayList<>();
    String req = "select * from categories ";
    PreparedStatement preparedStatement;
        try {
         preparedStatement = mycon.prepareStatement(req);
         ResultSet result = preparedStatement.executeQuery();
                     while (result.next()) {
                LibCat lib;
                lib = new LibCat();
                lib.setLibelle(result.getString("libelle"));
               lib.setIdC(result.getInt("id"));
                cat.add(lib);
                
            }
        }
           catch (SQLException ex) {
                        ex.printStackTrace();
            }
          return cat;
}
   
   
  public List<LibCat> afficherCat() throws SQLException
{
    List<LibCat> cat = new ArrayList<>();
    String req = "select * from categories ";
    PreparedStatement preparedStatement;
        try {
         preparedStatement = mycon.prepareStatement(req);
         ResultSet result = preparedStatement.executeQuery();
                     while (result.next()) {
                LibCat lib;
                lib = new LibCat();
                lib.setLibelle(result.getString("libelle"));
                lib.setDescription(result.getString("description"));
                lib.setIdC(result.getInt("id"));
                cat.add(lib);
                
            }
        }
           catch (SQLException ex) {
                        ex.printStackTrace();
            }
          return cat;
}
  
     public void supprimerLibCat(int id) throws SQLException {
        MyBDConnection cnx = MyBDConnection.getInstanceBD();

        String req = "DELETE FROM `categories` WHERE `id`="+id;
        try 
        {
            PreparedStatement statement = cnx.getConnection().prepareStatement(req);
            statement.executeUpdate();
        } 
        catch (SQLException ex) {
        }} 

    public void ModifierLibCat(LibCat obj,int idC) {
        MyBDConnection cnx = MyBDConnection.getInstanceBD();

        String req = "UPDATE `categories` SET `libelle`=?,`description`=? WHERE id =?";

        try {
            PreparedStatement statement = cnx.getConnection().prepareStatement(req);
            
            statement.setString(1, obj.getLibelle());
            statement.setString(2,obj.getDescription());
           
            statement.setInt(3, idC);
          

            statement.executeUpdate();

        } catch (SQLException ex) {

            ex.printStackTrace();

        }
}    

  
   


}
