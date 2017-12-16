package Client.Home;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe dove ci sono al suo interno tutti i metodi per caricare le varie pagine
 * dell'applicaivo
 */
public class CaricaPagine{
    
    public static Stage avvisoStage;
    public static int globalIdTipoTorneo;
    
    /**
     * Metodo per visualizzare l'interfaccia graphics.fxml
     */
    public void startHome() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Graphics/graphics.fxml"));
        Scene scene = new Scene(root);
        Client.Form.stage.setScene(scene);
        Client.Form.stage.setResizable(true);
        Client.Form.stage.show();
    }
    
    /**
     * Metodo per visualizzare l'interfaccia Prenotazione.fxml
     */
    public void startPrenota() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Graphics/Prenotazione.fxml"));
        Scene scene = new Scene(root);
        Client.Form.stage.setScene(scene);
        Client.Form.stage.setResizable(true);
        Client.Form.stage.show();       
    }
    
    /**
     * Metodo per visualizzare l'interfaccia Avviso.fxml
     */
    public void startAvviso() throws Exception{
        avvisoStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Graphics/Avviso.fxml"));
        Scene scene = new Scene(root);
        avvisoStage.setScene(scene);
        avvisoStage.setResizable(false);
        avvisoStage.show();
    }
    
    /**
     * Metodo per visualizzare l'interfaccia tornei_terminati.fxml dove vengono
     * visualizzati i tornei terminati
     */
    public void startTorneiTerminati() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Graphics/tornei_terminati.fxml"));
        Scene scene = new Scene(root);
        Client.Form.stage.setScene(scene);
        Client.Form.stage.setResizable(true);
        Client.Form.stage.show();      
    }
    
    /**
     * Metodo per visualizzare l'interfaccia iscrizione_squadra_torneo.fxml
     */
    public void startIscrizioneSquadra() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Graphics/iscrizione_squadra_torneo.fxml"));
        Scene scene = new Scene(root);
        Client.Form.stage.setScene(scene);
        Client.Form.stage.setResizable(true);
        Client.Form.stage.show();
    }
    
    /**
     * Metodo per visualizzare l'interfaccia avviso_iscrizione_squadra_torneo.fxml
     */
    public void startAvvisoIscrizione()throws Exception{
        avvisoStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Graphics/avviso_iscrizione_squadra_torneo.fxml"));
        Scene scene = new Scene(root);
        avvisoStage.setScene(scene);
        avvisoStage.setResizable(false);
        avvisoStage.show();
    }   
    
    /**
     * Metodo per visualizzare l'interfaccia anomeSquadra.fxml dove viene 
     * salvato il nome, il colore e il nome del capitano della squadra che si
     * sta creando
     */
    public void startNomeSquadra() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Graphics/nomeSquadra.fxml"));
        Scene scene = new Scene(root);
        Client.Form.stage.setScene(scene);
        Client.Form.stage.setResizable(true);
        Client.Form.stage.show();
    }
    
    /**
     * Metodo per visualizzare l'interfaccia creaSquadra.fxml dove vengono
     * visualizzati i vari giocatori che si inseriscono nella squadra e dove
     * vengono anche eliminati
     */
    public void startCreaSquadra() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Graphics/creaSquadra.fxml"));
        Scene scene = new Scene(root);
        Client.Form.stage.setScene(scene);
        Client.Form.stage.setResizable(true);
        Client.Form.stage.show();
    }
    
    /**
     * Metodo per visualizzare l'interfaccia lista_aggiungi_giocatore.fxml
     */
    public void startAggiungiGiocatore() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Graphics/lista_aggiungi_giocatore.fxml"));
        Scene scene = new Scene(root);
        Client.Form.stage.setScene(scene);
        Client.Form.stage.setResizable(true);
        Client.Form.stage.show();
    }
    
    /**
     * Metodo per visualizzare l'interfaccia Avviso_avvenuta_creazione_squadra.fxml
     */
    public void startAvvisoCreazioneSquadra()throws Exception{
        avvisoStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Graphics/Avviso_avvenuta_creazione_squadra.fxml"));
        Scene scene = new Scene(root);
        avvisoStage.setScene(scene);
        avvisoStage.setResizable(false);
        avvisoStage.show();
    }
    
    /**
     * Metodo per visualizzare l'interfaccia Avviso_conferma_richiesta_squadra.fxml
     */
    public void startAvvisoInvioRichiesta()throws Exception{
        avvisoStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Graphics/Avviso_conferma_richiesta_squadra.fxml"));
        Scene scene = new Scene(root);
        avvisoStage.setScene(scene);
        avvisoStage.setResizable(false);
        avvisoStage.show();
    }
    
    /**
     * Metodo per visualizzare l'interfaccia avviso_notifica.fxml
     */
    public void startNotifica()throws Exception{
        avvisoStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Graphics/avviso_notifica.fxml"));
        Scene scene = new Scene(root);
        avvisoStage.setScene(scene);
        avvisoStage.setResizable(false);
        avvisoStage.show();
    }
    
    /**
     * Metodo per visualizzare l'interfaccia eliminazioni_giocatori_squadra.fxml
     * dove vengono eliminati i giocatori di una squadra
     */
    public void startEliminaGiocatori() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Graphics/eliminazioni_giocatori_squadra.fxml"));
        Scene scene = new Scene(root);
        Client.Form.stage.setScene(scene);
        Client.Form.stage.setResizable(true);
        Client.Form.stage.show();
    }
    
    /**
     * Metodo per visualizzare l'interfaccia Avviso_conferma_eliminazione.fxml
     */
    public void startAvvisoConfermaEliminazioneGiocatore()throws Exception{
        avvisoStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Graphics/Avviso_conferma_eliminazione.fxml"));
        Scene scene = new Scene(root);
        avvisoStage.setScene(scene);
        avvisoStage.setResizable(false);
        avvisoStage.show();
    }
    
    /**
     * Metodo per visualizzare l'interfaccia Avviso_salvataggio_squadra.fxml
     */
    public void startAvvisoConfermaSalvataggioSquadra()throws Exception{
        avvisoStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Graphics/Avviso_salvataggio_squadra.fxml"));
        Scene scene = new Scene(root);
        avvisoStage.setScene(scene);
        avvisoStage.setResizable(false);
        avvisoStage.show();
    }   
    
    /**
     * Metodo dove l'applicativo controlla se la pagina da caricare sia relativa
     * al torneo a eliminazione diretta o al campionato
     * @param var: variabile usata per il contorllo
     */
    public void startTorneo(int var) throws Exception{
        globalIdTipoTorneo = var;
        if(var == 1) startTorneoEliminazioneDiretta();
        else if (var == 2) startTorneoCampionato();
    }
    
    /**
     * Metodo per visualizzare l'interfaccia vis_torneo_classifica.fxml dove vengono
     * visualizzate le tabelle che contengono le informazioni riguardante il
     * campionato
     */
    public void startTorneoCampionato() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Graphics/vis_torneo_classifica.fxml"));
        Scene scene = new Scene(root);
        Client.Form.stage.setScene(scene);
        Client.Form.stage.setResizable(true);
        Client.Form.stage.show();       
    }
    
    /**
     * Metodo per visualizzare l'interfaccia vis_torneo_eliminazione.fxml dove 
     * viene visualizzato il tabellone del torneo a eliminazione diretta selezionato
     */
    public void startTorneoEliminazioneDiretta() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Graphics/vis_torneo_eliminazione.fxml"));
        Scene scene = new Scene(root);
        Client.Form.stage.setScene(scene);
        Client.Form.stage.setResizable(true);
        Client.Form.stage.show();
    }
    
    /**
     * Metodo per visualizzare l'interfaccia vis_tabellone_partite.fxml dove
     * viene visualizzato il tabellone dove ci sono tutte le date delle partite
     * del torneo
     */
    public void startTabellone() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Graphics/vis_tabellone_partite.fxml"));
        Scene scene = new Scene(root);
        Client.Form.stage.setScene(scene);
        Client.Form.stage.setResizable(true);
        Client.Form.stage.show();
    }
  
      
}
