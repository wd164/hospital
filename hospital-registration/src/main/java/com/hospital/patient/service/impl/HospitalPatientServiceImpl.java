package com.hospital.patient.service.impl;

import java.util.List;
import com.hospital.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hospital.patient.mapper.HospitalPatientMapper;
import com.hospital.patient.domain.HospitalPatient;
import com.hospital.patient.service.IHospitalPatientService;

/**
 * 医院患者Service业务层处理
 * 
 * @author hjh
 * @date 2026-02-06
 */
@Service
public class HospitalPatientServiceImpl implements IHospitalPatientService 
{
    @Autowired
    private HospitalPatientMapper hospitalPatientMapper;

    /**
     * 查询医院患者
     * 
     * @param patientId 医院患者主键
     * @return 医院患者
     */
    @Override
    public HospitalPatient selectHospitalPatientByPatientId(Long patientId)
    {
        return hospitalPatientMapper.selectHospitalPatientByPatientId(patientId);
    }

    /**
     * 查询医院患者列表
     * 
     * @param hospitalPatient 医院患者
     * @return 医院患者
     */
    @Override
    public List<HospitalPatient> selectHospitalPatientList(HospitalPatient hospitalPatient)
    {
        return hospitalPatientMapper.selectHospitalPatientList(hospitalPatient);
    }

    /**
     * 新增医院患者
     * 
     * @param hospitalPatient 医院患者
     * @return 结果
     */
    @Override
    public int insertHospitalPatient(HospitalPatient hospitalPatient)
    {
        hospitalPatient.setCreateTime(DateUtils.getNowDate());
        return hospitalPatientMapper.insertHospitalPatient(hospitalPatient);
    }

    /**
     * 修改医院患者
     * 
     * @param hospitalPatient 医院患者
     * @return 结果
     */
    @Override
    public int updateHospitalPatient(HospitalPatient hospitalPatient)
    {
        hospitalPatient.setUpdateTime(DateUtils.getNowDate());
        return hospitalPatientMapper.updateHospitalPatient(hospitalPatient);
    }

    /**
     * 批量删除医院患者
     * 
     * @param patientIds 需要删除的医院患者主键
     * @return 结果
     */
    @Override
    public int deleteHospitalPatientByPatientIds(Long[] patientIds)
    {
        return hospitalPatientMapper.deleteHospitalPatientByPatientIds(patientIds);
    }

    /**
     * 删除医院患者信息
     * 
     * @param patientId 医院患者主键
     * @return 结果
     */
    @Override
    public int deleteHospitalPatientByPatientId(Long patientId)
    {
        return hospitalPatientMapper.deleteHospitalPatientByPatientId(patientId);
    }
}
