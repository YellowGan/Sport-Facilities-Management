package Server;

import Client.Aquisizioni;
import Client.ElementiNomiSquadreStatisticheTorneo;
import Client.ElementiStatisticheGiocatore;
import Client.ElementiTabellaCalendarioStatistiche;
import Client.ElementiTabellaCartelliniGialli;
import Client.ElementiTabellaCartelliniRossi;
import Client.ElementiTabellaClassificaMarcatori;
import Client.ElementiTabellaClassificaMigliorPortiere;
import Client.ElementiTabellaClassificaTorneo;
import Client.ElementiTabellaComponentiSquadra;
import Client.ElementiTabellaMiaSquadra;
import Client.ElementiTabellaPrenotazioni;
import Client.ElementiTabellaRicercaGiocatori;
import Client.ElementiTabellaTornei;
import Client.ElementiTabellone;
import ConnDB.ConnDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Operazioni {
    private static ResultSet risultato = null;
    private static String qry;
    
    /**
     * Metodo che serve per la registrazione di un nuovo utente nel database,
     * Quando inserisce l'username, se c'è qualche altro utente che ha lo stesso 
     * username allora lo comunica al client facendo cambiare l'username al client
     */
    public static void registrazione(Aquisizioni dati, ConnDB db){
        //dichiaro variabili di registrazione e le inizializzo
        String nome, cognome, nick, pass, mail, giorno,  anno, var = null , data, mese;

        nome = dati.getNome();
        cognome = dati.getCognome();
        nick = dati.getNick();
        pass = dati.getPassword();
        mail = dati.getMail();
        giorno = dati.getGiorno();
        mese = dati.getMese();
        anno = dati.getAnno();
        data = anno+"-"+mese+"-"+giorno;

        qry = "select credenziali.Username "
                + "from credenziali "
                + "where credenziali.Username = '"+nick+"'";
        risultato = db.interrogazione(qry);

        try{
            if(risultato.next()){
                System.out.println("Già presente");
                dati.setFeedBack(false);
            }else {
                qry = "insert into campi_yellow.credenziali (ID, Username, Password) "
                        + "values (NULL,'"+nick+"','"+pass+"');";
                db.inserimento(qry);
                qry = "select credenziali.ID "
                        + "from credenziali "
                        + "order by credenziali.id desc limit 1";
                risultato = db.interrogazione(qry);
                risultato.next();
                var = risultato.getString("ID" + "");
                qry = "insert into campi_yellow.utente (ID, Nome, Cognome, E_mail, Data_di_nascita) "
                        + "values ('"+var+"','"+nome+"','"+cognome+"','"+mail+"','"+data+"');";
                db.inserimento(qry);
                qry = "insert into campi_yellow.giocatore (ID) values ('"+var+"') ;";
                db.inserimento(qry); 
                System.out.println("Registrazione effettuata con successo");
                dati.setFeedBack(true);    
            }
        }catch (SQLException e){
            System.out.println("Variabile non aquisita");
        }
    }
    
    /**
     * Metodo che serve per il login del client, se l'utente sbaglia uno dei 2
     * campi allora il serve glie lo comunica.
     */
    public static void login(ConnDB db, Aquisizioni dati){
        String username, password;
        
        username = dati.getNick();
        password = dati.getPassword();
        qry = "select credenziali.* "
                + "from credenziali "
                + "where credenziali.Username = '"+username+"' "
                + "and credenziali.Password = '"+password+"'";
        risultato = db.interrogazione(qry);
        try{
            if(risultato.next()){
                dati.setFeedBack(true);
                dati.setGlobalId(risultato.getString("ID"));                
            } else {
                dati.setFeedBack(false);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("Login effettuato con successo");
    }

    /**
     * Metodo che serve a riempire la tabella delle prenotazioni del client
     */
    public static void riempiTabPrenotazioni(Aquisizioni dati, ConnDB db){
        ElementiTabellaPrenotazioni obj;
        String temp,giornata,stato,cliente;
        ResultSet admin = null;
                
        qry = "select prenota.* from giornata , prenota where giornata.Data_giornata = '"+dati.getAnno()+"' and prenota.Campo = "+dati.getIdCampo()+" and giornata.ID = prenota.Giornata";
        risultato = db.interrogazione(qry);
        try{
            if(risultato.next()){
                
                giornata = risultato.getString("Giornata");
                qry = "select calendario.ID, calendario.Fascia, prenota.Giornata, concat (Utente.Nome, \" \", Utente.Cognome) as Utente from calendario left join prenota on (calendario.ID = prenota.Calendario and prenota.Campo = '"+dati.getIdCampo()+"' and prenota.Giornata = '"+giornata+"') left join utente on (prenota.Utente = utente.ID) ORDER BY calendario.ID ASC";      
                risultato = db.interrogazione(qry);
                while(risultato.next()){
                    if(risultato.getString("Giornata") == null) {
                        temp = "libero";
                        stato = "-";
                        cliente = "-";
                    }
                    else {
                        temp = "Occupato";
                        if(risultato.getString("Utente").equals("admin admin")) {
                            stato = "Varie";
                            cliente = "Privato";
                        }
                        else {
                            stato = "Amichevole";
                            cliente = risultato.getString("Utente");
                        }
                        
                    }
                    obj = new ElementiTabellaPrenotazioni(risultato.getString("Fascia"),temp,cliente,stato);
                    dati.addArray(obj);
                }
            } else {
                qry = "select calendario.Fascia from calendario";
                risultato = db.interrogazione(qry);
                while(risultato.next()){
                    obj = new ElementiTabellaPrenotazioni(risultato.getString("Fascia"),"libero","-","-");
                    dati.addArray(obj);
                }     
            }
        } catch (SQLException e){
            e.printStackTrace();
        }    
    }
    
    /**
     * Metodo che serve per coonfermare la prenotazione di un utente una volta
     * scelta la fascia oraria del giorno selezionato nel campo desiderato
     */
    public static void confermaPrenotazione(Aquisizioni dati, ConnDB db){
        String fascia = null, data = null, idUtente;
        int idCampo;
        
        idUtente = dati.getGlobalID();
        idCampo = dati.getIdCampo();       
        try {
            qry = "select calendario.ID from calendario where calendario.Fascia = '"+dati.getFasciaOraria()+"'";
            risultato = db.interrogazione(qry);
            if(risultato.next()){
                fascia = risultato.getString("ID");
            }
            while(data == null){
                qry = "select giornata.Id from giornata "
                        + "where giornata.Data_giornata = '"+dati.getAnno()+"'";
                risultato = db.interrogazione(qry);
                if(risultato.next()){
                    data = risultato.getString("ID");
                } else {
                    qry = "INSERT INTO `campi_yellow`.`giornata` (`ID`, `Data_giornata`) "
                            + "VALUES (NULL, '"+dati.getAnno()+"');";
                    db.inserimento(qry);
                }
            }
            qry ="SELECT * FROM `prenota` "
                    + "WHERE prenota.Campo = '"+idCampo+"' "
                    + "and prenota.Giornata = '"+data+"' "
                    + "and prenota.Calendario = '"+fascia+"'";
            risultato = db.interrogazione(qry);
            if(risultato.next()) dati.setFeedBack(false);
            else {
                qry = "INSERT INTO `campi_yellow`.`prenota` (`Utente`, `Campo`, `Giornata`, `Calendario`) VALUES ('"+idUtente+"', '"+idCampo+"','"+data+"' , '"+fascia+"');";
                db.inserimento(qry);
                dati.setFeedBack(true);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Metodo che riempe la tabella dei tornei per visualizzarli nelle intarfacce
     * del client
     */
    public static void riempiTabTornei(Aquisizioni dati, ConnDB db){
        
        ElementiTabellaTornei obj;
        qry = "select torneo.Nome, torneo.Data_inizio, tipo_torneo.Nome as Tipo_Torneo "
                + "from torneo, tipo_torneo "
                + "where torneo.stato = '"+dati.getNumOperazione()+"' "
                + "and torneo.Tipo = tipo_torneo.ID";
        risultato = db.interrogazione(qry);
        try {
            while(risultato.next()){
               obj = new ElementiTabellaTornei(risultato.getString("Nome"), risultato.getString("Data_inizio"),risultato.getString("Tipo_Torneo"));
               dati.addArrayTornei(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Metodo che serve a riempire la combobox dei tornei nell'interfaccia di 
     * iscrizione di una squadra a un torneo
     */
    public static void riempiComboboxTornei(Aquisizioni dati, ConnDB db){
        qry = "select torneo.Nome, torneo.Data_inizio "
                + "from torneo "
                + "where torneo.stato = '1' "
                + "and torneo.Iscrizioni = '1'";
        risultato = db.interrogazione(qry);
        try {
            while(risultato.next()){
               
               dati.addArrayCombobox(risultato.getString("Nome"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Metodo che serve a riempire la combobox delle squadra di cui si fa parte
     * nell'interfaccia di iscrizione di una squadra a un torneo
     */
    public static void riempiComboboxSquadra(Aquisizioni dati, ConnDB db){
        
        qry = "select squadra.Nome "
                + "from squadra, detiene "
                + "where detiene.Giocatore = '"+dati.getGlobalID()+"' "
                + "and squadra.ID = detiene.Squadra";
        risultato = db.interrogazione(qry);
        try {
            while(risultato.next()){
               
               dati.addArrayCombobox(risultato.getString("Nome"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Metodo che serve a riempire la tabella dove si vedono tutte le squadre di 
     * cui di fa parte
     */
    public static void riempiTabellaMiaSquadra(Aquisizioni dati, ConnDB db){
        
        ElementiTabellaMiaSquadra obj;
        qry = "select squadra.Nome, squadra.Capitano "
                + "from squadra, detiene "
                + "where detiene.Giocatore = '"+dati.getGlobalID()+"' "
                + "and squadra.ID = detiene.Squadra";
        risultato = db.interrogazione(qry);
        try {
            while(risultato.next()){
                obj = new ElementiTabellaMiaSquadra(risultato.getString("Nome"),risultato.getString("Capitano"));
                dati.addArrayMiaSquadra(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    /**
     * Metodo che serve a confermare l'iscrizione di una squadra a un torneo
     */
    public static void confermaIscrizioneTorneo(Aquisizioni dati, ConnDB db){
        String idSquadra = null, idTorneo = null, idClassifica = null;
        int numero_iscritti = 0, maxIscrizione = 0;

        try {
            qry = "select squadra.ID from squadra where squadra.Nome = '"+dati.getNomeSquadra()+"'";
            risultato = db.interrogazione(qry);
            if(risultato.next()) idSquadra = risultato.getString("ID");
            
            qry = "SELECT torneo.ID from torneo WHERE torneo.Nome = '"+dati.getNomeTorneo()+"'";
            risultato = db.interrogazione(qry);
            if(risultato.next()) idTorneo = risultato.getString("ID");
            
            qry = "select torneo.Massimo_iscrizioni from torneo where torneo.ID = '"+idTorneo+"'";
            risultato = db.interrogazione(qry);
            
            if(risultato.next()) maxIscrizione = Integer.parseInt(risultato.getString("Massimo_iscrizioni"));
            
            qry = "select count(iscritta.Squadra) as Numero from iscritta where iscritta.Torneo = '"+idTorneo+"'";
            risultato = db.interrogazione(qry);
            if(risultato.next()) numero_iscritti = Integer.parseInt(risultato.getString("Numero"));
            
            if(numero_iscritti < maxIscrizione){

                qry = "INSERT INTO `campi_yellow`.`classifica` (`ID`, `Punti`, `Goal_fatti`, `Goal_subiti`) VALUES (NULL, 0, 0, 0);";
                db.inserimento(qry);

                qry = "select classifica.ID from classifica order by classifica.ID DESC LIMIT 1";
                risultato = db.interrogazione(qry);
                if(risultato.next()) idClassifica = risultato.getString("ID");

                qry = "INSERT INTO campi_yellow.iscritta (Squadra, Torneo, Classifica) VALUES ('"+idSquadra+"', '"+idTorneo+"', '"+idClassifica+"');";
                db.inserimento(qry); 
            } else {
                qry = "UPDATE campi_yellow.torneo SET Iscrizioni = '0' WHERE torneo.ID = '"+idTorneo+"';";
                db.inserimento(qry);
            }
            qry = "select * from iscritta where iscritta.Torneo = '"+idTorneo+"' and iscritta.Squadra = '"+idSquadra+"'"; 
            risultato = db.interrogazione(qry);
            if(risultato.next())
                dati.setFeedBack(true);
            else
                dati.setFeedBack(false);
            
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo che sere a inserire una nuova squadra quando la si crea
     */
    public static void inserisciSquadra(Aquisizioni dati, ConnDB db){
        String nomeSquadra, capitano, divisa, idSquadra = null;
        int idGiocatore;
        nomeSquadra = dati.getNomeSquadra();
        capitano = dati.getNome();
        divisa = dati.getDivisa();
        idGiocatore = dati.getNumOperazione();
        
        try{
            qry = "insert into campi_yellow.squadra (ID, Nome, Capitano, Colore_divisa) "
                    + "VALUES (NULL, '"+nomeSquadra+"', '"+capitano+"', '"+divisa+"')";
            db.inserimento(qry);
            
            qry = "select squadra.ID "
                    + "from squadra "
                    + "order by squadra.ID DESC LIMIT 1";
            risultato = db.interrogazione(qry);
            
            if(risultato.next()) idSquadra = risultato.getString("ID");
            qry = "INSERT INTO campi_yellow.detiene (Squadra, Giocatore) "
                    + "VALUES ('"+idSquadra+"', '"+idGiocatore+"');";
            db.inserimento(qry);
            
            qry = "select detiene.* "
                    + "om detiene where detiene.Squadra = '"+idSquadra+"' "
                    + "and detiene.Giocatore = '"+idGiocatore+"'";
            risultato = db.interrogazione(qry);
            
            if(risultato.next()) dati.setFeedBack(true);
            else dati.setFeedBack(false);
            
            dati.setNomeSquadra(idSquadra);
        } catch (SQLException e){
            e.printStackTrace();
        }   
    }
    
    /**
     * Metodo che al momento della creazione della squadra controlla se il nome
     * già esiste all'interno del database
     */
    public static void controlloNomeSquadra(Aquisizioni dati, ConnDB db){
        String nomeSquadra;
        
        nomeSquadra = dati.getGlobalID();
        qry = "select * from squadra where squadra.Nome = '"+nomeSquadra+"'";
        risultato = db.interrogazione(qry);
        try {
            if(risultato.next()) dati.setFeedBack(false);
            else dati.setFeedBack(true);
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    /**
     * Metodo che serve a riempire la tabella dove si visualizzano i membri di
     * una determinata squadra nella pagina di modifica della squadra
     */
    public static void riempiTabellaMembriSquadra(Aquisizioni dati, ConnDB db){

        qry = "select concat(utente.Nome,\" \",utente.Cognome) as Giocatore "
                + "from utente, detiene, giocatore "
                + "where detiene.Squadra = '"+dati.getGlobalID()+"' "
                + "and detiene.Giocatore = giocatore.ID "
                + "and giocatore.ID = utente.ID";
        risultato = db.interrogazione(qry);
        try {
            while(risultato.next()){
                dati.addArrayComponentiSquadra(new ElementiTabellaComponentiSquadra(risultato.getString("Giocatore")));
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo che serve a ricercare all'interno del database il nome del giocatore
     * inserendo nella textfield della pagina di ricerca il nome o una parte di esso
     * per cercare i giocarori
     */
    public static void ricercaGiocatore(Aquisizioni dati, ConnDB db){
        String subString;
        
        subString = dati.getObj().getSubString();
        qry = "SELECT credenziali.Username, concat(utente.Nome,\" \",utente.Cognome) as Giocatore "
                + "FROM credenziali, utente "
                + "WHERE credenziali.Username LIKE '%"+subString+"%' "
                + "AND credenziali.ID = utente.ID";
        risultato = db.interrogazione(qry);
        try {
            while(risultato.next()){
                dati.addArrayTabRicerca(new ElementiTabellaRicercaGiocatori(risultato.getString("Username"), risultato.getString("Giocatore")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo che serve a inviare l'invito di un utente ad un altro per partecipare
     * alla squadra
     */
    public static void invioRichiesta(Aquisizioni dati, ConnDB db){
        String idGiocatore = "", nomeSquadra = "";

        qry = "UPDATE campi_yellow.credenziali "
                + "SET Avviso = 1 "
                + "WHERE credenziali.Username = '"+dati.getRichiesta().getNick()+"'";
        db.inserimento(qry);
        
        qry = "select credenziali.ID "
                + "from credenziali where "
                + "credenziali.Username = '"+dati.getRichiesta().getNick()+"'";
        risultato = db.interrogazione(qry);
        
        try {
            if(risultato.next()) idGiocatore = risultato.getString("ID");
            qry = "select squadra.Nome from squadra where squadra.ID = '"+dati.getRichiesta().getSquadra()+"'";
            risultato = db.interrogazione(qry);
            if(risultato.next()) nomeSquadra = risultato.getString("Nome");
            qry = "INSERT INTO campi_yellow.notifica (ID, Utente, Squadra) VALUES (NULL, '"+idGiocatore+"', '"+nomeSquadra+"');";
            db.inserimento(qry);
            qry = "INSERT INTO campi_yellow.detiene (Squadra, Giocatore) VALUES ('"+dati.getRichiesta().getSquadra()+"', '"+idGiocatore+"');";
            db.inserimento(qry);
            
            qry = "select detiene.* from detiene where detiene.Squadra = '"+dati.getRichiesta().getSquadra()+"' and detiene.Giocatore = '"+idGiocatore+"'";
            risultato = db.interrogazione(qry);
            
            if(risultato.next()) dati.setFeedBack(true);
            else dati.setFeedBack(false);
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo che si attiva quando un utente effettua il login e serve a contorllare
     * se l'utente loggato ha un invito da parte di una squadra
     */
    public static void controllaNotifiche(Aquisizioni dati, ConnDB db){
        
        String nomeSquadra = "";
        String avviso = "";
        
        qry = "select notifica.Squadra, credenziali.Avviso "
                + "from credenziali, notifica "
                + "where notifica.Utente = credenziali.ID and credenziali.ID = '"+dati.getGlobalID()+"'";
        risultato = db.interrogazione(qry);
        try {
            if(risultato.next()) {
                System.out.println("avviso ");
                avviso = risultato.getString("Avviso");
                nomeSquadra = risultato.getString("Squadra");
                if(avviso.equals("1")) {
                    dati.setFeedBack(true);
                    dati.setNomeSquadra(nomeSquadra);
                }
            } else dati.setFeedBack(false);   
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    /**
     * Metodo che serve ad aggiornare la tabella delle notifiche nel caso in cui
     * l'utente invitato decide di non voler partecipare alla squadra
     */
    public static void aggiornaTabellaNotificheNo(Aquisizioni dati, ConnDB db){
        
        aggiornaTabellaNotifiche(dati,db);
        qry = "delete from detiene "
                + "where detiene.Giocatore = '"+dati.getAggiornamento().getIdUtente()+"'";
        db.inserimento(qry);
        
    }
    
    /**
     * Metodo che serve ad aggiornare la tabella delle notifiche nel caso in cui
     * l'utente decide di voler partecipare alla squadra
     */
    public static void aggiornaTabellaNotifiche(Aquisizioni dati, ConnDB db){
        
        qry = "UPDATE campi_yellow.credenziali "
                + "SET Avviso = 0 "
                + "WHERE credenziali.ID = '"+dati.getAggiornamento().getIdUtente()+"'";
        db.inserimento(qry);
        qry = "DELETE FROM notifica "
                + "WHERE notifica.Utente = '"+dati.getAggiornamento().getIdUtente()+"' and notifica.Squadra = '"+dati.getAggiornamento().getNomeSquadra()+"'";
        db.inserimento(qry);
    }
    
    /**
     * Metodo che quando richiesto restituisce l'id della squadra selezionata
     */
    public static void restituisciIdSquadra(Aquisizioni dati, ConnDB db){
        
        qry = "select squadra.ID "
                + "from squadra "
                + "where squadra.Nome = '"+dati.getGlobalID()+"'";
        risultato = db.interrogazione(qry);
        try {
            if(risultato.next()) dati.setGlobalId(risultato.getString("ID"));
            
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo che serve a visualizzare le informazioni di profilo di un utente 
     * quando logga
     */
    public static void riempiInfo(Aquisizioni dati, ConnDB db){
        
        qry = "SELECT utente.Nome, utente.Cognome, utente.E_mail, utente.Data_di_nascita, credenziali.Username "
                + "from credenziali, utente "
                + "where utente.ID = '"+dati.getGlobalID()+"' "
                + "and credenziali.ID = utente.ID";
        risultato = db.interrogazione(qry);
        try {
            while(risultato.next()){
                dati.setNome(risultato.getString("Nome"));
                dati.setCognome(risultato.getString("Cognome"));
                dati.setUsername(risultato.getString("Username"));
                dati.setEmail(risultato.getString("E_mail"));
                dati.setData(risultato.getString("Data_di_nascita"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo che serve a eliminare da uan determinata squadra un giocatore 
     * selezionato nella tabella dei membri della squadra nella pagina di 
     * eliminazione dei giocatori
     */
    public static void eliminaGiocatore(Aquisizioni dati, ConnDB db){
        
        String idUtente = null;
        
        qry = "select utente.ID from utente WHERE utente.Nome = '"+dati.getRichiesta().getNick()+"'";
        risultato = db.interrogazione(qry);
        try {
            if(risultato.next()) idUtente = risultato.getString("ID");
            qry = "delete from detiene where detiene.Squadra = '"+dati.getRichiesta().getSquadra()+"' and detiene.Giocatore = '"+idUtente+"'";
            db.inserimento(qry);
            qry = "select * from detiene where detiene.Squadra = '"+dati.getRichiesta().getSquadra()+"' and detiene.Giocatore = '"+idUtente+"'";
            risultato = db.interrogazione(qry);
            if(risultato.next()) {
                dati.setFeedBack(false);
                dati.setGlobalId(idUtente);
            }
            else dati.setFeedBack(true);
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo che quando richiesto restituisce l'ID del tipo di un torneo
     */
    public static void restituisciIdTipoTorneo(Aquisizioni dati, ConnDB db){
        
        qry = "select tipo_torneo.ID from torneo, tipo_torneo where torneo.Nome = '"+dati.getGlobalID()+"' and torneo.Tipo = tipo_torneo.ID";
        risultato = db.interrogazione(qry);
        try {
            if(risultato.next()) dati.setGlobalId(risultato.getString("ID"));
            
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo che setta la label del tipo di torneo nella pagina dove mostra
     * le informazioni sul torneo
     */
    public static void settaLabelTipoTorneo(Aquisizioni dati, ConnDB db){
        System.out.println("funziona "+dati.getGlobalID());
        qry = "select tipo_torneo.Nome from tipo_torneo where tipo_torneo.ID = '"+dati.getGlobalID()+"'";
        risultato = db.interrogazione(qry);
        try {
            if(risultato.next()) dati.setGlobalId(risultato.getString("Nome"));
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo che serve a riempire la tabella della classifica del torneo di tipo
     * campionato
     */
    public static void riempiTabellaClassificaTorneo(Aquisizioni dati, ConnDB db){
        qry = "select squadra.Nome, classifica.Punti, classifica.Goal_fatti, classifica.Goal_subiti " +
                "from squadra, classifica, iscritta, torneo " +
                "where torneo.Nome = '"+dati.getGlobalID()+"' " +
                "and torneo.ID = iscritta.Torneo " +
                "and iscritta.Squadra = squadra.ID " +
                "and iscritta.Classifica = classifica.ID "+
                "order by classifica.Punti desc";
        
        risultato = db.interrogazione(qry);
        try {
            while(risultato.next())
                dati.addArrayClassificaTorneo(new ElementiTabellaClassificaTorneo(risultato.getString("Nome"),risultato.getString("Punti"), risultato.getString("Goal_fatti"), risultato.getString("Goal_subiti")));
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo che serve a riempire le tabelle di classifica dei marcatori, dei 
     * portieri, dei cartellini gialli o rossi a seconda della variabile i. 
     * Sempre a seconda questa variabile, la query cambia a seconda del tipo 
     * di classifica.
     */
    public static void riempiTabelleClassifica(Aquisizioni dati, ConnDB db){
        String var, condition, ordine = "desc";
        int i = dati.getDatiRiempimentoTabellaClassifiche().getI();
        if(i == 1) var = "Goal_Fatti";
        else if(i == 2) {
            var = "Goal_subiti";
            ordine = "asc";
        }
        else if(i == 3) var = "Cart_gialli";
        else var = "Cart_rossi";
        
        if(i > 2) condition = "";
        else condition =  "and "+var+" > 0\n";
        
        
        qry = "select utente.Nome, sum("+var+") as "+var+"\n" +
            "from utente, statistiche, giocatore, possiede, partita, partita_torneo, tabellone, torneo, squadra, detiene, iscritta\n" +
            "where torneo.Nome = '"+dati.getDatiRiempimentoTabellaClassifiche().getNomeTorneo()+"'\n" +
            "and torneo.ID = tabellone.Torneo\n" +
            "and tabellone.ID = partita_torneo.Tabellone\n" +
            "and partita_torneo.ID = partita.ID\n" +
            "and partita.ID = possiede.Partita\n" +
            "and possiede.Giocatore = giocatore.ID\n" +
            "and giocatore.ID = utente.ID\n" +
            "and possiede.Statistiche = statistiche.ID\n" +
            "and giocatore.ID = detiene.Giocatore\n" +
            "and detiene.Squadra = squadra.ID\n" +
            "and squadra.ID = iscritta.Squadra\n" +
            "and iscritta.Torneo = torneo.ID\n" +
            condition +
            "group by utente.Nome\n" +
            "order by "+var+" "+ordine;
        
        risultato = db.interrogazione(qry);
        try {
            while(risultato.next()){
                if(i == 1) dati.addArrayClassificaMarcatori(new ElementiTabellaClassificaMarcatori(risultato.getString("Nome"), risultato.getString(var)));
                else if(i == 2) dati.addArrayClassificaPortiere(new ElementiTabellaClassificaMigliorPortiere(risultato.getString("Nome"), risultato.getString(var)));
                else if(i == 3) dati.addArrayCartelliniGialli(new ElementiTabellaCartelliniGialli(risultato.getString("Nome"), risultato.getString(var)));
                else dati.addArrayCartelliniRossi(new ElementiTabellaCartelliniRossi(risultato.getString("Nome"), risultato.getString(var)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo che riempe il tabellone di un determinato torneo
     */
    public static void riempiTabellone(Aquisizioni dati, ConnDB db){
        qry = "select casa.Nome as Casa, trasferta.Nome as Trasferta, tabellone.Data_partita, tabellone.Ris\n" +
            "from tabellone, torneo, \n" +
            "(select squadra.Nome, squadra.ID from tabellone, squadra, torneo\n" +
            "	WHERe torneo.nome ='"+dati.getGlobalID()+"'\n" +
            "	and torneo.ID = tabellone.Torneo\n" +
            "	and squadra.ID = tabellone.Casa) casa,\n" +
            "(select squadra.Nome, squadra.ID from tabellone, squadra, torneo\n" +
            "	WHERe torneo.nome = '"+dati.getGlobalID()+"'\n" +
            "	and torneo.ID = tabellone.Torneo\n" +
            "	and squadra.ID = tabellone.Trasferta) trasferta\n" +
            "where torneo.nome = '"+dati.getGlobalID()+"'\n" +
            "and torneo.ID = tabellone.Torneo\n" +
            "and tabellone.casa = casa.ID\n" +
            "and tabellone.trasferta = trasferta.ID\n" +
            "group by tabellone.Data_partita\n" +
            "order by tabellone.Data_partita asc";
        
        risultato = db.interrogazione(qry);
        try {
            while(risultato.next()) dati.addArrayTabellone(new ElementiTabellone(risultato.getString("Casa"),risultato.getString("Trasferta"),risultato.getString("Data_partita"),risultato.getString("Ris")));
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo che serve a inizializzare le prime squadre del tabellone del torneo
     * a eliminazione diretta
     */
    public static void riempiLabelTabellone(Aquisizioni dati, ConnDB db){
        qry = "select squadra.Nome \n" +
            "from squadra, iscritta, torneo \n" +
            "where torneo.Nome = '"+dati.getGlobalID()+"' \n" +
            "and torneo.ID = iscritta.Torneo \n" +
            "and iscritta.Squadra = squadra.ID";
        
        risultato = db.interrogazione(qry);
        
        try {
            while(risultato.next())
                dati.addArrayRiempiLabelTabellone(risultato.getString("Nome"));
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo che serve a settare le squadre che vincono lo scontro nelle fasi
     * del torneo a eliminazione diretta, questo metodo restituisce il risultato
     * della partita avendo come input le squadre
     */
    public static void passaggioFasi(Aquisizioni dati, ConnDB db){
        String sq1 = null,sq2 = null;
        qry = "select squadra.ID from squadra where squadra.Nome = '"+dati.getPasso().getSq1()+"'";
        risultato = db.interrogazione(qry);
        try {
            qry = "select squadra.ID from squadra where squadra.Nome = '"+dati.getPasso().getSq1()+"'";
            risultato = db.interrogazione(qry);
            if(risultato.next()) sq1 = risultato.getString("ID");
            
            qry = "select squadra.ID from squadra where squadra.Nome = '"+dati.getPasso().getSq2()+"'";
            risultato = db.interrogazione(qry);
            if(risultato.next()) sq2 = risultato.getString("ID");
            
            qry = "select tabellone.Ris as Risultato\n" +
                "from tabellone, torneo, \n" +
                "(select squadra.Nome, squadra.ID from tabellone, squadra, torneo\n" +
                "	WHERe torneo.nome = '"+dati.getPasso().getNomeTorneo()+"'\n" +
                "	and torneo.ID = tabellone.Torneo\n" +
                "	and squadra.ID = tabellone.Casa) casa,\n" +
                "(select squadra.Nome, squadra.ID from tabellone, squadra, torneo\n" +
                "	WHERe torneo.nome = '"+dati.getPasso().getNomeTorneo()+"'\n" +
                "	and torneo.ID = tabellone.Torneo\n" +
                "	and squadra.ID = tabellone.Trasferta) trasferta\n" +
                "where torneo.nome = '"+dati.getPasso().getNomeTorneo()+"'\n" +
                "and torneo.ID = tabellone.Torneo\n" +
                "and tabellone.casa = casa.ID\n" +
                "and tabellone.trasferta = trasferta.ID\n" +
                "and tabellone.casa = '"+sq1+"'\n" +
                "and tabellone.Trasferta = '"+sq2+"'\n" +
                "group by tabellone.Data_partita\n" +
                "order by tabellone.Data_partita asc";
            risultato = db.interrogazione(qry);
            if(risultato.next()) {
                dati.setRisPasso(risultato.getString("Risultato"));
                dati.setFeedBack(true);
            } else
                dati.setFeedBack(false);
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Da qui iniziano i metodi di gestione dell'amministratore
     */
    
    public static void loginAdmin(Aquisizioni dati, ConnDB db){
        
        qry = "select amministratore.ID\n" +
            "from amministratore, credenziali, utente\n" +
            "where credenziali.Username = '"+dati.getNick()+"'\n" +
            "and credenziali.Password = '"+dati.getPassword()+"'\n" +
            "and credenziali.ID = utente.ID\n" +
            "and utente.ID = amministratore.ID";
        
        risultato = db.interrogazione(qry);
        try{
            if(risultato.next()){
                dati.setFeedBack(true);
                dati.setGlobalId(risultato.getString("ID"));                
            } else {
                dati.setFeedBack(false);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("Login effettuato con successo");
    }
    
    public static void eliminazioneFasciaOraria(Aquisizioni dati, ConnDB db){
        String idFascia = "", idData = "";
        
        try {
            qry = "select giornata.ID as Giorno, calendario.ID as Fascia\n" +
                "FROM giornata , calendario\n" +
                "where giornata.Data_giornata = '"+dati.getAnno()+"'\n" +
                "and calendario.Fascia = '"+dati.getFasciaOraria()+"'";
            risultato = db.interrogazione(qry);
            while(risultato.next()){
                idData = risultato.getString("Giorno");
                idFascia = risultato.getString("Fascia");
            }
        
            qry = "delete from prenota \n" +
                "where prenota.Campo = '"+dati.getIdCampo()+"' \n" +
                "and prenota.Giornata = '"+idData+"'\n" +
                "and prenota.Calendario = '"+idFascia+"'";

            db.inserimento(qry);

            qry = "select prenota.*\n" +
                "from prenota\n" +
                "where prenota.Campo = '"+dati.getIdCampo()+"' \n" +
                "and prenota.Giornata = '"+idData+"'\n" +
                "and prenota.Calendario = '"+idFascia+"'";
            risultato = db.interrogazione(qry);
        
            if(risultato.next()) dati.setFeedBack(false);
            else dati.setFeedBack(true);
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void chiudiIscrizioni(Aquisizioni dati, ConnDB db){
        qry = "UPDATE campi_yellow.torneo\n" +
            "SET Iscrizioni = '0'\n" +
            "WHERE torneo.Nome = '"+dati.getGlobalID()+"';";
        db.inserimento(qry);
        
        qry = "select torneo.Iscrizioni \n" +
            "from torneo \n" +
            "where torneo.Nome = '"+dati.getGlobalID()+"'";
        risultato = db.interrogazione(qry);
        
        try {
            if(risultato.next())
                if(risultato.getString("Iscrizioni").equals("0"))
                    dati.setFeedBack(true);
                else
                    dati.setFeedBack(false);
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void riempiComboboxSquadreAdmin(Aquisizioni dati, ConnDB db){
        System.out.println("nome: "+dati.getGlobalID());
        qry = "SELECT squadra.Nome \n" +
            "FROM squadra, iscritta, torneo \n" +
            "WHERE torneo.Nome = '"+dati.getGlobalID()+"' \n" +
            "and torneo.ID = iscritta.Torneo \n" +
            "and squadra.ID = iscritta.Squadra";
        
        risultato = db.interrogazione(qry);
        try {
            while(risultato.next())
                dati.addArrayCombobox(risultato.getString("Nome"));
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void riempiComboboxFascieOrarie(Aquisizioni dati, ConnDB db){
        qry = "select calendario.Fascia from calendario";
        
        risultato = db.interrogazione(qry);
        try {
            while(risultato.next())
                dati.addArrayCombobox(risultato.getString("Fascia"));
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void riempiComboboxCampi(Aquisizioni dati, ConnDB db){
        qry = "select campo.Tipo_campo from campo";
        
        risultato = db.interrogazione(qry);
        try {
            while(risultato.next())
                dati.addArrayCombobox(risultato.getString("Tipo_campo"));
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void aggiungiPartitaAdmin(Aquisizioni dati, ConnDB db){
        String casa = null, trasferta = null, idTorneo = null, idTabellone = null, idPartita = null;
        confermaPrenotazione(new Aquisizioni(dati.getElementiAggiungiPartita().getFascia(),dati.getElementiAggiungiPartita().getData(),dati.getElementiAggiungiPartita().getIdUtente(),dati.getElementiAggiungiPartita().getIdCampo(), "confermaPrenotazione"),db);
                
        try {
            qry = "INSERT INTO `campi_yellow`.`partita` (`ID`, `Campo`) \n" +
                "VALUES (NULL, '"+dati.getElementiAggiungiPartita().getIdCampo()+"');";
            db.inserimento(qry);

            qry = "select casa.ID as Casa, trasferta.ID as Trasferta, torneo.ID as Torneo\n" +
                "from (select squadra.ID \n" +
                "      from squadra\n" +
                "      where squadra.Nome = '"+dati.getElementiAggiungiPartita().getCasa()+"') casa,\n" +
                "      (select squadra.ID \n" +
                "      from squadra\n" +
                "      where squadra.Nome = '"+dati.getElementiAggiungiPartita().getTrasferta()+"') trasferta, torneo\n" +
                "where torneo.Nome = '"+dati.getElementiAggiungiPartita().getNomeTorneo()+"'";
            risultato = db.interrogazione(qry);

            if(risultato.next()){
                casa = risultato.getString("Casa");
                trasferta = risultato.getString("Trasferta");
                idTorneo = risultato.getString("Torneo");
            }
            
            qry = "INSERT INTO campi_yellow.tabellone (ID, Casa, Trasferta, Torneo, Data_partita, Ris) \n" +
                "VALUES (NULL, '"+casa+"', '"+trasferta+"', '"+idTorneo+"', '"+dati.getElementiAggiungiPartita().getData()+"', NULL);";
            db.inserimento(qry);
            
            qry = "SELECT MAX(tabellone.ID) as Tabellone, MAX(partita.ID) as Partita \n" +
                "from tabellone ,partita\n" +
                "ORDER BY tabellone.ID desc limit 1";
            risultato = db.interrogazione(qry);
            if(risultato.next()){
                idTabellone = risultato.getString("Tabellone");
                idPartita = risultato.getString("Partita");
            }
            
            qry = "INSERT INTO `campi_yellow`.`partita_torneo` (`ID`, `Tabellone`, `Goal_casa`, `Goal_trasferta`) \n" +
                "VALUES ('"+idPartita+"', '"+idTabellone+"', NULL, NULL);";
            db.inserimento(qry);
            
            qry = "select partita_torneo.* \n" +
                "from partita_torneo\n" +
                "where partita_torneo.ID = 7\n" +
                "and partita_torneo.Tabellone = 15";
            risultato = db.interrogazione(qry);
            
            if(risultato.next()) dati.setFeedBack(true);
            else dati.setFeedBack(false);
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
            dati.setFeedBack(false);
        }
    }
    
    public static void riempiComboboxTipoTorneoAdmin(Aquisizioni dati, ConnDB db){
        qry = "select tipo_torneo.Nome\n" +
            "from tipo_torneo";
        
        risultato = db.interrogazione(qry);
        try {
            while(risultato.next())
                dati.addArrayCombobox(risultato.getString("Nome"));
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void creaTorneo(Aquisizioni dati, ConnDB db){
        String idTipoTorneo = null;
        
        qry = "select tipo_torneo.ID \n" +
            "from tipo_torneo\n" +
            "WHERE tipo_torneo.Nome = '"+dati.getElementiCreaTorneo().getIdTipoTorneo()+"'";
        risultato = db.interrogazione(qry);
        try {
            if(risultato.next()) idTipoTorneo = risultato.getString("ID");
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        qry = "select torneo.* from torneo "
            + "where torneo.Nome = '"+dati.getElementiCreaTorneo().getNomeTorneo()+"' "
            + "and torneo.Data_inizio = '"+dati.getElementiCreaTorneo().getData()+"'";
        
        risultato = db.interrogazione(qry);
        try {
            if(!risultato.next()){
                qry = "INSERT INTO `campi_yellow`.`torneo` (`ID`, `Nome`, `Descrizione`, `Data_inizio`, `Tipo`, `stato`, `Iscrizioni`, `Massimo_iscrizioni`) \n" +
                        "VALUES (NULL, '"+dati.getElementiCreaTorneo().getNomeTorneo()+"', '"+dati.getElementiCreaTorneo().getDescrizione()+"', '"+dati.getElementiCreaTorneo().getData()+"', '"+idTipoTorneo+"', '1', '1', '"+dati.getElementiCreaTorneo().getMaxIscrizioni()+"');";
                db.inserimento(qry);
                dati.setFeedBack(true);
            } else dati.setFeedBack(false);
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void riempiTabellaCalendarioStatistiche(Aquisizioni dati, ConnDB db){
        
        qry = "select tabellone.ID as IDPartita, casa.Nome as Casa, trasferta.Nome as Trasferta, tabellone.Data_partita, tabellone.Ris\n" +
            "from tabellone, torneo, \n" +
            "(select squadra.Nome, squadra.ID from tabellone, squadra, torneo\n" +
            "	WHERe torneo.nome ='"+dati.getGlobalID()+"'\n" +
            "	and torneo.ID = tabellone.Torneo\n" +
            "	and squadra.ID = tabellone.Casa) casa,\n" +
            "(select squadra.Nome, squadra.ID from tabellone, squadra, torneo\n" +
            "	WHERe torneo.nome = '"+dati.getGlobalID()+"'\n" +
            "	and torneo.ID = tabellone.Torneo\n" +
            "	and squadra.ID = tabellone.Trasferta) trasferta\n" +
            "where torneo.nome = '"+dati.getGlobalID()+"'\n" +
            "and torneo.ID = tabellone.Torneo\n" +
            "and tabellone.casa = casa.ID\n" +
            "and tabellone.trasferta = trasferta.ID\n" +
            "group by tabellone.Data_partita\n" +
            "order by tabellone.Data_partita asc";
        
        risultato = db.interrogazione(qry);
        try {
            while(risultato.next()){ 
                dati.addArrayElementiTabellaCalendarioStatistiche(new ElementiTabellaCalendarioStatistiche(risultato.getString("IDPartita"),risultato.getString("Casa"),risultato.getString("Trasferta"),risultato.getString("Data_partita"),risultato.getString("Ris")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void controlloPartitaSvolta(Aquisizioni dati, ConnDB db){
        System.out.println(dati.getGlobalID());
        qry = "select tabellone.Ris from tabellone where tabellone.ID = '"+dati.getGlobalID()+"'";
        risultato = db.interrogazione(qry);
        try {
            if(risultato.next())
                if(risultato.getString("Ris") == null){
                    dati.setFeedBack(false);
                } else 
                    dati.setFeedBack(true);
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void restituisciNomiSquadreStatistiche(Aquisizioni dati, ConnDB db){
        String idCasa = null, idTrasferta = null; 

        try {
            qry = "select tabellone.Casa, tabellone.Trasferta \n" +
            "FROM tabellone \n" +
            "WHERE tabellone.ID = '"+dati.getGlobalID()+"'";
            risultato = db.interrogazione(qry);
            
            if(risultato.next()){
                idCasa = risultato.getString("Casa");
                idTrasferta = risultato.getString("Trasferta");
            }
            dati.setNomiSquadreStatisticheTorneo(new ElementiNomiSquadreStatisticheTorneo(restituisciNomi(idCasa,db),restituisciNomi(idTrasferta,db)));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static String restituisciNomi(String id, ConnDB db) throws SQLException{
        qry = "SELECT squadra.Nome\n" +
            "from squadra\n" +
            "where squadra.ID = '"+id+"'";
        risultato = db.interrogazione(qry);
        
        if(risultato.next()) return risultato.getString("Nome");
        return null;
        
    }
    
    public static void restituisciNomiGiocatoriSquadreStatistiche(Aquisizioni dati, ConnDB db){
        String idCasa = null, idTrasferta = null;
        ArrayList<String> giocatoriCasa, giocatoriTrasferta;
        try {
            qry = "select tabellone.Casa, tabellone.Trasferta \n" +
            "FROM tabellone \n" +
            "WHERE tabellone.ID = '"+dati.getGlobalID()+"'";
            risultato = db.interrogazione(qry);
            
            if(risultato.next()){
                idCasa = risultato.getString("Casa");
                idTrasferta = risultato.getString("Trasferta");
            }
            
            dati.setArrayNomiGiocatoriCasa(nomiGiocatori(idCasa,db));
            dati.setArrayNomiGiocatoriTrasferta(nomiGiocatori(idTrasferta,db));
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ArrayList<String> nomiGiocatori(String id, ConnDB db){
        ArrayList<String> array = new ArrayList<String>();
        
        qry = "select credenziali.Username as Giocatore\n" +
            "from detiene, giocatore, utente, credenziali\n" +
            "where detiene.Squadra = '"+id+"'\n" +
            "and detiene.Giocatore = giocatore.ID\n" +
            "and giocatore.ID = utente.ID\n" +
            "and utente.ID = credenziali.ID";
        risultato = db.interrogazione(qry);
        
        try {
            while(risultato.next()){
                array.add(risultato.getString("Giocatore"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return array;
    }
    
    public static void inserisciStatistiche(Aquisizioni dati, ConnDB db){
        String risultatoCasa, risultatoTrasferta, idPartita = null, nome, cognome, split[], idUtente = null, idStatistica = null;
        ElementiStatisticheGiocatore x;
        int i;
        boolean inseritiCasa = false, inseritiTrasferta = false;
        x = (ElementiStatisticheGiocatore) dati.getGiocatoreCasa().get(0);
        risultatoTrasferta = x.getRisultato();
        x = (ElementiStatisticheGiocatore) dati.getGiocatoreTrasferta().get(0);
        risultatoCasa = x.getRisultato();
        
        qry = "select partita_torneo.ID \n" +
            "from partita_torneo\n" +
            "where partita_torneo.Tabellone = '"+x.getIdTabellone()+"'";
        
        risultato = db.interrogazione(qry);
        try {
            if(risultato.next()) idPartita = risultato.getString("ID");
            
            qry = "UPDATE `campi_yellow`.`partita_torneo` SET `Goal_casa` = '"+risultatoCasa+"' WHERE `partita_torneo`.`ID` = '"+idPartita+"';";
            db.inserimento(qry);
            qry = "UPDATE `campi_yellow`.`partita_torneo` SET `Goal_trasferta` = '"+risultatoTrasferta+"' WHERE `partita_torneo`.`ID` = '"+idPartita+"';";
            db.inserimento(qry);
            risultatoCasa = risultatoCasa+"-"+risultatoTrasferta;
            
            qry = "UPDATE `campi_yellow`.`tabellone` SET `Ris` = '"+risultatoCasa+"' WHERE `tabellone`.`ID` = '"+x.getIdTabellone()+"';";
            db.inserimento(qry);
            
            inseritiCasa = verificaInserimento(idPartita,dati.getGiocatoreCasa(),db);
            inseritiTrasferta = verificaInserimento(idPartita,dati.getGiocatoreTrasferta(),db);
            
            if(inseritiCasa && inseritiTrasferta)
                dati.setFeedBack(true);
            else 
                dati.setFeedBack(false);
            
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean verificaInserimento(String idPartita,ArrayList array, ConnDB db){
        String  idUtente = null, idStatistica = null;
        ElementiStatisticheGiocatore x;
        boolean stato = false;
        int i;
         try {
            for(i = 0; i < array.size(); i++){
                x = (ElementiStatisticheGiocatore) array.get(i);
                
                /*split = x.getNomeGiocatore().split(" ");
                nome = split[0];
                cognome = split[1];*/
                
                qry = "select giocatore.ID\n" +
                    "from giocatore, squadra, detiene, tabellone, torneo, utente, credenziali\n" +
                    "where credenziali.Username =  '"+x.getNomeGiocatore()+"'\n" +
                    "and credenziali.ID = utente.ID\n" +
                    "and utente.ID = giocatore.ID\n" +
                    "and detiene.Giocatore = giocatore.ID\n" +
                    "and detiene.Squadra = squadra.ID\n" +
                    "and squadra.Nome = '"+x.getNomeSquadra()+"'\n" +
                    "and tabellone.ID = '"+x.getIdTabellone()+"'\n" +
                    "and tabellone.Torneo = torneo.ID";
                System.out.println(qry);
                
                risultato = db.interrogazione(qry);
                if(risultato.next()) idUtente = risultato.getString("ID");
                
                qry = "INSERT INTO `campi_yellow`.`statistiche` (`ID`, `Goal_fatti`, `Goal_subiti`, `Cart_gialli`, `Cart_rossi`) \n" +
                        "VALUES (NULL, '"+x.getGoalFatti()+"', '"+x.getGoalSubiti()+"', '"+x.getGialli()+"', '"+x.getRossi()+"');";
                db.inserimento(qry);
                
                qry = "select statistiche.ID \n" +
                    "from statistiche\n" +
                    "ORDER by statistiche.ID desc\n" +
                    "limit 1";
                risultato = db.interrogazione(qry);
                if(risultato.next()) idStatistica = risultato.getString("ID");
                
                qry = "INSERT INTO `campi_yellow`.`possiede` (`Giocatore`, `Statistiche`, `Partita`) \n" +
                    "VALUES ('"+idUtente+"', '"+idStatistica+"', '"+idPartita+"');";
                db.inserimento(qry);
                
                qry = "select * from possiede where possiede.Giocatore = '"+idUtente+"' and possiede.Statistiche = '"+idStatistica+"' and possiede.Partita = '"+idPartita+"'";
                risultato = db.interrogazione(qry);
                if(risultato.next()) stato = true;
                else stato = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operazioni.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        array.clear();
        return stato;
    }
}
