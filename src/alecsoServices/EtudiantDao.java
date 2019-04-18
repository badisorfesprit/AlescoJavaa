/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecsoServices;



import alecso.Entity.Etudiant;
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
public class EtudiantDao implements Idao<Etudiant>{
    
    private static EtudiantDao instance;
    private Statement st;
    private ResultSet rs;
    
    private EtudiantDao() {
        MyBDConnection cs=MyBDConnection.getInstanceBD();
        try {
            st=cs.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(EtudiantDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static EtudiantDao getInstance(){
        if(instance==null) 
            instance=new EtudiantDao();
        return instance;
    }

    @Override
    public void insert(Etudiant o) {
        String req="INSERT INTO etudiant (nom, prenom, declass, Versclass, Etablissement, user_id) VALUES ('"+o.getNom()+"','"+o.getPrenom()+"','"+o.getDeclass()+"','"+o.getVersclass()+"','"+o.getEtablissement()+"','"+o.getUser_id()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EtudiantDao.class.getName()).log(Level.SEVERE, null, ex);
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
    public void delete(Etudiant o) {
        System.out.println("obkject send"+o.toString());
        String req="delete from etudiant where id="+o.getId();
        
        //Covoiturage p=displayById(o.getId());
        
          
              try {
           
            st.executeUpdate(req);
                  System.out.println(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(CovoiturageDao.class.getName()).log(Level.SEVERE, null, ex);}
    }

    @Override
    public List<Etudiant> displayAll() {
          String req="SELECT * FROM etudiant WHERE 1";
        ObservableList<Etudiant> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Etudiant p=new Etudiant();
                p.setId(rs.getInt(1));
                p.setUser_id(rs.getInt(2));
                p.setNom(rs.getString(3));
                p.setPrenom(rs.getString(4));
                p.setDeclass(rs.getString(5));
                p.setVersclass(rs.getString(6));
                p.setEtablissement(rs.getString(7));
               
                
                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CovoiturageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Etudiant displayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Etudiant os) {
        String qry = "UPDATE etudiant SET nom = '"+os.getNom()+"', prenom = '"+os.getPrenom()+"',declass= '"+os.getDeclass()+"',Versclass='"+os.getVersclass()+"',Etablissement='"+os.getEtablissement()+"' WHERE id = "+os.getId();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EtudiantDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;    }
        
        @Override
    public List<Etudiant> displayAllById(int id) {
          String req="SELECT * FROM etudiant WHERE user_id="+id;
          
          System.out.println(req);
        ObservableList<Etudiant> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Etudiant p=new Etudiant();
                p.setId(rs.getInt(1));
                p.setUser_id(rs.getInt(2));
                p.setNom(rs.getString(3));
                p.setPrenom(rs.getString(4));
                p.setDeclass(rs.getString(5));
                p.setVersclass(rs.getString(6));
                p.setEtablissement(rs.getString(7));
                
                System.out.println(p);
               
                
                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EtudiantDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    }
    
    

