/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.Covoiturage;
import alecso.Entity.prof;
import alecsoServices.ProfDao;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
public class AfficherProfController implements Initializable {

    /**
     * Initializes the controller class.
     */
 
   
           @FXML
    private Button remove;

    @FXML
    private Button btn_edit;

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
    private ImageView backk;

    @FXML
    private Button annonces;

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
    
   

    private ListData listdata = new ListData();

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // set editable textfield hidden
        nomtxt.setVisible(false);
        nomlbl.setVisible(false);
        prenomtxt.setVisible(false);
        prenomlbl.setVisible(false);
        statsocialetxt.setVisible(false);
        statsocialelbl.setVisible(false);
        deregiontxt.setVisible(false);
        deregionlbl.setVisible(false);
        versregiontxt.setVisible(false);
        versregionlbl.setVisible(false);
        etablissementtxt.setVisible(false);
        etablissementlbl.setVisible(false);
        emailtxt.setVisible(false);
        emaillbl.setVisible(false);
       
        // TODO
        TableP.setItems(listdata.getAnnProf());
        
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
       
            remove.setOnAction(event->{
            
            ProfDao pdao = ProfDao.getInstance();                                               
            int index = TableP.getSelectionModel().getSelectedIndex();
            prof c = TableP.getItems().get(index);
            TableP.getItems().remove(index);
            pdao.delete(c);
            Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("supprimer avec succes !");
                    alert.show();
            });
            
            
            btn_edit.setOnAction(event->{
                
            
        nomtxt.setVisible(true);
        nomlbl.setVisible(true);
        prenomtxt.setVisible(true);
        prenomlbl.setVisible(true);
        statsocialetxt.setVisible(true);
        statsocialelbl.setVisible(true);
        deregiontxt.setVisible(true);
        deregionlbl.setVisible(true);
        versregiontxt.setVisible(true);
        versregionlbl.setVisible(true);
        etablissementtxt.setVisible(true);
        etablissementlbl.setVisible(true);
        emailtxt.setVisible(true);
        emaillbl.setVisible(true);
        
        // get selected item from current row
        ProfDao pdao = ProfDao.getInstance();                                               
            int index = TableP.getSelectionModel().getSelectedIndex();
            prof c = TableP.getItems().get(index);
            nomtxt.setText(c.getNom());
            prenomtxt.setText(c.getPrenom());
            statsocialetxt.setText(c.getStatsociale());
            deregiontxt.setText(c.getDeregion());
            versregiontxt.setText(c.getVersregion());
            etablissementtxt.setText(c.getEtablissement());
            emailtxt.setText(c.getEmail());
            
	
	
            

            
            btn_edit.setOnAction(event1->{
                
                c.setNom(nomtxt.getText());
                c.setPrenom(prenomtxt.getText());
                c.setStatsociale(statsocialetxt.getText());
                c.setDeregion(deregiontxt.getText());
                c.setVersregion(versregiontxt.getText());
                c.setEtablissement(etablissementtxt.getText());
                c.setEmail(emailtxt.getText());
              
                if(pdao.update(c)){
                    JOptionPane.showMessageDialog(null, "modifier avec succee !");
                            TableP.refresh();
                            

                };

            
            });
            });
            
                        annonces.setOnAction(event -> {
            try {//FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/alecso/ListProf.fxml"));
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

