package Client;

import java.io.IOException;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * La classe Aquisizioni è la classe dove vengono immessi tutti i dati che servono per compiere le varie operazioni di richiesta da parte del client
 * (richiesta di login, richiesta di iscrizione al database dell'applicativo, prenotazione dei campi disponibili, creazione di squadre e invio di partecipazione
 * alla squadra creata di altri utenti, ecc) e di risposta da parte del server (accettazione delle credenziali, accettazione dei dati di registrazione, ecc).
 * Questa classe implementa l'interfaccia Serializable che permette di trasferire gli oggetti attraverso stream (ObjectInputStream e ObjectOutputStream).
 */
public class Aquisizioni implements Serializable{
    private String globalID;
    private String nome;
    private String cognome;
    private String nick;
    private String pass;
    private String mail;
    private String conf;
    private String giorno;
    private String mese;
    private String anno;
    private String operazione;
    private boolean feedBack = false;
    private String fascia_oraria;
    private String stato;
    private String prenotato_da;
    private String tipo_partita;
    private ArrayList<ElementiTabellaPrenotazioni> righe = new ArrayList<ElementiTabellaPrenotazioni>();
    private ArrayList<ElementiTabellaTornei> arrayTornei = new ArrayList<ElementiTabellaTornei>();
    private ArrayList<String> arrayCombobox = new ArrayList<String>();
    private ArrayList<ElementiTabellaMiaSquadra> arrayMiaSquadra = new ArrayList<ElementiTabellaMiaSquadra>();
    private ArrayList<ElementiTabellaComponentiSquadra> arrayComponentiSquadra = new ArrayList<ElementiTabellaComponentiSquadra>();
    private ArrayList<ElementiTabellaRicercaGiocatori> arrayTabRicerca = new ArrayList<ElementiTabellaRicercaGiocatori>();
    private int idCampo;
    private int numOperazione;
    private String nomeSquadra;
    private String nomeTorneo;
    private String divisa;
    private ElementiTabellaRicercaGiocatori obj;
    private ElementiRichiesta richiesta;
    private ElementiAggiornaNotifiche aggiornamento;
    private ArrayList<ElementiTabellaClassificaTorneo> classificaTorneo = new ArrayList<ElementiTabellaClassificaTorneo>();
    private ElementiRiempiTabelleClassifica datiRiempimentoTabellaClassifiche;
    private ArrayList<ElementiTabellaClassificaMarcatori> classificaMarcatori = new ArrayList<ElementiTabellaClassificaMarcatori>();
    private ArrayList<ElementiTabellaClassificaMigliorPortiere> classificaPortiere = new ArrayList<ElementiTabellaClassificaMigliorPortiere>();
    private ArrayList<ElementiTabellaCartelliniGialli> cartelliniGialli = new ArrayList<ElementiTabellaCartelliniGialli>();
    private ArrayList<ElementiTabellaCartelliniRossi> cartelliniRossi = new ArrayList<ElementiTabellaCartelliniRossi>();
    private ArrayList<ElementiTabellone> tabellone = new ArrayList<ElementiTabellone>();
    private ArrayList<String> riempiLabelTabellone = new ArrayList<String>();
    private ElementiPassaggioFasi passo;
    private ElementiAggiungiPartitaAdmin elementiAggiungiPartita;
    private ElementiCreaTorneo elementiCreaTorneo;
    private ArrayList<ElementiTabellaCalendarioStatistiche> elementiTabellaCalendarioStatistiche = new ArrayList<ElementiTabellaCalendarioStatistiche>();
    private ElementiNomiSquadreStatisticheTorneo nomiSquadreStatisticheTorneo;
    private ArrayList<String> nomiGiocatoriCasa = new ArrayList<String>();
    private ArrayList<String> nomiGiocatoriTrasferta = new ArrayList<String>();
    private ArrayList<ElementiStatisticheGiocatore> giocatoreCasa = new ArrayList<ElementiStatisticheGiocatore>();
    private ArrayList<ElementiStatisticheGiocatore> giocatoreTrasferta = new ArrayList<ElementiStatisticheGiocatore>();
    
    
    public Aquisizioni(){
        this.globalID = null;
        this.nome = null;
        this.cognome = null;
        this.nick = null;
        this.pass = null;
        this.mail = null;
        this.conf = null;
        this.giorno = null;
        this.mese = null;
        this.anno = null;
        this.operazione = null; 
        this.fascia_oraria = null;
        this.stato = null;
        this.prenotato_da = null;
        this.tipo_partita = null;
        this.idCampo = 0;
        this.numOperazione = 0;
        this.nomeSquadra = null;
        this.nomeTorneo = null;
    }
    
    
    public Aquisizioni(ArrayList<ElementiStatisticheGiocatore> giocatoreCasa, ArrayList<ElementiStatisticheGiocatore> giocatoreTrasferta,String operazione){
        this.giocatoreCasa = giocatoreCasa;
        this.giocatoreTrasferta = giocatoreTrasferta;
        this.operazione = operazione;
    }
    /**
     * Costruttore per l'inserimento di un nuovo torneo
     * @param dati: oggetto contentente gli elementi che servono per creare
     * il nuovo torneo
     * @param operazione: indica al server cosa fare 

     */
    public Aquisizioni(ElementiCreaTorneo dati, String operazione){
        super();
        this.elementiCreaTorneo = dati;
        this.operazione = operazione;
    }
    /**
     * Costruttore per inserimento di una nuova partita di torneo
     * @param dati: oggetto contenente gli elementi che servono per creare la
     * partita di torneo
     * @param operazione: indica al server cosa fare 
     */
    public Aquisizioni(ElementiAggiungiPartitaAdmin dati, String operazione){
        this.elementiAggiungiPartita = dati;
        this.operazione = operazione;
    }
    
