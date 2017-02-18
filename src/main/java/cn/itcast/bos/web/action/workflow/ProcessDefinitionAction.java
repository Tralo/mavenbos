package cn.itcast.bos.web.action.workflow;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipInputStream;

import org.jbpm.api.RepositoryService;

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
	
	

}
