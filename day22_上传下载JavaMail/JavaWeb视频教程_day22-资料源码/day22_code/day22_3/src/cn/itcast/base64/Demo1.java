package cn.itcast.base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

public class Demo1 {
	@Test
	public void fun1() throws IOException {
		
		// BASE64编码
		String s = "Username";
		BASE64Encoder encoder = new BASE64Encoder();
		s = encoder.encode(s.getBytes("UTF-8"));
		System.out.println(s);
		
		// BASE64解码
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] bytes = decoder.decodeBuffer("dXNlcm5hbWU6");
		System.out.println(new String(bytes, "UTF-8"));
	}
}
