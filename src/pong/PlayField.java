package pong;

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

import pong.math.Point2;
import pong.math.Vec2;
import pong.net.GameConnection;

public class PlayField extends JPanel implements KeyListener, ActionListener, IBallListener
{
    private static final long serialVersionUID = 2944593318509141449L;

    private static final int BAT_WIDTH = 15;
    private static final int BAT_HEIGHT = 150;
    private static final int BALL_SIZE = 15;

    private static final int BAT_TO_BORDER_DISTANCE = 30;

    private static final float BALL_VELOCITY = 0.5F;

    private long lastTime;

    private Timer t;

    private boolean vk_up = false;
    private boolean vk_down = false;

    private boolean vk_w = false;
    private boolean vk_s = false;

    private GameObject[] objects;

    private GameType type = GameType.SINGLE_PLAYER;
    private GameConnection connection = null;

    private long restartTime = -1;

    public PlayField()
    {
        objects = new GameObject[] { new Player(this, new Point2(0F, 0F), BAT_WIDTH, BAT_HEIGHT, Color.WHITE), new Ball(this, new Point2(0F, 0F), BALL_SIZE, BALL_SIZE, Color.WHITE, this), new Player(this, new Point2(0F, 0F), BAT_WIDTH, BAT_HEIGHT, Color.WHITE) };

        t = new Timer(1, this);
        addKeyListener(this);
    }

    public void initGame(GameType type)
    {
        float width = getWidth();
        float height = getHeight();

        float halfX = width / 2F;
        float halfY = height / 2F;

        this.type = type;

        objects[0].setLocation(new Point2(BAT_TO_BORDER_DISTANCE, halfY));
        objects[1].setLocation(new Point2(halfX, halfY));
        objects[2].setLocation(new Point2(width - BAT_TO_BORDER_DISTANCE, halfY));

        objects[1].setVelocity(new Vec2(BALL_VELOCITY, BALL_VELOCITY));

        lastTime = System.currentTimeMillis();
        t.start();
    }

    public GameConnection getConnection()
    {
        return connection;
    }

    public void setConnection(GameConnection connection)
    {
        this.connection = connection;
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

        for (int i = 0; i < objects.length; i++)
        {
            objects[i].drawObject(g);
        }
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            vk_up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            vk_down = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_W)
        {
            vk_w = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S)
        {
            vk_s = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            vk_up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            vk_down = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_W)
        {
            vk_w = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S)
        {
            vk_s = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    public boolean isMovingUp()
    {
        return vk_up;
    }

    public void setMovingUp(boolean movingUp)
    {
        this.vk_up = movingUp;
    }

    public boolean isMovingDown()
    {
        return vk_down;
    }

    public void setMovingDown(boolean movingDown)
    {
        this.vk_down = movingDown;
    }

    private void setPlayerVelocity(GameObject player, boolean up, boolean down)
    {
        if (up && !down)
        {
            player.setVelocity(new Vec2(0F, -BALL_VELOCITY));
        }
        else if (down && !up)
        {
            player.setVelocity(new Vec2(0F, BALL_VELOCITY));
        }
        else
        {
            player.setVelocity(new Vec2(0F, 0F));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (type == GameType.LOCAL_MULTI_PLAYER || type == GameType.MULTI_PLAYER_GUEST)
        {
            setPlayerVelocity(objects[2], vk_up, vk_down);
        }

        if (type == GameType.SINGLE_PLAYER || type == GameType.LOCAL_MULTI_PLAYER || type == GameType.MULTI_PLAYER_HOST)
        {
            setPlayerVelocity(objects[0], vk_w, vk_s);

            for (int i = 0; i < objects.length; i++)
            {
                objects[i].update(System.currentTimeMillis() - lastTime);
            }
        }

        if (restartTime != -1 && System.currentTimeMillis() - restartTime >= 2000)
        {
            objects[1].setVelocity(new Vec2(BALL_VELOCITY, BALL_VELOCITY));
            restartTime = -1;
        }

        if (type == GameType.SINGLE_PLAYER)
        {
            float y = objects[1].getLocation().y;
            float height = getHeight();
            float halfHeight = BAT_HEIGHT / 2F;

            if (y < halfHeight)
            {
                y = halfHeight;
            }
            else if (y > height - halfHeight)
            {
                y = height - halfHeight;
            }

            objects[2].setLocation(new Point2(getWidth() - BAT_TO_BORDER_DISTANCE, y));
        }
        else if (type == GameType.MULTI_PLAYER_GUEST)
        {
            if (connection != null)
            {
                float[] coords = new float[3];

                connection.readValues(coords, 3);
                objects[0].setLocation(new Point2(BAT_TO_BORDER_DISTANCE, coords[0]));
                objects[1].setLocation(new Point2(coords[1], coords[2]));
            }
        }

        lastTime = System.currentTimeMillis();

        repaint();
        setFocusable(true);
        requestFocus(true);

        t.restart();
    }

    @Override
    public void ballOutOfField(int player)
    {
        objects[1].setLocation(new Point2(getWidth() / 2F, getHeight() / 2F));
        objects[1].setVelocity(new Vec2(0F, 0F));
        restartTime = System.currentTimeMillis();
    }
}
