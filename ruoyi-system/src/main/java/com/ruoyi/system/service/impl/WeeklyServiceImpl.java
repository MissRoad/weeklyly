package com.ruoyi.system.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.ruoyi.common.constant.GeneralDataConstants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.DateUtilsPlus;
import com.ruoyi.common.utils.ExcelUtil;
import com.ruoyi.system.domain.GeneralData;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.Weekly;
import com.ruoyi.system.dto.WeeklyDto;
import com.ruoyi.system.mapper.GeneralDataMapper;
import com.ruoyi.system.mapper.WeeklyMapper;
import com.ruoyi.system.service.IWeeklyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
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

    @Override
    public void exportExcel(WeeklyDto weeklyDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //根据条件查询的周报结果
        List<List<WeeklyDto>> weekly = this.getWeekly(weeklyDto);
        File file = ResourceUtils.getFile("classpath:static/template/weekly_template.xlsx");
        FileInputStream is = new FileInputStream(file);
        //创建excel文件
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        for (int i = 0; i < weekly.size(); i++) {
            String name = weekly.get(i).get(0).getName();
            Date time = weekly.get(i).get(0).getTime();
            //创建一个excel的sheet
            XSSFSheet sheet = workbook.cloneSheet(i);
            //设置当前sheet名
            workbook.setSheetName(i + 1, name);
            //设置姓名
            XSSFRow row1 = sheet.getRow(0);
            XSSFCell cell = row1.getCell(1);
            sheet.getRow(0).getCell(1).setCellValue(name);
            //设置期间
            sheet.getRow(1).getCell(1).setCellValue(DateUtilsPlus.getMondayOrSunday(time, DateUtilsPlus.MONDAY));
            sheet.getRow(1).getCell(3).setCellValue(DateUtilsPlus.getMondayOrSunday(time, DateUtilsPlus.SUNDAY));
            //定义从第几行开始插
            int rowNum = 3;
            for (WeeklyDto r : weekly.get(i)) {
                ExcelUtil.copyRows(sheet, rowNum, 1);
                XSSFRow row = sheet.getRow(rowNum);
                //日期
                row.getCell(0).setCellValue(r.getTime());
                //星期
                row.getCell(1).setCellValue(r.getWhichDay());
                //项目/客户
                row.getCell(2).setCellValue(r.getProject());
                //项目区分
                row.getCell(3).setCellValue(r.getProjectDetail());
                //作业区分
                row.getCell(4).setCellValue(r.getJobDetail());
                //作业内容描述
                row.getCell(5).setCellValue(r.getDescription());
                //工时（h）
                row.getCell(6).setCellValue(r.getWorkHours());
                //课题及问题点
                row.getCell(7).setCellValue(r.getProblem());
                rowNum = rowNum + 1;
            }
        }
        workbook.removeSheetAt(0);
        response.reset();
        OutputStream out = response.getOutputStream();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vdn.ms-excel");
        String fileName = ExcelUtil.toUtf8String("周报_" + DateUtils.dateTimeNow(DateUtils.YYYYMMDDHHMMSS) + ".xlsx");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        workbook.write(out);
        out.flush();
        out.close();
    }

    @Override
    public List<List<WeeklyDto>> getWeekly(WeeklyDto weeklyDto) {
        List<WeeklyDto> weeklyDtoList = weeklyMapper.selectWeekly(weeklyDto);
        List<List<WeeklyDto>> weeks = Lists.newArrayList();
        List<GeneralData> projects = generalDataMapper.selectGeneralByType(GeneralDataConstants.PRO_DIS);
        List<GeneralData> jobs = generalDataMapper.selectGeneralByType(GeneralDataConstants.JOB_DIS);
        HashSet<Integer> uid = Sets.newHashSet();
        weeklyDtoList.forEach(r -> {
            if (!uid.contains(uid)) {
                uid.add(r.getUid());
            }
        });
        uid.forEach(r -> {
            List<WeeklyDto> list = Lists.newArrayList();
            weeklyDtoList.forEach(w -> {
                if (r.equals(w.getUid())) {
                    projects.stream().filter(n -> n.getGeneralKey().equals(w.getProjectDist().toString())).
                            collect(Collectors.toList()).forEach(n -> {
                        w.setProjectDetail(n.getValue());
                    });
                    jobs.stream().filter(n -> n.getGeneralKey().equals(w.getJobDist().toString())).
                            collect(Collectors.toList()).forEach(n -> {
                        w.setJobDetail(n.getValue());
                    });
                    w.setWhichDay(DateUtilsPlus.getDay(w.getTime()));
                    list.add(w);
                }
            });
            weeks.add(list);
        });
        return weeks;
    }

}
