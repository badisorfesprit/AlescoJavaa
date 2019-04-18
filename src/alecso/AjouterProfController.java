/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.fos_user;
import alecso.Entity.prof;
import alecsoServices.ProfDao;
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
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.util.regex.*; 


/**
 * FXML Controller class
 *
 * @author wiemhjiri
 */
public class AjouterProfController implements Initializable {

 
   
   @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField deregion;

    @FXML
    private TextField versregion;

    @FXML
    private TextField etablissement;

    @FXML
    private Button btn;

    @FXML
    private Label nomlbl;

    @FXML
    private Label prenomlbl;

    @FXML
    private Label deregionlbl;

    @FXML
    private Label versregionlbl;

    @FXML
    private Label etablissementlbl;

    @FXML
    private Button btna;

    @FXML
    private TextField statsociale;

    @FXML
    private Label statsocialelbl;

    @FXML
    private TextField email;

    @FXML
    private Label emaillbl;
    
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
               if (nom.getText().isEmpty() || prenom.getText().isEmpty() || statsociale.getText().isEmpty() || deregion.getText().isEmpty() || versregion.getText().isEmpty() || etablissement.getText().isEmpty()|| email.getText().isEmpty()) {

                Alert alert1 = new Alert(AlertType.INFORMATION);
                alert1.setTitle("Information Dialog");
                alert1.setHeaderText(null);
                alert1.setContentText("veuillez remplir tous les champs ");
                alert1.show();
            } else {
            prof p = new prof();
            p.setNom(nom.getText());
            p.setPrenom(prenom.getText());
            p.setStatsociale(statsociale.getText());
            p.setDeregion(deregion.getText());
            p.setVersregion(versregion.getText());
           p.setEtablissement(etablissement.getText());
            p.setEmail(email.getText());
          
           p.setUser_id(fos_user.getIdcnct());
            
            
           String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
Pattern pattern = Pattern.compile(masque);
Matcher controler = pattern.matcher(email.getText());
if (controler.matches()){
//Ok : la saisie est bonne
  ProfDao pdao = ProfDao.getInstance();
                        System.out.println(p);        

            pdao.insert(p);
            Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Personne insérée avec succés!");
        alert.show();
}else{
    Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("email non valide!");
        alert.show();
//La c'est pas bon
} 
          
        
        
        nom.setText("");
        prenom.setText("");
        statsociale.setText("");
       // picker.setText("");
        deregion.setText("");
        versregion.setText("");
        etablissement.setText("");
        email.setText("");
         
               }  
        });
         btna.setOnAction(event ->{ try {//FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/alecso/AfficherProf.fxml"));
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
