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

import spring.mvc.dto.AreaDto;
import spring.mvc.entities.AreaEntity;
import spring.mvc.repositories.AreaRepository;
import spring.mvc.utils.ConvertArea;

@Service
public class AreaService {
	@Autowired
	AreaRepository areaRepo;

	public List<AreaDto> findAll() {
		List<AreaEntity> data = areaRepo.findAll();
		List<AreaDto> result = data.stream().map((o) -> ConvertArea.convertDtoFromEntity(o))
				.collect(Collectors.toList());
		return result;
	}

	public boolean add(AreaDto dto) {
		AreaEntity entity = ConvertArea.convertDtoToEntity(dto);
		areaRepo.save(entity);
		return true;
	}

	public AreaDto findOne(int id) {
		Optional<AreaEntity> data = areaRepo.findById(id);
		if (data.isPresent()) {
			AreaDto user = ConvertArea.convertDtoFromEntity(data.get());
			return user;
		} else {
			return null;
		}
	}

	public boolean update(AreaDto dto) {
		AreaEntity entity = ConvertArea.convertDtoToEntity(dto);
		areaRepo.save(entity);
		return true;
	}

	public boolean delete(int id) {
		areaRepo.deleteById(id);
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
