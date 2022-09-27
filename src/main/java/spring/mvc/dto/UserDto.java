/**
 * (C) Copyright of Fresher FPT Software Academy. All Rights Reserved
 *
 * @author Minh
 * @date Aug 15, 2021
 * @version 1.0
 */
package spring.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends Base {
	private String fullName;
	private String userName;
	private String password;
	private String address;
	private String phone;
	private String email;
	private long salary;
	private boolean gender;
	private boolean status;
	private int roleId;

	public void showInfo() {
		System.out.println(this.id);
		System.out.println(this.userName);
	}
}
