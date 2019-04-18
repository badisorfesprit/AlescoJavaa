/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.Covoiturage;
import alecso.Entity.Etudiant;
import alecso.Entity.fos_user;
import alecsoServices.EtudiantDao;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author wiemhjiri
 */
public class AjouterEtudiantController implements Initializable {

 
    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField declass;

    @FXML
    private TextField versclass;

    @FXML
    private TextField etablissement;

    @FXML
    private Button btn;
    
    @FXML
    private Button btna;
    
    @FXML
    private ImageView backk;


    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO     
       btn.setOnAction(event -> {
             
               if (nom.getText().isEmpty() || prenom.getText().isEmpty() || declass.getText().isEmpty() || versclass.getText().isEmpty() || etablissement.getText().isEmpty()) {

                Alert alert1 = new Alert(AlertType.INFORMATION);
                alert1.setTitle("Information Dialog");
                alert1.setHeaderText(null);
                alert1.setContentText("veuillez remplir tous les champs ");
                alert1.show();
            } else {
            Etudiant p = new Etudiant();
            p.setNom(nom.getText());
            p.setPrenom(prenom.getText());
            p.setDeclass(declass.getText());
            p.setVersclass(versclass.getText());
           p.setEtablissement(etablissement.getText());
          
           p.setUser_id(fos_user.getIdcnct());
            
            
            
           EtudiantDao pdao = EtudiantDao.getInstance();
                        System.out.println(p);        

            pdao.insert(p);
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Personne insérée avec succés!");
        alert.show();
        nom.setText("");
        prenom.setText("");
       // picker.setText("");
        declass.setText("");
        versclass.setText("");
        etablissement.setText("");
         
               } 
        });
         btna.setOnAction(event ->{ try {//FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/alecso/AfficherEtudiant.fxml"));
                // Give the controller access to the main app.
//                AfficherPersonneController controller =loader.getController();
//                controller.setListData(new ListData());
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilAnnotationController.class.getName()).log(Level.SEVERE, null, ex);
            }});
          backk.setOnMouseClicked(event -> {
            try {//FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/alecso/AccueilAnnotation.fxml"));
                // Give the controller access to the main app.
//                AfficherPersonneController controller =loader.getController();
//                controller.setListData(new ListData());
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilAnnotationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        
        
    }


}
