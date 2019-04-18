/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecsoServices;


import alecso.Entity.Covoiturage;
import alecso.Entity.fos_user;
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
public class CovoiturageDao implements Idao<Covoiturage>{
    
    private static CovoiturageDao instance;
    private Statement st;
    private ResultSet rs;
    
    private CovoiturageDao() {
        MyBDConnection cs=MyBDConnection.getInstanceBD();
        try {
            st=cs.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CovoiturageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static CovoiturageDao getInstance(){
        if(instance==null) 
            instance=new CovoiturageDao();
        return instance;
    }

    @Override
    public void insert(Covoiturage o) {
        String req="INSERT INTO covoiturage(De, Vers, Escall, Date, Nbplaces, Intere, user_id) VALUES ('"+o.getDe()+"','"+o.getVers()+"','"+o.getEscall()+"','"+o.getDateC()+"','"+o.getNbplaces()+"','"+o.getIntere()+"','"+o.getUser_id()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CovoiturageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

  /*  @Override
    public void delete(Covoiturage o) {
        String req="delete from personne where id="+o.getId();
        Covoiturage p=displayById(o.getId());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(CovoiturageDao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    @Override
    public ObservableList<Covoiturage> displayAll() {
        String req="select * from personne";
        ObservableList<Covoiturage> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Covoiturage p=new Covoiturage();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString(3));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CovoiturageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Covoiturage> displayAllList() {
        String req="select * from personne";
        List<Covoiturage> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Covoiturage p=new Covoiturage();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString(3));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CovoiturageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public Covoiturage displayById(int id) {
           String req="select * from personne where id ="+id;
           Covoiturage p=new Covoiturage();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(CovoiturageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }

    @Override
    public boolean update(Covoiturage p) {
        String qry = "UPDATE personne SET nom = '"+p.getNom()+"', prenom = '"+p.getPrenom()+"' WHERE id = "+p.getId();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CovoiturageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
*/

    @Override
    public void delete(Covoiturage o) {
        System.out.println("obkject send"+o.toString());
        String req="delete from covoiturage where id="+o.getId();
        
        //Covoiturage p=displayById(o.getId());
        
          
              try {
           
            st.executeUpdate(req);
                  System.out.println(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(CovoiturageDao.class.getName()).log(Level.SEVERE, null, ex);}
       
    }

    @Override
    public List<Covoiturage> displayAll() {
         String req="SELECT * FROM covoiturage WHERE 1";
        ObservableList<Covoiturage> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Covoiturage p=new Covoiturage();
                p.setId(rs.getInt(1));
                p.setUser_id(rs.getInt(2));
                p.setDe(rs.getString(3));
                p.setVers(rs.getString(4));
                p.setEscall(rs.getInt(5));
                p.setDateC(String.valueOf(rs.getDate(6)));
                p.setNbplaces(rs.getInt(7));
                p.setIntere(rs.getString(8));
                
                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CovoiturageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Covoiturage displayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Covoiturage os) {
        
String qry = "UPDATE covoiturage SET De = '"+os.getDe()+"', vers = '"+os.getVers()+"',Escall= '"+os.getEscall()+"',Date='"+os.getDateC()+"',Nbplaces='"+os.getNbplaces()+"',Intere='"+os.getIntere()+"' WHERE id = "+os.getId();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CovoiturageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;    }

    @Override
    public List<Covoiturage> displayAllById(int id) {
        String req="SELECT * FROM covoiturage WHERE user_id="+id;;
        ObservableList<Covoiturage> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Covoiturage p=new Covoiturage();
                p.setId(rs.getInt(1));
                p.setUser_id(rs.getInt(2));
                p.setDe(rs.getString(3));
                p.setVers(rs.getString(4));
                p.setEscall(rs.getInt(5));
                p.setDateC(String.valueOf(rs.getDate(6)));
                p.setNbplaces(rs.getInt(7));
                p.setIntere(rs.getString(8));
                
                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CovoiturageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    
        public boolean updateNBP(int nbp,int id) {
        
String qry = "UPDATE covoiturage SET Nbplaces='"+nbp+"' WHERE id = "+id;
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CovoiturageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;    }
    
    
     
    public List<Covoiturage> recherchecov(String de , String vers ) {
        String req="SELECT * FROM covoiturage WHERE De= '"+de+"' and Vers= '"+vers+"'";
        System.out.println(req);
        ObservableList<Covoiturage> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Covoiturage p=new Covoiturage();
                p.setId(rs.getInt(1));
                p.setUser_id(rs.getInt(2));
                p.setDe(rs.getString(3));
                p.setVers(rs.getString(4));
                p.setEscall(rs.getInt(5));
                p.setDateC(String.valueOf(rs.getDate(6)));
                p.setNbplaces(rs.getInt(7));
                p.setIntere(rs.getString(8));
                
                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CovoiturageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
 
    
    
}
