package pong.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class GameConnection extends Thread
{
    private Socket socket;
    private IGameDataHandler handler;
    private int receiveSize;
    private boolean stopped = false;
    private DataInputStream dis;
    private DataOutputStream dos;

    public GameConnection(Socket socket, IGameDataHandler handler, int receiveSize) throws IOException
    {
        this.socket = socket;
        this.handler = handler;
        this.receiveSize = receiveSize;
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
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

    public void stopConnection() throws IOException
    {
        stopped = true;
        socket.close();
    }

    @Override
    public void run()
    {
        try
        {
            while (!stopped)
            {
                int available = dis.available() >> 2;
                if (available >= receiveSize)
                {
                    int[] data = new int[receiveSize];

                    for (int i = 0; i < receiveSize; i++)
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
