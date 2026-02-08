package com.hospital.reg.service.impl;

import java.util.List;
import com.hospital.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hospital.reg.mapper.HospitalRegMapper;
import com.hospital.reg.domain.HospitalReg;
import com.hospital.reg.service.IHospitalRegService;

/**
 * 医院挂号预约Service业务层处理
 * 
 * @author hjh
 * @date 2026-02-07
 */
@Service
public class HospitalRegServiceImpl implements IHospitalRegService 
{
    @Autowired
    private HospitalRegMapper hospitalRegMapper;

    /**
     * 查询医院挂号预约
     * 
     * @param regId 医院挂号预约主键
     * @return 医院挂号预约
     */
    @Override
    public HospitalReg selectHospitalRegByRegId(Long regId)
    {
        return hospitalRegMapper.selectHospitalRegByRegId(regId);
    }

    /**
     * 查询医院挂号预约列表
     * 
     * @param hospitalReg 医院挂号预约
     * @return 医院挂号预约
     */
    @Override
    public List<HospitalReg> selectHospitalRegList(HospitalReg hospitalReg)
    {
        return hospitalRegMapper.selectHospitalRegList(hospitalReg);
    }

    /**
     * 新增医院挂号预约
     * 
     * @param hospitalReg 医院挂号预约
     * @return 结果
     */
    @Override
    public int insertHospitalReg(HospitalReg hospitalReg)
    {
        hospitalReg.setCreateTime(DateUtils.getNowDate());
        return hospitalRegMapper.insertHospitalReg(hospitalReg);
    }

    /**
     * 修改医院挂号预约
     * 
     * @param hospitalReg 医院挂号预约
     * @return 结果
     */
    @Override
    public int updateHospitalReg(HospitalReg hospitalReg)
    {
        hospitalReg.setUpdateTime(DateUtils.getNowDate());
        return hospitalRegMapper.updateHospitalReg(hospitalReg);
    }

    /**
     * 批量删除医院挂号预约
     * 
     * @param regIds 需要删除的医院挂号预约主键
     * @return 结果
     */
    @Override
    public int deleteHospitalRegByRegIds(Long[] regIds)
    {
        return hospitalRegMapper.deleteHospitalRegByRegIds(regIds);
    }

    /**
     * 删除医院挂号预约信息
     * 
     * @param regId 医院挂号预约主键
     * @return 结果
     */
    @Override
    public int deleteHospitalRegByRegId(Long regId)
    {
        return hospitalRegMapper.deleteHospitalRegByRegId(regId);
    }
}
