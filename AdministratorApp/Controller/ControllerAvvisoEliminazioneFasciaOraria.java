package Amministratore.Controller;

import Client.Aquisizioni;
import Amministratore.Amministratore;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControllerAvvisoEliminazioneFasciaOraria implements Initializable{

    @FXML Button si = new Button();
    @FXML Button no = new Button();
    @FXML Button ok = new Button();
    @FXML Label info = new Label();
    
    private Aquisizioni ritorno;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    @FXML
    public void si(ActionEvent event){

        ritorno = Amministratore.trasmissione(new Aquisizioni(ControllerPrenotazione.globalFascia,ControllerPrenotazione.globalData,"admin",ControllerSceltaCampo.var,"eliminazioneFasciaOraria"));
        si.setVisible(false);
        no.setVisible(false);
        ok.setVisible(true);
        if(ritorno.getFeedBack()) info.setText("Eliminazione prenotazione effettuata con successo!");
        else info.setText("Eliminazione prenotazione non effettuata!");
    }
    
    @FXML
    public void no(ActionEvent event){
        try {
            CaricaPagine.avvisoStage.close();
            new CaricaPagine().startSceltaAzioneFasciaOraria();
        } catch (Exception ex) {
            Logger.getLogger(ControllerAvvisoEliminazioneFasciaOraria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void ok(ActionEvent event){
        try {
            CaricaPagine.avvisoStage.close();
            new CaricaPagine().startPrenotazione();
        } catch (Exception ex) {
            Logger.getLogger(ControllerAvvisoEliminazioneFasciaOraria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
