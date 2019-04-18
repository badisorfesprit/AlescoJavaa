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
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.controlsfx.control.Notifications;




/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class AdminBackEventController implements Initializable {
    private ObservableList<Evenement> data;
     
 @FXML
    private TableView<Evenement> Event_tab;
    @FXML
    private TableColumn<Evenement, Integer> id_COL;
    @FXML
    private TableColumn<Evenement, String> titre_COL;
    @FXML
    private TableColumn<Evenement, String> lieu_COL;
    @FXML
    private TableColumn<Evenement, String> description_COL;
    @FXML
    private TableColumn<Evenement, Date> date_event_COL;
    @FXML
    private TableColumn<Evenement, Integer> nbplace_COL;
    @FXML
    private TableColumn<Evenement, String> nom_image_COL;
@FXML
    private TableColumn<Evenement, Integer> categorie_COL;
 public ArrayList<Evenement> ran;
   @FXML
    private Button supprimer;
    

      @FXML
    private TextField rechercheCol;
      

    private ImageView UploadImage;
     private String imageUpload;

 @FXML
    private ComboBox<String> trier;
    
    ObservableList<String> TriList=FXCollections.observableArrayList("Titre","NBplace","Date");
    private ComboBox idCat;
    
    @FXML
    private Button ajouteer;
    @FXML
    private Button modif;
    @FXML
    private Button eventaujourdhui;
    @FXML
    private Button excelbtn;
   
 
 
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         EvenementDB  crud = new  EvenementDB  ();
         Evenement e  ;
          data = FXCollections.observableArrayList();
        
       trier.setItems(TriList);
   
        Search();
          
          id_COL.setCellValueFactory(new PropertyValueFactory<>("id")); //from entity
     titre_COL.setCellValueFactory(new PropertyValueFactory<>("titre"));
    lieu_COL.setCellValueFactory(new PropertyValueFactory<>("lieu"));
   description_COL.setCellValueFactory(new PropertyValueFactory<>("description")); //from entity
   date_event_COL.setCellValueFactory(new PropertyValueFactory<>("date_event"));
  nbplace_COL.setCellValueFactory(new PropertyValueFactory<>("nbplace"));
  nom_image_COL.setCellValueFactory(new PropertyValueFactory<>("nom_image"));

  categorie_COL.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
  
  Event_tab.setItems(crud.afficherEvent());
  
   
  notif();

    }

   

    @FXML
    private void supprimer_evenement(ActionEvent event) {
        
         EvenementDB  cs = new EvenementDB ();
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
  cs.delete_Event(Event_tab.getSelectionModel().getSelectedItem());
    
        
      Event_tab.setItems(cs.afficherEvent()); //Affichage après suppression
   
    }

   
      public void Search(){
        EvenementDB bs = new   EvenementDB();
    
        rechercheCol.setOnKeyReleased(e->{
            if(rechercheCol.getText().equals("")){
                bs.afficherEvent();
            }
            else {
                data.clear();
                data=bs.Search(rechercheCol.getText());
                 Event_tab.setItems(data);
            }
        });
        
          
    }

   


    
     @FXML
    private void TrierButton(ActionEvent event) {
        if(trier.getSelectionModel().getSelectedItem().equals("Titre"))
        {
            TrierTitre();
        }
        else if (trier.getSelectionModel().getSelectedItem().equals("NBplace"))
        {
            TriernbPlace();
        }
         else if (trier.getSelectionModel().getSelectedItem().equals("Date"))
        {
            TrierDate();
        }
  }
     public void TrierTitre()
    {
    Task<ArrayList<Evenement>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                
                ran = (ArrayList<Evenement>) new EvenementDB().TrierTitre();
                //  ran = (ArrayList<Salle>) new CRUDSalle().afficherSalle();
                return ran;
            }
        };
         task.setOnSucceeded((WorkerStateEvent e) -> {
           id_COL.setCellValueFactory(new PropertyValueFactory<>("id")); //from entity
     titre_COL.setCellValueFactory(new PropertyValueFactory<>("titre"));
    lieu_COL.setCellValueFactory(new PropertyValueFactory<>("lieu"));
   description_COL.setCellValueFactory(new PropertyValueFactory<>("description")); //from entity
   date_event_COL.setCellValueFactory(new PropertyValueFactory<>("date_event"));
  nbplace_COL.setCellValueFactory(new PropertyValueFactory<>("nbplace"));
  nom_image_COL.setCellValueFactory(new PropertyValueFactory<>("nom_image"));

  categorie_COL.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
  
            
            

           
 
            // pourcentage.setCellValueFactory(new PropertyValueFactory<>("pourcentage"));
            //  table.setItems(FXCollections.observableArrayList(task.getValue()));
             Event_tab.setItems(FXCollections.observableArrayList(task.getValue()));
            //ObservableList<Salle> s=FXCollections.observableArrayList();

       });
        task.setOnFailed(e -> {
               EvenementDB  crud = new  EvenementDB  ();
             Event_tab.setItems(crud.afficherEvent());
        });
        Thread th = new Thread(task);
        th.start(); 
    }  
      public void TriernbPlace()
    {
    Task<ArrayList<Evenement>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                
                ran = (ArrayList<Evenement>) new EvenementDB().TriernbPlace();
                //  ran = (ArrayList<Salle>) new CRUDSalle().afficherSalle();
                return ran;
            }
        };
         task.setOnSucceeded((WorkerStateEvent e) -> {
           id_COL.setCellValueFactory(new PropertyValueFactory<>("id")); //from entity
     titre_COL.setCellValueFactory(new PropertyValueFactory<>("titre"));
    lieu_COL.setCellValueFactory(new PropertyValueFactory<>("lieu"));
   description_COL.setCellValueFactory(new PropertyValueFactory<>("description")); //from entity
   date_event_COL.setCellValueFactory(new PropertyValueFactory<>("date_event"));
  nbplace_COL.setCellValueFactory(new PropertyValueFactory<>("nbplace"));
  nom_image_COL.setCellValueFactory(new PropertyValueFactory<>("nom_image"));

  categorie_COL.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
  
            
            

           
 
            // pourcentage.setCellValueFactory(new PropertyValueFactory<>("pourcentage"));
            //  table.setItems(FXCollections.observableArrayList(task.getValue()));
             Event_tab.setItems(FXCollections.observableArrayList(task.getValue()));
            //ObservableList<Salle> s=FXCollections.observableArrayList();

       });
        task.setOnFailed(e -> {
               EvenementDB  crud = new  EvenementDB  ();
             Event_tab.setItems(crud.afficherEvent());
        });
        Thread th = new Thread(task);
        th.start(); 
    }  
      
      
      
      
       public void TrierDate()
    {
    Task<ArrayList<Evenement>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                
                ran = (ArrayList<Evenement>) new EvenementDB().TrierDate();
                //  ran = (ArrayList<Salle>) new CRUDSalle().afficherSalle();
                return ran;
            }
        };
         task.setOnSucceeded((WorkerStateEvent e) -> {
           id_COL.setCellValueFactory(new PropertyValueFactory<>("id")); //from entity
     titre_COL.setCellValueFactory(new PropertyValueFactory<>("titre"));
    lieu_COL.setCellValueFactory(new PropertyValueFactory<>("lieu"));
   description_COL.setCellValueFactory(new PropertyValueFactory<>("description")); //from entity
   date_event_COL.setCellValueFactory(new PropertyValueFactory<>("date_event"));
  nbplace_COL.setCellValueFactory(new PropertyValueFactory<>("nbplace"));
  nom_image_COL.setCellValueFactory(new PropertyValueFactory<>("nom_image"));

  categorie_COL.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
  
            
            

           
 
            // pourcentage.setCellValueFactory(new PropertyValueFactory<>("pourcentage"));
            //  table.setItems(FXCollections.observableArrayList(task.getValue()));
             Event_tab.setItems(FXCollections.observableArrayList(task.getValue()));
            //ObservableList<Salle> s=FXCollections.observableArrayList();

       });
        task.setOnFailed(e -> {
               EvenementDB  crud = new  EvenementDB  ();
             Event_tab.setItems(crud.afficherEvent());
        });
        Thread th = new Thread(task);
        th.start(); 
    }  
        private int findId(List<categorie> c){
        int id=0;
        for(int i=0;i<c.size();i++){
            if(c.get(i).getLibelle().equals(idCat.getValue())){
                id=c.get(i).getId();
            }
        }
        return id;
    }
