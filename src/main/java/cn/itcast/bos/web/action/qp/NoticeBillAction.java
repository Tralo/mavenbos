package cn.itcast.bos.web.action.qp;

import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.qp.NoticeBill;
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

}
