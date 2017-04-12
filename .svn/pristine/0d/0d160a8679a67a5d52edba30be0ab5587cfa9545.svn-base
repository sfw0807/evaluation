package com.fykj.product.evaluation.modular.evaluating.scoreitem.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fykj.platform.kernel._c.ServerSessionHolder;
import com.fykj.platform.kernel._c.model.InvokeResult;
import com.fykj.platform.kernel._c.model.JPage;
import com.fykj.platform.kernel._c.model.JPageUtil;
import com.fykj.platform.kernel._c.model.SimplePageRequest;
import com.fykj.platform.kernel.parameter.annotation.ParamValidation4Controller;
import com.fykj.platform.util.Copy;
import com.fykj.platform.web.model.SimplePageRequestVO;
import com.fykj.product.evaluation.api.filling.model.RptQuestionOption;
import com.fykj.product.evaluation.api.filling.vo.ReportQuestionInVO;
import com.fykj.product.evaluation.api.filling.vo.ReportQuestionOutVO;
import com.fykj.product.evaluation.api.scoreitem.model.IntervalScore;
import com.fykj.product.evaluation.api.scoreitem.model.ScoreItem;
import com.fykj.product.evaluation.api.scoreitem.service.ScoreItemServiceApi;
import com.fykj.product.evaluation.api.scoreitem.vo.ScoreItemCriteriaInVO;
import com.fykj.product.evaluation.api.scoreitem.vo.ScoreItemInVO;
import com.fykj.product.evaluation.api.scoreitem.vo.ScoreItemOutVO;
import com.fykj.product.evaluation.common.constant.DataCollectionType;
import com.fykj.product.evaluation.common.constant.FillType;
import com.fykj.product.evaluation.common.constant.PreliminaryType;
import com.fykj.product.evaluation.common.constant.RptCommonConstants;
import com.fykj.product.evaluation.common.constant.SplitCharacter;
@Controller
@RequestMapping("/scoreItem/")
@ParamValidation4Controller
public class ScoreItemController {

	@Autowired
	private ScoreItemServiceApi scoreItemService ;

	/**
	 *  saveScoreItem:(新增得分点).
	 *  @return_type:InvokeResult
	 *  @author zhangtian
	 *  @param scoreItemInVO
	 *  @return
	 *  @throws Exception
	 */
	@RequestMapping(value = "saveScoreItem", method = RequestMethod.POST)
	@ResponseBody
	public InvokeResult saveScoreItem(ScoreItemInVO scoreItemInVO) throws Exception {
		if(StringUtils.equals(scoreItemInVO.getPreliminaryType(), PreliminaryType.SYSTEM.getKey())) {
			scoreItemInVO.setSelfPerson("");
			scoreItemInVO.setPreliminaryExpect("");
		}
		ScoreItem scoreItem = Copy.simpleCopy(scoreItemInVO, ScoreItem.class) ;
		scoreItemService.saveScoreItem(scoreItem);
		return InvokeResult.success(scoreItem.getId()) ;
	}

	/**
	 *  updateScoreItem:(编辑得分点).
	 *  @return_type:InvokeResult
	 *  @author zhangtian
	 *  @param scoreItemInVO
	 *  @return
	 *  @throws Exception
	 */
	@RequestMapping(value = "updateScoreItem", method = RequestMethod.POST)
	@ResponseBody
	public InvokeResult updateScoreItem(ScoreItemInVO scoreItemInVO) throws Exception {
		ScoreItem scoreItem = Copy.simpleCopy(scoreItemInVO, ScoreItem.class) ;
		scoreItemService.updateScoreItem(scoreItem);
		return InvokeResult.success(scoreItem.getId()) ;
	}
	
	/**
	 *  deleteScoreItemById:(根据ID删除得分点). 
	 *  @return_type:InvokeResult
	 *  @author zhangtian  
	 *  @param ids
	 *  @return
	 *  @throws Exception
	 */
	@RequestMapping(value = "deleteScoreItemById", method = RequestMethod.POST)
	@ResponseBody
	public InvokeResult deleteScoreItemById(String ids) throws Exception {
		String[] arr = ids.split(SplitCharacter.SPLIT_DOUBLE.key) ;
		scoreItemService.deleteScoreItems(arr);
		return InvokeResult.success(true) ;
	}
	
