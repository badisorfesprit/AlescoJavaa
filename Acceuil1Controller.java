/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.BCrypt;
import alecso.Entity.fos_user;
import alecsoServices.CrudUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LINA
 */
public class Acceuil1Controller implements Initializable {

    @FXML
    private Button bntReg;
    @FXML
    private AnchorPane parentContainer;
    @FXML
    private TextField idpseudo;
    @FXML
    private TextField idpwd;
    @FXML
    private Button bntlogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
    }    


           @FXML
    private void register(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
    
    private void test(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
    
    public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(13);
        System.out.println(salt);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);

        return (hashed_password);
    }
    
    @FXML
     private void login(ActionEvent event) throws SQLException, IOException {
               CrudUser cu = new CrudUser();
       if( cu.login(idpseudo.getText(),idpwd.getText())==true)
       { 
            JOptionPane.showMessageDialog(null, "Bienvenue !");
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("welcome.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
       }
       else{JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou Mot de passe non valide");}
             
    
        }
    
    
    
    
}
