/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecsoServices;

import static alecso.AnnoncesfrontController.nbinscriptions;
import static alecso.AnnoncesfrontController.placeamodifier;
import alecso.Entity.Club;
import alecso.Entity.fos_user;
import alecso.Entity.inscetudiant;
import static alecso.InscetudiantController.nbinsc;
import static alecso.InscetudiantController.placeupdate;
import alecso.Utils.MyBDConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nawres
 */
public class inscetudiantCrud {
     Statement ste;
    Connection con=MyBDConnection.getInstanceBD().getConnection();
    
     public inscetudiantCrud() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(inscetudiantCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void addenfant(inscetudiant r,int id_club){
      int id = fos_user.getIdcnct();
     // Club b = new Club(id_club);
   java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

 String req = "INSERT INTO `inscenfant` " + "(`club_id`,`user_id`,`datenaissance`,`ecole`,`adresse`,`dateinsc`)" + " VALUES ('"
                + id_club+ "','"+ id + "','"+ r.getDatenaissance() +"','"+ r.getEcole()+"','"+ r.getAdresse()+"','"+ date + "' )";
 
  
        try {
            PreparedStatement ste = con.prepareStatement(req);
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        
        
        int nvnombre = (nbinscriptions + 1);
          String requete = "UPDATE `club` SET `nbinscriptions`='"+nvnombre+"' WHERE `id`='"+ id_club +"'";
            try{
                
           PreparedStatement statement = con.prepareStatement(requete);
            
           statement.executeUpdate();
            }
            catch (SQLException ex) {

}
            int Nvplace =( placeamodifier - 1); 
            
            String requete2 = "UPDATE `club` SET `place`='"+Nvplace+"' WHERE `id`='"+ id_club +"'";
            try{
                
           PreparedStatement statement = con.prepareStatement(requete2);
           
           statement.executeUpdate();
            }
            catch (SQLException ex) {

}
         String complet="complet";
            
            if (Nvplace == 0 ) {
                 try {
            String req3 = "UPDATE `club` SET `etat`='"+complet+"' WHERE `id`='"+ id_club +"'";
            PreparedStatement ps = con.prepareStatement(req3);
            
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            
        }
                
            }
    }
     public List<inscetudiant> Displayinsc(){
        List<inscetudiant> insc = new ArrayList<>();
        try {
            String req = "select e.*,c.place,c.nbinscriptions from inscenfant e INNER JOIN club c";
            PreparedStatement ps = con.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                inscetudiant e = new inscetudiant(rs.getInt(1), rs.getInt(2),rs.getInt(3), rs.getDate(4), rs.getString(5), rs.getString(6),rs.getDate(7),rs.getInt(8),rs.getInt(9));
                insc.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return insc;
    }
     public List<inscetudiant> Displayoneinsc(int id){
        List<inscetudiant> insc = new ArrayList<>();
        try {
            String req = "select e.*,c.place,c.nbinscriptions from inscenfant e INNER JOIN club c where c.user_id="+id;
            PreparedStatement ps = con.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                inscetudiant e = new inscetudiant(rs.getInt(1), rs.getInt(2),rs.getInt(3), rs.getDate(4), rs.getString(5), rs.getString(6),rs.getDate(7),rs.getInt(8),rs.getInt(9));
                insc.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return insc;
    }
     public  void Deleteinsc(int id,int id_club){
        
        try {
            PreparedStatement pt=con.prepareStatement("Delete from inscenfant where id="+id); //Delete e.id,e.club_id,e.user_id,e.datenaissance,e.ecole,e.adresse,e.dateinsc from inscenfant e INNER JOIN club c where e.club_id= '"+id_club+"' and where e.id='"+id"'
           // pt.setInt(1, id);
            //pt.setInt(2, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(inscetudiantCrud.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
         int nvnombre = (nbinsc - 1);
          String requete = "UPDATE `club` SET `nbinscriptions`='"+nvnombre+"' WHERE `id`='"+ id_club +"'";
            try{
                
           PreparedStatement statement = con.prepareStatement(requete);
            
           statement.executeUpdate();
            }
            catch (SQLException ex) {

}
            int Nvplace =( placeupdate + 1); 
            
            String requete2 = "UPDATE `club` SET `place`='"+Nvplace+"' WHERE `id`='"+ id_club +"'";
            try{
                
           PreparedStatement statement = con.prepareStatement(requete2);
           
           statement.executeUpdate();
            }
            catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

}
         String complet="complet";
         String dispo="places disponibles";
         
            
            if (Nvplace == 0 ) {
                 try {
            String req3 = "UPDATE `club` SET `etat`='"+complet+"' WHERE `id`='"+ id_club +"'";
            PreparedStatement ps = con.prepareStatement(req3);
            
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            
        }
                
            }
            else {
                 try {
            String req3 = "UPDATE `club` SET `etat`='"+dispo+"' WHERE `id`='"+ id_club +"'";
            PreparedStatement ps = con.prepareStatement(req3);
            
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            
        }
                
            }
        }
     public void UpdateEvent(inscetudiant e){
        try {
           String req="UPDATE inscenfant SET `datenaissance`='"+e.getDatenaissance()+"',`ecole`='"+e.getEcole()+
           "',`adresse`='"+e.getAdresse()+"'";

            ste= con.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(inscetudiantCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public List<inscetudiant> Displayclubinsc(int id) 
{
     List<inscetudiant> clubs = new ArrayList<>();
        try {
           String req = "SELECT e.*,f.username FROM inscenfant e INNER JOIN fos_user f ON f.id=e.user_id INNER JOIN club cl ON e.club_id="+id;
            //String req = "SELECT cl.nom, cl.delai ,cl.place ,cl.etat , cl.nbinscriptions ,l.libelle FROM club cl INNER JOIN categorie_c l ON cl.CategorieC=l.id";
            
            PreparedStatement ps = con.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                inscetudiant i = new inscetudiant(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7));
               
               //Club c = new Club(rs.getString(3),rs.getDate(9),rs.getInt(10),rs.getString(11),rs.getInt(12), new CrudCatC().findById(rs.getInt(14)));
              //this Club c = new Club(rs.getString(1),rs.getDate(2),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getString(6));
                clubs.add(i);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clubs;
}
     ////***notification****
      public int Notif() {
        try {
            String sql2 = "SELECT count(*) FROM `inscenfant` WHERE dateinsc = CURRENT_DATE ";
            Statement st2 = con.createStatement();
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
      public int verif(int id)
      {
          try {
            String sql2 = "select e.user_id from inscenfant e INNER JOIN club cl on cl.id= "+id;
            Statement st2 = con.createStatement();
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
    
}
