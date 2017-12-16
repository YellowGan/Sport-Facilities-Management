package Client.Home;

import Client.Aquisizioni;
import Client.Client;
import Client.ElementiPassaggioFasi;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * Classe che controlla il tabellone del torneo a eliminazione diretta. Questa classe
 * ha come attributi tutte le label che servono per visualizzare le vari fasi del
 * torneo
 */
public class ControllerTorneoEliminazione implements Initializable{
    
    @FXML Label nomeTorneo = new Label();
    @FXML Label tipoTorneo = new Label();
    @FXML Label squadra1 = new Label();
    @FXML Label squadra2 = new Label();
    @FXML Label squadra3 = new Label();
    @FXML Label squadra4 = new Label();
    @FXML Label squadra5 = new Label();
    @FXML Label squadra6 = new Label();
    @FXML Label squadra7 = new Label();
    @FXML Label squadra8 = new Label();
    @FXML Label squadra9 = new Label();
    @FXML Label squadra10 = new Label();
    @FXML Label squadra11= new Label();
    @FXML Label squadra12 = new Label();
    @FXML Label squadra13 = new Label();
    @FXML Label squadra14 = new Label();
    @FXML Label squadra15 = new Label();
    @FXML Label squadra16 = new Label();
    @FXML Label squadraV12 = new Label();
    @FXML Label squadraV34 = new Label();
    @FXML Label squadraV56 = new Label();
    @FXML Label squadraV78 = new Label();
    @FXML Label squadraV14 = new Label();
    @FXML Label squadraV58 = new Label();
    @FXML Label squadraV18 = new Label();
    @FXML Label squadraV916 = new Label();
    @FXML Label squadraV912 = new Label();
    @FXML Label squadraV1316 = new Label();
    @FXML Label squadraV910 = new Label();
    @FXML Label squadraV1112 = new Label();
    @FXML Label squadraV1314 = new Label();
    @FXML Label squadraV1516 = new Label();
    @FXML Label squadraVincente = new Label();
    
    private ArrayList<Label> sinistra = new ArrayList<Label>();
    private ArrayList<Label> destra = new ArrayList<Label>();
    private ArrayList<Label> sinistra1 = new ArrayList<Label>();
    private ArrayList<Label> destra1 = new ArrayList<Label>();
    private ArrayList<Label> sinistra2 = new ArrayList<Label>();
    private ArrayList<Label> destra2 = new ArrayList<Label>();
    private ArrayList<Label> sinistra3 = new ArrayList<Label>();
    private ArrayList<Label> destra3 = new ArrayList<Label>();
    
