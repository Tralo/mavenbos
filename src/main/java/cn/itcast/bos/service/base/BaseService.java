package cn.itcast.bos.service.base;

import java.util.List;

import javax.annotation.Resource;

import cn.itcast.bos.dao.GenericDAO;
import cn.itcast.bos.domain.bc.Region;
import cn.itcast.bos.domain.bc.Staff;
import cn.itcast.bos.domain.bc.Standard;
import cn.itcast.bos.domain.user.User;
import cn.itcast.bos.page.PageRequestBean;
import cn.itcast.bos.page.PageResponseBean;

/**
 * 业务层抽象（实现业务层代码复用）
 */
public abstract class BaseService {
	@Resource(name = "userDAO")
	protected GenericDAO<User> userDAO;

	@Resource(name = "standardDAO")
	protected GenericDAO<Standard> standardDAO;
	
	@Resource(name = "staffDAO")
	protected GenericDAO<Staff> staffDAO;
	
	@Resource(name = "regionDAO")
	protected GenericDAO<Region> regionDAO;
	
	
	public <T> PageResponseBean pageQuery(PageRequestBean pageRequestBean,GenericDAO<T> dao) {
		PageResponseBean pageResponseBean = new PageResponseBean();
		
		// 满足当前条件，记录总条数
		long total = standardDAO.findTotalCount(pageRequestBean.getDetachedCriteria());
		pageResponseBean.setTotal(total);
		
		// 查询当前显示数据
		int firstResult = (pageRequestBean.getPage() - 1) * pageRequestBean.getRows(); //从哪条开始
		int maxResult =  pageRequestBean.getRows(); // 返回记录数
		pageRequestBean.getDetachedCriteria().setProjection(null);// 清除之前 rowCount 的投影效果
		List<T> data = dao.pageQuery(pageRequestBean.getDetachedCriteria(),firstResult,maxResult);
		pageResponseBean.setRows(data);
		
		return pageResponseBean;
	}

}
