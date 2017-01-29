package cn.itcast.bos.service.impl.bc;

import java.util.List;

import cn.itcast.bos.domain.bc.Staff;
import cn.itcast.bos.page.PageRequestBean;
import cn.itcast.bos.page.PageResponseBean;
import cn.itcast.bos.service.base.BaseService;
import cn.itcast.bos.service.bc.StaffService;

public class StaffServiceImpl extends BaseService implements StaffService{

	@Override
	public void saveOrUpdate(Staff staff) {
		staffDAO.saveOrUpdate(staff);
	}

	@Override
	public PageResponseBean pageQuery(PageRequestBean pageRequestBean) {
		PageResponseBean pageResponseBean = new PageResponseBean();
		// 查询 total
		long totalCount = staffDAO.findTotalCount(pageRequestBean.getDetachedCriteria());
		pageResponseBean.setTotal(totalCount);// 查询 totalCount 经过投影, select * ----  select count(*)
		
		// 查询 rows
		pageRequestBean.getDetachedCriteria().setProjection(null);//清除投影效果 select count (*) ----  select * 
		int firstResult = (pageRequestBean.getPage() - 1) * pageRequestBean.getRows();
		int maxResults = pageRequestBean.getRows();
		List<Staff> data = staffDAO.pageQuery(pageRequestBean.getDetachedCriteria(), firstResult, maxResults);
		pageResponseBean.setRows(data);
		
		
		return pageResponseBean;
	}

	

}
