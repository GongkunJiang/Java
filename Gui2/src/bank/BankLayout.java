package bank;


import javax.swing.*;
import java.awt.*;

public class BankLayout {
    public static void main(String[] args){

         try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame f = new JFrame();
        f.setSize(850,650);
        Bankpanel b = new Bankpanel();
        b.setBackground(Color.white);
        f.add(b);
        f.setVisible(true);
    }
}
