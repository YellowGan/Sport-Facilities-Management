package Client.Home;

import Client.Aquisizioni;
import Client.Client;
import Client.Home.Controller;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 * Classe che controlla la pagina che permette di iscrivere una squadra a un
 * determinato torneo
 */
public class ControllerIscrizioneTorneo implements Initializable{
    
    public static String globalSquadra;
    public static String globalTorneo;
    
    @FXML ComboBox tornei;
    @FXML ComboBox squadra;
    ObservableList<String> elementiVisualizzati;
    ArrayList<String> elementiRicevuti;
    
    /**
     * Metodo che attiva l'avviso di conferma iscrizione della squadra al torneo
     * quando si preme il pulsante iscrivi
     */
    @FXML
    public void iscrivi(ActionEvent event){
        System.out.println("Ho premuto iscrivi in iscrivi squadra a torneo");
        globalSquadra = (String) squadra.getValue();
        globalTorneo = (String) tornei.getValue();
         try {
            new CaricaPagine().startAvvisoIscrizione();
        } catch (Exception ex) {
            Logger.getLogger(ControllerAvvisoIscrizione.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo che permette di tornare alla home quando si preme il pulsante annulla
     */
    @FXML
    public void annulla(ActionEvent event) throws Exception{
        System.out.println("ho premuto annulla in iscrivi squadra a torneo");
        new CaricaPagine().startHome();
    }
    
    /**
     * Metodo che inizializza le combobox della pagina
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        riempiCombobox("riempiComboboxTornei");
        riempiCombobox("riempiComboboxSquadra");
    }
    
    /**
     * Metodo che riempe le combobox dei tornei o della squadra a seconda
     * del paramtro che riceve dal metodo initialize
     */
    public void riempiCombobox(String operazione){
        Aquisizioni aqui = Client.trasmissione(new Aquisizioni(operazione, Controller.globalID));
        elementiRicevuti = aqui.getArrayCombobox();
        elementiVisualizzati = FXCollections.observableArrayList(elementiRicevuti);
        if(operazione.equals("riempiComboboxTornei"))
            tornei.setItems(elementiVisualizzati);
        else 
            squadra.setItems(elementiVisualizzati);
    }   
}
