package Client;

import java.io.Serializable;

/**
 * Classe usata per la richiesta di partecipazione alla squadra
 */
public class ElementiRichiesta implements Serializable{
    private String nickname;
    private String nomeSquadra;
    
    public ElementiRichiesta(){
        this.nickname = null;
        this.nomeSquadra = null;
    }
    
    public ElementiRichiesta(String nick, String nomeSquadra){
        this.nickname = nick;
        this.nomeSquadra = nomeSquadra;
    }
    
    public String getNick(){
        return this.nickname;
    }
    
    public String getSquadra(){
        return this.nomeSquadra;
    }
}
