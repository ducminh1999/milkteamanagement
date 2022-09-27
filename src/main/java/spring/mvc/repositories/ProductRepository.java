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
import spring.mvc.entities.UserEntity;

@Repository
public class ProductRepository {

	@Autowired
	@Qualifier(value = "sessionFactory")
	SessionFactory sf;
	
	public List<ProductEntity> findNameNotLock(String name) {
		Session session = sf.openSession();
		String strSql = "SELECT * FROM product where status=1 and name like ?";
		NativeQuery<ProductEntity> sql = session.createNativeQuery(strSql, ProductEntity.class);
		sql.setParameter(1, "%"+name+"%");
		List<ProductEntity> result = sql.getResultList();
		session.close();
		return result;
	}
	
	public List<ProductEntity> findName(String name) {
		Session session = sf.openSession();
		String strSql = "SELECT * FROM product where name like ?";
		NativeQuery<ProductEntity> sql = session.createNativeQuery(strSql, ProductEntity.class);
		sql.setParameter(1, "%"+name+"%");
		List<ProductEntity> result = sql.getResultList();
		session.close();
		return result;
	}
	public List<ProductEntity> findAll() {
		Session session = sf.openSession();
		String strSql = "SELECT * FROM product";
		NativeQuery<ProductEntity> sql = session.createNativeQuery(strSql, ProductEntity.class);
		List<ProductEntity> result = sql.getResultList();
		session.close();
		return result;
	}
	public List<ProductEntity> findAllNotLock() {
		Session session = sf.openSession();
		String strSql = "SELECT * FROM product where status=1";
		NativeQuery<ProductEntity> sql = session.createNativeQuery(strSql, ProductEntity.class);
		List<ProductEntity> result = sql.getResultList();
		session.close();
		return result;
	}

	public boolean save(ProductEntity entity) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(entity);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	public List<ProductEntity> findById(int id) {
		Session session = sf.openSession();
		String strHql = "Select * FROM product where id=?1";
		Query<ProductEntity> hql = session.createNativeQuery(strHql, ProductEntity.class);
		hql.setParameter(1, id);
		List<ProductEntity> result = hql.getResultList();
		session.close();
		return result;
	}
	
	public List<ProductEntity> findByCategory(int id) {
		Session session = sf.openSession();
		String strHql = "Select * FROM product where status=1 and categoryId=?1";
		Query<ProductEntity> hql = session.createNativeQuery(strHql, ProductEntity.class);
		hql.setParameter(1, id);
		List<ProductEntity> result = hql.getResultList();
		session.close();
		return result;
	}

	public boolean deleteById(int id) {
		Session session = sf.openSession();
		session.beginTransaction();
		ProductEntity obj = session.get(ProductEntity.class, id);
		if (obj != null) {
			session.delete(obj);
			session.getTransaction().commit();
			session.close();
		}
		return true;
	}
}
