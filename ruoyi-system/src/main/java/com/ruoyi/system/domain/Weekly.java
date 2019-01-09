package com.ruoyi.system.domain;

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
public class Weekly extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 用户id */
	private Integer uid;
	/** 当前周为本月第几周； */
	private Integer weekType;
	/** 日期 */
	private Date time;
	/** 客户/项目 */
	private String project;
	/** 作业内容 */
	private String description;
	/** 工时 */
	private Integer workHours;
	/** 列表排序 */
	private Integer sort;
	/** 插入时间 */
	private Date addTime;
	/** 更新时间 */
	private Date insertTime;

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
	public void setInsertTime(Date insertTime) 
	{
		this.insertTime = insertTime;
	}

	public Date getInsertTime() 
	{
		return insertTime;
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
            .append("insertTime", getInsertTime())
            .toString();
    }
}
