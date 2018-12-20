package programdesign.java.bank;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * 没有把VIP窗口和快速窗口做成子类，是因为实际业务中的普通窗口可以随时被设置为VIP窗口和快速窗口。
 */
public class ServiceWindow {
    private static Logger logger = Logger.getLogger("programdesign.java.bank");
    private CustomerType type = CustomerType.COMMON;
    private int number = 1;

    public CustomerType getType() {
        return type;
    }

    void setType(CustomerType type) {
        this.type = type;
    }

    void setNumber(int number) {
        this.number = number;
    }

    void start() {
        Executors.newSingleThreadExecutor().execute(
                new Runnable() {
                    public void run() {
                        //下面这种写法的运行效率低，最好是把while放在case下面
                        while (true) {
                            switch (type) {
                                case COMMON:
                                    commonService();
                                    break;
                                case EXPRESS:
                                    expressService();
                                    break;
                                case VIP:
                                    vipService();
                                    break;
                            }
                        }
                    }
                }
        );
    }

    private void commonService() {
        String windowName = "【" + number + "号《" + type + "窗口》】";
        System.out.println(windowName + "开始获取『普通任务』!");
        Integer serviceNumber = NumberMachine.getInstance().getCommonManager().fetchNumber();
        if (serviceNumber != null) {
            System.out.println(windowName + "开始为【" + serviceNumber + "号《普通客户》】服务");
            int maxRandom = Constants.MAX_SERVICE_TIME - Constants.MIN_SERVICE_TIME;
            int serviceTime = new Random().nextInt(maxRandom) + 1 + Constants.MIN_SERVICE_TIME;

            try {
                Thread.sleep(serviceTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(windowName + "完成为【" + serviceNumber + "号《普通客户》】服务，总共耗时" + serviceTime / 1000 + "秒");
        } else {
            System.out.println(windowName + "没有取到『普通任务』，正在空闲一秒");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void expressService() {
        Integer serviceNumber = NumberMachine.getInstance().getExpressManager().fetchNumber();
        String windowName = "【" + number + "号《" + type + "窗口》】";
        System.out.println(windowName + "开始获取『快速任务』!");
        if (serviceNumber != null) {
            System.out.println(windowName + "开始为【" + serviceNumber + "号《快速客户》】服务");
            int serviceTime = Constants.MIN_SERVICE_TIME;
            try {
                Thread.sleep(serviceTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(windowName + "完成为【" + serviceNumber + "号《快速客户》】服务，总共耗时" + serviceTime / 1000 + "秒");
        } else {
            System.out.println(windowName + "没有取到『快速任务』！");
            commonService();
        }
    }

    private void vipService() {

        Integer serviceNumber = NumberMachine.getInstance().getVipManager().fetchNumber();
        String windowName = "【" + number + "号《" + type + "窗口》】";
        System.out.println(windowName + "『VIP任务』!");
        if (serviceNumber != null) {
            System.out.println(windowName + "开始为【" + serviceNumber + "号《VIP客户》】服务");
            int maxRandom = Constants.MAX_SERVICE_TIME - Constants.MIN_SERVICE_TIME;
            int serviceTime = new Random().nextInt(maxRandom) + 1 + Constants.MIN_SERVICE_TIME;
            try {
                Thread.sleep(serviceTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(windowName + "完成为【" + serviceNumber + "号《VIP客户》】服务，总共耗时" + serviceTime / 1000 + "秒");
        } else {
            System.out.println(windowName + "没有取到『VIP任务』！");
            commonService();
        }
    }
}
