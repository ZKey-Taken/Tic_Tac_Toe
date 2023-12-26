package GamePackage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board{

    private ArrayList<JPanel> panels = new ArrayList<>();
    private ArrayList<JButton> buttons = new ArrayList<>();
    private Player player1;
    private Player player2;
    private JLabel p1_score;
    private JLabel p2_score;

    private boolean player1_turn = true;
    private int counter = 0;

    Board(String p1_name, String p2_name){
        this.player1 = new Player(p1_name, Color.red);
        this.player2 = new Player(p2_name, Color.blue);
    }

    Board(String p1_name, Color p1_color, String p2_name, Color p2_color){
        this.player1 = new Player(p1_name, p1_color);
        this.player2 = new Player(p2_name, p2_color);
    }

    public void setupGrids(JFrame frame, GridBagConstraints gbc){
        gbc.gridx = 1;
        gbc.gridy = 1;
        for(int i = 1; i <= 9; i++){
            JPanel panel = new JPanel();
            JButton button  = new JButton(String.valueOf(i));
            button.setFont(new Font("Arial", Font.PLAIN, 0));
            button.setMargin(new Insets(60, 50, 60, 50));
            panel.add(button);
            frame.add(panel, gbc);
            panels.add(panel);
            buttons.add(button);
            gbc.gridx++;
            if(i % 3 == 0){
                gbc.gridy++;
                gbc.gridx = 1;
            }
        }
    }

    public void setUpPlayersUI(JFrame frame, GridBagConstraints gbc){
        // Add GamePackage.Player 1 Names & Score
        JLabel p1_name = new JLabel(this.getPlayer1().getName() + "     ");
        p1_name.setFont(new Font("Impact", Font.PLAIN, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(p1_name, gbc);
        p1_score = new JLabel(this.getPlayer1().getScore() + "  ");
        p1_score.setFont(new Font("Bazooka", Font.BOLD, 50));
        p1_score.setForeground(this.getPlayer1().getColor());
        gbc.gridy = 1;
        frame.add(p1_score, gbc);

        // Add GamePackage.Player 2 Names & Score
        JLabel p2_name = new JLabel( "     " + this.getPlayer2().getName());
        p2_name.setFont(new Font("Impact", Font.PLAIN, 30));
        gbc.gridx = 10;
        gbc.gridy = 0;
        frame.add(p2_name, gbc);
        p2_score = new JLabel("  " + this.getPlayer2().getScore());
        p2_score.setFont(new Font("Bazooka", Font.BOLD, 50));
        p2_score.setForeground(this.getPlayer2().getColor());
        gbc.gridy = 1;
        frame.add(p2_score, gbc);
    }

    public void place(JFrame frame, int index){
        buttons.get(index).setEnabled(false);
        if(player1_turn) {
            panels.get(index).setBackground(player1.getColor());
        }else{
            panels.get(index).setBackground(player2.getColor());
        }
        checkWin(frame);
        checkDraw(frame);
        switchPlayersTurns();
    }

    private void switchPlayersTurns(){
        if(player1_turn)
            player1_turn = false;
        else
            player1_turn = true;
    }

    private void updateScores(){
        p1_score.setText(this.getPlayer1().getScore() + "  ");
        p2_score.setText("  " + this.getPlayer2().getScore());
    }

    private void resetBoard(){
        for(int i = 0; i < buttons.size(); i++){
            buttons.get(i).setEnabled(true);
            Color defaultColor = UIManager.getColor ( "Panel.background" );
            panels.get(i).setBackground(defaultColor);
        }
        counter = 0;
        player1_turn = true;
        updateScores();
    }

    private void checkDraw(JFrame frame){
        counter++;
        if(counter == 9){
            JOptionPane.showMessageDialog(null, "Draw !!!");
            //resetBoard();
            System.exit(0);
        }
    }
    private void checkWin(JFrame frame){
        /* Grid:
        0 1 2
        3 4 5
        6 7 8
         */

        // Horizontal & Vertical Checks
        for(int i = 0; i < 3; i++){
            if( (panels.get(i).getBackground() == player1.getColor()
            && panels.get(i).getBackground() == panels.get(3 + i).getBackground()
            && panels.get(i).getBackground() == panels.get(6 + i).getBackground())
                    ||
                (panels.get(3 * i).getBackground() == player1.getColor()
                        && panels.get(i).getBackground() == panels.get((3 * i) + 1).getBackground()
                        && panels.get(i).getBackground() == panels.get((3 * i) + 2).getBackground())
            ){
                this.getPlayer1().addScore();
                JOptionPane.showMessageDialog(null, player1.getName() + " Won !!!");
                //resetBoard();
                System.exit(0);
            }else if( (panels.get(i).getBackground() == player2.getColor()
                    && panels.get(i).getBackground() == panels.get(3 + i).getBackground()
                    && panels.get(i).getBackground() == panels.get(6 + i).getBackground())
                    ||
                    (panels.get(3 * i).getBackground() == player2.getColor()
                            && panels.get(i).getBackground() == panels.get((3 * i) + 1).getBackground()
                            && panels.get(i).getBackground() == panels.get((3 * i) + 2).getBackground())
            ){
                this.getPlayer2().addScore();
                JOptionPane.showMessageDialog(null, player2.getName() + " Won !!!");
                //resetBoard();
                System.exit(0);
            }
        }

        // Diagonal Checks
        if( (panels.get(0).getBackground() == player1.getColor()
        && panels.get(0).getBackground() == panels.get(4).getBackground()
        && panels.get(0).getBackground() == panels.get(8).getBackground())
                ||
                (panels.get(2).getBackground() == player1.getColor()
                        && panels.get(2).getBackground() == panels.get(4).getBackground()
                        && panels.get(2).getBackground() == panels.get(6).getBackground())
        ){
            this.getPlayer1().addScore();
            JOptionPane.showMessageDialog(null, player1.getName() + " Won !!!");
            //resetBoard();
            System.exit(0);
        }else if( (panels.get(0).getBackground() == player2.getColor()
                && panels.get(0).getBackground() == panels.get(4).getBackground()
                && panels.get(0).getBackground() == panels.get(8).getBackground())
                ||
                (panels.get(2).getBackground() == player2.getColor()
                        && panels.get(2).getBackground() == panels.get(4).getBackground()
                        && panels.get(2).getBackground() == panels.get(6).getBackground())
        ){
            this.getPlayer2().addScore();
            JOptionPane.showMessageDialog(null, player2.getName() + " Won !!!");
            //resetBoard();
            System.exit(0);
        }
    }

    public Player getPlayer1() {
        return player1;
    }
    public Player getPlayer2() {
        return player2;
    }
    public ArrayList<JButton> getButtons() {
        return buttons;
    }
    public ArrayList<JPanel> getPanels() {
        return panels;
    }
}
