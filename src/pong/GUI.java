package pong;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame implements ActionListener
{
	private CardLayout cl;
	private JPanel mainMenu;
	private JPanel canvas;

	
	
	//Menübuttons
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

		Container contentPane = getContentPane();
		cl = new CardLayout();
		contentPane.setLayout(cl);
		
		mainMenu = new JPanel(new GridLayout(4, 1));
		mainMenu.setBackground(Color.BLACK);
		
		canvas = new JPanel();
		canvas.setBackground(Color.WHITE);
		
		contentPane.add(mainMenu);
		contentPane.add(canvas);
		
		cl.addLayoutComponent(mainMenu, "main");
		cl.addLayoutComponent(canvas, "canvas");
		
		cl.show(contentPane, "main");
		
		
		singleplayer = new JButton("Singleplayer");
		mainMenu.add(singleplayer);
		
		multiplayer = new JButton("Multiplayer");
		mainMenu.add(multiplayer);
		
		settings = new JButton("Settings");
		mainMenu.add(settings);
		
		exit = new JButton("Exit");
		mainMenu.add(exit);
		exit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == exit)
		{
			System.exit(0);
		}
	}

}
