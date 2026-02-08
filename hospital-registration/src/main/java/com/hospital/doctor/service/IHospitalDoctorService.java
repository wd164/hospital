package com.hospital.doctor.service;

import java.util.List;
import com.hospital.doctor.domain.HospitalDoctor;
import com.hospital.patient.domain.HospitalPatient;

/**
 * 医院医生Service接口
 * 
 * @author hjh
 * @date 2026-02-07
 */
public interface IHospitalDoctorService 
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
     * 批量删除医院医生
     * 
     * @param doctorIds 需要删除的医院医生主键集合
     * @return 结果
     */
    public int deleteHospitalDoctorByDoctorIds(Long[] doctorIds);

    /**
     * 删除医院医生信息
     * 
     * @param doctorId 医院医生主键
     * @return 结果
     */
    public int deleteHospitalDoctorByDoctorId(Long doctorId);

    // ========== 新增：查看我的患者列表方法 ==========
    /**
     * 根据医生ID查询对应的患者列表
     * @param doctorId 医生ID（即当前登录用户ID）
     * @return 患者列表
     */
    List<HospitalPatient> selectMyPatientList(Long doctorId);
}
