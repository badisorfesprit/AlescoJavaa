/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import static alecso.AnnoncesadminController.Idpouraff;
import alecso.Entity.Club;
import alecso.Entity.inscetudiant;
import alecso.Utils.MyBDConnection;
import alecsoServices.CrudAnnonce;
import alecsoServices.inscetudiantCrud;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Nawres
 */
public class ListinsclubController implements Initializable {

    @FXML
    private Button catAnnonce;
    @FXML
    private Button Annonces;
    @FXML
    private TableView<inscetudiant> table;
    @FXML
    private TableColumn<inscetudiant, String> nom;
    @FXML
    private TableColumn<inscetudiant, Date> date;
    @FXML
    private TableColumn<inscetudiant, String> etab;
    @FXML
    private TableColumn<inscetudiant, String> adresse;
     private MyBDConnection mycon;
    @FXML
    private TabPane page;
    @FXML
    private Tab page1;
    @FXML
    private Tab page2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         mycon = new MyBDConnection();
        try {
            Loadinsc();
        } catch (SQLException ex) {
            Logger.getLogger(AffcatcController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    private void Loadinsc() throws SQLException {
        inscetudiantCrud sr=new inscetudiantCrud();
        ObservableList<inscetudiant> listclubs =FXCollections.observableArrayList(sr.Displayclubinsc(Idpouraff));
        
        nom.setCellValueFactory(new PropertyValueFactory<>("username"));
        date.setCellValueFactory(new PropertyValueFactory<>("datenaissance"));
        etab.setCellValueFactory(new PropertyValueFactory<>("ecole"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
       
       
        
        table.setItems(listclubs);
        
        
    } 

    @FXML
    private void CatC(ActionEvent event) {
    }

    @FXML
    private void Annonces(ActionEvent event) {
    }

    @FXML
    private void pageaff(Event event) {
        mycon = new MyBDConnection();
        try {
            Loadinsc();
        } catch (SQLException ex) {
            Logger.getLogger(AffcatcController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void pagestat(Event event) {
    }
    
}
