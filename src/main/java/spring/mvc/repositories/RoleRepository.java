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

import spring.mvc.entities.RoleEntity;

@Repository
public class RoleRepository {

	@Autowired
	@Qualifier(value = "sessionFactory")
	SessionFactory sf;

	public List<RoleEntity> findAll() {
		Session session = sf.openSession();
		String strSql = "SELECT * FROM role";
		NativeQuery<RoleEntity> sql = session.createNativeQuery(strSql, RoleEntity.class);
		List<RoleEntity> result = sql.getResultList();
		session.close();
		return result;
	}

	public boolean save(RoleEntity entity) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(entity);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	public Optional<RoleEntity> findById(int id) {
		Session session = sf.openSession();
		RoleEntity obj = session.get(RoleEntity.class, id);
		Optional<RoleEntity> result = Optional.ofNullable(obj);
		session.close();
		return result;
	}

	public boolean deleteById(int id) {
		Session session = sf.openSession();
		session.beginTransaction();
		RoleEntity obj = session.get(RoleEntity.class, id);
		if (obj != null) {
			session.delete(obj);
			session.getTransaction().commit();
			session.close();
		}
		return true;
	}
}
