package Client;

import java.io.Serializable;

/**
 * Classe che server per riempire la tabella della classifica di un deternminato
 * campionato
 */
public class ElementiTabellaClassificaTorneo implements Serializable{
    private String nomeSquadra;
    private String punti;
    private String goalFatti;
    private String goalSubiti;
    
    public ElementiTabellaClassificaTorneo(){
        this.nomeSquadra = null;
        this.punti = null;
        this.goalFatti = null;
        this.goalSubiti = null;
    }
    
    public ElementiTabellaClassificaTorneo(String nome, String punti, String goalFatti, String goalSubiti){
        this.nomeSquadra = nome;
        this.punti = punti;
        this.goalFatti = goalFatti;
        this.goalSubiti = goalSubiti;
    }

    public String getNomeSquadra() {
        return nomeSquadra;
    }

    public String getPunti() {
        return punti;
    }

    public String getGoalFatti() {
        return goalFatti;
    }

    public String getGoalSubiti() {
        return goalSubiti;
    }
}
