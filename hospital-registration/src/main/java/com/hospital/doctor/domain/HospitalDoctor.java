package com.hospital.doctor.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hospital.common.annotation.Excel;
import com.hospital.common.core.domain.BaseEntity;

/**
 * 医院医生对象 sys_hospital_doctor
 * 
 * @author hjh
 * @date 2026-02-07
 */
public class HospitalDoctor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 医生ID */
    private Long doctorId;

    /** 医生姓名 */
    @Excel(name = "医生姓名")
    private String doctorName;

    /** 性别（0女 1男 2未知） */
    @Excel(name = "性别", readConverterExp = "0=女,1=男,2=未知")
    private String doctorGender;

    /** 职称（如主任医师、主治医师） */
    @Excel(name = "职称", readConverterExp = "如=主任医师、主治医师")
    private String doctorTitle;

    /** 所属科室ID */
    @Excel(name = "所属科室ID")
    private Long deptId;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String doctorPhone;

    /** 医生简介 */
    @Excel(name = "医生简介")
    private String doctorIntro;

    /** 状态（0正常 1停用） */
    private String status;

    public void setDoctorId(Long doctorId) 
    {
        this.doctorId = doctorId;
    }

    public Long getDoctorId() 
    {
        return doctorId;
    }

    public void setDoctorName(String doctorName) 
    {
        this.doctorName = doctorName;
    }

    public String getDoctorName() 
    {
        return doctorName;
    }

    public void setDoctorGender(String doctorGender) 
    {
        this.doctorGender = doctorGender;
    }

    public String getDoctorGender() 
    {
        return doctorGender;
    }

    public void setDoctorTitle(String doctorTitle) 
    {
        this.doctorTitle = doctorTitle;
    }

    public String getDoctorTitle() 
    {
        return doctorTitle;
    }

    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }

    public void setDoctorPhone(String doctorPhone) 
    {
        this.doctorPhone = doctorPhone;
    }

    public String getDoctorPhone() 
    {
        return doctorPhone;
    }

    public void setDoctorIntro(String doctorIntro) 
    {
        this.doctorIntro = doctorIntro;
    }

    public String getDoctorIntro() 
    {
        return doctorIntro;
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
            .append("doctorId", getDoctorId())
            .append("doctorName", getDoctorName())
            .append("doctorGender", getDoctorGender())
            .append("doctorTitle", getDoctorTitle())
            .append("deptId", getDeptId())
            .append("doctorPhone", getDoctorPhone())
            .append("doctorIntro", getDoctorIntro())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
