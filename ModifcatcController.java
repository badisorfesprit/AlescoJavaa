/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import static alecso.AffcatcController.IdpourModifier;
import alecso.Entity.CategorieC;
import alecso.Utils.MyBDConnection;
import alecsoServices.CrudCatC;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nawres
 */
public class ModifcatcController implements Initializable {

    @FXML
    private Text txtajout;
    @FXML
    private Button valider;
    @FXML
    private TextField libelle;
    @FXML
    private TextArea desc;
    private MyBDConnection mycon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mycon = new MyBDConnection();
        modifierdata();
    }    
     private void modifierdata() {
            Connection con = mycon.getConnection();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `categorie_c` WHERE id="+IdpourModifier);
            while(rs.next()) {
               CategorieC c=(new CategorieC(rs.getString(2),rs.getString(3)));
               
               
               libelle.setText(c.getLibelle()); 
               desc.setText(c.getDescription());
               
               
              
               
                 }
        }catch (SQLException ex) {
            Logger.getLogger(ModifcatcController.class.getName()).log(Level.SEVERE, null, ex);
    }}

    @FXML
    private void validercat(ActionEvent event) throws IOException {
        CrudCatC service=new CrudCatC();
        CategorieC c = new CategorieC();
        c.setLibelle(libelle.getText());
        c.setDescription(desc.getText());
        
    
        
        service.UpdateCat(c, IdpourModifier); 
    
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("affcatc.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
}
