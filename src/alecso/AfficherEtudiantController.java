/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.Covoiturage;
import alecso.Entity.Etudiant;
import alecsoServices.EtudiantDao;
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
public class AfficherEtudiantController implements Initializable {

    /**
     * Initializes the controller class.
     */
  @FXML
    private Button remove;

    @FXML
    private Button btn_edit;
    
      @FXML
    private Button Annonces;

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

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println("afivher 1");
        
        
        // set editable textfield hidden
        nomtxt.setVisible(false);
        nomlbl.setVisible(false);
        prenomtxt.setVisible(false);
        prenomlbl.setVisible(false);
        declasstxt.setVisible(false);
        declasslbl.setVisible(false);
        versclasstxt.setVisible(false);
        versclasslbl.setVisible(false);
        etablissementtxt.setVisible(false);
        etablissementlbl.setVisible(false);
       
        
        for(Etudiant e : listdata.getAnnEtudiants()){
            System.out.println(e);}
        // TODO
        TableEt.setItems(listdata.getAnnEtudiants());
        
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
       
            remove.setOnAction(event->{
            
            EtudiantDao pdao = EtudiantDao.getInstance();                                               
            int index = TableEt.getSelectionModel().getSelectedIndex();
            Etudiant c = TableEt.getItems().get(index);
            TableEt.getItems().remove(index);
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
        declasstxt.setVisible(true);
        declasslbl.setVisible(true);
        versclasstxt.setVisible(true);
        versclasslbl.setVisible(true);
        etablissementtxt.setVisible(true);
        etablissementlbl.setVisible(true);
      
        
        // get selected item from current row
        EtudiantDao pdao = EtudiantDao.getInstance();                                               
            int index = TableEt.getSelectionModel().getSelectedIndex();
            Etudiant c = TableEt.getItems().get(index);
            nomtxt.setText(c.getNom());
            prenomtxt.setText(c.getPrenom());
            declasstxt.setText(c.getDeclass());
            versclasstxt.setText(c.getVersclass());
            etablissementtxt.setText(c.getEtablissement());
            
	
	
            

            
            btn_edit.setOnAction(event1->{
                
                c.setNom(nomtxt.getText());
                c.setPrenom(prenomtxt.getText());
                c.setDeclass(declasstxt.getText());
                c.setVersclass(versclasstxt.getText());
                c.setEtablissement(etablissementtxt.getText());
             
              
                if(pdao.update(c)){
                    JOptionPane.showMessageDialog(null, "modifier avec succee !");
                            TableEt.refresh();
                            

                };

            
            });
            });
              Annonces.setOnAction(event ->{ try {//FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/alecso/ListEtudiant.fxml"));
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
              
             backk.setOnMouseClicked(event ->{ try {//FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/alecso/AjouterEtudiant.fxml"));
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
             
             
}
   
}

