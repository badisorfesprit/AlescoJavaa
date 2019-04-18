/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;
import alecso.Entity.Evenement;
import alecso.Entity.categorie;
import alecso.Utils.MyBDConnection;
import alecsoServices.CategorieDB;
import alecsoServices.EvenementDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class CategorieBackController implements Initializable {
    private ObservableList<categorie> data;


    @FXML
    private TableView<categorie> categorie_tab;
    @FXML
    private TableColumn<categorie, Integer> id_COL;
    @FXML
    private TableColumn<categorie, String> libelle_COL;
    @FXML
    private TableColumn<categorie, String> description_COL;

    @FXML
    private TextField id;
   
    @FXML
    private TextField rechercheCol;
    @FXML
    private TextField libelle;
    @FXML
    private TextField description;
    @FXML
    private Button ajout;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         CategorieDB  crud = new  CategorieDB  ();
          data = FXCollections.observableArrayList();
        
        Search();
          
          id_COL.setCellValueFactory(new PropertyValueFactory<>("id")); //from entity
     libelle_COL.setCellValueFactory(new PropertyValueFactory<>("libelle"));
    description_COL.setCellValueFactory(new PropertyValueFactory<>("description"));
  
  categorie_tab.setItems(crud.afficherCategorie());
    }    

    @FXML
    private void supprimer_evenement(ActionEvent event) {
         CategorieDB  cs = new CategorieDB ();
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog");
alert.setHeaderText("Look, a Confirmation Dialog");
alert.setContentText("you are sure to delete");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    // ... user chose OK
} else {
    // ... user chose CANCEL or closed the dialog
}
  cs.delete_Categorie(categorie_tab.getSelectionModel().getSelectedItem());
    
        
      categorie_tab.setItems(cs.afficherCategorie()); //Affichage après suppression
    }

    @FXML
    private void ajouter_evenement(ActionEvent event) {
         if(id.getText().isEmpty() || libelle.getText().isEmpty() || description.getText().isEmpty())
               
         {
          
             Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider vos champs");
            alert1.setHeaderText(null);
            alert1.setContentText("Veuillez remplir les champs ");
            alert1.showAndWait();
         
         }
       else if(!libelle.getText().matches("^[a-zA-Z\\s]*$") )
         {
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider les noms des acteurs");
            alert1.setHeaderText(null);
            alert1.setContentText("Veuillez les noms des lieu ");
            alert1.showAndWait();
         }else if(!description.getText().matches("^[a-zA-Z\\s]*$") )
         {
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider le nom du directeur");
            alert1.setHeaderText(null);
            alert1.setContentText("Veuillez verifier le nom du description ");
            alert1.showAndWait();
         }
         else {
          categorie C;
        C = new categorie(Integer.parseInt(id.getText()),libelle.getText(),description.getText());
        CategorieDB  crud = new CategorieDB  ();
 
      crud.ajouterEvent(C);
    categorie_tab.setItems(crud.afficherCategorie()); 
    Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
            alert3.setTitle("Information Dialog");
            alert3.setHeaderText("Ajout avec Succes");
            Optional <ButtonType> result=alert3.showAndWait();
            if(result.get()==ButtonType.OK)
                {
                    clear();
                }
    }
    }

    @FXML
    private void modifier(ActionEvent event) {
        
          categorie C;
      C = new categorie(Integer.parseInt(id.getText()),libelle.getText(),description.getText());
      CategorieDB  crud = new CategorieDB  ();
 
      crud.Modifier_salle(C);
       categorie_tab.setItems(crud.afficherCategorie()); 
}

   
       
    public void Search(){
       CategorieDB bs = new   CategorieDB();
    
        rechercheCol.setOnKeyReleased(e->{
            if(rechercheCol.getText().equals("")){
                bs.afficherCategorie();
            }
            else {
                data.clear();
                data=bs.Search(rechercheCol.getText());
                 categorie_tab.setItems(data);
            }
        });
        
          
    }
      
     @FXML
	public void click_affiche()
	{
    
     try
		{
			categorie categorie=(categorie)   categorie_tab.getSelectionModel().getSelectedItem();
			String query="select * from categorie";
		
   
     
     
     
             Connection mycon = MyBDConnection.getInstanceBD().getConnection(); 
             Statement st = mycon.createStatement();
         
         String str = ""+categorie.getId();
        id.setText(str);
            
            libelle.setText(categorie.getLibelle());
             String str2 = ""+categorie.getDescription();
        description.setText(str2);
               
            st.executeUpdate(query);
        } catch (SQLException ex) {
           // Logger.getLogger(PersonneDB.class.getName()).log(Level.SEVERE, null, ex);
        }
			
		
			
    
}  
     public void clear()
     {
         id.clear();
         libelle.clear();
         description.clear();
         
         
     
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
    private void GoToProduit(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("AdminProd.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void Annonces(ActionEvent event)  throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("AdminProd.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void CatC(ActionEvent event) throws IOException {
        
 Parent root = FXMLLoader.load(getClass().getResource("affcatc.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();

    }

    @FXML
    private void categoriebtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("categorieBack.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
        
 
    }

    @FXML
    private void bntAnnotation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("backEtudiant.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();}

    @FXML
    private void GoToAffiche(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Affiche.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();}

    @FXML
    private void statbtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("stat.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
     @FXML
    private void retour(ActionEvent event) throws IOException {
               Parent root = FXMLLoader.load(getClass().getResource("LibCatAll.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
}
