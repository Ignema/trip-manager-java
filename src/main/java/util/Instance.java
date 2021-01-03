package util;

import java.io.Serializable;

public class Instance implements Serializable {
    private int next = 0;
    public synchronized int getNext() {
        return ++next;
    }
}
