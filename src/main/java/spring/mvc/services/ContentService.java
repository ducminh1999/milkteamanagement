/**
 * (C) Copyright of Fresher FPT Software Academy. All Rights Reserved
 *
 * @author Minh
 * @date Aug 15, 2021
 * @version 1.0
 */
package spring.mvc.services;

import org.springframework.stereotype.Service;

@Service
public class ContentService {
//	@Autowired
//	ContentRepository contentRepo;

//	ConvertContent convertContent;

//	public List<ContentDto> findAll() {
//		List<ContentEntity> list = contentRepo.findAll();
//		List<ContentDto> result = list.stream().map((o) -> ConvertContent.convertDtoFromEntity(o))
//				.collect(Collectors.toList());
//		return result;
//	}
//
//	public List<ContentDto> findByTitleContains(String title) {
//		System.out.println("title:"+title);
//		List<ContentEntity> list = contentRepo.findByTitleContains(title);
//
//		System.out.println("title:"+title+"="+list.size());
//		List<ContentDto> result = list.stream().map((o) -> ConvertContent.convertDtoFromEntity(o))
//				.collect(Collectors.toList());
//		return result;
//	}
//
//	public ContentDto findId(int id) {
//		Optional<ContentEntity> data = contentRepo.findById(id);
//		if (data.isPresent()) {
//			ContentDto result = ConvertContent.convertDtoFromEntity(data.get());
//			return result;
//		} else {
//			return null;
//		}
//	}
//
//	public boolean add(ContentDto contentDto) {
//		contentRepo.save(ConvertContent.convertDtoToEntity(contentDto));
//		return true;
//	}
//
//	public boolean update(ContentDto contentDto) {
//		contentRepo.save(ConvertContent.convertDtoToEntity(contentDto));
//		return true;
//	}

//	public boolean delete(int id) {
//		contentRepo.deleteById(id);
//		return true;
//	}
}
