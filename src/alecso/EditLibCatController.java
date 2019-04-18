/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.LibCat;
import static alecso.LibCatAllController.IdpourModifier;
import alecso.Utils.MyBDConnection;
import alecsoServices.CRUDLibCat;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LINA
 */
public class EditLibCatController implements Initializable {

    @FXML
    private TextField idT;
    @FXML
    private JFXTextArea idD;
    @FXML
    private Button btnCon;
    @FXML
    private Button btnAnn;
      private MyBDConnection mycon;

    /**
     * Initializes the controller class.
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        mycon = new MyBDConnection();
        modifierdata();
    }    

    private void modifierdata() {
            Connection con = mycon.getConnection();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `categorie` WHERE id="+IdpourModifier);
            while(rs.next()) {
               LibCat c=(new LibCat(rs.getString(2),rs.getString(3)));
               
               
               idT.setText(c.getLibelle()); 
               idD.setText(c.getDescription());
             
               
              
               
                 }
        }catch (SQLException ex) {
            Logger.getLogger(EditLibCatController.class.getName()).log(Level.SEVERE, null, ex);
    }}

    @FXML
    private void Modifier(ActionEvent event) throws IOException {
         CRUDLibCat service=new CRUDLibCat();
        LibCat c = new LibCat();
        c.setLibelle(idT.getText());
        c.setDescription(idD.getText());
          if ((idT.getText().length())==0 || (idD.getText().length())==0){
             JOptionPane.showMessageDialog(null, "Veuiller remplir tous les champs");
         }
         else{
          
       
    
        
        service.ModifierLibCat(c, IdpourModifier); 
        JOptionPane.showMessageDialog(null, "Catégorie Modifiée");
    
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("LibCatAll.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();}
    }


    private void dconnecter(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    private void retour_menu(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("Affiche_Cov.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void controle_lbl(KeyEvent event) {
    }

    @FXML
    private void controle_desc(KeyEvent event) {
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
    }}