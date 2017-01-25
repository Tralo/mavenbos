package cn.itcast.bos.web.action.bc;

import java.sql.Timestamp;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.bc.Standard;
import cn.itcast.bos.domain.user.User;
import cn.itcast.bos.web.action.base.BaseAction;

/**
 * 收派标准管理
 */
public class StandardAction extends BaseAction implements ModelDriven<Standard>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 模型驱动
	 */
	private Standard standard = new Standard();
	
	
	@Override
	public Standard getModel() {
		return standard;
	}
	
	public String save(){
		// 完善 model 数据
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		standard.setUser(user);
		standard.setUpdatetime(new Timestamp(System.currentTimeMillis()));
		standardService.saveStandard(standard);
		return "saveSUCCESS";
	}

	
}