    /**
     * 
     * @param passo: Oggetto di tipo ElementiPassaggioFasi che ha come attributi
     * le squadre in casa e in trasferta di una partita di unospecifico toreno e
     * l'eventuale risultato.
     * 
     * @param operazione : Variabile che si trova all'interno di quasi tutti i 
     * costruttori che racchiude comunica al server l'operazione da effettuare.
     */
    public Aquisizioni(ElementiPassaggioFasi passo, String operazione){
        this.passo = passo;
        this.operazione = operazione;
    }
    /**
     * 
     * @param dati : oggetto di tipo ElementiRiempiTabelleClassifica dove al suo
     * interno gli attributi servono per l'operazione da effettuare
     * 
     * costruttore per riempimento tabella classifiche marcatori, portier, 
     * cartellini gialli e rossi
     */
    public Aquisizioni(ElementiRiempiTabelleClassifica dati, String operazione){
        this.datiRiempimentoTabellaClassifiche = dati;
        this.operazione = operazione;
    }
    
    /**
     * 
     * Costruttore per l'aggiornamento delle notifiche 
     */
    public Aquisizioni(ElementiAggiornaNotifiche aggiornamento, String operazione){
        super();
        this.aggiornamento = aggiornamento;
        this.operazione = operazione;
    }
    
    /**
     * 
     * @param richiesta: Oggetto di tipo ElementiRichiesta dove sono racchiusi i
     * dati che servono per inviare la richiesta di partecipazione a una squadra
     */
    public Aquisizioni(ElementiRichiesta richiesta, String operazione){
        super();
        this.richiesta = richiesta;
        this.operazione = operazione;
    }
    
    
    /**
     * 
     * @param dati: oggetto che racchiude al suo interno i dati che servono per 
     * la ricerca del giocatore all'interno del database
     * @param operazione 
     */
    public Aquisizioni(ElementiTabellaRicercaGiocatori dati, String operazione){
        super();
        this.obj = dati;
        this.operazione = operazione;
    }
    
    /**
     * Costruttore per creare una squadra
     * 
     * @param nomeSquadra: parametro che indica il nome della squadra
     * @param capitano: parametro che indicia il nome del capitano della squadra
     * @param divisa: parametro che indica il colore della divisa
     * @param globalID : parametro che comuncia l'ID dell'utente
     */
    public Aquisizioni(String nomeSquadra, String capitano, String divisa, String operazione, int globalID){
        this.nomeSquadra = nomeSquadra;
        this.nome = capitano;
        this.divisa = divisa;
        this.operazione = operazione;
        this.numOperazione = globalID;
    }
    
    /**
     * 
     * @param operazione: indica al server l'operazione da effettuare
     * @param nomeSquadra: indica il nomed della squadra
     * @param nomeTorneo: indica il nome del torneo
     * @param stato : indica lo stato del torneo (in corso/terminato)
     */
    public Aquisizioni(String operazione, String nomeSquadra, String nomeTorneo, boolean stato){
        super();
        this.nomeSquadra = nomeSquadra;
        this.nomeTorneo = nomeTorneo;
        this.operazione = operazione;
    }
    
