package pong;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import pong.math.Point2;

public class Player extends GameObject
{
    private PlayField pf;
    private SinglePlayerOptions spo;
    private Bot b;
    private boolean movingUp = false;
    private boolean movingDown = false;
    private Component base;

    private int playerPosition = 200;
    private int player2_Position = 200;

    private Timer t;

    public Player()
    {
        super(new Point2(0F, 0F));
        this.movingUp = movingUp;
        this.movingDown = movingDown;

        // if(spo.isEnemyBot()) {
        // b = new Bot();
        // }

    }

    public ActionListener updateTimer()
    {
        // t.restart();
        if (pf.isMovingUp())
        {
            playerPosition = playerPosition - 5;
        }
        else if (pf.isMovingDown())
        {
            playerPosition = playerPosition + 5;
        }
        // pf.repaint();

        return null;
    }

    public int getPlayerPosition()
    {
        return playerPosition;
    }

    public void setPlayerPosition(int playerPosition)
    {
        this.playerPosition = playerPosition;
    }

    public int getPlayer2_Position()
    {
        return player2_Position;
    }

    public void setPlayer2_Position(int player2_Position)
    {
        this.player2_Position = player2_Position;
    }

    @Override
    protected void draw(Graphics g)
    {
        g.fillRect(-10, -30, 10, 30);
    }

}
