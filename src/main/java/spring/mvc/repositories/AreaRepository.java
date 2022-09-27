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

import spring.mvc.entities.AreaEntity;

@Repository
public class AreaRepository {

	@Autowired
	@Qualifier(value = "sessionFactory")
	SessionFactory sf;

	public List<AreaEntity> findAll() {
		Session session = sf.openSession();
		String strSql = "SELECT * FROM area";
		NativeQuery<AreaEntity> sql = session.createNativeQuery(strSql, AreaEntity.class);
		List<AreaEntity> result = sql.getResultList();
		session.close();
		return result;
	}

	public boolean save(AreaEntity entity) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(entity);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	public Optional<AreaEntity> findById(int id) {
		Session session = sf.openSession();
		AreaEntity obj = session.get(AreaEntity.class, id);
		Optional<AreaEntity> result = Optional.ofNullable(obj);
		session.close();
		return result;
	}

	public boolean deleteById(int id) {
		Session session = sf.openSession();
		session.beginTransaction();
		AreaEntity obj = session.get(AreaEntity.class, id);
		if (obj != null) {
			session.delete(obj);
			session.getTransaction().commit();
			session.close();
		}
		return true;
	}
}
