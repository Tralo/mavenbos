package cn.itcast.bos.service.base;

import java.util.List;

import javax.annotation.Resource;

import org.jbpm.api.ProcessEngine;

import cn.itcast.bos.dao.GenericDAO;
import cn.itcast.bos.domain.auth.Function;
import cn.itcast.bos.domain.auth.Role;
import cn.itcast.bos.domain.bc.DecidedZone;
import cn.itcast.bos.domain.bc.Region;
import cn.itcast.bos.domain.bc.Staff;
import cn.itcast.bos.domain.bc.Standard;
import cn.itcast.bos.domain.bc.Subarea;
import cn.itcast.bos.domain.qp.NoticeBill;
import cn.itcast.bos.domain.qp.WorkBill;
import cn.itcast.bos.domain.qp.WorkOrderManage;
import cn.itcast.bos.domain.user.User;
import cn.itcast.bos.domain.zm.TransferInfo;
import cn.itcast.bos.domain.zm.ZhongZhuanInfo;
import cn.itcast.bos.page.PageRequestBean;
import cn.itcast.bos.page.PageResponseBean;
import cn.itcast.crm.service.CustomerService;

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
	
	@Resource(name = "subareaDAO")
	protected GenericDAO<Subarea> subareaDAO;
	
	@Resource(name = "decidedZoneDAO")
	protected GenericDAO<DecidedZone> decidedZoneDAO;
	
	@Resource(name = "noticeBillDAO")
	protected GenericDAO<NoticeBill> noticeBillDAO;
	
	@Resource(name = "workBillDAO")
	protected GenericDAO<WorkBill> workBillDAO;
	
	
	@Resource(name = "customerService")
	protected CustomerService customerService;
	
	@Resource(name = "workOrderManageDAO")
	protected GenericDAO<WorkOrderManage> workOrderManageDAO;
	
	@Resource(name = "functionDAO")
	protected GenericDAO<Function> functionDAO;
	
	@Resource(name = "roleDAO")
	protected GenericDAO<Role> roleDAO;
	
	@Resource(name = "processEngine")
	protected ProcessEngine processEngine;
	
	@Resource(name = "zhongZhuanInfoDAO")
	protected GenericDAO<ZhongZhuanInfo> zhongZhuanInfoDAO;
	
	@Resource(name = "transferInfoDAO")
	protected GenericDAO<TransferInfo> transferInfoDAO;
	
	public <T> PageResponseBean pageQuery(PageRequestBean pageRequestBean,GenericDAO<T> dao) {
		PageResponseBean pageResponseBean = new PageResponseBean();
		
		
		// 查询当前显示数据
		int firstResult = (pageRequestBean.getPage() - 1) * pageRequestBean.getRows(); //从哪条开始
		int maxResult =  pageRequestBean.getRows(); // 返回记录数
		List<T> data = dao.pageQuery(pageRequestBean.getDetachedCriteria(),firstResult,maxResult);
		pageResponseBean.setRows(data);
		
		// 满足当前条件，记录总条数
		long total = standardDAO.findTotalCount(pageRequestBean.getDetachedCriteria());
		pageResponseBean.setTotal(total);
		
		
		return pageResponseBean;
	}

}
