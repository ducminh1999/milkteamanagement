/**
 * (C) Copyright of Fresher FPT Software Academy. All Rights Reserved
 *
 * @author Minh
 * @date Aug 15, 2021
 * @version 1.0
 */
package spring.mvc.repositories;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import spring.mvc.entities.UserEntity;

@Repository
public class UserRepository {

	@Autowired
	@Qualifier(value = "sessionFactory")
	SessionFactory sf;

	public int countEmployee() {
		Session session = sf.openSession();
		String strHql = "Select * FROM userTable where roleId = '1' or roleId = '2'";
		Query<UserEntity> hql = session.createNativeQuery(strHql, UserEntity.class);
		List<UserEntity> result = hql.getResultList();
		session.close();
		return result.size();
	}

	public List<UserEntity> findAll() {
		Session session = sf.openSession();
		String strHql = "Select * FROM userTable";
		Query<UserEntity> hql = session.createNativeQuery(strHql, UserEntity.class);
		List<UserEntity> result = hql.getResultList();
		session.close();
		return result;
	}
	public List<UserEntity> findAllEmployee() {
		Session session = sf.openSession();
		String strHql = "Select * FROM userTable where roleId = 2 or roleId = 1";
		Query<UserEntity> hql = session.createNativeQuery(strHql, UserEntity.class);
		List<UserEntity> result = hql.getResultList();
		session.close();
		return result;
	}

	public List<UserEntity> findByUserName(String userName) {
		Session session = sf.openSession();
		String strHql = "Select * FROM userTable u where u.userName=?1";
		Query<UserEntity> hql = session.createNativeQuery(strHql, UserEntity.class);
		hql.setParameter(1, userName);
		List<UserEntity> result = hql.getResultList();
		session.close();
		return result;
	}

	public Optional<UserEntity> findById(int userId) {
		Session session = sf.openSession();
		UserEntity obj = session.get(UserEntity.class, userId);
		Optional<UserEntity> result = Optional.ofNullable(obj);
		session.close();
		return result;
	}

	public boolean save(UserEntity entity) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(entity);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	public boolean deleteById(int userId) {
		Session session = sf.openSession();
		session.beginTransaction();
		UserEntity obj = session.get(UserEntity.class, userId);
		if (obj != null) {
			session.delete(obj);
			session.getTransaction().commit();
			session.close();
		}
		return true;
	}
}
