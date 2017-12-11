package pong.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class GameServer extends Thread
{
    private Socket client;
    private IClientDataHandler handler;
    private boolean stopped = false;
    private DataInputStream dis;
    private DataOutputStream dos;

    public GameServer(Socket client, IClientDataHandler handler) throws IOException
    {
        this.client = client;
        this.handler = handler;
        dis = new DataInputStream(client.getInputStream());
        dos = new DataOutputStream(client.getOutputStream());
    }

    public void sendData(int[] data)
    {
        try
        {
            for (int i = 0; i < data.length; i++)
            {
                dos.writeInt(data[i]);
            }
        }
        catch (IOException e)
        {
        }
    }

    public void stopServer()
    {
        stopped = true;
    }

    @Override
    public void run()
    {
        try
        {
            while (!client.isClosed() && !stopped)
            {
                int available = dis.available();
                if (available > 0 && (available & 0x03) == 0)
                {
                    int[] data = new int[available << 2];

                    for (int i = 0; i < data.length; i++)
                    {
                        data[i] = dis.readInt();
                    }

                    handler.dataArrived(data);
                }
            }
        }
        catch (IOException e)
        {
        }
    }
}
