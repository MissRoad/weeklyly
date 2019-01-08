package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;
import java.util.Date;

/**
 * 员工账号绑定表 fl_bind
 * 
 * @author ruoyi
 * @date 2018-12-27
 */
public class Bind extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 微信openid */
	private String openid;
	/** 员工姓名 */
	private String nickName;
	/** flowlites员工id */
	private String flUserCode;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setOpenid(String openid) 
	{
		this.openid = openid;
	}

	public String getOpenid() 
	{
		return openid;
	}
	public void setNickName(String nickName) 
	{
		this.nickName = nickName;
	}

	public String getNickName() 
	{
		return nickName;
	}
	public void setFlUserCode(String flUserCode) 
	{
		this.flUserCode = flUserCode;
	}

	public String getFlUserCode() 
	{
		return flUserCode;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("openid", getOpenid())
            .append("nickName", getNickName())
            .append("flUserCode", getFlUserCode())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
