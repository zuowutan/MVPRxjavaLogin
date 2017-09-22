package com.example.tzw.mvprxjavalogin.tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MD5Tool {
	/**
	 * 加密
	 * @param data
	 * @return
	 */
	public static String calcMD5( byte[] data ) {
		MessageDigest md = null;
		try{
			md = MessageDigest.getInstance( "MD5" );
		}catch( NoSuchAlgorithmException e ){
			return "";
		}
		// 生成MessageDigest
		md.update( data );
		byte[] hash = md.digest();
		// 转换为字符串
		StringBuffer sbRet = new StringBuffer();
		for( int i = 0 ; i<hash.length ; i++ ){
			int v = hash[i]&0xFF;
			if( v<16 )
				sbRet.append( "0" );
			sbRet.append( Integer.toString( v,16 ) );
		}
		return sbRet.toString();
	}

	public static String calcMD5(String input, String charset) {
		try {
			return calcMD5(input.getBytes(charset));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}
