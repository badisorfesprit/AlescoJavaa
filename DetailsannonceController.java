/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;



import static alecso.AnnoncesfrontController.Idpourajouter;
import alecso.Entity.Club;
import alecso.Utils.MyBDConnection;
import alecsoServices.CrudAnnonce;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.sun.prism.PhongMaterial.MapType;
import java.io.File;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
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
    int place;
    Date delair;
    private GeocodingService geocodingService; 
    private GoogleMap map;
    public static Double Latitude = 36.830651;
     public static Double Langitude = 10.194270;
     private final StringProperty address = new SimpleStringProperty();
    @FXML
    private Button btnmap;
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
            address.bind(adresse.textProperty());
            
        
    }  
   
    private void Loadannoncess()  {
        System.out.println("   khra "+Idpourajouter);
       Connection con = mycon.getConnection();
       try {
            ResultSet rs = con.createStatement().executeQuery("select nom,description,adresse,telephone,delai,place,affiche from club where id="+19);
            
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
                A = "C:/Users/Nawres/Desktop/pialecso3a23-master/src/" + A;
            File F1 = new File(A);
            Image image1 = new Image(F1.toURI().toString());
            UploadImage.setImage(image1);
            imageUpload = rs.getString("affiche");
           
                 }
        }catch (SQLException ex) {
            Logger.getLogger(DetailsannonceController.class.getName()).log(Level.SEVERE, null, ex);
    }
       nom.setText(nomr);
       desc.setText(desr);
       adresse.setText(adresser);
       delai.setText(delair.toString());
       telephone.setText(telephoner);
       if(place != 0)
               {
                   alertplace.setText("Encore de places disponibles");
               }
               else {alertplace.setText("pas de places disponibles");}
       
        System.out.println(nomr);
        
        
        
    } 

    @FXML
    private void gotomap(ActionEvent event) {
    }
 
}
