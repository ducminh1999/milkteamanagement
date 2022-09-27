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
public class OrderItemDto extends Base{
	//private int price;
	private int quantity;
	private int productId;
	private String billId;
	private int userId;
	public void showInfo() {
		System.out.println(this.id);
		System.out.println(this.productId);
	}
}
