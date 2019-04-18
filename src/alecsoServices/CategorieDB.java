/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecsoServices;

import alecso.Entity.Evenement;
import alecso.Entity.categorie;
import alecso.Utils.MyBDConnection;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author Chaima
 */
public class CategorieDB {



  Connection mycon = MyBDConnection.getInstanceBD().getConnection(); 
     
     
     public void ajouterEvent(categorie C){
    
        
        try {
            Statement st = mycon.createStatement();
            String query = "insert into categorie values("+C.id+",'"+C.libelle+"','"+C.description+"')";
            
           
            
        st.executeUpdate(query);
        } catch (SQLException ex) {
           // Logger.getLogger(PersonneDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   
    
    }
   
    
     
   public ObservableList<categorie> afficherCategorie(){
        ObservableList myList = FXCollections.observableArrayList();
        
        try {
            Statement st = mycon.createStatement();
            String str = "SELECT * FROM categorie";
            ResultSet rs = st.executeQuery(str);
            while(rs.next()){
            System.out.println("Categorie : "+rs.getInt(1)
                    +" | "+rs.getString(2)
                    +" | "+rs.getString(3)
                    );
                    
              myList.add(new categorie(rs.getInt(1),rs.getString(2),rs.getString(3)
                    ));
            }    
        } catch (SQLException ex) {
            
            System.out.println("erreur lors de l'affichage " + ex.getMessage());
        }      
        return myList;
    }
   
   public void delete_Categorie(categorie c) {

     
String requete = "delete from categorie where id = ?";
        try {
            PreparedStatement ps = mycon.prepareStatement(requete);
            ps.setInt(1, c.getId());
            ps.executeUpdate();
            System.out.println("Categorie supprimé");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }    
     
}  
   

  public void Modifier_salle(categorie c) {
   

        
      PreparedStatement ps ;
            String req = "UPDATE categorie set libelle = ? , description = ?   where id=?";
            try {
                ps = mycon.prepareStatement(req);

                   ps.setString(1, c.getLibelle());
                   ps.setString(2, c.getDescription());
                   ps.setInt(3,c.getId());



                ps.executeUpdate();
                System.out.println("DONE UPDATE");
            } catch (SQLException ex) {
          //Logger.getLogger(BoutiqueDAO.class.getName()).log(Level.SEVERE, null, ex);

            }}
 	

public  ObservableList<categorie> Search(String tf){
        ObservableList myListBenef = FXCollections.observableArrayList();
        
        try {
            Statement st = mycon.createStatement();
            String str = "SELECT * FROM categorie WHERE id LIKE '%"+tf+"%'"
                        +"UNION SELECT * FROM categorie WHERE libelle LIKE '%"+tf+"%'"
                        +"UNION SELECT * FROM categorie WHERE description LIKE '%"+tf+"%'";
                        
            ResultSet rs = st.executeQuery(str);
            while(rs.next()){
            myListBenef.add(new categorie(rs.getInt(1),rs.getString(2),rs.getString(3)
                   ));
            }    
        } catch (SQLException ex) {
            
            System.out.println("erreur lors de l'affichage " + ex.getMessage());
        }      
        return myListBenef;
    }
 
  public List<categorie> afficherComb() throws SQLException
{
   List<categorie> cat = new ArrayList<>();
     //String pattern = "yyyy-MM-dd";
    //SimpleDateFormat format = new SimpleDateFormat(pattern);
    String req = "select * from categorie ";
    PreparedStatement preparedStatement;
        try {
         preparedStatement = mycon.prepareStatement(req);
         ResultSet result = preparedStatement.executeQuery();
                     while (result.next()) {
                 categorie lib;
                lib=new categorie();
                lib.setLibelle(result.getString("libelle"));
               lib.setId(result.getInt("id"));
                cat.add(lib);
                
            }
        }
           catch (SQLException ex) {
                        ex.printStackTrace();
            }
          return cat;
}
   
}