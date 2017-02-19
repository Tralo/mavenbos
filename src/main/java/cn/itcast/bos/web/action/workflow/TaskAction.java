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
	

}
