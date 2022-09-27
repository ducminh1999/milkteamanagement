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
import spring.mvc.entities.CategoryEntity;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto extends Base {
	private String name;
	private int price;
	private int categoryId;
	private String imageFile;
	private int quantity;
	private boolean status;
	private CategoryDto categoryDto;

	public void showInfo() {
		System.out.println(this.id);
		System.out.println(this.name);
	}
}
