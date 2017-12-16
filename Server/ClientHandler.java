package Server;


import Client.Aquisizioni;
import java.io.IOException;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Classe che estende la classe Thread, questa classe, ogni volta che un client
 * si connette al server, crea un thread che gestisce il client creato. Quindi il
 * server thread per ogni client che si connette
 */
public class ClientHandler extends Thread
{

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    Aquisizioni dati;

    public ClientHandler(Socket socket)
    {
        this.socket = socket;
    }

    /**
     * Override del metodo run della classe thread, in questo metodo si inizializzano
     * gli stream di input e output che servonon per comunicare con il client.
     * Ogni volta che il server riceve dal client un oggetto, da quell'oggetto
     * legge l'attributo operazione che è una stringa che il server legge per sapere
     * quale operazione deve compiere mediante uno switch case.
     */
    @Override
    public void run()
    {
        
        String operazione; 
        try
        {
            in = new ObjectInputStream(socket.getInputStream());
            out =  new ObjectOutputStream(socket.getOutputStream());
            while(true)
            {
                System.out.println("aspetto l'aquisizione");
                dati = (Aquisizioni) in.readObject();
                operazione = dati.getOperazione();
                
                switch(operazione)
                {
                    case "Registrazione": Operazioni.registrazione(dati,Server.db);
                        break;

                    case "login": Operazioni.login(Server.db,dati);
                        break;
                        
                    case "tabellaPrenotazioni": Operazioni.riempiTabPrenotazioni(dati,Server.db);
                        break;
                        
                    case "confermaPrenotazione": Operazioni.confermaPrenotazione(dati, Server.db);
                        break;
                        
                    case "tabellaTornei": Operazioni.riempiTabTornei(dati,Server.db);
                        break;
                        
                    case "riempiComboboxTornei": Operazioni.riempiComboboxTornei(dati, Server.db);
                        break;
                        
                    case "riempiComboboxSquadra": Operazioni.riempiComboboxSquadra(dati, Server.db);
                        break;
                        
                    case "riempiTabellaMiaSquadra": Operazioni.riempiTabellaMiaSquadra(dati, Server.db);
                        break;
                        
                    case "confermaIscrizioneTorneo": Operazioni.confermaIscrizioneTorneo(dati, Server.db);
                        break;
                        
                    case "inserisciSquadra": Operazioni.inserisciSquadra(dati, Server.db);
                        break;
                        
                    case "controlloNomeSquadra": Operazioni.controlloNomeSquadra(dati, Server.db);
                        break;
                        
                    case "riempiTabellaMembriSquadra": Operazioni.riempiTabellaMembriSquadra(dati, Server.db);
                        break;
                        
                    case "ricercaGiocatore": Operazioni.ricercaGiocatore(dati, Server.db);
                        break;
                        
                    case "invioRichiesta": Operazioni.invioRichiesta(dati, Server.db);           
                        break;
                        
                    case "controllaNotifiche": Operazioni.controllaNotifiche(dati, Server.db);
                        break;
                        
                    case "aggiornaTabellaNotifiche": Operazioni.aggiornaTabellaNotifiche(dati,Server.db);
                        break;
                        
                    case "aggiornaTabellaNotificheNo": Operazioni.aggiornaTabellaNotificheNo(dati,Server.db);
                        break;
                        
                    case "restituisciIdSquadra": Operazioni.restituisciIdSquadra(dati, Server.db);
                        break;
                        
                    case "riempiInfo": Operazioni.riempiInfo(dati, Server.db);
                        break;
                        
                    case "eliminaGiocatore": Operazioni.eliminaGiocatore(dati, Server.db);
                        break;
                        
                    case "restituisciIdTipoTorneo": Operazioni.restituisciIdTipoTorneo(dati, Server.db);
                        break;
                        
                    case "settaLabelTipoTorneo": Operazioni.settaLabelTipoTorneo(dati, Server.db);
                        break;
                        
                    case "riempiTabellaClassificaTorneo": Operazioni.riempiTabellaClassificaTorneo(dati, Server.db);
                        break;
                        
                    case "riempiTabelleClassifica": Operazioni.riempiTabelleClassifica(dati, Server.db);
                        break;
                        
                    case "riempiTabellone": Operazioni.riempiTabellone(dati, Server.db);
                        break;
                        
                    case "riempiLabelTabellone": Operazioni.riempiLabelTabellone(dati, Server.db);
                        break;
                        
                    case "passaggioFasi": Operazioni.passaggioFasi(dati, Server.db);
                        break;
                        
                    case "loginAdmin": Operazioni.loginAdmin(dati, Server.db);
                        break;
                        
                    case "eliminazioneFasciaOraria": Operazioni.eliminazioneFasciaOraria(dati, Server.db);
                        break;
                        
                    case "chiudiIscrizioni": Operazioni.chiudiIscrizioni(dati, Server.db);
                        break;
                        
                    case "riempiComboboxSquadreAdmin": Operazioni.riempiComboboxSquadreAdmin(dati, Server.db);
                        break;
                        
                    case "riempiComboboxFascieOrarie": Operazioni.riempiComboboxFascieOrarie(dati, Server.db);
                        break;
                        
                    case "riempiComboboxCampi": Operazioni.riempiComboboxCampi(dati, Server.db);
                        break;
                        
                    case "aggiungiPartitaAdmin": Operazioni.aggiungiPartitaAdmin(dati, Server.db);
                        break;
                        
                    case "riempiComboboxTipoTorneoAdmin": Operazioni.riempiComboboxTipoTorneoAdmin(dati, Server.db);
                        break;
                        
                    case "creaTorneo": Operazioni.creaTorneo(dati, Server.db);
                        break;
                        
                    case "riempiTabellaCalendarioStatistiche": Operazioni.riempiTabellaCalendarioStatistiche(dati, Server.db);
                        break;
                        
                    case "controlloPartitaSvolta": Operazioni.controlloPartitaSvolta(dati, Server.db);
                        break;
                        
                    case "restituisciNomiSquadreStatistiche": Operazioni.restituisciNomiSquadreStatistiche(dati, Server.db);
                        break;
                        
                    case "restituisciNomiGiocatoriSquadreStatistiche": Operazioni.restituisciNomiGiocatoriSquadreStatistiche(dati, Server.db);
                        break;
                        
                    case "inserisciStatistiche": Operazioni.inserisciStatistiche(dati, Server.db);
                        break;
                }                  
                out.writeObject(dati);
                out.flush();
            }     
        }
        catch (IOException e)
        {
            System.out.println("Errore di ricezione");
            rimuoviClient(socket);
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println("Errore in object prova = in.readObject()");
        }
    }
   
    /**
     * Questo metodo permette di rimuovere il client da una lista presente nella
     * classe server dove ci sono tutti i client che sono connessi.
     * Questa lista serve come riferimento per il server per pingare ogni client
     * in modo da sapere quando i client si scollegano per mancanza di segnale
     * o perchè chiudono l'applicazione. In entrambi i casi, vengono rimossi
     * dalla lista mediante questo metodo
     */
    public void rimuoviClient(Socket sock)
    {
        int i = 0;
        String ip;
        ObjClient obj;
        ip =  sock.getInetAddress().toString().replaceAll("/", "");
        System.out.println("ip da eliminare dalla lista = " + ip);
        while(i < Server.arrayClient.size())
        {
            obj = (ObjClient) Server.arrayClient.get(i);
            if(obj.getIp().equals(ip))
            {
                obj.chiudiSocket();
                Server.arrayClient.remove(i);
                break;
            }
        }
    }
}