    //cotruttore per riempire le combobox dell'iscrizione squadra a torneo e tabella mia squadra,
    //per controllare che il nome di una squadra durante la sua creazione sia uguale a un altra
    //per prendere l'id del tipo di torneo per visualizzare la pagina giusta
    //per riempire tabella classifica torneo
    
    /**
     * Costruttore che serve per riempire la combobox per l'iscrizione di una 
     * squadra al torneo, per controllare che il nome di una squadra durante
     * la creazione non sia ripetuto, per per prendere l'ID del tipo di un 
     * torneo cosi da visualizzare la pagina corretta e per riempire la tabella
     * dove vengono visualizzate le informazioni sulla classifica del torneo.
     */
    public Aquisizioni(String operazione, String globalID){
        super();
        this.operazione = operazione;
        this.globalID = globalID;
    }
    
    
    public Aquisizioni(String operazione, int i){
        super();
        this.operazione = operazione;
        this.numOperazione = i;
    }

    
    /**
     * Costruttore che serve per la query di riempimento della tabella dove 
     * vengono visualizzati a seconda della data gli orari liberi e occupati
     * nella tabella prenotazioni
     * 
     * @param data: indica la data scelta
     * @param operazione: indica al server l'operazione da effettuare
     * @param i : indica l'ID del campo in cui si desidera prenotare
     */
    public Aquisizioni(String data, String operazione, int i){
        super();
        this.anno = data;
        this.operazione = operazione;
        this.idCampo = i;
    }
    
    /**
     * Costruttore che serve per il login del client
     * 
     * @param username: indica l'username del cliente
     * @param password: indica la password del cliente
     * @param operazione: indica al server l'operazione da effettuare
     */
    public Aquisizioni(String username, String password, String operazione){
        super();
        this.nick = username;
        this.pass = password;
        this.operazione = operazione;
    }
    
    /**
     * Costruttore che serve per la registrazione di un cliente al database.
     * 
     * @param nome: nome del cliente
     * @param cognome: cognome del cliente
     * @param nick: username desiderato
     * @param pass: password desiderata
     * @param mail: email di recapito
     * @param conf: conferma dell'email di recapito
     * @param giorno: giorno di nasciata
     * @param mese: mese di nascita
     * @param anno: anno di nascita
     * @param operazione: indica al server l'operazione da effettuare
     */
    public Aquisizioni(String nome, String cognome, String nick, String pass, String mail, String conf, String giorno, String mese, String anno, String operazione){
        super();
        this.nome = nome;
        this.cognome = cognome;
        this.nick = nick;
        this.pass = pass;
        this.mail = mail;
        this.conf = conf;
        this.giorno = giorno;
        this.mese = mese;
        this.anno = anno;
        this.operazione = operazione; 
    }
    
    /**
     * Costruttore che serve a riempire la tabella dove vengono visualizzate le
     * informazioni della tabella di prenotazione dei campi
     * 
     * @param fascia_oraria: indica la colonna delle fascie orarie
     * @param stato: lo stato del campo (occupato, libero)
     * @param prenotato_da: indica da chi è prenotato
     * @param tipo_partita: indica il tipo di partita
     * @param operazione : indica al server l'operazione da effettuare
     */
    public Aquisizioni(String fascia_oraria, String stato, String prenotato_da, String tipo_partita, String operazione){
        super();
        this.fascia_oraria = fascia_oraria;
        this.stato = stato;
        this.prenotato_da = prenotato_da;
        this.tipo_partita = tipo_partita;
        this.operazione = operazione;
    }
    
    
    /**
     * Costruttore per la conferma di prenotazione del campo
     */
    public Aquisizioni(String Fascia_oraria, String data, String idUtente, int idCampo, String operazione){
        super();
        this.fascia_oraria = Fascia_oraria;
        this.anno = data;
        this.globalID = idUtente;
        this.idCampo = idCampo;
        this.operazione = operazione;
    }

