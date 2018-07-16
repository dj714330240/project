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
public class OnLineTimesController extends BaseController {

    @Autowired
    CreditLogService creditLogService;

    @Log("在网时长")
    @RequestMapping("on_line_times")
    @RequiresPermissions("on_line_times:list")
    public String index() {
        return "credit/on_line_times/on_line_times";
    }

    @Log("在网时长查询")
    @RequiresPermissions("on_line_times:info")
    @RequestMapping(value = "on_line_times/info",method = RequestMethod.POST)
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
            Map<String,Object> map = HttpUtils.sendPostBse(RequestConfig.ONLINE_TIMES_URL, parms);
            CreditLog creditLog = new CreditLog();
            creditLog.setUserId(user.getUserId());
            creditLog.setName("在网时长查询");
            creditLog.setType(6);
            creditLogService.addCreditLog(creditLog);
            if (map.get("code").toString().equals("00")) {
                return ResponseBo.ok(map);
            }else {
                return ResponseBo.error(map.get("errorDesc").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("在网时长查询失败，请联系网站管理员！");
        }
    }

}
