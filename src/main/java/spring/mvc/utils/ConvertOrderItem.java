/**
 * (C) Copyright of Fresher FPT Software Academy. All Rights Reserved
 *
 * @author Minh
 * @date Aug 23, 2021
 * @version 1.0
 */
package spring.mvc.utils;

import spring.mvc.dto.OrderItemDto;
import spring.mvc.entities.OrderItemEntity;

public class ConvertOrderItem {
	public static OrderItemEntity convertDtoToEntity(OrderItemDto dto) {
		if (dto == null) {
			return null;
		}
		OrderItemEntity entity = new OrderItemEntity();
		entity.setId(dto.getId());
		entity.setBillId(dto.getBillId());
		entity.setProductId(dto.getProductId());
		entity.setQuantity(dto.getQuantity());
		//entity.setPrice(dto.getPrice());
		entity.setCreateAt(dto.getCreateAt());
		entity.setUpdateAt(dto.getUpdateAt());
		entity.setUserId(dto.getUserId());
		entity.setDescription(dto.getDescription());
		return entity;
	}

	public static OrderItemDto convertDtoFromEntity(OrderItemEntity entity) {
		if (entity == null) {
			return null;
		}
		OrderItemDto dto = new OrderItemDto();
		dto.setId(entity.getId());
		dto.setBillId(entity.getBillId());
		dto.setUserId(entity.getUserId());
		dto.setProductId(entity.getProductId());
		dto.setQuantity(entity.getQuantity());
		//dto.setPrice(entity.getPrice());
		dto.setCreateAt(entity.getCreateAt());
		dto.setUpdateAt(entity.getUpdateAt());
		dto.setDescription(entity.getDescription());
		return dto;
	}
}
