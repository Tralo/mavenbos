package cn.itcast.bos.service.impl.qp;

import cn.itcast.bos.domain.qp.WorkOrderManage;
import cn.itcast.bos.service.base.BaseService;
import cn.itcast.bos.service.qp.WorkOrderManageService;

public class WorkOrderManageServiceImpl extends BaseService implements WorkOrderManageService{

	@Override
	public void saveOrUpdate(WorkOrderManage workOrderManage) {
		workOrderManageDAO.saveOrUpdate(workOrderManage);
	}

}
