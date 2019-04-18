/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.fos_user;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LINA
 */
public class WelcomeController implements Initializable {

    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button b4;
    @FXML
    private Button b5;
    @FXML
    private Text labUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String nom=fos_user.getNamecnt();
        labUser.setText(nom);
        // TODO
        
        
    } 
       private void test(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("AdminUser.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void btnCr(ActionEvent event) throws IOException {
        
        
        Parent root = FXMLLoader.load(getClass().getResource("ListEvent.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void btnEvent(ActionEvent event) throws IOException {
        
        
        Parent root = FXMLLoader.load(getClass().getResource("exemple.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void btnAnn(ActionEvent event) throws IOException {
        
           Parent tableViewParent = FXMLLoader.load(getClass().getResource("annoncesfront.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void btnLib(ActionEvent event) throws IOException {
        
           Parent tableViewParent = FXMLLoader.load(getClass().getResource("ProdAll.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
               Parent root = FXMLLoader.load(getClass().getResource("Acceuil1.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void panier(MouseEvent event) throws IOException {
               Parent root = FXMLLoader.load(getClass().getResource("panier.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void bntAnn(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("AccueilAnnotation.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
    
}
