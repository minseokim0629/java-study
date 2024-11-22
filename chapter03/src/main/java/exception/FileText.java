package exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileText {

	public static void main(String[] args) {
		FileInputStream fis = null; // 지역변수 초기화 필수

		try {
			fis = new FileInputStream("hello.txt");
			int data = fis.read();
			System.out.println((char) data);
		} catch (FileNotFoundException e) {
			System.out.println("error:" + e);
		} catch (IOException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
