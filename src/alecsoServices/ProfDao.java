/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecsoServices;



import alecso.Entity.prof;
import alecso.Utils.MyBDConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author wiemhjiri
 */
public class ProfDao implements Idao<prof>{
    
    private static ProfDao instance;
    private Statement st;
    private ResultSet rs;
    
    private ProfDao() {
        MyBDConnection cs=MyBDConnection.getInstanceBD();
        try {
            st=cs.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ProfDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ProfDao getInstance(){
        if(instance==null) 
            instance=new ProfDao();
        return instance;
    }


        @Override
    public void insert(prof o) {
           String req="INSERT INTO prof (nom, prenom, statsociale, deregion, versregion, etablissement, email, user_id) VALUES ('"+o.getNom()+"','"+o.getPrenom()+"','"+o.getStatsociale()+"','"+o.getDeregion()+"','"+o.getVersregion()+"','"+o.getEtablissement()+"','"+o.getEmail()+"','"+o.getUser_id()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ProfDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    @Override
    public void delete(prof o) {
           System.out.println("obkject send"+o.toString());
        String req="delete from prof where id="+o.getId();
        
        //Covoiturage p=displayById(o.getId());
        
          
              try {
           
            st.executeUpdate(req);
                  System.out.println(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(CovoiturageDao.class.getName()).log(Level.SEVERE, null, ex);}
    }

    @Override
    public List<prof> displayAll() {
                 String req="SELECT * FROM prof WHERE 1";
        ObservableList<prof> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                prof p=new prof();
                p.setId(rs.getInt(1));
                p.setUser_id(rs.getInt(2));
                p.setNom(rs.getString(3));
                p.setPrenom(rs.getString(4));
                p.setStatsociale(rs.getString(5));
                p.setDeregion(rs.getString(6));
                p.setVersregion(rs.getString(7));
                p.setEtablissement(rs.getString(8));
                p.setEmail(rs.getString(9));
               
                
                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CovoiturageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public prof displayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(prof os) {
           String qry = "UPDATE prof SET nom = '"+os.getNom()+"', prenom = '"+os.getPrenom()+"',statsociale= '"+os.getStatsociale()+"',deregion='"+os.getDeregion()+"',versregion='"+os.getVersregion()+"',etablissement='"+os.getEtablissement()+"',email='"+os.getEmail()+"' WHERE id = "+os.getId();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EtudiantDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;  
    }

    @Override
    public List<prof> displayAllById(int id) {
         String req="SELECT * FROM prof WHERE user_id="+id;
          ObservableList<prof> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                prof p=new prof();
                p.setId(rs.getInt(1));
                p.setUser_id(rs.getInt(2));
                p.setNom(rs.getString(3));
                p.setPrenom(rs.getString(4));
                p.setStatsociale(rs.getString(5));
                p.setDeregion(rs.getString(6));
                p.setVersregion(rs.getString(7));
                p.setEtablissement(rs.getString(8));
                p.setEmail(rs.getString(9));
               
                
                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CovoiturageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

   
    }
    
    

