/**
 * (C) Copyright of Fresher FPT Software Academy. All Rights Reserved
 *
 * @author Minh
 * @date Aug 23, 2021
 * @version 1.0
 */
package spring.mvc.utils;

import spring.mvc.dto.SeatDto;
import spring.mvc.entities.SeatEntity;

public class ConvertSeat {
	public static SeatEntity convertDtoToEntity(SeatDto dto) {
		if (dto == null) {
			return null;
		}
		SeatEntity entity = new SeatEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setCreateAt(dto.getCreateAt());
		entity.setUpdateAt(dto.getUpdateAt());
		entity.setDescription(dto.getDescription());
		entity.setStatus(dto.isStatus());
		entity.setAreaId(dto.getAreaId());
		return entity;
	}

	public static SeatDto convertDtoFromEntity(SeatEntity entity) {
		if (entity == null) {
			return null;
		}
		SeatDto dto = new SeatDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCreateAt(entity.getCreateAt());
		dto.setUpdateAt(entity.getUpdateAt());
		dto.setDescription(entity.getDescription());
		dto.setStatus(entity.isStatus());
		dto.setAreaId(entity.getAreaId());
		return dto;
	}
}
