package pong;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		setSize(800, 500);
		setResizable(false);

		setIconImage(loadImage("Pong_Icon.png"));
//		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Pong_Icon.png")));
		
		Container contentPane = getContentPane();
		cl = new CardLayout();
		contentPane.setLayout(cl);

		mainMenu = new BackgroundPanel(loadImage("Pong.png"));
		mainMenu.setLayout(new GridLayout(5, 1));
//		mainMenu = new JPanel(new GridLayout(5, 1));
//		mainMenu.setBackground(Color.GREEN);

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
		mainMenu.add(title);

		singleplayer = new JButton("Singleplayer");
		singleplayer.setOpaque(true);
		singleplayer.setContentAreaFilled(false);
		singleplayer.setBorderPainted(false);
		mainMenu.add(singleplayer);
		singleplayer.addActionListener(this);


		multiplayer = new JButton("Multiplayer");
		multiplayer.setOpaque(false);
		multiplayer.setContentAreaFilled(false);
		multiplayer.setBorderPainted(false);
		mainMenu.add(multiplayer);
		multiplayer.addActionListener(this);


		settings = new JButton("Settings");
		settings.setOpaque(false);
		settings.setContentAreaFilled(false);
		settings.setBorderPainted(false);
		mainMenu.add(settings);
		settings.addActionListener(this);


		exit = new JButton("Exit");
		exit.setOpaque(false);
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		mainMenu.add(exit);
		exit.addActionListener(this);
		
		repaint();
//		validate();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == exit)
		{
			System.exit(0);
		}
        if(e.getSource() == singleplayer)
        {
            cl.show(getContentPane(), "canvas");
        }
	}
	
	public Image loadImage(String ImageName)
	{
		return Toolkit.getDefaultToolkit().getImage(getClass().getResource(ImageName));
	}

}
