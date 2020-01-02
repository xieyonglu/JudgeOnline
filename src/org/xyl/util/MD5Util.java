package org.xyl.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class MD5Util {
	private static final String HEX_NUMS_STR = "0123456789ABCDEF";
	private static final Integer SALT_LENGTH = 12;

	/**
	 *��16�����ַ���ת�����ֽ�����
	 * 
	 * @paramhex
	 *@return
	 */
	public static byte[] hexStringToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] hexChars = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (HEX_NUMS_STR.indexOf(hexChars[pos]) << 4 | HEX_NUMS_STR
					.indexOf(hexChars[pos + 1]));
		}
		return result;
	}

	/**
	 *��ָ��byte����ת����16�����ַ���
	 * 
	 * @paramb
	 *@return
	 */
	public static String byteToHexString(byte[] b) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			hexString.append(hex.toUpperCase());
		}
		return hexString.toString();
	}

	/**
	 *��֤�����Ƿ�Ϸ�
	 * 
	 * @parampassword
	 *@parampasswordInDb
	 *@return
	 *@throwsNoSuchAlgorithmException
	 *@throwsUnsupportedEncodingException
	 */
	public static boolean validPassword(String password, String passwordInDb)
			{//throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// ��16�����ַ�����ʽ����ת�����ֽ�����
		byte[] pwdInDb = hexStringToByte(passwordInDb);
		// �����α���
		byte[] salt = new byte[SALT_LENGTH];
		// ���δ����ݿ��б���Ŀ����ֽ���������ȡ����
		System.arraycopy(pwdInDb, 0, salt, 0, SALT_LENGTH);
		// ������ϢժҪ����
		MessageDigest md;
		byte[] digest=null;
		byte[] digestInDb=null;
		try {
			md = MessageDigest.getInstance("MD5");
			// �������ݴ�����ϢժҪ����
			md.update(salt);
			// ����������ݴ�����ϢժҪ����
			md.update(password.getBytes("UTF-8"));
			// ��������������ϢժҪ
			digest = md.digest();
			// ����һ���������ݿ��п�����ϢժҪ�ı���
			digestInDb = new byte[pwdInDb.length - SALT_LENGTH];
			// ȡ�����ݿ��п������ϢժҪ
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.arraycopy(pwdInDb, SALT_LENGTH, digestInDb, 0,
						digestInDb.length);
		// �Ƚϸ�������������ɵ���ϢժҪ�����ݿ�����ϢժҪ�Ƿ���ͬ
		if (Arrays.equals(digest, digestInDb)) {
			// ������ȷ���ؿ���ƥ����Ϣ
			return true;
		} else {
			// �����ȷ���ؿ��ƥ����Ϣ
			return false;
		}
	}

	/**
	 *��ü��ܺ��16������ʽ����
	 * 
	 * @parampassword
	 *@return
	 *@throwsNoSuchAlgorithmException
	 *@throwsUnsupportedEncodingException
	 */
	public static String getEncryptedPwd(String password){
			//throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// �������ܺ�Ŀ����������
		byte[] pwd = null;
		// �����������
		SecureRandom random = new SecureRandom();
		// �������������
		byte[] salt = new byte[SALT_LENGTH];
		// ������������α�����
		random.nextBytes(salt);
		// ������ϢժҪ����
		MessageDigest md = null;
		// ������ϢժҪ
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// �������ݴ�����ϢժҪ����
		md.update(salt);
		// ����������ݴ�����ϢժҪ����
		try {
			md.update(password.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// �����ϢժҪ���ֽ�����
		byte[] digest = md.digest();
		// ��ΪҪ�ڿ�����ֽ������д���Σ����Լ����ε��ֽڳ���
		pwd = new byte[digest.length + SALT_LENGTH];
		// ���ε��ֽڿ��������ɵļ��ܿ����ֽ������ǰ12���ֽڣ��Ա�����֤����ʱ
		System.arraycopy(salt, 0, pwd, 0, SALT_LENGTH);
		// ����ϢժҪ���������ܿ����ֽ�����ӵ�13���ֽڿ�ʼ���ֽ�
		System.arraycopy(digest, 0, pwd, SALT_LENGTH, digest.length);
		// ���ֽ������ʽ���ܺ�Ŀ���ת��Ϊ16�����ַ�����ʽ�Ŀ���
		return byteToHexString(pwd);
	}

	public static void main(String[] args) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		System.out.println(getEncryptedPwd("admin"));
		System.out.println(validPassword("admin",
				"59E599FE426B926A877122767F51960BFBB6FA1F176886D59E48AE9B"));
		System.out.println("59E599FE426B926A877122767F51960BFBB6FA1F176886D59E48AE9B".length());
	}
}