/*
    @FXML
    private void ajoutbtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ajoutEvent.fxml"));
                
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
     */  

    @FXML
    private void ajoutbtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ajoutEvent.fxml"));
                
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void modifbtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("modifierEvent.fxml"));
                
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
      
       
     public void notif()
        {
            
        
        EvenementDB es = new EvenementDB();
        if(es.Notif()!=-1)
        {
        Notifications notificationBuilder=Notifications.create()
                .title("Notification")
                .text("vous avez "+es.Notif()+" evenements aujourd'hui Veuillez consulter la liste des evenements")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_CENTER);
        
        notificationBuilder.darkStyle();
        notificationBuilder.showWarning();
        
        }else{
        
        Notifications notificationBuilder1=Notifications.create()
                .title("Notification")
                .text("vous n'avez aucun evenement aujourd'hui")
                .graphic(null)
                .hideAfter(Duration.seconds(10))
                .position(Pos.TOP_CENTER);
        
        notificationBuilder1.darkStyle();
        notificationBuilder1.showWarning();
        }
        }
     
    

    @FXML
    private void evntbtn(ActionEvent event) {
         Task<ArrayList<Evenement>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                
           
                ran = (ArrayList<Evenement>) new EvenementDB().afficherEvenementsAjourdhui();
                //  ran = (ArrayList<Salle>) new CRUDSalle().afficherSalle();
                return ran;
            }
        };
       task.setOnSucceeded((WorkerStateEvent e) -> {
           id_COL.setCellValueFactory(new PropertyValueFactory<>("id")); //from entity
     titre_COL.setCellValueFactory(new PropertyValueFactory<>("titre"));
    lieu_COL.setCellValueFactory(new PropertyValueFactory<>("lieu"));
   description_COL.setCellValueFactory(new PropertyValueFactory<>("description")); //from entity
   date_event_COL.setCellValueFactory(new PropertyValueFactory<>("date_event"));
  nbplace_COL.setCellValueFactory(new PropertyValueFactory<>("nbplace"));
  nom_image_COL.setCellValueFactory(new PropertyValueFactory<>("nom_image"));

 // nature.setCellValueFactory(new PropertyValueFactory<>("libelle"));
  
            
            

           
 
            // pourcentage.setCellValueFactory(new PropertyValueFactory<>("pourcentage"));
            //  table.setItems(FXCollections.observableArrayList(task.getValue()));
             Event_tab.setItems(FXCollections.observableArrayList(task.getValue()));
            //ObservableList<Salle> s=FXCollections.observableArrayList();

       });
        task.setOnFailed(e -> {
               EvenementDB  crud = new  EvenementDB  ();
             Event_tab.setItems(crud.afficherEvent());
        });
        Thread th = new Thread(task);
        th.start();  
        
       // notif();
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
    void PDF_open(ActionEvent event)throws FileNotFoundException, DocumentException {
Evenement E=   Event_tab.getSelectionModel().getSelectedItem();
            int CurrentSelected = E.getId();
           
       System.out.println("CurrentSelected "+CurrentSelected+"  E.getId()) "+ E.getId());
             
        Document document = new Document() ;
          
          

    
    
        PdfWriter.getInstance(document, new FileOutputStream(new File ("C:\\Users\\Chaima\\Desktop\\pialecso3a23-master\\test1.pdf")));
        document.open();
      EvenementDB  crud = new  EvenementDB  ();
        document.add(new Paragraph(crud.afficher_contrat_id(CurrentSelected)));
        document.close();
    }
    
    
    
     @FXML
    void esporter_table(ActionEvent event) throws FileNotFoundException, IOException{
Workbook workbook = new HSSFWorkbook();
            Sheet spreadsheet = workbook.createSheet("sample");
            org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);
            for (int j = 0; j < Event_tab.getColumns().size(); j++) {
                row.createCell(j).setCellValue(Event_tab.getColumns().get(j).getText());
            }
            for (int i = 0; i < Event_tab.getItems().size(); i++) {
                row = spreadsheet.createRow(i + 1);
                for (int j = 0; j <Event_tab.getColumns().size(); j++) {
                    if(Event_tab.getColumns().get(j).getCellData(i) != null) {
                        row.createCell(j).setCellValue(Event_tab.getColumns().get(j).getCellData(i).toString());
                    }
                    else {
                        row.createCell(j).setCellValue("");
                    }
                }
            }
            FileOutputStream fileOut = new FileOutputStream("validations.xls");
            workbook.write(fileOut);
            fileOut.close();
            System.out.println("Data is wrtten Successfully");
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
    private void retour(ActionEvent event) throws   IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LibCatAll.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
    
}