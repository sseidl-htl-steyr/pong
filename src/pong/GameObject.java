package pong;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import pong.math.Point2;
import pong.math.Vec2;

public abstract class GameObject
{
    private Point2 location;
    private Vec2 velocity;

    public GameObject(Point2 location)
    {
        this.location = location;
        velocity = new Vec2(0F, 0F);
    }

    protected abstract void draw(Graphics g);

    public void drawObject(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) (g);
        AffineTransform trans = g2d.getTransform();
        g2d.translate(location.x, location.y);
        draw(g);
        g2d.setTransform(trans);
    }

    public void update(float dt)
    {
        location = Vec2.add(location, Vec2.multiply(velocity, dt));
        // TODO collision detection
    }
}
