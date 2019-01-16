package com.ruoyi.system.service.impl;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.GeneralDataConstants;
import com.ruoyi.common.json.JSONObject;
import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.DateUtilsPlus;
import com.ruoyi.system.domain.GeneralData;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.Weekly;
import com.ruoyi.system.dto.WeeklyDto;
import com.ruoyi.system.mapper.GeneralDataMapper;
import com.ruoyi.system.mapper.WeeklyMapper;
import com.ruoyi.system.service.IWeeklyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 周报 服务层实现
 *
 * @author ruoyi
 * @date 2019-01-09
 */
@Service
@Slf4j
public class WeeklyServiceImpl implements IWeeklyService {
    @Autowired
    private WeeklyMapper weeklyMapper;

    @Autowired
    private GeneralDataMapper generalDataMapper;

    /**
     * 查询周报信息
     *
     * @param id 周报ID
     * @return 周报信息
     */
    @Override
    public Weekly selectWeeklyById(Integer id) {
        return weeklyMapper.selectWeeklyById(id);
    }

    /**
     * 查询周报列表
     *
     * @param uid       uid
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 周报集合
     */
    @Override
    public List<Weekly> selectWeeklyList(Integer uid, Date startDate, Date endDate) {
        return weeklyMapper.selectWeeklyList(uid, startDate, endDate);
    }

    /**
     * 新增周报
     *
     * @param weekly 周报信息
     * @return 结果
     */
    @Override
    public int insertWeekly(Weekly weekly) {
        return weeklyMapper.insertWeekly(weekly);
    }

    /**
     * 修改周报
     *
     * @param weekly 周报信息
     * @return 结果
     */
    @Override
    public int updateWeekly(Weekly weekly) {
        return weeklyMapper.updateWeekly(weekly);
    }

    /**
     * 删除周报对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWeeklyByIds(String ids) {
        return weeklyMapper.deleteWeeklyByIds(Convert.toStrArray(ids));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchInsertWeekly(List<Weekly> list, Integer uid) {
        //添加前先判断本周的周报是否已经添加，若已经添加则删除在添加
        List<Weekly> weeklies = weeklyMapper.selectWeeklyList(uid, DateUtilsPlus.getBeginDayOfWeek(), DateUtilsPlus.getEndDayOfWeek());
        if (!weeklies.isEmpty()) {
            String[] arr = new String[7];
            for (int i = 0; i < weeklies.size(); i++) {
                arr[i] = String.valueOf(weeklies.get(i).getId());
            }
            int i = weeklyMapper.deleteWeeklyByIds(arr);
            Validate.isTrue(i > 0);
        }
        //插入周报
        for (Weekly weekly : list) {
            weekly.setUid(uid);
            int i = weeklyMapper.insertWeekly(weekly);
            Validate.isTrue(i > 0);
        }
        return true;
    }

    @Override
    public List<WeeklyDto> getWeekLy(WeeklyDto weeklyDto) {
        List<WeeklyDto> weekly = weeklyMapper.selectWeekly(weeklyDto);
        HashSet<String> timeLine = Sets.newHashSet();
        List<WeeklyDto> weeklyDtoList = Lists.newArrayList();
        weekly.forEach(r -> {
            String s = DateUtils.parseDateToStr(DateUtils.YYYY_MM, r.getTime());
            if (!timeLine.contains(s)) {
                timeLine.add(s);
            }
        });
        timeLine.forEach(r -> {
            WeeklyDto week = new WeeklyDto();
            week.setTimeLine(r);
            List<SysUser> users = Lists.newArrayList();
            HashSet<Integer> uid = Sets.newHashSet();
            weekly.forEach(m -> {
                SysUser sysUser = new SysUser();
                String time = DateUtils.parseDateToStr(DateUtils.YYYY_MM, m.getTime());
                if (r.equals(time)) {
                    sysUser.setUserName(m.getName());
                    sysUser.setUserId(m.getUid().longValue());
                    sysUser.setDeptId(m.getDepartmentId().longValue());
                    if (!uid.contains(m.getUid())) {
                        users.add(sysUser);
                        uid.add(m.getUid());
                    }
                }
            });
            week.setUser(users);
            weeklyDtoList.add(week);
        });
        return weeklyDtoList;
    }

    @Override
    public List<WeeklyDto> getWeeklyDetail(WeeklyDto weeklyDto) {
        List<WeeklyDto> weeklyList = weeklyMapper.selectWeekly(weeklyDto);
        List<GeneralData> projects = generalDataMapper.selectGeneralByType(GeneralDataConstants.PRO_DIS);
        List<GeneralData> jobs = generalDataMapper.selectGeneralByType(GeneralDataConstants.JOB_DIS);
        List<WeeklyDto> week = Lists.newArrayList();
        HashSet<Integer> weekDay = Sets.newHashSet();
        weeklyList.forEach(r -> {
            Integer weekType = r.getWeekType();
            if (!weekDay.contains(weekType)) {
                weekDay.add(weekType);
            }
        });
        for (Integer w : weekDay) {
            List<Weekly> list = Lists.newArrayList();
            WeeklyDto weekly = new WeeklyDto();
            weekly.setWeek(DateUtilsPlus.getWeek(w));
            for (WeeklyDto t : weeklyList) {
                Weekly w1 = new Weekly();
                if (w.equals(t.getWeekType())) {
                    BeanUtils.copyProperties(t, w1);
                    w1.setWhichDay(DateUtilsPlus.getDay(w1.getTime()));
                    jobs.stream().filter(r -> r.getGeneralKey().equals(w1.getJobDist().toString())).collect(Collectors.toList()).forEach(r -> {
                        w1.setJobDetail(r.getValue());
                    });
                    projects.stream().filter(r -> r.getGeneralKey().equals(w1.getProjectDist().toString())).collect(Collectors.toList()).forEach(r -> {
                        w1.setProjectDetail(r.getValue());
                    });
                    list.add(w1);
                }
            }
            weekly.setWeekly(list);
            week.add(weekly);
        }
        return week;
    }

}
