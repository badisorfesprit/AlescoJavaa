/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.LibCat;
import alecsoServices.CRUDLibCat;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LINA
 */
public class AffCAtController implements Initializable {

    @FXML
    private TextField lib;
    @FXML
    private JFXTextArea desc;
    @FXML
    private Button ajout;
    @FXML
    private Label lbl1;
    @FXML
    private Label lbl2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajoucat(ActionEvent event) throws IOException {
          LibCat c = new LibCat(lib.getText(),desc.getText());
         CRUDLibCat service =new CRUDLibCat(); 
         if ((lib.getText().length())==0 || (desc.getText().length())==0){
             JOptionPane.showMessageDialog(null, "Veuiller remplir tous les champs");
         }
         else{
            service.ajouterCat(c);
           JOptionPane.showMessageDialog(null, "Catégorie ajoutée avec succées");
            
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("LibCatAll.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();}
    }
    @FXML
    private void controle_lbl(KeyEvent event) {       
    String u=lib.getText();
   
    if((u.equals("")))
            {lbl1.setText("*Champs obligatoire");
            lbl1.setTextFill(Color.web("#b52727  "));
            ajout.setDisable(true);
            }
     else                  
        {lbl1.setText("");
        
         }
    }
    
     @FXML
    private void controle_desc(KeyEvent event) {       
    String u=desc.getText();
   
    if((u.equals("")))
            {lbl1.setText("*Champs obligatoire");
            lbl1.setTextFill(Color.web("#b52727  "));
            ajout.setDisable(true);
            }
     else                  
        {lbl1.setText("");
        
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
    private void logout(MouseEvent event) throws IOException {
               Parent root = FXMLLoader.load(getClass().getResource("Acceuil1.fxml"));
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
    
   
    
    
    
}
