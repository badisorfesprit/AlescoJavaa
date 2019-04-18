/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import static alecso.AffcatcController.Idpouraff;
//import static alecso.AnnoncesadminController.Idpouraff;
import alecso.Entity.Club;
import alecso.Entity.fos_user;
import alecso.Utils.MyBDConnection;
import alecsoServices.CrudAnnonce;
import alecsoServices.CrudUser;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nawres
 */
public class ListcatclubController implements Initializable {

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
    private MyBDConnection mycon;
    @FXML
    private TabPane page;
    @FXML
    private Tab page1;
    @FXML
    private Tab page2;
    @FXML
    private StackPane test;
    
    public static int nbtotalclub,nbtotalcat;
    @FXML
    private Text txt;
    @FXML
    private ImageView img;

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
        nbtotalcat = totalcat();
        nbtotalclub = nbtotalclub();
        System.out.println("   nb cat  "+nbtotalcat);
        System.out.println("   nb club  "+nbtotalclub);
    }    
    private void Loadannonces() throws SQLException {
        CrudAnnonce sr=new CrudAnnonce();
        ObservableList<Club> listclubs =FXCollections.observableArrayList(sr.Displayclubcat(Idpouraff));
        
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        delai.setCellValueFactory(new PropertyValueFactory<>("delai"));
        places.setCellValueFactory(new PropertyValueFactory<>("place"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        nbinsc.setCellValueFactory(new PropertyValueFactory<>("nbinscriptions"));
        nature.setCellValueFactory(new PropertyValueFactory<>("libelle"));
       
        
        table.setItems(listclubs);
        
        
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
    private void Annonces(ActionEvent event) throws IOException, SQLException {
          CrudUser cu = new CrudUser();
        fos_user user;
            
            user = cu.getconnecteduser(fos_user.getIdcnct());
            
            String[] role = user.getRoles().split("\"");
           String roleconnected = role[1];
            if (roleconnected.equals("ROLE_ADMIN"))
                   {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("annoncespartenire.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
                   }
           else
           {
               Parent tableViewParent = FXMLLoader.load(getClass().getResource("annoncesadmin.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show(); 
           
           }
    }

    @FXML
    private void affichage(Event event) {
    }

    @FXML
    private void stat(Event event) {
        RingProgressIndicator ringProgressIndicator = new RingProgressIndicator();
        ringProgressIndicator.setRingWidth(200);
        ringProgressIndicator.makeIndeterminate();
        //page2.add(ringProgressIndicator);
        test.getChildren().add(ringProgressIndicator);
        new WorkerThread(ringProgressIndicator).start();
        
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
     class WorkerThread extends Thread
    {
        RingProgressIndicator rpi;
        int progress =0;
        public WorkerThread(RingProgressIndicator rpi)
        {this.rpi=rpi;}
        public void run()
        {
            while(true)
            {
                try {
                    Thread.sleep(100);
                    
                } catch(InterruptedException ex){
                    Logger.getLogger(AffcatcController.class.getName()).log(Level.SEVERE, null, ex);
                    
                }
                Platform.runLater(()-> {
                rpi.setProgress(progress);});
                progress+=1;
                int limite= (((nbtotalcat)/(nbtotalclub))*100);
                if(progress>limite)
                {
                    break;
                }
            }
        }
    }
    public int nbtotalclub() {
        Connection mycon=MyBDConnection.getInstanceBD().getConnection();
        
        try {
            String sql2 = "SELECT count(*) FROM `Club`  ";
            Statement st2 = mycon.createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);
            
            while(rs2.next())
            {
                //return 0;
                  nbtotalclub = rs2.getInt(1);
                return rs2.getInt(1);            
                //System.out.println(rs2.getInt(1)); 
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return -1;
        
        
    }
    public int totalcat() {
        Connection mycon=MyBDConnection.getInstanceBD().getConnection();
        
        try {
            String sql2 = "SELECT count(*) FROM club cl INNER JOIN categorie_c l ON l.id= "+Idpouraff;
            Statement st2 = mycon.createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);
            
            while(rs2.next())
            {
                //return 0;
                  nbtotalcat = rs2.getInt(1);
                return rs2.getInt(1);            
                //System.out.println(rs2.getInt(1)); 
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return -1;
        
        
    }
    
}
