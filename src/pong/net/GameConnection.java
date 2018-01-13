package pong.net;

import java.io.BufferedInputStream;
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
        dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        dos = new DataOutputStream(socket.getOutputStream());

        socket.setTcpNoDelay(true);
    }

    public void sendData(float[] data)
    {
        try
        {
            for (int i = 0; i < data.length; i++)
            {
                dos.writeFloat(data[i]);
            }
        }
        catch (IOException e)
        {
        }
    }

    public void readValues(float[] f, int n)
    {
        try
        {
            for (int i = 0; i < n; i++)
            {
                f[i] = dis.readFloat();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
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
                    float[] data = new float[receiveSize];

                    for (int i = 0; i < receiveSize; i++)
                    {
                        data[i] = dis.readFloat();
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
