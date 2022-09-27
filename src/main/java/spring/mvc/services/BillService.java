/**
 * (C) Copyright of Fresher FPT Software Academy. All Rights Reserved
 *
 * @author Minh
 * @date Aug 15, 2021
 * @version 1.0
 */
package spring.mvc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.engine.query.spi.sql.NativeSQLQueryCollectionReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.mvc.dto.BillDto;
import spring.mvc.entities.BillEntity;
import spring.mvc.repositories.BillRepository;
import spring.mvc.utils.ConvertBill;

@Service
public class BillService {
	@Autowired
	BillRepository billRepo;

	public long sumTotalAll() {
		return billRepo.sumTotalAll();
	}

	public long sumTotalMonth(String month) {
		return billRepo.sumTotalMonth(month);
	}

	public long sumTotalDay(String day) {
		return billRepo.sumTotalDay(day);
	}

	public List<BillDto> sumAll() {
		List<BillEntity> data = billRepo.findAll();
		List<BillDto> result = data.stream().map((o) -> ConvertBill.convertDtoFromEntity(o))
				.collect(Collectors.toList());
		return result;
	}

	public List<BillDto> findAll() {
		List<BillEntity> data = billRepo.findAll();
		List<BillDto> result = data.stream().map((o) -> ConvertBill.convertDtoFromEntity(o))
				.collect(Collectors.toList());
		List<BillDto> result1 = result;
		try {
			result1 = bubbleSort(result);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result1;
	}

	public List<BillDto> bubbleSort(List<BillDto> data) throws ParseException {
		BillDto temp;
		int i, j;

		boolean swapped = false;
		for (i = 0; i < data.size() - 1; i++) {
			swapped = false;

			for (j = 0; j < data.size() - 1 - i; j++) {

				Date date1 = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(data.get(j).getCreateAt());
				Date date2 = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(data.get(j+1).getCreateAt());
				// kiem xa xem so ke tiep co nho hon so hien tai hay khong
				// trao doi cac so.
				// (Muc dich: lam noi bot (bubble) so lon nhat)
				if (!date2.before(date1)) {
//					if (arr[j] > arr[j + 1]) {
					temp = data.get(j);
					data.set(j, data.get(j+1));
					data.set(j+1, temp);
					swapped = true;
				} 
			}
			if (!swapped) {
				break;
			}
		}
		return data;
	}

	public boolean save(BillDto dto) {
		BillEntity entity = ConvertBill.convertDtoToEntity(dto);
		billRepo.save(entity);
		return true;
	}

	public BillDto findOne(String id) {
		Optional<BillEntity> data = billRepo.findById(id);
		if (data.isPresent()) {
			BillDto user = ConvertBill.convertDtoFromEntity(data.get());
			return user;
		} else {
			return null;
		}
	}

	public boolean delete(String id) {
		billRepo.deleteById(id);
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
