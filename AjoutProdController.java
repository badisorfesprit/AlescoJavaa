/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.Prod;
import alecso.Entity.fos_user;
import alecsoServices.CrudProduits;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LINA
 */
public class AjoutProdController implements Initializable {

    @FXML
    private TextField txtImage;
    @FXML
    private TextField txtprix;
    @FXML
    private TextField txtCat;
    @FXML
    private TextField txtDesc;
    @FXML
    private TextField txtStock;
    @FXML
    private Button btnAdd;
    @FXML
    private TextField txtnom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void AjouterProd(ActionEvent event) throws SQLException, ParseException, IOException {
        
        

//        String dateString = DateDepart.getText();
        
//        Date date = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(dateString);
        


        int id = fos_user.getIdcnct();
        int qty = Integer.parseInt(txtStock.getText());
        int pr = Integer.parseInt(txtprix.getText());
        
       

        Prod p = new Prod(txtImage.getText(), id, txtnom.getText(), txtDesc.getText(), pr,txtCat.getText(), qty);
       
        if ( qty<0)
        {
        JOptionPane.showMessageDialog(null, "Nombre de places non valide");
        }
        else{
        CrudProduits service =new CrudProduits(); 
            service.AjouterProduit(p);
           JOptionPane.showMessageDialog(null, "Annonce ajoutée avec succées");
            
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("welcome.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

           window.setScene(tableViewScene);
            window.show();
        }
            
    }

}
