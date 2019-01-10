package com.ruoyi.web.controller.weekly;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.DateUtilsPlus;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.system.domain.Weekly;
import com.ruoyi.system.mapper.WeeklyMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/weekly")
@Slf4j
public class WeeklyController extends BaseController {
    private String prefix = "system/weekly";

    @Autowired
    private WeeklyMapper weeklyMapper;

    /**
     * 添加周报页面
     *
     * @param model
     * @return
     */
    @RequiresPermissions("system:weekly:view")
    @GetMapping()
    public String weekly(Model model) {
        Date endDayOfWeek = DateUtilsPlus.getEndDayOfWeek();
        model.addAttribute("start", DateUtilsPlus.getBeginDayOfWeek());
        model.addAttribute("end", endDayOfWeek);
        model.addAttribute("weekType", DateUtilsPlus.getWeekOfMonth(endDayOfWeek));
        return prefix + "/add";
    }

    /**
     * 周报查询页面
     *
     * @return
     */
    @RequiresPermissions("system:weekly:view")
    @GetMapping("list")
    public String list() {
        return prefix + "/weekly";
    }

    /**
     * 个人周报详情页面
     *
     * @return
     */
    @GetMapping("detail")
    public String detail() {
        return prefix + "/detail";
    }

    /**
     * 添加周报
     *
     * @return
     */
    @PostMapping(value = "save")
    @ResponseBody
    public AjaxResult addWeekly(@RequestBody List<Weekly> weekly) throws JsonProcessingException {

        int i = weeklyMapper.batchInsertWeekly(weekly);
        return i > 0 ? AjaxResult.success() : AjaxResult.error();
    }
}
