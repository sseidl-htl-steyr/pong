package pong.common;

import java.awt.Color;

import pong.client.Ball;
import pong.client.Player;
import pong.math.Point2;

public class GameConstants
{
    public static final int GAME_WIDTH = 1280;
    public static final int GAME_HEIGHT = 720;

    public static final int BAT_WIDTH = 15;
    public static final int BAT_HEIGHT = 150;
    public static final int BALL_SIZE = 15;

    public static final int BAT_TO_BORDER_DISTANCE = 30;

    public static final float BALL_VELOCITY = 0.5F;

    public static final int SERVER_PORT = 3465;

    public static GameObject[] createGameObjects()
    {
        GameObject[] result = new GameObject[3];
        float halfX = GAME_WIDTH / 2F;
        float halfY = GAME_HEIGHT / 2F;

        result[0] = new Player(new Point2(GameConstants.BAT_TO_BORDER_DISTANCE, halfY), BAT_WIDTH, BAT_HEIGHT, Color.WHITE);
        result[1] = new Ball(new Point2(halfX, halfY), BALL_SIZE, BALL_SIZE, Color.WHITE);
        result[2] = new Player(new Point2(GAME_WIDTH - GameConstants.BAT_TO_BORDER_DISTANCE, halfY), BAT_WIDTH, BAT_HEIGHT, Color.WHITE);

        return result;
    }
}
