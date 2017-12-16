package Amministratore.Controller;

import Amministratore.Amministratore;
import Client.Aquisizioni;
import Client.ElementiCreaTorneo;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControllerCreaTorneo implements Initializable{
    
    @FXML TextField nomeTorneo;
    @FXML TextArea descrizione;
    @FXML DatePicker dataInizio;
    @FXML ComboBox tipoTorneo;
    @FXML TextField maxIscrizioni;
    
    private ArrayList<String> ricevuti;
    private ObservableList<String> visualizzati;
    private Aquisizioni ritorno;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        riempiComboBox();
    }
    
    @FXML
    public void salva(ActionEvent event){
        String data, idTipoTorneo;
        System.out.println(nomeTorneo.getText());
        System.out.println(descrizione.getText());
        System.out.println(maxIscrizioni.getText());
        LocalDate ld = dataInizio.getValue();
        data = ld.toString();
        System.out.println(data);
        
        idTipoTorneo = (String) tipoTorneo.getValue();
        System.out.println("ID tipo torneo: "+idTipoTorneo);
        ritorno = Amministratore.trasmissione(new Aquisizioni(new ElementiCreaTorneo(nomeTorneo.getText(), descrizione.getText(), maxIscrizioni.getText(), data, idTipoTorneo),"creaTorneo"));
        try{    
            if(ritorno.getFeedBack()){
                new CaricaPagine().startAvvisoCreaTorneoSi();
            } else {
                new CaricaPagine().startAvvisoCreaTorneoNo();
            }
        } catch (Exception e){
            Logger.getLogger(ControllerCreaTorneo.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    @FXML
    public void annulla(ActionEvent event){
        try {
            new CaricaPagine().startVisualizzaTornei();
        } catch (Exception ex) {
            Logger.getLogger(ControllerCreaTorneo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void riempiComboBox() {
        ritorno = Amministratore.trasmissione(new Aquisizioni("riempiComboboxTipoTorneoAdmin",ControllerVisualizzaTornei.globalTorneo));
        ricevuti = ritorno.getArrayCombobox();
        visualizzati = FXCollections.observableArrayList(ricevuti);
        tipoTorneo.setItems(visualizzati);
        ricevuti.clear();
    }
    
    
}
