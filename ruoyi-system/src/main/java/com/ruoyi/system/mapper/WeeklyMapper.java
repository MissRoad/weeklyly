package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Weekly;
import java.util.List;	

/**
 * 周报 数据层
 * 
 * @author ruoyi
 * @date 2019-01-09
 */
public interface WeeklyMapper 
{
	/**
     * 查询周报信息
     * 
     * @param id 周报ID
     * @return 周报信息
     */
	public Weekly selectWeeklyById(Integer id);
	
	/**
     * 查询周报列表
     * 
     * @param weekly 周报信息
     * @return 周报集合
     */
	public List<Weekly> selectWeeklyList(Weekly weekly);
	
	/**
     * 新增周报
     * 
     * @param weekly 周报信息
     * @return 结果
     */
	public int insertWeekly(Weekly weekly);
	
	/**
     * 修改周报
     * 
     * @param weekly 周报信息
     * @return 结果
     */
	public int updateWeekly(Weekly weekly);
	
	/**
     * 删除周报
     * 
     * @param id 周报ID
     * @return 结果
     */
	public int deleteWeeklyById(Integer id);
	
	/**
     * 批量删除周报
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWeeklyByIds(String[] ids);
	
}