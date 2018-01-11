package pong;

import java.awt.BorderLayout;
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
	public CardLayout cl;
	private JPanel mainMenu;
	public Container contentPane;
	private SinglePlayerOptions spo;
	private MPField mpField;
	private JPanel singlePlayerOpt;
	private JPanel multiPlayerOpt;
	private JPanel multiPlayerOnline;
	private LocalMP mpo;
	private boolean bool_single = false;
	private boolean bool_multi = false;

	

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




		spo = new SinglePlayerOptions();

		
		singlePlayerOpt = new JPanel();
		singlePlayerOpt.setLayout(new BorderLayout());
		singlePlayerOpt.add(spo, BorderLayout.CENTER);
		
		contentPane.add(singlePlayerOpt);

		cl.addLayoutComponent(singlePlayerOpt, "SingelOptions");

		mpField = new MPField();

		multiPlayerOnline = new JPanel();
		multiPlayerOnline.setLayout(new BorderLayout());
		multiPlayerOnline.add(mpField, BorderLayout.CENTER);

		contentPane.add(multiPlayerOnline);

		
		cl.addLayoutComponent(multiPlayerOnline, "OnlineMP");
		
		mpo = new LocalMP();

		multiPlayerOpt = new JPanel();
		multiPlayerOpt.setLayout(new BorderLayout());
		multiPlayerOpt.add(mpo, BorderLayout.CENTER);

		contentPane.add(multiPlayerOpt);

		
		cl.addLayoutComponent(multiPlayerOpt, "MultiOptions");

		contentPane.add(mainMenu);

		cl.addLayoutComponent(mainMenu, "main");

		cl.show(contentPane, "main");
		

		
		singleplayer = new BackgroundButton(loadImage("SingleplayerButton.png"));
		singleplayer.setSize(getWidth() / 4, 100);
		int x = (getWidth()/2)-(singleplayer.getWidth()/2);
		singleplayer.setBounds(x, getHeight()/2+70, getWidth()/4, 50);

		//singleplayer.setBounds(x, getHeight()/2+70, getWidth()/4, 50);
		singleplayer.setMaximumSize(singleplayer.getSize());
		singleplayer.setOpaque(false);
		singleplayer.setContentAreaFilled(false);
		singleplayer.setBorderPainted(false);
		singleplayer.addActionListener(this);
		mainMenu.add(singleplayer);

		localMP = new BackgroundButton(loadImage("localmp.png"));
		localMP.setMaximumSize(singleplayer.getSize());
		localMP.setBounds(x, getHeight()/2+120, getWidth()/4, 50);
		localMP.setOpaque(false);
		localMP.setContentAreaFilled(false);
		localMP.setBorderPainted(false);
		localMP.addActionListener(this);
		mainMenu.add(localMP);
		localMP.addActionListener(this);
		
		joinMP = new BackgroundButton(loadImage("joinmp.png"));
		joinMP.setBounds(x, getHeight()/2+170, getWidth()/4, 50);
		joinMP.setOpaque(false);
		joinMP.setContentAreaFilled(false);
		joinMP.setBorderPainted(false);
		joinMP.addActionListener(this);
		mainMenu.add(joinMP);

		hostMP = new BackgroundButton(loadImage("Hostmp.png"));
		hostMP.setBounds(x, getHeight()/2+220, getWidth()/4, 50);
		hostMP.setOpaque(false);
		hostMP.setContentAreaFilled(false);
		hostMP.setBorderPainted(false);
		hostMP.addActionListener(this);
		mainMenu.add(hostMP);

		exit = new BackgroundButton(loadImage("exit.png"));
		exit.setMaximumSize(singleplayer.getSize());		
		exit.setBounds(x, getHeight()/2+270, getWidth()/4, 50);
		exit.setOpaque(false);
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		exit.addActionListener(this);
		mainMenu.add(exit);


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
			new ManageMP("host");
			cl.show(contentPane, "OnlineMP");
		}
		if(e.getSource() == joinMP) {
			new ManageMP("join");
			cl.show(contentPane, "OnlineMP");
		} 
	}

//	public void showMPField() {
//		cl.show(contentPane, "OnlineMP");
//	}
	
	public Image loadImage(String ImageName)
	{
		return Toolkit.getDefaultToolkit().getImage(getClass().getResource(ImageName));
	}
	
}
