package pong.gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class BackgroundPanel extends JPanel
{
    private static final long serialVersionUID = -4917619106067488731L;

    Image bgImage;

    BackgroundPanel(Image bgImage)
    {
        this.bgImage = bgImage;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
    }
}
