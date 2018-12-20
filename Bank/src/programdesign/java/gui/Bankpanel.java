package bank;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


class Bankpanel extends JPanel {
    final int service_window_Height = 50;
    final int service_window_Width = 130;
    final int wait_room = 40;
    private List<Custome> customer_list = new ArrayList<Custome>();
    private Custome[][] wait_list = new Custome[5][12];
    private Custome[] service_list = new Custome[6];

    Bankpanel() {
        Custome c= create_come_customer();
        convert_type_customer(c,"member",1);
        convert_wait_custom(c);
        convert_service_custom(c,5);
        convert_exit_customer(c);
//        convert_public_customer(c,9);
//        create_exit_customer();
//        for (int i = 0; i < 60; i++)
//            create_wait_custom(i);
//        for(int j=0;j<8;j++){
//            create_service_custom(j,j);
//        }
//        create_common_customer(61);
//        create_express_customer(62);
//        create_vip_customer(63);
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

    private void convert_service_custom(Custome c,int service_No){
//        if (service_No < 6) {
//            c = new Custome(Color.CYAN, 55 + service_No * 80, 100, custome_No, 60 + service_No * 80, 115);
//        }
//        else if(service_No<9){
//            c = new Custome(Color.GREEN, 55 + service_No * 80, 100, custome_No, 60 + service_No * 80, 115);
//        }
//        else{
//            c = new Custome(Color.pink, 55 + service_No * 80, 100, custome_No, 60 + service_No * 80, 115);
//        }
        c.setX1((service_No+1) * service_window_Width-45);
        c.setY1(100);
        c.setX2((service_No+1) * service_window_Width-40);
        c.setY2(115);
        service_list[service_No] = c;
    }

    private void convert_wait_custom(Custome c) {
//        Custome common = new Custome(Color.CYAN, 130, 380, No+"", 135, 395);
//        Custome c = new Custome(Color.pink, 250 + 10, 400 - 30, No, 250 + 5 + 10, 400 - 30 + 15);
        outterLoop:
        for (int i = 3; i < wait_list.length; i++) {
            for (int j = 4; j < wait_list[i].length; j++)
                if (wait_list[i][j] == null) {
                    c.setX1(c.getX1()*2 + j * 40);
                    c.setY1(c.getY1()-10 - i * 40);
                    c.setX2(c.getX2()+130 + j * 40);
                    c.setY2(c.getY2()-10 - i * 40);
                    wait_list[i][j] = c;
//                    customer_list.add(c);
                    break outterLoop;
                }
        }
    }

    private Custome create_come_customer() {
        Custome come = new Custome(Color.gray, 130, 580, 0, 135, 595);
        customer_list.add(come);
        return come;
    }

    private void convert_exit_customer(Custome c) {
        for(int i=0; i<6;i++)
            if(service_list[i] == c)
                service_list[i] = null;
        c.setFillcolor(Color.gray);
        c.setNo(0);
        c.setX1(790);
        c.setY1(580);
        c.setX2(795);
        c.setY2(595);
//       Custome(Color.gray, 790, 580, 0, 795, 595);
    }

    private void convert_type_customer(Custome c,String type,int No) {
        switch (type){
            case "common":c.setFillcolor(Color.CYAN);break;
            case "topublic":c.setFillcolor(Color.GREEN);break;
            case "member":c.setFillcolor(Color.PINK);break;
        }
        c.setY1(c.getY1()-200);
        c.setY2(c.getY2()-200);
        c.setNo(No);
//        Custome common = new Custome(Color.CYAN, 130, 380, No+"", 135, 395);
//        Custome come = new Custome(Color.gray, 130, 580, 0, 135, 595);
//        customer_list.add(common);
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
            g.drawLine(250, 400 - i * wait_room, 730, 400 - i * wait_room);
        for (int j = 0; j < 13; j++)
            g.drawLine(250 + wait_room * j, 400, 250 + wait_room * j, 200);
    }

    private void draw_customer(Graphics g, Color cyan, int x1, int y1, int No, int x2, int y2) {
        g.setColor(cyan);
        g.fillOval(x1, y1, 20, 20);
        g.setColor(Color.black);
        g.drawString(No+"", x2, y2);
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
        String str;
        int state;
        for (int j = 0; j < 6; j++) {
            state = 0;
            if (j < 4) {
                str =  "号普通窗口";
                g.setColor(Color.CYAN);
                g.fillRect(j * service_window_Width + 25, 25, service_window_Width, service_window_Height);

            } else if (j < 5) {
                str =  "号对公窗口";
                g.setColor(Color.GREEN);
                g.fillRect(j * service_window_Width + 25, 25, service_window_Width, service_window_Height);
            } else {
                str =  "号会员窗口";
                g.setColor(Color.PINK);
                g.fillRect(j * service_window_Width + 25, 25, service_window_Width, service_window_Height);
            }
            g.setColor(Color.BLUE);
            g.drawString((j + 1) + str, 65 + j * service_window_Width, 55);
            if(service_list[j]!=null)
                state = 1;
            g.setColor(state == 0 ? Color.white : Color.RED);
            g.drawString("◉", (j+1) * service_window_Width, service_window_Height+20);
        }
        g.setColor(Color.gray);
        g.drawLine(25, 25, 805, 25);
        g.drawLine(25, 75, 805, 75);
        for (int i = 0; i < 7; i++)
            g.drawLine(i * service_window_Width + 25, 75, i * service_window_Width + 25, 25);
    }
}

