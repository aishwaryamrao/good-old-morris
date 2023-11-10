package production.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import production.logic.Board;

public class Menu {
    private JFrame welcomeWindow = new JFrame("Good Old Morris");
    private boolean isCPUOpponent;
    private Board currentBoard = new Board();
    // creates the Menu frame and containers
    public Menu()
    {
        makeWelcomeFrame();
    }
    private void makeWelcomeFrame()
    {
        JPanel welcomePanel = new JPanel();
        try {
            welcomeWindow.setIconImage(ImageIO.read(getClass().getResource("/resources/images/logo.jpg")));
        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        welcomePanel.setLayout(new GridLayout(0, 1));
        JLabel welcomeText = new JLabel("Welcome to Good Old Morris\n");
        welcomeText.setHorizontalAlignment(JLabel.CENTER);
        welcomeText.setVerticalAlignment(JLabel.CENTER);
        welcomeText.setFont(new Font("Verdana", Font.ITALIC, 24));
        JLabel optionsText = new JLabel("Choose your options:\n");
        JRadioButton radioButtonOption1 = new JRadioButton("Human vs Computer");
        JRadioButton radioButtonOption2 = new JRadioButton("Human vs Human");
        radioButtonOption2.setSelected(true);
        ButtonGroup group = new ButtonGroup();
        group.add(radioButtonOption1);
        group.add(radioButtonOption2);

        JLabel player1Text = new JLabel("Player 1 : ");
        JLabel player2Text = new JLabel("Player 2 : ");

        JPanel buttonPanel = new JPanel();
        JButton aboutButton = new JButton("Help");
        aboutButton.setPreferredSize(new Dimension(150, 25)); // Set preferred size for the button
        buttonPanel.add(aboutButton);
        JPanel buttonPanel1 = new JPanel();
        JButton acceptButton = new JButton("Accept");
        buttonPanel1.setBackground(Color.pink);

        buttonPanel.setBackground(Color.pink);
        acceptButton.setPreferredSize(new Dimension(150, 25)); // Set preferred size for the button
        buttonPanel1.add(acceptButton);
        JPanel buttonPanel2 = new JPanel();
        JButton CancelButton = new JButton("Cancel");
        buttonPanel2.setBackground(Color.pink);
        CancelButton.setPreferredSize(new Dimension(150, 25)); // Set preferred size for the button
        buttonPanel2.add(CancelButton);
        //cancel button implementation
        CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Right-align the component
        JTextField player1Name = new JTextField(10);
        player1Name.setPreferredSize(new Dimension(150, 25)); // Set dimensions
        panel1.add(new JLabel("Name"));
        panel1.add(player1Name);
        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Right-align the component
        JTextField player2Name = new JTextField(10);
        player2Name.setPreferredSize(new Dimension(150, 25)); // Set dimensions

        player1Name.setInputVerifier(new NotNullOrEmptyVerifier());
        player2Name.setInputVerifier(new NotNullOrEmptyVerifier());

        acceptButton.addActionListener(e -> {
            if (player1Name.getInputVerifier().shouldYieldFocus(player1Name)) {
                if (player2Name.getInputVerifier().shouldYieldFocus(player2Name)) {
                    currentBoard.setPlayerOneName(player1Name.getText());
                    currentBoard.setPlayerTwoName(player2Name.getText());
                    GameGUI myFrame = new GameGUI(currentBoard);
                    myFrame.beginGame();
                    myFrame.createNewBoard();
                    myFrame.gameWindow.pack();
                    myFrame.gameWindow.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Player Name cannot be empty", "Validation Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Player Name cannot be empty", "Validation Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel2.add(new JLabel("Name"));
        panel2.add(player2Name);
        radioButtonOption1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player2Text.setVisible(false);
                panel2.setVisible(false);
                isCPUOpponent = true;
            }
        });
        radioButtonOption2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player2Text.setVisible(true);
                panel2.setVisible(true);
                isCPUOpponent = false;
            }
        });
        CancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        aboutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aboutClick();
            }
        });
        welcomePanel.add(welcomeText);
        welcomePanel.add(optionsText);
        welcomePanel.add(radioButtonOption1);
        welcomePanel.add(radioButtonOption2);
        welcomePanel.setPreferredSize(new Dimension(600, 400));
        welcomePanel.setBackground(Color.pink);
        welcomePanel.add(player1Text);
        welcomePanel.add(panel1);
        welcomePanel.add(player2Text);
        welcomePanel.add(panel2);
        welcomePanel.add(new JSeparator());
        welcomePanel.add(buttonPanel);
        welcomePanel.add(buttonPanel1);
        welcomePanel.add(buttonPanel2);

        JScrollPane panelPane = new JScrollPane(welcomePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        welcomeWindow.getContentPane().add(panelPane);
        welcomeWindow.pack();
        welcomeWindow.setLocationRelativeTo(null);
        welcomeWindow.setVisible(true);
    }
    //about message to be displayed when help button is clicked
    public void aboutClick() {
        String aboutMessage = "The basic aim of Nine Mens Morris is to make \"mills\" - vertical or horizontal lines of three in a row.\nEvery time this is achieved, an opponent's piece is removed, the overall objective being to reduce the number of opponent's pieces to less than three or to render the opponent unable to play.";
        JOptionPane.showMessageDialog(welcomeWindow, aboutMessage);
    }
    private void getPlayersName(){
        String p1=JOptionPane.showInputDialog("Player 1 : ");
        currentBoard.setPlayerOneName(p1);
        String p2=JOptionPane.showInputDialog("Player 2 : ");
        currentBoard.setPlayerTwoName(p2);
    }
    public class NotNullOrEmptyVerifier extends InputVerifier {
        @Override
        public boolean verify(JComponent input) {
            if (input instanceof JTextField) {
                JTextField textField = (JTextField) input;
                String text = textField.getText();
                return !text.trim().isEmpty();
            }
            return false;
        }
    }
}

