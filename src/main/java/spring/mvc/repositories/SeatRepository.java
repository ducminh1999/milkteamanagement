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

import spring.mvc.entities.ProductEntity;
import spring.mvc.entities.SeatEntity;

@Repository
public class SeatRepository {

	@Autowired
	@Qualifier(value = "sessionFactory")
	SessionFactory sf;

	public List<SeatEntity> findAll() {
		Session session = sf.openSession();
		String strSql = "SELECT * FROM seat";
		NativeQuery<SeatEntity> sql = session.createNativeQuery(strSql, SeatEntity.class);
		List<SeatEntity> result = sql.getResultList();
		session.close();
		return result;
	}

	public boolean save(SeatEntity entity) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(entity);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	public List<SeatEntity> findById(int id) {
		Session session = sf.openSession();
		String strHql = "Select * FROM seat where id=?1";
		Query<SeatEntity> hql = session.createNativeQuery(strHql, SeatEntity.class);
		hql.setParameter(1, id);
		List<SeatEntity> result = hql.getResultList();
		session.close();
		return result;
	}

	public boolean deleteById(int id) {
		Session session = sf.openSession();
		session.beginTransaction();
		SeatEntity obj = session.get(SeatEntity.class, id);
		if (obj != null) {
			session.delete(obj);
			session.getTransaction().commit();
			session.close();
		}
		return true;
	}
}
