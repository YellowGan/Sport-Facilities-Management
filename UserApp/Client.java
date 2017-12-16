package Client;

import java.io.IOException;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * Classe che rappresenta il Client dove vengono inizializzate le variabili che 
 * indicano l'ID del server e la porta da dove resta in ascolto, dove vengono 
 * inizializzate gli stream per poter comunicare con il server.
 */
public class Client{
    private String ip;
    private int port;
    public static Socket socket;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;

    /**
     * Costruttore per settare la porta e l'IP
     */
    public Client(String ip, int port){
            this.ip = ip;
            this.port = port;
    }

    
    /**
     * 
     * Metodo Main che avvia il Client
     */
    public static void main(String [] args){  
        Client client = new Client("127.0.0.1", 1337);
        try{
                client.startClient(args);
        }catch (IOException e){
                System.out.println("Errore nel client");
        }
    }

    /**
     * Metodo che crea la socket per comunicare con il server e dove parte 
     * l'interfaccia grafica dell'applicativo
     */
    public void startClient(String [] args) throws IOException {    
            this.socket = new Socket (ip,port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connessione stabilita");
            Form.parti(args);
    }

    /**
     * Metodo che permette la trasmissione e la ricezione dei dati tra il 
     * client il server
     */
    public static Aquisizioni trasmissione(Aquisizioni aquisizioni){
        try{
            out.writeObject(aquisizioni);
            out.flush();
            aquisizioni = (Aquisizioni) in.readObject();
        } catch (IOException e){
            System.out.println("Errore di trasmissione");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
                    
        }
        return aquisizioni;
    }   
}
