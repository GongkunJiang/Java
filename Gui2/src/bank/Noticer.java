package bank;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

class Noticer {
    static void WelcomeNotice(int size) {
        if (size > 30) {
            JOptionPane op = new JOptionPane("欢迎光临！", JOptionPane.INFORMATION_MESSAGE);
            show(op);
        }
    }

    static void TicketNotice(Boolean had, int No) {
        if (had) {
            if (No > 30) {
                JOptionPane op = new JOptionPane(No + "号客户取号成功。", JOptionPane.INFORMATION_MESSAGE);
                show(op);
            }
        } else {
            JOptionPane op = new JOptionPane("取票失败，请先点击【入口】或者直接选择业务类型！", JOptionPane.INFORMATION_MESSAGE);
            show(op);
        }
    }

    static void WaitNotice(Boolean had) {
        if (had) {
            JOptionPane op = new JOptionPane("入座后，请您稍等片刻。", JOptionPane.INFORMATION_MESSAGE);
            show(op);
        } else {
            JOptionPane op = new JOptionPane("您还没有取号，请您先取号再入座！", JOptionPane.INFORMATION_MESSAGE);
            show(op);
        }
    }

    static void ServiceNotice(Boolean state, int service_No, int custome_No) {
        if (!state) {
            JOptionPane op = new JOptionPane(service_No + 1 + "号服务窗口正在为" + custome_No + "号客户服务！", JOptionPane.INFORMATION_MESSAGE);
            show(op);
        } else {
            JOptionPane op = new JOptionPane(service_No + 1 + "号服务窗口完成" + custome_No + "号客户服务，窗口空闲……", JOptionPane.INFORMATION_MESSAGE);
            show(op);
        }
    }


    static void GoodbyeNotice() {
        JOptionPane op = new JOptionPane("谢谢光临，再见！", JOptionPane.INFORMATION_MESSAGE);
        show(op);
    }

    private static void show(JOptionPane op) {
        final JDialog dialog = op.createDialog("系统提示");

        // 创建一个新计时器
        Timer timer = new Timer();

        // 1秒 后执行该任务
        timer.schedule(new TimerTask() {
            public void run() {
                dialog.setVisible(false);
                dialog.dispose();
            }
        }, 2000);

        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setAlwaysOnTop(true);
        dialog.setModal(false);
        dialog.setVisible(true);
    }
}
