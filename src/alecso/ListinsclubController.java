/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import static alecso.AnnoncesadminController.Idpouraff;
import alecso.Entity.Club;
import alecso.Entity.fos_user;
import alecso.Entity.inscetudiant;
import alecso.Utils.MyBDConnection;
import alecsoServices.CrudAnnonce;
import alecsoServices.CrudUser;
import alecsoServices.inscetudiantCrud;
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
public class ListinsclubController implements Initializable {

    @FXML
    private TableView<inscetudiant> table;
    @FXML
    private TableColumn<inscetudiant, String> nom;
    @FXML
    private TableColumn<inscetudiant, Date> date;
    @FXML
    private TableColumn<inscetudiant, String> etab;
    @FXML
    private TableColumn<inscetudiant, String> adresse;
     private MyBDConnection mycon;
    @FXML
    private TabPane page;
    @FXML
    private Tab page1;
    @FXML
    private Tab page2;
    @FXML
    private StackPane test;
public static int nbinsc,place;
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
            Loadinsc();
        } catch (SQLException ex) {
            Logger.getLogger(AffcatcController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nbinsc =nbinsc();
        place = place();
        System.out.println("    id"+Idpouraff);
        System.out.println("    nbinsc "+nbinsc);
        System.out.println("    nbolace "+place);
        
    }    
    private void Loadinsc() throws SQLException {
        inscetudiantCrud sr=new inscetudiantCrud();
        ObservableList<inscetudiant> listclubs =FXCollections.observableArrayList(sr.Displayclubinsc(Idpouraff));
        
        nom.setCellValueFactory(new PropertyValueFactory<>("username"));
        date.setCellValueFactory(new PropertyValueFactory<>("datenaissance"));
        etab.setCellValueFactory(new PropertyValueFactory<>("ecole"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
       
       
        
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
    private void pageaff(Event event) {
        mycon = new MyBDConnection();
        try {
            Loadinsc();
        } catch (SQLException ex) {
            Logger.getLogger(AffcatcController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private void pagestat(Event event) {
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
                int limite= (((nbinsc)/(nbinsc+place))*100);
                if(progress>limite)
                {
                    break;
                }
            }
        }
    }
    public int nbinsc() {
        Connection mycon=MyBDConnection.getInstanceBD().getConnection();
        
        try {
            String sql2 = "SELECT nbinscriptions FROM `Club` WHERE id= "+Idpouraff;
            Statement st2 = mycon.createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);
            
            while(rs2.next())
            {
                //return 0;
                  nbinsc = rs2.getInt(1);
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
    public int place() {
        Connection mycon=MyBDConnection.getInstanceBD().getConnection();
        
        try {
            String sql2 = "SELECT place FROM `Club` WHERE id= "+Idpouraff;
            Statement st2 = mycon.createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);
            
            while(rs2.next())
            {
                //return 0;
                  place = rs2.getInt(1);
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
