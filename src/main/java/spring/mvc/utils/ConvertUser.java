/**
 * (C) Copyright of Fresher FPT Software Academy. All Rights Reserved
 *
 * @author Minh
 * @date Aug 23, 2021
 * @version 1.0
 */
package spring.mvc.utils;

import spring.mvc.dto.UserDto;
import spring.mvc.entities.UserEntity;

public class ConvertUser {
	public static UserEntity convertDtoToEntity(UserDto dto) {
		if (dto == null) {
			return null;
		}
		UserEntity entity = new UserEntity();
		entity.setId(dto.getId());
		entity.setFullName(dto.getFullName());
		entity.setUserName(dto.getUserName());
		entity.setPassword(dto.getPassword());
		entity.setAddress(dto.getAddress());
		entity.setPhone(dto.getPhone());
		entity.setEmail(dto.getEmail());
		entity.setSalary(dto.getSalary());
		entity.setGender(dto.isGender());
		entity.setStatus(dto.isStatus());
		entity.setDescription(dto.getDescription());
		entity.setCreateAt(dto.getCreateAt());
		entity.setUpdateAt(dto.getUpdateAt());
		entity.setRoleId(dto.getRoleId());
		return entity;
	}

	public static UserDto convertDtoFromEntity(UserEntity entity) {
		if (entity == null) {
			return null;
		}
		UserDto dto = new UserDto();
		dto.setId(entity.getId());
		dto.setFullName(entity.getFullName());
		dto.setUserName(entity.getUserName());
		dto.setPassword(entity.getPassword());
		dto.setAddress(entity.getAddress());
		dto.setPhone(entity.getPhone());
		dto.setEmail(entity.getEmail());
		dto.setSalary(entity.getSalary());
		dto.setGender(entity.isGender());
		dto.setStatus(entity.isStatus());
		dto.setDescription(entity.getDescription());
		dto.setCreateAt(entity.getCreateAt());
		dto.setUpdateAt(entity.getUpdateAt());
		dto.setRoleId(entity.getRoleId());
		return dto;
	}
}
