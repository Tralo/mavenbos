package cn.itcast.bos.web.action.workflow;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.jbpm.api.Deployment;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.RepositoryService;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import cn.itcast.bos.web.action.base.BaseAction;
/**
 * 流程定义管理
 */
public class ProcessDefinitionAction extends BaseAction {

	private static final long serialVersionUID = -422217736339191388L;
	
	
	//业务方法
	public String deploy() throws IOException{
		// 上传文件应该 zip 压缩包, 使用 JBPM RepositoryService 将业务流程部署到项目中
		RepositoryService repositoryService = processEngine.getRepositoryService();
		repositoryService.createDeployment()
					.addResourcesFromZipInputStream(new ZipInputStream(new FileInputStream(deploy)))
					.deploy();
		return "deploySUCCESS";
	}
	// struts2 文件上传 FileInterceptor 完成
	
	private File deploy;
	public void setDeploy(File deploy) {
		this.deploy = deploy;
	}
	
	// 业务方法 --- 查询所有业务流程定义
	public String list(){
		// 查询所有流程定义
		RepositoryService repositoryService = processEngine.getRepositoryService();
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().orderAsc(ProcessDefinitionQuery.PROPERTY_VERSION).list();// 所有流程定义
		
		// 每个相同 key 的流程，只显示最高版本
		// map 的 key 就是 pdKey 流程关键字, map 的 value 就是流程定义
		Map<String, ProcessDefinition> map = new HashMap<String, ProcessDefinition>();
		for(ProcessDefinition processDefinition : list){
			map.put(processDefinition.getKey(), processDefinition);
		}
		
		// 放入值栈
		ActionContext.getContext().put("processDefinitions", map.values());
		
		return "listSUCCESS";
	}

}
