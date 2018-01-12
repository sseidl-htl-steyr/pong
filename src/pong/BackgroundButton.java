package pong;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;

public class BackgroundButton extends JButton
{
    Image bgImage;

    BackgroundButton(Image bgImage)
    {
        this.bgImage = bgImage;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), null);
    }
}
