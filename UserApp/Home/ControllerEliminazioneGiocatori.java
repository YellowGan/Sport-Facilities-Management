package Client.Home;

import Client.Aquisizioni;
import Client.Client;
import Client.ElementiAggiornaNotifiche;
import Client.ElementiRichiesta;
import Client.ElementiTabellaComponentiSquadra;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Questa classe controlla la pagina che si occupa delle eliminazioni 
 * dei giocatori all'unterno della squadra
 */
public class ControllerEliminazioneGiocatori implements Initializable{

    @FXML TableView<ElementiTabellaComponentiSquadra> tabSquadra = new TableView<ElementiTabellaComponentiSquadra>();
    @FXML TableColumn giocatoriPresenti = new TableColumn();
    @FXML Label info1 = new Label();
    @FXML Label confermato = new Label();
    @FXML Button si = new Button();
    @FXML Button no = new Button();
    @FXML Button ok = new Button();
    
    ArrayList<ElementiTabellaComponentiSquadra> ricevuti;
    ObservableList<ElementiTabellaComponentiSquadra> visualizzati;
    private static String giocatore;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        visualizzaGiocatori();
    }
    
    /**
     * Questo metodo che si attiva cliccando torna ti permette di tornare all'
     * interfaccia di creazione della squadra
     */
    @FXML
    public void torna(ActionEvent event){
        try {
            new CaricaPagine().startCreaSquadra();
        } catch (Exception ex) {
            Logger.getLogger(ControllerEliminazioneGiocatori.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo che ti permette di selezionare dalla tabella il giocatore che si 
     * desidera elminiare dalla squadra
     */
    @FXML
    public void eliminazione(){
        String split[];
        TablePosition pos = tabSquadra.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        ElementiTabellaComponentiSquadra item = tabSquadra.getItems().get(row);
        TableColumn col = pos.getTableColumn();
        this.giocatore = (String) col.getCellObservableValue(item).getValue();
        split = this.giocatore.split(" ");
        this.giocatore = split[0];
        
        try {
            new CaricaPagine().startAvvisoConfermaEliminazioneGiocatore();
        } catch (Exception ex) {
            Logger.getLogger(ControllerEliminazioneGiocatori.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo che si attiva quando si preme il pulsante si nella finestra di avviso 
     * e che manda la richiesta al server per eliminare il giocatore selezionato
     */
    @FXML
    public void si(ActionEvent event){
        
        String idUtente;
        Aquisizioni ritorno = Client.trasmissione(new Aquisizioni(new ElementiRichiesta(this.giocatore, ControllerCreaSquadra.idSquadra),"eliminaGiocatore"));
        idUtente = ritorno.getGlobalID();
        si.setVisible(false);
        no.setVisible(false);
        info1.setVisible(false);
        ok.setVisible(true);
        if(ritorno.getFeedBack()) {
            confermato.setText("Il giocatore è stato rimosso dalla squadra");
            Client.trasmissione((new Aquisizioni(new ElementiAggiornaNotifiche(idUtente,ControllerCreaSquadra.idSquadra),"aggiornaTabellaNotifiche")));
        }
        else confermato.setText("L'operazione non è avvenuta con successo");   
    }
    
    @FXML
    public void no(ActionEvent event){
        try {
            new CaricaPagine().startEliminaGiocatori();
        } catch (Exception ex) {
            Logger.getLogger(ControllerEliminazioneGiocatori.class.getName()).log(Level.SEVERE, null, ex);
        }
        CaricaPagine.avvisoStage.close();
    }
    
    /**
     * Metodo che permette la visualizzazione dei membri della squadra nella tabella
     * di eliminazione dei giocatori dalla squadra
     */
    public void visualizzaGiocatori(){
        Aquisizioni aqui = Client.trasmissione(new Aquisizioni("riempiTabellaMembriSquadra",ControllerCreaSquadra.idSquadra));
        ricevuti = aqui.getArrayComponentiSquadra();
        giocatoriPresenti.setEditable(true);
        visualizzati = FXCollections.observableArrayList(ricevuti);
        giocatoriPresenti.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("Giocatore"));
        tabSquadra.setItems(visualizzati);
        ricevuti.clear();
    }
}
