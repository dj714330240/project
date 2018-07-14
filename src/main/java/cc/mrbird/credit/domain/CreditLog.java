package cc.mrbird.credit.domain;

import cc.mrbird.common.annotation.ExportConfig;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_credit_log")
public class CreditLog implements Serializable {

    private static final long serialVersionUID = -6790334862410409053L;

    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "ID")
    @ExportConfig(value = "编号")
    private Long id;

    @Column(name = "NAME")
    @ExportConfig(value = "日志名称")
    private String name;

    @Column(name = "COUNTS")
    @ExportConfig(value = "操作次数")
    private Long counts;

    /**
     * 1 - 三要素核身
     */
    @Column(name = "TYPE")
    @ExportConfig(value = "操作类型")
    private Integer type;

    /**
     * 1 - 日
     * 2 - 月
     * 3 - 年
     */
    @Column(name = "TIME_TYPE")
    @ExportConfig(value = "日期类型")
    private Integer timeType;

    @Column(name = "USER_ID")
    @ExportConfig(value = "用户ID")
    private Long userId;

    @Column(name = "CREATE_TIME")
    @ExportConfig(value = "创建时间", convert = "c:cc.mrbird.common.util.poi.convert.TimeConvert")
    private Date createTime;

    /** 修改日期 */
    @Column(name = "MODIFY_DATE")
    @ExportConfig(value = "修改日期")
    private Date modifyDate;

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCounts() {
        return counts;
    }

    public void setCounts(Long counts) {
        this.counts = counts;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTimeType() {
        return timeType;
    }

    public void setTimeType(Integer timeType) {
        this.timeType = timeType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
