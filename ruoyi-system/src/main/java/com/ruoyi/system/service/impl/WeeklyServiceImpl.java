package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WeeklyMapper;
import com.ruoyi.system.domain.Weekly;
import com.ruoyi.system.service.IWeeklyService;
import com.ruoyi.common.support.Convert;

/**
 * 周报 服务层实现
 * 
 * @author ruoyi
 * @date 2019-01-09
 */
@Service
public class WeeklyServiceImpl implements IWeeklyService 
{
	@Autowired
	private WeeklyMapper weeklyMapper;

	/**
     * 查询周报信息
     * 
     * @param id 周报ID
     * @return 周报信息
     */
    @Override
	public Weekly selectWeeklyById(Integer id)
	{
	    return weeklyMapper.selectWeeklyById(id);
	}
	
	/**
     * 查询周报列表
     * 
     * @param weekly 周报信息
     * @return 周报集合
     */
	@Override
	public List<Weekly> selectWeeklyList(Weekly weekly)
	{
	    return weeklyMapper.selectWeeklyList(weekly);
	}
	
    /**
     * 新增周报
     * 
     * @param weekly 周报信息
     * @return 结果
     */
	@Override
	public int insertWeekly(Weekly weekly)
	{
	    return weeklyMapper.insertWeekly(weekly);
	}
	
	/**
     * 修改周报
     * 
     * @param weekly 周报信息
     * @return 结果
     */
	@Override
	public int updateWeekly(Weekly weekly)
	{
	    return weeklyMapper.updateWeekly(weekly);
	}

	/**
     * 删除周报对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWeeklyByIds(String ids)
	{
		return weeklyMapper.deleteWeeklyByIds(Convert.toStrArray(ids));
	}
	
}
