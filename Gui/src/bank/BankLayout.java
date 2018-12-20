package bank;


import javax.swing.*;
import java.awt.*;

public class BankLayout {
    public static void main(String[] args){
        JFrame f = new JFrame();
        f.setSize(865,650);
        Bankpanel b = new Bankpanel();
        b.setBackground(Color.white);
        f.add(b);
        f.setVisible(true);
    }
}
