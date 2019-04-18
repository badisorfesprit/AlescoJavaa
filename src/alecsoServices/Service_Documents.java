/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecsoServices;

import alecso.Entity.Documents;
import alecso.Utils.MyBDConnection;
import java.sql.Connection;
import java.sql.Date;
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
 * @author dell
 */
public class Service_Documents implements Crud<Documents>{
    private MyBDConnection con;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
        Connection mycon;
    public Service_Documents() {
         mycon = MyBDConnection.getInstanceBD().getConnection();
    }
    @Override
    public void insert(Documents t) {
        String req = "INSERT INTO `document`( `nom`, `type`, `corrige`, `matiere`, `classe`, `section`, `nomfich`, `date`) VALUES (?,?,?,?,?,?,?,?)";
        try {
             pst = mycon.prepareStatement(req);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getType());
            pst.setString(3, t.getCorrige());
            pst.setString(4, t.getMatiere());
            pst.setString(5, t.getClasse());
            pst.setString(6, t.getSection());
            pst.setString(7, t.getNomfich());
            pst.setDate(8, t.getDate());
            pst.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Service_Documents.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public void Update(Documents t) {
        String req = "UPDATE `document` SET `nom`=?,`type`=?,`corrige`=?,`matiere`=?,`classe`=?,`section`=?,`nomfich`=?,`date`=? WHERE `id` = ?";
        try {
             pst = mycon.prepareStatement(req);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getType());
            pst.setString(3, t.getCorrige());
            pst.setString(4, t.getMatiere());
            pst.setString(5, t.getClasse());
            pst.setString(6, t.getSection());
            pst.setString(7, t.getNomfich());
            pst.setDate(8, t.getDate());
            pst.setInt(9, t.getId());
            pst.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Service_Documents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Delete(Documents t) {
       String req="delete from document where id=?";
        try {
            pst = mycon.prepareStatement(req);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Service_Documents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Documents> getshow() {
        String req="SELECT * from document";
        List<Documents> list= new ArrayList<>();
        try {
            ste=mycon.createStatement();
            rs=ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Documents(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getDate(9),rs.getString(8)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Service_Documents.class.getName()).log(Level.SEVERE, null, ex);
        }
       return list; 
    }

  
    public int getelementt(String t) {
         String req="SELECT `id`  FROM `document` WHERE `nom`= '"+t+"' ";
        List<Documents> list= new ArrayList<>();
        int x = 0 ;
        try {
            ste=mycon.createStatement();
            rs=ste.executeQuery(req);
            while (rs.next()) {
               x=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Service_Documents.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(x);
       return x; 
    }

    @Override
    public List<Documents> getshowadvanced(Documents t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Documents getelement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
