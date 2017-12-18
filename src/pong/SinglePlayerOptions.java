package pong;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SinglePlayerOptions extends JPanel implements ActionListener
{
    private PlayField pf;
    private Player p;
    private Ball b;

    private JPanel btnPanel;
    private JButton b_easy;
    private JButton b_medium;
    private JButton b_hard;

    private boolean EnemyBot = true;
    private String difficulty;

    public SinglePlayerOptions()
    {
        super();

        b_easy = new JButton("Easy");
        b_medium = new JButton("Medium");
        b_hard = new JButton("Hard");

        b_easy.addActionListener(this);
        b_medium.addActionListener(this);
        b_hard.addActionListener(this);

        btnPanel = new JPanel(new FlowLayout());
        btnPanel.add(b_easy);
        btnPanel.add(b_medium);
        btnPanel.add(b_hard);
        btnPanel.setBackground(Color.black);

        pf = new PlayField();

        this.setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        add(btnPanel, BorderLayout.NORTH);
        add(pf, BorderLayout.CENTER);
    }

    public boolean isEnemyBot()
    {
        return EnemyBot;
    }

    public void setEnemyBot(boolean enemyBot)
    {
        EnemyBot = enemyBot;
    }

    public String getDifficulty()
    {
        return difficulty;
    }

    public void setDifficulty(String difficulty)
    {
        this.difficulty = difficulty;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == b_easy)
        {
            difficulty = "easy";
        }
        if (e.getSource() == b_medium)
        {
            difficulty = "medium";
        }
        if (e.getSource() == b_hard)
        {
            difficulty = "hard";
        }
        pf.requestFocus();
    }
}
