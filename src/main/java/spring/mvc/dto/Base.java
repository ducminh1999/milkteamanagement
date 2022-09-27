/**
 * (C) Copyright of Fresher FPT Software Academy. All Rights Reserved
 *
 * @author Minh
 * @date Jan 11, 2022
 * @version 1.0
 */
package spring.mvc.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public abstract class Base {
	protected int id;
	protected String description;
	protected String createAt;
	protected String updateAt;
}
