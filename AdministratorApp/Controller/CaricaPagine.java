package Amministratore.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CaricaPagine {
    
    public static Stage avvisoStage;
    public static int globalIdTipoTorneo;
    
    public void startHome() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("grafica_amministratore/home_amministratore.fxml"));
        Scene scene = new Scene(root);
        Amministratore.Login.stage.setScene(scene);
        Amministratore.Login.stage.setResizable(true);
        Amministratore.Login.stage.show();
    }
    
    public void startSceltaCampo()throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("grafica_amministratore/scelta_campo.fxml"));
        Scene scene = new Scene(root);
        Amministratore.Login.stage.setScene(scene);
        Amministratore.Login.stage.setResizable(true);
        Amministratore.Login.stage.show();
    }
    
    public void startPrenotazione() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("grafica_amministratore/prenotazione.fxml"));
        Scene scene = new Scene(root);
        Amministratore.Login.stage.setScene(scene);
        Amministratore.Login.stage.setResizable(true);
        Amministratore.Login.stage.show();
    }
    
    public void startSceltaAzioneFasciaOraria() throws Exception{
        avvisoStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("grafica_amministratore/scelta_azione_fasciaoraria.fxml"));
        Scene scene = new Scene(root);
        avvisoStage.setScene(scene);
        avvisoStage.setResizable(false);
        avvisoStage.show();
    }
    
    public void startEliminaFasciaOraria() throws Exception{
        avvisoStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("grafica_amministratore/avviso_elimina_fasciaoraria.fxml"));
        Scene scene = new Scene(root);
        avvisoStage.setScene(scene);
        avvisoStage.setResizable(false);
        avvisoStage.show();
    }
    public void startAvvisoPrenotaFasciaoraria() throws Exception{
        avvisoStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("grafica_amministratore/avviso_prenota_fasciaoraria.fxml"));
        Scene scene = new Scene(root);
        avvisoStage.setScene(scene);
        avvisoStage.setResizable(false);
        avvisoStage.show();
    }
    
    public void startVisualizzaTornei() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("grafica_amministratore/visualizza_tornei.fxml"));
        Scene scene = new Scene(root);
        Amministratore.Login.stage.setScene(scene);
        Amministratore.Login.stage.setResizable(true);
        Amministratore.Login.stage.show();
    }
    
    public void startVisualizzaTorneiTerminati() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("grafica_amministratore/visualizza_tornei_terminati.fxml"));
        Scene scene = new Scene(root);
        Amministratore.Login.stage.setScene(scene);
        Amministratore.Login.stage.setResizable(true);
        Amministratore.Login.stage.show();
    }
    
    public void startSceltaAzioneTorneo() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("grafica_amministratore/scelta_azione_torneo.fxml"));
        Scene scene = new Scene(root);
        Amministratore.Login.stage.setScene(scene);
        Amministratore.Login.stage.setResizable(true);
        Amministratore.Login.stage.show();
    }
    
    public void startAvvisoChiudiIscrizioni() throws Exception{
        avvisoStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("grafica_amministratore/avviso_chiudiiscrizioni.fxml"));
        Scene scene = new Scene(root);
        avvisoStage.setScene(scene);
        avvisoStage.setResizable(false);
        avvisoStage.show();
    }
    
    public void startCreaPartita() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("grafica_amministratore/crea_partita.fxml"));
        Scene scene = new Scene(root);
        Amministratore.Login.stage.setScene(scene);
        Amministratore.Login.stage.setResizable(true);
        Amministratore.Login.stage.show();
    }
    
    public void startAvvisoCreaPartitaSi() throws Exception{
        avvisoStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("grafica_amministratore/avviso_creapartita_ok.fxml"));
        Scene scene = new Scene(root);
        avvisoStage.setScene(scene);
        avvisoStage.setResizable(false);
        avvisoStage.show();
    }
    
    public void startCreaTorneo() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("grafica_amministratore/crea_torneo.fxml"));
        Scene scene = new Scene(root);
        Amministratore.Login.stage.setScene(scene);
        Amministratore.Login.stage.setResizable(true);
        Amministratore.Login.stage.show();
    }
    
    public void startAvvisoCreaTorneoSi() throws Exception{
        avvisoStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("grafica_amministratore/avviso_creatorneo_ok.fxml"));
        Scene scene = new Scene(root);
        avvisoStage.setScene(scene);
        avvisoStage.setResizable(false);
        avvisoStage.show();
    }
    
    public void startAvvisoCreaTorneoNo() throws Exception{
        avvisoStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("grafica_amministratore/avviso_creatorneo_errore.fxml"));
        Scene scene = new Scene(root);
        avvisoStage.setScene(scene);
        avvisoStage.setResizable(false);
        avvisoStage.show();
    }
    
    public void startSceltaTorneoStatistica() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("grafica_amministratore/scelta_torneo_statistica.fxml"));
        Scene scene = new Scene(root);
        Amministratore.Login.stage.setScene(scene);
        Amministratore.Login.stage.setResizable(true);
        Amministratore.Login.stage.show();
    }
    
    public void startVisualizzaCalendarioTorneo() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("grafica_amministratore/visualizza_calendario_torneo.fxml"));
        Scene scene = new Scene(root);
        Amministratore.Login.stage.setScene(scene);
        Amministratore.Login.stage.setResizable(true);
        Amministratore.Login.stage.show();
    }
    
    public void startStatisticheTorneo() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("grafica_amministratore/statistiche_torneo.fxml"));
        Scene scene = new Scene(root);
        Amministratore.Login.stage.setScene(scene);
        Amministratore.Login.stage.setResizable(true);
        Amministratore.Login.stage.show();
    }
    
    public void startAvvisoPartitaNonModificabile() throws Exception{
        avvisoStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("grafica_amministratore/avviso_partitanonmodificabile.fxml"));
        Scene scene = new Scene(root);
        avvisoStage.setScene(scene);
        avvisoStage.setResizable(false);
        avvisoStage.show();
    }
}


//avviso_partitanonmodificabile