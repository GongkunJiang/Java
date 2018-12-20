package chess;

import javax.swing.*;
import java.awt.*;

public class ChessPlayer extends JFrame {
    private ChessPanel chessPanel = new ChessPanel(20,30);

    public ChessPlayer(String title){
        super(title);

        Container contentPane=getContentPane();
        contentPane.add(chessPanel);
        setJMenuBar(chessPanel.getMenuBar());

        setSize(600,600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String args[]){
        new ChessPlayer("五子棋、围棋");
    }
}
