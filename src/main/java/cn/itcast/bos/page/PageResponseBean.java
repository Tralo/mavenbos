package cn.itcast.bos.page;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import sun.util.logging.resources.logging;

/**
 * 封装分页查询返回结果
 *
 */
@SuppressWarnings("all")
public class PageResponseBean implements Serializable{
	
	private long total; //总记录数
	private List rows;//当前页数据记录
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	@Override
	public String toString() {
		return "PageResponseBean [total=" + total + ", rows=" + rows + "]";
	}
	
	
	
}
