package Client.Home;

import Client.Aquisizioni;
import Client.Client;
import Client.ElementiAggiornaNotifiche;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Classe che controlla la notifica di un utente quando riceve una richiesta
 * di partecipazione a una squadra da parte di un altro utente
 */
public class ControllerNotifica implements Initializable{
    @FXML Label Squadra = new Label();
    @FXML Button si = new Button();
    @FXML Button no = new Button(); 
    
    Aquisizioni ritorno;
    
    /**
     * Metodo che si attiva quando si preme il pulsante si, questo metodo indica
     * che l'utente accetta di partecipare alla squadra e invia i dati che servono per
     * confermare la sua partecipazione alla squadra e aggiorna le tabelle del 
     * database dicendo che l'utente che è stato aggiunta non ha più notifiche
     */
    @FXML
    public void si(ActionEvent event){
        System.out.println("si");
        ritorno = Client.trasmissione(new Aquisizioni(new ElementiAggiornaNotifiche(Controller.globalID,ControllerPagine.globalNomeSquadra),"aggiornaTabellaNotifiche"));
        try {
            CaricaPagine.avvisoStage.close();
        } catch (Exception ex) {
            Logger.getLogger(ControllerAvviso.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Metodo simile a quello precedente che però comunica al server che l'utente
     * non vuole essere membro della squadra e quindi manda la richiesta di 
     * cancellazione dalla squadra
     */
    @FXML
    public void no(ActionEvent event){
        System.out.println("no");
        ritorno = Client.trasmissione(new Aquisizioni(new ElementiAggiornaNotifiche(Controller.globalID,ControllerPagine.globalNomeSquadra),"aggiornaTabellaNotificheNo"));
        try {
            CaricaPagine.avvisoStage.close();
        } catch (Exception ex) {
            Logger.getLogger(ControllerAvviso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(ControllerPagine.globalNomeSquadra);
        Squadra.setVisible(true);
        Squadra.setText(ControllerPagine.globalNomeSquadra);
    }
    
}
