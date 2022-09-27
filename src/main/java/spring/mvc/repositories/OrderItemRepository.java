/**
 * (C) Copyright of Fresher FPT Software Academy. All Rights Reserved
 *
 * @author Minh
 * @date Aug 15, 2021
 * @version 1.0
 */
package spring.mvc.repositories;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import spring.mvc.dto.OrderItemDto;
import spring.mvc.dto.ProductDto;
import spring.mvc.entities.OrderItemEntity;
import spring.mvc.entities.ProductEntity;
import spring.mvc.entities.UserEntity;

@Repository
public class OrderItemRepository {

	@Autowired
	@Qualifier(value = "sessionFactory")
	SessionFactory sf;

	public List<OrderItemEntity> findIdBill(String id) {
		Session session = sf.openSession();
		String strSql = "Select * FROM orderItem o where o.billId=?1";
		Query<OrderItemEntity> hql = session.createNativeQuery(strSql, OrderItemEntity.class);
		hql.setParameter(1, id);
		List<OrderItemEntity> result = hql.getResultList();
		session.close();
		return result;
	}

	public OrderItemEntity findOrder(int productId, int userId) {
		Session session = sf.openSession();
		String strSql = "Select * FROM orderItem o where o.productId=?1 and userId=?2";
		Query<OrderItemEntity> hql = session.createNativeQuery(strSql, OrderItemEntity.class);
		hql.setParameter(1, productId);
		hql.setParameter(2, userId);
		List<OrderItemEntity> result = hql.getResultList();
		session.close();
		if (result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}

	public List<OrderItemEntity> findAll() {
		Session session = sf.openSession();
		String strSql = "SELECT * FROM orderItem";
		NativeQuery<OrderItemEntity> sql = session.createNativeQuery(strSql, OrderItemEntity.class);
		List<OrderItemEntity> result = sql.getResultList();
		session.close();
		return result;
	}

	public List<OrderItemEntity> findByUserId(int id) {
		Session session = sf.openSession();
		String strSql = "SELECT * FROM orderItem where userId=?1";
		NativeQuery<OrderItemEntity> sql = session.createNativeQuery(strSql, OrderItemEntity.class);
		sql.setParameter(1, id);
		List<OrderItemEntity> result = sql.getResultList();
		session.close();
		return result;
	}

	public int countByUserId(int id) {
		Session session = sf.openSession();
		String strSql = "SELECT * FROM orderItem where userId=?1";
		NativeQuery<OrderItemEntity> sql = session.createNativeQuery(strSql, OrderItemEntity.class);
		sql.setParameter(1, id);
		List<OrderItemEntity> result = sql.getResultList();
		session.close();
		return result.size();
	}

	public boolean save(OrderItemEntity entity) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(entity);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	public Optional<OrderItemEntity> findById(int id) {
		Session session = sf.openSession();
		OrderItemEntity obj = session.get(OrderItemEntity.class, id);
		Optional<OrderItemEntity> result = Optional.ofNullable(obj);
		session.close();
		return result;
	}

	public boolean deleteById(int id) {
		Session session = sf.openSession();
		session.beginTransaction();
		OrderItemEntity obj = session.get(OrderItemEntity.class, id);
		if (obj != null) {
			session.delete(obj);
			session.getTransaction().commit();
			session.close();
		}
		return true;
	}
}
