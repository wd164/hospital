package com.hospital.patient.mapper;

import java.util.List;
import com.hospital.patient.domain.HospitalPatient;

/**
 * 医院患者Mapper接口
 * 
 * @author hjh
 * @date 2026-02-06
 */
public interface HospitalPatientMapper 
{
    /**
     * 查询医院患者
     * 
     * @param patientId 医院患者主键
     * @return 医院患者
     */
    public HospitalPatient selectHospitalPatientByPatientId(Long patientId);

    /**
     * 查询医院患者列表
     * 
     * @param hospitalPatient 医院患者
     * @return 医院患者集合
     */
    public List<HospitalPatient> selectHospitalPatientList(HospitalPatient hospitalPatient);

    /**
     * 新增医院患者
     * 
     * @param hospitalPatient 医院患者
     * @return 结果
     */
    public int insertHospitalPatient(HospitalPatient hospitalPatient);

    /**
     * 修改医院患者
     * 
     * @param hospitalPatient 医院患者
     * @return 结果
     */
    public int updateHospitalPatient(HospitalPatient hospitalPatient);

    /**
     * 删除医院患者
     * 
     * @param patientId 医院患者主键
     * @return 结果
     */
    public int deleteHospitalPatientByPatientId(Long patientId);

    /**
     * 批量删除医院患者
     * 
     * @param patientIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHospitalPatientByPatientIds(Long[] patientIds);
}
