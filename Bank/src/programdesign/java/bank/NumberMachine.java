package programdesign.java.bank;

class NumberMachine {
    // 返回三个管理器
    private NumberManager commonManager = new NumberManager();
    private NumberManager expressManager = new NumberManager();
    private NumberManager vipManager = new NumberManager();

    // 返回三个get方法
    NumberManager getCommonManager() {
        return commonManager;
    }

    NumberManager getExpressManager() {
        return expressManager;
    }

    NumberManager getVipManager() {
        return vipManager;
    }

    // 对象变成单例，首先构造方法私有化
    private NumberMachine() {
    }

    private static NumberMachine instance = new NumberMachine();

    static NumberMachine getInstance() {
        return instance;
    }
}
