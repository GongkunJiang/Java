package action;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class action extends JPanel {
    private static final long serialVersionUID = 1L;
    private final int DELAY = 50;// 转动快慢设置
    private final static Long time = (long) 5000;
    private static Timer timer;
    private int x = 0;

    /**
     * 调用
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("正转");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new action());
        frame.setSize(300, 300);
        frame.setLocation(400, 400);
        frame.setVisible(true);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
        timer.stop();
        frame.setVisible(false);
        frame.dispose();

    }

    /**
     * 面板构造函数，初始化面板。包括Timer 的场景。
     */
    public action() {
        timer = new Timer(DELAY, new ReboundListener());
        timer.start();
    }


    /**
     * 动画效果：不断的更新图像的位置，已达到动画的效果。
     */
    private class ReboundListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (x < 360) {
                x = x - 20;
            } else {
                x = 0;
            }
            repaint();
        }
    }

    /**
     * 绘出图像在面板中的位置
     */
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        drawArc(page);
    }

    /**
     * 画图形
     */
    private void drawArc(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        g2d.setColor(Color.BLACK);
        g2d.drawArc(width / 2 - 110, height / 2 - 110, 10 + 200, 10 + 200, 0, 360);
        g2d.fillArc(width / 2 - 110, height / 2 - 110, 10 + 200, 10 + 200, x, 240);
        g2d.setColor(Color.WHITE);
        g2d.fillArc(width / 2 - 90, height / 2 - 90, 10 + 160, 10 + 160, 0, 360);
        g2d.dispose();
    }
}
