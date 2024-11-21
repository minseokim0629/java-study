package chapter03;

public class SwapTest02 {

	public static void main(String[] args) {
		int i = 10;
		int j = 20;
		System.out.println(i + ", " + j);

		swap(i, j);

		System.out.println(i + ", " + j);
	}

	//파라미터 값도 전부 스택에 들어가는 것
	private static void swap(int m, int n) {
		int temp = m;
		m = n;
		n = temp;
	}

}
