package cn.com.test;

public class TestSplit {
	public static void main(String[] args) {
		String str = "No0001|YA001:1|YA002:2|YA003:3|YA004:4";
		String str1 = "baicai|kk";
		String []array = str.split("\\|");
		System.out.println(array[0]);
	}
}
