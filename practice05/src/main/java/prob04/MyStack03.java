package prob04;

public class MyStack03<T> {
	private int top;
	private T[] buffer;

	@SuppressWarnings("unchecked")
	public MyStack03(int capacity) {
		/* 구현하기 */
		buffer = (T[])new Object[capacity];
		top = -1;
	}

	public void push(T s) { 
		/* 구현하기 */
		if (top == buffer.length - 1) {
			resize();
		}
		buffer[++top] = s;
	}

	public T pop() throws MyStackException {
		/* 구현하기 */
		if (isEmpty()) {
			throw new MyStackException("stack is empty");
		}
		T result = buffer[top];
		buffer[top--] = null;
		return result;
	}

	public boolean isEmpty() {
		/* 구현하기 */
		return top == -1;
	}

	@SuppressWarnings("unchecked")
	private void resize() {
		/* 구현하기 */
		T[] temp = (T[])new Object[buffer.length * 2];
		for (int i = 0; i < buffer.length; i++) {
			temp[i] = buffer[i];
		}
		buffer = temp;
	}
}
