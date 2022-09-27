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
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import spring.mvc.entities.CartEntity;

@Repository
public class CartRepository {

	@Autowired
	@Qualifier(value = "sessionFactory")
	SessionFactory sf;

	public CartEntity findCart(int productId, int userId) {
		Session session = sf.openSession();
		String strSql = "Select * FROM cart o where o.productId=?1 and userId=?2";
		Query<CartEntity> hql = session.createNativeQuery(strSql, CartEntity.class);
		hql.setParameter(1, productId);
		hql.setParameter(2, userId);
		List<CartEntity> result = hql.getResultList();
		session.close();
		if (result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}

	public List<CartEntity> findByUser(int id) {
		Session session = sf.openSession();
		String strSql = "SELECT * FROM cart where userId=?1";
		NativeQuery<CartEntity> sql = session.createNativeQuery(strSql, CartEntity.class);
		sql.setParameter(1, id);
		List<CartEntity> result = sql.getResultList();
		session.close();
		return result;
	}
	public List<CartEntity> findAll() {
		Session session = sf.openSession();
		String strSql = "SELECT * FROM cart";
		NativeQuery<CartEntity> sql = session.createNativeQuery(strSql, CartEntity.class);
		List<CartEntity> result = sql.getResultList();
		session.close();
		return result;
	}

	public int countByUserId(int id) {
		Session session = sf.openSession();
		String strSql = "SELECT * FROM cart where userId=?1";
		NativeQuery<CartEntity> sql = session.createNativeQuery(strSql, CartEntity.class);
		sql.setParameter(1, id);
		List<CartEntity> result = sql.getResultList();
		session.close();
		return result.size();
	}

	public boolean save(CartEntity entity) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(entity);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	public Optional<CartEntity> findById(int id) {
		Session session = sf.openSession();
		CartEntity obj = session.get(CartEntity.class, id);
		Optional<CartEntity> result = Optional.ofNullable(obj);
		session.close();
		return result;
	}

	public boolean deleteById(int id) {
		Session session = sf.openSession();
		session.beginTransaction();
		CartEntity obj = session.get(CartEntity.class, id);
		if (obj != null) {
			session.delete(obj);
			session.getTransaction().commit();
			session.close();
		}
		return true;
	}
}
