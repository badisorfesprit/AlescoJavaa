/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;



import static alecso.AnnoncesfrontController.Idpourajouter;
import static alecso.AnnoncesfrontController.Idpourajouter2;
import alecso.Entity.Club;
import alecso.Utils.MyBDConnection;
import alecsoServices.CrudAnnonce;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Nawres
 */
public class DetailsannonceController implements Initializable {

    @FXML
    private Text nom;
    @FXML
    private Text desc;
    @FXML
    private Text delai;
    @FXML
    private Text jrsrestant;
    @FXML
    private Text alertplace;
    @FXML
    private Text adresse;
    @FXML
    private Text telephone;
    @FXML
    private Text labelUser;
    private MyBDConnection mycon;
    String nomr,desr,jrsrestantr,adresser,telephoner;
    int place,restant;
    Date delair;
    
    @FXML
    private ImageView UploadImage;
    private String imageUpload;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //mapView.addMapInializedListener(this);
        //mapInitialized();
        
         mycon = new MyBDConnection();
         
      
            Loadannoncess();
            //address.bind(adresse.textProperty());
            
        
    }  
   
    private void Loadannoncess()  {
        System.out.println("    2       "+Idpourajouter2);
       Connection con = mycon.getConnection();
       try {
            ResultSet rs = con.createStatement().executeQuery("select nom,description,adresse,telephone,delai,place,affiche from club where id="+Idpourajouter2);
            
            while(rs.next()) {
               
                Club c = new Club(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),rs.getInt(6),rs.getString(7));
               
               
                nomr= rs.getString("nom");
                desr= rs.getString("description"); 
                adresser= rs.getString("adresse"); 
                //adresser= rs.getString("telephone"); 
               delair= rs.getDate("delai"); 
               telephoner= rs.getString("telephone");
               place=rs.getInt("place");
               String A = c.getAffiche();
                A = "C:/Users/Nawres/Desktop/pialecso3a23-master/src/" + A; //  C:/wamp64/www/alecsoed/web/images/
                
            File F1 = new File(A);
            Image image1 = new Image(F1.toURI().toString());
            UploadImage.setImage(image1);
            imageUpload = rs.getString("affiche");
           
                 }
        }catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            Logger.getLogger(DetailsannonceController.class.getName()).log(Level.SEVERE, null, ex);
    }
       try {
            ResultSet rs = con.createStatement().executeQuery("SELECT COUNT(*),delai-CURRENT_DATE from club where id="+Idpourajouter2);
            
            while(rs.next()) {
                restant=rs.getInt(2);
                 }
        }catch (SQLException ex) {
            Logger.getLogger(DetailsannonceController.class.getName()).log(Level.SEVERE, null, ex);
    }
       nom.setText(nomr);
       desc.setText(desr);
       adresse.setText(adresser);
       delai.setText(delair.toString());
       telephone.setText(telephoner);
       jrsrestant.setText("Reste que "+restant+" J");
       if(place != 0)
               {
                   alertplace.setText("Encore de places disponibles");
               }
               else {alertplace.setText("pas de places disponibles");}
       
        System.out.println(nomr);
        
        
        
    } 

    @FXML
    private void back(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("annoncesfront.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
        
    }
 
}
