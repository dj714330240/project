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
public class OnLineNumberController extends BaseController {

    @Autowired
    CreditLogService creditLogService;

    @Log("用户在网号码个数")
    @RequestMapping("on_line_number")
    @RequiresPermissions("on_line_number:list")
    public String index() {
        return "credit/on_line_number/on_line_number";
    }

    @Log("用户在网号码个数查询")
    @RequiresPermissions("on_line_number:info")
    @RequestMapping(value = "on_line_number/info",method = RequestMethod.GET)
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
            Map<String,Object> map = HttpUtils.sendPostBse(RequestConfig.ONLINE_USER_NUMBER_URL, parms);
            CreditLog creditLog = new CreditLog();
            creditLog.setUserId(user.getUserId());
            creditLog.setName("用户在网号码个数查询");
            creditLog.setType(5);
            creditLogService.addCreditLog(creditLog);
            return ResponseBo.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("用户在网号码个数查询失败，请联系网站管理员！");
        }
    }

}
