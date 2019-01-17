package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 周报表 weekly
 *
 * @author ruoyi
 * @date 2019-01-09
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Weekly {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 当前周为本月第几周；
     */
    private Integer weekType;
    /**
     * 日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;
    /**
     * 客户/项目
     */
    private String project;
    /**
     * 项目区分
     */
    private Integer projectDist;
    /**
     * 作业区分
     */
    private Integer jobDist;
    /**
     * 作业内容
     */
    private String description;
    /**
     * 工时
     */
    private Integer workHours;
    /**
     * 列表排序
     */
    private Integer sort;
    /**
     * 插入时间
     */
    private Date addTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 课题\问题点
     **/
    private String problem;
    /**
     * 周几
     */
    private String whichDay;

    /**
     * 项目区分
     */
    private String projectDetail;

    /**
     * 作业区分
     */
    private String jobDetail;
}
