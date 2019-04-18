/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecsoServices;

import alecso.Entity.Club;
import alecso.Entity.fos_user;
import alecso.Utils.MyBDConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.time.Instant.now;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nawres
 */
public class CrudAnnonce {
  
   Connection con;
    Statement st;
    ResultSet rst;
    CrudUser as= new CrudUser();
    public CrudAnnonce() {
    con = MyBDConnection.getInstanceBD().getConnection();
  }
    
    public void AddAnnonce(Club c){
        try {
            String req = "INSERT INTO club (user_id,nom,description,affiche,adresse,ville,telephone,delai,place,CategorieC,prolongation) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(req);
            
            pst.setInt(1, fos_user.getIdcnct());
            pst.setString(2, c.getNom());
            pst.setString(3, c.getDescription()); 
            pst.setString(4, c.getAffiche());
            pst.setString(5, c.getAdresse());
            pst.setString(6, c.getVille());
            pst.setString(7, c.getTelephone());
            pst.setDate(8, c.getDelai());
            pst.setInt(9, c.getPlace());
            //pst.setString(10, "places disponibles");
            //pst.setInt(11, 0);
            //pst.setDate(12,date);
            pst.setInt(10, c.getCat().getId());
            pst.setInt(11, c.getProlongation());
            
            
            int exec = pst.executeUpdate();
            System.out.println("Annonce ajouté avec succès!");
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonce.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Club> Displayadmin() 
{
     List<Club> clubs = new ArrayList<>();
        try {
           String req = "SELECT cl.*,l.libelle FROM club cl INNER JOIN categorie_c l ON cl.CategorieC=l.id";
            //String req = "SELECT cl.nom, cl.delai ,cl.place ,cl.etat , cl.nbinscriptions ,l.libelle FROM club cl INNER JOIN categorie_c l ON cl.CategorieC=l.id";
            
            PreparedStatement ps = con.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Club c = new Club(rs.getInt(1), rs.getInt(2), 
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), 
                        rs.getDate(9), rs.getInt(10),rs.getString(11),rs.getInt(12), rs.getDate(13), rs.getInt(14), rs.getInt(15),rs.getString(16));
               
               //Club c = new Club(rs.getString(3),rs.getDate(9),rs.getInt(10),rs.getString(11),rs.getInt(12), new CrudCatC().findById(rs.getInt(14)));
              //this Club c = new Club(rs.getString(1),rs.getDate(2),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getString(6));
                clubs.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clubs;
}
    public List<Club> Displayoneadmin(int id) 
{
     List<Club> clubs = new ArrayList<>();
        try {
           String req = "SELECT cl.*,l.libelle FROM club cl INNER JOIN categorie_c l ON cl.CategorieC=l.id where cl.user_id="+id;
            //String req = "SELECT cl.nom, cl.delai ,cl.place ,cl.etat , cl.nbinscriptions ,l.libelle FROM club cl INNER JOIN categorie_c l ON cl.CategorieC=l.id";
            
            PreparedStatement ps = con.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Club c = new Club(rs.getInt(1), rs.getInt(2), 
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), 
                        rs.getDate(9), rs.getInt(10),rs.getString(11),rs.getInt(12), rs.getDate(13), rs.getInt(14), rs.getInt(15),rs.getString(16));
               
               //Club c = new Club(rs.getString(3),rs.getDate(9),rs.getInt(10),rs.getString(11),rs.getInt(12), new CrudCatC().findById(rs.getInt(14)));
              //this Club c = new Club(rs.getString(1),rs.getDate(2),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getString(6));
                clubs.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clubs;
}
     public List<Club> Displaydetails(int id) 
{
     List<Club> clubs = new ArrayList<>();
        try {
            
          String req = "select nom,description,adresse,telephone,delai from club";
            
            PreparedStatement ps = con.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               Club c = new Club(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),rs.getInt(6));
               
               //Club c = new Club(rs.getString(3),rs.getDate(9),rs.getInt(10),rs.getString(11),rs.getInt(12), new CrudCatC().findById(rs.getInt(14)));
              //this Club c = new Club(rs.getString(1),rs.getDate(2),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getString(6));
                clubs.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clubs;
}
          public List<Club> p(int id) 
{
     List<Club> clubs = new ArrayList<>();
        try {
            
          String req = "select prolongation from club where id"+id;
            
            PreparedStatement ps = con.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               Club c = new Club(rs.getInt(1));
               
               //Club c = new Club(rs.getString(3),rs.getDate(9),rs.getInt(10),rs.getString(11),rs.getInt(12), new CrudCatC().findById(rs.getInt(14)));
              //this Club c = new Club(rs.getString(1),rs.getDate(2),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getString(6));
                clubs.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clubs;
}
    
    public List<Club> Displayfront() 
{
     List<Club> clubs = new ArrayList<>();
        try {
            String req = "select * from club where delai>CURRENT_DATE ";
            PreparedStatement ps = con.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Club c = new Club (rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5)
                        ,rs.getString(6),rs.getString(7),rs.getString(8),rs.getDate(9),
                        rs.getInt(10),rs.getString(11),rs.getInt(12),rs.getDate(13));
               //Club c = new Club(rs.getString(3),rs.getDate(9),rs.getInt(10),rs.getString(11),rs.getInt(12), new CrudCatC().findById(rs.getInt(14)));
                clubs.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clubs;
}
     public void Deleteannonce(int id){
       
       
        MyBDConnection cnx = MyBDConnection.getInstanceBD();

        String req = "DELETE FROM `club` WHERE `id`="+id;
        try 
        {
            PreparedStatement statement = cnx.getConnection().prepareStatement(req);
            statement.executeUpdate();
        } 
        catch (SQLException ex) {
        }
    
    }
     public void updateannonce(Club obj,int idM) {
        MyBDConnection cnx = MyBDConnection.getInstanceBD();

        String req = "UPDATE `club` SET `nom`=?,`description`=?,`affiche`=?,`adresse`=?,`ville`=?,`telephone`=?,`delai`=?,`place`=?,`CategorieC`=?,`prolongation`=? WHERE id =?";

        try {
            PreparedStatement statement = cnx.getConnection().prepareStatement(req);
            
            statement.setString(1, obj.getNom());
            statement.setString(2,obj.getDescription());
           
            statement.setString(3, obj.getAffiche());
            statement.setString(4, obj.getAdresse());
            statement.setString(5, obj.getVille());
            statement.setString(6, obj.getTelephone());
            statement.setDate(7, obj.getDelai());
            statement.setInt(8, obj.getPlace());
            statement.setInt(9, obj.getCat().getId());
            statement.setInt(10, obj.getProlongation());
            statement.setInt(11, idM);
            

            statement.executeUpdate();

        } catch (SQLException ex) {

            ex.printStackTrace();
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }
}  
     ///lets start el trie yohoooooo
     
     public List<Club> Trieinscc() 
{
     List<Club> clubs = new ArrayList<>();
        try {
           String req = "SELECT cl.*,l.libelle FROM club cl INNER JOIN categorie_c l ON cl.CategorieC=l.id order by cl.nbinscriptions ASC ";
            //String req = "SELECT cl.nom, cl.delai ,cl.place ,cl.etat , cl.nbinscriptions ,l.libelle FROM club cl INNER JOIN categorie_c l ON cl.CategorieC=l.id";
            
            PreparedStatement ps = con.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Club c = new Club(rs.getInt(1), rs.getInt(2), 
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), 
                        rs.getDate(9), rs.getInt(10),rs.getString(11),rs.getInt(12), rs.getDate(13), rs.getInt(14), rs.getString(15));
               
               //Club c = new Club(rs.getString(3),rs.getDate(9),rs.getInt(10),rs.getString(11),rs.getInt(12), new CrudCatC().findById(rs.getInt(14)));
              //this Club c = new Club(rs.getString(1),rs.getDate(2),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getString(6));
                clubs.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clubs;
}
     public List<Club> Trieinscd() 
{
     List<Club> clubs = new ArrayList<>();
        try {
           String req = "SELECT cl.*,l.libelle FROM club cl INNER JOIN categorie_c l ON cl.CategorieC=l.id order by cl.nbinscriptions DESC ";
            //String req = "SELECT cl.nom, cl.delai ,cl.place ,cl.etat , cl.nbinscriptions ,l.libelle FROM club cl INNER JOIN categorie_c l ON cl.CategorieC=l.id";
            
            PreparedStatement ps = con.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Club c = new Club(rs.getInt(1), rs.getInt(2), 
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), 
                        rs.getDate(9), rs.getInt(10),rs.getString(11),rs.getInt(12), rs.getDate(13), rs.getInt(14), rs.getString(15));
               
               //Club c = new Club(rs.getString(3),rs.getDate(9),rs.getInt(10),rs.getString(11),rs.getInt(12), new CrudCatC().findById(rs.getInt(14)));
              //this Club c = new Club(rs.getString(1),rs.getDate(2),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getString(6));
                clubs.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clubs;
}
     public List<Club> Triedelaic() 
{
     List<Club> clubs = new ArrayList<>();
        try {
           String req = "SELECT cl.*,l.libelle FROM club cl INNER JOIN categorie_c l ON cl.CategorieC=l.id order by cl.delai ASC ";
            //String req = "SELECT cl.nom, cl.delai ,cl.place ,cl.etat , cl.nbinscriptions ,l.libelle FROM club cl INNER JOIN categorie_c l ON cl.CategorieC=l.id";
            
            PreparedStatement ps = con.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Club c = new Club(rs.getInt(1), rs.getInt(2), 
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), 
                        rs.getDate(9), rs.getInt(10),rs.getString(11),rs.getInt(12), rs.getDate(13), rs.getInt(14), rs.getString(15));
               
               //Club c = new Club(rs.getString(3),rs.getDate(9),rs.getInt(10),rs.getString(11),rs.getInt(12), new CrudCatC().findById(rs.getInt(14)));
              //this Club c = new Club(rs.getString(1),rs.getDate(2),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getString(6));
                clubs.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clubs;
}
      public List<Club> Triedelaid() 
{
     List<Club> clubs = new ArrayList<>();
        try {
           String req = "SELECT cl.*,l.libelle FROM club cl INNER JOIN categorie_c l ON cl.CategorieC=l.id order by cl.delai DESC ";
            //String req = "SELECT cl.nom, cl.delai ,cl.place ,cl.etat , cl.nbinscriptions ,l.libelle FROM club cl INNER JOIN categorie_c l ON cl.CategorieC=l.id";
            
            PreparedStatement ps = con.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Club c = new Club(rs.getInt(1), rs.getInt(2), 
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), 
                        rs.getDate(9), rs.getInt(10),rs.getString(11),rs.getInt(12), rs.getDate(13), rs.getInt(14), rs.getString(15));
               
               //Club c = new Club(rs.getString(3),rs.getDate(9),rs.getInt(10),rs.getString(11),rs.getInt(12), new CrudCatC().findById(rs.getInt(14)));
              //this Club c = new Club(rs.getString(1),rs.getDate(2),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getString(6));
                clubs.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clubs;
}
      public List<Club> grpdispo() 
{
     List<Club> clubs = new ArrayList<>();
        try {
            //String complet="places disponibles";
           String req = "SELECT cl.*,l.libelle FROM club cl INNER JOIN categorie_c l ON cl.CategorieC=l.id where cl.etat = \"places disponibles\"";
            //String req = "SELECT cl.nom, cl.delai ,cl.place ,cl.etat , cl.nbinscriptions ,l.libelle FROM club cl INNER JOIN categorie_c l ON cl.CategorieC=l.id";
            
            PreparedStatement ps = con.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Club c = new Club(rs.getInt(1), rs.getInt(2), 
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), 
                        rs.getDate(9), rs.getInt(10),rs.getString(11),rs.getInt(12), rs.getDate(13), rs.getInt(14), rs.getString(15));
               
               //Club c = new Club(rs.getString(3),rs.getDate(9),rs.getInt(10),rs.getString(11),rs.getInt(12), new CrudCatC().findById(rs.getInt(14)));
              //this Club c = new Club(rs.getString(1),rs.getDate(2),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getString(6));
                clubs.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clubs;
}
       public List<Club> grpnondispo() 
{
     List<Club> clubs = new ArrayList<>();
        try {
            //String complet="complet";
           String req = "SELECT cl.*,l.libelle FROM club cl INNER JOIN categorie_c l ON cl.CategorieC=l.id where cl.etat = \"complet\" ";
            //String req = "SELECT cl.nom, cl.delai ,cl.place ,cl.etat , cl.nbinscriptions ,l.libelle FROM club cl INNER JOIN categorie_c l ON cl.CategorieC=l.id";
            
            PreparedStatement ps = con.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Club c = new Club(rs.getInt(1), rs.getInt(2), 
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), 
                        rs.getDate(9), rs.getInt(10),rs.getString(11),rs.getInt(12), rs.getDate(13), rs.getInt(14), rs.getString(15));
               
               //Club c = new Club(rs.getString(3),rs.getDate(9),rs.getInt(10),rs.getString(11),rs.getInt(12), new CrudCatC().findById(rs.getInt(14)));
              //this Club c = new Club(rs.getString(1),rs.getDate(2),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getString(6));
                clubs.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clubs;
}
       public List<Club> grparch() 
{
     List<Club> clubs = new ArrayList<>();
        try {
            //String complet="complet";
           String req = "SELECT cl.*,l.libelle FROM club cl INNER JOIN categorie_c l ON cl.CategorieC=l.id where cl.delai<CURRENT_DATE and cl.prolongation=-1 ";
            //String req = "SELECT cl.nom, cl.delai ,cl.place ,cl.etat , cl.nbinscriptions ,l.libelle FROM club cl INNER JOIN categorie_c l ON cl.CategorieC=l.id";
            
            PreparedStatement ps = con.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Club c = new Club(rs.getInt(1), rs.getInt(2), 
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), 
                        rs.getDate(9), rs.getInt(10),rs.getString(11),rs.getInt(12), rs.getDate(13), rs.getInt(14), rs.getString(15));
               
               //Club c = new Club(rs.getString(3),rs.getDate(9),rs.getInt(10),rs.getString(11),rs.getInt(12), new CrudCatC().findById(rs.getInt(14)));
              //this Club c = new Club(rs.getString(1),rs.getDate(2),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getString(6));
                clubs.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clubs;
}
       //////////SEARCH
       public List<Club> rechercheadmin(String rech) {

     List<Club> list = new ArrayList<>();
        try {
            String sql2 = "SELECT cl.*,l.libelle FROM club cl INNER JOIN categorie_c l ON cl.CategorieC=l.id WHERE (nom LIKE '%"+rech+"%' or delai LIKE '%"+rech+"%' OR place LIKE '%"+rech+"%' or etat LIKE '%"+rech+"%' or nbinscriptions LIKE '%"+rech+"%' or libelle LIKE '%"+rech+"%'  ) ";
            Statement st2 = con.createStatement();
            ResultSet rs = st2.executeQuery(sql2);
            //System.out.println(sql2);
            while (rs.next()) {
Club c = new Club(rs.getInt(1), rs.getInt(2), 
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), 
                        rs.getDate(9), rs.getInt(10),rs.getString(11),rs.getInt(12), rs.getDate(13), rs.getInt(14), rs.getString(15));
                                list.add(c);
            }
        } catch (SQLException ex) {
            //System.out.println("SQLException: " + ex.getMessage());
            //System.out.println("SQLState: " + ex.getSQLState());
            //System.out.println("VendorError: " + ex.getErrorCode());
        }

        return list;
        
        
    
    }
       public List<Club> Displayclubcat(int id) 
{
     List<Club> clubs = new ArrayList<>();
        try {
           String req = "SELECT cl.*,l.libelle FROM club cl INNER JOIN categorie_c l ON l.id="+id;
            //String req = "SELECT cl.nom, cl.delai ,cl.place ,cl.etat , cl.nbinscriptions ,l.libelle FROM club cl INNER JOIN categorie_c l ON cl.CategorieC=l.id";
            
            PreparedStatement ps = con.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Club c = new Club(rs.getInt(1), rs.getInt(2), 
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), 
                        rs.getDate(9), rs.getInt(10),rs.getString(11),rs.getInt(12), rs.getDate(13), rs.getInt(14), rs.getString(15));
               
               //Club c = new Club(rs.getString(3),rs.getDate(9),rs.getInt(10),rs.getString(11),rs.getInt(12), new CrudCatC().findById(rs.getInt(14)));
              //this Club c = new Club(rs.getString(1),rs.getDate(2),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getString(6));
                clubs.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clubs;
}
        
       public int Notifdate() {
           
        try {
            String sql2 = "SELECT count(*) FROM `Club` WHERE delai - CURRENT_DATE <3 ";
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
