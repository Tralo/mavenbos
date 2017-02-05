package cn.itcast.bos.service.impl.qp;

import cn.itcast.bos.domain.qp.WorkOrderManage;
import cn.itcast.bos.page.PageRequestBean;
import cn.itcast.bos.page.PageResponseBean;
import cn.itcast.bos.service.base.BaseService;
import cn.itcast.bos.service.qp.WorkOrderManageService;

public class WorkOrderManageServiceImpl extends BaseService implements WorkOrderManageService{

	//保存或更新
	@Override
	public void saveOrUpdate(WorkOrderManage workOrderManage) {
		workOrderManageDAO.saveOrUpdate(workOrderManage);
	}
	// 分页查询
	@Override
	public PageResponseBean pageQuery(PageRequestBean pageRequestBean) {
		return pageQuery(pageRequestBean, workOrderManageDAO);
	}

}
