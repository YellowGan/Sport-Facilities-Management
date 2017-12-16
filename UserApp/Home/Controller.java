package Client.Home;

import Client.Aquisizioni;
import Client.Client;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import Client.Home.CaricaPagine;

/**
 * La classe Controller è il controller della pagina iniziale dell'applicativo.
 */
public class Controller implements Initializable{
    
    private Aquisizioni ritorno;
    public static String globalID;
    
    @FXML Label loginErrato;
    @FXML Label campiVuoti;
    @FXML TextField nickLogin;
    @FXML PasswordField passLogin;
    @FXML TextField nomeIscriviti;
    @FXML TextField cognomeIscriviti;
    @FXML TextField nickIscriviti;
    @FXML PasswordField passIscriviti;
    @FXML TextField email;
    @FXML TextField conf_email;
    @FXML ComboBox gg;
    @FXML ComboBox mm;
    @FXML ComboBox aa;

    /**
     * Metodo che viene attivato quando viene premuto il pulsante "Iscriviti",
     * una volta fatto manda la richiesta al client mandando tutti i dati che
     * servono al server per registrare il cliente nel database
     * @param event 
     */
    @FXML
    public void Iscriviti(ActionEvent event){
        String nome, cognome, nick, pass, mail, conf, giorno, anno, mese, operazione;

        System.out.println("Ho premuto iscriviti");
        nome = nomeIscriviti.getText();
        cognome = cognomeIscriviti.getText();
        nick = nickIscriviti.getText();
        pass = passIscriviti.getText();
        mail = email.getText();
        conf = conf_email.getText();
        giorno = (String) gg.getValue();
        mese = (String) mm.getValue();
        anno = (String) aa.getValue();
        operazione = "Registrazione";
        
        if (!nome.equals("") && !cognome.equals("") && !nick.equals("") && !pass.equals("") && !mail.equals("") && !conf.equals("") && !giorno.equals("") && !mese.equals("") && !anno.equals("") && mail.equals(conf)){
            ritorno = Client.trasmissione(new Aquisizioni(nome, cognome, nick, pass, mail, conf, giorno, mese, anno, operazione));
            if(ritorno.getFeedBack()){
                campiVuoti.setText("");
                nomeIscriviti.setText("");
                cognomeIscriviti.setText("");
                nickIscriviti.setText("");
                passIscriviti.setText("");
                email.setText("");
                conf_email.setText("");
                gg.setValue("GG");
                mm.setValue("MM");
                aa.setValue("AA");
                campiVuoti.setText("Hai effettuato l'iscrizione con successo.\nPuoi effettuare il login"); 
                loginErrato.setText("");
            } else {
                campiVuoti.setText("Il nickname è già usato da un altro utente.\nInserisci un altro nick name.");
                loginErrato.setText("");
            }
        }else{
            campiVuoti.setText("Non sono stati riempiti tutti i campi o non\n hai confermato l'email.\n"+"Per favore inserire tutti i dati correttamente. \n");
            loginErrato.setText("");
        }        
    }
    
    /**
     * Metodo che viene attivato quando viene premuto il pulsante "Login", una 
     * volta attivato il client manda la richiesta di login al server
     */
    @FXML
    public void Login(ActionEvent event){
        String nome, password, operazione = "login";
        nome = nickLogin.getText();
        password = passLogin.getText();
        System.out.println("Ho premuto login");
        if(!nome.equals("") && !password.equals("")){
            ritorno = Client.trasmissione(new Aquisizioni(nome, password, operazione));
            if(ritorno.getFeedBack()){
                try {
                    globalID = ritorno.getGlobalID();
                    new CaricaPagine().startHome();
                    new ControllerPagine().controllaNotifiche();
                    
                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }                
            } else {
                loginErrato.setText("L'username o la password non sono stati\n inseriti correttamente");
                campiVuoti.setText("");
            }
        } else {
            loginErrato.setText("Non hai inserito l'username o la password correttamente.");
            campiVuoti.setText("");
        }
    }
    
    /**
     * Metodo initializa che serve per inizializzare le variabili presenti nell'
     * interfaccia all'avvio
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
