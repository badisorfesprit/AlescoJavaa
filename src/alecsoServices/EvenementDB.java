/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecsoServices;

import alecso.Entity.Evenement;
import alecsoServices.CategorieDB;
import alecso.Utils.MyBDConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import javafx.scene.image.Image;

/**
 *
 * @author Chaima
 */
public class EvenementDB {



  Connection mycon = MyBDConnection.getInstanceBD().getConnection(); 
     
     
     public void ajouterEvent(Evenement E){
    
        
        try {
            Statement st = mycon.createStatement();
            String query = "insert into evenement values("+E.id+",'"+E.id_categorie+"','"+E.titre+"','"+E.nom_image+"','"+E.lieu+"','"+E.description+"','"+E.date_event+"','"+E.nbplace+"')";
            
           
            
        st.executeUpdate(query);
        } catch (SQLException ex) {
           // Logger.getLogger(PersonneDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   
    
    }
   
    
     
   public ObservableList<Evenement> afficherEvent(){
        ObservableList myList = FXCollections.observableArrayList();
        
        try {
            Statement st = mycon.createStatement();
            String str = "SELECT * FROM evenement";
            ResultSet rs = st.executeQuery(str);
            while(rs.next()){
               CategorieDB  ce=new CategorieDB();
            System.out.println("Evenement : "+rs.getInt(1)
                    +" | "+rs.getString(2)
                    +" | "+rs.getString(3)
                    +" | "+rs.getString(4)
                    +" | "+rs.getString(5)
                    +" | "+rs.getString(6)
                    +" | "+rs.getDate(7)
                    +" | "+rs.getInt(8));
                    
              myList.add(new Evenement(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),
                    rs.getString(6),rs.getDate(7),rs.getInt(8)));
               
            } 
            
        } catch (SQLException ex) {
            
            System.out.println("erreur lors de l'affichage " + ex.getMessage());
        }      
        return myList;
    }
    
