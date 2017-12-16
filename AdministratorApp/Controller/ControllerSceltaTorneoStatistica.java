package Amministratore.Controller;

import Amministratore.Amministratore;
import Client.Aquisizioni;
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
import javafx.scene.control.ComboBox;

public class ControllerSceltaTorneoStatistica implements Initializable{
    
    @FXML ComboBox tornei = new ComboBox();
    
    private ArrayList<String> ricevuti;
    private ObservableList<String> visualizzati;
    public static String globalNomeTorneoStatistica;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        riempiCombobox();
    }
    
    @FXML
    public void avanti(ActionEvent event){
        globalNomeTorneoStatistica = (String) tornei.getValue();
        try {
            new CaricaPagine().startVisualizzaCalendarioTorneo();
        } catch (Exception ex) {
            Logger.getLogger(ControllerSceltaTorneoStatistica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void indietro(ActionEvent event){
        try {
            new CaricaPagine().startHome();
        } catch (Exception ex) {
            Logger.getLogger(ControllerSceltaTorneoStatistica.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public void riempiCombobox(){
        Aquisizioni aqui = Amministratore.trasmissione(new Aquisizioni("riempiComboboxTornei","non ci deve essere niente"));
        ricevuti = aqui.getArrayCombobox();
        visualizzati = FXCollections.observableArrayList(ricevuti);
        tornei.setItems(visualizzati);
        ricevuti.clear();
    }
}
