package pong;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import pong.math.Point2;

public class Ball extends GameObject implements ActionListener
{
    private int x_Ball = 200;
    private int y_Ball = 100;
    private Timer t;

    public Ball()
    {
        super(new Point2(0F, 0F));
    }

    public ActionListener updateTimer()
    {

        x_Ball += 5;

        if (y_Ball >= 0 && y_Ball <= 400)
            y_Ball += 10;
        else
            y_Ball -= 10;

        return null;
    }

    public int getX_Ball()
    {
        return x_Ball;
    }

    public void setX_Ball(int x_Ball)
    {
        this.x_Ball = x_Ball;
    }

    public int getY_Ball()
    {
        return y_Ball;
    }

    public void setY_Ball(int y_Ball)
    {
        this.y_Ball = y_Ball;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // TODO Auto-generated method stub

    }

    @Override
    protected void draw(Graphics g)
    {
        g.fillRect(-10, -10, 10, 10);
    }

}
