/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecsoServices;

import alecso.Entity.Documents;
import alecso.Entity.comment;
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
 * @author dell
 */
public class Service_comment implements Crud<comment>{
   private MyBDConnection con;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
        Connection mycon;
    public Service_comment() {
         mycon = MyBDConnection.getInstanceBD().getConnection();
    }
    @Override
    public void insert(comment t) {
         
        String req = "INSERT INTO `commentaire` ( `document`, `texte`, `date`) VALUES (?,?,?)";
        try {
            pst = mycon.prepareStatement(req);
            pst.setInt(1, t.getId_document());
            pst.setString(2, t.getText());
            pst.setDate(3, t.getDate());
  
            pst.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Service_Documents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Update(comment t) {
     String req = "UPDATE `commentaire` SET `document`=?,`texte`=?,`date`=? WHERE `id`=?";
        try {
            pst = mycon.prepareStatement(req);
            pst.setInt(1, t.getId_document());
            pst.setString(2, t.getText());
            pst.setDate(3, t.getDate());
            pst.setInt(4, t.getId());
  
            pst.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Service_Documents.class.getName()).log(Level.SEVERE, null, ex);
        }   
    
    }

    @Override
    public void Delete(comment t) {
     String req="delete from commentaire where id=?";
        try {
            pst = mycon.prepareStatement(req);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Service_Documents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<comment> getshow() {
     String req="SELECT * from commentaire";
        List<comment> list= new ArrayList<>();
        try {
            ste=mycon.createStatement();
            rs=ste.executeQuery(req);
            while (rs.next()) {
                list.add(new comment(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getDate(4)));
      
            }
        } catch (SQLException ex) {
            Logger.getLogger(Service_Documents.class.getName()).log(Level.SEVERE, null, ex);
        }
       return list; 
    }
   
    public List<comment> getshows(String t) {
        
     String req="SELECT * from commentaire where document = '"+t+"'";
        List<comment> list= new ArrayList<>();
        try {
            ste=mycon.createStatement();
            rs=ste.executeQuery(req);
            while (rs.next()) {
                list.add(new comment(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getDate(4)));
      
            }
        } catch (SQLException ex) {
            Logger.getLogger(Service_Documents.class.getName()).log(Level.SEVERE, null, ex);
        }
       return list; 
    }



    @Override
    public comment getelement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<comment> getshowadvanced(comment t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
