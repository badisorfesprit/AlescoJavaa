/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.CategorieC;
import alecso.Entity.Club;
import alecso.Utils.MyBDConnection;
import alecsoServices.CrudAnnonce;
import alecsoServices.CrudCatC;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Nawres
 */
public class AffcatcController implements Initializable {

    @FXML
    private Button gotocat;
    @FXML
    private Button gotoannonces;
    @FXML
    private TableView<CategorieC> table;
    @FXML
    private TableColumn<CategorieC, String> libelle;
    @FXML
    private TableColumn<CategorieC, String> description;
    private MyBDConnection mycon;
    @FXML
    private Button addcat;
    @FXML
    private Button updatecat;
    @FXML
    private Button deletecat;
    @FXML
    private Button listbtn;
    public int IdDelete;
    public static int IdpourModifier,Idpouraff;
    @FXML
    private TextField recherche;
    public ArrayList<CategorieC> ran;
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
            
             mycon = new MyBDConnection();
        try {
            LoadCats();
        } catch (SQLException ex) {
            Logger.getLogger(AffcatcController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }    
 private void LoadCats() throws SQLException {
        CrudCatC sr=new CrudCatC();
        ObservableList<CategorieC> listcats =FXCollections.observableArrayList(sr.Displaycat());
        
        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        table.setItems(listcats);
        
    }
    @FXML
    private void gotocat(ActionEvent event) {
    }

    @FXML
    private void gotoannonces(ActionEvent event) {
    }

    @FXML
    private void addcat(ActionEvent event) throws IOException {
        
         Parent root = FXMLLoader.load(getClass().getResource("ajoutCatC.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
       
    }

    @FXML
    private void updatecat(ActionEvent event) throws IOException {
        ObservableList<CategorieC> selectedRows, listcats;
        listcats = table.getItems();

        //this gives us the rows that were selected
        selectedRows = table.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Person objects from the table
        for (CategorieC c : selectedRows) {

            try {
                IdpourModifier = c.getId();

            } catch (NumberFormatException e) {

            }

        }
        System.out.println("Id pour modifier :  " + IdpourModifier);
        if (IdpourModifier != 0) {

            Parent tableViewParent = FXMLLoader.load(getClass().getResource("modifcatc.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();

        } else {
            JOptionPane.showMessageDialog(null, "tu dois selectionner le champs a modifier ");
        }
    }

    @FXML
    private void deletecat(ActionEvent event) {
           ObservableList<CategorieC> selectedRows, AllCovs;
        AllCovs = table.getItems();
        selectedRows = table.getSelectionModel().getSelectedItems();
        for (CategorieC c : selectedRows) {

            try {
                IdDelete = c.getId();

            } catch (NumberFormatException e) {

            }
        
            CrudCatC s = new CrudCatC();

            System.out.println("Id à supprimer :  " + IdDelete);
            if (IdDelete != 0) {
                AllCovs.remove(c);
                s.DeleteCat(IdDelete);
                JOptionPane.showMessageDialog(null, "Supprimeé avec succées");
            } else {
                JOptionPane.showMessageDialog(null, "Vous devez selectionner le covoiturage a supprimer");
            }

        }
            
    }
    

    @FXML
    private void listbtn(ActionEvent event) throws IOException {
        //SELECT cl.*,l.libelle FROM club cl INNER JOIN categorie_c l ON cl.CategorieC=l.id
         ObservableList<CategorieC> selectedRows, listcats;
        listcats = table.getItems();

        //this gives us the rows that were selected
        selectedRows = table.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Person objects from the table
        for (CategorieC c : selectedRows) {

            try {
                Idpouraff = c.getId();
                

            } catch (NumberFormatException e) {

            }

        }
        System.out.println("Id pour aff :  " + Idpouraff);
        if (Idpouraff != 0) {

            Parent tableViewParent = FXMLLoader.load(getClass().getResource("listcatclub.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();

        } else {
            JOptionPane.showMessageDialog(null, "tu dois selectionner un champ  ");
        }
    }

    @FXML
    private void rech(KeyEvent event) {
        Task<ArrayList<CategorieC>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                
                ran = (ArrayList<CategorieC>) new CrudCatC().rechercheadmin(recherche.getText());
                //  ran = (ArrayList<Salle>) new CRUDSalle().afficherSalle();
                return ran;
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {
           
        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

           

            // pourcentage.setCellValueFactory(new PropertyValueFactory<>("pourcentage"));
            //  table.setItems(FXCollections.observableArrayList(task.getValue()));
            table.setItems(FXCollections.observableArrayList(task.getValue()));
            //ObservableList<Salle> s=FXCollections.observableArrayList();

        });
        task.setOnFailed(e -> {
            try {
                LoadCats();
            } catch (SQLException ex) {
                Logger.getLogger(AffcatcController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Thread th = new Thread(task);
        th.start(); 
    }
    
}
