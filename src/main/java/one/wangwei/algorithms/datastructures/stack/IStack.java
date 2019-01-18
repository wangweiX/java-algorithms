package one.wangwei.algorithms.datastructures.stack;

/**
 * Stack
 *
 * @author https://wangwei.one
 * @date 2019/01/09
 */
public interface IStack<T> {

    /**
     * Push into Stack
     *
     * @param value
     * @return
     */
    public boolean push(T value);

    /**
     * Pop from Stack
     *
     * @return
     */
    public T pop();

    /**
     * Peek Top Element
     *
     * @return
     */
    public T peek();

    /**
     * remove element
     *
     * @param value
     * @return
     */
    public boolean remove(T value);

    /**
     * clear stack
     */
    public void clear();

    /**
     * Contain certain element
     *
     * @param value
     * @return
     */
    public boolean contains(T value);

    /**
     * Get Stack size
     *
     * @return
     */
    public int size();

    /**
     * Is empty
     *
     * @return
     */
    public boolean empty();

}
