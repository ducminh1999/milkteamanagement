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

import spring.mvc.dto.CartDto;
import spring.mvc.entities.CartEntity;
import spring.mvc.repositories.CartRepository;
import spring.mvc.utils.ConvertCart;

@Service
public class CartService {
	@Autowired
	CartRepository cartRepo;

	public int countByUserId(int id) {
		return cartRepo.countByUserId(id);
	}

	public CartDto findCart(int productId, int userId) {
		CartEntity dto = cartRepo.findCart(productId, userId);
		if (dto == null)
			return null;
		return ConvertCart.convertDtoFromEntity(dto);
	}

	public List<CartDto> findByUser(int id) {
		List<CartEntity> data = cartRepo.findByUser(id);
		List<CartDto> result = data.stream().map((o) -> ConvertCart.convertDtoFromEntity(o))
				.collect(Collectors.toList());
		return result;
	}
	public List<CartDto> findAll() {
		List<CartEntity> data = cartRepo.findAll();
		List<CartDto> result = data.stream().map((o) -> ConvertCart.convertDtoFromEntity(o))
				.collect(Collectors.toList());
		return result;
	}

	public boolean save(CartDto dto) {
		CartEntity entity = ConvertCart.convertDtoToEntity(dto);
		cartRepo.save(entity);
		return true;
	}

	public CartDto findOne(int id) {
		Optional<CartEntity> data = cartRepo.findById(id);
		if (data.isPresent()) {
			CartDto user = ConvertCart.convertDtoFromEntity(data.get());
			return user;
		} else {
			return null;
		}
	}

	public boolean delete(int id) {
		cartRepo.deleteById(id);
		return true;
	}
}
