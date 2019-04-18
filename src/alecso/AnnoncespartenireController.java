/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import static alecso.AnnoncesadminController.IdpourModifier;
import static alecso.AnnoncesadminController.Idpouraff;
import alecso.Entity.Club;
import alecso.Entity.fos_user;
import alecso.Utils.MyBDConnection;
import alecsoServices.CrudAnnonce;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Nawres
 */
public class AnnoncespartenireController implements Initializable {

    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button insclist;
    @FXML
    private TableView<Club> table;
    @FXML
    private TableColumn<Club, String> nom;
    @FXML
    private TableColumn<Club, Date> delai;
    @FXML
    private TableColumn<Club, Integer> places;
    @FXML
    private TableColumn<Club, String> etat;
    @FXML
    private TableColumn<Club, Integer> nbinsc;
    @FXML
    private TableColumn<Club, String> nature;
    public static int IdpourModifier,Idpouraff,Idinsc;
    public int IdDelete;
    @FXML
    private ComboBox<String> group;
    @FXML
    private ComboBox<String> trie;
    public ArrayList<Club> ran;
    ObservableList<String> TriList=FXCollections.observableArrayList("Nombre d'inscription croissant","Nombre d'inscription decroissant","Delai d'inscription croissant","delai d'inscription decroissant");
    ObservableList<String> grplist=FXCollections.observableArrayList("Places disponibles","Places non disponibles","archive");
    @FXML
    private TextField search;
    public int idprolo;
    private MyBDConnection mycon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         mycon = new MyBDConnection();
         
          
        
