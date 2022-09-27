/**
 * (C) Copyright of Fresher FPT Software Academy. All Rights Reserved
 *
 * @author Minh
 * @date Aug 23, 2021
 * @version 1.0
 */
package spring.mvc.utils;

import spring.mvc.dto.CategoryDto;
import spring.mvc.entities.CategoryEntity;

public class ConvertCategory {
	public static CategoryEntity convertDtoToEntity(CategoryDto dto) {
		if (dto == null) {
			return null;
		}
		CategoryEntity entity = new CategoryEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setCreateAt(dto.getCreateAt());
		entity.setUpdateAt(dto.getUpdateAt());
		entity.setDescription(dto.getDescription());
		return entity;
	}

	public static CategoryDto convertDtoFromEntity(CategoryEntity entity) {
		if (entity == null) {
			return null;
		}
		CategoryDto dto = new CategoryDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCreateAt(entity.getCreateAt());
		dto.setUpdateAt(entity.getUpdateAt());
		dto.setDescription(entity.getDescription());
		return dto;
	}
}
