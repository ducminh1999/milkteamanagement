/**
 * (C) Copyright of Fresher FPT Software Academy. All Rights Reserved
 *
 * @author Minh
 * @date Aug 23, 2021
 * @version 1.0
 */
package spring.mvc.utils;

import spring.mvc.dto.CartDto;
import spring.mvc.entities.CartEntity;

public class ConvertCart {
	public static CartEntity convertDtoToEntity(CartDto dto) {
		if (dto == null) {
			return null;
		}
		CartEntity entity = new CartEntity();
		entity.setId(dto.getId());
		entity.setProductId(dto.getProductId());
		entity.setQuantity(dto.getQuantity());
		entity.setCreateAt(dto.getCreateAt());
		entity.setUpdateAt(dto.getUpdateAt());
		entity.setUserId(dto.getUserId());
		entity.setDescription(dto.getDescription());
		return entity;
	}

	public static CartDto convertDtoFromEntity(CartEntity entity) {
		if (entity == null) {
			return null;
		}
		CartDto dto = new CartDto();
		dto.setId(entity.getId());
		dto.setUserId(entity.getUserId());
		dto.setProductId(entity.getProductId());
		dto.setQuantity(entity.getQuantity());
		dto.setCreateAt(entity.getCreateAt());
		dto.setUpdateAt(entity.getUpdateAt());
		dto.setDescription(entity.getDescription());
		return dto;
	}
}
