package prob04;

public class MyStack {
	private int top;
	private String[] buffer;

	public MyStack(int capacity) {
		/* 구현하기 */
		buffer = new String[capacity];
		top = -1;
	}

	public void push(String s) {
		/* 구현하기 */
		if (top == buffer.length - 1) {
			resize();
		}
		buffer[++top] = s;
	}

	public String pop() throws MyStackException {
		/* 구현하기 */
		if (isEmpty()) {
			throw new MyStackException("stack is empty");
		}
		String result = buffer[top];
		buffer[top--] = null;
		return result;
	}

	public boolean isEmpty() {
		/* 구현하기 */
		return top == -1;
	}

	private void resize() {
		/* 구현하기 */
		String[] temp = new String[buffer.length * 2];
		for (int i = 0; i < buffer.length; i++) {
			temp[i] = buffer[i];
		}
		buffer = temp;
	}
}