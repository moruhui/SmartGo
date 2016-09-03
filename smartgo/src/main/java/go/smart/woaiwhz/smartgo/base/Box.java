package go.smart.woaiwhz.smartgo.base;

/**
 * Created by huazhou.whz on 2016/8/24.
 */
public class Box<T> {
    private T item;

    public T get() {
        return item;
    }

    public void set(T item) {
        this.item = item;
    }
}
