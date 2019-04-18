/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import static alecso.AnnoncesfrontController.Idpourajouter;
import alecso.Entity.CategorieC;
import alecso.Entity.Club;
import alecso.Entity.fos_user;
import alecso.Entity.inscetudiant;
import alecsoServices.CrudAnnonce;
import alecsoServices.CrudCatC;
import alecsoServices.inscetudiantCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Nawres
 */
public class InscetudiantController implements Initializable {

    @FXML
    private DatePicker date;
    @FXML
    private TextField etab;
    @FXML
    private TextField ad;
    @FXML
    private TableView<inscetudiant> table;
    @FXML
    private TableColumn<inscetudiant, Date> datetable;
    @FXML
    private TableColumn<inscetudiant, String> etabtable;
    @FXML
    private TableColumn<inscetudiant, String> adressetable;
    @FXML
    private Button validerajout;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private ImageView retour;
    @FXML
    private Button validermodifier;
    @FXML
    private Text labelUser;
    public int IdDelete,IdDelete2;
    public static int placeupdate,nbinsc;
    public static inscetudiant e;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        validermodifier.setVisible(false);
        Loadinsc();
        
    }    
    private void Loadinsc(){
        inscetudiantCrud sr=new inscetudiantCrud();
         int id = fos_user.getIdcnct();
        ObservableList<inscetudiant> listinsc =FXCollections.observableArrayList(sr.Displayoneinsc(id));
        
        datetable.setCellValueFactory(new PropertyValueFactory<>("datenaissance"));
        etabtable.setCellValueFactory(new PropertyValueFactory<>("ecole"));
        adressetable.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        

        table.setItems(listinsc);
        
    }

    @FXML
    private void validerajout(ActionEvent event) {
        inscetudiantCrud ic = new inscetudiantCrud();
         int id = fos_user.getIdcnct();
         inscetudiant e ;
         java.sql.Date naissance = java.sql.Date.valueOf(date.getValue());
        e = new inscetudiant(Idpourajouter,id,naissance ,etab.getText(),ad.getText());
                
        ic.addenfant(e, Idpourajouter);
        JOptionPane.showMessageDialog(null, "Annonce ajoutée avec succées");
        System.out.println("ajouter");
        
       Loadinsc();
       clear();
                    
                    
                    
                    
                    
           
        
        }

    private void clear() {
        etab.clear();
        ad.clear();
        date.setValue(null);
    }
        
        
    

    @FXML
    private void modifier(ActionEvent event) {
        validermodifier.setVisible(true);
        validerajout.setVisible(false);
        e = (inscetudiant) table.getSelectionModel().getSelectedItem();
        inscetudiantCrud sr =new inscetudiantCrud();
        etab.setText(e.getEcole());
        ad.setText(e.getAdresse());
        date.setValue(e.getDatenaissance().toLocalDate());
//        affTxt.setText(e.getAffiche());
        
        
        
    }

    @FXML
    private void supprimer(ActionEvent event) {
        //ObservableList<inscetudiant> selectedRows, Allinsc;
        CrudAnnonce c = new CrudAnnonce();
       
        
        ObservableList<inscetudiant> selectedRows, allinsc, selectedRows2;
        allinsc = table.getItems();
        selectedRows = table.getSelectionModel().getSelectedItems();
        selectedRows2 = table.getSelectionModel().getSelectedItems();
        
        for (inscetudiant i : selectedRows2) {

            try {
                IdDelete2 = i.getClub_id();
                placeupdate = i.getPlace();
                nbinsc = i.getNbinsc();

            } catch (NumberFormatException e) {

            }}
        System.out.println(placeupdate);
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Confirmation de suppression");
        alert1.setHeaderText(null);
        alert1.setContentText("Voulez-vous vraiment supprimer votre évènement ?");
        Optional<ButtonType> result = alert1.showAndWait();
        if (result.get() == ButtonType.OK){
            inscetudiantCrud es = new inscetudiantCrud();
            es.Deleteinsc(table.getSelectionModel().getSelectedItem().getId(),Idpourajouter);
            Loadinsc();
           //  Allinsc.remove(table.getSelectionModel().getSelectedItem().getId());
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Succès");
            alert2.setHeaderText(null);
            alert2.setContentText("Suppression effectuée avec succés!");
            alert2.show();
        } 
    }

    @FXML
    private void retour(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void validermodifier(ActionEvent event) {
         inscetudiantCrud es = new inscetudiantCrud();
          Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("Confirmation Dialog");
            alert2.setHeaderText("Modification");
            alert2.setContentText("etes vous sur de modifier? ");
            Optional <ButtonType> result = alert2.showAndWait(); 
                Date d = java.sql.Date.valueOf((date.getValue()));
        if(result.get()==ButtonType.OK)
         {
         inscetudiant e = new inscetudiant(d,etab.getText(),ad.getText()) ;
         
        es.UpdateEvent(e);
         Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
            alert3.setTitle("Information Dialog");
            alert3.setHeaderText("Modification avec succes ");
            alert3.showAndWait();
                        Loadinsc();
                        clear();  
                        validermodifier.setVisible(false);
                        validerajout.setVisible(true);
         }else{
            validermodifier.setVisible(false);
                        validerajout.setVisible(true);
         clear();
         }
    }
    
    
   
    
}
