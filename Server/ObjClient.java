package Server;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObjClient
{
    
    private String ip;
    private String port;
    private Socket socket;
    
    /**
     * Costruttore che serve per inizializzare gli attributi della classe
     * @param ip_port: indirizzo ip della porta di comunicazione client/server
     * @param socket : socket di comunicazione client/server
     */
    public ObjClient(String ip_port,Socket socket)
    {       
        String [] split;
        this.socket = socket;
        this.ip = (String) ip_port.replaceAll("/","");
        split = this.ip.split(":");
        this.ip = split[0];
        this.port = split[1];       
    }   
 
    /**
     * Vari metodi di getter
     * @return 
     */
    public String getIp()
    {
        return this.ip;
    }
    
    public String getPort()
    {
        return this.port;
    }
    
    public Socket socket()
    {
        return this.socket;
    }
    
    public void chiudiSocket()
    {
        try 
        {
            this.socket.close();
        }
        catch (IOException ex)
        {
            Logger.getLogger(ObjClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Socket getSocket()
    {
        return this.socket;
    }
}
