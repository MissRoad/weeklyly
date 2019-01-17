package com.ruoyi.system.service;

import com.ruoyi.system.domain.Weekly;
import com.ruoyi.system.dto.WeeklyDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * 周报 服务层
 *
 * @author ruoyi
 * @date 2019-01-09
 */
public interface IWeeklyService {
    /**
     * 查询周报列表
     *
     * @param uid       uid
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 周报集合
     */
    public List<Weekly> selectWeeklyList(Integer uid, Date startDate, Date endDate);

    /**
     * 添加周报
     *
     * @param list 集合
     * @param uid  用户id
     * @return
     */
    public boolean batchInsertWeekly(List<Weekly> list, Integer uid);

    /**
     * 周报展示页面周报查询
     *
     * @param weeklyDto
     * @return
     */
    public List<WeeklyDto> getWeekLy(WeeklyDto weeklyDto);

    /**
     * 周报详情
     *
     * @param weeklyDto
     * @return
     */
    public List<WeeklyDto> getWeeklyDetail(WeeklyDto weeklyDto);

    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    public void exportExcel(WeeklyDto weeklyDto, HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * 获取导出数据
     *
     * @param weeklyDto
     * @return
     */
    public List<List<WeeklyDto>> getWeekly(WeeklyDto weeklyDto);
}
