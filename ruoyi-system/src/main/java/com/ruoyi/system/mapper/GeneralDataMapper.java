package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.GeneralData;

import java.util.List;

/**
 * 通用数据 数据层
 *
 * @author ruoyi
 * @date 2019-01-15
 */
public interface GeneralDataMapper {
    /**
     * 查询通用数据信息
     *
     * @param id 通用数据ID
     * @return 通用数据信息
     */
    public GeneralData selectDataById(Integer id);

    /**
     * 查询通用数据列表
     *
     * @param data 通用数据信息
     * @return 通用数据集合
     */
    public List<GeneralData> selectDataList(GeneralData data);

    /**
     * 新增通用数据
     *
     * @param data 通用数据信息
     * @return 结果
     */
    public int insertData(GeneralData data);

    /**
     * 修改通用数据
     *
     * @param data 通用数据信息
     * @return 结果
     */
    public int updateData(GeneralData data);

    /**
     * 删除通用数据
     *
     * @param id 通用数据ID
     * @return 结果
     */
    public int deleteDataById(Integer id);

    /**
     * 批量删除通用数据
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDataByIds(String[] ids);

    /**
     * 根据类型查找通用数据
     *
     * @param type
     * @return
     */
    public List<GeneralData> selectGeneralByType(String type);
}