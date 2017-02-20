package cn.itcast.bos.service.workflow;

import cn.itcast.bos.domain.zm.TransferInfo;

public interface BosTaskService {

	/**
	 * 办理中转环节业务
	 * @param transferInfo
	 */
	void completeTransferInfoTask(TransferInfo transferInfo,String taskId);

}
