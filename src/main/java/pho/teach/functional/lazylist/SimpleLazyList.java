package pho.teach.functional.lazylist;

import java.util.List;

public class SimpleLazyList implements LazyList<String> {

    private final List<String> items;

    private SimpleLazyList(List<String> items) {
        this.items = items;
    }

    public static SimpleLazyList of(List<String> items) {
        return new SimpleLazyList(items);
    }

    @Override
    public String head() {
        return items.get(0);
    }

    @Override
    public LazyList<String> tail() {
        return SimpleLazyList.of(items.stream().skip(1).toList());
    }

    @Override
    public boolean isEmpty() {
        return LazyList.super.isEmpty();
    }
}
