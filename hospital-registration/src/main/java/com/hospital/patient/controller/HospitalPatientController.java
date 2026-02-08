package com.hospital.patient.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.hospital.patient.domain.HospitalPatient;
import com.hospital.patient.service.IHospitalPatientService;
import com.hospital.common.utils.poi.ExcelUtil;
import com.hospital.common.core.page.TableDataInfo;
import com.hospital.common.utils.SecurityUtils; // 新增：获取当前登录用户

/**
 * 医院患者Controller
 *
 * @author hjh
 * @date 2026-02-06
 */
@RestController
@RequestMapping("/patient/patient")
public class HospitalPatientController extends BaseController
{
    @Autowired
    private IHospitalPatientService hospitalPatientService;

    // ====================== 原有管理员接口（保留不变） ======================
    @PreAuthorize("@ss.hasPermi('patient:patient:list')")
    @GetMapping("/list")
    public TableDataInfo list(HospitalPatient hospitalPatient)
    {
        startPage();
        List<HospitalPatient> list = hospitalPatientService.selectHospitalPatientList(hospitalPatient);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('patient:patient:export')")
    @Log(title = "医院患者", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HospitalPatient hospitalPatient)
    {
        List<HospitalPatient> list = hospitalPatientService.selectHospitalPatientList(hospitalPatient);
        ExcelUtil<HospitalPatient> util = new ExcelUtil<HospitalPatient>(HospitalPatient.class);
        util.exportExcel(response, list, "医院患者数据");
    }

    @PreAuthorize("@ss.hasPermi('patient:patient:query')")
    @GetMapping(value = "/{patientId}")
    public AjaxResult getInfo(@PathVariable("patientId") Long patientId)
    {
        return success(hospitalPatientService.selectHospitalPatientByPatientId(patientId));
    }

    @PreAuthorize("@ss.hasPermi('patient:patient:add')")
    @Log(title = "医院患者", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HospitalPatient hospitalPatient)
    {
        return toAjax(hospitalPatientService.insertHospitalPatient(hospitalPatient));
    }

    @PreAuthorize("@ss.hasPermi('patient:patient:edit')")
    @Log(title = "医院患者", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HospitalPatient hospitalPatient)
    {
        return toAjax(hospitalPatientService.updateHospitalPatient(hospitalPatient));
    }

    @PreAuthorize("@ss.hasPermi('patient:patient:remove')")
    @Log(title = "医院患者", businessType = BusinessType.DELETE)
    @DeleteMapping("/{patientIds}")
    public AjaxResult remove(@PathVariable Long[] patientIds)
    {
        return toAjax(hospitalPatientService.deleteHospitalPatientByPatientIds(patientIds));
    }

    // ====================== 新增：患者自操作接口（无管理员权限） ======================
    /**
     * 患者查询自己的信息（无权限注解，仅能查自己）
     */
    @GetMapping("/myInfo")
    public AjaxResult getMyInfo()
    {
        // 1. 获取当前登录用户ID（patient_id = user_id）
        Long userId = SecurityUtils.getUserId();
        // 2. 查询自己的患者信息
        HospitalPatient patient = hospitalPatientService.selectHospitalPatientByPatientId(userId);
        return success(patient);
    }

    /**
     * 患者新增自己的信息（无权限注解，强制绑定当前用户ID）
     */
    @Log(title = "患者自填信息", businessType = BusinessType.INSERT)
    @PostMapping("/myAdd")
    public AjaxResult addMyInfo(@RequestBody HospitalPatient hospitalPatient)
    {
        // 1. 强制设置patientId为当前登录用户ID，防止篡改
        Long userId = SecurityUtils.getUserId();
        hospitalPatient.setPatientId(userId);
        // 2. 新增信息
        return toAjax(hospitalPatientService.insertHospitalPatient(hospitalPatient));
    }

    /**
     * 患者修改自己的信息（无权限注解，仅能改自己）
     */
    @Log(title = "患者修改信息", businessType = BusinessType.UPDATE)
    @PutMapping("/myEdit")
    public AjaxResult editMyInfo(@RequestBody HospitalPatient hospitalPatient)
    {
        // 1. 获取当前登录用户ID
        Long userId = SecurityUtils.getUserId();
        // 2. 强制限定修改自己的信息（防止传参篡改patientId）
        hospitalPatient.setPatientId(userId);
        // 3. 修改信息
        return toAjax(hospitalPatientService.updateHospitalPatient(hospitalPatient));
    }
}