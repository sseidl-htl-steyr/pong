package pong.gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;

public class BackgroundButton extends JButton
{
    private static final long serialVersionUID = -5878185791126151308L;

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
