package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class Bankpanel extends JPanel {
    static int count;
    final int service_window_Height = 50;
    final int service_window_Width = 130;
    final int wait_room = 40;
    static int custom_No = 1;
    static int random_index;
    private List<Custome> customer_list = new ArrayList<Custome>();
    private Custome[][] wait_list = new Custome[5][12];
    private Custome[] service_list = new Custome[6];
    private Windows[] windows_list = new Windows[6];
    private String[] type = {"common", "common", "common", "common", "topublic", "member"};

    Bankpanel() {
        while (custom_No <= 30) {
            random_index = (int) (Math.random() * type.length);
            Custome c = create_come_customer();
            convert_type_customer(c, type[random_index], custom_No++);
            convert_wait_custom(c);
        }
        addMouseListener(select_Handler);

    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        service_window(g);
        queuing_machine(g);
        gate(g);
        waiting_zone(g);
        service_zone(g);
        select_zone(g);
        draw_customer_list(g);
        System.out.println(find_min_custom("common").getY1() + "\t" + customer_list.size());
    }

    private MouseListener select_Handler = new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            if (x >= 430 && x <= 470 && y >= 550 && y <= 590) {// 选择普通业务类型
                Custome c = create_come_customer();
                convert_type_customer(c, "common", custom_No++);
                Noticer.TicketNotice(true, custom_No - 1);
            } else if (x >= 480 && x <= 520 && y >= 550 && y <= 590) {//选择对公业务类型
                Custome c = create_come_customer();
                convert_type_customer(c, "topublic", custom_No++);
                Noticer.TicketNotice(true, custom_No - 1);
            } else if (x >= 530 && x <= 570 && y >= 550 && y <= 590) {//选择会员业务类型
                Custome c = create_come_customer();
                convert_type_customer(c, "member", custom_No++);
                Noticer.TicketNotice(true, custom_No - 1);
            } else if (x >= 2 && x <= 122 && y >= 555 && y <= 615) {//入口生成客户
                boolean flag = true;
                while (flag) {
                    for (Custome c : customer_list) {
                        if (c.getX1() == 130 && c.getY1() == 580) {
                            flag = false;
                        } else if (c == customer_list.get(customer_list.size() - 1)) {
                            create_come_customer();
                            Noticer.WelcomeNotice(customer_list.size());
                            break;
                        }
                    }
                }
            } else if (x >= 100 && x <= 200 && y >= 450 && y <= 550) { //取号机
                if (customer_list.get(customer_list.size() - 1).getY1() == 580) {
                    random_index = (int) (Math.random() * type.length);
                    Noticer.TicketNotice(true, custom_No);
                    convert_type_customer(customer_list.get(customer_list.size() - 1), type[random_index], custom_No++);
                } else
                    Noticer.TicketNotice(false, custom_No);
            } else if (x >= 2 && x <= 122 && y >= 360 && y <= 420) {//入座
                for (int i = customer_list.size() - 1; i >= 0; i--) {
                    Custome c = customer_list.get(i);
                    if (c.getY1() == 380) {
                        convert_wait_custom(c);
                        Noticer.WaitNotice(true);
                        break;
                    } else if (i == 0) {
                        Noticer.WaitNotice(false);
                    }
                }
            } else if (x >= 25 && x <= 25 + 6 * service_window_Width && y >= 25 && y <= 25 + service_window_Height) {//服务窗口
                if (x <= 25 + service_window_Width) {//1号普通窗口
                    Custome c = find_min_custom("common");
                    if (windows_list[0].getState() == 0) {
                        convert_service_custom(c, 0);
                        Noticer.ServiceNotice(false, 0, c.getNo());
                    } else if (windows_list[0].getState() == 1) {
                        for (Custome w : customer_list)
                            if (w == service_list[0]) {
                                convert_exit_customer(w);
                                Noticer.ServiceNotice(true, 0, w.getNo());
                                break;
                            }
                        service_list[0] = null;
                    }
                } else if (x <= 25 + 2 * service_window_Width) {//2号普通窗口
                    Custome c = find_min_custom("common");
                    if (windows_list[1].getState() == 0) {
                        convert_service_custom(c, 1);
                        Noticer.ServiceNotice(false, 1, c.getNo());
                    } else if (windows_list[1].getState() == 1) {
                        for (Custome w : customer_list)
                            if (w == service_list[1]) {
                                convert_exit_customer(w);
                                Noticer.ServiceNotice(true, 1, w.getNo());
                                break;
                            }
                        service_list[1] = null;
                    }
                } else if (x <= 25 + 3 * service_window_Width) {//3号普通窗口
                    Custome c = find_min_custom("common");
                    if (windows_list[2].getState() == 0) {
                        convert_service_custom(c, 2);
                        Noticer.ServiceNotice(false, 2, c.getNo());
                    } else if (windows_list[2].getState() == 1) {
                        for (Custome w : customer_list)
                            if (w == service_list[2]) {
                                convert_exit_customer(w);
                                Noticer.ServiceNotice(true, 2, w.getNo());
                                break;
                            }
                        service_list[2] = null;
                    }
                } else if (x <= 25 + 4 * service_window_Width) {//4号普通窗口
                    Custome c = find_min_custom("common");
                    if (windows_list[3].getState() == 0) {
                        convert_service_custom(c, 3);
                        Noticer.ServiceNotice(false, 3, c.getNo());
                    } else if (windows_list[3].getState() == 1) {
                        for (Custome w : customer_list)
                            if (w == service_list[3]) {
                                convert_exit_customer(w);
                                Noticer.ServiceNotice(true, 3, w.getNo());
                                break;
                            }
                        service_list[3] = null;
                    }
                } else if (x <= 25 + 5 * service_window_Width) {//5号对公窗口
                    Custome c = find_min_custom("topublic");
                    if (windows_list[4].getState() == 0) {
                        convert_service_custom(c, 4);
                        Noticer.ServiceNotice(false, 4, c.getNo());
                    } else if (windows_list[4].getState() == 1) {
                        for (Custome w : customer_list)
                            if (w == service_list[4]) {
                                convert_exit_customer(w);
                                Noticer.ServiceNotice(true, 4, w.getNo());
                                break;
                            }
                        service_list[4] = null;
                    }
                } else {//6号会员窗口
                    Custome c = find_min_custom("member");
                    if (windows_list[5].getState() == 0) {
                        convert_service_custom(c, 5);
                        Noticer.ServiceNotice(false, 1, c.getNo());
                    } else if (windows_list[5].getState() == 1) {
                        for (Custome w : customer_list)
                            if (w == service_list[5]) {
                                convert_exit_customer(w);
                                Noticer.ServiceNotice(true, 5, w.getNo());
                                break;
                            }
                        service_list[5] = null;
                    }
                }
            } else if (x >= 702 && x <= 822 && y >= 555 && y <= 615) {
                for (Custome c : customer_list) {
                    if (c.getX1() == 790) {
                        customer_list.remove(c);
                        Noticer.GoodbyeNotice();
                        break;
                    }
                }
//                for (int i = customer_list.size() - 1; i >= 0; i--) {
//                    Custome c = customer_list.get(i);
//                    if (c.getX1() == 790) {
//                        customer_list.remove(c);
//                        break;
//                    }
//                }
            }
            repaint();
        }
    };

    private Custome find_min_custom(String custom_type) {
        Custome m = create_come_customer();
        int num = 9999;
        customer_list.remove(m);

        switch (custom_type) {
            case "common":
                for (Custome c : customer_list)
                    if (c.getFillcolor() == Color.CYAN && c.getNo() < num && c.getY1() != 100) {
                        m = c;
                        num = m.getNo();
                    }
                break;

            case "topublic":
                for (Custome c : customer_list)
                    if (c.getFillcolor() == Color.GREEN && c.getNo() < num && c.getY1() != 100) {
                        m = c;
                        num = m.getNo();
                    }
                break;
            case "member":
                for (Custome c : customer_list)
                    if (c.getFillcolor() == Color.PINK && c.getNo() < num && c.getY1() != 100) {
                        m = c;
                        num = m.getNo();
                    }
                break;
        }
        if (num == 9999)
            m = find_min_custom("common");
        return m;
    }

    private void select_zone(Graphics g) {
        g.drawString("选择业务类型", 350, 590);
        g.drawString("普通", 450, 540);
        g.drawString("对公", 500, 540);
        g.drawString("会员", 550, 540);
        Custome c = new Custome(Color.cyan, 450, 570, 0, 455, 585);
//        g.fillRect(450,570,20,20);
        draw_customer(g, c.getFillcolor(), c.getX1(), c.getY1(), c.getNo(), c.getX2(), c.getY2());
        draw_customer(g, Color.green, c.getX1() + 50, c.getY1(), c.getNo(), c.getX2() + 50, c.getY2());
        draw_customer(g, Color.pink, c.getX1() + 100, c.getY1(), c.getNo(), c.getX2() + 100, c.getY2());
    }

    private void convert_service_custom(Custome c, int service_No) {
//        if (service_No < 6) {
//            c = new Custome(Color.CYAN, 55 + service_No * 80, 100, custome_No, 60 + service_No * 80, 115);
//        }
//        else if(service_No<9){
//            c = new Custome(Color.GREEN, 55 + service_No * 80, 100, custome_No, 60 + service_No * 80, 115);
//        }
//        else{
//            c = new Custome(Color.pink, 55 + service_No * 80, 100, custome_No, 60 + service_No * 80, 115);
//        }
        c.setX1((service_No + 1) * service_window_Width - 45);
        c.setY1(100);
        c.setX2((service_No + 1) * service_window_Width - 40);
        c.setY2(115);
        for (int i = 0; i < wait_list.length; i++) {
            for (int j = 0; j < wait_list[i].length; j++)
                if (wait_list[i][j] == c)
                    wait_list[i][j] = null;
            service_list[service_No] = c;
        }
    }

    private void convert_wait_custom(Custome c) {
//        Custome common = new Custome(Color.CYAN, 130, 380, No+"", 135, 395);
//        Custome c = new Custome(Color.pink, 250 + 10, 400 - 30, No, 250 + 5 + 10, 400 - 30 + 15);
        outterLoop:
        for (int i = 0; i < wait_list.length; i++) {
            for (int j = 0; j < wait_list[i].length; j++)
                if (wait_list[i][j] == null) {
                    c.setX1(c.getX1() * 2 + j * 40);
                    c.setY1(c.getY1() - 10 - i * 40);
                    c.setX2(c.getX1() + 5);
                    c.setY2(c.getY1() + 15);
                    wait_list[i][j] = c;
//                    Noticer.WelcomeNotice(c.getNo());
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
        int count = 0;
        boolean flag = true;

        for (int i = 0; i < 6; i++)
            if (service_list[i] == c)
                service_list[i] = null;
        c.setFillcolor(Color.gray);

        while (flag) {
            for (Custome e : customer_list) {
                if (e.getY1() == 580 - 20 * count) {
                    count++;
                    break;
                } else if (e == customer_list.get(customer_list.size() - 1))
                    flag = false;
            }
        }

        c.setX1(790);
        c.setY1(580 - 20 * count);
        c.setX2(795);
        c.setY2(595 - 20 * count);
//       Custome(Color.gray, 790, 580, 0, 795, 595);
    }

    private void convert_type_customer(Custome c, String type, int No) {
        switch (type) {
            case "common":
                c.setFillcolor(Color.CYAN);
                break;
            case "topublic":
                c.setFillcolor(Color.GREEN);
                break;
            case "member":
                c.setFillcolor(Color.PINK);
                break;
        }

        c.setY1(c.getY1() - 200);
        c.setY2(c.getY2() - 200);
        c.setNo(No);
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
        g.drawString(No + "", x2, y2);
    }

    private void gate(Graphics g) {
        g.setColor(Color.orange);
        g.fillOval(42, 575, 40, 20);
        g.setColor(Color.blue);
        g.drawString("入口", 50, 590);

        g.setColor(Color.orange);
        g.fillOval(742, 575, 40, 20);
        g.setColor(Color.blue);
        g.drawString("出口", 750, 590);

        g.setColor(Color.orange);
        g.fillOval(42, 380, 40, 20);
        g.setColor(Color.blue);
        g.drawString("入座", 50, 395);
    }

    private void queuing_machine(Graphics g) {
        g.setColor(Color.orange);
        g.fillOval(100, 450, 100, 100);
        g.setColor(Color.blue);
        g.drawString("取号机", 133, 505);
    }

    private void service_window(Graphics g) {
        String str;
        for (int j = 0; j < 6; j++) {
            windows_list[j] = new Windows(j * service_window_Width + 25, 25, service_list[j] == null ? 0 : 1);
            if (j < 4) {
                str = "号普通窗口";
                g.setColor(Color.CYAN);
                g.fillRect(j * service_window_Width + 25, 25, service_window_Width, service_window_Height);

            } else if (j < 5) {
                str = "号对公窗口";
                g.setColor(Color.GREEN);
                g.fillRect(j * service_window_Width + 25, 25, service_window_Width, service_window_Height);
            } else {
                str = "号会员窗口";
                g.setColor(Color.PINK);
                g.fillRect(j * service_window_Width + 25, 25, service_window_Width, service_window_Height);
            }
            g.setColor(Color.BLUE);
            g.drawString((j + 1) + str, 65 + j * service_window_Width, 55);
            g.setColor(windows_list[j].getState() == 0 ? Color.white : Color.RED);
            g.drawString("◉", (j + 1) * service_window_Width, service_window_Height + 20);
        }
        g.setColor(Color.gray);
        g.drawLine(25, 25, 805, 25);
        g.drawLine(25, 75, 805, 75);
        for (int i = 0; i < 7; i++)
            g.drawLine(i * service_window_Width + 25, 75, i * service_window_Width + 25, 25);
    }

}



