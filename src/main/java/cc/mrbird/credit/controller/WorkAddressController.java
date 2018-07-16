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
public class WorkAddressController extends BaseController {

    @Autowired
    CreditLogService creditLogService;

    @Log("工作地验证")
    @RequestMapping("work_address")
    @RequiresPermissions("work_address:list")
    public String index() {
        return "credit/work_address/work_address";
    }

    @Log("工作地验证查询")
    @RequiresPermissions("work_address:info")
    @RequestMapping(value = "work_address/info",method = RequestMethod.POST)
    @ResponseBody
    public ResponseBo getDetail (String phone, String longitude, String latitude) {
        try {
            User user = super.getCurrentUser();
            if (StringUtils.isEmpty(phone)) {
                return ResponseBo.error("手机号码不能为空！");
            }
            if (!AccountValidatorUtil.isMobile(phone)) {
                return ResponseBo.error("手机号码格式不正确！");
            }
            if (StringUtils.isEmpty(longitude)) {
                return ResponseBo.error("所在经度不能为空！");
            }
            if (StringUtils.isEmpty(latitude)) {
                return ResponseBo.error("所在纬度不能为空！");
            }
            Map<String,Object> parms = new HashMap<>();
            parms.put("sendTelNo",phone);
            parms.put("longitude",longitude);
            parms.put("latitude",latitude);
            Map<String,Object> map = HttpUtils.sendPostBse(RequestConfig.WORK_ADDRESS_URL, parms);
            CreditLog creditLog = new CreditLog();
            creditLog.setUserId(user.getUserId());
            creditLog.setName("工作地验证查询");
            creditLog.setType(2);
            creditLogService.addCreditLog(creditLog);
            return ResponseBo.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("工作地验证查询失败，请联系网站管理员！");
        }
    }


}
