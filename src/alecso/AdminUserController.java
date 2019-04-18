/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.LibCat;
import alecso.Entity.Prod;
import alecso.Entity.fos_user;
import alecso.Utils.MyBDConnection;
import alecsoServices.CRUDLibCat;
import alecsoServices.CrudProduits;
import alecsoServices.CrudUser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LINA
 */
public class AdminUserController implements Initializable {

    @FXML
    private TableColumn<fos_user, String> IdN;
    @FXML
    private TableColumn<fos_user, String> IdP;
    @FXML
    private TableView<fos_user> table;
    
     private MyBDConnection mycon;
    @FXML
    private TableColumn<fos_user, String> IdE;
    @FXML
    private TableColumn<fos_user, String> IdR;
      public int IdDelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
             mycon = new MyBDConnection();
        try {
            LoadU();
        } catch (SQLException ex) {
            Logger.getLogger(LibCatAllController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }   
    
    private void LoadU() throws SQLException {
        CrudUser sr=new CrudUser();
        ObservableList<fos_user> listcat =FXCollections.observableArrayList(sr.AfficherUsers());
        IdN.setCellValueFactory(new PropertyValueFactory<>("nom"));
       IdP.setCellValueFactory(new PropertyValueFactory<>("prenom"));
       IdE.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        table.setItems(listcat);
        
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLException {
            ObservableList<fos_user> selectedRows, AllCovs;
        AllCovs = table.getItems();
        selectedRows = table.getSelectionModel().getSelectedItems();
        for (fos_user c : selectedRows) {

            try {
                IdDelete = c.getId();

            } catch (NumberFormatException e) {

            }
        
            CrudUser s = new CrudUser();

            System.out.println("Id à supprimer :  " + IdDelete);
            if (IdDelete != 0) {
                AllCovs.remove(c);
                s.supprimerUser(IdDelete);
                JOptionPane.showMessageDialog(null, "Supprimeé avec succées");
            } else {
                JOptionPane.showMessageDialog(null, "Vous devez selectionner le user à supprimer");
            }

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
    private void GoToAdminUser(ActionEvent event) {
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
