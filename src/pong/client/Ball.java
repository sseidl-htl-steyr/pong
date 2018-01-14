package pong.client;

import java.awt.Color;

import pong.common.GameConstants;
import pong.common.GameObject;
import pong.gui.IBallListener;
import pong.math.Point2;

public class Ball extends GameObject
{
    private IBallListener listener = null;

    public Ball(Point2 location, int width, int height, Color color)
    {
        super(location, width, height, color, false);
    }

    public void setBallListener(IBallListener listener)
    {
        this.listener = listener;
    }

    @Override
    protected void collisionDetected(boolean bounds, int axis)
    {
        if (axis == 0)
        {
            if (bounds)
            {
                if (listener != null)
                {
                    listener.ballOutOfField(location.x > GameConstants.GAME_WIDTH >> 1 ? 0 : 1);
                }
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
