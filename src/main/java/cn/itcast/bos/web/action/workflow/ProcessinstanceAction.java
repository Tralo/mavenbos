package cn.itcast.bos.web.action.workflow;

import java.util.List;

import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.ProcessInstanceQuery;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import cn.itcast.bos.web.action.base.BaseAction;
/**
 * 流程实例管理
 */
public class ProcessinstanceAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	// 业务方法 --- 查看所有正在运行流程实例
	public String list(){
		// 获得 ExecutionService
		ExecutionService executionService = processEngine.getExecutionService();
		ProcessInstanceQuery query = executionService.createProcessInstanceQuery();
		List<ProcessInstance> processInstances = query.list();
		
		// 将结果放入到值栈中
		ActionContext.getContext().put("processInstances", processInstances);
		
		return "listSUCCESS";
	}

}
