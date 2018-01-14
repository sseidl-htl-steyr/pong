package pong.gui;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import pong.client.GameClient;
import pong.client.GameType;
import pong.common.GameConstants;
import pong.server.GameServer;
import pong.server.IServerConnectionHandler;

public class MainFrame extends JFrame implements ActionListener, IServerConnectionHandler, IGameCancelledListener
{
    private static final long serialVersionUID = -8493978125642898935L;

    public CardLayout cl;
    private BackgroundPanel mainMenu;
    public Container contentPane;
    private PlayField field;

    private BackgroundButton singleplayer;
    private BackgroundButton localMP;
    private BackgroundButton joinMP;
    private BackgroundButton hostMP;
    private BackgroundButton exit;

    private JDialog dialog = null;
    private boolean connectionEstablished;

    private GameServer currentServer = null;
    private GameClient[] currentClients = new GameClient[2];
    private GameType currentGameType;

    public MainFrame()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("BSS Pong");
        setResizable(false);

        setIconImage(loadImage("Pong_Icon.png"));
        // setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Pong_Icon.png")));

        contentPane = getContentPane();
        contentPane.setPreferredSize(new Dimension(GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT));
        pack();

        cl = new CardLayout();
        contentPane.setLayout(cl);

        mainMenu = new BackgroundPanel(loadImage("Pong_Test.png"));
        // mainMenu = new BackgroundPanel(loadImage("Pong.png"));
        mainMenu.setLayout(null);

        field = new PlayField(this);

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

        validate();
        repaint();
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
            currentGameType = GameType.SINGLE_PLAYER;
            try
            {
                startServer(true);

                for (int i = 0; i < currentClients.length; i++)
                {
                    currentClients[i] = new GameClient("127.0.0.1", GameConstants.SERVER_PORT);
                    currentClients[i].start();
                }
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }
        if (e.getSource() == localMP)
        {
            currentGameType = GameType.LOCAL_MULTI_PLAYER;
            try
            {
                startServer(false);

                for (int i = 0; i < currentClients.length; i++)
                {
                    currentClients[i] = new GameClient("127.0.0.1", GameConstants.SERVER_PORT);
                    currentClients[i].start();
                }
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }
        if (e.getSource() == hostMP)
        {
            currentGameType = GameType.MULTI_PLAYER_HOST;
            try
            {
                startServer(false);

                currentClients[0] = new GameClient("127.0.0.1", GameConstants.SERVER_PORT);
                currentClients[0].start();

                connectionEstablished = false;
                dialog = new JOptionPane("Waiting for a client to connect!", JOptionPane.INFORMATION_MESSAGE).createDialog(this, "Awaiting Connection");

                while (!connectionEstablished)
                {
                    dialog.setVisible(true);
                }
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }
        if (e.getSource() == joinMP)
        {
            String target = JOptionPane.showInputDialog(this, "IP-Address:", "Connection Target", JOptionPane.INFORMATION_MESSAGE);

            if (target != null)
            {
                try
                {
                    currentClients[0] = new GameClient(target, GameConstants.SERVER_PORT);
                    currentClients[0].start();

                    field.initGame(GameType.MULTI_PLAYER_GUEST, currentClients);
                    cl.show(getContentPane(), "field");
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(this, e1.getMessage(), "Connection Error!", JOptionPane.ERROR_MESSAGE, null);
                }
            }
        }
    }

    private void startServer(boolean player2Bot) throws IOException
    {
        if (currentServer != null)
        {
            currentServer.stopServer();
        }

        currentServer = new GameServer(GameConstants.SERVER_PORT, this, player2Bot);
        currentServer.start();
    }

    public Image loadImage(String name)
    {
        return Toolkit.getDefaultToolkit().getImage(getClass().getResource("/pong/resources/" + name));
    }

    @Override
    public void clientsConnected()
    {
        connectionEstablished = true;

        if (dialog != null)
        {
            dialog.setVisible(false);
            dialog = null;
        }

        field.initGame(currentGameType, currentClients);
        cl.show(getContentPane(), "field");
    }

    @Override
    public void gameCancelled()
    {
        cl.show(getContentPane(), "main");
    }
}
