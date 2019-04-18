/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecsoServices;

import alecso.Entity.CategorieC;
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
public class CrudCatC {
    Connection con;
    Statement st;
    ResultSet rst;
    
    public CrudCatC() {
    con = MyBDConnection.getInstanceBD().getConnection();
  }
     public void AddCatC(CategorieC c){
        try {
            String req = "INSERT INTO categorie_c (libelle,description) VALUES (?,?)";
            PreparedStatement pst = con.prepareStatement(req);
            
            pst.setString(1, c.getLibelle());
            pst.setString(2, c.getDescription());
           
            
            int exec = pst.executeUpdate();
            System.out.println("Categorie ajoutée avec succès!");
        } catch (SQLException ex) {
            Logger.getLogger(CrudCatC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void DeleteCat(int id){
       
       
        MyBDConnection cnx = MyBDConnection.getInstanceBD();

        String req = "DELETE FROM `categorie_c` WHERE `id`="+id;
        try 
        {
            PreparedStatement statement = cnx.getConnection().prepareStatement(req);
            statement.executeUpdate();
        } 
        catch (SQLException ex) {
        }
    
    }
 public List<CategorieC> Displaycat(){
       List<CategorieC> cats = new ArrayList<>();
        try {
            String req = "select * from categorie_c";
            PreparedStatement ps = con.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CategorieC cat = new CategorieC(rs.getInt("id"),rs.getString("libelle"),rs.getString("description"));
                cats.add(cat);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cats;
    }
 public void UpdateCat(CategorieC obj,int id) {
        MyBDConnection cnx = MyBDConnection.getInstanceBD();

        String req = "UPDATE `categorie_c` SET `libelle`=?,`description`=? WHERE id =?";

        try {
            PreparedStatement statement = cnx.getConnection().prepareStatement(req);
            
            statement.setString(1, obj.getLibelle());
            statement.setString(2,obj.getDescription()); 
            statement.setInt(3, id);
            

            statement.executeUpdate();

        } catch (SQLException ex) {

            ex.printStackTrace();

        }
}
 public CategorieC GetcatByName(String nom) {
        CategorieC l=null;
        try {
            String req = "select id,libelle from categorie_c where  libelle=?";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1,nom);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
            l = new CategorieC(rs.getInt(1),rs.getString(2));
               
        }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
      return l;
    }
 
 
     public CategorieC findById(Integer id) {

    
        CategorieC pt=null;
      try {
            String req = "select * from categorie_c where  id=?";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
            pt = new CategorieC(rs.getInt(1),rs.getString(2));
               
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
      return pt;
    }
      public CategorieC findnameById(Integer id) {

    
        CategorieC pt=null;
      try {
            String req = "select libelle from categorie_c where  id=?";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
            pt = new CategorieC(rs.getString(2));
            //e.setIdEv(rs.getInt(1));
            pt.setId(rs.getInt(1));
               
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
      return pt;
    }
      public List<CategorieC> rechercheadmin(String rech) {

     List<CategorieC> list = new ArrayList<>();
        try {
            String sql2 = "SELECT * FROM categorie_c l WHERE ( id LIKE '%"+rech+"%' or libelle LIKE '%"+rech+"%' or description LIKE '%"+rech+"%') ";
            Statement st2 = con.createStatement();
            ResultSet rs = st2.executeQuery(sql2);
            //System.out.println(sql2);
            while (rs.next()) {
CategorieC c = new CategorieC( rs.getInt(1),rs.getString(2), rs.getString(3));
                                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return list;
        
        
    
    }
}
