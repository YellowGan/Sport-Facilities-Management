package Client.Home;

import Client.Aquisizioni;
import Client.Client;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller che controlla l'interfaccia in cui si aggiungono i giocatori da
 * inserire all'interno di una squadra
 */
public class ControllerAggiungiGiocatori implements Initializable{
    
    @FXML Label nominativoSquadra = new Label();
    @FXML TableView<ElementiTabellaComponentiSquadra> tabSquadra = new TableView<ElementiTabellaComponentiSquadra>();
    @FXML TableColumn giocatoriSquadra = new TableColumn();
    
    ArrayList<ElementiTabellaComponentiSquadra> ricevuti;
    ObservableList<ElementiTabellaComponentiSquadra> visualizzati;

    /**
     * Metodo che serve per inizializzare le variabili all'avvio dell'interfaccia
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nominativoSquadra.setText(ControllerCreaSquadra.squadra);
        visualizzaGiocatori();
    }
    
    /**
     * Metodo attivato quando si preme il pulsante che serve per far partire 
     * l'interfaccia dedicata all'eliminazione di un giocatore dalla squadra 
     */
    @FXML
    public void eliminaGiocatore(ActionEvent event){
        System.out.println("Premuto elimina giocatore");
        try {
            new CaricaPagine().startEliminaGiocatori();
        } catch (Exception ex) {
            Logger.getLogger(ControllerCreaSquadra.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Metodo attivato quando si preme il pulsante che serve per far partire 
     * l'interfaccia che serve a ricercare i giocatori e inserirli nella squadra
     */
    @FXML
    public void aggiungiGiocatore(ActionEvent event){
        System.out.println("premuto aggiungi giocatore");
        try {
            new CaricaPagine().startAggiungiGiocatore();
        } catch (Exception ex) {
            Logger.getLogger(ControllerCreaSquadra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo che serve per salvare le modifiche apportate alla squadra, esso
     * è attivato qunando si preme il relativo pulsante
     */
    @FXML
    public void salva(ActionEvent event){
        System.out.println("premuto salva");
        try {
            new CaricaPagine().startAvvisoConfermaSalvataggioSquadra();
        } catch (Exception ex) {
            Logger.getLogger(ControllerCreaSquadra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo per tornare indietro alla home e viene attivato quando si preme il 
     * relativo pulsante
     */
    @FXML
    public void torna(ActionEvent event){
        System.out.println("torna");
        try {
            new CaricaPagine().startHome();
        } catch (Exception ex) {
            Logger.getLogger(ControllerCreaSquadra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo per chiudere l'avviso quando si preme il pulsante ok 
     */
    @FXML
    public void ok(ActionEvent event){
        System.out.println("ok");
        CaricaPagine.avvisoStage.close();

    }

    /**
     * Metodo che serve per visualizzare i giocatori all'interno della tabella
     * dove vengono visualizzati i membri della squadra.
     */
    public void visualizzaGiocatori(){
        Aquisizioni aqui = Client.trasmissione(new Aquisizioni("riempiTabellaMembriSquadra",ControllerCreaSquadra.idSquadra));
        ricevuti = aqui.getArrayComponentiSquadra();
        giocatoriSquadra.setEditable(true);
        visualizzati = FXCollections.observableArrayList(ricevuti);
        giocatoriSquadra.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("Giocatore"));
        tabSquadra.setItems(visualizzati);
        ricevuti.clear();
    }
}
