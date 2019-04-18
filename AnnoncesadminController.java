/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.CategorieC;
import alecso.Entity.Club;
import alecso.Entity.fos_user;
import alecso.Utils.MyBDConnection;
import alecsoServices.CrudAnnonce;
import alecsoServices.CrudCatC;
import alecsoServices.CrudUser;
import alecsoServices.inscetudiantCrud;
import com.teknikindustries.bulksms.SMS;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Nawres
 */
public class AnnoncesadminController implements Initializable {

    @FXML
    private Button gotocat;
    @FXML
    private Button gotoannonces;
    private MyBDConnection mycon;
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
        
        try {
            pro();
        } catch (MessagingException ex) {
            Logger.getLogger(AnnoncesadminController.class.getName()).log(Level.SEVERE, null, ex);
        }
         notif();
        
        
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
private void Loadannoncespartenaire() throws SQLException {
        CrudAnnonce sr=new CrudAnnonce();
        int id = fos_user.getIdcnct();
        //String role = fos_user.namecnt
       
           //CrudUser cu = new CrudUser();
           //String role = cu.role(id);
      
           //System.out.println("connected role:"+role);
        ObservableList<Club> listclubs =FXCollections.observableArrayList(sr.Displayadmin());
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        delai.setCellValueFactory(new PropertyValueFactory<>("delai"));
        places.setCellValueFactory(new PropertyValueFactory<>("place"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        nbinsc.setCellValueFactory(new PropertyValueFactory<>("nbinscriptions"));
        nature.setCellValueFactory(new PropertyValueFactory<>("libelle"));
       
        
        table.setItems(listclubs);
        
        
    }

    @FXML
    private void gotocat(ActionEvent event) {
    }

    @FXML
    private void gotoannonces(ActionEvent event) {
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
    public void notif()
        {
            
        
        inscetudiantCrud i = new inscetudiantCrud();
        if(i.Notif()!=0 )
        {
        Notifications notificationBuilder=Notifications.create()
                .title("Notification")
                .text("vous avez "+i.Notif()+" inscriptions aujourd'hui Veuillez consulter la liste des inscriptions")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_CENTER);
        
        notificationBuilder.darkStyle();
        notificationBuilder.showWarning();
        
        }else{
        
        Notifications notificationBuilder1=Notifications.create()
                .title("Notification")
                .text("vous n'avez aucune inscription aujourd'hui")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_CENTER);
        
        notificationBuilder1.darkStyle();
        notificationBuilder1.showWarning();
        }
        }
    
    
    
    
    
    
    ////METIER*********
    private void pro() throws AddressException, MessagingException  {
        
        Connection con=MyBDConnection.getInstanceBD().getConnection();
        int nb = nbplaces();
        System.out.println(nb);
      int idprolo= prolo();
      int prolon= valeurprolo();
        System.out.println("id l prolongation"+idprolo);
        System.out.println("prolongation "+ prolon);
        System.out.println("nombre places"+nb);
        
        
            if( nb != -1 && prolon !=  -1 && prolon !=  -2)
        
        { //if(prolo()!=-1 && nb!=1 )        
            System.out.println("    update ye hawet ");
               String requete = "UPDATE `club` SET `delai` = delai + '"+prolon+"' WHERE `id`='"+ idprolo +"'"  ;
               //"UPDATE `club` SET `delai`= delai + '"+ c.getProlongation()+" where id= '"+idprolo
               //String requete = "UPDATE `club` SET `delai`=delai + ? "+ c.getProlongation() ;
              
            try{               
           PreparedStatement statement = con.prepareStatement(requete);
           statement.executeUpdate();
                System.out.println("    Done");
            }
            catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } }
            else if( nb != -1 && prolon == -1 && prolon !=  -2) {
            int sup= supp();
            if(sup != 0)
            {
                System.out.println("    supression ye hawet ");
                int tel = tel();
                String mail= email();
                System.out.println(tel);
                System.out.println(mail);
                   String host = "smtp.gmail.com";
            String user = "alecsoed@gmail.com";
            String pass = "Alecso123";
            String to = (mail);
            String from = "alecsoed@gmail.com";
            String subject = "nous sommes desole de vous informer que votre inscription a ete annuler ";
            String messageText = "Annulation D'inscription :";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new java.util.Date());
            msg.setText(messageText);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("message send successfully");
            
           /* SMS smstut=new SMS();
        
        String message=" Annulation d'inscription: Bonjour, nous sommes desolee de vus informer que votre inscription a ete annule   "; 
        
        smstut.SendSMS("louloutest", "Alecso123", message ,"+21694892706", "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");*/
        
            }
            else{System.out.println("   rien a signaler");}
            }          
              
    
         
    }
    
    public int prolo() {
        Connection mycon=MyBDConnection.getInstanceBD().getConnection();
        
        try {
            String sql2 = "SELECT count(*),id FROM `Club` WHERE delai - CURRENT_DATE <3 ";
            Statement st2 = mycon.createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);
            
            while(rs2.next())
            {
                //return 0;
                  idprolo = rs2.getInt(2);
                return rs2.getInt(2);            
                //System.out.println(rs2.getInt(1)); 
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return -1;
        
        
    }
     public int supp() {
        Connection con=MyBDConnection.getInstanceBD().getConnection();
        
       try {
            String sql3 = "select count(*),f.email,f.tel from fos_user f INNER JOIN inscenfant e ON f.id=e.user_id and e.club_id="+idprolo;
            Statement st3 = con.createStatement();
            ResultSet rs3 = st3.executeQuery(sql3);
            //String email;
            while(rs3.next())
            {
                //return 0;
                //int insc = rs2.getInt(2);
                 // int p = rs2.getInt(2);
                int sup = rs3.getInt(1);
                 
                return rs3.getInt(1);
                  
                
            }
                
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return -1;
        
        
    }
      public String email() {
        Connection con=MyBDConnection.getInstanceBD().getConnection();
        
       try {
            String sql3 = "select f.email from fos_user f INNER JOIN inscenfant e ON f.id=e.user_id and e.club_id="+idprolo;
            Statement st3 = con.createStatement();
            ResultSet rs3 = st3.executeQuery(sql3);
            //String email;
            while(rs3.next())
            {
                //return 0;
                //int insc = rs2.getInt(2);
                 // int p = rs2.getInt(2);
                //int sup = rs3.getInt(1);
                 String email = rs3.getString(1);
            
                return rs3.getString(1);
                  
                
            }
                
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
       return null;

        
        
        
    }
      public int tel() {
        Connection con=MyBDConnection.getInstanceBD().getConnection();
        
       try {
            String sql3 = "select f.tel from fos_user f INNER JOIN inscenfant e ON f.id=e.user_id and e.club_id="+idprolo;
            Statement st3 = con.createStatement();
            ResultSet rs3 = st3.executeQuery(sql3);
            //String email;
            while(rs3.next())
            {
                //return 0;
                //int insc = rs2.getInt(2);
                 // int p = rs2.getInt(2);
              
                int telephone = rs3.getInt(1);
               return rs3.getInt(1);
               //return rs3.getInt(1);
                  
                
            }
                
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
       return -1;
     

        
        
        
    }
    public int nbplaces() {
        Connection mycon=MyBDConnection.getInstanceBD().getConnection();
        int idprolo= prolo();
     // int prolon= valeurprolo();
        try {
            String sql2 = "SELECT nbinscriptions,place from club where id= "+idprolo;
            Statement st2 = mycon.createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);
            
            while(rs2.next())
            {
                //return 0;
                int insc = rs2.getInt(1);
                  int p = rs2.getInt(2);
                  if(((insc + p)*0.25)>=insc)
                  {return 1;} 
                  
                //System.out.println(rs2.getInt(1)); 
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return -1;
        
        
    }
    public int valeurprolo() {
        
        
        int prolon;
        
            //String sql22 = ("SELECT count(*),prolongation FROM `Club` WHERE  id = "+idprolo);
            Connection con=MyBDConnection.getInstanceBD().getConnection();
        try {
            String sql2 = "SELECT count(*),prolongation FROM `Club` WHERE  id = " + idprolo ;
            Statement st2 = con.createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);
            
            while(rs2.next())
            {
                //return 0;
                 prolon = rs2.getInt(2);
                return rs2.getInt(2);            
                //System.out.println(rs2.getInt(1)); 
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return -2;
        
        
    }
   
    
    
}
