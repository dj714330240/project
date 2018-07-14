package cc.mrbird.credit.service;

import cc.mrbird.common.service.IService;
import cc.mrbird.credit.domain.CreditLog;

import java.util.List;


public interface CreditLogService extends IService<CreditLog> {

    /**
     * 添加日志
     * @param creditLog
     */
    void addCreditLog (CreditLog creditLog);


    /**
     * 查询日志
     * @param creditLog
     * @return
     */
    List<CreditLog> findCreditLog (CreditLog creditLog);

}
