/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.CategorieC;
import alecsoServices.CrudCatC;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Nawres
 */
public class AjoutCatCController implements Initializable {

    @FXML
    private TextField libelle;
    @FXML
    private TextArea desc;
    @FXML
    private Button valider;
    @FXML
    private Text txtajout;

    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //txtmodif.setVisible(false);
        //valider2.setVisible(false);
    }    

    @FXML
    private void validercat(ActionEvent event) throws IOException {
        if(libelle.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Entrez la libelle svp!");}
        else if(desc.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Entrez la description svp!!");}
        else if(!libelle.getText().matches("^[a-zA-Z]+$") )
         {
          JOptionPane.showMessageDialog(null,"Entrez un libelle valide svp!");
         }
         else {
            CrudCatC cat = new CrudCatC();  
            CategorieC c = new CategorieC(libelle.getText(),desc.getText());
            cat.AddCatC(c);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Categorie ajoutée avec succés!");
            alert.show(); 
             Parent tableViewParent = FXMLLoader.load(getClass().getResource("affcatc.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        }
    }



   
    
}
