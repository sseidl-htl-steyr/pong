package pong;

import java.awt.Color;

import pong.math.Point2;

public class Ball extends GameObject
{
    private IBallListener listener;

    public Ball(PlayField pf, Point2 location, int width, int height, Color color, IBallListener listener)
    {
        super(pf, location, width, height, color, false);
        this.listener = listener;
    }

    @Override
    protected void collisionDetected(boolean bounds, int axis)
    {
        if (axis == 0)
        {
            if (bounds)
            {
                listener.ballOutOfField(location.x < 0 ? 0 : 1);
            }
            else
            {
                velocity.x = -velocity.x;
            }
        }
        else
        {
            velocity.y = -velocity.y;
        }
    }
}
