package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;
import java.util.Date;

/**
 * 周报表 weekly
 * 
 * @author ruoyi
 * @date 2019-01-09
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Weekly
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 用户id */
	private Integer uid;
	/** 当前周为本月第几周； */
	private Integer weekType;
	/** 日期 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date time;
	/** 客户/项目 */
	private String project;
	/** 项目区分 */
	private Integer projectDist;
	/** 作业区分 */
	private Integer jobDist;
	/** 作业内容 */
	private String description;
	/** 工时 */
	private Integer workHours;
	/** 列表排序 */
	private Integer sort;
	/** 插入时间 */
	private Date addTime;
	/** 更新时间 */
	private Date updateTime;
	/**课题\问题点	**/
	private String problem;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setUid(Integer uid) 
	{
		this.uid = uid;
	}

	public Integer getUid() 
	{
		return uid;
	}
	public void setWeekType(Integer weekType) 
	{
		this.weekType = weekType;
	}

	public Integer getWeekType() 
	{
		return weekType;
	}
	public void setTime(Date time) 
	{
		this.time = time;
	}

	public Date getTime() 
	{
		return time;
	}
	public void setProject(String project) 
	{
		this.project = project;
	}

	public String getProject() 
	{
		return project;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}

	public String getDescription() 
	{
		return description;
	}
	public void setWorkHours(Integer workHours) 
	{
		this.workHours = workHours;
	}

	public Integer getWorkHours() 
	{
		return workHours;
	}
	public void setSort(Integer sort) 
	{
		this.sort = sort;
	}

	public Integer getSort() 
	{
		return sort;
	}
	public void setAddTime(Date addTime) 
	{
		this.addTime = addTime;
	}

	public Date getAddTime() 
	{
		return addTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public Integer getProjectDist() {
		return projectDist;
	}

	public void setProjectDist(Integer projectDist) {
		this.projectDist = projectDist;
	}

	public Integer getJobDist() {
		return jobDist;
	}

	public void setJobDist(Integer jobDist) {
		this.jobDist = jobDist;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("uid", getUid())
            .append("weekType", getWeekType())
            .append("time", getTime())
            .append("project", getProject())
            .append("description", getDescription())
            .append("workHours", getWorkHours())
            .append("sort", getSort())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
