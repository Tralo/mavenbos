package cn.itcast.bos.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.bos.dao.GenericDAO;

public class GenericDAOImpl<T> extends HibernateDaoSupport implements GenericDAO<T> {
	
	private Class<T> clazz;
	private String className;
	
	public GenericDAOImpl(String className) {
//		//得到泛型超类
//		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
//		clazz = (Class<T>) type.getActualTypeArguments()[0];
		this.className = className;
	}

	@Override
	public void save(T obj) {
//		this.getSession();//使用原始hibernate编程方式
//		this.getHibernateTemplate();//使用 Spring 提供模版工具类
		this.getHibernateTemplate().save(obj);
	}

	@Override
	public void update(T obj) {
		this.getHibernateTemplate().update(obj);
	}

	@Override
	public void delete(T obj) {
		this.getHibernateTemplate().delete(obj);
	}

	@Override
	public T findById(Serializable id) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return (T) this.getHibernateTemplate().get(clazz, id);
//		return this.getHibernateTemplate().get(clazz, id);
	}

	@Override
	public List<T> findAll() {
		
		return this.getHibernateTemplate().find("from " + className);
//		return this.getHibernateTemplate().find("from " + clazz.getName());
	}

	@Override
	public List<T> findByNamedQuery(String queryName, Object... values) {
		return this.getHibernateTemplate().findByNamedQuery(queryName,values);
	}

	@Override
	public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
		return this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

}
