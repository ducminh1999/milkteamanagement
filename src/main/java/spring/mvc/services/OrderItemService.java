/**
 * (C) Copyright of Fresher FPT Software Academy. All Rights Reserved
 *
 * @author Minh
 * @date Aug 15, 2021
 * @version 1.0
 */
package spring.mvc.services;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.mvc.dto.OrderItemDto;
import spring.mvc.entities.OrderItemEntity;
import spring.mvc.repositories.OrderItemRepository;
import spring.mvc.utils.ConvertOrderItem;

@Service
public class OrderItemService {
	@Autowired
	OrderItemRepository orderItemRepo;

	public int countByUserId(int id) {
		return orderItemRepo.countByUserId(id);
	}

	public OrderItemDto findOrder(int productId, int userId) {
		OrderItemEntity dto = orderItemRepo.findOrder(productId, userId);
		if (dto == null)
			return null;
		return ConvertOrderItem.convertDtoFromEntity(dto);
	}

	public List<OrderItemDto> findByUserId(int id) {
		List<OrderItemEntity> data = orderItemRepo.findByUserId(id);
		List<OrderItemDto> result = data.stream().map((o) -> ConvertOrderItem.convertDtoFromEntity(o))
				.collect(Collectors.toList());
		return result;
	}
	public List<OrderItemDto> findIdBill(String id) {
		List<OrderItemEntity> data = orderItemRepo.findIdBill(id);
		List<OrderItemDto> result = data.stream().map((o) -> ConvertOrderItem.convertDtoFromEntity(o))
				.collect(Collectors.toList());
		return result;
	}

	public List<OrderItemDto> findAll() {
		List<OrderItemEntity> data = orderItemRepo.findAll();
		List<OrderItemDto> result = data.stream().map((o) -> ConvertOrderItem.convertDtoFromEntity(o))
				.collect(Collectors.toList());
		return result;
	}

	public boolean save(OrderItemDto dto) {
		OrderItemEntity entity = ConvertOrderItem.convertDtoToEntity(dto);
		orderItemRepo.save(entity);
		return true;
	}

	public OrderItemDto findOne(int id) {
		Optional<OrderItemEntity> data = orderItemRepo.findById(id);
		if (data.isPresent()) {
			OrderItemDto user = ConvertOrderItem.convertDtoFromEntity(data.get());
			return user;
		} else {
			return null;
		}
	}

	public boolean delete(int id) {
		orderItemRepo.deleteById(id);
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
