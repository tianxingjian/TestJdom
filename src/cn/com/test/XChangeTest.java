package cn.com.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class XChangeTest {
	public static void main(String[] args) throws Exception {
		String file = "src/F1.xml";
		System.out.println("��ʼ���ԣ�");
		testRequst(file);
	}
	
	private static void testRequst(String filename) throws Exception{
		String url = "http://127.0.0.1:80/service/XChangeServlet";
		URL realURL = new URL(url);
		HttpURLConnection connection = (HttpURLConnection)realURL.openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-type", "text/xml");
		connection.setRequestMethod("POST");
		// ��Document����д�����ӵ��������,xml���ݸ�ʽ���ڶ������ݸ�ʽ��Ϊ��
		//���Է���ͨ���ļ���ʽд�ģ���ϵͳ��ôƴxml�ĵ����޸Ĵ˴�
		File file = new File(filename);
		BufferedOutputStream out = new BufferedOutputStream(connection.getOutputStream());
		BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
		int length;
		byte[] buffer = new byte[1000];
		while ((length = input.read(buffer, 0, 1000)) != -1) {
		       out.write(buffer, 0, length);
		}
		input.close();
		out.flush();
		out.close();
		// �����ӵ���������ȡ�û�ִ��Ϣ
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream(), "utf-8"));
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		connection.disconnect();
	}
}
