package Amministratore.Controller;

import Amministratore.Amministratore;
import Client.Aquisizioni;
import Client.ElementiAggiungiPartitaAdmin;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class ControllerCreaPartita implements Initializable{
    
    @FXML ComboBox squadraCasa;
    @FXML ComboBox squadraTrasferta;
    @FXML ComboBox fasciaOraria;
    @FXML ComboBox campo;
    @FXML DatePicker data = new DatePicker();
    
    private Aquisizioni ritorno;
    private ArrayList<String> elementiRicevuti;
    private ObservableList<String> elementiVisualizzati;
    private int idCampo;
    private String globalData;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        riempiCombobox("riempiComboboxSquadreAdmin");
        riempiCombobox("riempiComboboxFascieOrarie");
        riempiCombobox("riempiComboboxCampi");
        
        
    }
    
    @FXML
    public void salva(ActionEvent event){
        LocalDate ld = data.getValue();
        globalData = ld.toString();
        if(((String) campo.getValue()).equals("Calcio a 5")) idCampo = 1;
        else if(((String) campo.getValue()).equals("Calcio a 7")) idCampo = 2;
        else if(((String) campo.getValue()).equals("Calcio a 11")) idCampo = 3;
        else idCampo = 0;
        ritorno = Amministratore.trasmissione(new Aquisizioni(new ElementiAggiungiPartitaAdmin((String) squadraCasa.getValue(),(String) squadraTrasferta.getValue(),(String) fasciaOraria.getValue(),globalData,ControllerVisualizzaTornei.globalTorneo,idCampo,ControllerLogin.GlobalID),"aggiungiPartitaAdmin"));
        if(ritorno.getFeedBack()){
            try {
                new CaricaPagine().startAvvisoCreaPartitaSi();
            } catch (Exception ex) {
                Logger.getLogger(ControllerCreaPartita.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else 
            System.out.println("Errore");
            
    }
    
    @FXML
    public void annulla(ActionEvent event){
        try {
            new CaricaPagine().startVisualizzaTornei();
        } catch (Exception ex) {
            Logger.getLogger(ControllerCreaPartita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void riempiCombobox(String operazione){
        ritorno = Amministratore.trasmissione(new Aquisizioni(operazione,ControllerVisualizzaTornei.globalTorneo));
        
        elementiRicevuti = ritorno.getArrayCombobox();
        elementiVisualizzati = FXCollections.observableArrayList(elementiRicevuti);
        if(operazione.equals("riempiComboboxFascieOrarie"))
            fasciaOraria.setItems(elementiVisualizzati); 
        else if(operazione.equals("riempiComboboxSquadreAdmin")){
            squadraCasa.setItems(elementiVisualizzati);
            squadraTrasferta.setItems(elementiVisualizzati);
        }
        else if(operazione.equals("riempiComboboxCampi"))
            campo.setItems(elementiVisualizzati);
        
        elementiRicevuti.clear();
    }
    
    
    
}
