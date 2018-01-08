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
import javax.swing.BoxLayout;
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
		mainMenu.setLayout(new GridLayout(1, 0));

//		Box vBox = Box.createHorizontalBox();
		Box vBox = Box.createVerticalBox();
//		vBox.add(Box.createGlue());
		vBox.add(Box.createRigidArea(new Dimension(0, (int) (getHeight() / 1.5F + 0.5F)))); // Hoehe der Buttons
																							// veraendern



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
		
//		vBox.add(vBox.createHorizontalGlue());
		singleplayer = new BackgroundButton(loadImage("SingleplayerButton.png"));
		singleplayer.setSize(getWidth() / 4, 100);
//		singleplayer.setAlignmentX(getWidth()/2);
		singleplayer.setMaximumSize(singleplayer.getSize());
		singleplayer.setOpaque(false);
		singleplayer.setContentAreaFilled(false);
		singleplayer.setBorderPainted(false);
		singleplayer.addActionListener(this);
		vBox.add(singleplayer);

		localMP = new BackgroundButton(loadImage("localmp.png"));
		// multiplayer.setSize(getWidth() / 8, 50);
		localMP.setMaximumSize(singleplayer.getSize());
		localMP.setOpaque(false);
		localMP.setContentAreaFilled(false);
		localMP.setBorderPainted(false);
//		multiplayer.setAlignmentX(getWidth()/2);
		localMP.addActionListener(this);
		vBox.add(localMP);
		
		joinMP = new BackgroundButton(loadImage("joinmp.png"));
		// multiplayer.setSize(getWidth() / 8, 50);
		joinMP.setMaximumSize(singleplayer.getSize());
		joinMP.setOpaque(false);
		joinMP.setContentAreaFilled(false);
		joinMP.setBorderPainted(false);
//		multiplayer.setAlignmentX(getWidth()/2);
		joinMP.addActionListener(this);
		vBox.add(joinMP);

		hostMP = new BackgroundButton(loadImage("Hostmp.png"));
		// settings.setSize(getWidth() / 8, 50);
		hostMP.setMaximumSize(singleplayer.getSize());
		hostMP.setOpaque(false);
		hostMP.setContentAreaFilled(false);
		hostMP.setBorderPainted(false);
		hostMP.addActionListener(this);
		vBox.add(hostMP);

		exit = new BackgroundButton(loadImage("exit.png"));
		// exit.setSize(getWidth() / 8, 50);
		exit.setMaximumSize(singleplayer.getSize());
		 exit.setOpaque(false);
//		exit.setBackground(new Color(200, 0, 0));
		 exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		exit.addActionListener(this);
		vBox.add(exit);

//		vBox.add(Box.createGlue());
		mainMenu.add(vBox);

		setVisible(true);
		validate();
//		repaint();
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
		if(e.getSource() == hostMP) {
			new HostMP();
		}
	}

	public Image loadImage(String ImageName)
	{
		return Toolkit.getDefaultToolkit().getImage(getClass().getResource(ImageName));
	}
	
}
