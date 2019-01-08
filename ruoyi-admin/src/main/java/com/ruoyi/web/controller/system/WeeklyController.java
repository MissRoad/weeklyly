package com.ruoyi.web.controller.system;

import com.ruoyi.common.utils.DateUtilsPlus;
import com.ruoyi.framework.web.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/weekly")
public class WeeklyController extends BaseController {
    private String prefix = "system/weekly";

    @RequiresPermissions("system:weekly:view")
    @GetMapping()
    public String weekly(Model model) {
        model.addAttribute("start", DateUtilsPlus.getBeginDayOfWeek());
        model.addAttribute("end", DateUtilsPlus.getEndDayOfWeek());
        return prefix + "/add";
    }
}
