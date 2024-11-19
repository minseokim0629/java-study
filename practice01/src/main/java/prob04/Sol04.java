package prob04;

import java.util.Scanner;

public class Sol04 {

	public static void main(String[] args) {

		/* 코드 작성 */
		System.out.print("문자열을 입력하세요 : ");
		Scanner scanner = new Scanner(System.in);
		String s = scanner.nextLine();
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j <= i; j++) {
				System.out.print(s.charAt(j));
			}
			System.out.println();
		}
		scanner.close();
	}
}