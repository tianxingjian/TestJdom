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
		System.out.println("开始测试！");
		testRequst(file);
	}
	
	private static void testRequst(String filename) throws Exception{
		String url = "http://127.0.0.1:80/service/XChangeServlet";
		URL realURL = new URL(url);
		HttpURLConnection connection = (HttpURLConnection)realURL.openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-type", "text/xml");
		connection.setRequestMethod("POST");
		// 将Document对象写入连接的输出流中,xml数据格式见第二节数据格式，为了
		//测试方便通过文件形式写的，外系统怎么拼xml文档请修改此处
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
		// 从连接的输入流中取得回执信息
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream(), "utf-8"));
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		connection.disconnect();
	}
}
