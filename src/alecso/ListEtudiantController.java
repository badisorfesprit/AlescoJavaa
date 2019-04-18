/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.Covoiturage;
import alecso.Entity.Etudiant;
import alecso.Entity.prof;
import alecsoServices.EtudiantDao;
import alecsoServices.ProfDao;
import java.io.IOException;
import java.net.URL;
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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author wiemhjiri
 */
public class ListEtudiantController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField recherchetxt;
  @FXML
    private Button changer;

    @FXML
    private Button btn_edit;

    @FXML
    private TextField nomtxt;

    @FXML
    private TextField etablissementtxt;

    @FXML
    private TextField versclasstxt;

    @FXML
    private TextField declasstxt;

    @FXML
    private TextField prenomtxt;

    @FXML
    private Label nomlbl;

    @FXML
    private Label prenomlbl;

    @FXML
    private Label declasslbl;

    @FXML
    private Label versclasslbl;

    @FXML
    private Label etablissementlbl;

    @FXML
    private TableView<Etudiant> TableEt;

    @FXML
    private TableColumn<Etudiant, String> nomEt;

    @FXML
    private TableColumn<Etudiant, String> prenomEt;

    @FXML
    private TableColumn<Etudiant, String> declassEt;

    @FXML
    private TableColumn<Etudiant, String> versclassEt;

    @FXML
    private TableColumn<Etudiant, String> etablissementEt;
      @FXML
    private ImageView backk;
    
    

        private ListData listdata = new ListData();
        private ObservableList<Etudiant> masterData = FXCollections.observableArrayList();


  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           masterData = listdata.getEtudiants();
       
        // TODO
        TableEt.setItems(listdata.getEtudiants());
        
        nomEt.setCellValueFactory(cell -> cell.
                getValue().getnomProperty());
        prenomEt.setCellValueFactory(cell -> cell.
                getValue().getprenomProperty());
        declassEt.setCellValueFactory(cell -> cell.
                getValue().getdeclassProperty());
        versclassEt.setCellValueFactory(cell -> cell.
                getValue().getVersclassProperty());
        etablissementEt.setCellValueFactory(cell -> cell.
                getValue().getetablissementProperty());
        
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
         FilteredList<Etudiant> filteredData = new FilteredList<>(masterData, p -> true);
                 // 2. Set the filter Predicate whenever the filter changes.
        recherchetxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Etudiant -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (Etudiant.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (Etudiant.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }else if (Etudiant.getDeclass().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }else if (Etudiant.getVersclass().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }else if (Etudiant.getEtablissement().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Etudiant> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(TableEt.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        TableEt.setItems(sortedData);

    backk.setOnMouseClicked(event ->{ try {//FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/alecso/AfficherEtudiant.fxml"));
                // Give the controller access to the main app.
//                AfficherPersonneController controller =loader.getController();
//                controller.setListData(new ListData());
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilAnnotationController.class.getName()).log(Level.SEVERE, null, ex);
            }});
             
       changer.setOnAction(event->{
           
            EtudiantDao pdao = EtudiantDao.getInstance();                                              
            int index = TableEt.getSelectionModel().getSelectedIndex();
            Etudiant e = TableEt.getItems().get(index);
            String declass = e.getDeclass();
            e.setDeclass(e.getVersclass());
            e.setVersclass(declass);
            
            if(pdao.update(e)){
            
                JOptionPane.showMessageDialog(null, "changer avec succee !");
                TableEt.refresh();
            };
            
       
       });
           
      
    
	
	
            

        
            
      
}
}
   


