/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.LibCat;
import alecso.Utils.MyBDConnection;
import alecsoServices.CRUDLibCat;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LINA
 */
public class LibCatAllController implements Initializable {
 @FXML
    private TableView<LibCat> table;
    private TableColumn<LibCat, String> idc;
    @FXML
    private TableColumn<LibCat, String> lib;
    @FXML
    private TableColumn<LibCat, String> desc;
private ObservableList<LibCat> data;
    private MyBDConnection mycon;
    public int IdDelete;
     public static int IdpourModifier;
     private int id;
    @FXML
    private Button supp;
    @FXML
    private Text libTit;
    @FXML
    private Text libDesc;
    @FXML
    private Button ed;
   
    /**
     * Initializes the controller class.
     */
      /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
            
             mycon = new MyBDConnection();
        try {
            LoadCat();
        } catch (SQLException ex) {
            Logger.getLogger(LibCatAllController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Clear person details.
    showCatDetails(null);

    // Listen for selection changes and show the person details when changed.
    table.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showCatDetails(newValue));
        
       
    }    
    
    private void showCatDetails(LibCat c) {
    if (c != null) {
        // Fill the labels with info from the person object.
        libTit.setText(c.getLibelle());
        libDesc.setText(c.getDescription());
     

        // TODO: We need a way to convert the birthday into a String!
        // birthdayLabel.setText(...);
    } else {
        // Person is null, remove all the text.
        libTit.setText("");
        libDesc.setText("");
      
    }}
    
 @FXML
    public void GotoModif(ActionEvent event) throws IOException {
        //go to modifier page

        ObservableList<LibCat> selectedRows, AllCovs;
        AllCovs = table.getItems();

        //this gives us the rows that were selected
        selectedRows = table.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Person objects from the table
        for (LibCat c : selectedRows) {

            try {
                IdpourModifier = c.getIdC();

            } catch (NumberFormatException e) {

            }

        }
        System.out.println("Id pour modifier :  " + IdpourModifier);
        if (IdpourModifier != 0) {

            Parent tableViewParent = FXMLLoader.load(getClass().getResource("EditLibCat.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();

        } else {
            JOptionPane.showMessageDialog(null, "tu dois selectionner le champs a modifier ");
        }
    }

    
 private void LoadCat() throws SQLException {
        CRUDLibCat sr=new CRUDLibCat();
        ObservableList<LibCat> listcat =FXCollections.observableArrayList(sr.afficherCat());
        
        lib.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        table.setItems(listcat);
        
    }
 
   
    @FXML
    public void Supprimer(ActionEvent event) throws SQLException {
        ObservableList<LibCat> selectedRows, AllCovs;
        AllCovs = table.getItems();
        selectedRows = table.getSelectionModel().getSelectedItems();
        for (LibCat c : selectedRows) {

            try {
                IdDelete = c.getIdC();

            } catch (NumberFormatException e) {

            }
        
            CRUDLibCat s = new CRUDLibCat();

            System.out.println("Id à supprimer :  " + IdDelete);
            if (IdDelete != 0) {
                AllCovs.remove(c);
                s.supprimerLibCat(IdDelete);
                JOptionPane.showMessageDialog(null, "Supprimeé avec succées");
            } else {
                JOptionPane.showMessageDialog(null, "Vous devez selectionner la categorie a supprimer");
            }

        }

    } 

    @FXML
    private void add(ActionEvent event) throws IOException {
        
             Parent root = FXMLLoader.load(getClass().getResource("AffCAt.fxml"));
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

    @FXML
    private void retour(ActionEvent event) {
    }

    }


    
    
    
    

