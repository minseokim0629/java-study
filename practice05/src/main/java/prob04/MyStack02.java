package prob04;

public class MyStack02 {
	private int top;
	private Object[] buffer;

	public MyStack02(int capacity) {
		/* 구현하기 */
		buffer = new String[capacity];
		top = -1;
	}

	public void push(Object s) { // Object 자식들은 다 사용 가능 → 다형성
		/* 구현하기 */
		if (top == buffer.length - 1) {
			resize();
		}
		buffer[++top] = s;
	}

	public Object pop() throws MyStackException {
		/* 구현하기 */
		if (isEmpty()) {
			throw new MyStackException("stack is empty");
		}
		Object result = buffer[top];
		buffer[top--] = null;
		return result;
	}

	public boolean isEmpty() {
		/* 구현하기 */
		return top == -1;
	}

	private void resize() {
		/* 구현하기 */
		Object[] temp = new Object[buffer.length * 2];
		for (int i = 0; i < buffer.length; i++) {
			temp[i] = buffer[i];
		}
		buffer = temp;
	}
}
