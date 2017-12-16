package Amministratore.Controller;

import Amministratore.Amministratore;
import Client.ElementiTabellaPrenotazioni;
import Client.Aquisizioni;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ControllerPrenotazione implements Initializable{

    @FXML Label LCalcio5 = new Label();    
    @FXML Label LCalcio7 = new Label();    
    @FXML Label LCalcio11 = new Label();
    @FXML DatePicker vis_data = new DatePicker();
    @FXML TableView<ElementiTabellaPrenotazioni> tabella_prenotazioni = new TableView<ElementiTabellaPrenotazioni>();    
    @FXML TableColumn fasciaOraria = new TableColumn("fasciaOraria");    
    @FXML TableColumn stato = new TableColumn("stato");    
    @FXML TableColumn prenotatoDa = new TableColumn("prenotatoDa");    
    @FXML TableColumn tipoPartita = new TableColumn("tipoPartita");
    
    ArrayList<ElementiTabellaPrenotazioni> ricevuti;
    ObservableList<ElementiTabellaPrenotazioni> visualizzati;
    
    public static String globalData;
    public static String globalFascia;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        int var = ControllerSceltaCampo.var;
            if(var == 1){
            LCalcio5.setVisible(true);
            LCalcio7.setVisible(false);
            LCalcio11.setVisible(false);
        } else if(var == 2){
            LCalcio5.setVisible(false);
            LCalcio7.setVisible(true);
            LCalcio11.setVisible(false);
        } else if(var == 3){
            LCalcio5.setVisible(false);
            LCalcio7.setVisible(false);
            LCalcio11.setVisible(true);
        }
    }
    
    @FXML
    public void tornaHome(MouseEvent event){
        
        try {
            new CaricaPagine().startHome();
        } catch (Exception ex) {
            Logger.getLogger(ControllerPrenotazione.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Questo metodo viene attivato quando dal datapicker l'utente sceglie una
     * data per visualizzare le fascie orarie occupate in un determinato campo
     * nel giorno scelto.
     */
    @FXML
    public void visData(ActionEvent event){
        
        //prendo la data
        LocalDate ld = vis_data.getValue();
        globalData = ld.toString();
        Aquisizioni aqui = Amministratore.trasmissione(new Aquisizioni(globalData,"tabellaPrenotazioni",ControllerSceltaCampo.var));
        //ricevuti.add(aqui);
        ricevuti = aqui.getRighe();
        
        fasciaOraria.setEditable(true);
        stato.setEditable(true);
        prenotatoDa.setEditable(true);
        tipoPartita.setEditable(true);
        
        visualizzati = FXCollections.observableArrayList(ricevuti);
        
        fasciaOraria.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("fasciaOraria"));
        stato.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("stato"));
        prenotatoDa.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("prenotatoDa"));
        tipoPartita.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("tipoPartita"));
        
        tabella_prenotazioni.setItems(visualizzati);
        ricevuti.clear();
    }
    
    
    
    /**
     * Questo metodo si attiva quando si clicca su una fascia oraria che si vuole
     * occupare, questo metodo una volta scelta la fascia desiderata attiva un
     * avviso di conferma prenotazione
     */
    public void confermaPrenotazioni(){
        try {
            TablePosition pos = tabella_prenotazioni.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
            ElementiTabellaPrenotazioni item = tabella_prenotazioni.getItems().get(row);
            TableColumn col = pos.getTableColumn();
            globalFascia = (String) col.getCellObservableValue(item).getValue();
            System.out.println("fascia = "+globalFascia);
            new CaricaPagine().startSceltaAzioneFasciaOraria();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
