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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Classe che controlla la finestra di creazione e salvataggio della squadra.
 * Questa pagina fa scegliere il nome, il colore della divisa e il nome del capitano.
 */
public class ControllerCreaSquadra implements Initializable{
    Aquisizioni ritorno;
    public static String squadra, idSquadra;
    
    @FXML TextField nomeSquadra;
    @FXML TextField coloreDivisa;
    @FXML TextField capitano = new TextField();
    @FXML Label nomePresente = new Label();

    /**
     * Questo metodono permettere di tornare indietro alla home quando si clicca
     * il pulsante annulla
     */
    @FXML
    public void annulla(ActionEvent event){
        System.out.println("premuto annulla");
        try {
            new CaricaPagine().startHome();
        } catch (Exception ex) {
            Logger.getLogger(ControllerCreaSquadra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Questo metodo permette di salvare i dati immessi nelle textfield della
     * pagine di salvataggio della squadra permettendo cosi successivamente
     * di scegliere i giocatori da agginungere a quest'ultima
     */
    @FXML
    public void salvaNome(ActionEvent event){
        String divisa, cap;
        
        squadra = nomeSquadra.getText();
        divisa = coloreDivisa.getText();
        cap = capitano.getText();
        ritorno = Client.trasmissione(new Aquisizioni("controlloNomeSquadra",squadra));
        
        if(ritorno.getFeedBack()){
            ritorno = Client.trasmissione(new Aquisizioni(squadra, cap, divisa, "inserisciSquadra", Integer.parseInt(Controller.globalID)));
            if(ritorno.getFeedBack()){
                System.out.println("è andato tutto bene");
                try {
                    idSquadra = ritorno.getNomeSquadra();
                    new CaricaPagine().startCreaSquadra();
                    new CaricaPagine().startAvvisoCreazioneSquadra();
                } catch (Exception ex) {
                    Logger.getLogger(ControllerCreaSquadra.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("è andata male");
            }
            System.out.println("salvanome");
        } else {
            nomePresente.setVisible(true);
        }
    }
    
    /**
     * Metodo che si attiva cliccando il pulsante ok nella finestra di avviso
     * di avvenuto salvataggio del nome della squadra
     */
    @FXML
    public void ok(ActionEvent event){
        try {
            CaricaPagine.avvisoStage.close();
        } catch (Exception ex) {
            Logger.getLogger(ControllerAvviso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomePresente.setVisible(false);
    }
    
}
