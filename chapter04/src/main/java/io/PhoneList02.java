package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PhoneList02 {

	public static void main(String[] args) {
		Scanner scanner = null;

		try {
			File file = new File("./phone.txt");
			if (!file.exists()) {
				System.out.println("File Not Found");
				return;
			}

			System.out.println("== 파일정보 ==");
			System.out.println(file.getAbsolutePath()); // 절대경로
			System.out.println(file.length() + "bytes");
			// Long timestamp = file.lastModified();
			// Date date = new Date(timestamp);
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(file.lastModified())));

			System.out.println("== 전화번호 ==");

			scanner = new Scanner(file);

			// 4. 처리
			while (scanner.hasNextLine()) {
				// token 가져옴. default 설정 : \t, space
				String name = scanner.next();
				String phone01 = scanner.next();
				String phone02 = scanner.next();
				String phone03 = scanner.next();

				System.out.println(name + ":" + phone01 + "-" + phone02 + "-" + phone03);
			}
		} catch (IOException e) { // FileNotFoundException의 부모가 IOException
			System.out.println("error:" + e);
		} finally {
			scanner.close(); // 내부적으로 IOException 처리를 해서 따로 처리해줄 필요 없음
		}
	}

}
