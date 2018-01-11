package pong;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HostMP extends JFrame implements ActionListener{
	public JLabel l_host;
	public JLabel l_port;
	public JLabel l_username;
	
	public JPanel contentPane;
	public FlowLayout fl;
	public GridLayout gl;
	
	public JTextField t_username;
	public JTextField t_host;
	public JTextField t_port;
	public JButton b_enter;

	
	public HostMP() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("BSS Pong");
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
		if(arg0.getSource() == b_enter) {
			// TODO: Check Connection, Open PlayField
		}
	}
}
