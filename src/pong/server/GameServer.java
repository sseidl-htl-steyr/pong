package pong.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import pong.client.Ball;
import pong.common.GameConstants;
import pong.common.GameObject;
import pong.gui.IBallListener;
import pong.math.Point2;
import pong.math.Vec2;

public class GameServer extends Thread implements IBallListener
{
    private ServerSocket serverSocket;

    private Socket[] clients = new Socket[2];
    private DataInputStream[] dis = new DataInputStream[2];
    private DataOutputStream[] dos = new DataOutputStream[2];
    private IServerConnectionHandler handler;
    private boolean player2Bot;

    private GameObject[] objects;
    private int[] scores = new int[] { 0, 0 };
    private long restartTime;

    private Random rand;

    private boolean stopped = false;

    public GameServer(int port, IServerConnectionHandler handler, boolean player2Bot) throws IOException
    {
        serverSocket = new ServerSocket(port);
        this.handler = handler;
        this.player2Bot = player2Bot;

        objects = GameConstants.createGameObjects();
        ((Ball) (objects[1])).setBallListener(this);

        rand = new Random();
    }

    public void stopServer()
    {
        stopped = true;

        try
        {
            serverSocket.close();
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
            for (int i = 0; i < clients.length; i++)
            {
                clients[i] = serverSocket.accept();
                clients[i].setTcpNoDelay(true);
                dis[i] = new DataInputStream(new BufferedInputStream(clients[i].getInputStream()));
                dos[i] = new DataOutputStream(new BufferedOutputStream(clients[i].getOutputStream()));
            }

            handler.clientsConnected();

            long lastTime = System.nanoTime();
            restartTime = System.currentTimeMillis() + 1000;

            while (!stopped)
            {
                objects[0].setVelocity(new Vec2(0F, dis[0].readFloat()));

                if (!player2Bot)
                {
                    objects[2].setVelocity(new Vec2(0F, dis[1].readFloat()));
                }
                else
                {
                    Point2 location = objects[2].getLocation();
                    location.y = objects[1].getLocation().y;

                    objects[2].setLocation(location);
                }

                if (restartTime != -1 && System.currentTimeMillis() >= restartTime)
                {
                    float vX = rand.nextInt(2) == 0 ? GameConstants.BALL_VELOCITY : -GameConstants.BALL_VELOCITY;
                    float vY = rand.nextInt(2) == 0 ? GameConstants.BALL_VELOCITY : -GameConstants.BALL_VELOCITY;

                    objects[1].setVelocity(new Vec2(vX, vY));
                    restartTime = -1;
                }

                for (int i = 0; i < objects.length; i++)
                {
                    objects[i].update((System.nanoTime() - lastTime) / 1e6F, objects);

                    Point2 location = objects[i].getLocation();

                    for (int j = 0; j < clients.length; j++)
                    {
                        dos[j].writeFloat(location.x);
                        dos[j].writeFloat(location.y);
                    }
                }

                for (int i = 0; i < clients.length; i++)
                {
                    dos[i].writeInt(scores[0]);
                    dos[i].writeInt(scores[1]);

                    dos[i].flush();
                }

                lastTime = System.nanoTime();

                try
                {
                    Thread.sleep(1);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void ballOutOfField(int player)
    {
        scores[player]++;

        objects[1].setLocation(new Point2(GameConstants.GAME_WIDTH / 2F, GameConstants.GAME_HEIGHT / 2F));
        objects[1].setVelocity(new Vec2(0F, 0F));

        restartTime = System.currentTimeMillis() + 1000;
    }
}
