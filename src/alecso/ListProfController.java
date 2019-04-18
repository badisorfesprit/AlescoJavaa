/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.Covoiturage;
import alecso.Entity.Etudiant;
import alecso.Entity.fos_user;
import alecso.Entity.prof;
import alecsoServices.CrudProduits;
import alecsoServices.CrudUser;
import alecsoServices.EtudiantDao;
import alecsoServices.ProfDao;
import alecsoServices.SendMailImpl;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author wiemhjiri
 */
public class ListProfController implements Initializable {

    /**
     * Initializes the controller class.
     */
 
     @FXML
    private Button sendmail;


   @FXML
    private TextField recherchetxt;
    @FXML
    private TextField statsocialetxt;

    @FXML
    private TextField emailtxt;

    @FXML
    private TextField etablissementtxt;

    @FXML
    private TextField versregiontxt;

    @FXML
    private TextField deregiontxt;

    @FXML
    private Label statsocialelbl;

    @FXML
    private Label deregionlbl;

    @FXML
    private Label versregionlbl;

    @FXML
    private Label etablissementlbl;

    @FXML
    private Label emaillbl;

    @FXML
    private TextField prenomtxt;

    @FXML
    private TextField nomtxt;

    @FXML
    private Label prenomlbl;

    @FXML
    private Label nomlbl;

    @FXML
    private TableView<prof> TableP;

    @FXML
    private TableColumn<prof, String> nomP;

    @FXML
    private TableColumn<prof, String> prenomP;

    @FXML
    private TableColumn<prof, String> statsocialeP;

    @FXML
    private TableColumn<prof, String> deregionP;

    @FXML
    private TableColumn<prof, String> versregionP;

    @FXML
    private TableColumn<prof, String> etablissementP;

    @FXML
    private TableColumn<prof, String> emailP;
    
    @FXML
    private ImageView backk;

    private ListData listdata = new ListData();
        private ObservableList<prof> masterData = FXCollections.observableArrayList();


  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      

        masterData = listdata.getProf();
       
        // TODO
        TableP.setItems(listdata.getProf());
        
        nomP.setCellValueFactory(cell -> cell.
                getValue().getnomProperty());
        prenomP.setCellValueFactory(cell -> cell.
                getValue().getprenomProperty());
        statsocialeP.setCellValueFactory(cell -> cell.
                getValue().getstatsocialeProperty());
        deregionP.setCellValueFactory(cell -> cell.
                getValue().getderegionProperty());
        versregionP.setCellValueFactory(cell -> cell.
                getValue().getversregionProperty());
        etablissementP.setCellValueFactory(cell -> cell.
                getValue().getetablissementProperty());
         emailP.setCellValueFactory(cell -> cell.
                getValue().getemailProperty());
        
        
       /* personsTable.setOnMouseClicked(event->{
        idLabel.setText(String.valueOf(listdata.getPersons()
                .get(personsTable.getSelectionModel().getSelectedIndex())
                .getId()));
        nomLabel.setText(listdata.getPersons()
                .get(personsTable.getSelectionModel().getSelectedIndex())
                .getNom());
        prenomLabel.setText(listdata.getPersons()
                .get(personsTable.getSelectionModel().getSelectedIndex())
                .getPrenom());
    
    });*/
        //Redirection vers l'interface PieChart
       /* btn_pie.setOnAction(event->{
            try {
                System.out.println("testttttttttttttt");
                Parent pagePieChart=FXMLLoader.load(getClass().getResource("/com/esprit/view/PieChartView.fxml"));
                Scene scene=new Scene(pagePieChart);
                Stage stage=(Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AfficherCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });*/
       
       
               FilteredList<prof> filteredData = new FilteredList<>(masterData, p -> true);
                 // 2. Set the filter Predicate whenever the filter changes.
        recherchetxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(prof -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (prof.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (prof.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }else if (prof.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }else if (prof.getStatsociale().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }else if (prof.getDeregion().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }else if (prof.getVersregion().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }else if (prof.getEtablissement().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<prof> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(TableP.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        TableP.setItems(sortedData);

       
        sendmail.setOnAction(event->{
      
      
        
        // get selected item from current row
        ProfDao pdao = ProfDao.getInstance() ;                                              
            int index = TableP.getSelectionModel().getSelectedIndex();
            prof p = TableP.getItems().get(index);
            SendMailImpl sMail = new SendMailImpl();
            CrudUser cu = new CrudUser();
            fos_user user;
            try {
                user = cu.getconnecteduser(fos_user.getIdcnct());
            } catch (SQLException ex) {
                Logger.getLogger(ListProfController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            try {
                sMail.sendMessage(p.getEmail(),"dv",",jh,");
                System.out.println("send");
            } catch (MessagingException ex) {
                Logger.getLogger(ListProfController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       
  
                 backk.setOnMouseClicked(event -> {
            try {//FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/alecso/AjouterProf.fxml"));
                // Give the controller access to the main app.
//                AfficherPersonneController controller =loader.getController();
//                controller.setListData(new ListData());
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilAnnotationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
                 
            
}
   
}

