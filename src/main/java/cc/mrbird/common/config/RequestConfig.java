package cc.mrbird.common.config;

public class RequestConfig {
    /***
     * 机构名称： 江西卡特里克
     *
     * 机构账号： JX_KTLK_FRAME
     *
     * 机构密码： 7F5E25798398E8EC
     *
     *
     * 正式环境
     *
     *
     * 1.三要素验证（8档） ==  http://60.10.25.208:8080/crp_xxh/inter/check/userCheck.do
     *
     * 2.工作地验证  ==  http://60.10.25.208:8080/crp_xxh/inter/work/workDistance.do
     *
     * 3.居住地验证  ==  http://60.10.25.208:8080/crp_xxh/inter/house/houseDistance.do
     *
     * 4.最近一次活跃时间  ==  http://60.10.25.208:8080/crp_xxh/inter/active/lastActive.do
     *
     * 5.用户在网号码个数查询  ==  http://60.10.25.208:8080/crp_xxh/inter/userstaus/phonePnum.do
     *
     * 6.在网时长（7档）  ==  http://60.10.25.208:8080/crp_xxh/inter/userstaus/inUseTime.do
     *
     * 7.用户近六个月平均通话量评分接  ==  http://60.10.25.208:8080/crp_xxh/inter/avgbillcheck/avgVoiceScore.do
     *
     * 8.常用联系人验证  ==  http://60.10.25.208:8080/crp_xxh/inter/province/isTopContact.do
     *
     * 9.通话次数评分  ==  http://60.10.25.208:8080/crp_xxh/inter/call/voiceNumber.do
     */

    /**
     *机构账号
     */
    public static final String INSTITUTION_ACCOUNT = "JX_KTLK_FRAME";

    /**
     *机构密码
     */
    public static final String INSTITUTION_PWD = "7F5E25798398E8EC";

    /**
     * 三要素验证
     */
    public static final String THREE_ELEMENTS_URL = "http://60.10.25.208:8080/crp_xxh/inter/check/userCheck.do";

    /**
     * 工作地址验证
     */
    public static final String WORK_ADDRESS_URL = "http://60.10.25.208:8080/crp_xxh/inter/work/workDistance.do";

    /**
     * 居住地址验证
     */
    public static final String RESIDENTIAL_ADDRESS_URL = "http://60.10.25.208:8080/crp_xxh/inter/house/houseDistance.do";

    /**
     * 最近一次活跃时间
     */
    public static final String LAST_ACTIVE_TIME_URL = "http://60.10.25.208:8080/crp_xxh/inter/active/lastActive.do";

    /**
     * 用户在网号码个数查询
     */
    public static final String ONLINE_USER_NUMBER_URL = "http://60.10.25.208:8080/crp_xxh/inter/userstaus/phonePnum.do";

    /**
     * 在网时长
     */
    public static final String ONLINE_TIMES_URL = "http://60.10.25.208:8080/crp_xxh/inter/userstaus/inUseTime.do";

    /**
     * 用户近六个月平均通话量评分验证
     */
    public static final String RECENT_SIX_MONTH_MARK_URL = "http://60.10.25.208:8080/crp_xxh/inter/avgbillcheck/avgVoiceScore.do";

    /**
     * 常用联系人
     */
    public static final String REGULAR_CONTACT_URL = "http://60.10.25.208:8080/crp_xxh/inter/province/isTopContact.do";

    /**
     * 通话次数评分
     */
    public static final String CALL_FREQUENCY_URL =  "http://60.10.25.208:8080/crp_xxh/inter/call/voiceNumber.do";

    /**
     * 证件类型
     * 身份证
     */
    public static final String CARD_ID_CARD_TYPE = "0101" ;

    /**
     * 证件类型
     * 户口簿
     */
    public static final String CARD_RESIDENCE_BOOKLET_TYPE = "0102";

    /**
     * 证件类型
     * 公众证件—军人身份证
     */
    public static final String CARD_SOLDIER_ID_CARD_TYPE = "0103";

    /**
     * 证件类型
     * 公众证件—武装警察身份证
     */
    public static final String CARD_POLICE_ID_CARD_TYPE = "0104";

    /**
     * 证件类型
     * 公众证件—港澳居民往来内地通行
     */
    public static final String CARD_GANGAO_PASS_TYPE = "0105";

    /**
     * 证件类型
     * 公众证件—台湾居民往来大陆通行证
     */
    public static final String CARD_TAIWAN_PASS_TYPE = "0106";

    /**
     * 证件类型
     * 公众证件—护照
     */
    public static final String CARD_PASSPORT_TYPE = "0107";

    /**
     * 证件类型
     * 公众证件—其他个人证件
     */
    public static final String CARD_OTHER_TYPE = "0199";

    /**
     * 证件类型
     * 未知(手机号码不存在或者本身非实名)
     */
    public static final String CARD_UNKNOWN_TYPE = "0999";
}