    /**
     * 
     * Metodo che permette di scrivere gli oggetti nello stream che collega il 
     * client con il server
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.writeObject(this.globalID);
        stream.writeObject(this.nome);
        stream.writeObject(this.cognome);
        stream.writeObject(this.nick);
        stream.writeObject(this.pass);
        stream.writeObject(this.mail);
        stream.writeObject(this.conf);
        stream.writeObject(this.giorno);
        stream.writeObject(this.mese);
        stream.writeObject(this.anno); 
        stream.writeObject(this.feedBack);
        stream.writeObject(this.operazione);
        stream.writeObject(this.fascia_oraria);
        stream.writeObject(this.stato);
        stream.writeObject(this.prenotato_da);
        stream.writeObject(this.tipo_partita);
        stream.writeObject(this.righe);
        stream.writeObject(this.arrayTornei);
        stream.writeObject(this.idCampo);
        stream.writeObject(this.numOperazione);
        stream.writeObject(this.arrayCombobox);
        stream.writeObject(this.arrayMiaSquadra);
        stream.writeObject(this.nomeSquadra);
        stream.writeObject(this.nomeTorneo);
        stream.writeObject(this.divisa);
        stream.writeObject(this.arrayComponentiSquadra);
        stream.writeObject(this.obj);
        stream.writeObject(this.arrayTabRicerca);
        stream.writeObject(this.richiesta);
        stream.writeObject(this.aggiornamento);
        stream.writeObject(this.classificaTorneo);
        stream.writeObject(this.datiRiempimentoTabellaClassifiche);
        stream.writeObject(this.classificaMarcatori);
        stream.writeObject(this.classificaPortiere);
        stream.writeObject(this.cartelliniGialli);
        stream.writeObject(this.cartelliniRossi);
        stream.writeObject(this.tabellone);
        stream.writeObject(this.riempiLabelTabellone);
        stream.writeObject(this.passo);
        stream.writeObject(this.elementiAggiungiPartita);
        stream.writeObject(this.elementiCreaTorneo);
        stream.writeObject(this.elementiTabellaCalendarioStatistiche);
        stream.writeObject(this.nomiSquadreStatisticheTorneo);
        stream.writeObject(this.nomiGiocatoriCasa);
        stream.writeObject(this.nomiGiocatoriTrasferta);
        stream.writeObject(this.giocatoreCasa);
        stream.writeObject(this.giocatoreTrasferta);
        
    }

    
    /**
     * 
     * Metodo che permette di leggere gli oggetti nello stream che collega il 
     * client con il server
     */
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        globalID = (String) stream.readObject();
        nome = (String) stream.readObject();
        cognome = (String) stream.readObject();
        nick = (String) stream.readObject();
        pass = (String) stream.readObject();
        mail = (String) stream.readObject();
        conf = (String) stream.readObject();
        giorno = (String) stream.readObject();
        mese = (String) stream.readObject();
        anno = (String) stream.readObject();
        feedBack = (boolean) stream.readObject();
        operazione = (String) stream.readObject();
        fascia_oraria = (String) stream.readObject();
        stato = (String) stream.readObject();
        prenotato_da = (String) stream.readObject();
        tipo_partita = (String) stream.readObject();
        righe = (ArrayList<ElementiTabellaPrenotazioni>) stream.readObject();
        arrayTornei = (ArrayList<ElementiTabellaTornei>) stream.readObject();
        idCampo = (int) stream.readObject();
        numOperazione = (int) stream.readObject();
        arrayCombobox = (ArrayList<String>) stream.readObject();
        arrayMiaSquadra = (ArrayList<ElementiTabellaMiaSquadra>) stream.readObject();
        nomeSquadra = (String) stream.readObject();
        nomeTorneo = (String) stream.readObject();
        divisa = (String) stream.readObject();
        arrayComponentiSquadra = (ArrayList<ElementiTabellaComponentiSquadra>) stream.readObject();
        obj = (ElementiTabellaRicercaGiocatori) stream.readObject();
        arrayTabRicerca = (ArrayList<ElementiTabellaRicercaGiocatori>) stream.readObject();
        richiesta = (ElementiRichiesta) stream.readObject();
        aggiornamento = (ElementiAggiornaNotifiche) stream.readObject();
        classificaTorneo = (ArrayList<ElementiTabellaClassificaTorneo>) stream.readObject();
        datiRiempimentoTabellaClassifiche = (ElementiRiempiTabelleClassifica) stream.readObject();
        classificaMarcatori = (ArrayList<ElementiTabellaClassificaMarcatori>) stream.readObject();
        classificaPortiere = (ArrayList<ElementiTabellaClassificaMigliorPortiere>) stream.readObject();
        cartelliniGialli = (ArrayList<ElementiTabellaCartelliniGialli>) stream.readObject();
        cartelliniRossi = (ArrayList<ElementiTabellaCartelliniRossi>) stream.readObject();
        tabellone = (ArrayList<ElementiTabellone>) stream.readObject();
        riempiLabelTabellone = (ArrayList<String>) stream.readObject();
        passo = (ElementiPassaggioFasi) stream.readObject();
        elementiAggiungiPartita = (ElementiAggiungiPartitaAdmin) stream.readObject();
        elementiCreaTorneo = (ElementiCreaTorneo) stream.readObject();
        elementiTabellaCalendarioStatistiche = (ArrayList<ElementiTabellaCalendarioStatistiche>) stream.readObject();
        nomiSquadreStatisticheTorneo = (ElementiNomiSquadreStatisticheTorneo) stream.readObject();
        nomiGiocatoriCasa = (ArrayList<String>) stream.readObject();
        nomiGiocatoriTrasferta = (ArrayList<String>) stream.readObject();
        giocatoreCasa = (ArrayList<ElementiStatisticheGiocatore>) stream.readObject();
        giocatoreTrasferta = (ArrayList<ElementiStatisticheGiocatore>) stream.readObject();
    }

    
    /**
     * Vari metodi di setter e getter 
     */
    public String getNome(){
        return this.nome;
    }
    
    public String getCognome(){
        return this.cognome;
    }
    
    public String getNick(){
        return this.nick;
    }
    
    public String getPassword(){
        return this.pass;
    }
    
    public String getMail(){
        return this.mail;
    }
    
    public String getConfermaEmail(){
        return this.conf;
    }
    
    public String getGiorno(){
        return this.giorno;
    }
    
    public String getMese(){
        return this.mese;
    }
    
    public String getAnno(){
        return this.anno;
    }
    
    public boolean getFeedBack(){
        return this.feedBack;
    }
    
    public void setFeedBack(boolean stato){
        this.feedBack = stato;
    }
    
    public String getOperazione(){
        return this.operazione;
    }
    
    public void setOperazione(String operazione){
        this.operazione = operazione;
    }
    
    public String getFasciaOraria(){
        return this.fascia_oraria;
    }
    
    public String getStato(){
        return this.stato;
    }
    
    public String getPrenotatoDa(){
        return this.prenotato_da;
    }
    
    public String getTipoPartita(){
        return this.tipo_partita;
    }
    
    public ArrayList getRighe(){
        return this.righe;
    }
    
    public void addArray(ElementiTabellaPrenotazioni dati){
        this.righe.add(dati);
    }
    
    public int getIdCampo(){
        return this.idCampo;
    }
    
    public String getGlobalID(){
        return this.globalID;
    }
    
    public void setGlobalId(String id){
        this.globalID = id;
    }
    
    public ArrayList getArrayTornei(){
        return this.arrayTornei;
    }
    
    public void addArrayTornei(ElementiTabellaTornei dati){
        this.arrayTornei.add(dati);
    }
    
    public int getNumOperazione(){
        return this.numOperazione;
    }
    
    public ArrayList getArrayCombobox(){
        return this.arrayCombobox;
    }
    
    public void addArrayCombobox(String elemento){
        this.arrayCombobox.add(elemento);
    }
    
    public ArrayList getArrayMiaSquadra(){
        return this.arrayMiaSquadra;
    }
    
    public void addArrayMiaSquadra(ElementiTabellaMiaSquadra dati){
        this.arrayMiaSquadra.add(dati);
    }
    
    public String getNomeTorneo(){
        return this.nomeTorneo;
    }
    
    public String getNomeSquadra(){
        return this.nomeSquadra;
    }
    
    public String getDivisa(){
        return this.divisa;
    }
    
    public void setNomeSquadra(String nome){
        this.nomeSquadra = nome;
    }
    
    public ArrayList getArrayComponentiSquadra(){
        return this.arrayComponentiSquadra;
    }
    
    public void addArrayComponentiSquadra(ElementiTabellaComponentiSquadra dati){
        this.arrayComponentiSquadra.add(dati);
    }
    
    public ArrayList getArrayTabRicerca(){
        return this.arrayTabRicerca;
    }
    
    public void addArrayTabRicerca(ElementiTabellaRicercaGiocatori dati){
        this.arrayTabRicerca.add(dati);
    }
    
    public ElementiTabellaRicercaGiocatori getObj(){
        return this.obj;
    }
    
    public ElementiRichiesta getRichiesta(){
        return this.richiesta;
    }
    
    public ElementiAggiornaNotifiche getAggiornamento(){
        return this.aggiornamento;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setCognome(String cognome){
        this.cognome = cognome;
    }
    
    public void setUsername(String username){
        this.nick = username;
    }
    
    public void setEmail(String email){
        this.mail = email;
    }
    
    public void setData(String data){
        this.anno = data;
    }
    
    public ArrayList getArrayClassificaTorneo(){
        return this.classificaTorneo;
    }
    
    public void addArrayClassificaTorneo(ElementiTabellaClassificaTorneo dati){
        this.classificaTorneo.add(dati);
    }
    
    public ElementiRiempiTabelleClassifica getDatiRiempimentoTabellaClassifiche(){
        return this.datiRiempimentoTabellaClassifiche;
    }
    
    public ArrayList getArrayClassificaMarcatori(){
        return this.classificaMarcatori;
    }
    
    public void addArrayClassificaMarcatori(ElementiTabellaClassificaMarcatori dati){
        this.classificaMarcatori.add(dati);
    }
    
    public ArrayList getArrayClassificaPortiere(){
        return this.classificaPortiere;
    }
    
    public void addArrayClassificaPortiere(ElementiTabellaClassificaMigliorPortiere dati){
        this.classificaPortiere.add(dati);
    }
    
    public ArrayList getArrayCartelliniGialli(){
        return this.cartelliniGialli;
    }
    
    public void addArrayCartelliniGialli(ElementiTabellaCartelliniGialli dati){
        this.cartelliniGialli.add(dati);
    }
    
    public ArrayList getArrayCartelliniRossi(){
        return this.cartelliniRossi;
    }
    
    public void addArrayCartelliniRossi(ElementiTabellaCartelliniRossi dati){
        this.cartelliniRossi.add(dati);
    }
    
    public ArrayList getArrayTabellone(){
        return this.tabellone;
    }
    
    public void addArrayTabellone(ElementiTabellone dati){
        this.tabellone.add(dati);
    }
    
    public ArrayList getArrayRiempiLabelTabellone(){
        return this.riempiLabelTabellone;
    }
    
    public void addArrayRiempiLabelTabellone(String dati){
        this.riempiLabelTabellone.add(dati);
    }
    
    public ElementiPassaggioFasi getPasso(){
        return this.passo;
    }
    
    public void setRisPasso(String ris){
        this.passo.setRis(ris);
    }
    
    public ElementiAggiungiPartitaAdmin getElementiAggiungiPartita(){
        return this.elementiAggiungiPartita;
    }
    
    public ElementiCreaTorneo getElementiCreaTorneo(){
        return this.elementiCreaTorneo;
    }
    
    public ArrayList getArrayElementiTabellaCalendarioStatistiche(){
        return this.elementiTabellaCalendarioStatistiche;
    }
    
    public void addArrayElementiTabellaCalendarioStatistiche(ElementiTabellaCalendarioStatistiche dati){
        this.elementiTabellaCalendarioStatistiche.add(dati);
    }
    
    public ElementiNomiSquadreStatisticheTorneo getNomiSquadreStatisticheTorneo(){
        return this.nomiSquadreStatisticheTorneo;
    }
    
    public void setNomiSquadreStatisticheTorneo(ElementiNomiSquadreStatisticheTorneo dati){
        this.nomiSquadreStatisticheTorneo = dati;
    }
    
    public ArrayList getArrayNomiGiocatoriCasa(){
        return this.nomiGiocatoriCasa;
    }
    
    /*public void addArrayArrayNomiGiocatoriCasa(String dati){
        this.nomiGiocatoriCasa.add(dati);
    }*/
    
    public ArrayList getArrayNomiGiocatoriTrasferta(){
        return this.nomiGiocatoriTrasferta;
    }
    
    /*public void addArrayArrayNomiGiocatoriTrasferta(String dati){
        this.nomiGiocatoriTrasferta.add(dati);
    }*/
    
    public void setArrayNomiGiocatoriCasa(ArrayList<String> dati){
        this.nomiGiocatoriCasa = dati;
    }
    
    public void setArrayNomiGiocatoriTrasferta(ArrayList<String> dati){
        this.nomiGiocatoriTrasferta = dati;
    }
    
    public ArrayList getGiocatoreCasa(){
        return this.giocatoreCasa;
    }
    
    public ArrayList getGiocatoreTrasferta(){
        return this.giocatoreTrasferta;
    }
}
