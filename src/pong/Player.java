package pong;

import java.awt.Color;

import pong.math.Point2;

public class Player extends GameObject
{
    public Player(PlayField pf, Point2 location, int width, int height, Color color)
    {
        super(pf, location, width, height, color, true);
    }
}
