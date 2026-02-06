package com.hospital.registration.service.impl;

import java.util.List;
import com.hospital.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hospital.registration.mapper.HospitalDeptsMapper;
import com.hospital.registration.domain.HospitalDepts;
import com.hospital.registration.service.IHospitalDeptsService;

/**
 * 医院科室Service业务层处理
 * 
 * @author hjh
 * @date 2026-02-06
 */
@Service
public class HospitalDeptsServiceImpl implements IHospitalDeptsService 
{
    @Autowired
    private HospitalDeptsMapper hospitalDeptsMapper;

    /**
     * 查询医院科室
     * 
     * @param deptId 医院科室主键
     * @return 医院科室
     */
    @Override
    public HospitalDepts selectHospitalDeptsByDeptId(Long deptId)
    {
        return hospitalDeptsMapper.selectHospitalDeptsByDeptId(deptId);
    }

    /**
     * 查询医院科室列表
     * 
     * @param hospitalDepts 医院科室
     * @return 医院科室
     */
    @Override
    public List<HospitalDepts> selectHospitalDeptsList(HospitalDepts hospitalDepts)
    {
        return hospitalDeptsMapper.selectHospitalDeptsList(hospitalDepts);
    }

    /**
     * 新增医院科室
     * 
     * @param hospitalDepts 医院科室
     * @return 结果
     */
    @Override
    public int insertHospitalDepts(HospitalDepts hospitalDepts)
    {
        hospitalDepts.setCreateTime(DateUtils.getNowDate());
        return hospitalDeptsMapper.insertHospitalDepts(hospitalDepts);
    }

    /**
     * 修改医院科室
     * 
     * @param hospitalDepts 医院科室
     * @return 结果
     */
    @Override
    public int updateHospitalDepts(HospitalDepts hospitalDepts)
    {
        hospitalDepts.setUpdateTime(DateUtils.getNowDate());
        return hospitalDeptsMapper.updateHospitalDepts(hospitalDepts);
    }

    /**
     * 批量删除医院科室
     * 
     * @param deptIds 需要删除的医院科室主键
     * @return 结果
     */
    @Override
    public int deleteHospitalDeptsByDeptIds(Long[] deptIds)
    {
        return hospitalDeptsMapper.deleteHospitalDeptsByDeptIds(deptIds);
    }

    /**
     * 删除医院科室信息
     * 
     * @param deptId 医院科室主键
     * @return 结果
     */
    @Override
    public int deleteHospitalDeptsByDeptId(Long deptId)
    {
        return hospitalDeptsMapper.deleteHospitalDeptsByDeptId(deptId);
    }
}
