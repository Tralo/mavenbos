package cn.itcast.bos.service.impl.qp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.ExecutionService;

import cn.itcast.bos.domain.qp.WorkOrderManage;
import cn.itcast.bos.domain.zm.ZhongZhuanInfo;
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
	@Override
	public PageResponseBean queryByLuence(String conditionName, String conditionValue, int page, int rows) {
		
		return workOrderManageDAO.queryByLucene(conditionName,conditionValue,page,rows);
	}
	@Override
	public List<WorkOrderManage> listUnCheckWorkOrderManages() {
		return workOrderManageDAO.findByNamedQuery("WorkOrderManage.listUnChecked");
	}
	@Override
	public void check(WorkOrderManage workOrderManage) {
		// 操作一: 将工作单 managerCheck 属性值设置为 1
		WorkOrderManage persistWorkOrderManage = workOrderManageDAO.findById(workOrderManage.getId());
		persistWorkOrderManage.setManagerCheck("1");
		
		// 操作二: 启动中转配送流程
		ExecutionService executionService = processEngine.getExecutionService();
		// 在启动流程时，关联流程实例 对应全局中转信息对象
		ZhongZhuanInfo zhongZhuanInfo = new ZhongZhuanInfo();
		zhongZhuanInfo.setArrive("0");// 未到达
		zhongZhuanInfo.setWorkOrderManage(persistWorkOrderManage);// 关联工作单信息
		// 对ZhongZhuanInfo 进行持久化
		zhongZhuanInfoDAO.save(zhongZhuanInfo);
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("zhongZhuanInfo", zhongZhuanInfo);
		
		executionService.startProcessInstanceByKey("transfer",variables);
		
	}

}