   public void delete_Event(Evenement E) {

     
String requete = "delete from evenement where id = ?";
        try {
            PreparedStatement ps = mycon.prepareStatement(requete);
            ps.setInt(1, E.getId());
            ps.executeUpdate();
            System.out.println("Salles supprimé");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }    
     
}  
 public void Modifier_salle(Evenement E) {
   

        
      PreparedStatement ps ;
            String req = "UPDATE evenement set    titre= ?,date_event=?, nom_image= ?,lieu= ?,description= ? ,nbplace= ?  where id=?";
            try {
                ps = mycon.prepareStatement(req);

                ps.setString(1, E.getTitre());
                ps.setDate(2, E.getDate_event());
                 ps.setString(3, E.getNom_image());
                ps.setString(4,E.getLieu());
                ps.setString(5, E.getDescription());
               
                ps.setInt(6,E.getNbplace());
                 ps.setInt(7,E.getId());



                ps.executeUpdate();
                System.out.println("DONE UPDATE");
            } catch (SQLException ex) {
          //Logger.getLogger(BoutiqueDAO.class.getName()).log(Level.SEVERE, null, ex);

            }}



public  ObservableList<Evenement> Search(String tf){
        ObservableList myListBenef = FXCollections.observableArrayList();
        
        try {
            Statement st = mycon.createStatement();
            String str = "SELECT * FROM evenement WHERE id LIKE '%"+tf+"%'"
                        +"UNION SELECT * FROM evenement WHERE titre LIKE '%"+tf+"%'"
                        +"UNION SELECT * FROM evenement WHERE lieu LIKE '%"+tf+"%'"
                        +"UNION SELECT * FROM evenement WHERE description LIKE'%"+tf+"%'"
                        +"UNION SELECT * FROM evenement WHERE date_event LIKE '%"+tf+"%'"
                        +"UNION SELECT * FROM evenement WHERE nbplace LIKE '%"+tf+"%'"
                        +"UNION SELECT * FROM evenement WHERE nom_image LIKE '%"+tf+"%'"
                     +"UNION SELECT * FROM evenement WHERE id_categorie LIKE '%"+tf+"%'";
            ResultSet rs = st.executeQuery(str);
            while(rs.next()){
            myListBenef.add(new Evenement(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),
                    rs.getString(6),rs.getDate(7),rs.getInt(8)));
            }    
        } catch (SQLException ex) {
            
            System.out.println("erreur lors de l'affichage " + ex.getMessage());
        }      
        return myListBenef;
    }
 
  public List<Evenement> TrierTitre() {
        List<Evenement> list = new ArrayList<>();
        try {
            String sql2 = "SELECT * from evenement  order by titre ASC ";
            Statement st2 = mycon.createStatement();
            ResultSet rs = st2.executeQuery(sql2);
            while (rs.next()) {

                                Evenement e;
               e = new Evenement(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),
                    rs.getString(6),rs.getDate(7),rs.getInt(8));
               list.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return list;
        
        
    }
      
    public List<Evenement> TriernbPlace() {
        List<Evenement> list = new ArrayList<>();
        try {
            String sql2 = "SELECT * from evenement  order by nbplace ASC ";
            Statement st2 = mycon.createStatement();
            ResultSet rs = st2.executeQuery(sql2);
            while (rs.next()) {

                                Evenement e;
               e = new Evenement(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),
                    rs.getString(6),rs.getDate(7),rs.getInt(8));
               list.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return list;
        
        
    }
    
    
    public List<Evenement> TrierDate() {
        List<Evenement> list = new ArrayList<>();
        try {
            String sql2 = "SELECT * from evenement  order by  date_event ASC ";
            Statement st2 = mycon.createStatement();
            ResultSet rs = st2.executeQuery(sql2);
            while (rs.next()) {

                                Evenement e;
               e = new Evenement(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),
                    rs.getString(6),rs.getDate(7),rs.getInt(8));
               list.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return list;
        
        
    }
      public int Notif() {
        try {
            String sql2 = "SELECT count(*) FROM `evenement` WHERE date_event = CURRENT_DATE  ";
            Statement st2 = mycon.createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);
            
            while(rs2.next())
            {
                //return 0;
                return rs2.getInt(1);            
                //System.out.println(rs2.getInt(1)); 
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return -1;
        
        
    }
      public List<Evenement> afficherEvenementsAjourdhui() {
        List<Evenement> list = new ArrayList<>();
        try {
            String sql2 = "SELECT * FROM `evenement` WHERE date_event = CURRENT_DATE  ; ";
            Statement st2 = mycon.createStatement();
            ResultSet rs = st2.executeQuery(sql2);
            while (rs.next()) {

                                Evenement e;
                       e = new Evenement(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),
                    rs.getString(6),rs.getDate(7),rs.getInt(8));
                        
                                list.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return list;
        
        
    }
     
    /* public void Reserver(int idr) throws SQLException, IOException 
{
        
  //  int id = fos_user.getIdcnct();
 String req1 =  "SELECT `id`,`nbplace` FROM `evenement` WHERE `id`="+idr;
        try {
            PreparedStatement ste = mycon.prepareStatement(req1);
            int x = ste.executeUpdate(req1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

}
    
 */
  public void quantiteReduce(int idr) throws SQLException, IOException 
{
        
   
 String req1 =  "UPDATE `evenement` " + "SET `nbplace` = `nbplace`-1" + " WHERE `id`="+idr+ " and `nbplace`>"+ 0;
        try {
            PreparedStatement ste = mycon.prepareStatement(req1);
            int x = ste.executeUpdate(req1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

} 
 
     
      
     public String afficher_contrat_id(int id)
    {  
        String msg="";
        try {
            Statement st2 = mycon.createStatement();
            String requete ="select * from evenement where id='"+id+"'" ;
            ResultSet rs = st2.executeQuery(requete);
            while(rs.next())
            {
              msg = "id :  " +rs.getInt(2)
                        +"\r\n" +"lieu :  "+rs.getString(3)
                        +"\r\n" +    "Image :  "+rs.getString(4)
                        +"\r\n" +" : titre "+rs.getString(5)
                        +"\r\n" +"  description  :   "+rs.getString(6) 
                       +"\r\n" +"  Date de l'evenement  :   "+rs.getDate(7);
                         //+"\r\n" +"  id_categorie  :   "+rs.getInt(8);
            }    
        }  catch (SQLException ex) {   
             System.out.println("Pas de pdf" + ex.getMessage());
        } 
        return msg ;
    }
     
     
      public String getLibelle(int id) throws SQLException{
      String l=null;
    
    String req = "select libelle from categorie  where id="+ id+ " limit 1"; 
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
      
}