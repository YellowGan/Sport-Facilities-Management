package Amministratore.Controller;

import Amministratore.Amministratore;
import Client.Aquisizioni;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControllerAvvisoChiudiIscrizioni implements Initializable{

    private Aquisizioni ritorno;
    
    @FXML Button ok = new Button();
    @FXML Button no = new Button();
    @FXML Button si = new Button();
    @FXML Label info = new Label();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    @FXML
    public void no(ActionEvent event){
        try {
            CaricaPagine.avvisoStage.close();
            new CaricaPagine().startVisualizzaTornei();
        } catch (Exception ex) {
            Logger.getLogger(ControllerAvvisoChiudiIscrizioni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void si(ActionEvent event){
        
        ritorno = Amministratore.trasmissione(new Aquisizioni("chiudiIscrizioni",ControllerVisualizzaTornei.globalTorneo));
        ok.setVisible(true);
        no.setVisible(false);
        si.setVisible(false);
        if(ritorno.getFeedBack())
            info.setText("Hai chiuso le iscrizioni con successo");
        else
            info.setText("Non hai chiuso le iscrizioni");
        
    }
    
    
}
