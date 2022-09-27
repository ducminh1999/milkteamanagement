/**
 * (C) Copyright of Fresher FPT Software Academy. All Rights Reserved
 *
 * @author Minh
 * @date Aug 15, 2021
 * @version 1.0
 */
package spring.mvc.repositories;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import spring.mvc.entities.CategoryEntity;

@Repository
public class CategoryRepository {

	@Autowired
	@Qualifier(value = "sessionFactory")
	SessionFactory sf;

	public List<CategoryEntity> findAll() {
		Session session = sf.openSession();
		String strSql = "SELECT * FROM category";
		NativeQuery<CategoryEntity> sql = session.createNativeQuery(strSql, CategoryEntity.class);
		List<CategoryEntity> result = sql.getResultList();
		session.close();
		return result;
	}

	public boolean save(CategoryEntity entity) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(entity);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	public Optional<CategoryEntity> findById(int id) {
		Session session = sf.openSession();
		CategoryEntity obj = session.get(CategoryEntity.class, id);
		Optional<CategoryEntity> result = Optional.ofNullable(obj);
		session.close();
		return result;
	}

	public boolean deleteById(int id) {
		Session session = sf.openSession();
		session.beginTransaction();
		CategoryEntity obj = session.get(CategoryEntity.class, id);
		if (obj != null) {
			session.delete(obj);
			session.getTransaction().commit();
			session.close();
		}
		return true;
	}
}
