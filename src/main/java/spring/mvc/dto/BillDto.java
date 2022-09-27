/**
 * (C) Copyright of Fresher FPT Software Academy. All Rights Reserved
 *
 * @author Minh
 * @date Aug 15, 2021
 * @version 1.0
 */
package spring.mvc.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BillDto {
	private int status;
	// private int userId;
	private int seatId;
	private int discount;
	private int paymentId;
	private String deliverId;
	private long total;
	private String id;
	private String description;
	private String createAt;
	private String updateAt;
	private List<ProductDto> listProduct;

	public void showInfo() {
		System.out.println(this.id);
		System.out.println(this.total);
	}

	@Override
	public boolean equals(Object v) {
		boolean retVal = false;

		if (v instanceof BillDto) {
			BillDto ptr = (BillDto) v;
			retVal = ptr.id.equals(this.id);
		}

		return retVal;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
		return hash;
	}
}
