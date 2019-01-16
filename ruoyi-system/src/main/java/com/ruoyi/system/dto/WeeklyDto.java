package com.ruoyi.system.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.Weekly;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * 周报查询页面
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper = false)
public class WeeklyDto extends Weekly {

    /**
     * 月份
     */
    private String month;

    /**
     * 用户
     */
    private List<SysUser> user;

    /**
     * 姓名
     */
    private String name;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 部门id
     */
    private Integer departmentId;

    /**
     * 时间线
     */
    private String timeLine;

    /**
     * 第几周
     */
    private String week;

    /**
     * 周报
     */
    List<Weekly> weekly;
}
