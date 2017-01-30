package cn.itcast.bos.web.action.base;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.bos.domain.bc.Staff;
import cn.itcast.bos.page.PageRequestBean;
import cn.itcast.bos.service.bc.RegionService;
import cn.itcast.bos.service.bc.StaffService;
import cn.itcast.bos.service.bc.StandardService;
import cn.itcast.bos.service.user.UserService;

/**
 * 公共抽象Action(实现代码复用)
 */
public abstract class BaseAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name = "userService")
	protected UserService userService;
	
	
	
	@Resource(name = "standardService")
	protected StandardService standardService;
	
	@Resource(name = "staffService")
	protected StaffService staffService;
	
	@Resource(name = "regionService")
	protected RegionService regionService;
	
	//属性驱动，分装分页参数
	private int page;
	private int rows;
	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	
	
	public PageRequestBean initPageRequestBean(DetachedCriteria detachedCriteria){
		
		// 将分页查询参数，封装到 PageRequestBean 对象中
		PageRequestBean pageRequestBean = new PageRequestBean();
		pageRequestBean.setPage(page);
		pageRequestBean.setRows(rows);
		
		//条件对象
		pageRequestBean.setDetachedCriteria(detachedCriteria);
		return pageRequestBean;
		
	}
	

}
