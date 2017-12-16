package Client.Home;

import Client.Aquisizioni;
import Client.Client;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Classe che serve come controller per l'avviso di conferma della prenotazione
 */
public class ControllerAvviso implements Initializable{
    
    private Aquisizioni ritorno;
    
    @FXML Label data;
    @FXML Label fascia;    
    @FXML Label info1;    
    @FXML Label info2;    
    @FXML Label esito;    
    @FXML Button Conferma;
    @FXML Button Annulla;
    
    
    /**
     * 
     * Metodo che viene attivato quando si preme il pulsante Conferma nell'interfaccia di avviso
     * e dove viene visualizzata la label in caso di avvenuta prenotazioneo non.
     */
    @FXML
    public void Conferma(ActionEvent event) throws InterruptedException{
        
        System.out.println("premuto conferma");
        System.out.println("la data: "+ControllerPagine.globalData);
        System.out.println("la fascia: "+ControllerPagine.globalFascia);
        System.out.println(Controller.globalID );
        ritorno = Client.trasmissione(new Aquisizioni(ControllerPagine.globalFascia,ControllerPagine.globalData,Controller.globalID,ControllerPagine.var,"confermaPrenotazione"));
        info1.setVisible(false);
        info2.setVisible(false);
        data.setVisible(false);
        fascia.setVisible(false);
        Annulla.setVisible(false);
        Conferma.setVisible(false);
        if(ritorno.getFeedBack()) {
            esito.setText("La prenotazione è andata a buon fine");
        }
        else esito.setText("Non è possibile prenotare nella fascia selezionata");
    }
    
    /**
     * Metodo che viene attivato quando si preme il pulsante annulla e serve per
     * chiudere la finestra di avviso
     */
    @FXML
    public void Annulla(ActionEvent event){
        try {
            CaricaPagine.avvisoStage.close();
        } catch (Exception ex) {
            Logger.getLogger(ControllerAvviso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo per inizializzare le label all'avvio dell'interfaccia grafica
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data.setText(ControllerPagine.globalData);
        fascia.setText(ControllerPagine.globalFascia);
        data.setVisible(true);
        fascia.setVisible(true);
    }
}
