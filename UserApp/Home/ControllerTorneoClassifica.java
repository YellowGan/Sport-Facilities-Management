package Client.Home;

import Client.Aquisizioni;
import Client.ElementiTabellaCartelliniGialli;
import Client.ElementiTabellaCartelliniRossi;
import Client.ElementiTabellaClassificaMigliorPortiere;
import Client.ElementiTabellaClassificaTorneo;
import Client.ElementiTabellaClassificaMarcatori;
import Client.Client;
import Client.ElementiRiempiTabelleClassifica;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * Classe che controlla la pagina dove si visualizzano i dettagli del torneo di 
 * tipo campionato.
 */
public class ControllerTorneoClassifica implements Initializable{
    
    //dichiarazione oggetti del file fxml
    @FXML Label nomeTorneo = new Label();
    @FXML Label tipoTorneo = new Label();
    @FXML TableView<ElementiTabellaClassificaTorneo> tabClassificaTorneo = new TableView<ElementiTabellaClassificaTorneo>();
    @FXML TableColumn nomeSquadra = new TableColumn("nomeSquadra");
    @FXML TableColumn puntiSquadra = new TableColumn("puntiSquadra");
    @FXML TableColumn goalFatti = new TableColumn("goalFatti");
    @FXML TableColumn goalSubiti = new TableColumn("goalSubiti");
    @FXML TableView<ElementiTabellaClassificaMarcatori> tabClassificaMarcatori = new TableView<ElementiTabellaClassificaMarcatori>();
    @FXML TableColumn nomeMarcatore = new TableColumn("nomeMarcatore");
    @FXML TableColumn goalGiocatoreFatti = new TableColumn("goalGiocatoreFatti");
    @FXML TableView<ElementiTabellaClassificaMigliorPortiere> tabClassificaMigliorPortiere= new TableView<ElementiTabellaClassificaMigliorPortiere>();
    @FXML TableColumn nomePortiere = new TableColumn("nomePortiere");
    @FXML TableColumn goalPortiereSubiti = new TableColumn("goalPortiereSubiti");
    @FXML TableView<ElementiTabellaCartelliniGialli> tabCartelliniGialli = new TableView<ElementiTabellaCartelliniGialli>();
    @FXML TableColumn nomeGiocatoreAmmonito = new TableColumn("nomeGiocatoreAmmonito");
    @FXML TableColumn numeroGialli = new TableColumn("numeroGialli");
    @FXML TableView<ElementiTabellaCartelliniRossi> tabCartelliniRossi = new TableView<ElementiTabellaCartelliniRossi>();
    @FXML TableColumn nomeGiocatoreEspulso = new TableColumn("nomeGiocatoreEspulso");
    @FXML TableColumn numeroRossi = new TableColumn("numeroRossi");
    