	/**
	 *  getScoreItemByPage:(分页条件查询得分点列表). 
	 *  @return_type:InvokeResult
	 *  @author zhangtian  
	 *  @param ScoreItemCriteriaInVo
	 *  @param simplePageRequestVO
	 *  @return
	 *  @throws Exception
	 */
	@RequestMapping(value = "getScoreItemByPage", method = RequestMethod.POST)
	@ResponseBody
	public InvokeResult getScoreItemByPage(ScoreItemCriteriaInVO scoreItemCriteriaInVo, 
			SimplePageRequestVO simplePageRequestVO) throws Exception {
		JPage<ScoreItem> page = scoreItemService.getScoreItems(scoreItemCriteriaInVo, 
				new SimplePageRequest(simplePageRequestVO.getPage(), simplePageRequestVO.getSize())) ;
		List<ScoreItem> content = page.getContent() ;
		List<ScoreItemOutVO> outContent = new ArrayList<ScoreItemOutVO>() ;
		
		for(ScoreItem scoreItem : content) {
			ScoreItemOutVO scoreItemOutVO = Copy.simpleCopy(scoreItem, ScoreItemOutVO.class) ;
			scoreItemOutVO.setParentQuota(scoreItemOutVO.getParentQuota());
			scoreItemOutVO.setDataCollectionTypeName(DataCollectionType.getValue(scoreItemOutVO.getDataCollectionType()));
			scoreItemOutVO.setPreliminaryTypeName(PreliminaryType.getValue(scoreItemOutVO.getPreliminaryType()));
			outContent.add(scoreItemOutVO) ;
		}
		JPageUtil.replaceConent(page, outContent);
		return InvokeResult.success(page) ;
	}
	
	/**
	 *  isScoreItemExist:(根据名称校验得分点是否已经存在). 
	 *  @return_type:InvokeResult
	 *  @author zhangtian  
	 *  @param ScoreItemName
	 *  @return
	 *  @throws Exception
	 */
	@RequestMapping(value = "isScoreItemExist", method = RequestMethod.POST)
	@ResponseBody
	public InvokeResult isScoreItemExist(ScoreItemInVO scoreItemInVO) throws Exception {
		boolean flag = scoreItemService.isScoreItemExist(scoreItemInVO) ;
		return InvokeResult.success(flag) ;
	}
	
	/**
	 *  getScoreItemById:(根据ID查询得分项详情). 
	 *  @return_type:InvokeResult
	 *  @author zhangtian  
	 *  @return
	 *  @throws Exception
	 */
	@RequestMapping(value = "getScoreItemById", method = RequestMethod.POST)
	@ResponseBody
	public InvokeResult getScoreItemById(String id) throws Exception {
		ScoreItem scoreItem = scoreItemService.getScoreItemById(id) ;
		ScoreItemOutVO scoreItemOutVO = null ;
		if(scoreItem != null) {
			scoreItemOutVO = Copy.simpleCopy(scoreItem, ScoreItemOutVO.class) ;
			scoreItemOutVO.setDataCollectionTypeName(DataCollectionType.getValue(scoreItemOutVO.getDataCollectionType()));
			scoreItemOutVO.setPreliminaryTypeName(PreliminaryType.getValue(scoreItemOutVO.getPreliminaryType()));
		}
		return InvokeResult.success(scoreItemOutVO) ;
	}
	
	/**
	 *  isLastItem:(新增得分项时判断选择的子标项是否是最末级别的子标项). 
	 *  @return_type:InvokeResult
	 *  @author zhangtian  
	 *  @param parentQuotaId
	 *  @return
	 *  @throws Exception
	 */
	@RequestMapping(value = "isLastItem", method = RequestMethod.POST)
	@ResponseBody
	public InvokeResult isLastItem (@RequestParam(name = "parentQuotaId") String parentQuotaId) throws Exception{
		boolean flag = scoreItemService.isLastItem(parentQuotaId) ;
		return InvokeResult.success(flag) ;
	}
	
