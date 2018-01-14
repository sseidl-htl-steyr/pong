package pong.client;

import java.awt.Color;

import pong.common.GameObject;
import pong.math.Point2;

public class Player extends GameObject
{
    public Player(Point2 location, int width, int height, Color color)
    {
        super(location, width, height, color, true);
    }
}