        try {
            Loadannonces();
        } catch (SQLException ex) {
            Logger.getLogger(AffcatcController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         trie.setItems(TriList);
         group.setItems(grplist);
        
        
        // notif();
    }    
private void Loadannonces() throws SQLException {
        CrudAnnonce sr=new CrudAnnonce();
        int id = fos_user.getIdcnct();
        //String role = fos_user.namecnt
       
          // CrudUser cu = new CrudUser();
          // String role = cu.role(id);
      
          // System.out.println("connected role:"+role);
        ObservableList<Club> listclubs =FXCollections.observableArrayList(sr.Displayadmin());
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        delai.setCellValueFactory(new PropertyValueFactory<>("delai"));
        places.setCellValueFactory(new PropertyValueFactory<>("place"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        nbinsc.setCellValueFactory(new PropertyValueFactory<>("nbinscriptions"));
        nature.setCellValueFactory(new PropertyValueFactory<>("libelle"));
       
        
        table.setItems(listclubs);
        
        
    }
    private void gotocat(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("affcatc.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    private void gotoannonces(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("annoncesadmin.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void add(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("addannonce.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void update(ActionEvent event) throws IOException {
         ObservableList<Club> selectedRows, listclubs;
        listclubs = table.getItems();

        //this gives us the rows that were selected
        selectedRows = table.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Person objects from the table
        for (Club c : selectedRows) {

            try {
                IdpourModifier = c.getId();

            } catch (NumberFormatException e) {

            }

        }
        System.out.println("Id pour modifier :  " + IdpourModifier);
        if (IdpourModifier != 0) {

            Parent tableViewParent = FXMLLoader.load(getClass().getResource("modifannonce.fxml"));
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
    private void delete(ActionEvent event) {
         ObservableList<Club> selectedRows, allclubs;
        allclubs = table.getItems();
        selectedRows = table.getSelectionModel().getSelectedItems();
        for (Club c : selectedRows) {

            try {
                IdDelete = c.getId();

            } catch (NumberFormatException e) {

            }
        
            CrudAnnonce s = new CrudAnnonce();

            System.out.println("Id à supprimer :  " + IdDelete);
            if (IdDelete != 0) {
                allclubs.remove(c);
                s.Deleteannonce(IdDelete);
                
                JOptionPane.showMessageDialog(null, "Supprimeé avec succées");
            } else {
                JOptionPane.showMessageDialog(null, "Vous devez selectionner l'annonce a supprimer a supprimer");
            }

        }
    }

    @FXML
    private void insclist(ActionEvent event) throws IOException {
        ObservableList<Club> selectedRows, listclubs;
        listclubs = table.getItems();

        //this gives us the rows that were selected
        selectedRows = table.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Person objects from the table
        for (Club c : selectedRows) {

            try {
                Idpouraff = c.getId();

            } catch (NumberFormatException e) {

            }

        }
        System.out.println("Id pour modifier :  " + Idpouraff);
        if (Idpouraff != 0) {

            Parent tableViewParent = FXMLLoader.load(getClass().getResource("listinsclub.fxml"));
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
    private void rech(KeyEvent event) {
          Task<ArrayList<Club>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                
                ran = (ArrayList<Club>) new CrudAnnonce().rechercheadmin(search.getText());
                //  ran = (ArrayList<Salle>) new CRUDSalle().afficherSalle();
                return ran;
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        delai.setCellValueFactory(new PropertyValueFactory<>("delai"));
        places.setCellValueFactory(new PropertyValueFactory<>("place"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        nbinsc.setCellValueFactory(new PropertyValueFactory<>("nbinscriptions"));
        nature.setCellValueFactory(new PropertyValueFactory<>("libelle"));
            

           

            // pourcentage.setCellValueFactory(new PropertyValueFactory<>("pourcentage"));
            //  table.setItems(FXCollections.observableArrayList(task.getValue()));
            table.setItems(FXCollections.observableArrayList(task.getValue()));
            //ObservableList<Salle> s=FXCollections.observableArrayList();

        });
        task.setOnFailed(e -> {
            afficherAnnonces();
        });
        Thread th = new Thread(task);
        th.start(); 
    }
    private void afficherAnnonces()
    {
      Task<ArrayList<Club>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                
                ran = (ArrayList<Club>) new CrudAnnonce().Displayadmin();
                //  ran = (ArrayList<Salle>) new CRUDSalle().afficherSalle();
                return ran;
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        delai.setCellValueFactory(new PropertyValueFactory<>("delai"));
        places.setCellValueFactory(new PropertyValueFactory<>("place"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        nbinsc.setCellValueFactory(new PropertyValueFactory<>("nbinscriptions"));
        nature.setCellValueFactory(new PropertyValueFactory<>("libelle"));
            
            
            

           

            // pourcentage.setCellValueFactory(new PropertyValueFactory<>("pourcentage"));
            //  table.setItems(FXCollections.observableArrayList(task.getValue()));
            table.setItems(FXCollections.observableArrayList(task.getValue()));
            //ObservableList<Salle> s=FXCollections.observableArrayList();

        });
        task.setOnFailed(e -> {
            afficherAnnonces();
        });
        Thread th = new Thread(task);
        th.start();  
    }

    @FXML
    private void trie(ActionEvent event) {
         if(trie.getSelectionModel().getSelectedItem().equals("Nombre d'inscription croissant"))
        {
            Trieinscc();
            
            
        }else if (trie.getSelectionModel().getSelectedItem().equals("Nombre d'inscription decroissant"))
        {
            Trieinscd();
        }else if(trie.getSelectionModel().getSelectedItem().equals("Delai d'inscription croissant"))
        {
            Trierdelaic();
        }else if(trie.getSelectionModel().getSelectedItem().equals("delai d'inscription decroissant"))
        {
            Trierdelaid();
        }
    }

    @FXML
    private void group(ActionEvent event) {
         if(group.getSelectionModel().getSelectedItem().equals("Places disponibles"))
        {
            groupdispo();
            
            
        }else if (group.getSelectionModel().getSelectedItem().equals("Places non disponibles"))
        {
            grpnondispo(); 
        }
        else if (group.getSelectionModel().getSelectedItem().equals("archive"))
        {
            grparchive(); 
        }
    }
    private void Trieinscc() {
        Task<ArrayList<Club>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                
                ran = (ArrayList<Club>) new CrudAnnonce().Trieinscc();
                //  ran = (ArrayList<Salle>) new CRUDSalle().afficherSalle();
                return ran;
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        delai.setCellValueFactory(new PropertyValueFactory<>("delai"));
        places.setCellValueFactory(new PropertyValueFactory<>("place"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        nbinsc.setCellValueFactory(new PropertyValueFactory<>("nbinscriptions"));
        nature.setCellValueFactory(new PropertyValueFactory<>("libelle"));
            
       
            table.setItems(FXCollections.observableArrayList(task.getValue()));
           

        });
        task.setOnFailed(e -> {
            try {
                Loadannonces();
            } catch (SQLException ex) {
                Logger.getLogger(AnnoncesadminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Thread th = new Thread(task);
        th.start();
    }

    private void Trieinscd() {
         Task<ArrayList<Club>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                
                ran = (ArrayList<Club>) new CrudAnnonce().Trieinscd();
                //  ran = (ArrayList<Salle>) new CRUDSalle().afficherSalle();
                return ran;
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        delai.setCellValueFactory(new PropertyValueFactory<>("delai"));
        places.setCellValueFactory(new PropertyValueFactory<>("place"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        nbinsc.setCellValueFactory(new PropertyValueFactory<>("nbinscriptions"));
        nature.setCellValueFactory(new PropertyValueFactory<>("libelle"));
            
       
            table.setItems(FXCollections.observableArrayList(task.getValue()));
           

        });
        task.setOnFailed(e -> {
            try {
                Loadannonces();
            } catch (SQLException ex) {
                Logger.getLogger(AnnoncesadminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Thread th = new Thread(task);
        th.start();
    }

    private void Trierdelaic() {
        Task<ArrayList<Club>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                
                ran = (ArrayList<Club>) new CrudAnnonce().Triedelaic();
                //  ran = (ArrayList<Salle>) new CRUDSalle().afficherSalle();
                return ran;
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        delai.setCellValueFactory(new PropertyValueFactory<>("delai"));
        places.setCellValueFactory(new PropertyValueFactory<>("place"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        nbinsc.setCellValueFactory(new PropertyValueFactory<>("nbinscriptions"));
        nature.setCellValueFactory(new PropertyValueFactory<>("libelle"));
            
       
            table.setItems(FXCollections.observableArrayList(task.getValue()));
           

        });
        task.setOnFailed(e -> {
            try {
                Loadannonces();
            } catch (SQLException ex) {
                Logger.getLogger(AnnoncesadminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Thread th = new Thread(task);
        th.start();
    }

    private void Trierdelaid() {
       Task<ArrayList<Club>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                
                ran = (ArrayList<Club>) new CrudAnnonce().Triedelaid();
                //  ran = (ArrayList<Salle>) new CRUDSalle().afficherSalle();
                return ran;
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        delai.setCellValueFactory(new PropertyValueFactory<>("delai"));
        places.setCellValueFactory(new PropertyValueFactory<>("place"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        nbinsc.setCellValueFactory(new PropertyValueFactory<>("nbinscriptions"));
        nature.setCellValueFactory(new PropertyValueFactory<>("libelle"));
            
       
            table.setItems(FXCollections.observableArrayList(task.getValue()));
           

        });
        task.setOnFailed(e -> {
            try {
                Loadannonces();
            } catch (SQLException ex) {
                Logger.getLogger(AnnoncesadminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Thread th = new Thread(task);
        th.start();
    }

    private void groupdispo() {
        Task<ArrayList<Club>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                
                ran = (ArrayList<Club>) new CrudAnnonce().grpdispo();
                //  ran = (ArrayList<Salle>) new CRUDSalle().afficherSalle();
                return ran;
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        delai.setCellValueFactory(new PropertyValueFactory<>("delai"));
        places.setCellValueFactory(new PropertyValueFactory<>("place"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        nbinsc.setCellValueFactory(new PropertyValueFactory<>("nbinscriptions"));
        nature.setCellValueFactory(new PropertyValueFactory<>("libelle"));
            
       
            table.setItems(FXCollections.observableArrayList(task.getValue()));
           

        });
        task.setOnFailed(e -> {
            try {
                Loadannonces();
            } catch (SQLException ex) {
                Logger.getLogger(AnnoncesadminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Thread th = new Thread(task);
        th.start();
    }

    private void grpnondispo() {
        Task<ArrayList<Club>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                
                ran = (ArrayList<Club>) new CrudAnnonce().grpnondispo();
                //  ran = (ArrayList<Salle>) new CRUDSalle().afficherSalle();
                return ran;
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        delai.setCellValueFactory(new PropertyValueFactory<>("delai"));
        places.setCellValueFactory(new PropertyValueFactory<>("place"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        nbinsc.setCellValueFactory(new PropertyValueFactory<>("nbinscriptions"));
        nature.setCellValueFactory(new PropertyValueFactory<>("libelle"));
            
       
            table.setItems(FXCollections.observableArrayList(task.getValue()));
           

        });
        task.setOnFailed(e -> {
            try {
                Loadannonces();
            } catch (SQLException ex) {
                Logger.getLogger(AnnoncesadminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Thread th = new Thread(task);
        th.start();
    }
    private void grparchive() {
        Task<ArrayList<Club>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                
                ran = (ArrayList<Club>) new CrudAnnonce().grparch();
                //  ran = (ArrayList<Salle>) new CRUDSalle().afficherSalle();
                return ran;
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        delai.setCellValueFactory(new PropertyValueFactory<>("delai"));
        places.setCellValueFactory(new PropertyValueFactory<>("place"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        nbinsc.setCellValueFactory(new PropertyValueFactory<>("nbinscriptions"));
        nature.setCellValueFactory(new PropertyValueFactory<>("libelle"));
            
       
            table.setItems(FXCollections.observableArrayList(task.getValue()));
           

        });
        task.setOnFailed(e -> {
            try {
                Loadannonces();
            } catch (SQLException ex) {
                Logger.getLogger(AnnoncesadminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Thread th = new Thread(task);
        th.start();
    }

    private void deconnexion(MouseEvent event) throws IOException {
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
