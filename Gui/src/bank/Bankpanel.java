package bank;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


class Bankpanel extends JPanel {

    private List<Custome> customer_list = new ArrayList<Custome>();
    private Custome[][] wait_list = new Custome[5][12];
    private Custome[] service_list = new Custome[10];

    Bankpanel() {
        create_come_customer();
        create_exit_customer();
        for (int i = 0; i < 60; i++)
            create_wait_custom(i);
        for(int j=0;j<8;j++){
            create_service_custom(j,j);
        }
        create_common_customer(61);
        create_express_customer(62);
        create_vip_customer(63);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        service_window(g);
        queuing_machine(g);
        gate(g);
        waiting_zone(g);
        service_zone(g);
        draw_customer_list(g);

//        System.out.println("woc");
//
//        try{
//            Thread.sleep(1000);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//        repaint();
//        Custome come = create_come_customer();
//
//        Custome common = create_common_customer("1");
//
//        Custome  express = create_express_customer("2");
//
//        Custome vip = create_vip_customer("3");
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    private void create_service_custom(int service_No, int custome_No){
        Custome c;
        if (service_No < 6) {
            c = new Custome(Color.CYAN, 55 + service_No * 80, 100, custome_No+"", 60 + service_No * 80, 115);
        }
        else if(service_No<9){
            c = new Custome(Color.GREEN, 55 + service_No * 80, 100, custome_No+"", 60 + service_No * 80, 115);
        }
        else{
            c = new Custome(Color.pink, 55 + service_No * 80, 100, custome_No+"", 60 + service_No * 80, 115);
        }
        service_list[service_No] = c;
        customer_list.add(c);
    }

    private void create_wait_custom(int No) {
        Custome a = new Custome(Color.pink, 250 + 10, 400 - 30, No+"", 250 + 5 + 10, 400 - 30 + 15);
        outterLoop:
        for (int i = 0; i < wait_list.length; i++) {
            for (int j = 0; j < wait_list[i].length; j++)
                if (wait_list[i][j] == null) {
                    a.setX1(a.getX1() + j * 40);
                    a.setX2(a.getX2() + j * 40);
                    a.setY1(a.getY1() - i * 40);
                    a.setY2(a.getY2() - i * 40);
                    wait_list[i][j] = a;
                    customer_list.add(a);
                    break outterLoop;
                }
        }
    }

    private void create_come_customer() {
        Custome come = new Custome(Color.gray, 130, 580, "", 135, 295);
        customer_list.add(come);
    }

    private void create_exit_customer() {
        Custome come = new Custome(Color.gray, 790, 580, "", 135, 295);
        customer_list.add(come);
    }

    private void create_common_customer(int No) {
        Custome common = new Custome(Color.CYAN, 130, 380, No+"", 135, 395);
        customer_list.add(common);
    }

    private void create_express_customer(int No) {
        Custome express = new Custome(Color.GREEN, 130, 280, No+"", 135, 295);
        customer_list.add(express);
    }

    private void create_vip_customer(int No) {
        Custome vip = new Custome(Color.pink, 130, 180, No+"", 135, 195);
        customer_list.add(vip);
    }

    private void draw_customer_list(Graphics g) {
        for (Custome c : customer_list)
            draw_customer(g, c.getFillcolor(), c.getX1(), c.getY1(), c.getNo(), c.getX2(), c.getY2());
    }

    private void service_zone(Graphics g) {
        g.setColor(Color.blue);
        g.drawString("服务区", 10, 100);
    }

    private void waiting_zone(Graphics g) {
        g.setColor(Color.blue);
        g.drawString("等候区", 750, 250);
        g.setColor(Color.gray);
        for (int i = 0; i < 6; i++)
            g.drawLine(250, 400 - i * 40, 730, 400 - i * 40);
        for (int j = 0; j < 13; j++)
            g.drawLine(250 + 40 * j, 400, 250 + 40 * j, 200);
    }

    private void draw_customer(Graphics g, Color cyan, int x1, int y1, String No, int x2, int y2) {
        g.setColor(cyan);
        g.fillOval(x1, y1, 20, 20);
        g.setColor(Color.black);
        g.drawString(No, x2, y2);
    }

    private void gate(Graphics g) {
        g.setColor(Color.blue);
        g.drawString("入口", 50, 590);
        g.drawString("出口", 750, 590);
//        g.drawOval(800, 580, 20, 20);
//        g.setColor(Color.black);
//        g.drawString("n", 800 + 5, 580 + 15);
    }

    private void queuing_machine(Graphics g) {
        g.setColor(Color.gray);
        g.drawOval(100, 450, 100, 100);
        g.setColor(Color.blue);
        g.drawString("取号机", 133, 505);
    }

    private void service_window(Graphics g) {
        final int Height = 50;
        final int Width = 80;
        int state;
        for (int j = 0; j < 10; j++) {
            state = 0;
            if (j < 6) {
                g.setColor(Color.CYAN);
                g.fillRect(j * Width + 25, 25, Width, Height);
                g.setColor(Color.BLUE);
                g.drawString((j + 1) + "号普通窗口", 35 + j * Width, 55);
            } else if (j < 9) {
                g.setColor(Color.GREEN);
                g.fillRect(j * Width + 25, 25, Width, Height);
                g.setColor(Color.BLUE);
                g.drawString((j + 1) + "号快速窗口", 35 + j * Width, 55);
            } else {
                g.setColor(Color.PINK);
                g.fillRect(j * Width + 25, 25, Width, Height);
                g.setColor(Color.BLUE);
                g.drawString((j + 1) + "号VIP窗口", 35 + j * Width, 55);
            }
            if(service_list[j]!=null)
                state = 1;
            g.setColor(state == 0 ? Color.white : Color.RED);
            g.drawString("◉", 90 + j * Width, 70);

        }
        g.setColor(Color.gray);
        g.drawLine(25, 25, 825, 25);
        g.drawLine(25, 75, 825, 75);
        for (int i = 0; i < 11; i++)
            g.drawLine(i * Width + 25, 75, i * Width + 25, 25);
    }
}

