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

import spring.mvc.dto.SeatDto;
import spring.mvc.entities.SeatEntity;
import spring.mvc.repositories.SeatRepository;
import spring.mvc.utils.ConvertSeat;

@Service
public class SeatService {
	@Autowired
	SeatRepository seatRepo;

	public List<SeatDto> findAll() {
		List<SeatEntity> data = seatRepo.findAll();
		List<SeatDto> result = data.stream().map((o) -> ConvertSeat.convertDtoFromEntity(o))
				.collect(Collectors.toList());
		return result;
	}

	public boolean add(SeatDto dto) {
		SeatEntity entity = ConvertSeat.convertDtoToEntity(dto);
		seatRepo.save(entity);
		return true;
	}

	public SeatDto findOne(int id) {
		List<SeatEntity> data = seatRepo.findById(id);
		if (data != null) {
			SeatDto user = ConvertSeat.convertDtoFromEntity(data.get(0));
			return user;
		} else {
			return null;
		}
	}

	public boolean update(SeatDto dto) {
		SeatEntity entity = ConvertSeat.convertDtoToEntity(dto);
		seatRepo.save(entity);
		return true;
	}

	public boolean delete(int id) {
		seatRepo.deleteById(id);
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
