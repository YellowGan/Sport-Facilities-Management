package Client.Home;

import Client.Client;
import Client.Aquisizioni;
import Client.ElementiRichiesta;
import Client.ElementiTabellaRicercaGiocatori;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Classe che controlla la paigna di ricerca dei giocatori, manda la richiesta 
 * con la sottostringa al server che successivamente a sua volta risponde con
 * i giocarori che contengono quella sottostring. La classe poi stamperà nella 
 * tabella il risultato della ricerca
 */
public class ControllerCercaGiocatori implements Initializable{
    
    @FXML TableView<ElementiTabellaRicercaGiocatori> tabRicerca = new TableView<ElementiTabellaRicercaGiocatori>();
    @FXML TableColumn nick = new TableColumn("nick");
    @FXML TableColumn nomeCognome = new TableColumn("nomeCognome");
    @FXML TextField cercaNick;
    @FXML Label Giocatore = new Label();
    @FXML Button conferma = new Button();
    @FXML Button ok = new Button();
    @FXML Button annulla = new Button();
    @FXML Label info1 = new Label();
    @FXML Label info2 = new Label();
    @FXML Label inviata = new Label();
    
    ArrayList<ElementiTabellaRicercaGiocatori> ricevuti;
    ObservableList<ElementiTabellaRicercaGiocatori> visualizzati;
    public static String giocatore;

    /**
     * Metodo che si attiva premento l'apposito pulsante. Questo metodo permette
     * di inviare la richiesta di riccerca dei giocaori inviando la sottostringa.
     */
    @FXML
    public void cerca(ActionEvent event){
        String substring;
        System.out.println("cerca");
        substring = cercaNick.getText();
        Aquisizioni aqui = Client.trasmissione(new Aquisizioni(new ElementiTabellaRicercaGiocatori(substring),"ricercaGiocatore"));
        ricevuti = aqui.getArrayTabRicerca();
        nick.setEditable(true);
        nomeCognome.setEditable(true);
        visualizzati = FXCollections.observableArrayList(ricevuti);
        nick.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("nick"));
        nomeCognome.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("nomeCognome"));
        tabRicerca.setItems(visualizzati);
        ricevuti.clear();
    }
    
    /**
     * Metodo che serve per tornaro alla pagina precedente
     */
    @FXML
    public void tornaIndietro(ActionEvent event){
        try {
            new CaricaPagine().startCreaSquadra();
        } catch (Exception ex) {
            Logger.getLogger(ControllerCercaGiocatori.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo che serve per selezionare un giocatore dalla tabella di risultato
     * della ricerca dei giocatori, una volta selezionato questo metodo avvierà
     * una finestra di conferma di aggiunta giocatore
     */
    public void selezioneGiocatori(){
        TablePosition pos = tabRicerca.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        ElementiTabellaRicercaGiocatori item = tabRicerca.getItems().get(row);
        TableColumn col = pos.getTableColumn();
        this.giocatore = (String) col.getCellObservableValue(item).getValue();
        try {
            new CaricaPagine().startAvvisoInvioRichiesta();
            
        } catch (Exception ex) {
            Logger.getLogger(ControllerCercaGiocatori.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo che serve per confermare la richiesta di aggiunta del giocatore
     */
    @FXML
    public void conferma(ActionEvent event){
        System.out.println("ho premuto conferma");
        Aquisizioni aqui = Client.trasmissione(new Aquisizioni(new ElementiRichiesta(this.giocatore, ControllerCreaSquadra.idSquadra),"invioRichiesta"));
        conferma.setVisible(false);
        annulla.setVisible(false);
        info1.setVisible(false);
        info2.setVisible(false);
        Giocatore.setVisible(false);
        ok.setVisible(true);
        if(aqui.getFeedBack()){
            inviata.setText("Richiesta inviata con successo");
        }else{
            inviata.setText("Richiesta non inviata");
        }
    }
    
    /**
     * Metodo che chiude la finestra di avviso  
     */
    @FXML
    public void annulla(ActionEvent event){
        try {
            CaricaPagine.avvisoStage.close();
        } catch (Exception ex) {
            Logger.getLogger(ControllerAvviso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
            Giocatore.setText(this.giocatore);
    } 
}