package com.hospital.doctor.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.hospital.common.utils.DateUtils;
import com.hospital.patient.domain.HospitalPatient;
import com.hospital.patient.mapper.HospitalPatientMapper;
import com.hospital.reg.domain.HospitalReg;
import com.hospital.reg.mapper.HospitalRegMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hospital.doctor.mapper.HospitalDoctorMapper;
import com.hospital.doctor.domain.HospitalDoctor;
import com.hospital.doctor.service.IHospitalDoctorService;

/**
 * 医院医生Service业务层处理
 * 
 * @author hjh
 * @date 2026-02-07
 */
@Service
public class HospitalDoctorServiceImpl implements IHospitalDoctorService 
{
    @Autowired
    private HospitalDoctorMapper hospitalDoctorMapper;

    @Autowired
    private HospitalRegMapper hospitalRegMapper;
    @Autowired
    private HospitalPatientMapper hospitalPatientMapper;

    /**
     * 查询医院医生
     * 
     * @param doctorId 医院医生主键
     * @return 医院医生
     */
    @Override
    public HospitalDoctor selectHospitalDoctorByDoctorId(Long doctorId)
    {
        return hospitalDoctorMapper.selectHospitalDoctorByDoctorId(doctorId);
    }

    /**
     * 查询医院医生列表
     * 
     * @param hospitalDoctor 医院医生
     * @return 医院医生
     */
    @Override
    public List<HospitalDoctor> selectHospitalDoctorList(HospitalDoctor hospitalDoctor)
    {
        return hospitalDoctorMapper.selectHospitalDoctorList(hospitalDoctor);
    }

    /**
     * 新增医院医生
     * 
     * @param hospitalDoctor 医院医生
     * @return 结果
     */
    @Override
    public int insertHospitalDoctor(HospitalDoctor hospitalDoctor)
    {
        hospitalDoctor.setCreateTime(DateUtils.getNowDate());
        return hospitalDoctorMapper.insertHospitalDoctor(hospitalDoctor);
    }

    /**
     * 修改医院医生
     * 
     * @param hospitalDoctor 医院医生
     * @return 结果
     */
    @Override
    public int updateHospitalDoctor(HospitalDoctor hospitalDoctor)
    {
        hospitalDoctor.setUpdateTime(DateUtils.getNowDate());
        return hospitalDoctorMapper.updateHospitalDoctor(hospitalDoctor);
    }

    /**
     * 批量删除医院医生
     * 
     * @param doctorIds 需要删除的医院医生主键
     * @return 结果
     */
    @Override
    public int deleteHospitalDoctorByDoctorIds(Long[] doctorIds)
    {
        return hospitalDoctorMapper.deleteHospitalDoctorByDoctorIds(doctorIds);
    }

    /**
     * 删除医院医生信息
     * 
     * @param doctorId 医院医生主键
     * @return 结果
     */
    @Override
    public int deleteHospitalDoctorByDoctorId(Long doctorId)
    {
        return hospitalDoctorMapper.deleteHospitalDoctorByDoctorId(doctorId);
    }

    // ========== 新增：实现selectMyPatientList方法 ==========
    @Override
    public List<HospitalPatient> selectMyPatientList(Long doctorId)
    {
        // 步骤1：根据医生ID查询所有关联的挂号记录（sys_hospital_reg表）
        HospitalReg regQuery = new HospitalReg();
        regQuery.setDoctorId(doctorId); // 筛选当前医生的挂号记录
        List<HospitalReg> regList = hospitalRegMapper.selectHospitalRegList(regQuery);

        // 步骤2：如果没有挂号记录，直接返回空列表
        if (regList.isEmpty()) {
            return new ArrayList<>();
        }

        // 步骤3：从挂号记录中提取所有患者ID（去重，避免同一患者多次挂号重复显示）
        List<Long> patientIds = regList.stream()
                .map(HospitalReg::getPatientId) // 从挂号记录中获取patient_id
                .distinct() // 去重
                .collect(Collectors.toList());

        // 步骤4：根据患者ID批量查询患者信息并返回
        return hospitalPatientMapper.selectHospitalPatientByPatientIds(patientIds);
    }
}
