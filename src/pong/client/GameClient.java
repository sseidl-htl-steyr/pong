package pong.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import pong.common.GameConstants;
import pong.common.GameObject;
import pong.math.Point2;

public class GameClient extends Thread
{
    private Socket socket;
    protected boolean stopped = false;
    protected DataInputStream dis;
    protected DataOutputStream dos;

    protected GameObject[] objects;

    private int[] scores = new int[] { 0, 0 };

    public GameClient(String address, int port) throws IOException
    {
        this.socket = new Socket(address, port);
        socket.setTcpNoDelay(true);

        dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

        objects = GameConstants.createGameObjects();
    }

    public GameObject[] getObjects()
    {
        return objects;
    }

    public int[] getScores()
    {
        return scores;
    }

    public void sendData(float f)
    {
        try
        {
            dos.writeFloat(f);
            dos.flush();
        }
        catch (IOException e)
        {
        }
    }

    public void stopClient()
    {
        stopped = true;

        try
        {
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        try
        {
            while (!stopped)
            {
                for (int i = 0; i < objects.length; i++)
                {
                    objects[i].setLocation(new Point2(dis.readFloat(), dis.readFloat()));
                }

                scores[0] = dis.readInt();
                scores[1] = dis.readInt();
            }
        }
        catch (IOException e)
        {
        }
    }
}
