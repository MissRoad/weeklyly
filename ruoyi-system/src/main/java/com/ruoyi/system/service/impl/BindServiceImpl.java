package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BindMapper;
import com.ruoyi.system.domain.Bind;
import com.ruoyi.system.service.IBindService;
import com.ruoyi.common.support.Convert;

/**
 * 员工账号绑定 服务层实现
 * 
 * @author ruoyi
 * @date 2018-12-27
 */
@Service
public class BindServiceImpl implements IBindService 
{
	@Autowired
	private BindMapper bindMapper;

	/**
     * 查询员工账号绑定信息
     * 
     * @param id 员工账号绑定ID
     * @return 员工账号绑定信息
     */
    @Override
	public Bind selectBindById(Integer id)
	{
	    return bindMapper.selectBindById(id);
	}
	
	/**
     * 查询员工账号绑定列表
     * 
     * @param bind 员工账号绑定信息
     * @return 员工账号绑定集合
     */
	@Override
	public List<Bind> selectBindList(Bind bind)
	{
	    return bindMapper.selectBindList(bind);
	}
	
    /**
     * 新增员工账号绑定
     * 
     * @param bind 员工账号绑定信息
     * @return 结果
     */
	@Override
	public int insertBind(Bind bind)
	{
	    return bindMapper.insertBind(bind);
	}
	
	/**
     * 修改员工账号绑定
     * 
     * @param bind 员工账号绑定信息
     * @return 结果
     */
	@Override
	public int updateBind(Bind bind)
	{
	    return bindMapper.updateBind(bind);
	}

	/**
     * 删除员工账号绑定对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBindByIds(String ids)
	{
		return bindMapper.deleteBindByIds(Convert.toStrArray(ids));
	}
	
}
