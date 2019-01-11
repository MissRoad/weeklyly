package com.ruoyi.web.controller.weekly;

import com.google.common.collect.Lists;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.DateUtilsPlus;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.system.domain.Weekly;
import com.ruoyi.system.dto.WeeklyDto;
import com.ruoyi.system.service.IWeeklyService;
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
    private IWeeklyService weeklyService;

    /**
     * 添加周报页面
     *
     * @param model
     * @return
     */
    @RequiresPermissions("system:weekly:view")
    @GetMapping()
    public String weekly(Model model) {
        Date start = DateUtilsPlus.getBeginDayOfWeek();
        Date end = DateUtilsPlus.getEndDayOfWeek();
        int weekType = DateUtilsPlus.getWeekOfMonth(end);
        List<Weekly> weeklies = weeklyService.selectWeeklyList(getUserId().intValue(), start, end);
        if (weeklies.isEmpty()) {
            model.addAttribute("hasWeekly", "0");
        } else {
            model.addAttribute("hasWeekly", "1");
        }
        model.addAttribute("weekly", weeklies);
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        model.addAttribute("weekType", weekType);
        return prefix + "/add";
    }

    /**
     * 周报查询页面
     *
     * @return
     */
    @RequiresPermissions("system:weekly:view")
    @GetMapping("list")
    public String list(WeeklyDto weeklyDto, Model model) {
        List<WeeklyDto> weekLy = weeklyService.getWeekLy(weeklyDto);
        model.addAttribute("week", weekLy);
        return prefix + "/weekly";
    }

    @GetMapping("lists")
    @ResponseBody
    public List<WeeklyDto> get(WeeklyDto weeklyDto) {
        List<WeeklyDto> weekLy = weeklyService.getWeekLy(weeklyDto);
        return weekLy;
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
    public AjaxResult addWeekly(@RequestBody List<Weekly> weekly) {
        boolean result = weeklyService.batchInsertWeekly(weekly, getUserId().intValue());
        return result ? AjaxResult.success() : AjaxResult.error();
    }
}
