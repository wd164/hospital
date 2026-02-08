package com.hospital.reg.mapper;

import java.util.List;
import com.hospital.reg.domain.HospitalReg;

/**
 * 医院挂号预约Mapper接口
 * 
 * @author hjh
 * @date 2026-02-07
 */
public interface HospitalRegMapper 
{
    /**
     * 查询医院挂号预约
     * 
     * @param regId 医院挂号预约主键
     * @return 医院挂号预约
     */
    public HospitalReg selectHospitalRegByRegId(Long regId);

    /**
     * 查询医院挂号预约列表
     * 
     * @param hospitalReg 医院挂号预约
     * @return 医院挂号预约集合
     */
    public List<HospitalReg> selectHospitalRegList(HospitalReg hospitalReg);

    /**
     * 新增医院挂号预约
     * 
     * @param hospitalReg 医院挂号预约
     * @return 结果
     */
    public int insertHospitalReg(HospitalReg hospitalReg);

    /**
     * 修改医院挂号预约
     * 
     * @param hospitalReg 医院挂号预约
     * @return 结果
     */
    public int updateHospitalReg(HospitalReg hospitalReg);

    /**
     * 删除医院挂号预约
     * 
     * @param regId 医院挂号预约主键
     * @return 结果
     */
    public int deleteHospitalRegByRegId(Long regId);

    /**
     * 批量删除医院挂号预约
     * 
     * @param regIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHospitalRegByRegIds(Long[] regIds);
}
