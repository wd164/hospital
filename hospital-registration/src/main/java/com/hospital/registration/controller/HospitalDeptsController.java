package com.hospital.registration.controller;

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
import com.hospital.registration.domain.HospitalDepts;
import com.hospital.registration.service.IHospitalDeptsService;
import com.hospital.common.utils.poi.ExcelUtil;
import com.hospital.common.core.page.TableDataInfo;

/**
 * 医院科室Controller
 * 
 * @author hjh
 * @date 2026-02-06
 */
@RestController
@RequestMapping("/registration/depts")
public class HospitalDeptsController extends BaseController
{
    @Autowired
    private IHospitalDeptsService hospitalDeptsService;

    /**
     * 查询医院科室列表
     */
    @PreAuthorize("@ss.hasPermi('registration:depts:list')")
    @GetMapping("/list")
    public TableDataInfo list(HospitalDepts hospitalDepts)
    {
        startPage();
        List<HospitalDepts> list = hospitalDeptsService.selectHospitalDeptsList(hospitalDepts);
        return getDataTable(list);
    }

    /**
     * 导出医院科室列表
     */
    @PreAuthorize("@ss.hasPermi('registration:depts:export')")
    @Log(title = "医院科室", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HospitalDepts hospitalDepts)
    {
        List<HospitalDepts> list = hospitalDeptsService.selectHospitalDeptsList(hospitalDepts);
        ExcelUtil<HospitalDepts> util = new ExcelUtil<HospitalDepts>(HospitalDepts.class);
        util.exportExcel(response, list, "医院科室数据");
    }

    /**
     * 获取医院科室详细信息
     */
    @PreAuthorize("@ss.hasPermi('registration:depts:query')")
    @GetMapping(value = "/{deptId}")
    public AjaxResult getInfo(@PathVariable("deptId") Long deptId)
    {
        return success(hospitalDeptsService.selectHospitalDeptsByDeptId(deptId));
    }

    /**
     * 新增医院科室
     */
    @PreAuthorize("@ss.hasPermi('registration:depts:add')")
    @Log(title = "医院科室", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HospitalDepts hospitalDepts)
    {
        return toAjax(hospitalDeptsService.insertHospitalDepts(hospitalDepts));
    }

    /**
     * 修改医院科室
     */
    @PreAuthorize("@ss.hasPermi('registration:depts:edit')")
    @Log(title = "医院科室", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HospitalDepts hospitalDepts)
    {
        return toAjax(hospitalDeptsService.updateHospitalDepts(hospitalDepts));
    }

    /**
     * 删除医院科室
     */
    @PreAuthorize("@ss.hasPermi('registration:depts:remove')")
    @Log(title = "医院科室", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deptIds}")
    public AjaxResult remove(@PathVariable Long[] deptIds)
    {
        return toAjax(hospitalDeptsService.deleteHospitalDeptsByDeptIds(deptIds));
    }
}
