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

import spring.mvc.dto.UserDto;
import spring.mvc.entities.UserEntity;
import spring.mvc.repositories.UserRepository;
import spring.mvc.utils.ConvertUser;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;

	public int countEmployee() {
		return userRepo.countEmployee();
	}
	
	public List<UserDto> findAllEmployee() {
		List<UserEntity> data = userRepo.findAll();
		List<UserDto> result = data.stream().map((o) -> ConvertUser.convertDtoFromEntity(o))
				.collect(Collectors.toList());
		return result;
	}
	public List<UserDto> findAll() {
		List<UserEntity> data = userRepo.findAll();
		List<UserDto> result = data.stream().map((o) -> ConvertUser.convertDtoFromEntity(o))
				.collect(Collectors.toList());
		return result;
	}

	public UserDto findOne(String userName) {
		List<UserEntity> entity = userRepo.findByUserName(userName);
		for (UserEntity userEntity : entity) {
			userEntity.toString();
		}
        if (entity.size() == 0) {
            return null;
        } else {
        	UserDto result = ConvertUser.convertDtoFromEntity(entity.get(0));
            result.toString();
            return result;
        }
	}

	public UserDto findById(int id) {
		Optional<UserEntity> data = userRepo.findById(id);
		if(data.isPresent()) {
			UserDto user = ConvertUser.convertDtoFromEntity(data.get());
			return user;
		} else {
			return null;
		}
	}

	public boolean save(UserDto user) {
		UserEntity userEntity = ConvertUser.convertDtoToEntity(user);
		userRepo.save(userEntity);
		return true;
	}

	public boolean delete(int id) {
		userRepo.deleteById(id);
		return true;
	}

}
