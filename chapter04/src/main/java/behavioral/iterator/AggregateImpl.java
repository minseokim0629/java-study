package behavioral.iterator;

public class AggregateImpl<E> implements Aggregate<E> {
	private E[] datas;
	
	public AggregateImpl(E[] datas) {
		this.datas = datas;
	}
	
	@Override
	public Iterator<E> createIterator() {
		return new IteratorImpl();
	}
	
	//AgreegateImpl<E> 타입으로 선언했기 떄문에 IteratorImpl<E>로 하면 에러남 <T>로 해주거나 아님 지우거나
	private class IteratorImpl implements Iterator<E>{
		private int index = 0;
		
		@Override
		public E next() {
			return index < datas.length ? datas[index++] : null;
		}

		@Override
		public boolean hasNext() {
			return index < datas.length;
		}
		
	}
}
