package Client.Home;

import Client.Aquisizioni;
import Client.Client;
import Client.ElementiTabellaMiaSquadra;
import Client.ElementiTabellaPrenotazioni;
import Client.ElementiTabellaTornei;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * Classse che controlla le pagine iniziali (home) dell'applicativo.
 */
public class ControllerPagine implements Initializable{  
    public static int var;
    public static int statoTorneo = 1;
    
    @FXML Label LCalcio5 = new Label();    
    @FXML Label LCalcio7 = new Label();    
    @FXML Label LCalcio11 = new Label();    
    @FXML DatePicker vis_data = new DatePicker();    
    @FXML TableView<ElementiTabellaPrenotazioni> tabella_prenotazioni = new TableView<ElementiTabellaPrenotazioni>();    
    @FXML TableColumn fasciaOraria = new TableColumn("fasciaOraria");    
    @FXML TableColumn stato = new TableColumn("stato");    
    @FXML TableColumn prenotatoDa = new TableColumn("prenotatoDa");    
    @FXML TableColumn tipoPartita = new TableColumn("tipoPartita");    
    @FXML TableView<ElementiTabellaTornei> tabTornei = new TableView<ElementiTabellaTornei>();
    @FXML TableColumn tornei = new TableColumn("torneiInCorso");    
    @FXML TableColumn dataInizio = new TableColumn("dataInizio");
    @FXML TableColumn tipoTorneo = new TableColumn("tipoTorneo");
    @FXML TableView<ElementiTabellaMiaSquadra> tabMiaSquadra = new TableView<ElementiTabellaMiaSquadra>();
    @FXML TableColumn mySquadra = new TableColumn("mySquadra");    
    @FXML TableColumn capitano = new TableColumn("capitano");
    @FXML Label nome = new Label();
    @FXML Label cognome = new Label();
    @FXML Label username = new Label();
    @FXML Label email = new Label();
    @FXML Label data = new Label();
    
    ArrayList<ElementiTabellaPrenotazioni> ricevuti;
    ArrayList<ElementiTabellaTornei> ricevutiTornei;
    ArrayList<ElementiTabellaMiaSquadra> ricevutiMiaSquadra;
    ObservableList<ElementiTabellaPrenotazioni> visualizzati;
    ObservableList<ElementiTabellaTornei> visualizzatiTornei;
    ObservableList<ElementiTabellaMiaSquadra> visualizzatiMiaSquadra;
    public static String globalData;
    public static String globalFascia;
    public static String globalNomeSquadra;
    public static String globalTorneo;
    
    /**
     * I seguenti metodi (Clacio5, Clacio7 e Calcio11) si attivano cliccando sui
     * relativi pulsanti permettono di aprire la finestra di prenotazione del 
     * campo a seconda di quale si vuole scegliere, se a 5, a 7 o a 11.
     */
    @FXML
    public void Calcio5(ActionEvent event){
       
       try{
            var = 1;
            new CaricaPagine().startPrenota();
        } catch (Exception e){
            e.printStackTrace();            
        }       
    }
    
    @FXML
    public void Calcio7(ActionEvent event){
         try{
            var = 2;
            new CaricaPagine().startPrenota();
        } catch (Exception e){
            e.printStackTrace();            
        }
    }
    
    @FXML
    public void Calcio11(ActionEvent event){
         try{
            var = 3;
            new CaricaPagine().startPrenota();
        } catch (Exception e){
            e.printStackTrace();            
        }
    }
    
