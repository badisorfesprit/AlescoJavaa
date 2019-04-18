/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.Documents;
import alecsoServices.Service_Documents;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AfficherController implements Initializable {

    @FXML
    private TableColumn<String, String> nom;
    @FXML
    private TableColumn<String, String> type;
    @FXML
    private TableColumn<String, String> corrige;
    @FXML
    private TableColumn<String, String> matiere;
    @FXML
    private TableColumn<String, String> classe;
    @FXML
    private TableColumn<String, String> section;
    @FXML
    private TableColumn<String, String> date;
    @FXML
    private Rectangle rectangle;
    @FXML
    private JFXButton go_to_add;
    @FXML
    private JFXButton go_to_edit;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXTextField search_barre;
    @FXML
    private JFXButton retour;
    @FXML
    private TableView<Documents> afficher_document;
        public void afficher() {
         Service_Documents ps = new Service_Documents();
        ArrayList list = (ArrayList) ps.getshow();
        ObservableList<Documents> obslist = FXCollections.observableArrayList(list);
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        corrige.setCellValueFactory(new PropertyValueFactory<>("corrige"));
        matiere.setCellValueFactory(new PropertyValueFactory<>("matiere"));
        classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
        section.setCellValueFactory(new PropertyValueFactory<>("section"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        afficher_document.setItems(obslist);
    } 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        afficher();
        go_to_edit.setOnAction(e->{
              try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("update.fxml"));
                Parent root = loader.load();
                update det = (update) loader.getController();
                Documents ev = afficher_document.getSelectionModel().getSelectedItem();
                det.loadvalues(ev);

                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Update event");
                stage.setScene(new Scene(root));
                Stage stagee = (Stage) go_to_edit.getScene().getWindow();
                stagee.close();
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AfficherController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        go_to_add.setOnAction(e->{
            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("DocumentFXML.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                Stage stagee = (Stage) go_to_add.getScene().getWindow();

                stagee.close();
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AfficherController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        delete.setOnAction(e->{
            Service_Documents sd= new Service_Documents();
            sd.Delete(afficher_document.getSelectionModel().getSelectedItem());
            afficher();
        });
    }
    @FXML
    private void afficher_taswira(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Documents e = afficher_document.getSelectionModel().getSelectedItem();
            Image imageURI2 = new Image("file:C://wamp64/www/uploads/" + e.getNomfich());//C:\\wamp64\\www\\uploads
            rectangle.setFill(new ImagePattern(imageURI2));
        }
    }

   @FXML
    private void retour(ActionEvent event) throws IOException {
               Parent root = FXMLLoader.load(getClass().getResource("LibCatAll.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
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
    
}
