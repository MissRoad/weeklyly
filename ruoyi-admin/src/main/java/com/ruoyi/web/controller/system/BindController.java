package com.ruoyi.web.controller.system;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Bind;
import com.ruoyi.system.service.IBindService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.common.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;

/**
 * 员工账号绑定 信息操作处理
 * 
 * @author ruoyi
 * @date 2018-12-27
 */
@Controller
@RequestMapping("/system/bind")
public class BindController extends BaseController
{
    private String prefix = "system/bind";
	
	@Autowired
	private IBindService bindService;
	
	@RequiresPermissions("system:bind:view")
	@GetMapping()
	public String bind()
	{
	    return prefix + "/bind";
	}
	
	/**
	 * 查询员工账号绑定列表
	 */
	@RequiresPermissions("system:bind:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Bind bind)
	{
		startPage();
        List<Bind> list = bindService.selectBindList(bind);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出员工账号绑定列表
	 */
	@RequiresPermissions("system:bind:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Bind bind)
    {
    	List<Bind> list = bindService.selectBindList(bind);
        ExcelUtil<Bind> util = new ExcelUtil<Bind>(Bind.class);
        return util.exportExcel(list, "bind");
    }
	
	/**
	 * 新增员工账号绑定
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存员工账号绑定
	 */
	@RequiresPermissions("system:bind:add")
	@Log(title = "员工账号绑定", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Bind bind)
	{		
		return toAjax(bindService.insertBind(bind));
	}

	/**
	 * 修改员工账号绑定
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Bind bind = bindService.selectBindById(id);
		mmap.put("bind", bind);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存员工账号绑定
	 */
	@RequiresPermissions("system:bind:edit")
	@Log(title = "员工账号绑定", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Bind bind)
	{		
		return toAjax(bindService.updateBind(bind));
	}
	
	/**
	 * 删除员工账号绑定
	 */
	@RequiresPermissions("system:bind:remove")
	@Log(title = "员工账号绑定", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(bindService.deleteBindByIds(ids));
	}
	
}