	/**
	 *  getScoreItemByParentQuota:(根据上级指标项获取得分项列表). 
	 *  @return_type:InvokeResult
	 *  @author zhangtian  
	 *  @param parentQuota
	 *  @return
	 *  @throws Exception
	 */
	@RequestMapping(value = "getScoreItemByParentQuota", method = RequestMethod.POST)
	@ResponseBody
	public List<ScoreItemOutVO> getScoreItemByParentQuota(ScoreItemCriteriaInVO criteriaInVO) throws Exception {
		String userId = ServerSessionHolder.getSessionUser().getId() ;
		List<ScoreItemOutVO> result = scoreItemService.getScoreItemByParentQuota(criteriaInVO.getParentQuota(), userId, criteriaInVO.getTargetId()) ;
		return result ;
	}
	
	// ***************************** 填报API调用 ***************************
	/**
	 *  getRptQuestion:(列表显示得分点下所有的得分项). 
	 *  @return_type:InvokeResult
	 *  @author zhangtian  
	 *  @param scoreItemId
	 *  @return
	 *  @throws Exception
	 */
	@RequestMapping(value = "getRptQuestion", method = RequestMethod.POST)
	@ResponseBody
	public InvokeResult getRptQuestion(@RequestParam String scoreItemId) throws Exception{
  		List<ReportQuestionOutVO> rs = scoreItemService.getRptQuestionByScoreItemId(scoreItemId, RptCommonConstants.QUERY_TYPE_BRIEF) ;
		for(ReportQuestionOutVO vo : rs) {
			vo.setQuestionType(FillType.getValue(vo.getQuestionType()));
		}
  		return InvokeResult.success(JPageUtil.wrap(rs)) ;
	}
	
	/**
	 *  deleteQuestionById:(根据选项ID删除记录). 
	 *  @return_type:InvokeResult
	 *  @author zhangtian  
	 *  @param queId
	 *  @return
	 *  @throws Exception
	 */
	@RequestMapping(value = "deleteQuestionById", method = RequestMethod.POST)
	@ResponseBody
	public InvokeResult deleteQuestionById(@RequestParam String quesIds) throws Exception {
		String[] arr = quesIds.split(SplitCharacter.SPLIT_DOUBLE.key) ;
		scoreItemService.deleteQuestionByIds(arr);
		return InvokeResult.success(true) ;
	}
	
