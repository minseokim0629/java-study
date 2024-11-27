package test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost {

	public static void main(String[] args) {
		try {
			// InetAddress는 추상클래스여서 이렇게 인스턴스를 생성할 수 없음
			// InetAddress i1 = new InetAddress();
			
			InetAddress inetAddress = InetAddress.getLocalHost();
			String hostname = inetAddress.getHostName();
			String hostIPAddress = inetAddress.getHostAddress();
			System.out.println(hostname);
			System.out.println(hostIPAddress);
			
			byte[] IPAddresses = inetAddress.getAddress();
			System.out.println(IPAddresses.length);
			for(byte IPAddress : IPAddresses) {
				//System.out.println(IPAddress);
				System.out.println(IPAddress & 0x000000ff);
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
