package cn.itcast.bos.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.bos.domain.bc.Standard;
import cn.itcast.bos.page.PageResponseBean;

/**
 * 通用DAO设计接口
 */
public interface GenericDAO<T> {

	/**
	 * 保存
	 * 
	 * @param obj
	 */
	public void save(T obj);

	/**
	 * 保存或更新
	 * 
	 * @param obj
	 */
	public void saveOrUpdate(T obj);

	/**
	 * 修改
	 * 
	 * @param obj
	 */
	public void update(T obj);

	/**
	 * 删除
	 * 
	 * @param obj
	 */
	public void delete(T obj);

	/**
	 * 根据 id 查询
	 * 
	 * @param id
	 * @return
	 */
	public T findById(Serializable id);

	/**
	 * 查询所有数据
	 * 
	 * @return
	 */
	public List<T> findAll();

	/**
	 * 条件查询
	 */
	public List<T> findByNamedQuery(String queryName, Object... values);// 根据hql查询

	public List<T> findByCriteria(DetachedCriteria detachedCriteria);

	/**
	 * 查询瞒住条件记录总数
	 */
	public long findTotalCount(DetachedCriteria detachedCriteria);

	/**
	 * 分页查询，查询当前页面的数据
	 * 
	 * @param detachedCriteria
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	public List<T> pageQuery(DetachedCriteria detachedCriteria, int firstResult, int maxResult);

	/**
	 * 结合Lucene索引库进行查询
	 * 
	 * @param conditionName
	 * @param conditionValue
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageResponseBean queryByLucene(String conditionName, String conditionValue, int page, int rows);

}
