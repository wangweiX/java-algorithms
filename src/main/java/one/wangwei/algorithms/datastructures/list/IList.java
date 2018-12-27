package one.wangwei.algorithms.datastructures.list;

/**
 * List Interface
 *
 * @param <T>
 * @author https://wangwei.one/
 * @date 2018/04/27
 */
public interface IList<T> {

    /**
     * add element
     *
     * @param element
     * @return
     */
    public boolean add(T element);

    /**
     * add element at index
     *
     * @param index
     * @param element
     * @return
     */
    public boolean add(int index, T element);

    /**
     * remove element
     *
     * @param element
     * @return
     */
    public boolean remove(T element);

    /**
     * remove element by index
     *
     * @param index
     * @return
     */
    public T remove(int index);

    /**
     * set element by index
     *
     * @param index
     * @param element
     * @return old element
     */
    public T set(int index, T element);

    /**
     * clear list
     */
    public void clear();

    /**
     * contain certain element
     *
     * @param element
     * @return
     */
    public boolean contains(T element);

    /**
     * get list size
     *
     * @return
     */
    public int size();

}
