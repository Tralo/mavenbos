package cn.itcast.bos.service.impl.workflow;

import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.env.EnvironmentFactory;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.ExecutionImpl;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.jbpm.pvm.internal.model.TransitionImpl;

import cn.itcast.bos.domain.zm.InStore;
import cn.itcast.bos.domain.zm.OutStore;
import cn.itcast.bos.domain.zm.ReceiveGoodsInfo;
import cn.itcast.bos.domain.zm.TransferInfo;
import cn.itcast.bos.domain.zm.ZhongZhuanInfo;
import cn.itcast.bos.service.base.BaseService;
import cn.itcast.bos.service.workflow.BosTaskService;

public class BosTaskServiceImpl extends BaseService implements BosTaskService {

	@Override
	public void completeTransferInfoTask(TransferInfo transferInfo,String taskId) {
		// 1.业务数据持久化
		transferInfoDAO.save(transferInfo);
		
		// 2.将业务数据 关联到流程变量
		TaskService taskService = processEngine.getTaskService();
		ZhongZhuanInfo zhongZhuanInfo = (ZhongZhuanInfo) taskService.getVariable(taskId, "zhongZhuanInfo");
		zhongZhuanInfo.setArrive(transferInfo.getArrive());
		zhongZhuanInfo.getTransferInfos().add(transferInfo);// 向集合中添加中转环节信息
		
		// 3. 办理任务
		if(zhongZhuanInfo.getArrive().equals("0")){
			// 未到达，继续中转
			// 自由刘庄
			Task task = taskService.getTask(taskId);
			completeTaskByCreateTransition(task, "中转环节", "to 中转环节xxx");
		} else {
			// 已到达
			taskService.completeTask(taskId,"to 入库");// 流向入库了
			
		}
	}
	@Override
	public void completeInStoreTask(InStore instore, String taskId) {
		//1. 业务数据持久化
		inStoreDAO.save(instore);
		
		//2. 关联流程变量
		TaskService taskService = processEngine.getTaskService();
		ZhongZhuanInfo zhongZhuanInfo = (ZhongZhuanInfo) taskService.getVariable(taskId, "zhongZhuanInfo");
		zhongZhuanInfo.setInStore(instore);
		
		//3. 办理任务，流程向后流转
		taskService.completeTask(taskId, "to 出库");
		
	}
	@Override
	public void completeOutStoreTask(OutStore outStore, String taskId) {
		//1. 业务数据持久化
		outStoreDAO.save(outStore);
		//2. 关联流程变量
		TaskService taskService = processEngine.getTaskService();
		ZhongZhuanInfo zhongZhuanInfo = (ZhongZhuanInfo) taskService.getVariable(taskId, "zhongZhuanInfo");
		zhongZhuanInfo.setOutStore(outStore);
		
		//3. 办理任务，流程向后流转
		taskService.completeTask(taskId, "to 配送签收");
		
	}
	@Override
	public void completeReceiveGoodsInfo(ReceiveGoodsInfo receiveGoodsInfo, String taskId) {
		//1. 业务数据持久化
		receiveGoodsInfoDAO.save(receiveGoodsInfo);
		//2. 关联流程变量
		TaskService taskService = processEngine.getTaskService();
		ZhongZhuanInfo zhongZhuanInfo = (ZhongZhuanInfo) taskService.getVariable(taskId, "zhongZhuanInfo");
		zhongZhuanInfo.setReceiveGoodsInfo(receiveGoodsInfo);
		
		//3. 办理业务，流程向后流转
		taskService.completeTask(taskId,"to end1");
		
	}
	
	
	/**
	 * 自由流转
	 * @param task 当前任务
	 * @param destActivityName 目标节点 name 属性
	 * @param createTransitionName 流转 transition 的名称
	 */
	public void completeTaskByCreateTransition(Task task,String destActivityName,String createTransitionName){
		// 这里不会影响事务
		EnvironmentImpl envImpl = ((EnvironmentFactory)processEngine).openEnvironment();
		try{
			// 动态回退
			ExecutionImpl e = (ExecutionImpl) processEngine.getExecutionService().findExecutionById(task.getExecutionId());
			ActivityImpl currentActivityImpl = e.getActivity();
			ProcessDefinitionImpl  processDefinitionImpl = currentActivityImpl.getProcessDefinition();
			
			// 生成一个 transition
			ActivityImpl destActivityImpl = processDefinitionImpl.findActivity(destActivityName);
			TransitionImpl toApply = currentActivityImpl.createOutgoingTransition();
			toApply.setSource(currentActivityImpl);
			toApply.setDestination(destActivityImpl);
			toApply.setName(createTransitionName);
			processEngine.getTaskService().completeTask(task.getId(),createTransitionName);
			
		} catch (Exception e) {
		} finally{
			envImpl.close();
		}
	}


}
