package programdesign.java.bank;

import java.util.ArrayList;
import java.util.List;

class NumberManager {
    private int lastNumber = 0;
    private List<Integer> queueNumbers = new ArrayList<Integer>();

    synchronized Integer generateNewNumber() {
        queueNumbers.add(++lastNumber);
        return lastNumber;
    }

    synchronized Integer fetchNumber() {
        if (queueNumbers.size() > 0) {
            return (Integer) queueNumbers.remove(0);
        } else {
            return null;
        }
    }
}