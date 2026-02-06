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

    /**
     * 查询医院患者列表
     */
    @PreAuthorize("@ss.hasPermi('patient:patient:list')")
    @GetMapping("/list")
    public TableDataInfo list(HospitalPatient hospitalPatient)
    {
        startPage();
        List<HospitalPatient> list = hospitalPatientService.selectHospitalPatientList(hospitalPatient);
        return getDataTable(list);
    }

    /**
     * 导出医院患者列表
     */
    @PreAuthorize("@ss.hasPermi('patient:patient:export')")
    @Log(title = "医院患者", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HospitalPatient hospitalPatient)
    {
        List<HospitalPatient> list = hospitalPatientService.selectHospitalPatientList(hospitalPatient);
        ExcelUtil<HospitalPatient> util = new ExcelUtil<HospitalPatient>(HospitalPatient.class);
        util.exportExcel(response, list, "医院患者数据");
    }

    /**
     * 获取医院患者详细信息
     */
    @PreAuthorize("@ss.hasPermi('patient:patient:query')")
    @GetMapping(value = "/{patientId}")
    public AjaxResult getInfo(@PathVariable("patientId") Long patientId)
    {
        return success(hospitalPatientService.selectHospitalPatientByPatientId(patientId));
    }

    /**
     * 新增医院患者
     */
    @PreAuthorize("@ss.hasPermi('patient:patient:add')")
    @Log(title = "医院患者", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HospitalPatient hospitalPatient)
    {
        return toAjax(hospitalPatientService.insertHospitalPatient(hospitalPatient));
    }

    /**
     * 修改医院患者
     */
    @PreAuthorize("@ss.hasPermi('patient:patient:edit')")
    @Log(title = "医院患者", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HospitalPatient hospitalPatient)
    {
        return toAjax(hospitalPatientService.updateHospitalPatient(hospitalPatient));
    }

    /**
     * 删除医院患者
     */
    @PreAuthorize("@ss.hasPermi('patient:patient:remove')")
    @Log(title = "医院患者", businessType = BusinessType.DELETE)
	@DeleteMapping("/{patientIds}")
    public AjaxResult remove(@PathVariable Long[] patientIds)
    {
        return toAjax(hospitalPatientService.deleteHospitalPatientByPatientIds(patientIds));
    }
}
