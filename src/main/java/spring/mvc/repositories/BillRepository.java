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

import spring.mvc.entities.BillEntity;

@Repository
public class BillRepository {

	@Autowired
	@Qualifier(value = "sessionFactory")
	SessionFactory sf;

	public long sumTotalAll() {
		Session session = sf.openSession();
		String strSql = "SELECT * FROM bill";
		NativeQuery<BillEntity> sql = session.createNativeQuery(strSql, BillEntity.class);
		List<BillEntity> result = sql.getResultList();
		long sum = 0;
		for (BillEntity billEntity : result) {
			if (billEntity.getStatus() == 2 || (billEntity.getPaymentId() == 2 && billEntity.getStatus() == 1)) {
				sum += billEntity.getTotal();
			}
		}
		session.close();
		return sum;
	}

	public long sumTotalMonth(String month) {
		Session session = sf.openSession();
		String strSql = "SELECT * FROM bill";
		NativeQuery<BillEntity> sql = session.createNativeQuery(strSql, BillEntity.class);
		List<BillEntity> result = sql.getResultList();
		long sum = 0;
		for (BillEntity billEntity : result) {
			if (billEntity.getCreateAt().substring(3, 5).equals(month)) {
				if (billEntity.getStatus() == 2 || (billEntity.getPaymentId() == 2 && billEntity.getStatus() == 1)) {
					sum += billEntity.getTotal();
				}
			}
		}
		session.close();
		return sum;
	}

	public long sumTotalDay(String day) {
		Session session = sf.openSession();
		String strSql = "SELECT * FROM bill";
		NativeQuery<BillEntity> sql = session.createNativeQuery(strSql, BillEntity.class);
		List<BillEntity> result = sql.getResultList();
		long sum = 0;
		for (BillEntity billEntity : result) {
			if (billEntity.getCreateAt().substring(0, 10).equals(day)) {
				if (billEntity.getStatus() == 2 || (billEntity.getPaymentId() == 2 && billEntity.getStatus() == 1)) {
					sum += billEntity.getTotal();
				}
			}
		}
		session.close();
		return sum;
	}

	public List<BillEntity> findAll() {
		Session session = sf.openSession();
		String strSql = "SELECT * FROM bill";
		NativeQuery<BillEntity> sql = session.createNativeQuery(strSql, BillEntity.class);
		List<BillEntity> result = sql.getResultList();
		session.close();
		return result;
	}

	public boolean save(BillEntity entity) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(entity);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	public Optional<BillEntity> findById(String id) {
		Session session = sf.openSession();
		BillEntity obj = session.get(BillEntity.class, id);
		Optional<BillEntity> result = Optional.ofNullable(obj);
		session.close();
		return result;
	}

	public boolean deleteById(String id) {
		Session session = sf.openSession();
		session.beginTransaction();
		BillEntity obj = session.get(BillEntity.class, id);
		if (obj != null) {
			session.delete(obj);
			session.getTransaction().commit();
			session.close();
		}
		return true;
	}
}
