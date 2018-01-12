package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;

import pong.math.Point2;
import pong.math.Vec2;

public abstract class GameObject
{
    private static LinkedList<GameObject> instances = new LinkedList<GameObject>();

    private PlayField pf;
    protected Point2 location;
    private int width;
    private int height;
    private Color color;
    protected Vec2 velocity;
    private boolean passive;

    public GameObject(PlayField pf, Point2 location, int width, int height, Color color, boolean passive)
    {
        this.pf = pf;
        this.location = location;
        this.width = width;
        this.height = height;
        this.color = color;
        this.passive = passive;
        velocity = new Vec2(0F, 0F);

        instances.add(this);
    }

    public Point2 getLocation()
    {
        return location;
    }

    public void setLocation(Point2 location)
    {
        this.location = location;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public Vec2 getVelocity()
    {
        return velocity;
    }

    public void setVelocity(Vec2 velocity)
    {
        this.velocity = velocity;
    }

    public void drawObject(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) (g);

        g.setColor(color);

        AffineTransform tf = g2d.getTransform();
        g2d.translate(location.x - width / 2F, location.y - height / 2F);
        g.fillRect(0, 0, width, height);
        g2d.setTransform(tf);
    }

    public void update(float dt)
    {
        location = Vec2.add(location, Vec2.multiply(velocity, dt));

        float halfWidth = width / 2F;
        float halfHeight = height / 2F;

        float minX = location.x - halfWidth;
        float minY = location.y - halfHeight;

        float maxX = location.x + halfWidth;
        float maxY = location.y + halfHeight;

        if (minX < 0F)
        {
            location.x = halfWidth;
            collisionDetected(true, 0);
        }
        else if (maxX > pf.getWidth())
        {
            location.x = pf.getWidth() - halfWidth;
            collisionDetected(true, 0);
        }

        if (minY < 0F)
        {
            location.y = halfHeight;
            collisionDetected(true, 1);
        }
        else if (maxY > pf.getHeight())
        {
            location.y = pf.getHeight() - halfHeight;
            collisionDetected(true, 1);
        }

        if (!passive)
        {
            for (GameObject obj : instances)
            {
                if (obj != this)
                {
                    float objHalfWidth = obj.width / 2F;
                    float objHalfHeight = obj.height / 2F;

                    float objMinX = obj.location.x - objHalfWidth;
                    float objMinY = obj.location.y - objHalfHeight;

                    float objMaxX = obj.location.x + objHalfWidth;
                    float objMaxY = obj.location.y + objHalfHeight;

                    if ((minY > objMinY && minY < objMaxY) || (maxY > objMinY && maxY < objMaxY))
                    {
                        if ((maxX > objMinX && maxX < objMaxX) || (minX > objMinX && minX < objMaxX))
                        {
                            if (location.x < obj.location.x)
                            {
                                location.x = obj.location.x - halfWidth - objHalfWidth;
                            }
                            else
                            {
                                location.x = obj.location.x + halfWidth + objHalfWidth;
                            }
                            collisionDetected(false, 0);
                        }
                    }
                }
            }
        }
    }

    protected void collisionDetected(boolean bounds, int axis)
    {
    }
}
