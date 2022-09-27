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

import spring.mvc.dto.RoleDto;
import spring.mvc.entities.RoleEntity;
import spring.mvc.repositories.RoleRepository;
import spring.mvc.utils.ConvertRole;

@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepo;

	public List<RoleDto> findAll() {
		List<RoleEntity> data = roleRepo.findAll();
		List<RoleDto> result = data.stream().map((o) -> ConvertRole.convertDtoFromEntity(o))
				.collect(Collectors.toList());
		return result;
	}

	public boolean add(RoleDto dto) {
		RoleEntity entity = ConvertRole.convertDtoToEntity(dto);
		roleRepo.save(entity);
		return true;
	}

	public RoleDto findOne(int id) {
		Optional<RoleEntity> data = roleRepo.findById(id);
		if (data.isPresent()) {
			RoleDto user = ConvertRole.convertDtoFromEntity(data.get());
			return user;
		} else {
			return null;
		}
	}

	public boolean update(RoleDto dto) {
		RoleEntity entity = ConvertRole.convertDtoToEntity(dto);
		roleRepo.save(entity);
		return true;
	}

	public boolean delete(int id) {
		roleRepo.deleteById(id);
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
