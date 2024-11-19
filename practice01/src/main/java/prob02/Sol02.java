package prob02;

public class Sol02 {
	public static void main(String[] args) {
		/* 코드 작성 */
		int cnt = 10;
		for (int i = 0; i < 9; i++) {
			for (int j = 1; j <= cnt; j++) {
				System.out.print(j + " ");
			}
			System.out.println();
			cnt++;
		}
	}
}