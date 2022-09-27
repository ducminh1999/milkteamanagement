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

import spring.mvc.dto.CategoryDto;
import spring.mvc.entities.CategoryEntity;
import spring.mvc.repositories.CategoryRepository;
import spring.mvc.utils.ConvertCategory;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepo;

	public List<CategoryDto> findAll() {
		List<CategoryEntity> data = categoryRepo.findAll();
		List<CategoryDto> result = data.stream().map((o) -> ConvertCategory.convertDtoFromEntity(o))
				.collect(Collectors.toList());
		return result;
	}

	public boolean add(CategoryDto dto) {
		CategoryEntity entity = ConvertCategory.convertDtoToEntity(dto);
		categoryRepo.save(entity);
		return true;
	}

	public CategoryDto findOne(int id) {
		Optional<CategoryEntity> data = categoryRepo.findById(id);
		if (data.isPresent()) {
			CategoryDto user = ConvertCategory.convertDtoFromEntity(data.get());
			return user;
		} else {
			return null;
		}
	}

	public boolean update(CategoryDto dto) {
		CategoryEntity entity = ConvertCategory.convertDtoToEntity(dto);
		categoryRepo.save(entity);
		return true;
	}

	public boolean delete(int id) {
		categoryRepo.deleteById(id);
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