    //dichiarazione attributi controller
    private ArrayList<ElementiTabellaClassificaTorneo> ricevutiTorneo;
    private ObservableList<ElementiTabellaClassificaTorneo> visualizzatiTorneo;
    private ArrayList<ElementiTabellaClassificaMarcatori> ricevutiMarcatori;
    private ObservableList<ElementiTabellaClassificaMarcatori> visualizzatiMarcatori;
    private ArrayList<ElementiTabellaClassificaMigliorPortiere> ricevutiPortiere;
    private ObservableList<ElementiTabellaClassificaMigliorPortiere> visualizzatiPortiere;
    private ArrayList<ElementiTabellaCartelliniGialli> ricevutiGialli;
    private ObservableList<ElementiTabellaCartelliniGialli> visualizzatiGialli;
    private ArrayList<ElementiTabellaCartelliniRossi> ricevutiRossi;
    private ObservableList<ElementiTabellaCartelliniRossi> visualizzatiRossi;
    private Aquisizioni ritorno;
    
    
    /**
     * Metodo di inizializzaizione delle tabelle contenenti le informazioni del 
     * torneo
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inizializzaLabel();
        riempiTabClassificaTorneo();
        riempiTabClassificaMarcatori(1);
        riempiTabClassificaMigliorPortiere(2);
        riempiTabCartelliniGialli(3);
        riempiTabCartelliniRossi(4);
        
    }
    
    @FXML
    public void tornaHome(MouseEvent event){
        try {
            new CaricaPagine().startHome();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    /**
     * Metodo che fa partire la visualizzazione che mostra il tabellone (inteso
     * come calendario del torneo) del campionato
     */
    @FXML
    public void tastoTabellone(ActionEvent event){
        try {
            new CaricaPagine().startTabellone();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo che permette di riempire la classifica del campionato
     */
    public void riempiTabClassificaTorneo(){
        ritorno = Client.trasmissione(new Aquisizioni("riempiTabellaClassificaTorneo",ControllerPagine.globalTorneo));
        ricevutiTorneo = ritorno.getArrayClassificaTorneo();
        
        nomeSquadra.setEditable(true);
        puntiSquadra.setEditable(true);
        goalFatti.setEditable(true);
        goalSubiti.setEditable(true);
        
        visualizzatiTorneo = FXCollections.observableArrayList(ricevutiTorneo);
        
        nomeSquadra.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("nomeSquadra"));
        puntiSquadra.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("punti"));
        goalFatti.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("goalFatti"));
        goalSubiti.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("goalSubiti"));
        
        tabClassificaTorneo.setItems(visualizzatiTorneo);
        ricevutiTorneo.clear();
        
    }
    
    /**
     * Metodo che permette di riempire la classifica dei dei capocannonieri
     */
    public void riempiTabClassificaMarcatori(int var){
        ritorno = Client.trasmissione(new Aquisizioni(new ElementiRiempiTabelleClassifica(ControllerPagine.globalTorneo, var),"riempiTabelleClassifica"));
        ricevutiMarcatori = ritorno.getArrayClassificaMarcatori();
        
        nomeMarcatore.setEditable(true);
        goalGiocatoreFatti.setEditable(true);
        
        visualizzatiMarcatori = FXCollections.observableArrayList(ricevutiMarcatori);
        
        nomeMarcatore.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("nomeMarcatore"));
        goalGiocatoreFatti.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("goalGiocatoreFatti"));
        
        tabClassificaMarcatori.setItems(visualizzatiMarcatori);
        ricevutiMarcatori.clear();
    }
    
    /**
     * Metodo che permette di riempire la classifica del miglior poriere
     */
    public void riempiTabClassificaMigliorPortiere(int var){
        ritorno = Client.trasmissione(new Aquisizioni(new ElementiRiempiTabelleClassifica(ControllerPagine.globalTorneo, var),"riempiTabelleClassifica"));
        ricevutiPortiere = ritorno.getArrayClassificaPortiere();
        
        nomePortiere.setEditable(true);
        goalPortiereSubiti.setEditable(true);
        
        visualizzatiPortiere = FXCollections.observableArrayList(ricevutiPortiere);
        
        nomePortiere.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("nomePortiere"));
        goalPortiereSubiti.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("goalPortiereSubiti"));
        
        tabClassificaMigliorPortiere.setItems(visualizzatiPortiere);
        ricevutiPortiere.clear();
    }
    
    /**
     * Metodo che permette di riempire la tabella dove vengono visualizzati i 
     * cartellini gialli dei giocatori
     */
    public void riempiTabCartelliniGialli(int var){
        ritorno = Client.trasmissione(new Aquisizioni(new ElementiRiempiTabelleClassifica(ControllerPagine.globalTorneo, var),"riempiTabelleClassifica"));
        ricevutiGialli = ritorno.getArrayCartelliniGialli();
        
        nomeGiocatoreAmmonito.setEditable(true);
        numeroGialli.setEditable(true);
        
        visualizzatiGialli = FXCollections.observableArrayList(ricevutiGialli);
        
        nomeGiocatoreAmmonito.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("nomeGiocatoreAmmonito"));
        numeroGialli.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("numeroGialli"));
        
        tabCartelliniGialli.setItems(visualizzatiGialli);
        ricevutiGialli.clear();
    }
    
    /**
     * Metodo che permette di riempire la tabella dove vengono visualizzati i 
     * cartellini rossi dei giocatori
     */
    public void riempiTabCartelliniRossi(int var){
        ritorno = Client.trasmissione(new Aquisizioni(new ElementiRiempiTabelleClassifica(ControllerPagine.globalTorneo, var),"riempiTabelleClassifica"));
        ricevutiRossi = ritorno.getArrayCartelliniRossi();
        
        nomeGiocatoreEspulso.setEditable(true);
        numeroRossi.setEditable(true);
        
        visualizzatiRossi = FXCollections.observableArrayList(ricevutiRossi);
        
        nomeGiocatoreEspulso.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("nomeGiocatoreEspulso"));
        numeroRossi.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("numeroRossi"));
        
        tabCartelliniRossi.setItems(visualizzatiRossi);
        ricevutiRossi.clear();
    }
    
    /**
     * Metodo che inizializza le label della pagina
     */
    public void inizializzaLabel(){  
        ritorno = Client.trasmissione(new Aquisizioni("settaLabelTipoTorneo",Integer.toString(CaricaPagine.globalIdTipoTorneo)));
        tipoTorneo.setText(ritorno.getGlobalID());
        nomeTorneo.setText(ControllerPagine.globalTorneo);    
    }
    
}
