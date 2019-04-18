/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;


import static alecso.AnnoncesadminController.IdpourModifier;
import alecso.Entity.CategorieC;
import alecso.Entity.Club;
import alecso.Utils.MyBDConnection;
import alecsoServices.CrudAnnonce;
import alecsoServices.CrudCatC;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Nawres
 */
public class ModifannonceController implements Initializable {

    @FXML
    private Button modifannonce;
    @FXML
    private TextField nom;
    @FXML
    private TextArea desc;
    @FXML
    private TextField adresse;
    @FXML
    private DatePicker delai;
    @FXML
    private TextField nbplaces;
    @FXML
    private TextField tel;
    private Text txtPath;
    @FXML
    private Button btnimg;
    @FXML
    private ComboBox<String> cbxcat;
    @FXML
    private ComboBox<String> cbxvilles;
private MyBDConnection mycon;
    @FXML
    private ImageView UploadImage;
    private String imageUpload;
    @FXML
    private ComboBox<Integer> prolongation;
    @FXML
    private CheckBox supression;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prolongation.getItems().addAll(1,2,3,5,4,6,1,2,3,5,4,6,1,2,3,5,4,6);
        cbxvilles.getItems().add("Ariana");cbxvilles.getItems().add("Beja"); cbxvilles.getItems().add("Ben Arous");cbxvilles.getItems().add("Bizerte");  cbxvilles.getItems().add("Gabes"); 
        cbxvilles.getItems().add("Gafsa"); cbxvilles.getItems().add("Jendouba"); cbxvilles.getItems().add("Kairouan"); cbxvilles.getItems().add("Kasserine");  cbxvilles.getItems().add("Kebili");
        cbxvilles.getItems().add("Le Kef");cbxvilles.getItems().add("Mahdia"); cbxvilles.getItems().add("Manouba");cbxvilles.getItems().add("Medenine");  cbxvilles.getItems().add("Monastir"); 
        cbxvilles.getItems().add("Nabeul"); cbxvilles.getItems().add("Sfax"); cbxvilles.getItems().add("Sidi Bouzid"); cbxvilles.getItems().add("Siliana");  cbxvilles.getItems().add("Sousse");
        cbxvilles.getItems().add("Tataouine"); cbxvilles.getItems().add("Tozeur"); cbxvilles.getItems().add("Tunis"); cbxvilles.getItems().add("Zagouan");
         try {
            
            String requete="select * from categorie_c";
            ObservableList<String> list = FXCollections.observableArrayList();
            Connection con= MyBDConnection.getInstanceBD().getConnection();
            Statement ste = con.createStatement();
            ResultSet rs;
            rs=ste.executeQuery(requete);
            while(rs.next()){
                list.add(rs.getString("libelle"));
                cbxcat.setItems(list);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddannonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mycon = new MyBDConnection();
        modifierdata();
        // TODO
    }   
     private void modifierdata() {
            Connection con = mycon.getConnection();
       try {
            ResultSet rs = con.createStatement().executeQuery("SELECT cl.*,l.libelle FROM club cl INNER JOIN categorie_c l ON cl.CategorieC=l.id WHERE cl.id="+IdpourModifier);
            
            while(rs.next()) {
               
                Club c = new Club(rs.getInt(1), rs.getInt(2), 
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), 
                        rs.getDate(9), rs.getInt(10),rs.getString(11),rs.getInt(12), rs.getDate(13), rs.getInt(14), rs.getInt(15),rs.getString(16));
               
               
                
               int valeur = c.getProlongation();
               if(valeur == -1)  {supression.setSelected(true);}
               else {prolongation.setValue(c.getProlongation());}
               nom.setText(c.getNom()); 
               desc.setText(c.getDescription());
              
               //txtPath.setText(c.getAffiche());
               adresse.setText(c.getAdresse());
               delai.setValue(c.getDelai().toLocalDate());
                nbplaces.setText(String.valueOf(c.getPlace()));
               
                //CrudCatC cat = new CrudCatC();
                //CategorieC khra = cat.findnameById(c.getCat().getId());
                //String k=khra.toString();
                 cbxcat.setValue(c.getLibelle());
                //CategorieC cc = cat.GetcatByName(cbxcat.getValue().toString());
                tel.setText(c.getTelephone());
                cbxvilles.setValue(c.getVille());
                String A = c.getAffiche();
                A = "C:/Users/Nawres/Desktop/pialecso3a23-master/src/" + A;
            File F1 = new File(A);
            Image image1 = new Image(F1.toURI().toString());
            UploadImage.setImage(image1);
            imageUpload = c.getAffiche();
                
                
               
               
              
               
                 }
        }catch (SQLException ex) {
            Logger.getLogger(ModifcatcController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
    }
       prolongation.setValue(-1);
     
     }

    @FXML
    private void modifannonce(ActionEvent event) throws IOException {
         
         if(nom.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Entrez le nom de votre annonce!");}
         else if(desc.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Entrez la description de votre annonce!");}
         else if(adresse.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Entrez l'adresse de votre annonce!");}
        else if(nbplaces.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Entrez le nombre de place de votre annonce!");}
        else if(tel.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Entrez votre telephone!");}
         else if(tel.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Entrez le nombre de place de votre annonce!");}
             
        else{   CrudAnnonce service=new CrudAnnonce();
        Club c = new Club();
        c.setNom(nom.getText());
        c.setDescription(desc.getText());
        c.setAffiche(imageUpload);
        c.setAdresse(adresse.getText());
        c.setDelai(java.sql.Date.valueOf((delai.getValue())));
        c.setPlace(Integer.parseInt(nbplaces.getText()));
         CrudCatC locser = new CrudCatC();
            CategorieC l = locser.GetcatByName(cbxcat.getValue().toString());
            c.setCat(l);
            c.setTelephone(tel.getText());
            c.setVille(cbxvilles.getValue());
            int v=-1;
        if(supression.isPressed()) {c.setProlongation(prolongation.getValue()); }
        else {
            c.setProlongation(prolongation.getValue());
        }
    
        
        service.updateannonce(c, IdpourModifier);
         JOptionPane.showMessageDialog(null, "Annonce modifiée avec succées");
    
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("annoncesadmin.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();}
    }

    @FXML
    private void choisirimg(ActionEvent event) throws IOException {
         FileChooser fc = new FileChooser();
           FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)","*.jpg");
           FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("PNG files(*.png)","*.png");
           fc.getExtensionFilters().addAll(ext1,ext2);
           Stage stage = new Stage();
           File file = fc.showOpenDialog(stage);
           BufferedImage bf;
           if( file != null){
               bf = ImageIO.read(file);
           javafx.scene.image.Image image = SwingFXUtils.toFXImage(bf, null);
           UploadImage.setImage(image);
           FileInputStream fin = new FileInputStream(file);
           imageUpload = file.getName();
           }
    }
    
}
