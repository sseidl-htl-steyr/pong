package pong;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class GUI extends JFrame implements ActionListener
{
	private CardLayout cl;
	private JPanel mainMenu;
	private JPanel canvas;
	private PlayField pf;

	// Menübuttons
	private JTextPane title;
	private JButton singleplayer;
	private JButton multiplayer;
	private JButton settings;
	private JButton exit;

	public GUI()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("BSS Pong");
		setSize(1280, 720);
		setResizable(false);

		setIconImage(loadImage("Pong_Icon.png"));
		// setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Pong_Icon.png")));

		Container contentPane = getContentPane();
		cl = new CardLayout();
		contentPane.setLayout(cl);

		mainMenu = new BackgroundPanel(loadImage("Pong_Test.png"));
		// mainMenu = new BackgroundPanel(loadImage("Pong.png"));
		mainMenu.setLayout(new GridLayout(1, 0));

		Box vBox = Box.createVerticalBox();

		// vBox.add(Box.createRigidArea(new Dimension(0, (int) (getHeight() / 1.5F +
		// 0.5F)))); //Hoehe der Buttons veraendern

		mainMenu.add(vBox);

		canvas = new JPanel();
		canvas.setBackground(Color.WHITE);

		pf = new PlayField(this);
		canvas.setLayout(new BorderLayout());
		canvas.add(pf, BorderLayout.CENTER);
		contentPane.add(canvas);

		contentPane.add(mainMenu);
		contentPane.add(canvas);

		cl.addLayoutComponent(mainMenu, "main");
		cl.addLayoutComponent(canvas, "canvas");

		cl.show(contentPane, "main");

		title = new JTextPane();
		title.setText("Pong");
		title.setEditable(false);
		// mainMenu.add(title);

		singleplayer = new JButton("Singleplayer");
		singleplayer.setSize(getWidth() / 8, 50);
		singleplayer.setMaximumSize(singleplayer.getSize());
		singleplayer.setOpaque(true);
		// singleplayer.setContentAreaFilled(false);
		singleplayer.setBorderPainted(false);
		singleplayer.addActionListener(this);
		vBox.add(singleplayer);

		multiplayer = new JButton("Multiplayer");
		multiplayer.setSize(getWidth() / 8, 50);
		multiplayer.setMaximumSize(singleplayer.getSize());
		multiplayer.setOpaque(false);
		// multiplayer.setContentAreaFilled(false);
		multiplayer.setBorderPainted(false);
		multiplayer.addActionListener(this);
		vBox.add(multiplayer);

		settings = new JButton("Settings");
		settings.setSize(getWidth() / 8, 50);
		settings.setMaximumSize(singleplayer.getSize());
		settings.setOpaque(false);
		// settings.setContentAreaFilled(false);
		settings.setBorderPainted(false);
		settings.addActionListener(this);
		vBox.add(settings);

		exit = new JButton("Exit");
		exit.setSize(getWidth() / 8, 50);
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
			cl.show(getContentPane(), "canvas");
		}
	}

	public Image loadImage(String ImageName)
	{
		return Toolkit.getDefaultToolkit().getImage(getClass().getResource(ImageName));
	}

}
