/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.Prod;
import alecso.Utils.MyBDConnection;
import alecsoServices.CrudProduits;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LINA
 */
public class AdminProdController implements Initializable {

    @FXML
    private TableView<Prod> table;
    @FXML
    private TableColumn<Prod, String> IdN;
    @FXML
    private TableColumn<Prod, String> IdD;
    @FXML
    private Text lblPr;
    @FXML
    private Text lblCat;
    @FXML
    private Text lblQ;
    @FXML
    private Text lblP;
    @FXML
    private Text txtPr;
    @FXML
    private Text txtCat;
    @FXML
    private Text txtQ;
    @FXML
    private Text txtP;
    @FXML
    private ImageView libImage;
    
    private String imageUpload;
      private MyBDConnection mycon;
      public int IdDelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         mycon = new MyBDConnection();
        try {
            LoadProd();
                        // Clear person details.
    showProdDetails(null);

    // Listen for selection changes and show the person details when changed.
    table.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                try {
                    showProdDetails(newValue);
                } catch (SQLException ex) {
                    Logger.getLogger(AdminProdController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(AdminProdController.class.getName()).log(Level.SEVERE, null, ex);
               // Clear person details.
        
       
    }    
        // TODO
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLException {
             ObservableList<Prod> selectedRows, AllCovs;
        AllCovs = table.getItems();
        selectedRows = table.getSelectionModel().getSelectedItems();
        for (Prod c : selectedRows) {

            try {
                IdDelete = c.getIdProd();

            } catch (NumberFormatException e) {

            }
        
            CrudProduits s = new  CrudProduits();

            System.out.println("Id à supprimer :  " + IdDelete);
            if (IdDelete != 0) {
                AllCovs.remove(c);
                s.supprimerProd(IdDelete);
                JOptionPane.showMessageDialog(null, "Supprimeé avec succées");
            } else {
                JOptionPane.showMessageDialog(null, "Vous devez selectionner le produit à supprimer");
            }

        }
    }

 private void showProdDetails(Prod c) throws SQLException {
        Connection con;
        con = MyBDConnection.getInstanceBD().getConnection();
           
    if (c != null) {
          int idq=c.getCategorie();
          int idp=c.getIdUser();
        CrudProduits crud=new CrudProduits();
        System.out.println(idq);
         System.out.println(c.getPrix());
        
           
      
        // Fill the labels with info from the person object.
        
        
       
       txtP.setText(String.valueOf(c.getPrix()));
        txtQ.setText(String.valueOf(c.getStockQty()));
        txtCat.setText(crud.getLibelle(idq));
        txtPr.setText(crud.getProp(idp));
       // libCat.setText(CrudProduits.getLibelle(idq));
         lblCat.setText("Categorie:");
         lblP.setText("prix");
          lblPr.setText("Proprietaire");
           lblQ.setText("Quantité:");
            
            
            
          
            
            String req = "select * from produits where id= "+c.getIdProd();
    java.sql.PreparedStatement preparedStatement;
        try {
         preparedStatement = con.prepareStatement(req);
         ResultSet   result = preparedStatement.executeQuery();
                     while (result.next()) {
String A = c.getNomImage();
                A =  "C:\\wamp64\\www\\alecso\\" + A;
            File F1 = new File(A);
            Image image1 = new Image(F1.toURI().toString());
            libImage.setImage(image1);
                         imageUpload = result.getString("nom_image");
               
                
            }
        }
           catch (SQLException ex) {
                        ex.printStackTrace();
            }
            System.out.println("    image nom"+imageUpload);

           // imageUpload = rs.getString("affiche");
           
           

        // TODO: We need a way to convert the birthday into a String!
        // birthdayLabel.setText(...);
    } else {
        // Person is null, remove all the text.
     txtP.setText("");
        txtQ.setText("");
        txtCat.setText("");
        txtPr.setText("");
       // libCat.setText(CrudProduits.getLibelle(idq));
         lblCat.setText("");
         lblP.setText("");
          lblPr.setText("");
           lblQ.setText("");
            
            
        
      
    }}
    
    
        
 private void LoadProd() throws SQLException {
        CrudProduits sr=new  CrudProduits();
        ObservableList<Prod> prod =FXCollections.observableArrayList(sr.AfficherAdminProd());
       
        IdN.setCellValueFactory(new PropertyValueFactory<>("nom"));
        IdD.setCellValueFactory(new PropertyValueFactory<>("description"));
       
       
        table.setItems(prod);
        
    }
     
    @FXML
    private void retour(ActionEvent event) throws IOException {
             Parent root = FXMLLoader.load(getClass().getResource("LibCatAll.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
               Parent root = FXMLLoader.load(getClass().getResource("Acceuil1.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
      private void GoToAdminUser(ActionEvent event) throws IOException {
               Parent root = FXMLLoader.load(getClass().getResource("AdminUser.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void GoToProduit(ActionEvent event) {
        
    }

   
}
