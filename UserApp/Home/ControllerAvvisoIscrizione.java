package Client.Home;
import Client.Client;
import Client.Aquisizioni;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

/**
 * Classe che serve come controller alla finestra di avviso di iscrizione
 * questa finestra comunica se l'utente è riuscito a iscriversi al torneo con
 * successo
 */
public class ControllerAvvisoIscrizione implements Initializable {

    private Aquisizioni ritorno;
    
    @FXML Label squadra;
    @FXML Label torneo;
    @FXML Label info1;
    @FXML Label info2;
    @FXML Label info3;
    @FXML Button conferma = new Button();
    @FXML Button annulla = new Button();
    @FXML Label confermaIscrizione = new Label();
    @FXML Button ok = new Button();
    
    /**
     * Metodo che si attiva quando si preme il pulsante conferma nella fienstra
     * di avviso, questo metodo manda la richiesta di iscrizione al torneo
     * e visualizza se l'iscrizione è avvenuta con successo o non
     */
    @FXML
    public void conferma(ActionEvent event){
        System.out.println("Premuto conferma");
        ritorno = Client.trasmissione(new Aquisizioni("confermaIscrizioneTorneo",ControllerIscrizioneTorneo.globalSquadra,ControllerIscrizioneTorneo.globalTorneo,false));
        squadra.setVisible(false);
        torneo.setVisible(false);
        conferma.setVisible(false);
        annulla.setVisible(false);
        info1.setVisible(false);
        info2.setVisible(false);
        info3.setVisible(false);
        ok.setVisible(true);
        if(ritorno.getFeedBack())
            confermaIscrizione.setText("L'iscrizione è avvenuta con successo");
        else
            confermaIscrizione.setText("Non è stato possibile iscrivere la squadra"); 
    }
    
    /**
     * Metodo che serve per chiudere la finestra di avviso e si attva quando si
     * preme i relativo tasto annulla
     */
    @FXML
    public void annulla(ActionEvent event){
        System.out.println("Premuto annulla");
        try {
            CaricaPagine.avvisoStage.close();
        } catch (Exception ex) {
            Logger.getLogger(ControllerAvviso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        squadra.setText(ControllerIscrizioneTorneo.globalSquadra);
        torneo.setText(ControllerIscrizioneTorneo.globalTorneo);
    }
}
