/**
 * (C) Copyright of Fresher FPT Software Academy. All Rights Reserved
 *
 * @author Minh
 * @date Aug 25, 2021
 * @version 1.0
 */
package spring.mvc.utils;

public class ValidateData {

	public static void main(String[] args) {
		System.out.println(ValidateData.validateEmail("mmm.m.+-_in1@gmai11l.com.cc.dd"));
		System.out.println(ValidateData.validatePhone("0000000000"));

	}

	public static boolean validateEmail(String email) {
		String mailPattern = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(mailPattern);
	}

	public static boolean validatePhone(String phone) {
//		String phonePattern = "/^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$/im";
		String phonePattern = "^\\d{9,13}$";
		return phone.matches(phonePattern);
	}

}
