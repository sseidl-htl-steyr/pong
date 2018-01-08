package pong;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class GUI extends JFrame implements ActionListener
{
	public CardLayout cl;
	private JPanel mainMenu;
	public Container contentPane;
	private SinglePlayerOptions spo;
	private JPanel singlePlayerOpt;
	private JPanel multiPlayerOpt;
	private LocalMP mpo;
	private boolean bool_single = false;
	private boolean bool_multi = false;

	

	// Men�buttons
	private JTextPane title;
	private JButton singleplayer;
	private JButton localMP;
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
		mainMenu.setLayout(new GridLayout(1, 0));

		Box vBox = Box.createVerticalBox();

		vBox.add(Box.createRigidArea(new Dimension(0, (int) (getHeight() / 1.5F + 0.5F)))); // Hoehe der Buttons
																							// veraendern

		mainMenu.add(vBox);


		spo = new SinglePlayerOptions();

		
		singlePlayerOpt = new JPanel();
		singlePlayerOpt.setLayout(new BorderLayout());
		singlePlayerOpt.add(spo, BorderLayout.CENTER);
		
		contentPane.add(singlePlayerOpt);

		cl.addLayoutComponent(singlePlayerOpt, "SingelOptions");


		
		mpo = new LocalMP();

		multiPlayerOpt = new JPanel();
		multiPlayerOpt.setLayout(new BorderLayout());
		multiPlayerOpt.add(mpo, BorderLayout.CENTER);

		contentPane.add(multiPlayerOpt);

		
		cl.addLayoutComponent(multiPlayerOpt, "MultiOptions");

		contentPane.add(mainMenu);

		cl.addLayoutComponent(mainMenu, "main");

		cl.show(contentPane, "main");

		title = new JTextPane();
		title.setText("Pong");
		title.setEditable(false);
		// mainMenu.add(title);

		singleplayer = new BackgroundButton(loadImage("SingleplayerButton.png"));
		singleplayer.setSize(getWidth() / 4, 70);
		singleplayer.setMaximumSize(singleplayer.getSize());
		singleplayer.setOpaque(false);
		singleplayer.setContentAreaFilled(false);
		singleplayer.setBorderPainted(false);
		singleplayer.addActionListener(this);
		vBox.add(singleplayer);

		localMP = new BackgroundButton(loadImage("MultiplayerButton.png"));
		// multiplayer.setSize(getWidth() / 8, 50);
		localMP.setMaximumSize(singleplayer.getSize());
		localMP.setOpaque(false);
		localMP.setContentAreaFilled(false);
		localMP.setBorderPainted(false);
		localMP.addActionListener(this);
		vBox.add(localMP);

		hostMP = new JButton("Host MP");
		// settings.setSize(getWidth() / 8, 50);
		hostMP.setMaximumSize(singleplayer.getSize());
		hostMP.setOpaque(false);
		// settings.setContentAreaFilled(false);
		hostMP.setBorderPainted(false);
		hostMP.addActionListener(this);
		vBox.add(hostMP);

		exit = new JButton("Exit");
		// exit.setSize(getWidth() / 8, 50);
		exit.setMaximumSize(singleplayer.getSize());
		// exit.setOpaque(false);
		exit.setBackground(new Color(200, 0, 0));
		// exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		exit.addActionListener(this);
		vBox.add(exit);

		repaint();
		// validate();
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
			cl.show(getContentPane(), "SingelOptions");
		}
		if (e.getSource() == localMP)
		{
			mpo.setLocalMultiplayer(true);
			cl.show(contentPane, "MultiOptions");
		}
		if (e.getSource() == hostMP) {
			new HostMP();
		}
	}

	public Image loadImage(String ImageName)
	{
		return Toolkit.getDefaultToolkit().getImage(getClass().getResource(ImageName));
	}
	
}
