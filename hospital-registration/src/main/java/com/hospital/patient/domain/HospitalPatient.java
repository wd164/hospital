package com.hospital.patient.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hospital.common.annotation.Excel;
import com.hospital.common.core.domain.BaseEntity;

/**
 * 医院患者对象 sys_hospital_patient
 * 
 * @author hjh
 * @date 2026-02-06
 */
public class HospitalPatient extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 患者ID */
    private Long patientId;

    /** 患者姓名 */
    @Excel(name = "患者姓名")
    private String patientName;

    /** 患者性别（0女 1男 2未知） */
    @Excel(name = "患者性别", readConverterExp = "0=女,1=男,2=未知")
    private String patientGender;

    /** 患者年龄 */
    @Excel(name = "患者年龄")
    private Long patientAge;

    /** 身份证号（唯一） */
    @Excel(name = "身份证号", readConverterExp = "唯=一")
    private String patientIdcard;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String patientPhone;

    /** 家庭地址 */
    @Excel(name = "家庭地址")
    private String patientAddress;

    /** 状态（0正常 1禁用） */
    private String status;

    public void setPatientId(Long patientId) 
    {
        this.patientId = patientId;
    }

    public Long getPatientId() 
    {
        return patientId;
    }

    public void setPatientName(String patientName) 
    {
        this.patientName = patientName;
    }

    public String getPatientName() 
    {
        return patientName;
    }

    public void setPatientGender(String patientGender) 
    {
        this.patientGender = patientGender;
    }

    public String getPatientGender() 
    {
        return patientGender;
    }

    public void setPatientAge(Long patientAge) 
    {
        this.patientAge = patientAge;
    }

    public Long getPatientAge() 
    {
        return patientAge;
    }

    public void setPatientIdcard(String patientIdcard) 
    {
        this.patientIdcard = patientIdcard;
    }

    public String getPatientIdcard() 
    {
        return patientIdcard;
    }

    public void setPatientPhone(String patientPhone) 
    {
        this.patientPhone = patientPhone;
    }

    public String getPatientPhone() 
    {
        return patientPhone;
    }

    public void setPatientAddress(String patientAddress) 
    {
        this.patientAddress = patientAddress;
    }

    public String getPatientAddress() 
    {
        return patientAddress;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("patientId", getPatientId())
            .append("patientName", getPatientName())
            .append("patientGender", getPatientGender())
            .append("patientAge", getPatientAge())
            .append("patientIdcard", getPatientIdcard())
            .append("patientPhone", getPatientPhone())
            .append("patientAddress", getPatientAddress())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
