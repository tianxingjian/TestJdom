package cn.com.test;

import nc.vo.framework.rsa.Encode;

public class AdminEncode {
	public static void main(String[] args) {
		Encode encode = new Encode();
		System.out.println(encode.decode("fknocphmbnadnidb"));
	}
}
