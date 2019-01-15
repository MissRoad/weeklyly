package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;

import java.util.Date;

/**
 * 通用数据表 general_data
 *
 * @author ruoyi
 * @date 2019-01-15
 */
public class GeneralData extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Integer id;
    /**
     * 类型
     */
    private String type;
    /**
     * 键
     */
    private String generalKey;
    /**
     * 值
     */
    private String value;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 是否删除[0：未删除；1：删除]
     */
    private String isDel;
    /**
     *
     */
    private Date createTime;
    /**
     *
     */
    private Date updateTime;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getGeneralKey() {
        return generalKey;
    }

    public void setGeneralKey(String generalKey) {
        this.generalKey = generalKey;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSort() {
        return sort;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("type", getType())
                .append("key", getGeneralKey())
                .append("value", getValue())
                .append("sort", getSort())
                .append("isDel", getIsDel())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
