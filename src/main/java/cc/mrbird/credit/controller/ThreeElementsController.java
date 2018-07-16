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
public class ThreeElementsController extends BaseController {

    @Autowired
    CreditLogService creditLogService;

    @Log("三要素核身")
    @RequestMapping("three_elements")
    @RequiresPermissions("three_elements:list")
    public String index() {
        return "credit/three_elements/three_elements";
    }

    @Log("三要素核身查询")
    @RequiresPermissions("three_elements:info")
    @RequestMapping(value = "three_elements/info",method = RequestMethod.POST)
    @ResponseBody
    public ResponseBo getDetail (String phone,String cardType, String cardNumber,String userName) {
        try {
            User user = super.getCurrentUser();
            if (StringUtils.isEmpty(phone)) {
                return ResponseBo.error("手机号码不能为空！");
            }
            if (!AccountValidatorUtil.isMobile(phone)) {
                return ResponseBo.error("手机号码格式不正确！");
            }
            if (StringUtils.isEmpty(cardType)) {
                return ResponseBo.error("证件类型不能为空！");
            }
            if (StringUtils.isEmpty(cardNumber)) {
                return ResponseBo.error("证件号码不能为空！");
            }
            if (StringUtils.isEmpty(userName)) {
                return ResponseBo.error("姓名不能为空！");
            }
            Map<String,Object> parms = new HashMap<>();
            parms.put("sendTelNo",phone);
            parms.put("certType",cardType);
            parms.put("certCode",cardNumber);
            parms.put("userName",userName);
            Map<String,Object> map = HttpUtils.sendPostBse(RequestConfig.THREE_ELEMENTS_URL, parms);
            CreditLog creditLog = new CreditLog();
            creditLog.setUserId(user.getUserId());
            creditLog.setName("三要素核身查询");
            creditLog.setType(1);
            creditLogService.addCreditLog(creditLog);
            if (map.get("code").toString().equals("00")) {
                return ResponseBo.ok(map);
            }else {
                return ResponseBo.error(map.get("errorDesc").toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("三要素核身查询失败，请联系网站管理员！");
        }
    }



}
