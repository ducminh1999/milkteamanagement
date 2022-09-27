/**
 * (C) Copyright of Fresher FPT Software Academy. All Rights Reserved
 *
 * @author Minh
 * @date Aug 23, 2021
 * @version 1.0
 */
package spring.mvc.utils;

import spring.mvc.dto.AreaDto;
import spring.mvc.entities.AreaEntity;

public class ConvertArea {
	public static AreaEntity convertDtoToEntity(AreaDto dto) {
		if (dto == null) {
			return null;
		}
		AreaEntity entity = new AreaEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setCreateAt(dto.getCreateAt());
		entity.setUpdateAt(dto.getUpdateAt());
		entity.setDescription(dto.getDescription());
		entity.setStatus(dto.isStatus());
		return entity;
	}

	public static AreaDto convertDtoFromEntity(AreaEntity entity) {
		if (entity == null) {
			return null;
		}
		AreaDto dto = new AreaDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCreateAt(entity.getCreateAt());
		dto.setUpdateAt(entity.getUpdateAt());
		dto.setDescription(entity.getDescription());
		dto.setStatus(entity.isStatus());
		return dto;
	}
}
