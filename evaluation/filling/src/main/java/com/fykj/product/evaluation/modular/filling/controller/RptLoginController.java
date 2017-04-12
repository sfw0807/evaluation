package com.fykj.product.evaluation.modular.filling.controller;

import com.fykj.platform.kernel._c.ServerSessionHolder;
import com.fykj.platform.kernel._c.model.InvokeResult;
import com.fykj.platform.kernel._c.model.SessionUser;
import com.fykj.platform.kernel.parameter.annotation.ParamValidation4Controller;
import com.fykj.platform.util.Copy;
import com.fykj.product.evaluation.common.constant.RptCommonConstants;
import com.fykj.product.evaluation.modular.filling.model.FillProject;
import com.fykj.product.evaluation.modular.filling.service.FillProjectService;
import com.fykj.product.evaluation.modular.filling.service.RptPubAnswerService;
import com.fykj.product.evaluation.modular.filling.vo.FillAccountProjectsVO;
import com.fykj.product.evaluation.modular.filling.vo.RptLoginVO;
import com.fykj.sample.login.service.LoginService;
import com.fykj.sample.sysuser.model.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Erik_Yim on 2017/4/10.
 */
@Controller
@RequestMapping("/rpt")
@ParamValidation4Controller
public class RptLoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    FillProjectService fillProjectService;

    @RequestMapping("/userLogin")
    @ResponseBody
    public InvokeResult userLogin(RptLoginVO inVO, HttpServletRequest request) throws Exception {

        if (StringUtils.equals(RptCommonConstants.LOGIN_TYPE_REAL_ACCOUNT,inVO.getLoginType())) {
            SysUser sysUser = Copy.simpleCopy(inVO, SysUser.class);
            loginService.userLogin(sysUser);
        } else if (StringUtils.equals(RptCommonConstants.LOGIN_TYPE_UNICODE,inVO.getLoginType()) ||
                StringUtils.equals(RptCommonConstants.LOGIN_TYPE_TMP_ACCOUNT,inVO.getLoginType())) {
            setMockSessionUser(inVO);
        }


        SessionUser sessionUser= ServerSessionHolder.getSessionUser();
        Map<String, Object> maps=new HashMap<>();
        maps.put("userInfo", sessionUser);

        FillAccountProjectsVO projects = fillProjectService.findByAccount(inVO.getUserAccount());

        maps.put("projectInfos", projects); //只有实名登录会产生多条项目信息，其他登录只有一条

        return InvokeResult.success(maps);

    }


    private void setMockSessionUser(RptLoginVO inVO) {
        String userId = inVO.getUserAccount();
        // 用户信息
        SessionUser user = new SessionUser();
        user.setId(userId);
        user.setUserName("RPT_LOGIN_USER_NAME");
        user.setNatureName("RPT_LOGIN_NATURE_NAME");
        ServerSessionHolder.setSessionUser(user);
    }


}
