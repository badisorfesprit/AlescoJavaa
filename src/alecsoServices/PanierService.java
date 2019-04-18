/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecsoServices;

import alecso.Entity.Panier;
import alecso.Entity.Prod;
import alecso.Entity.fos_user;
import static alecso.ProdAllController.IdpourReserver;
import alecso.Utils.MyBDConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LINA
 */
public class PanierService {
    Connection mycon;
    Statement st;
    ResultSet rst;
    
public PanierService() {
    mycon = MyBDConnection.getInstanceBD().getConnection();
  }
    

 public String getNomProd(int id) throws SQLException{
      String l=null;
    String req = "select nom from produits  where id="+ id+ " limit 1"; 
     PreparedStatement preparedStatement;
        try {
         preparedStatement = mycon.prepareStatement(req);
         ResultSet result = preparedStatement.executeQuery();
                     while (result.next()) {
             l=result.getString("nom");
                
            }
        }
           catch (SQLException ex) {
                        System.out.println(ex.getMessage());
            }
          return l;
}
 


 public List<Panier> AfficherPan() throws SQLException
{
    List<Panier> p = new ArrayList<>();
    String req = "SELECT p.total,p.id, p.quantite, pr.nom FROM panier p, produits pr WHERE p.idProd=pr.id AND `idConn`="+fos_user.getIdcnct(); 
    PreparedStatement preparedStatement;
        try {
         preparedStatement = mycon.prepareStatement(req);
         ResultSet result = preparedStatement.executeQuery();
                     while (result.next()) {
                Panier c;
                c = new Panier();
                
               // c.setIdProd(result.getInt("idProd"));
                c.setQuantite(result.getInt("quantite"));
               c.setProd(result.getString("nom"));
                c.setTotal(result.getInt("total"));
                   c.setId(result.getInt("id"));
          
                p.add(c);
                
            }
        }
           catch (SQLException ex) {
                        ex.printStackTrace();
            }
          return p;
}
 
 public List<Panier> AfficherPanier() throws SQLException
{
    List<Panier> p = new ArrayList<>();
     //String pattern = "yyyy-MM-dd";
    //SimpleDateFormat format = new SimpleDateFormat(pattern);
    String req = "SELECT * FROM panier  WHERE `idConn`="+fos_user.getIdcnct(); 
    PreparedStatement preparedStatement;
        try {
         preparedStatement = mycon.prepareStatement(req);
         ResultSet result = preparedStatement.executeQuery();
                     while (result.next()) {
                Panier c;
                c = new Panier();
                
                c.setIdProd(result.getInt("idProd"));
                c.setQuantite(result.getInt("quantite"));
               
                c.setTotal(result.getInt("total"));
                p.add(c);
                
            }
        }
           catch (SQLException ex) {
                        ex.printStackTrace();
            }
          return p;
}
 
  public void supprimerProd(int id) throws SQLException {
        MyBDConnection cnx = MyBDConnection.getInstanceBD();
      int idc = fos_user.getIdcnct();
    PanierService pa=new PanierService();
   List<Panier> p=pa.AfficherPanier();
   List<Panier> rsList=new ArrayList<>();
   for(int i=0;i<p.size();i++){
       if(p.get(i).getIdProd()==id){
           rsList.add(p.get(i));
       }
   }
        String req ;
   if(rsList.size()==1){
  req = "DELETE FROM `panier` WHERE `id`="+id+" And idConn="+idc;
   }else{
  req = "Update `panier` " + "set quantite= quantite-1  WHERE `id`="+id+" AND "+"idConn="+idc;

   }
        try 
        {
            PreparedStatement statement = cnx.getConnection().prepareStatement(req);
            statement.executeUpdate();
        } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }} 
     
      public void quantiteAdd(int idr) throws SQLException, IOException 
{
        
   
 String req1 = "UPDATE `produits` " + "SET stock_qty = stock_qty+1  WHERE `id`="+idr;
        try {
            PreparedStatement ste = mycon.prepareStatement(req1);
            int x = ste.executeUpdate(req1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

} 
}
