package cn.itcast.bos.service.impl.qp;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.jdbc.Work;

import cn.itcast.bos.domain.bc.DecidedZone;
import cn.itcast.bos.domain.bc.Subarea;
import cn.itcast.bos.domain.qp.NoticeBill;
import cn.itcast.bos.domain.qp.WorkBill;
import cn.itcast.bos.service.base.BaseService;
import cn.itcast.bos.service.qp.NoticeBillService;

public class NoticeBillServiceImpl extends BaseService implements NoticeBillService {

	@Override
	public void saveNoticeBill(NoticeBill noticeBill) {
		//将业务通知单数据保存到数据库
		noticeBillDAO.save(noticeBill);
		
		//自动分单
		// 1. 使用当前取件地址，去查询 CRM 系统定区编码
		String decidedZoneId = customerService.findDecidedZoneIdByCustomerAddress(noticeBill.getPickaddress());
		if(decidedZoneId == null){
			// 未查到
			String[] addressArray = noticeBill.getPickaddress().split(" "); // 北京市海淀区 xxx路1号楼
			if(addressArray.length >= 2){
				String addresskey = addressArray[1];// 取第二个元素作为关键字
				List<Subarea> list = subareaDAO.findByNamedQuery("Subarea.findByAddresskey", addresskey);
				// 只匹配到唯一的一个分区，而且这个分区已经关联到定区
				if(list.size() == 1 && list.get(0).getDecidedZone() != null){
					// 查到(自动分单成功)
					DecidedZone decidedZone = list.get(0).getDecidedZone();
					// 通知单
					noticeBill.setStaff(decidedZone.getStaff());
					noticeBill.setOrdertype("自动");
					// 工单信息
					WorkBill workBill = new WorkBill();
					workBill.setNoticeBill(noticeBill);
					workBill.setStaff(decidedZone.getStaff());
					workBill.setType("新");
					workBill.setPickstate("新单");
					workBill.setBuildtime(new Timestamp(System.currentTimeMillis()));
					workBill.setAttachbilltimes(0);
					workBill.setRemark(noticeBill.getRemark());
					workBillDAO.save(workBill);
				} else {
					// 人工调度
					noticeBill.setOrdertype("人工");
				} 
			} else {
				//人工调度
				noticeBill.setOrdertype("人工");
			}
		} else {
			// 查到(自动分单成功)
			DecidedZone decidedZone = decidedZoneDAO.findById(decidedZoneId);
			// 通知单
			noticeBill.setStaff(decidedZone.getStaff());
			noticeBill.setOrdertype("自动");
			// 工单信息
			WorkBill workBill = new WorkBill();
			workBill.setNoticeBill(noticeBill);
			workBill.setStaff(decidedZone.getStaff());
			workBill.setType("新");
			workBill.setPickstate("新单");
			workBill.setBuildtime(new Timestamp(System.currentTimeMillis()));
			workBill.setAttachbilltimes(0);
			workBill.setRemark(noticeBill.getRemark());
			workBillDAO.save(workBill);
		}
		
	}

}
