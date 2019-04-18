/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import static alecso.AffcatcController.Idpouraff;
import alecso.Entity.Club;
import alecso.Utils.MyBDConnection;
import alecsoServices.CrudAnnonce;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Nawres
 */
public class ListcatclubController implements Initializable {

    @FXML
    private Button catAnnonce;
    @FXML
    private Button Annonces;
    @FXML
    private TableView<Club> table;
    @FXML
    private TableColumn<Club, String> nom;
    @FXML
    private TableColumn<Club, Date> delai;
    @FXML
    private TableColumn<Club, Integer> places;
    @FXML
    private TableColumn<Club, String> etat;
    @FXML
    private TableColumn<Club, Integer> nbinsc;
    @FXML
    private TableColumn<Club, String> nature;
    @FXML
    private Text total;
    private MyBDConnection mycon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mycon = new MyBDConnection();
        try {
            Loadannonces();
        } catch (SQLException ex) {
            Logger.getLogger(AffcatcController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    private void Loadannonces() throws SQLException {
        CrudAnnonce sr=new CrudAnnonce();
        ObservableList<Club> listclubs =FXCollections.observableArrayList(sr.Displayclubcat(Idpouraff));
        
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        delai.setCellValueFactory(new PropertyValueFactory<>("delai"));
        places.setCellValueFactory(new PropertyValueFactory<>("place"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        nbinsc.setCellValueFactory(new PropertyValueFactory<>("nbinscriptions"));
        nature.setCellValueFactory(new PropertyValueFactory<>("libelle"));
       
        
        table.setItems(listclubs);
        
        
    } 

    @FXML
    private void CatC(ActionEvent event) {
    }

    @FXML
    private void Annonces(ActionEvent event) {
    }
    
}
