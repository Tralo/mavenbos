package cn.itcast.bos.web.action.qp;

import javax.servlet.Servlet;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.qp.NoticeBill;
import cn.itcast.bos.domain.user.User;
import cn.itcast.bos.web.action.base.BaseAction;
/**
 * 业务通知单
 */
public class NoticeBillAction extends BaseAction implements ModelDriven<NoticeBill>{

	private static final long serialVersionUID = 1L;
	
	private NoticeBill noticeBill = new NoticeBill();

	@Override
	public NoticeBill getModel() {
		return noticeBill;
	}
	
	// 业务方法 --- 保存业务通知
	public String save(){
		// 完善 model 信息
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		noticeBill.setUser(user);
		
		// 调用业务层，完成通知单新增
		noticeBillService.saveNoticeBill(noticeBill);
		
		return "saveSUCCESS";
	}

}
