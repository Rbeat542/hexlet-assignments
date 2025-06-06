package exercise;

import org.apache.commons.lang3.ArrayUtils;

class SafetyList {
    // BEGIN
    private int[] x;

    public synchronized void add(Integer obj) {
        x = ArrayUtils.add(x, obj);
    }

    public Integer get(Integer index) {
        return x[index];
    }

    public Integer getSize() {
        return x.length;
    }
    // END
}
