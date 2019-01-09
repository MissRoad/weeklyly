package com.ruoyi.system.service;

import com.ruoyi.system.domain.Weekly;
import java.util.List;

/**
 * 周报 服务层
 * 
 * @author ruoyi
 * @date 2019-01-09
 */
public interface IWeeklyService 
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
     * 删除周报信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWeeklyByIds(String ids);
	
}
