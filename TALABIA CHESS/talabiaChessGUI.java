import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class talabiaChessGUI extends JFrame { // by Javier Austin ^.^
    private static int column = 7;
    private static int row = 6;

    public talabiaChessGUI() {
        super("Talabia Chess");
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 400));

        JPanel gridPanel = gridPanel();
        add(gridPanel, BorderLayout.CENTER);

        JPanel buttonPanel = buttonPanel();
        add(buttonPanel, BorderLayout.WEST);
    }

    private JPanel gridPanel() {
        JPanel gridPanel = new JPanel(new GridLayout(row, column));

        for (int i = 0; i < column * row; i++) {
            gridPanel.add(new JButton(i % column + "," + i / column) {
                {
                    setPreferredSize(new Dimension(50, 50));
                    setOpaque(true);
                    setBorderPainted(true);
                }
            });
        }

        return gridPanel;
    }

    private JPanel buttonPanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setPreferredSize(new Dimension(100,400));

        JButton startButton = new JButton("Start");
        buttonPanel.add(startButton);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO add Start Game feature
            }
        });

        JButton saveButton = new JButton("Save");
        buttonPanel.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO Save Game feature
                // prompts the user to input a filename
            }
        });

        JButton loadButton = new JButton("Load");
        buttonPanel.add(loadButton);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO add Load feature
            }
            
        });

        JButton helpButton = new JButton("Help");
        buttonPanel.add(helpButton);
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String helpMessage = Game.helpCommands();

                // Check if the helpMessage is not null before showing the dialog
                if (helpMessage != null) {
                    JOptionPane.showMessageDialog(buttonPanel, helpMessage);
                } else {
                    JOptionPane.showMessageDialog(buttonPanel, "No help available.");
                }
            }
        });

        JButton exitButton = new JButton("Exit");
        buttonPanel.add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.exit();
            }
        });

        return buttonPanel;
    }

    public static void main(String[] args) {
        JFrame f = new talabiaChessGUI();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }
}
