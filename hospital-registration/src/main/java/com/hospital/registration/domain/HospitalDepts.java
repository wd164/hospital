package com.hospital.registration.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hospital.common.annotation.Excel;
import com.hospital.common.core.domain.BaseEntity;

/**
 * 医院科室对象 sys_hospital_depts
 * 
 * @author hjh
 * @date 2026-02-06
 */
public class HospitalDepts extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 科室ID */
    private Long deptId;

    /** 科室名称 */
    @Excel(name = "科室名称")
    private String deptName;

    /** 科室编码 */
    @Excel(name = "科室编码")
    private String deptCode;

    /** 父科室ID（0为顶级科室） */
    @Excel(name = "父科室ID", readConverterExp = "0=为顶级科室")
    private Long parentId;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Long orderNum;

    /** 科室状态（0正常 1停用） */
    @Excel(name = "科室状态", readConverterExp = "0=正常,1=停用")
    private String status;

    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }

    public void setDeptName(String deptName) 
    {
        this.deptName = deptName;
    }

    public String getDeptName() 
    {
        return deptName;
    }

    public void setDeptCode(String deptCode) 
    {
        this.deptCode = deptCode;
    }

    public String getDeptCode() 
    {
        return deptCode;
    }

    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }

    public void setOrderNum(Long orderNum) 
    {
        this.orderNum = orderNum;
    }

    public Long getOrderNum() 
    {
        return orderNum;
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
            .append("deptId", getDeptId())
            .append("deptName", getDeptName())
            .append("deptCode", getDeptCode())
            .append("parentId", getParentId())
            .append("orderNum", getOrderNum())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
