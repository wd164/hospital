package com.hospital.doctor.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hospital.common.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hospital.common.annotation.Log;
import com.hospital.common.core.controller.BaseController;
import com.hospital.common.core.domain.AjaxResult;
import com.hospital.common.enums.BusinessType;
import com.hospital.doctor.domain.HospitalDoctor;
import com.hospital.doctor.service.IHospitalDoctorService;
import com.hospital.common.utils.poi.ExcelUtil;
import com.hospital.common.core.page.TableDataInfo;

/**
 * 医院医生Controller
 * 
 * @author hjh
 * @date 2026-02-07
 */
@RestController
@RequestMapping("/doctor/doctor")
public class HospitalDoctorController extends BaseController
{
    @Autowired
    private IHospitalDoctorService hospitalDoctorService;

    /**
     * 查询医院医生列表
     */
    @PreAuthorize("@ss.hasPermi('doctor:doctor:list')")
    @GetMapping("/list")
    public TableDataInfo list(HospitalDoctor hospitalDoctor)
    {
        startPage();
        List<HospitalDoctor> list = hospitalDoctorService.selectHospitalDoctorList(hospitalDoctor);
        return getDataTable(list);
    }

    /**
     * 导出医院医生列表
     */
    @PreAuthorize("@ss.hasPermi('doctor:doctor:export')")
    @Log(title = "医院医生", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HospitalDoctor hospitalDoctor)
    {
        List<HospitalDoctor> list = hospitalDoctorService.selectHospitalDoctorList(hospitalDoctor);
        ExcelUtil<HospitalDoctor> util = new ExcelUtil<HospitalDoctor>(HospitalDoctor.class);
        util.exportExcel(response, list, "医院医生数据");
    }

    /**
     * 获取医院医生详细信息
     */
    @PreAuthorize("@ss.hasPermi('doctor:doctor:query')")
    @GetMapping(value = "/{doctorId}")
    public AjaxResult getInfo(@PathVariable("doctorId") Long doctorId)
    {
        return success(hospitalDoctorService.selectHospitalDoctorByDoctorId(doctorId));
    }

    /**
     * 新增医院医生
     */
    @PreAuthorize("@ss.hasPermi('doctor:doctor:add')")
    @Log(title = "医院医生", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HospitalDoctor hospitalDoctor)
    {
        return toAjax(hospitalDoctorService.insertHospitalDoctor(hospitalDoctor));
    }

    /**
     * 修改医院医生
     */
    @PreAuthorize("@ss.hasPermi('doctor:doctor:edit')")
    @Log(title = "医院医生", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HospitalDoctor hospitalDoctor)
    {
        return toAjax(hospitalDoctorService.updateHospitalDoctor(hospitalDoctor));
    }

    /**
     * 删除医院医生
     */
    @PreAuthorize("@ss.hasPermi('doctor:doctor:remove')")
    @Log(title = "医院医生", businessType = BusinessType.DELETE)
	@DeleteMapping("/{doctorIds}")
    public AjaxResult remove(@PathVariable Long[] doctorIds)
    {
        return toAjax(hospitalDoctorService.deleteHospitalDoctorByDoctorIds(doctorIds));
    }

    // ====================== 新增：医生自操作接口（无管理员权限，完全复刻患者接口） ======================
    /**
     * 医生查询自己的信息（无权限注解，仅能查自己）
     */
    @GetMapping("/myInfo")
    public AjaxResult getMyInfo()
    {
        // 1. 获取当前登录用户ID（doctor_id = user_id）
        Long userId = SecurityUtils.getUserId();
        // 2. 查询自己的医生信息
        HospitalDoctor doctor = hospitalDoctorService.selectHospitalDoctorByDoctorId(userId);
        return success(doctor);
    }

    /**
     * 医生新增自己的信息（无权限注解，强制绑定当前用户ID）
     */
    @Log(title = "医生自填信息", businessType = BusinessType.INSERT)
    @PostMapping("/myAdd")
    public AjaxResult addMyInfo(@RequestBody HospitalDoctor hospitalDoctor)
    {
        // 1. 强制设置doctorId为当前登录用户ID，防止篡改
        Long userId = SecurityUtils.getUserId();
        hospitalDoctor.setDoctorId(userId);
        // 2. 新增信息（仅这两步，和患者接口完全一致）
        return toAjax(hospitalDoctorService.insertHospitalDoctor(hospitalDoctor));
    }

    /**
     * 医生修改自己的信息（无权限注解，仅能改自己）
     */
    @Log(title = "医生修改信息", businessType = BusinessType.UPDATE)
    @PutMapping("/myEdit")
    public AjaxResult editMyInfo(@RequestBody HospitalDoctor hospitalDoctor)
    {
        // 1. 获取当前登录用户ID
        Long userId = SecurityUtils.getUserId();
        // 2. 强制限定修改自己的信息（防止传参篡改doctorId）
        hospitalDoctor.setDoctorId(userId);
        // 3. 修改信息（仅这三步，和患者接口完全一致）
        return toAjax(hospitalDoctorService.updateHospitalDoctor(hospitalDoctor));
    }

    /**
     * 医生查看自己的患者列表（无权限注解，仅能查自己的患者）
     */
    @GetMapping("/myPatient")
    public AjaxResult getMyPatient()
    {
        // 1. 获取当前登录用户ID（doctor_id = user_id）
        Long userId = SecurityUtils.getUserId();
        // 2. 查询自己的患者列表
        return success(hospitalDoctorService.selectMyPatientList(userId));
    }
}
