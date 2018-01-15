package pong.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import pong.client.GameClient;
import pong.client.GameType;
import pong.common.GameConstants;
import pong.common.GameObject;

public class PlayField extends JPanel implements KeyListener, ActionListener
{
    private static final long serialVersionUID = 2944593318509141449L;

    private IGameCancelledListener listener;

    private Timer t;

    private boolean vk_up = false;
    private boolean vk_down = false;

    private boolean vk_w = false;
    private boolean vk_s = false;

    private GameType type = GameType.SINGLE_PLAYER;
    private GameClient[] clients;

    public PlayField(IGameCancelledListener listener)
    {
        this.listener = listener;
        t = new Timer(8, this);
        addKeyListener(this);
    }

    public void initGame(GameType type, GameClient[] clients)
    {
        this.type = type;
        this.clients = clients;
        t.start();
    }

    public void stopGame()
    {
        t.stop();

        for (int i = 0; i < clients.length; i++)
        {
            clients[i].stopClient();
        }
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) (g);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        GameObject[] objects = clients[0].getObjects();

        for (int i = 0; i < objects.length; i++)
        {
            objects[i].drawObject(g);
        }

        int halfX = GameConstants.GAME_WIDTH >> 1;
        int[] scores = clients[0].getScores();

        g.setColor(Color.WHITE);
        g.drawLine(halfX, 0, halfX, GameConstants.GAME_HEIGHT);

        g.drawString(Integer.toString(scores[0]), halfX - GameConstants.BAT_TO_BORDER_DISTANCE, GameConstants.BAT_TO_BORDER_DISTANCE);
        g.drawString(Integer.toString(scores[1]), halfX + GameConstants.BAT_TO_BORDER_DISTANCE, GameConstants.BAT_TO_BORDER_DISTANCE);
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            vk_up = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            vk_down = true;
        }

        else if (e.getKeyCode() == KeyEvent.VK_W)
        {
            vk_w = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_S)
        {
            vk_s = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            listener.gameCancelled();
            stopGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            vk_up = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            vk_down = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_W)
        {
            vk_w = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_S)
        {
            vk_s = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    private float getPlayerVelocity(boolean up, boolean down)
    {
        float velocity;

        if (up && !down)
        {
            velocity = -GameConstants.BALL_VELOCITY;
        }
        else if (down && !up)
        {
            velocity = GameConstants.BALL_VELOCITY;
        }
        else
        {
            velocity = 0F;
        }

        return velocity;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (type == GameType.LOCAL_MULTI_PLAYER)
        {
            clients[0].sendData(getPlayerVelocity(vk_w, vk_s));
            clients[1].sendData(getPlayerVelocity(vk_up, vk_down));
        }
        else if (type == GameType.SINGLE_PLAYER || type == GameType.MULTI_PLAYER_HOST)
        {
            clients[0].sendData(getPlayerVelocity(vk_w, vk_s));
        }
        else if (type == GameType.MULTI_PLAYER_GUEST)
        {
            clients[0].sendData(getPlayerVelocity(vk_up, vk_down));
        }

        repaint();
        setFocusable(true);
        requestFocus(true);

        t.restart();
    }
}
