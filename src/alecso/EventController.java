/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.Evenement;
import alecsoServices.EvenementDB;
import java.io.IOException;
import java.sql.Date;
import javafx.fxml.FXML;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import alecso.Utils.MyBDConnection;
import static java.lang.Math.E;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class EventController implements Initializable {
    
     @FXML
    private TableView<Evenement> Event_tab;
    @FXML
    private TableColumn<Evenement, Integer> id_COL;
    @FXML
    private TableColumn<Evenement, String> titre_COL;
    @FXML
    private TableColumn<Evenement, String> lieu_COL;
    @FXML
    private TableColumn<Evenement, String> description_COL;
    @FXML
    private TableColumn<Evenement, Date> date_event_COL;
    @FXML
    private TableColumn<Evenement, Integer> nbplace_COL;
    @FXML
    private TableColumn<Evenement, String> nom_image_COL;
@FXML
    private TableColumn<Evenement, Integer> categorie_COL;
@FXML
    private Text labelUser;
@FXML
    private Button b1;
@FXML
    private Button supprimer;


    @FXML
    private TextField q1_aff;

    @FXML
    private TextField q2_aff;

    @FXML
    private TextField q3_aff;
 @FXML
    private TextField q4_aff;
    @FXML
    private TextField q5_aff;

    @FXML
    private TextField q6_aff;

    @FXML
    private TextField q7_aff;

    @FXML
    private TextField q8_aff;
          public static int IdpourReserver;




 private ObservableList<Evenement> data;
    @FXML
    private Button reservation;
    @FXML
    private Button exp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
         EvenementDB  crud = new  EvenementDB  ();
          id_COL.setCellValueFactory(new PropertyValueFactory<>("id")); //from entity
     titre_COL.setCellValueFactory(new PropertyValueFactory<>("titre"));
    lieu_COL.setCellValueFactory(new PropertyValueFactory<>("lieu"));
   description_COL.setCellValueFactory(new PropertyValueFactory<>("description")); //from entity
   date_event_COL.setCellValueFactory(new PropertyValueFactory<>("date_event"));
  nbplace_COL.setCellValueFactory(new PropertyValueFactory<>("nbplace"));
  nom_image_COL.setCellValueFactory(new PropertyValueFactory<>("nom_image"));

  categorie_COL.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
  	
        
 Event_tab.setItems(crud.afficherEvent());  // TODO
        // TODO
    }  
    
    
     @FXML
    void btn(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("ajoutEvent.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
        

    }
    
     @FXML
    void supprimer_evenement(ActionEvent event) {
     
 EvenementDB  cs = new EvenementDB ();
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog");
alert.setHeaderText("Look, a Confirmation Dialog");
alert.setContentText("you are sure to delete");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    // ... user chose OK
} else {
    // ... user chose CANCEL or closed the dialog
}
  cs.delete_Event(Event_tab.getSelectionModel().getSelectedItem());
    
        
      Event_tab.setItems(cs.afficherEvent()); //Affichage après suppression
   
    
    }
    
          @FXML
	public void click_affiche()
	{
    
     try
		{
			Evenement evenement=(Evenement)   Event_tab.getSelectionModel().getSelectedItem();
			String query="select * from evenement";
		
   
     
     
     
             Connection mycon = MyBDConnection.getInstanceBD().getConnection(); 
             Statement st = mycon.createStatement();
         
         String str = ""+evenement.getId();
        q1_aff.setText(str);
            
            q2_aff.setText(evenement.getTitre());
             String str2 = ""+evenement.getLieu();
        q3_aff.setText(str2);
             String str3 = ""+evenement.getDescription();
    q4_aff.setText(str3);
      String str4 = ""+evenement.getDate_event();
   q5_aff.setText(str4);
          String str5 = ""+evenement.getNbplace();
   q6_aff.setText(str5);    
	  String str6 = ""+evenement.getNom_image();
   q7_aff.setText(str6);  
     String str7 = ""+evenement.getId_categorie();
   q8_aff.setText(str7);  
            st.executeUpdate(query);
        } catch (SQLException ex) {
           // Logger.getLogger(PersonneDB.class.getName()).log(Level.SEVERE, null, ex);
        }
			
		
			
    
}  
    
  /*
    @FXML
    void save_reservation(ActionEvent event) {
 
    
         Notifications.create()    
        .title("notification")
        .text("Votre reservation effectuee avec succes").darkStyle().position(Pos.BOTTOM_CENTER)
                .showWarning();
                
        
    }  
    
    */
    
        @FXML
    private void save_reservation(ActionEvent event) throws SQLException, IOException {
           ObservableList<Evenement> selectedRows, AllCovs;
        AllCovs = Event_tab.getItems();

        //this gives us the rows that were selected
        selectedRows =Event_tab.getSelectionModel().getSelectedItems();
        for ( Evenement c : selectedRows) {

            try {
                IdpourReserver = c.getId();

            } catch (NumberFormatException e) {

            }

        }

        //loop over the selected rows and remove the Person objects from the table
               EvenementDB  crud= new EvenementDB();
      // crud.Reserver(IdpourReserver);
        crud.quantiteReduce(IdpourReserver);
        Event_tab.setItems(crud.afficherEvent());
    }

    @FXML
    private void expbtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("exemple.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

}
