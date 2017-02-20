package cn.itcast.bos.web.action.workflow;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.bos.domain.user.User;
import cn.itcast.bos.domain.zm.InStore;
import cn.itcast.bos.domain.zm.OutStore;
import cn.itcast.bos.domain.zm.ReceiveGoodsInfo;
import cn.itcast.bos.domain.zm.TransferInfo;
import cn.itcast.bos.web.action.base.BaseAction;
/**
 * 进行任务相关操作
 */
public class TaskAction extends BaseAction{

	private static final long serialVersionUID = 5256022449967762818L;
	
	// 业务方法 --- 查看当前用户的组任务
	public String grouptask(){
		// 查询组任务，使用 TaskService
		TaskService taskService = processEngine.getTaskService();
		User user  = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		List<Task> tasks = taskService.findGroupTasks(user.getId());
		
		// 将任务列表放入值栈
		ActionContext.getContext().put("tasks", tasks);
		
		return "findgrouptaskSUCCESS";
	}
	// 业务方法 --- 拾取组任务
	public String taketask(){
		// 调用 TaskService 方法进行组任务拾取
		TaskService taskService = processEngine.getTaskService();
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		
		taskService.takeTask(taskId, user.getId());
		
		return "taketaskSUCCESS";
	}
	
	// 业务方法 --- 获取个人任务列表
	public String personaltask(){
		// 使用 TaskService 查询个人任务
		TaskService taskService = processEngine.getTaskService();
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		List<Task> tasks = taskService.findPersonalTasks(user.getId());
		// 将结果压入值栈
		ActionContext.getContext().put("tasks", tasks);
		
		return "personaltaskSUCCESS";
	}
	// 业务方法 --- 办理中转环节任务
	public String saveTransferinfo(){
		// 请求数据封装到 model
		TransferInfo transferInfo = new TransferInfo();
		transferInfo.setInfo(info);
		transferInfo.setArrive(arrive);
		transferInfo.setUpdateTime(new Date());
		// 调用业务层
		bosTaskService.completeTransferInfoTask(transferInfo,taskId);
		
		return "saveTransferinfoSUCCESS";
	}
	// 业务方法 --- 办理入库任务
	public String instorecomplete(){
		// 将业务数据封装 PO 对象
		InStore instore = new InStore();
		instore.setInfo(info);
		instore.setUpdateTime(new Date());
		
		// 调用业务层
		bosTaskService.completeInStoreTask(instore,taskId);
		
		return "instorecompleteSUCCESS";
	}
	
	public String outstorecomplete(){
		// 将业务数据封装 PO 对象
		OutStore outStore = new OutStore();
		outStore.setInfo(info);
		outStore.setUpdateTime(new Date());
		
		// 调用业务层
		bosTaskService.completeOutStoreTask(outStore,taskId);
		
		return "outstorecompleteSUCCESS";
	}
	
	// 业务方法 --- 签收
	public String receiveinfocomplete(){
		// 将业务数据封装 PO 对象
		ReceiveGoodsInfo receiveGoodsInfo = new ReceiveGoodsInfo();
		receiveGoodsInfo.setInfo(info);
		receiveGoodsInfo.setUpdateTime(new Date());
		
		// 调用业务层
		bosTaskService.completeReceiveGoodsInfo(receiveGoodsInfo,taskId);
		
		return "receiveinfocompleteSUCCESS";
	}
	
	// 属性驱动
	private String taskId;

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	private String info;
	private String arrive;

	public void setInfo(String info) {
		this.info = info;
	}
	public void setArrive(String arrive) {
		this.arrive = arrive;
	}
	
	

}
