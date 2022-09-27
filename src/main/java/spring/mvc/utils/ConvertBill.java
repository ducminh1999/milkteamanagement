/**
 * (C) Copyright of Fresher FPT Software Academy. All Rights Reserved
 *
 * @author Minh
 * @date Aug 23, 2021
 * @version 1.0
 */
package spring.mvc.utils;

import spring.mvc.dto.BillDto;
import spring.mvc.entities.BillEntity;

public class ConvertBill {
	public static BillEntity convertDtoToEntity(BillDto dto) {
		if (dto == null) {
			return null;
		}
		BillEntity entity = new BillEntity();
		entity.setId(dto.getId());
		entity.setStatus(dto.getStatus());
		entity.setSeatId(dto.getSeatId());
		entity.setDeliverId(dto.getDeliverId());
		entity.setDiscount(dto.getDiscount());
		entity.setPaymentId(dto.getPaymentId());
		entity.setTotal(dto.getTotal());
		entity.setCreateAt(dto.getCreateAt());
		entity.setUpdateAt(dto.getUpdateAt());
		return entity;
	}

	public static BillDto convertDtoFromEntity(BillEntity entity) {
		if (entity == null) {
			return null;
		}
		BillDto dto = new BillDto();
		dto.setId(entity.getId());
		dto.setStatus(entity.getStatus());
		dto.setSeatId(entity.getSeatId());
		dto.setDeliverId(entity.getDeliverId());
		dto.setDiscount(entity.getDiscount());
		dto.setPaymentId(entity.getPaymentId());
		dto.setTotal(entity.getTotal());
		dto.setCreateAt(entity.getCreateAt());
		dto.setUpdateAt(entity.getUpdateAt());
		return dto;
	}
}
