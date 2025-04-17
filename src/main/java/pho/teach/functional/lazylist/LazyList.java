package pho.teach.functional.lazylist;

public interface LazyList<T> {

    T head();
    LazyList<T> tail();
    default boolean isEmpty() {
        return true;
    }

}
