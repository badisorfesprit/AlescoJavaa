/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecsoServices;

import alecso.Entity.BCrypt;
import alecso.Entity.Prod;
import alecso.Entity.fos_user;
import alecso.Utils.MyBDConnection;
import java.io.FileNotFoundException;
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
 * @author LINA
 */
public class CrudUser {
    String path = "";
    Connection mycon = MyBDConnection.getInstanceBD().getConnection();
      
    
    public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(13);
        System.out.println(salt);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);

        return (hashed_password);
    }
    
     public void supprimerUser(int id) throws SQLException {
        MyBDConnection cnx = MyBDConnection.getInstanceBD();

        String req = "DELETE FROM `fos_user` WHERE `id`="+id;
        try 
        {
            PreparedStatement statement = cnx.getConnection().prepareStatement(req);
            statement.executeUpdate();
        } 
        catch (SQLException ex) {
        }} 


    
    public void register(fos_user user) throws SQLException, FileNotFoundException{
        
        String password  =  hashPassword( user.getPassword());
        
        
         String req1 = "INSERT INTO `fos_user` " + "(`username`,`email`,`password`,`roles`,`nom`,`prenom`)" + " VALUES ('"
                + user.getUsername()+ "','" + user.getEmail() + "','" +password+ "','" 
                        + user.getRoles()+ "','" + user.getNom()+"','" + user.getPrenom() 
                                +"') ";
 
                try {
            PreparedStatement ste = mycon.prepareStatement(req1);
            int x = ste.executeUpdate(req1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                }
  
   
    
    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;

        if (null == stored_hash || !stored_hash.startsWith("$2y$")) {
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        }

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

        return (password_verified);
    }
    
   
     public List<fos_user> AfficherUsers() throws SQLException
{
    List<fos_user> p = new ArrayList<>();
    String req = "SELECT * FROM `fos_user` WHERE  'id'!="+fos_user.getIdcnct(); 
    PreparedStatement st = mycon.prepareStatement(req);
   
        ResultSet res = st.executeQuery();
        try {
        
         ResultSet result = st.executeQuery();
                     while (result.next()) {
                fos_user c;
                c = new fos_user();
                
                c.setNom(result.getString("nom"));
               c.setUsername(result.getString("username"));
               c.setEmail(result.getString("email"));
               c.setPrenom(result.getString("prenom"));
               c.setId(result.getInt("id"));
                p.add(c);
                
            }
        }
           catch (SQLException ex) {
                        ex.printStackTrace();
            }
          return p;
}
     
     
     
            public fos_user getMail(int id) throws SQLException {
        fos_user user = new fos_user();

        String requete = "select * from `fos_user` where `id`=?";
        PreparedStatement st = mycon.prepareStatement(requete);
        st.setString(1, String.valueOf(id));
        ResultSet res = st.executeQuery();

        while(res.next()){
            System.out.println(res.getString("id"));
            user.setId(res.getInt("id"));
            System.out.println(res.getString("username"));
            user.setUsername(res.getString("username"));
            System.out.println(res.getString("nom"));
            user.setNom(res.getString("nom"));
            System.out.println(res.getString("prenom"));
            user.setPrenom(res.getString("prenom"));
            System.out.println(res.getString("roles"));
            user.setRoles(res.getString("roles"));
            System.out.println(res.getString("email"));
            user.setEmail(res.getString("email"));
         
           
        }

        return user;
    }
            
    
       public fos_user getUser(int id) throws SQLException {
        fos_user user = new fos_user();

        String requete = "select * from `fos_user` where `id`=?";
        PreparedStatement st = mycon.prepareStatement(requete);
        st.setString(1, String.valueOf(id));
        ResultSet res = st.executeQuery();

        while(res.next()){
            System.out.println(res.getString("id"));
            user.setId(res.getInt("id"));
            System.out.println(res.getString("username"));
            user.setUsername(res.getString("username"));
            System.out.println(res.getString("nom"));
            user.setNom(res.getString("nom"));
            System.out.println(res.getString("prenom"));
            user.setPrenom(res.getString("prenom"));
            System.out.println(res.getString("roles"));
            user.setRoles(res.getString("roles"));
            System.out.println(res.getString("email"));
            user.setEmail(res.getString("email"));
         
           
        }

        return user;
    }
    public Boolean login(String username, String password) {
        try {
            //String requete = "SELECT * FROM `fos_user` where `username`=? and `password`=?";
            String requete = "SELECT `id`,`password`,`nom`,`prenom` FROM `fos_user` where `username`=?";

            PreparedStatement st = mycon.prepareStatement(requete);
            st.setString(1, username);
           // st.setString(2, password);
            ResultSet res = st.executeQuery();
            
            while(res.next()){
                if(BCrypt.checkpw(password,res.getString(2))){
                     System.out.println("True");
                 fos_user.setIdcnct(res.getInt("id"));
                  fos_user.setNamecnt(res.getString("nom")+" "+res.getString("prenom"));
                 System.out.println("id :" +fos_user.idcnct);
                  System.out.println("nom :" +fos_user.namecnt);
                 return true;
                }
               
            }
          
                System.out.println("username does not exist");
               
                return false;
            
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }

        return false;
        
    }
    
    public fos_user getconnecteduser(int id) throws SQLException {
     
             MyBDConnection cs=MyBDConnection.getInstanceBD();
       
            Statement st=cs.getConnection().createStatement();

         
            String req = "SELECT * FROM fos_user where id="+id;
          fos_user userconnected = null;
        try {
           ResultSet res=st.executeQuery(req);
           while(res.next()){
   //         rs.next();
            userconnected = new fos_user();
              userconnected.setId(id);
                userconnected.setNom(res.getString("nom"));
                userconnected.setPrenom(res.getString("prenom"));
                userconnected.setEmail(res.getString("email"));
                userconnected.setRoles(res.getString("roles"));
                userconnected.setUsername(res.getString("username"));
            }  
        } catch (SQLException ex) {
            Logger.getLogger(CrudUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    return userconnected;
     }
    
     public boolean checkUser(String name) throws SQLException {
        MyBDConnection cnx = MyBDConnection.getInstanceBD();

        String req = "select username FROM fos_user WHERE username="+name;
        try 
        {
            PreparedStatement statement = cnx.getConnection().prepareStatement(req);
        ResultSet result = statement.executeQuery();
                     while (result.next()) {
                         return true;
                     }
        } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
             return false;
        }return false;
} 
}
