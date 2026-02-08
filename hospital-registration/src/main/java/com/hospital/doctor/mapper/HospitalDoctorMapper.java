package com.hospital.doctor.mapper;

import java.util.List;
import com.hospital.doctor.domain.HospitalDoctor;

/**
 * 医院医生Mapper接口
 * 
 * @author hjh
 * @date 2026-02-07
 */
public interface HospitalDoctorMapper 
{
    /**
     * 查询医院医生
     * 
     * @param doctorId 医院医生主键
     * @return 医院医生
     */
    public HospitalDoctor selectHospitalDoctorByDoctorId(Long doctorId);

    /**
     * 查询医院医生列表
     * 
     * @param hospitalDoctor 医院医生
     * @return 医院医生集合
     */
    public List<HospitalDoctor> selectHospitalDoctorList(HospitalDoctor hospitalDoctor);

    /**
     * 新增医院医生
     * 
     * @param hospitalDoctor 医院医生
     * @return 结果
     */
    public int insertHospitalDoctor(HospitalDoctor hospitalDoctor);

    /**
     * 修改医院医生
     * 
     * @param hospitalDoctor 医院医生
     * @return 结果
     */
    public int updateHospitalDoctor(HospitalDoctor hospitalDoctor);

    /**
     * 删除医院医生
     * 
     * @param doctorId 医院医生主键
     * @return 结果
     */
    public int deleteHospitalDoctorByDoctorId(Long doctorId);

    /**
     * 批量删除医院医生
     * 
     * @param doctorIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHospitalDoctorByDoctorIds(Long[] doctorIds);
}
