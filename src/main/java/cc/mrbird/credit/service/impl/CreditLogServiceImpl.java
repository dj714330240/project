package cc.mrbird.credit.service.impl;

import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.credit.domain.CreditLog;
import cc.mrbird.credit.service.CreditLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("creditLogService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CreditLogServiceImpl extends BaseService<CreditLog> implements CreditLogService {

    @Transactional
    @Override
    public void addCreditLog(CreditLog creditLog) {
        if (null != creditLog) {
            //查询数据维度
            List<CreditLog> list = findCreditLog(creditLog);
            if (null != list &&list.size()>0) {
                for (CreditLog clg : list) {
                    addCounts(clg);
                }
            } else {
                creditLog.setCounts(1L);
                Date date = new Date();
                creditLog.setTimeType(1);
                creditLog.setModifyDate(date);
                creditLog.setCreateTime(date);
                super.save(creditLog);
                creditLog.setTimeType(2);
                creditLog.setId(null);
                super.save(creditLog);
                creditLog.setTimeType(3);
                creditLog.setId(null);
                super.save(creditLog);
            }
        }
    }

    /**
     *  增加访问记录
     * @param creditLog
     */
    public void addCounts(CreditLog creditLog) {
        Calendar cal = Calendar.getInstance();
        Calendar old = Calendar.getInstance();
                old.setTime(creditLog.getModifyDate());
        if (null != creditLog.getTimeType() && 1 == creditLog.getTimeType()) {
            if (old.get(Calendar.DATE) == cal.get(Calendar.DATE)) {
                creditLog.setCounts(creditLog.getCounts()+1);
            } else {
                creditLog.setCounts(1L);
            }
        } else if (null != creditLog.getTimeType() && 2 == creditLog.getTimeType()){
            if (old.get(Calendar.MONTH) == cal.get(Calendar.MONTH)) {
                creditLog.setCounts(creditLog.getCounts()+1);
            } else {
                creditLog.setCounts(1L);
            }
        } else {
            if (old.get(Calendar.YEAR) == cal.get(Calendar.YEAR)) {
                creditLog.setCounts(creditLog.getCounts()+1);
            } else {
                creditLog.setCounts(1L);
            }
        }
        creditLog.setModifyDate(new Date());
        this.updateNotNull(creditLog);
    }

    /**
     *  查询用户记录
     * @param creditLog
     * @return
     */
    @Override
    public List<CreditLog> findCreditLog(CreditLog creditLog) {
        try {

            Example example = new Example(CreditLog.class);
            if (null != creditLog.getUserId()) {
                example.createCriteria().andCondition("USER_ID =", creditLog.getUserId());
            }
            if (null != creditLog.getType()) {
                example.createCriteria().andCondition("TYPE =", creditLog.getType());
            }
            if (null != creditLog.getTimeType()) {
                example.createCriteria().andCondition("TIME_TYPE =", creditLog.getTimeType());
            }
            example.setOrderByClause("ID");
            return this.selectByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
