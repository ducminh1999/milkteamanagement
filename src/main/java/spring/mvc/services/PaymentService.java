/**
 * (C) Copyright of Fresher FPT Software Academy. All Rights Reserved
 *
 * @author Minh
 * @date Aug 15, 2021
 * @version 1.0
 */
package spring.mvc.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.mvc.dto.PaymentDto;
import spring.mvc.entities.PaymentEntity;
import spring.mvc.repositories.PaymentRepository;
import spring.mvc.utils.ConvertPayment;

@Service
public class PaymentService {
	@Autowired
	PaymentRepository paymentRepo;

	public List<PaymentDto> findAll() {
		List<PaymentEntity> data = paymentRepo.findAll();
		List<PaymentDto> result = data.stream().map((o) -> ConvertPayment.convertDtoFromEntity(o))
				.collect(Collectors.toList());
		return result;
	}

	public boolean add(PaymentDto dto) {
		PaymentEntity entity = ConvertPayment.convertDtoToEntity(dto);
		paymentRepo.save(entity);
		return true;
	}

	public PaymentDto findOne(int id) {
		Optional<PaymentEntity> data = paymentRepo.findById(id);
		if (data.isPresent()) {
			PaymentDto user = ConvertPayment.convertDtoFromEntity(data.get());
			return user;
		} else {
			return null;
		}
	}

	public boolean update(PaymentDto dto) {
		PaymentEntity entity = ConvertPayment.convertDtoToEntity(dto);
		paymentRepo.save(entity);
		return true;
	}

	public boolean delete(int id) {
		paymentRepo.deleteById(id);
		return true;
	}
//	public UserDto findOne(String userName) {
//		List<UserEntity> entity = userRepo.findByUserName(userName);
//		for (UserEntity userEntity : entity) {
//			userEntity.toString();
//		}
//        if (entity.size() == 0) {
//            return null;
//        } else {
//        	UserDto result = ConvertUser.convertDtoFromEntity(entity.get(0));
//            result.toString();
//            return result;
//        }
//	}
//
//	public UserDto findOne(int id) {
//		Optional<UserEntity> data = userRepo.findById(id);
//		if(data.isPresent()) {
//			UserDto user = ConvertUser.convertDtoFromEntity(data.get());
//			return user;
//		} else {
//			return null;
//		}
//	}
//
//	public boolean add(UserDto user) {
//		UserEntity userEntity = ConvertUser.convertDtoToEntity(user);
//		userRepo.save(userEntity);
//		return true;
//	}
//
//	public boolean update(UserDto user) {
//		UserEntity userEntity = ConvertUser.convertDtoToEntity(user);
//		userRepo.save(userEntity);
//		return true;
//	}
//
//	public boolean delete(int id) {
//		userRepo.deleteById(id);
//		return true;
//	}

}
