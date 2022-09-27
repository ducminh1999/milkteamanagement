/**
 * (C) Copyright of Fresher FPT Software Academy. All Rights Reserved
 *
 * @author Minh
 * @date Aug 23, 2021
 * @version 1.0
 */
package spring.mvc.utils;

import spring.mvc.dto.RoleDto;
import spring.mvc.entities.RoleEntity;

public class ConvertRole {
	public static RoleEntity convertDtoToEntity(RoleDto dto) {
		if (dto == null) {
			return null;
		}
		RoleEntity entity = new RoleEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setCreateAt(dto.getCreateAt());
		entity.setUpdateAt(dto.getUpdateAt());
		entity.setDescription(dto.getDescription());
		return entity;
	}

	public static RoleDto convertDtoFromEntity(RoleEntity entity) {
		if (entity == null) {
			return null;
		}
		RoleDto dto = new RoleDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCreateAt(entity.getCreateAt());
		dto.setUpdateAt(entity.getUpdateAt());
		dto.setDescription(entity.getDescription());
		return dto;
	}
}
