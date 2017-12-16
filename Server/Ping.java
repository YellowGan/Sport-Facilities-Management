package Server;

import java.io.IOException;
import java.net.InetAddress;

public class Ping extends Thread 
{
    
    ObjClient obj;
    
    @Override
    public void run()
    {
        int i = 0;
        InetAddress inet;
        while(true)
        {            
            if(Server.arrayClient.size() > 0)
            {
                if(!Server.arrayClient.isEmpty()) 
                {
                    i = i + 1;
                    i = i % Server.arrayClient.size();
                }
                try
                {
                    obj = (ObjClient) Server.arrayClient.get(i);
                    inet = InetAddress.getByName(obj.getIp());
                    if(inet.isReachable(5000))
                    {
                        System.out.println("Il cliente con indirizzo " + obj.getIp() + " è ancora in linea!");
                    }
                    else
                    {
                        System.out.println("Connessione con " + obj.getIp() + " persa.");
                        obj.chiudiSocket();
                        Server.arrayClient.remove(i);
                    }
                } 
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            else
            { 
                System.out.print("");
            }           
        }    
    } 
}
