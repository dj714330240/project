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

    @Override
    public void addCreditLog(CreditLog creditLog) {
        if (null != creditLog) {
            Calendar cal = Calendar.getInstance();



            //查询数据维度
            List<CreditLog> list = findCreditLog(creditLog);
            if (null != list &&list.size()>0) {
                for (CreditLog clg : list) {
                    if (null != clg.getTimeType() && 1 == clg.getTimeType()) {
                        int day = cal.get(Calendar.DATE);//获取日
                        cal.setTime(clg.getModifyDate());
                        int oldDay = cal.get(Calendar.DATE);
                        if (day == oldDay) {
                            creditLog.setCounts(creditLog.getCounts()+1);
                        }else {
                            creditLog.setCounts(1L);
                        }
                    } else if (null != clg.getTimeType() && 2 == clg.getTimeType()){

                    }else {

                    }
                }
//                creditLog = list.get(0);
//                if (null != creditLog.getCounts()) {
//                    creditLog.setCounts(creditLog.getCounts()+1);
//                }else {
//                    creditLog.setCounts(1L);
//                }
//                creditLog.setModifyDate(new Date());
//               super.updateNotNull(creditLog);
            } else {
                creditLog.setCounts(1L);
                Date date = new Date();
                creditLog.setModifyDate(date);
                creditLog.setCreateTime(date);
                super.save(creditLog);
            }
        }
    }

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
