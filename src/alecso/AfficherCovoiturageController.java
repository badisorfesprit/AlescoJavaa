/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.Covoiturage;
import alecsoServices.CovoiturageDao;
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
public class AfficherCovoiturageController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private TableView<Covoiturage> Tableco;

    @FXML
    private TableColumn<Covoiturage, String> Deco;

    @FXML
    private TableColumn<Covoiturage, String> Versco;

    @FXML
    private TableColumn<Covoiturage, Integer> Escallco;

    @FXML
    private TableColumn<Covoiturage, String> Dateco;

    @FXML
    private TableColumn<Covoiturage, Integer> Nbplacesco;

    @FXML
    private TableColumn<Covoiturage, String> intereco;

    @FXML
    private Label idLabel;

    @FXML
    private Label nomLabel;

    @FXML
    private Label prenomLabel;

   
    @FXML
    private Button btn_edit;
    
      @FXML
    private Button remove;
      @FXML
    private Button Annonces;
      
      
         @FXML
    private TextField detxt;

    @FXML
    private TextField interetxt;

    @FXML
    private TextField nbptxt;

    @FXML
    private TextField escaltxt;

    @FXML
    private TextField verstxt;

    @FXML
    private Label delbl;

    @FXML
    private Label verslbl;

    @FXML
    private Label escalbl;

    @FXML
    private Label datelbl;

    @FXML
    private Label nbplbl;

    @FXML
    private Label interelbl;

    @FXML
    private DatePicker datetxt;
     @FXML
    private ImageView backk;
    
    

    private ListData listdata = new ListData();

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // set editable textfield hidden
        detxt.setVisible(false);
        delbl.setVisible(false);
        verstxt.setVisible(false);
        verslbl.setVisible(false);
        escaltxt.setVisible(false);
        escalbl.setVisible(false);
        datetxt.setVisible(false);
        datelbl.setVisible(false);
        nbptxt.setVisible(false);
        nbplbl.setVisible(false);
        interetxt.setVisible(false);
        interelbl.setVisible(false);
        // TODO
        Tableco.setItems(listdata.getAnnCovoiturage());
        
        Deco.setCellValueFactory(cell -> cell.
                getValue().getDeProperty());
        Versco.setCellValueFactory(cell -> cell.
                getValue().getVersProperty());
        Escallco.setCellValueFactory(cell -> cell.
                getValue().getEscallProperty().asObject());
        Dateco.setCellValueFactory(cell -> cell.getValue().getDateProperty());
        Nbplacesco.setCellValueFactory(cell -> cell.getValue().getNBPProperty().asObject());
        intereco.setCellValueFactory(cell -> cell.
                getValue().getIntereProperty());
        
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
            
            CovoiturageDao pdao = CovoiturageDao.getInstance();                                               
            int index = Tableco.getSelectionModel().getSelectedIndex();
            Covoiturage c = Tableco.getItems().get(index);
            Tableco.getItems().remove(index);
            pdao.delete(c);
            Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("supprimer avec succes !");
                    alert.show();
            });
            
            
            btn_edit.setOnAction(event->{
                
            
        detxt.setVisible(true);
        delbl.setVisible(true);
        verstxt.setVisible(true);
        verslbl.setVisible(true);
        escaltxt.setVisible(true);
        escalbl.setVisible(true);
        datetxt.setVisible(true);
        datelbl.setVisible(true);
        nbptxt.setVisible(true);
        nbplbl.setVisible(true);
        interetxt.setVisible(true);
        interelbl.setVisible(true);
        
        // get selected item from current row
        CovoiturageDao pdao = CovoiturageDao.getInstance();                                               
            int index = Tableco.getSelectionModel().getSelectedIndex();
            Covoiturage c = Tableco.getItems().get(index);
            detxt.setText(c.getDe());
            verstxt.setText(c.getVers());
            escaltxt.setText(String.valueOf(c.getEscall()));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	//convert String to LocalDate
	LocalDate localDate = LocalDate.parse(c.getDateC(), formatter);
            datetxt.setValue(localDate);
            nbptxt.setText(String.valueOf(c.getNbplaces()));
            interetxt.setText(c.getIntere());

            
            btn_edit.setOnAction(event1->{
                
                c.setDe(detxt.getText());
                c.setVers(verstxt.getText());
                c.setEscall(Integer.parseInt(escaltxt.getText()));
                c.setNbplaces(Integer.parseInt(nbptxt.getText()));
                c.setDateC(String.valueOf(datetxt.getValue()));
                c.setIntere(interetxt.getText());
                if(pdao.update(c)){
                    JOptionPane.showMessageDialog(null, "modifier avec succee !");
                            Tableco.refresh();
                            

                };

            
            });
            });
            
                Annonces.setOnAction(event ->{ try {//FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/alecso/ListCovoiturage.fxml"));
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
            
                backk.setOnMouseClicked(event -> {
            try {//FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/alecso/AjouterCovoiturage.fxml"));
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
