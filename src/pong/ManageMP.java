package pong;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pong.net.IServerConnectionHandler;
import pong.net.ServerThread;

public class ManageMP extends JFrame implements ActionListener, KeyListener{
	public JLabel l_host;
	public JLabel l_port;
	public JLabel l_username;
	
	public int i_port;

	ServerThread server;
	PlayField pf;
	GUI g;
	
	public JPanel contentPane;
	public FlowLayout fl;
	public GridLayout gl;
	
	public JTextField t_username;
	public JTextField t_host;
	public JTextField t_port;
	public JButton b_enter;
	public String joinOrhost;
	private static Socket socket;

    private IServerConnectionHandler handler;

	
	public ManageMP(String s_JoinOrHost) {
		this.joinOrhost = s_JoinOrHost;
				
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		if(s_JoinOrHost == "join") {
			setTitle("Join Game");
		}
		if(s_JoinOrHost == "host") {
			setTitle("Host Game");
		}
		contentPane = (JPanel) getContentPane();
		setSize(250,150);
		setResizable(false);
		
		gl = new GridLayout(4,3);
		gl.setVgap(10);
		gl.setHgap(-30);
		contentPane.setLayout(gl);
		contentPane.setBackground(Color.white);
		
		b_enter = new JButton("Enter");
		b_enter.addActionListener(this);
	
		l_username = new JLabel("   Username:");
		l_port = new JLabel("   Port:");
		l_host = new JLabel("   Host:");
		
		t_username = new JTextField();
		t_port = new JTextField();
		t_host = new JTextField();
		t_username.setSize(100, 20);
		
		t_port.addKeyListener(this);
		t_host.addKeyListener(this);
		t_username.addKeyListener(this);
		
		contentPane.add(l_username);
		contentPane.add(t_username);
		contentPane.add(l_port);
		contentPane.add(t_port);
		contentPane.add(l_host);
		contentPane.add(t_host);
		contentPane.add(b_enter);

		
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == b_enter|| KeyEvent.VK_ENTER==(int)arg0.getSource()) {
			// TODO: Check Connection, Open PlayField
			if(t_port.getText() != "" && t_host.getText() != "" && t_username.getText() != "") {
				i_port = Integer.parseInt(t_port.getText());
				System.out.println(i_port);
				
				if(joinOrhost == "join") {
					try {
						socket = new Socket(t_host.getText(), i_port);
					} catch (IOException e) {
						// TODO Auto-generated catch block
//						new ErrorOptionPane();
						JOptionPane.showMessageDialog(contentPane,
							    "Port or Host is wrong!",
							    "Inane error",
							    JOptionPane.ERROR_MESSAGE);
//						e.printStackTrace();
					}
				} else {
					try {
						server = new ServerThread(i_port, handler);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
//				g.showMPField();
				this.dispose();
			}
		}
	}


	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO Auto-generated method stub
		System.out.println("keypressed");
		
	}


	@Override
	public void keyReleased(KeyEvent e)
	{
		System.out.println("keyreleased");
	}


	@Override
	public void keyTyped(KeyEvent e)
	{
		System.out.println("keyTyped");
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			actionPerformed(new ActionEvent(KeyEvent.VK_ENTER, KeyEvent.VK_ENTER, ""));
		}
		// TODO Auto-generated method stub
		
	}
}
