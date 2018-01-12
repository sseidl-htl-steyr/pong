package pong;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame implements ActionListener
{
    private static final long serialVersionUID = -8493978125642898935L;

    public CardLayout cl;
    private JPanel mainMenu;
    public Container contentPane;
    private PlayField field;

    // Men�buttons
    private JButton singleplayer;
    private JButton localMP;
    private JButton joinMP;
    private JButton hostMP;
    private JButton exit;

    public GUI()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("BSS Pong");
        setSize(1280, 720);
        setResizable(false);

        setIconImage(loadImage("Pong_Icon.png"));
        // setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Pong_Icon.png")));

        contentPane = getContentPane();
        cl = new CardLayout();
        contentPane.setLayout(cl);

        mainMenu = new BackgroundPanel(loadImage("Pong_Test.png"));
        // mainMenu = new BackgroundPanel(loadImage("Pong.png"));
        mainMenu.setLayout(null);

        field = new PlayField();

        // singlePlayerOpt = new JPanel();
        // singlePlayerOpt.setLayout(new BorderLayout());
        // singlePlayerOpt.add(spo, BorderLayout.CENTER);

        contentPane.add(field);
        cl.addLayoutComponent(field, "field");

        contentPane.add(mainMenu);
        cl.addLayoutComponent(mainMenu, "main");

        cl.show(contentPane, "main");

        singleplayer = new BackgroundButton(loadImage("SingleplayerButton.png"));
        singleplayer.setSize(getWidth() / 4, 100);
        int x = (getWidth() / 2) - (singleplayer.getWidth() / 2);
        singleplayer.setBounds(x, getHeight() / 2 + 70, getWidth() / 4, 50);

        // singleplayer.setBounds(x, getHeight()/2+70, getWidth()/4, 50);
        singleplayer.setMaximumSize(singleplayer.getSize());
        singleplayer.setOpaque(false);
        singleplayer.setContentAreaFilled(false);
        singleplayer.setBorderPainted(false);
        singleplayer.addActionListener(this);
        mainMenu.add(singleplayer);

        localMP = new BackgroundButton(loadImage("localmp.png"));
        localMP.setMaximumSize(singleplayer.getSize());
        localMP.setBounds(x, getHeight() / 2 + 120, getWidth() / 4, 50);
        localMP.setOpaque(false);
        localMP.setContentAreaFilled(false);
        localMP.setBorderPainted(false);
        localMP.addActionListener(this);
        mainMenu.add(localMP);
        localMP.addActionListener(this);

        joinMP = new BackgroundButton(loadImage("joinmp.png"));
        joinMP.setBounds(x, getHeight() / 2 + 170, getWidth() / 4, 50);
        joinMP.setOpaque(false);
        joinMP.setContentAreaFilled(false);
        joinMP.setBorderPainted(false);
        joinMP.addActionListener(this);
        mainMenu.add(joinMP);

        hostMP = new BackgroundButton(loadImage("Hostmp.png"));
        hostMP.setBounds(x, getHeight() / 2 + 220, getWidth() / 4, 50);
        hostMP.setOpaque(false);
        hostMP.setContentAreaFilled(false);
        hostMP.setBorderPainted(false);
        hostMP.addActionListener(this);
        mainMenu.add(hostMP);

        exit = new BackgroundButton(loadImage("exit.png"));
        exit.setMaximumSize(singleplayer.getSize());
        exit.setBounds(x, getHeight() / 2 + 270, getWidth() / 4, 50);
        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        exit.addActionListener(this);
        mainMenu.add(exit);

        setVisible(true);
        validate();
        // repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == exit)
        {
            System.exit(0);
        }
        if (e.getSource() == singleplayer)
        {
            field.initGame(GameType.SINGLE_PLAYER);
            cl.show(getContentPane(), "field");
        }
        if (e.getSource() == localMP)
        {
            field.initGame(GameType.LOCAL_MULTI_PLAYER);
            cl.show(getContentPane(), "field");
        }
        if (e.getSource() == hostMP)
        {
            field.initGame(GameType.MULTI_PLAYER_HOST);
            cl.show(getContentPane(), "field");
        }
        if (e.getSource() == joinMP)
        {
            field.initGame(GameType.MULTI_PLAYER_GUEST);
            cl.show(getContentPane(), "field");
        }
    }

    // public void showMPField() {
    // cl.show(contentPane, "OnlineMP");
    // }

    public Image loadImage(String ImageName)
    {
        return Toolkit.getDefaultToolkit().getImage(getClass().getResource(ImageName));
    }

}
