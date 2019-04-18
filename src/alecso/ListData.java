/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alecso;

import alecsoServices.CovoiturageDao;
import alecso.Entity.Covoiturage;
import alecsoServices.EtudiantDao;
import alecso.Entity.Etudiant;
import alecso.Entity.fos_user;
import alecsoServices.ProfDao;
import alecso.Entity.prof;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;



/**
 *
 * @author wiemhjiri
 */
public class ListData {
    
     /**
     * The data as an observable list of Persons.
     */
    
    private ObservableList<Covoiturage> covoiturages=FXCollections.observableArrayList();
    private ObservableList<Etudiant> etudiants=FXCollections.observableArrayList();
    private ObservableList<prof> prof=FXCollections.observableArrayList();
    
    
    
    private ObservableList<Etudiant> annoncesEt=FXCollections.observableArrayList();
     private ObservableList<Covoiturage> annoncesC=FXCollections.observableArrayList();
     private ObservableList<prof> annoncesP=FXCollections.observableArrayList();
  
    

    public ListData() {
        
        CovoiturageDao pdao=CovoiturageDao.getInstance();
       covoiturages= (ObservableList<Covoiturage>) pdao.displayAll();
       EtudiantDao Edao=EtudiantDao.getInstance();
       etudiants= (ObservableList<Etudiant>) Edao.displayAll();
       ProfDao Cdao=ProfDao.getInstance();
       prof= (ObservableList<prof>) Cdao.displayAll();
              
       annoncesEt= (ObservableList<Etudiant>) Edao.displayAllById(fos_user.getIdcnct());
          
       annoncesC= (ObservableList<Covoiturage>) pdao.displayAllById(fos_user.getIdcnct());
       
       annoncesP= (ObservableList<prof>) Cdao.displayAllById(fos_user.getIdcnct());
       
    }

    public ObservableList<Covoiturage> getCovoiturages() {
        return covoiturages;
    }

   
    public ObservableList<Etudiant> getEtudiants() {
        return etudiants;
    }
    public ObservableList<Etudiant> getAnnEtudiants() {
        return annoncesEt;
    }
    
      public ObservableList<prof> getProf() {
        return prof;
    }
      
      
      public ObservableList<Covoiturage> getAnnCovoiturage() {
        return annoncesC;
    }
      
       public ObservableList<prof> getAnnProf() {
        return annoncesP;
    }
    
    
   
}
