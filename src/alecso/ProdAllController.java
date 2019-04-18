/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.LibCat;
import alecso.Entity.Prod;
import alecso.Utils.MyBDConnection;
import alecsoServices.CRUDLibCat;
import alecsoServices.CrudProduits;
import com.mysql.jdbc.PreparedStatement;
import java.awt.image.BufferedImage;
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
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.input.TouchEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LINA
 */
public class ProdAllController implements Initializable {
     public int IdDelete;
      public static int IdpourReserver;

    @FXML
    private TableView<Prod> table;
    
    @FXML
    private TableColumn<Prod, String> idN;
    @FXML
    private TableColumn<Prod , String> idD;
    private MyBDConnection mycon;
    @FXML
    private Text libN;
    @FXML
    private Text libD;
    @FXML
    private Text libP;
    @FXML
    private Text libQ;
    @FXML
    private Text libCat;
    @FXML
    private ImageView libImage;
    
    public static int IdpourModifier;
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
     private String imageUpload;

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
                    Logger.getLogger(ProdAllController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(ProdAllController.class.getName()).log(Level.SEVERE, null, ex);
               // Clear person details.
        
       
    }    
    }
     
    private void showProdDetails(Prod c) throws SQLException {
        Connection con;
        con = MyBDConnection.getInstanceBD().getConnection();
           
    if (c != null) {
          int idq=c.getCategorie();
        CrudProduits crud=new CrudProduits();
        System.out.println(idq);
         System.out.println(c.getPrix());
        
           
      
        // Fill the labels with info from the person object.
        libN.setText(c.getNom());
        
        libD.setText(c.getDescription());
        libP.setText(String.valueOf(c.getPrix()));
        libQ.setText(String.valueOf(c.getStockQty()));
        libCat.setText(crud.getLibelle(idq));
       // libCat.setText(CrudProduits.getLibelle(idq));
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
                A =  "C:\\wamp64\\www\\alecso\\"+ A;
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
        n.setText("");
         d.setText("");
          p.setText("");
           q.setText("");
            ca.setText("");
        libN.setText("");
        libD.setText("");
        libP.setText("");
        libQ.setText("");
        libCat.setText("");
        
      
    }}
    
    
        
 private void LoadProd() throws SQLException {
        CrudProduits sr=new  CrudProduits();
        ObservableList<Prod> prod =FXCollections.observableArrayList(sr.afficherProd());
       
        idN.setCellValueFactory(new PropertyValueFactory<>("nom"));
        idD.setCellValueFactory(new PropertyValueFactory<>("description"));
       
       
        table.setItems(prod);
        
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
    private void MesProd(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("MyProd.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

  @FXML
    private void Reserver(ActionEvent event) throws SQLException, IOException {
            ObservableList<Prod> selectedRows, AllCovs;
        AllCovs = table.getItems();

        //this gives us the rows that were selected
        selectedRows = table.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Person objects from the table
        for (Prod c : selectedRows) {
if (c.getStockQty()==0){
    JOptionPane.showMessageDialog(null, "Produit Epuisé");}
else{
            try {
                IdpourReserver = c.getIdProd();

            } catch (NumberFormatException e) {

            }

        
        CrudProduits crud= new CrudProduits();
        crud.Reserver(IdpourReserver);
              crud.addUser();
        crud.quantiteReduce(IdpourReserver);
        LoadProd();
    }}}



    @FXML
    private void Panier(MouseEvent event) throws IOException {
               Parent root = FXMLLoader.load(getClass().getResource("panier.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
}
