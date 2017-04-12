package com.fykj.product.evaluation.api.scoreitem.service;

import com.fykj.platform.kernel._c.model.JPage;
import com.fykj.platform.kernel._c.model.SimplePageRequest;
import com.fykj.product.evaluation.api.filling.vo.ReportQuestionInVO;
import com.fykj.product.evaluation.api.filling.vo.ReportQuestionOutVO;
import com.fykj.product.evaluation.api.scoreitem.model.IntervalScore;
import com.fykj.product.evaluation.api.scoreitem.model.ScoreItem;
import com.fykj.product.evaluation.api.scoreitem.vo.ScoreItemCriteriaInVO;
import com.fykj.product.evaluation.api.scoreitem.vo.ScoreItemInVO;
import com.fykj.product.evaluation.api.scoreitem.vo.ScoreItemOutVO;

import java.util.List;

public interface ScoreItemServiceApi {
	// 保存得分点
	public void saveScoreItem(ScoreItem scoreItem) ;
	// 更新得分点
	public void updateScoreItem(ScoreItem scoreItem) ;
	// 删除得分点
	public void deleteScoreItem(ScoreItem scoreItem) ;
	// 根据ID删除得分点
	public void deleteScoreItemById(String id) ;
	// 批量删除得分点
	public void deleteScoreItems(String[] ids) ;
	// 根据ID查询得分点详情
	public ScoreItem getScoreItemById(String id) ;
	// 分页条件查询得分点
	public JPage<ScoreItem> getScoreItems(ScoreItemCriteriaInVO scoreItemCriteriaInVO, SimplePageRequest simplePageRequest) ;
	// 根据指标项集合查询得分项
	public List<ScoreItem> getScoreItemByItemMeasureIds(List<String> itemMeasureIds) ;
	// 新增得分点时判断得分点是否已经存在
	public boolean isScoreItemExist(ScoreItemInVO scoreItemInVO) ;
	
	// 根据主键ID删除填报项
	public void deleteQuestionByIds(String[] arr) ;
	// 根据得分点ID查询填报项
	public List<ReportQuestionOutVO> getRptQuestionByScoreItemId(String scoreItemId ,String queryType) ;
	// 查询以及保存填报详情
	public String getPubReportByProjectAndType(String projectId, int rptType);
	// 保存填报项信息
	public String saveOrUpdateScoreItemFills(ReportQuestionInVO reportQuestionInVO,List<IntervalScore> intervalScores, String rptId, String projectId);
	// 判断子标项是否是叶子节点
	public boolean isLastItem(String parentQuotaId);
	// 根据上级指标项获取得分项列表
	public List<ScoreItemOutVO> getScoreItemByParentQuota(String parentQuota, final String userId, String targetId);

    public ReportQuestionOutVO getQuestionById(String quesId);
}
