/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.Evenement;
import alecso.Entity.categorie;
import alecso.Utils.MyBDConnection;
import alecsoServices.CategorieDB;
import alecsoServices.EvenementDB;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class ModifierEventController implements Initializable {
    

    @FXML
    private TextField id;

    @FXML
    private TextField titre;

    @FXML
    private TextField lieu;

    @FXML
    private TextField description;

    @FXML
    private DatePicker date_event;

    @FXML
    private TextField nbplace;

    @FXML
    private TextField nom_image;

    @FXML
    private Button modifier;

    private TextField id_categorie;

    @FXML
    private Button annuler;
    @FXML
    private Button uploadImage;
    @FXML
    private ImageView UploadImage;
    @FXML
    private ComboBox idCat;
 private String imageUpload;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Connection mycon = MyBDConnection.getInstanceBD().getConnection(); 
    
          CategorieDB crud=new  CategorieDB();
        List<categorie> c=new ArrayList();
        List l=new ArrayList();
        try {
            c= crud.afficherComb();
        } catch (SQLException ex) {
            Logger.getLogger(ModifierEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        c.forEach((s->l.add(s.getLibelle())) );
        idCat.setItems(FXCollections.observableArrayList(l));
           // modifierdata(c);
    }    
    
    
    
 

    @FXML
    private void modif(ActionEvent event) throws SQLException {
         CategorieDB cl=new CategorieDB();
        List<categorie> c=new ArrayList();
        List l=new ArrayList();
             c= cl.afficherComb();
         Evenement E;
          
        E = new Evenement(Integer.parseInt(id.getText()),findId(l),titre.getText(),imageUpload,lieu.getText(),description.getText(),Date.valueOf(date_event.getValue().format(DateTimeFormatter.ISO_DATE)),Integer.parseInt(nbplace.getText()));

        EvenementDB  crud = new  EvenementDB  ();
        crud.Modifier_salle(E); 
    
      // Event_tab.setItems(crud.afficherEvent());
        
    }
       

      
        
        
        
    

    @FXML
    private void anuulerbtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminBackEvent.fxml"));
                
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void uploadImage(ActionEvent event) throws IOException {
       
           FileChooser fc = new FileChooser();
           FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)","*.jpg");
           FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("PNG files(*.png)","*.png");
           fc.getExtensionFilters().addAll(ext1,ext2);
           Stage stage = new Stage();
           File file = fc.showOpenDialog(stage);
           BufferedImage bf;
           if( file != null){
               bf = ImageIO.read(file);
           Image image = SwingFXUtils.toFXImage(bf, null);
           UploadImage.setImage(image);
           FileInputStream fin = new FileInputStream(file);
           imageUpload = file.getName();
           }
    }
    
    
      private int findId(List<categorie> c){
        int id=0;
        for(int i=0;i<c.size();i++){
            if(c.get(i).getLibelle().equals(idCat.getValue())){
                id=c.get(i).getId();
            }
        }
        return id;
    }
 @FXML
    private void GoToAdminUser(ActionEvent event) throws IOException {
               Parent root = FXMLLoader.load(getClass().getResource("AdminUser.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void GoToProduit(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("AdminProd.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void Annonces(ActionEvent event)  throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("AdminProd.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void CatC(ActionEvent event) throws IOException {
        
 Parent root = FXMLLoader.load(getClass().getResource("affcatc.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();

    }

    @FXML
    private void categoriebtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("categorieBack.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
        
 
    }

    @FXML
    private void bntAnnotation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("backEtudiant.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();}

    @FXML
    private void GoToAffiche(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Affiche.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();}

    @FXML
    private void statbtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("stat.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
     @FXML
    private void retour(ActionEvent event) throws IOException {
               Parent root = FXMLLoader.load(getClass().getResource("LibCatAll.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
    
}
