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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
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
public class ListCovoiturageController implements Initializable {

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
    private Button Reserver;
      
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
    
    @FXML
    private TextField reservertxt;
    
        @FXML
    private TextField destinationtxt;

    @FXML
    private TextField arriveetxt;

    @FXML
    private Button rechercherbtn;
    

    private ListData listdata = new ListData();

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        reservertxt.setVisible(false);
        // set editable textfield hidden
      
        // TODO
        Tableco.setItems(listdata.getCovoiturages());
        
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
       
       rechercherbtn.setOnAction(event->{
       if((!destinationtxt.getText().equals("")) && (!arriveetxt.getText().equals("")))
       {
           System.out.println("her");
            CovoiturageDao pdao = CovoiturageDao.getInstance();  
            List<Covoiturage> ls = pdao.recherchecov(destinationtxt.getText(),arriveetxt.getText());
            Tableco.getItems().clear();
             Tableco.setItems((ObservableList<Covoiturage>) ls);
        
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
        
       }else{
           
            Tableco.setItems(listdata.getCovoiturages());
        
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
           
       
       }
       
       
       
       
       
       
       
       
       
       });
     Reserver.setOnAction(event->{
                
            
        reservertxt.setVisible(true);
           
            
            
            Reserver.setOnAction(event1->{
                 CovoiturageDao pdao = CovoiturageDao.getInstance();                                               
            int index = Tableco.getSelectionModel().getSelectedIndex();
            Covoiturage c = Tableco.getItems().get(index);
                int nbp = c.getNbplaces();
            int id = c.getId();
            if(!reservertxt.getText().equals("")){
            int dim = Integer.parseInt(reservertxt.getText());
                if(dim<=nbp){
                    nbp = nbp-dim;
                    
                    if (pdao.updateNBP(nbp, id)){
                            c.setNbplaces(nbp);

                    JOptionPane.showMessageDialog(null, "modifier avec succee !");
                                                Tableco.refresh();

                            

                        }
                    
                }else{
                   JOptionPane.showMessageDialog(null, "champ non valide");

                }
                
               
            }else{
            JOptionPane.showMessageDialog(null, "Champ vide");

            }
            
            });
            
            
            
            
        
        
     });
            
        
                backk.setOnMouseClicked(event -> {
            try {//FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/alecso/AfficherCovoiturage.fxml"));
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
