package com.hospital.reg.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hospital.common.annotation.Excel;
import com.hospital.common.core.domain.BaseEntity;

/**
 * 医院挂号预约对象 sys_hospital_reg
 * 
 * @author hjh
 * @date 2026-02-07
 */
public class HospitalReg extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 挂号ID */
    private Long regId;

    /** 患者ID */
    @Excel(name = "患者ID")
    private Long patientId;

    /** 医生ID */
    @Excel(name = "医生ID")
    private Long doctorId;

    /** 科室ID（独立存储，不依赖医生关联） */
    @Excel(name = "科室ID", readConverterExp = "独=立存储，不依赖医生关联")
    private Long deptId;

    /** 挂号时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "挂号时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date regTime;

    /** 预约就诊时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预约就诊时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date appointmentTime;

    /** 挂号费用 */
    @Excel(name = "挂号费用")
    private BigDecimal regFee;

    /** 挂号状态（0取消 1待就诊 2已就诊 3已过期） */
    private String regStatus;

    public void setRegId(Long regId) 
    {
        this.regId = regId;
    }

    public Long getRegId() 
    {
        return regId;
    }

    public void setPatientId(Long patientId) 
    {
        this.patientId = patientId;
    }

    public Long getPatientId() 
    {
        return patientId;
    }

    public void setDoctorId(Long doctorId) 
    {
        this.doctorId = doctorId;
    }

    public Long getDoctorId() 
    {
        return doctorId;
    }

    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }

    public void setRegTime(Date regTime) 
    {
        this.regTime = regTime;
    }

    public Date getRegTime() 
    {
        return regTime;
    }

    public void setAppointmentTime(Date appointmentTime) 
    {
        this.appointmentTime = appointmentTime;
    }

    public Date getAppointmentTime() 
    {
        return appointmentTime;
    }

    public void setRegFee(BigDecimal regFee) 
    {
        this.regFee = regFee;
    }

    public BigDecimal getRegFee() 
    {
        return regFee;
    }

    public void setRegStatus(String regStatus) 
    {
        this.regStatus = regStatus;
    }

    public String getRegStatus() 
    {
        return regStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("regId", getRegId())
            .append("patientId", getPatientId())
            .append("doctorId", getDoctorId())
            .append("deptId", getDeptId())
            .append("regTime", getRegTime())
            .append("appointmentTime", getAppointmentTime())
            .append("regFee", getRegFee())
            .append("regStatus", getRegStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
