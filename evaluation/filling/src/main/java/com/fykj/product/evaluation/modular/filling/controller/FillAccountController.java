package com.fykj.product.evaluation.modular.filling.controller;

import com.fykj.common.excel.ExcelUtils;
import com.fykj.common.excel.constants.ExcelType;
import com.fykj.platform.kernel._c.model.InvokeResult;
import com.fykj.platform.kernel.parameter.annotation.ParamValidation4Controller;
import com.fykj.product.evaluation.modular.filling.service.FillAccountService;
import com.fykj.product.evaluation.modular.filling.service.FillProjectEvalTargetService;
import com.fykj.product.evaluation.modular.filling.vo.FillAccountExpVO;
import com.fykj.product.evaluation.modular.filling.vo.FillEvalTargetAccountOutVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

/**
 * Created by liwang on 2017/4/6.
 */
@Controller
@RequestMapping("/fillAccount")
@ParamValidation4Controller
public class FillAccountController {

    @Autowired
    FillAccountService fillAccountService;
    @Autowired
    FillProjectEvalTargetService fillProjectEvalTargetService;
    @ResponseBody
    @RequestMapping("/createAccount")
    public InvokeResult createAccount(String fillProjectId){
        fillAccountService.createFillAcc(fillProjectId);
        List<FillEvalTargetAccountOutVO> list = fillProjectEvalTargetService.getAccountOutByProject(fillProjectId);
        return InvokeResult.success(list);
    }

    /**
     * 根据projectId查询账号信息
     * 返回每个参与对象账号数
     * @param fillProjectId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCountByProjectId")
    public InvokeResult getCountByProjectId(String fillProjectId){
        List<FillEvalTargetAccountOutVO> list = fillProjectEvalTargetService.getAccountOutByProject(fillProjectId);
        return InvokeResult.success(list);
    }
    // 导出excel方法
    @RequestMapping("exportAccount")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, String fillProjectId)
    {
        List<FillAccountExpVO> list = fillAccountService.getByProject(fillProjectId);
        Workbook workbook = ExcelUtils.exportExcelData(list, FillAccountExpVO.class, ExcelType.OTHER, true, 2500) ;
        ExcelUtils.exportHttpFile(request, response, fillProjectId, workbook);
    }

    @ResponseBody
    @RequestMapping("/bindEvalTarget")
    public InvokeResult bindEvalTarget(String evalTargetIds, String fillProjectId){
        fillAccountService.bindEvalTarget(evalTargetIds, fillProjectId);
        return InvokeResult.success(true);
    }
}
