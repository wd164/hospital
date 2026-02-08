package com.hospital.reg.controller;

import java.sql.SQLException;
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
import com.hospital.reg.domain.HospitalReg;
import com.hospital.reg.service.IHospitalRegService;
import com.hospital.common.utils.poi.ExcelUtil;
import com.hospital.common.core.page.TableDataInfo;

/**
 * 医院挂号预约Controller
 * 
 * @author hjh
 * @date 2026-02-07
 */
@RestController
@RequestMapping("/reg/reg")
public class HospitalRegController extends BaseController
{
    @Autowired
    private IHospitalRegService hospitalRegService;

    /**
     * 查询医院挂号预约列表
     */
    @PreAuthorize("@ss.hasPermi('reg:reg:list')")
    @GetMapping("/list")
    public TableDataInfo list(HospitalReg hospitalReg)
    {
        startPage();
        List<HospitalReg> list = hospitalRegService.selectHospitalRegList(hospitalReg);
        return getDataTable(list);
    }

    /**
     * 导出医院挂号预约列表
     */
    @PreAuthorize("@ss.hasPermi('reg:reg:export')")
    @Log(title = "医院挂号预约", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HospitalReg hospitalReg)
    {
        List<HospitalReg> list = hospitalRegService.selectHospitalRegList(hospitalReg);
        ExcelUtil<HospitalReg> util = new ExcelUtil<HospitalReg>(HospitalReg.class);
        util.exportExcel(response, list, "医院挂号预约数据");
    }

    /**
     * 获取医院挂号预约详细信息
     */
    @PreAuthorize("@ss.hasPermi('reg:reg:query')")
    @GetMapping(value = "/{regId}")
    public AjaxResult getInfo(@PathVariable("regId") Long regId)
    {
        return success(hospitalRegService.selectHospitalRegByRegId(regId));
    }

    /**
     * 新增医院挂号预约
     */
    @PreAuthorize("@ss.hasPermi('reg:reg:add')")
    @Log(title = "医院挂号预约", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HospitalReg hospitalReg)
    {
        try {
            // 原有插入逻辑
            int result = hospitalRegService.insertHospitalReg(hospitalReg);
            return toAjax(result);
        } catch (Exception e) {
            // 关键：只要是SQL异常且包含#HY000，就提示绑定信息
            Throwable rootCause = e.getCause();
            if (rootCause instanceof SQLException && e.getMessage().contains("#HY000")) {
                // 直接返回自定义提示（错误码600，前端识别）
                return AjaxResult.error(600, "挂号失败：您尚未绑定个人患者信息，请先完成信息绑定后再尝试！");
            }
            // 其他异常返回通用错误
            return AjaxResult.error(500, "挂号失败：" + e.getMessage());
        }
    }

    /**
     * 修改医院挂号预约
     */
    @PreAuthorize("@ss.hasPermi('reg:reg:edit')")
    @Log(title = "医院挂号预约", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HospitalReg hospitalReg)
    {
        return toAjax(hospitalRegService.updateHospitalReg(hospitalReg));
    }

    /**
     * 删除医院挂号预约
     */
    @PreAuthorize("@ss.hasPermi('reg:reg:remove')")
    @Log(title = "医院挂号预约", businessType = BusinessType.DELETE)
	@DeleteMapping("/{regIds}")
    public AjaxResult remove(@PathVariable Long[] regIds)
    {
        return toAjax(hospitalRegService.deleteHospitalRegByRegIds(regIds));
    }
}
