/**
 * (C) Copyright of Fresher FPT Software Academy. All Rights Reserved
 *
 * @author Minh
 * @date Aug 23, 2021
 * @version 1.0
 */
package spring.mvc.utils;

import spring.mvc.dto.PaymentDto;
import spring.mvc.entities.PaymentEntity;

public class ConvertPayment {
	public static PaymentEntity convertDtoToEntity(PaymentDto dto) {
		if (dto == null) {
			return null;
		}
		PaymentEntity entity = new PaymentEntity();
		entity.setId(dto.getId());
		entity.setPaymentMethod(dto.getPaymentMethod());
		entity.setCreateAt(dto.getCreateAt());
		entity.setUpdateAt(dto.getUpdateAt());
		entity.setDescription(dto.getDescription());
		return entity;
	}

	public static PaymentDto convertDtoFromEntity(PaymentEntity entity) {
		if (entity == null) {
			return null;
		}
		PaymentDto dto = new PaymentDto();
		dto.setId(entity.getId());
		dto.setPaymentMethod(entity.getPaymentMethod());
		dto.setCreateAt(entity.getCreateAt());
		dto.setUpdateAt(entity.getUpdateAt());
		dto.setDescription(entity.getDescription());
		return dto;
	}
}
