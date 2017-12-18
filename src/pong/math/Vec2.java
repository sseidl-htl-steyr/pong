package pong.math;

public class Vec2
{
    public float x;
    public float y;

    public Vec2(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public float magnitude()
    {
        return (float) (Math.sqrt(x * x + y * y));
    }

    public static Vec2 add(Vec2 a, Vec2 b)
    {
        return new Vec2(a.x + b.x, a.y + b.y);
    }

    public static Point2 add(Point2 a, Vec2 b)
    {
        return new Point2(a.x + b.x, a.y + b.y);
    }

    public static Vec2 subtract(Vec2 a, Vec2 b)
    {
        return new Vec2(a.x - b.x, a.y - b.y);
    }

    public static Vec2 multiply(Vec2 a, float b)
    {
        return new Vec2(a.x * b, a.y * b);
    }
}
