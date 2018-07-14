package cc.mrbird.common.util;

import cc.mrbird.common.config.RequestConfig;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestJ {
    public static void main(String[] args) {
        try {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.DATE);//获取年份
            System.out.println(year);
            Map<String,Object> parms = new HashMap<>();
            String uuid = HttpUtils.generateShortUuid();
            String dateName = HttpUtils.getNowStr();
            String phone = "17679302598";
            parms.put("sendTelNo",phone);
            parms.put("certType",RequestConfig.CARD_ID_CARD_TYPE);
            parms.put("certCode","360732199502010615");
            parms.put("userName","邓太阳");
            parms.put("sequence",uuid);
            parms.put("orgCode",RequestConfig.INSTITUTION_ACCOUNT);
            parms.put("curTime",dateName);
            parms.put("orgSeq",HttpUtils.getOrgSeq(phone,dateName,uuid));
            Map<String,Object> map = HttpUtils.sendPostMap(RequestConfig.THREE_ELEMENTS_URL, parms);
            System.out.println(map.get("calledscore"));
            System.out.println(JsonUtils.toJson(map));
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
