package com.hospital.registration.service;

import java.util.List;
import com.hospital.registration.domain.HospitalDepts;

/**
 * 医院科室Service接口
 * 
 * @author hjh
 * @date 2026-02-06
 */
public interface IHospitalDeptsService 
{
    /**
     * 查询医院科室
     * 
     * @param deptId 医院科室主键
     * @return 医院科室
     */
    public HospitalDepts selectHospitalDeptsByDeptId(Long deptId);

    /**
     * 查询医院科室列表
     * 
     * @param hospitalDepts 医院科室
     * @return 医院科室集合
     */
    public List<HospitalDepts> selectHospitalDeptsList(HospitalDepts hospitalDepts);

    /**
     * 新增医院科室
     * 
     * @param hospitalDepts 医院科室
     * @return 结果
     */
    public int insertHospitalDepts(HospitalDepts hospitalDepts);

    /**
     * 修改医院科室
     * 
     * @param hospitalDepts 医院科室
     * @return 结果
     */
    public int updateHospitalDepts(HospitalDepts hospitalDepts);

    /**
     * 批量删除医院科室
     * 
     * @param deptIds 需要删除的医院科室主键集合
     * @return 结果
     */
    public int deleteHospitalDeptsByDeptIds(Long[] deptIds);

    /**
     * 删除医院科室信息
     * 
     * @param deptId 医院科室主键
     * @return 结果
     */
    public int deleteHospitalDeptsByDeptId(Long deptId);
}
