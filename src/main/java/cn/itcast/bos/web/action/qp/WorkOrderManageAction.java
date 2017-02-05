package cn.itcast.bos.web.action.qp;

import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.qp.WorkOrderManage;
import cn.itcast.bos.web.action.base.BaseAction;
/**
 * 工作单管理
 */
public class WorkOrderManageAction extends BaseAction implements ModelDriven<WorkOrderManage>{

	private static final long serialVersionUID = 1L;
	
	private WorkOrderManage workOrderManage = new WorkOrderManage();
	

	@Override
	public WorkOrderManage getModel() {
		return workOrderManage;
	}

}
