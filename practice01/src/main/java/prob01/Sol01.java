package prob01;

import java.util.Scanner;

public class Sol01 {
	public static void main(String[] args) {
		
		/* 코드 작성 */
		
		System.out.print("수를 입력하세요 : ");
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		if(a%3==0) {
			System.out.println("3의 배수 입니다.");
		}
		scanner.close();
	}
}