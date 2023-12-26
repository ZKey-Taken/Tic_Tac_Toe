package GamePackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class GameRunner implements ActionListener {
    private Board board;
    private JFrame startingFrame = new JFrame("Tic Tac Toe");
    private JFrame inGameFrame = new JFrame("Tic Tac Toe");
    private JButton startButton = new JButton("START");
    private JTextField nameInputBox1 = new JTextField("GamePackage.Player 1");
    private JTextField nameInputBox2 = new JTextField("GamePackage.Player 2");

    GameRunner(){
        setPlayersNames();
    }

    private void setPlayersNames(){
        // Establish JFrame
        startingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startingFrame.setSize(800, 800);

        // Set Fonts/Size
        nameInputBox1.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        nameInputBox1.setBackground(Color.lightGray);
        nameInputBox2.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        nameInputBox2.setBackground(Color.lightGray);
        startButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
        startButton.addActionListener(this);

        // Set Layouts
        GridBagConstraints gbc = new GridBagConstraints();
        startingFrame.setLayout(new GridBagLayout());

        // Add Image
        try {
            JLabel ticTacToeImage = new JLabel();
            ImageIcon ttt = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("NoCopyrightImages/TicTacToe.png")));
            ticTacToeImage.setIcon(ttt);
            gbc.gridx = 1;
            gbc.gridy = 0;
            startingFrame.add(ticTacToeImage, gbc);
        }catch (Exception e){
            System.out.println("Image Not Found");
        }

        // Add TextFields & Buttons
        gbc.gridx = 0;
        gbc.gridy = 1;
        startingFrame.add(nameInputBox1, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        startingFrame.add(nameInputBox2, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 2;
        gbc.gridwidth = 3;
        startingFrame.add(startButton, gbc);

        // Make JFrame visible
        startingFrame.setVisible(true);
    }

    public void beginGame(){
        // Swap and Setup Frame(s)
        inGameFrame.setVisible(true);
        startingFrame.setVisible(false);
        inGameFrame.setSize(startingFrame.getWidth(), startingFrame.getHeight());
        inGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inGameFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.CENTER;

        board.setUpPlayersUI(inGameFrame, gbc);

        board.setupGrids(inGameFrame, gbc);
        this.addButtonActions();

    }


    public static void main(String[] args) {
        GameRunner runner = new GameRunner();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton){
            board = new Board(nameInputBox1.getText(), nameInputBox2.getText());
            this.beginGame();
        }else{
            // e.getActionCommand returns 1 - 9 corresponding to the grids
            int index = Integer.parseInt(e.getActionCommand()) - 1;
            board.place(inGameFrame, index);
        }
    }

    private void addButtonActions(){
        for(JButton button : board.getButtons()){
            button.addActionListener(this);
        }
    }
}
