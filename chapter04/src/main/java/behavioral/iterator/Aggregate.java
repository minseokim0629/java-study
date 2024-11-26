package behavioral.iterator;

// E : element
public interface Aggregate<E> {
	Iterator<E> createIterator();
}