	/**
	 *  saveScoreItemFills:(保存填报项信息). 
	 *  @return_type:InvokeResult
	 *  @author zhangtian  
	 *  @return
	 *  @throws Exception
	 */
	@RequestMapping(value = "saveOrUpdateScoreItemFills", method = RequestMethod.POST)
	@ResponseBody
	public InvokeResult saveScoreItemFills(String paramsJson) throws Exception {
		JSONObject jsonObject = JSONObject.parseObject(paramsJson);
		String queId = jsonObject.getString("quesId") ;
		String fillType = jsonObject.getString("fillType") ;
		String scoreItemId = jsonObject.getString("scoreItemId") ;
		String projectId = jsonObject.getString("projectId") ;
		ReportQuestionInVO reportQuestionInVO = new ReportQuestionInVO() ;
		reportQuestionInVO.setQueId(queId);
		reportQuestionInVO.setScoreItemId(scoreItemId);
		reportQuestionInVO.setQuestionType(fillType);
		reportQuestionInVO.setQuestionCode(jsonObject.getIntValue("scoreItemFillSerialNumber"));
		reportQuestionInVO.setQuestionContent(jsonObject.getString("scoreItemFillDesc"));
		List<IntervalScore> intervals = new ArrayList<IntervalScore>() ;
		// 单选或多选
		if(StringUtils.equals(fillType, FillType.FILL_SINGLE.getKey())
				|| StringUtils.equals(fillType, FillType.FILL_MULTI.getKey())) {
			List<RptQuestionOption> options = new ArrayList<RptQuestionOption>() ;
			JSONArray nameArr = null ;
			JSONArray contentArr = null ;
			JSONArray scoreArr = null ;

			try {
				nameArr = jsonObject.getJSONArray("optionsName") ;
				contentArr = jsonObject.getJSONArray("optionsContent") ;
				scoreArr = jsonObject.getJSONArray("optionsScore") ;
			}catch (JSONException  e) {
				String nameStr = jsonObject.getString("optionsName");
				String contentStr = jsonObject.getString("optionsContent") ;
				String scoreStr = jsonObject.getString("optionsScore");

				nameArr = new JSONArray() ;
				nameArr.add(nameStr) ;
				contentArr = new JSONArray() ;
				contentArr.add(contentStr) ;
				scoreArr = new JSONArray() ;
				scoreArr.add(scoreStr) ;
			}

			for(int i = 0; i < nameArr.size(); i++){
				RptQuestionOption opt = new RptQuestionOption() ;
				opt.setOptName(nameArr.getString(i));
				opt.setOptContent(contentArr.getString(i)) ;
				opt.setOptScore(scoreArr.getFloatValue(i));
				options.add(opt) ;
			}
			reportQuestionInVO.setOptions(options);
		} else if(StringUtils.equals(fillType, FillType.FILL_ANSWER.getKey())) {// 问答题
			// 问答记分
			if(StringUtils.equals(jsonObject.getString("quesGetScore"), FillType.FillAnswerGetScore.GET_SCORE.getKey())) {
				// 保存区间记分
                JSONArray upperArray = null ;
                JSONArray lowerArray = null ;
                JSONArray scores = null ;
                try {
                    upperArray = jsonObject.getJSONArray("upperSection") ;
                    lowerArray = jsonObject.getJSONArray("lowerSection") ;
                    scores = jsonObject.getJSONArray("sectionScore") ;
                }catch (JSONException | ClassCastException e){
                    BigDecimal upperStr = jsonObject.getBigDecimal("upperSection") ;
					BigDecimal lowerStr = jsonObject.getBigDecimal("lowerSection") ;
					BigDecimal scoresStr = jsonObject.getBigDecimal("sectionScore") ;
                        upperArray = new JSONArray() ;
                    upperArray.add(upperStr) ;
                    lowerArray = new JSONArray() ;
                    lowerArray.add(lowerStr) ;
                    scores = new JSONArray() ;
                    scores.add(scoresStr) ;
                }
				for(int i = 0; i < upperArray.size(); i++) {
					IntervalScore intervalScore = new IntervalScore() ;
					intervalScore.setUpperLimit(upperArray.getBigDecimal(i));
					intervalScore.setLowLimit(lowerArray.getBigDecimal(i));
					intervalScore.setScore(scores.getBigDecimal(i));
					intervalScore.setScoreItemId(scoreItemId);
					
					intervals.add(intervalScore) ;
				}
			} else {//问答不计分
				// 问答不计分什么都不用做了
			}
		}else {// 附件
			// 附件类型什么都不用做了
		}
		
		String rptId = scoreItemService.getPubReportByProjectAndType(projectId, RptCommonConstants.RPT_TYPE_RPT) ;
		String quesId = scoreItemService.saveOrUpdateScoreItemFills(reportQuestionInVO, intervals, rptId, projectId) ;
		return InvokeResult.success(quesId) ;
	}

	/**
	 * 根据问题ID查询问题，数据回显使用
	 * @param quesId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getQuestionById", method = RequestMethod.POST)
	@ResponseBody
	public InvokeResult getQuestionById(@RequestParam String quesId) throws Exception{
		ReportQuestionOutVO reportQuestionOutVO = scoreItemService.getQuestionById(quesId) ;
		return  InvokeResult.success(reportQuestionOutVO) ;
	}
}
