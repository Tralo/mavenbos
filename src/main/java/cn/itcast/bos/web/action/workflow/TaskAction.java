package cn.itcast.bos.web.action.workflow;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.bos.domain.user.User;
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
	
	// 属性驱动
	private String taskId;

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	

}
