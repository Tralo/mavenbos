package cn.itcast.bos.service.workflow;

import cn.itcast.bos.domain.zm.InStore;
import cn.itcast.bos.domain.zm.OutStore;
import cn.itcast.bos.domain.zm.ReceiveGoodsInfo;
import cn.itcast.bos.domain.zm.TransferInfo;

public interface BosTaskService {

	/**
	 * 办理中转环节业务
	 * @param transferInfo
	 */
	void completeTransferInfoTask(TransferInfo transferInfo,String taskId);
	/**
	 * 办理入库业务
	 * @param instore
	 * @param taskId
	 */
	void completeInStoreTask(InStore instore, String taskId);
	/**
	 * 办理出库业务
	 * @param outStore
	 * @param taskId
	 */
	void completeOutStoreTask(OutStore outStore, String taskId);
	/**
	 * 办理配送签收业务
	 * @param receiveGoodsInfo
	 * @param taskId
	 */
	void completeReceiveGoodsInfo(ReceiveGoodsInfo receiveGoodsInfo, String taskId);

}
