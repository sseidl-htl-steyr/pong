package pong;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class MPField extends JPanel {
	private PlayField pf;
	private Player p;
	private Ball b;
	private GUI g;

	public MPField() {
		super();
		pf = new PlayField();

		this.setBackground(Color.BLACK);
		setLayout(new BorderLayout());
		add(pf, BorderLayout.CENTER);
		// }
	}
}
