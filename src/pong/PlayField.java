package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PlayField extends JPanel implements KeyListener, ActionListener
{
    Graphics g;
    private SinglePlayerOptions spo;
    private Player p;
    private Ball b;
    private int border_to_player = 20;
    private int playerHeight = 100;
    private int score = 10;
    private String difficulty;
    private int playerPosition_start = getHeight() / 2 - playerHeight / 2;
    private Timer t;

    private boolean movingUp = false;
    private boolean movingDown = false;

    private GameObject[] objects;

    public PlayField()
    {
        super();

        System.out.println(this.hasFocus());
        p = new Player();
        b = new Ball();

        t = new Timer(16, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (movingUp)
                {
                    p.setPlayer2_Position(p.getPlayer2_Position() - 10);
                }
                if (movingDown)
                {
                    p.setPlayer2_Position(p.getPlayer2_Position() + 10);
                }

                repaint();
                t.restart();
            }
        });

        t.start();

        addKeyListener(this);

        objects = new GameObject[] { new Player(), new Ball(), new Player() };
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);

        /* START POSITION */

        g.fillRect(border_to_player, p.getPlayerPosition(), 10, playerHeight); // Player
                                                                               // on
                                                                               // the
                                                                               // left
                                                                               // side

        g.fillRect((getWidth() - border_to_player) - 10, p.getPlayer2_Position(), 10, playerHeight); // Player
                                                                                                     // on
                                                                                                     // the
                                                                                                     // right
                                                                                                     // side

        g.fillRect(b.getX_Ball(), b.getY_Ball(), 20, 20); // Ball

     
        // repaint();
        // movePlayer(g);
        // drawScore(g, score);
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            movingUp = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            movingDown = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            movingUp = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            movingDown = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    public boolean isMovingUp()
    {
        return movingUp;
    }

    public void setMovingUp(boolean movingUp)
    {
        this.movingUp = movingUp;
    }

    public boolean isMovingDown()
    {
        return movingDown;
    }

    public void setMovingDown(boolean movingDown)
    {
        this.movingDown = movingDown;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
    }
}
