package com.ruoyi.system.service;

import com.ruoyi.system.domain.Bind;
import java.util.List;

/**
 * 员工账号绑定 服务层
 * 
 * @author ruoyi
 * @date 2018-12-27
 */
public interface IBindService 
{
	/**
     * 查询员工账号绑定信息
     * 
     * @param id 员工账号绑定ID
     * @return 员工账号绑定信息
     */
	public Bind selectBindById(Integer id);
	
	/**
     * 查询员工账号绑定列表
     * 
     * @param bind 员工账号绑定信息
     * @return 员工账号绑定集合
     */
	public List<Bind> selectBindList(Bind bind);
	
	/**
     * 新增员工账号绑定
     * 
     * @param bind 员工账号绑定信息
     * @return 结果
     */
	public int insertBind(Bind bind);
	
	/**
     * 修改员工账号绑定
     * 
     * @param bind 员工账号绑定信息
     * @return 结果
     */
	public int updateBind(Bind bind);
		
	/**
     * 删除员工账号绑定信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBindByIds(String ids);
	
}
