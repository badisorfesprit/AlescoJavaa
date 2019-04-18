/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.Panier;
import alecso.Entity.Prod;
import alecso.Utils.MyBDConnection;
import alecsoServices.CrudProduits;
import alecsoServices.PanierService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LINA
 */
public class PanierController implements Initializable {
    public int IdDelete;
    public static int IdpourAdd;

    @FXML
    private Text labelUser;
    @FXML
    private TableView<Panier> table;
    @FXML
    private TableColumn<Panier, Integer> idN;
    @FXML
    private TableColumn<Panier, Integer> idQ;
    @FXML
    private TableColumn<Panier , Integer> idP;
  //  public int IdDelete;
    
     private MyBDConnection mycon;
    @FXML
    private Button btnDel;
    @FXML
    private TableColumn<Panier, Integer> idI;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            // TODO
                       mycon = new MyBDConnection();
        try {
            LoadPan();
                        // Clear person details.
   

    // Listen for selection changes and show the person details when changed.
  // 
        } catch (SQLException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
               // Clear person details.
        
       
    } 
        // TODO
    }   
      private void LoadPan() throws SQLException {
        PanierService sr=new  PanierService();
        ObservableList<Panier> prod =FXCollections.observableArrayList(sr.AfficherPan());
            idI.setCellValueFactory(new PropertyValueFactory<>("id"));
        idN.setCellValueFactory(new PropertyValueFactory<>("Prod"));
      
        idQ.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        idP.setCellValueFactory(new PropertyValueFactory<>("total"));
        
        table.setItems(prod);
        
    }
      
        @FXML
    private void retour(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("ProdAll.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
    
        @FXML
    private void home(MouseEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
    

     @FXML
    private void Supprimer(ActionEvent event) throws SQLException, IOException {
           ObservableList<Panier> selectedRows, AllCovs;
        AllCovs = table.getItems();
        selectedRows = table.getSelectionModel().getSelectedItems();
        for (Panier p : selectedRows) {

            try {
                IdDelete = p.getId();
                IdpourAdd=p.getIdProd();
                

            } catch (NumberFormatException e) {

            }
        
            PanierService s = new  PanierService();

            System.out.println("Id à supprimer :  " + IdDelete);
            if (IdDelete != 0 ) {
                if(p.getQuantite()==0){
                AllCovs.remove(p);
                 LoadPan();
                 
                } else{
                s.supprimerProd(IdDelete);
                JOptionPane.showMessageDialog(null, "Retiré avec succées");
                 PanierService crud= new PanierService();
        
        crud.quantiteAdd(IdpourAdd);
        LoadPan();
            } }else {
                JOptionPane.showMessageDialog(null, "Vous devez selectionner le produit à retirer");
            }

        }
    }
    
}
