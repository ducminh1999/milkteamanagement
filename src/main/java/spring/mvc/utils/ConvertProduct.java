/**
 * (C) Copyright of Fresher FPT Software Academy. All Rights Reserved
 *
 * @author Minh
 * @date Aug 23, 2021
 * @version 1.0
 */
package spring.mvc.utils;

import spring.mvc.dto.ProductDto;
import spring.mvc.entities.ProductEntity;

public class ConvertProduct {
	public static ProductEntity convertDtoToEntity(ProductDto dto) {
		if (dto == null) {
			return null;
		}
		ProductEntity entity = new ProductEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setPrice(dto.getPrice());
		entity.setCategoryId(dto.getCategoryId());
		entity.setCreateAt(dto.getCreateAt());
		entity.setUpdateAt(dto.getUpdateAt());
		entity.setDescription(dto.getDescription());
		entity.setImageFile(dto.getImageFile());
		entity.setStatus(dto.isStatus());
		//entity.setCategoryEntity(ConvertCategory.convertDtoToEntity(dto.getCategoryDto()));
		return entity;
	}

	public static ProductDto convertDtoFromEntity(ProductEntity entity) {
		if (entity == null) {
			return null;
		}
		ProductDto dto = new ProductDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setPrice(entity.getPrice());
		dto.setCategoryId(entity.getCategoryId());
		dto.setCreateAt(entity.getCreateAt());
		dto.setUpdateAt(entity.getUpdateAt());
		dto.setDescription(entity.getDescription());
		dto.setStatus(entity.isStatus());
		dto.setImageFile(entity.getImageFile());
		//dto.setCategoryDto(ConvertCategory.convertDtoFromEntity(entity.getCategoryEntity()));
		return dto;
	}
}
