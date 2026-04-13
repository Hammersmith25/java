public interface MyCollection<T>{
    void add(T el);
    void remove(T el);
    boolean contains(T el);
    int length();
    boolean isEmpty();
    void clear();
}