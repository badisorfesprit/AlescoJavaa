/*Lina
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecso.Entity.LibCat;
import alecso.Entity.Prod;
import static alecso.LibCatAllController.IdpourModifier;
import alecso.Utils.MyBDConnection;
import alecsoServices.CRUDLibCat;
import alecsoServices.CrudProduits;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LINA
 */
public class EditProdController implements Initializable {

    @FXML
    private TextField idT;
    @FXML
    private ComboBox<?> idCat;
    @FXML
    private TextField idP;
    @FXML
    private TextField idQ;
    @FXML
    private TextArea idD;
    @FXML
    private Button bntAdd;
    private MyBDConnection mycon;
    @FXML
    private Text labelUser;
    @FXML
    private Text lblT;
    @FXML
    private Text lblP;
    @FXML
    private Text lblQ;
    @FXML
    private Text lblD;
    
    private  static int Verif=0;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mycon = new MyBDConnection();
    
          CRUDLibCat crud=new CRUDLibCat();
        List<LibCat> c=new ArrayList();
        List l=new ArrayList();
        try {
            c= crud.afficherComb();
        } catch (SQLException ex) {
            Logger.getLogger(AddProdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        c.forEach((s->l.add(s.getLibelle())) );
        idCat.setItems(FXCollections.observableArrayList(l));
            modifierdata(c);
    }    

    private void modifierdata(List<LibCat> c) {
            Connection con = mycon.getConnection();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `produits` WHERE id="+MyProdController.IdpourModifier);
            while(rs.next()) {
             //  Prod c=(new Prod(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4)));
               LibCat cat;
               int j=0;
               for(int i=0;i<c.size();i++){
                   if(c.get(i).getIdC()==rs.getInt(3)){
                       cat=c.get(i);
                       j=i;
                   }
               }
               
               idT.setText(rs.getString(6)); 
               idD.setText(rs.getString(7));
                idQ.setText(Integer.toString(rs.getInt(5)));
               idP.setText(Integer.toString(rs.getInt(8)));
              idCat.getSelectionModel().select(j);
             
               
              
               
                 }
        }catch (SQLException ex) {
            Logger.getLogger(EditProdController.class.getName()).log(Level.SEVERE, null, ex);
    }}
    private int findId(List<LibCat> c){
        int id=0;
        for(int i=0;i<c.size();i++){
            if(c.get(i).getLibelle().equals(idCat.getValue())){
                id=c.get(i).getIdC();
            }
        }
        return id;
    }
    
    @FXML
    private void Editer(ActionEvent event) throws IOException, SQLException {

            
          CrudProduits service=new CrudProduits();
        Prod c = new Prod();
         CRUDLibCat crud=new CRUDLibCat();
        List<LibCat> ca=new ArrayList();
        List l=new ArrayList();
            ca= crud.afficherComb();
        c.setNom(idT.getText());
        c.setDescription(idD.getText());
       c.setPrix(Integer.valueOf(idP.getText()));
       c.setStockQty(Integer.valueOf(idQ.getText()));
        c.setCategorie(findId(ca));
    
        
        service.ModifierProd(c, MyProdController.IdpourModifier); 
    
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("MyProd.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();

    }
  
    @FXML
    private void Annuler(ActionEvent event) throws IOException {
        
             Parent root = FXMLLoader.load(getClass().getResource("MyProd.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

   @FXML
    private void controle_tit(KeyEvent event) {
        
        if(idT.getText().length()>15 || idT.getText().length()<5 ){
        lblT.setText("*min 5 max 15 caracteres");
        }
        else if (idT.getText().length()==0){
        lblT.setText("*Champs obligatoire");
        }
        else{
        lblT.setText("");
        Verif=Verif+1;
        }
    }

    

    @FXML
    private void controle_prix(KeyEvent event) {
          String u=idP.getText();
          String pattern = "^[0-9]*$";
   
    if((u.equals("")))
            {lblP.setText("*Champs obligatoire!");
           
            }
     else if(!u.matches(pattern))
      {lblP.setText("*Seulement des chiffres");
           
     }
  
     else if(Integer.valueOf(u)>100 || Integer.valueOf(u)<0) {
         lblP.setText("*min 0 max 100 dt");
           
     }
        else{ lblP.setText("");
         Verif=Verif+1;
         
        }
        
        
    }

    @FXML
    private void controle_Q(KeyEvent event) {
               String u=idQ.getText();
          String pattern = "^[0-9]*$";
   
    if((u.equals("")))
            {lblQ.setText("*Champs obligatoire!");
           
            }
     else if(!u.matches(pattern))
      {lblQ.setText("*Seulement des chiffres");
           
     }
  
     else if(Integer.valueOf(u)>10 || Integer.valueOf(u)<1) {
         lblQ.setText("*min 1 max 20");
           
     }
        else{ lblQ.setText("");
         Verif=Verif+1;
         
        }
    }

    @FXML
    private void controle_desc(KeyEvent event) {
             if(idD.getText().length()>100 || idD.getText().length()<5 ){
        lblD.setText("*min 5 max 100 caracteres");
        }
        else if (idD.getText().length()==0){
        lblD.setText("*Champs obligatoire");
        }
        else{
        lblD.setText("");
         Verif=Verif+1;
        }
        
    }
    
}
