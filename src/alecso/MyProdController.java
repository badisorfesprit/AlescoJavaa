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
public class MyProdController implements Initializable {

    @FXML
    private TableView<Prod> table;
    @FXML
    private TableColumn<Prod, String> idN;
    @FXML
    private TableColumn<Prod, String> idD;
    @FXML
    private Text libN;
    @FXML
    private Text libD;
    @FXML
    private Text libP;
    @FXML
    private Text libQ;
     public static int IdpourModifier;
      public int IdDelete;
    private MyBDConnection mycon;
    @FXML
    private Text libC;
    @FXML
    private Text n;
    @FXML
    private Text d;
    @FXML
    private Text p;
    @FXML
    private Text q;
    @FXML
    private Text ca;
    @FXML
    private ImageView lblImage;
    private String imageUpload;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
                    Logger.getLogger(MyProdController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(MyProdController.class.getName()).log(Level.SEVERE, null, ex);
               // Clear person details.
        
       
    } 
    }  
     private void showProdDetails(Prod c) throws SQLException {
            Connection con;
          con = MyBDConnection.getInstanceBD().getConnection();
    if (c != null) {
        int idq=c.getCategorie();
        CrudProduits crud=new CrudProduits();
        // Fill the labels with info from the person object.
        libN.setText(c.getNom());
        libD.setText(c.getDescription());
        libP.setText(String.valueOf(c.getPrix()));
        libQ.setText(String.valueOf(c.getStockQty()));
        libC.setText(crud.getLibelle(idq));
         n.setText("Nom de produit:");
         d.setText("Description:");
          p.setText("Prix:");
           q.setText("Quantité:");
            ca.setText("Categorie: ");
           
           
       
            String req = "select * from produits where id= "+c.getIdProd();
    java.sql.PreparedStatement preparedStatement;
        try {
         preparedStatement = con.prepareStatement(req);
         ResultSet result = preparedStatement.executeQuery();
                     while (result.next()) {
String A = c.getNomImage();
                A = "C:\\wamp64\\www\\alecso\\" + A;
            File F1 = new File(A);
            Image image1 = new Image(F1.toURI().toString());
            lblImage.setImage(image1);
                         imageUpload = result.getString("nom_image");
               
                
            }
        }
           catch (SQLException ex) {
                        ex.printStackTrace();
            }
            System.out.println("    image nom"+imageUpload);

        // TODO: We need a way to convert the birthday into a String!
        // birthdayLabel.setText(...);
    } else {
        // Person is null, remove all the text.
        n.setText("");
         d.setText("");
          p.setText("");
           q.setText("");
            ca.setText("");
        libN.setText("");
        libD.setText("");
        libP.setText("");
        libQ.setText("");
        libC.setText("");
        
      
    }}
        

    
     private void LoadProd() throws SQLException {
        CrudProduits sr=new  CrudProduits();
        ObservableList<Prod> prod =FXCollections.observableArrayList(sr.AfficherMesProd());
       
        idN.setCellValueFactory(new PropertyValueFactory<>("nom"));
        idD.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        
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

    @FXML
    private void Modifier(ActionEvent event) throws IOException {
          //go to modifier page

        ObservableList<Prod> selectedRows, AllCovs;
        AllCovs = table.getItems();

        //this gives us the rows that were selected
        selectedRows = table.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Person objects from the table
        for (Prod c : selectedRows) {

            try {
                IdpourModifier = c.getIdProd();

            } catch (NumberFormatException e) {

            }

        }
        System.out.println("Id pour modifier :  " + IdpourModifier);
        if (IdpourModifier != 0) {

            Parent tableViewParent = FXMLLoader.load(getClass().getResource("EditProd.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();

        } else {
            JOptionPane.showMessageDialog(null, "tu dois selectionner le champs a modifier ");
        }
    }

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("AddProd.fxml"));
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
    
}
