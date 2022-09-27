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

import spring.mvc.dto.ProductDto;
import spring.mvc.entities.ProductEntity;
import spring.mvc.repositories.ProductRepository;
import spring.mvc.utils.ConvertProduct;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepo;

	public List<ProductDto> findByCategory(int id) {
		List<ProductEntity> data = productRepo.findByCategory(id);
		List<ProductDto> result = data.stream().map((o) -> ConvertProduct.convertDtoFromEntity(o))
				.collect(Collectors.toList());
		return result;
	}
	public List<ProductDto> findById(int id) {
		List<ProductEntity> data = productRepo.findById(id);
		List<ProductDto> result = data.stream().map((o) -> ConvertProduct.convertDtoFromEntity(o))
				.collect(Collectors.toList());
		return result;
	}
	public List<ProductDto> findName(String name) {
		List<ProductEntity> data = productRepo.findName(name);
		List<ProductDto> result = data.stream().map((o) -> ConvertProduct.convertDtoFromEntity(o))
				.collect(Collectors.toList());
		return result;
	}
	public List<ProductDto> findNameNotLock(String name) {
		List<ProductEntity> data = productRepo.findNameNotLock(name);
		List<ProductDto> result = data.stream().map((o) -> ConvertProduct.convertDtoFromEntity(o))
				.collect(Collectors.toList());
		return result;
	}
	public List<ProductDto> findAll() {
		List<ProductEntity> data = productRepo.findAll();
		List<ProductDto> result = data.stream().map((o) -> ConvertProduct.convertDtoFromEntity(o))
				.collect(Collectors.toList());
		return result;
	}
	public List<ProductDto> findAllNotLock() {
		List<ProductEntity> data = productRepo.findAllNotLock();
		List<ProductDto> result = data.stream().map((o) -> ConvertProduct.convertDtoFromEntity(o))
				.collect(Collectors.toList());
		return result;
	}

	public boolean add(ProductDto dto) {
		ProductEntity entity = ConvertProduct.convertDtoToEntity(dto);
		productRepo.save(entity);
		return true;
	}

	public ProductDto findOne(int id) {
		List<ProductEntity> data = productRepo.findById(id);
		if (data != null) {
			ProductDto user = ConvertProduct.convertDtoFromEntity(data.get(0));
			return user;
		} else {
			return null;
		}
	}

	public boolean update(ProductDto dto) {
		ProductEntity entity = ConvertProduct.convertDtoToEntity(dto);
		productRepo.save(entity);
		return true;
	}

	public boolean delete(int id) {
		productRepo.deleteById(id);
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
