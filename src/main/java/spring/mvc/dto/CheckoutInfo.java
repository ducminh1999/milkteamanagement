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
public class CheckoutInfo{
	private float paymentTotal;
	private long paymentVNTotal;
	private float shippingTotal;
	private int deliverDay;
	private String deliverId;
	private String orderId;
	private int paymentMethod;

}
