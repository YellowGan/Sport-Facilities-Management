package Client;

import java.io.Serializable;

public class ElementiStatisticheGiocatore implements Serializable{
    private String idTabellone;
    private String nomeGiocatore;
    private String goalFatti;
    private String goalSubiti;
    private String gialli;
    private String rossi;
    private String nomeSquadra;
    private String risultato;
    
    public ElementiStatisticheGiocatore(){
        this.idTabellone = null;
        this.nomeGiocatore = null;
        this.goalFatti = null;
        this.goalSubiti = null;
        this.gialli = null;
        this.rossi = null;
        this.nomeSquadra = null;
        this.risultato = null;
    }

    public ElementiStatisticheGiocatore(String idTabellone, String nomeGiocatore, String goalFatti, String goalSubiti, String gialli, String rossi, String nomeSquadra, String risultato) {
        this.idTabellone = idTabellone;
        this.nomeGiocatore = nomeGiocatore;
        this.goalFatti = goalFatti;
        this.goalSubiti = goalSubiti;
        this.gialli = gialli;
        this.rossi = rossi;
        this.nomeSquadra = nomeSquadra;
        this.risultato = risultato;
    }

    public String getIdTabellone() {
        return idTabellone;
    }

    public String getNomeGiocatore() {
        return nomeGiocatore;
    }

    public String getGoalFatti() {
        return goalFatti;
    }

    public String getGoalSubiti() {
        return goalSubiti;
    }

    public String getGialli() {
        return gialli;
    }

    public String getRossi() {
        return rossi;
    }

    public String getNomeSquadra() {
        return nomeSquadra;
    }

    public String getRisultato() {
        return risultato;
    }       
}