    private ArrayList<String> squadre = new ArrayList<String>();
    private Aquisizioni ritorno;
    
    
    /**
     * Questo metodo serve a inizializzare tutte le label del torneo, chiamando 
     * tutti i metodi che servono a settare le label a seconda di chi a vinto 
     * lo scontro in ogni fase
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        riempiLabel();
        creaLabelArray();
        riempiTabellone();
        riempiTabelloneFase(sinistra, destra, sinistra1, destra1);
        riempiTabelloneFase(sinistra1, destra1, sinistra2, destra2);
        riempiTabelloneFase(sinistra2, destra2, sinistra3, destra3);
        vincitore(sinistra3,destra3);
    }
    
    @FXML
    public void tornaHome(MouseEvent event){
        try {
            new CaricaPagine().startHome();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    /**
     * Metodo che si attiva quando si clicca sul pulsante calendario e apre la pagina
     * dove si visualizzano tutte le partite, le date e l'eventuale risultato di 
     * ogni paritta del torneo
     */
    @FXML
    public void tastoTabellone(ActionEvent event){
        try {
            new CaricaPagine().startTabellone();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void riempiLabel(){
        ritorno = Client.trasmissione(new Aquisizioni("settaLabelTipoTorneo",Integer.toString(CaricaPagine.globalIdTipoTorneo)));
        tipoTorneo.setText(ritorno.getGlobalID());
        nomeTorneo.setText(ControllerPagine.globalTorneo);
    }
    
    /**
     * Questo metodo serve per assegnare ogni label a un determinato arraylist a
     * seconda di quale fase del torneo si tratti
     */
    public void creaLabelArray(){
        sinistra.add(this.squadra1);
        sinistra.add(this.squadra2);
        sinistra.add(this.squadra3);
        sinistra.add(this.squadra4);
        sinistra.add(this.squadra5);
        sinistra.add(this.squadra6);
        sinistra.add(this.squadra7);
        sinistra.add(this.squadra8);
        destra.add(this.squadra9);
        destra.add(this.squadra10);
        destra.add(this.squadra11);
        destra.add(this.squadra12);
        destra.add(this.squadra13);
        destra.add(this.squadra14);
        destra.add(this.squadra15);
        destra.add(this.squadra16);
        sinistra1.add(this.squadraV12);
        sinistra1.add(this.squadraV34);
        sinistra1.add(this.squadraV56);
        sinistra1.add(this.squadraV78);
        destra1.add(this.squadraV910);
        destra1.add(this.squadraV1112);
        destra1.add(this.squadraV1314);
        destra1.add(this.squadraV1516);
        sinistra2.add(this.squadraV14);
        sinistra2.add(this.squadraV58);
        destra2.add(this.squadraV912);
        destra2.add(this.squadraV1316);
        sinistra3.add(this.squadraV18);
        destra3.add(this.squadraV916);
        
    }
    
    /**
     * Questo metodo riempe le label della prima fase del torneo
     */
    public void riempiTabellone(){
        int var = 0, i, j = 0, k = 0;
        ritorno = Client.trasmissione(new Aquisizioni("riempiLabelTabellone",ControllerPagine.globalTorneo));
        squadre = ritorno.getArrayRiempiLabelTabellone();
        
        for(i = 0; i < 16; i++){
            if(var == 0){
                sinistra.get(j).setText(squadre.get(i));
                j++;
                var = 1;
            } else {
                destra.get(k).setText(squadre.get(i));
                k++;
                var = 0;
            }
        }
    }
    
    /**
     * Questo metodo riempe le fasi successive alla prima a seconda degli arraylist 
     * che si passano come paramtri
     * 
     * @param sinistra: arraylist della fase corrente della parte sinistra del tabellone;
     * @param destra: arraylist della fase corrente della parte destra del tabellone;
     * @param sinistra1: arraylist della nuova fase della parte sinistra del tabellone;
     * @param destra1 : arraylistt della nuova fase della parte destra del tabellone;
     */
    public void riempiTabelloneFase(ArrayList<Label> sinistra, ArrayList<Label> destra, ArrayList<Label> sinistra1, ArrayList<Label> destra1){
        int i = 0, j = 0, risCasa,risTrasferta;
        String sq1, sq2, ris, split[];
        
        for(i = 0; i < sinistra.size(); i++){
            sq1 = sinistra.get(i).getText();
            sq2 = sinistra.get(++i).getText();
            ritorno = Client.trasmissione(new Aquisizioni(new ElementiPassaggioFasi(sq1,sq2,ControllerPagine.globalTorneo),"passaggioFasi"));
            if(ritorno.getFeedBack()){
                ris = ritorno.getPasso().getRis(); // per prendere il risultato
                split = ris.split("-");
                risCasa = Integer.parseInt(split[0]);
                risTrasferta = Integer.parseInt(split[1]);
                if(risCasa > risTrasferta){
                    sinistra1.get(j).setText(sq1);
                    sinistra.get(i).setTextFill(Color.web("#FF0000"));
                }
                else{
                    sinistra1.get(j).setText(sq2);
                    sinistra.get(i-1).setTextFill(Color.web("#FF0000"));
                }
            }
            j++;
        }
        
        i = 0;
        j = 0;
        
        for(i = 0; i < destra.size(); i++){
            sq1 = destra.get(i).getText();
            sq2 = destra.get(++i).getText();
            ritorno = Client.trasmissione(new Aquisizioni(new ElementiPassaggioFasi(sq1,sq2,ControllerPagine.globalTorneo),"passaggioFasi"));
            if(ritorno.getFeedBack()){
                ris = ritorno.getPasso().getRis(); // per prendere il risultato
                split = ris.split("-");
                risCasa = Integer.parseInt(split[0]);
                risTrasferta = Integer.parseInt(split[1]);
                if(risCasa > risTrasferta){
                    destra1.get(j).setText(sq1);
                    destra.get(i).setTextFill(Color.web("#FF0000"));
                }
                else{
                    destra1.get(j).setText(sq2);
                    destra.get(i-1).setTextFill(Color.web("#FF0000"));
                }
            }
            j++;
        }
    }
    
    /**
     * Metodo che riempe la label del vincitore a seconda della squadra vincente
     * @param sinistra: arraylist dove c'è la squadra finalista della parte sinistra del tabellone;
     * @param destra arraylist dove c'è la squadra finalista della parte destra del tabellone;
     */
    public void vincitore(ArrayList<Label> sinistra, ArrayList<Label> destra){
        ArrayList<Label> finale = new ArrayList<Label>();
        int i = 0, j = 0, risCasa,risTrasferta;
        String sq1, sq2, ris, split[];
        
        finale.add(sinistra.get(0));
        finale.add(destra.get(0));
        
        
        for(i = 0; i < finale.size(); i++){
            sq1 = finale.get(i).getText();
            sq2 = finale.get(++i).getText();
            ritorno = Client.trasmissione(new Aquisizioni(new ElementiPassaggioFasi(sq1,sq2,ControllerPagine.globalTorneo),"passaggioFasi"));
            if(ritorno.getFeedBack()){
                ris = ritorno.getPasso().getRis();
                split = ris.split("-");
                risCasa = Integer.parseInt(split[0]);
                risTrasferta = Integer.parseInt(split[1]);
                if(risCasa > risTrasferta){
                    squadraVincente.setText(sq1);
                    finale.get(i).setTextFill(Color.web("#FF0000"));
                }
                else{
                    squadraVincente.setText(sq2);
                    finale.get(i-1).setTextFill(Color.web("#FF0000"));
                }
            }
            j++;
        }
    }
}