    /**
     * Questo metodo si usa nella finestra di prenotazione e permette di tornare
     * alla home
     */
    @FXML
    public void tornaHome(MouseEvent event){
        try {
            new CaricaPagine().startHome();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
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
        Aquisizioni aqui = Client.trasmissione(new Aquisizioni(globalData,"tabellaPrenotazioni",var));
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
            new CaricaPagine().startAvviso();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Questo metodo si attiva quando si clicca sul pulsante tornei terminati
     * dove viene aperta una finestra dove si vedono i tornei che sono stati
     * terminati
     */
    public void torneiTerminati(ActionEvent event){
        try {
            this.statoTorneo = 0;
            new CaricaPagine().startTorneiTerminati();
            
        } catch (Exception ex) {
            Logger.getLogger(ControllerPagine.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Metodo che si attiva quando si clicca su crea nuova squadra.
     * Il metodo fa partire la finestra dove si salva il nome, il capitano e il 
     * colore della divisa della nuova squadra da creare
     */
    public void newSquadra(ActionEvent event){
        System.out.println("Crea nuova squadra");
        try {
            new CaricaPagine().startNomeSquadra();
        } catch (Exception ex) {
            Logger.getLogger(ControllerPagine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Questo metodo si attiva quando su vuole iscrivere una delle squadra di 
     * cui si è membro a un torneo di quelli in corso
     */
    public void iscriviSquadra(ActionEvent event){
        System.out.println("Iscrivi nuova squadra");
        try {
            new CaricaPagine().startIscrizioneSquadra();
        } catch (Exception ex) {
            Logger.getLogger(ControllerPagine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo che inizializza a seconda della variabile var la pagina di prenotazione
     * dei campi e chiama i metodi per riempire le tabelle delle proprie squadre
     * e dei tornei in corso
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
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
        
        riempiTabTornei(this.statoTorneo);
        riempiTabMiaSquadra();
        this.statoTorneo = 1;
        
        riempiInfo();
    }
    
    public void riempiTabTornei(int stato){
        Aquisizioni aqui = Client.trasmissione(new Aquisizioni("tabellaTornei",stato));
        ricevutiTornei = aqui.getArrayTornei();
        
        tornei.setEditable(true);
        dataInizio.setEditable(true);
        tipoTorneo.setEditable(true);
      
        visualizzatiTornei = FXCollections.observableArrayList(ricevutiTornei);
        
        tornei.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("nomeTorneo"));
        dataInizio.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("dataInizio"));
        tipoTorneo.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("tipoTorneo"));
        
        tabTornei.setItems(visualizzatiTornei);
        ricevutiTornei.clear();
    }
    
    public void riempiTabMiaSquadra(){
        Aquisizioni aqui = Client.trasmissione(new Aquisizioni("riempiTabellaMiaSquadra", Controller.globalID));
        ricevutiMiaSquadra = aqui.getArrayMiaSquadra();
        
        mySquadra.setEditable(true);
        capitano.setEditable(true);
        
        visualizzatiMiaSquadra = FXCollections.observableArrayList(ricevutiMiaSquadra);
        
        mySquadra.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("nomeSquadra"));
        capitano.setCellValueFactory(new PropertyValueFactory<Aquisizioni,String>("capitanoSquadra"));
        
        tabMiaSquadra.setItems(visualizzatiMiaSquadra);
        ricevutiMiaSquadra.clear();
    }
    
    /**
     * Metodo che controlla se l'utente ha un invito di partecipazione a una squadra,
     * se ci sono inviti allora fa partire un avviso per chiedere all'utente
     * se vuole o no partecipare alla squadra
     */
    public void controllaNotifiche(){
        Aquisizioni aqui = Client.trasmissione(new Aquisizioni("controllaNotifiche", Controller.globalID));
        
        if(aqui.getFeedBack()){
            try {
                globalNomeSquadra = aqui.getNomeSquadra();
                new CaricaPagine().startNotifica();
            } catch (Exception ex) {
                Logger.getLogger(ControllerPagine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else System.out.println("non hai notifiche");
    }
    
    /**
     * Metodo che si attiva qunado si sceglie una squadra da modificare dalla
     * propria tabella delle squadre di cui si fa parte
     */
    @FXML
    public void modificaSquadra(){
        
        TablePosition pos = tabMiaSquadra.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        ElementiTabellaMiaSquadra item = tabMiaSquadra.getItems().get(row);
        TableColumn col = pos.getTableColumn();
        ControllerCreaSquadra.squadra = (String) col.getCellObservableValue(item).getValue();
        Aquisizioni ritorno = Client.trasmissione(new Aquisizioni("restituisciIdSquadra",ControllerCreaSquadra.squadra));
        ControllerCreaSquadra.idSquadra = ritorno.getGlobalID();
        try {
            new CaricaPagine().startCreaSquadra();
        } catch (Exception ex) {
            Logger.getLogger(ControllerPagine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo che si attiva quando l'utente si logga e fa vedere le informazioni
     * del profilo dell'utente
     */
    public void riempiInfo(){
        String data_di_nascita,split [];
        Aquisizioni ritorno = Client.trasmissione(new Aquisizioni("riempiInfo",Controller.globalID));
        data_di_nascita = ritorno.getAnno();
        split = data_di_nascita.split("-");
        data_di_nascita = split[2] + "-" + split[1] + "-" + split[0];
                
        nome.setText(ritorno.getNome());
        cognome.setText(ritorno.getCognome());
        username.setText(ritorno.getNick());
        email.setText(ritorno.getMail());
        data.setText(data_di_nascita);
    }
    
    /**
     * Questo metodo si attiva quando si seglie un torneo dalla tabella di cui si
     * vuole vedere i dettagli del torneo selezioanto
     */
    @FXML
    public void selezionaTorneo(){
        
        TablePosition pos = tabTornei.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        ElementiTabellaTornei item = tabTornei.getItems().get(row);
        TableColumn col = pos.getTableColumn();
        globalTorneo = (String) col.getCellObservableValue(item).getValue();
        Aquisizioni ritorno = Client.trasmissione(new Aquisizioni("restituisciIdTipoTorneo",globalTorneo));     
        try {
            new CaricaPagine().startTorneo(Integer.parseInt(ritorno.getGlobalID()));
        } catch (Exception ex) {
            Logger.getLogger(ControllerPagine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
