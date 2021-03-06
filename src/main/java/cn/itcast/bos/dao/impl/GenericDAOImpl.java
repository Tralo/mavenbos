package cn.itcast.bos.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.WildcardQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.impl.FullTextSessionImpl;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.bos.dao.GenericDAO;
import cn.itcast.bos.domain.bc.Standard;
import cn.itcast.bos.page.PageResponseBean;

public class GenericDAOImpl<T> extends HibernateDaoSupport implements GenericDAO<T> {

	private Class<T> clazz;
	private String className;

	public GenericDAOImpl(String className) {
		// //得到泛型超类
		// ParameterizedType type = (ParameterizedType)
		// this.getClass().getGenericSuperclass();
		// clazz = (Class<T>) type.getActualTypeArguments()[0];
		this.className = className;
	}

	@Override
	public void save(T obj) {
		this.getHibernateTemplate().save(obj);
	}

	@Override
	public void saveOrUpdate(T obj) {
		this.getHibernateTemplate().saveOrUpdate(obj);
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
		// return this.getHibernateTemplate().get(clazz, id);
	}

	@Override
	public List<T> findAll() {
		return this.getHibernateTemplate().find("from " + className);
		// return this.getHibernateTemplate().find("from " + clazz.getName());
	}

	@Override
	public List<T> findByNamedQuery(String queryName, Object... values) {
		return this.getHibernateTemplate().findByNamedQuery(queryName, values);
	}

	@Override
	public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
		return this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	@Override
	public long findTotalCount(DetachedCriteria detachedCriteria) {
		// select count(*) from bc_standard
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> result = this.getHibernateTemplate().findByCriteria(detachedCriteria, 0, 1);
		return result.get(0);
	}

	@Override
	public List<T> pageQuery(DetachedCriteria detachedCriteria, int firstResult, int maxResult) {
		return this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResult);
	}

	@Override
	public PageResponseBean queryByLucene(String conditionName, String conditionValue, int page, int rows) {
		// Hibernate Search 编程步骤
		// 1. 获得 Session
		Session session = this.getSession();
		// 2. 获得全文检索 Session
		FullTextSession fullTextSession = new FullTextSessionImpl(session);
		// 3. 编写 Lucene 查询对象（词条模糊搜索）
		Query query = new WildcardQuery(new Term(conditionName,"*" + conditionValue + "*"));
		// 4. 获得全文搜索的Query
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(query);
		
		PageResponseBean pageResponseBean = new PageResponseBean();
		// 查询总记录数
		pageResponseBean.setTotal(fullTextQuery.getResultSize());
		
		// 当前页数据
		int firstResult = (page - 1) * rows;
		int maxResult = rows;
		List list = fullTextQuery.setFirstResult(firstResult).setMaxResults(maxResult).list();
		pageResponseBean.setRows(list);
		
		return pageResponseBean;
	}

}
