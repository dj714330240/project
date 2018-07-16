package cc.mrbird.credit.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.config.RequestConfig;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.common.util.AccountValidatorUtil;
import cc.mrbird.common.util.HttpUtils;
import cc.mrbird.credit.domain.CreditLog;
import cc.mrbird.credit.service.CreditLogService;
import cc.mrbird.system.domain.User;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CallFrequencyController extends BaseController {

    @Autowired
    CreditLogService creditLogService;

    @Log("通话次数评分")
    @RequestMapping("call_frequency")
    @RequiresPermissions("call_frequency:list")
    public String index() {
        return "credit/call_frequency/call_frequency";
    }

    @Log("通话次数评分查询")
    @RequiresPermissions("call_frequency:info")
    @RequestMapping(value = "call_frequency/info",method = RequestMethod.POST)
    @ResponseBody
    public ResponseBo getDetail (String phone) {
        try {
            User user = super.getCurrentUser();
            if (StringUtils.isEmpty(phone)) {
                return ResponseBo.error("手机号码不能为空！");
            }
            if (!AccountValidatorUtil.isMobile(phone)) {
                return ResponseBo.error("手机号码格式不正确！");
            }
            Map<String,Object> parms = new HashMap<>();
            parms.put("sendTelNo",phone);
            Map<String,Object> map = HttpUtils.sendPostBse(RequestConfig.LAST_ACTIVE_TIME_URL, parms);
            CreditLog creditLog = new CreditLog();
            creditLog.setUserId(user.getUserId());
            creditLog.setName("通话次数评分查询");
            creditLog.setType(9);
            creditLogService.addCreditLog(creditLog);
            return ResponseBo.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("通话次数评分查询失败，请联系网站管理员！");
        }
    }
}
