/**
 * 
 */
package com.fykj.sample.dictionary.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fykj.platform.kernel.BusinessException;
import com.fykj.platform.kernel._c.model.InvokeResult;
import com.fykj.platform.kernel._c.model.JPage;
import com.fykj.platform.kernel._c.model.SimplePageRequest;
import com.fykj.platform.util.Copy;
import com.fykj.platform.web.model.SimplePageRequestVO;
import com.fykj.sample.cache.DictionaryCacheHelper;
import com.fykj.sample.dictionary.model.Dictionary;
import com.fykj.sample.dictionary.model.DictionaryData;
import com.fykj.sample.dictionary.service.DictionaryService;
import com.fykj.sample.dictionary.vo.DictDataAddInVO;
import com.fykj.sample.dictionary.vo.DictDataEditInVO;
import com.fykj.sample.dictionary.vo.DictDataOutVO;
import com.fykj.sample.dictionary.vo.DictionaryAddInVO;
import com.fykj.sample.dictionary.vo.DictionaryEditInVO;

/**
 * @author zhengzw
 *
 */
@Controller
@RequestMapping("/dictionary")
public class DictionaryController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private DictionaryService dictionaryService;

	/**
	 * 字典列表页面
	 */
	@RequestMapping("/toDictionaryList")
	public String toDictionaryList() {
		return "dictionary/dictionary-list";
	}

	/**
	 * 添加字典页面
	 */
	@RequestMapping("/toDictionaryAdd")
	public String toDictionaryAdd() {
		return "dictionary/dictionary-add";
	}

	/**
	 * 编辑字典页面
	 */
	@RequestMapping("/toDictionaryEdit")
	public String toDictionaryEdit(String id, Model model) {
		Dictionary dict = dictionaryService.getDictionaryById(id);
		model.addAttribute("dict", dict);
		return "dictionary/dictionary-edit";
	}

	/**
	 * 字典详情页面
	 */
	@RequestMapping("/toDictionaryView")
	public String toDictionaryView(String id, Model model) {
		Dictionary dict = dictionaryService.getDictionaryById(id);
		model.addAttribute("dict", dict);
		return "dictionary/dictionary-view";
	}

	/**
	 * 字典数据列表页面
	 */
	@RequestMapping("/toDictionaryDataList")
	public String toDictionaryDataList(Model model) {
		List<Dictionary> list = dictionaryService.getAllDictionarys();
		model.addAttribute("dicts", list);
		return "dictionary/dictionaryData-list";
	}

	/**
	 * 添加字典数据页面
	 */
	@RequestMapping("/toDictionaryDataAdd")
	public String toDictionaryDataAdd(Model model) {
		List<Dictionary> list = dictionaryService.getAllDictionarys();
		model.addAttribute("dicts", list);
		return "dictionary/dictionaryData-add";
	}

	/**
	 * 编辑字典数据页面
	 */
	@RequestMapping("/toDictionaryDataEdit")
	public String toDictionaryDataEdit(String id, Model model) {
		List<DictDataOutVO> list = dictionaryService.getDictionaryDataById(id);
		model.addAttribute("dictData", list.get(0));
		return "dictionary/dictionaryData-edit";
	}

	/**
	 * 字典数据详情页面
	 */
	@RequestMapping("/toDictionaryDataView")
	public String toDictionaryDataView(String id, Model model) {
		List<DictDataOutVO> list = dictionaryService.getDictionaryDataById(id);
		model.addAttribute("dictData", list.get(0));
		return "dictionary/dictionaryData-view";
	}

	@RequestMapping("/getDictionaryByName")
	@ResponseBody
	public InvokeResult getDictionaryByName(String name, SimplePageRequestVO pageVo) {
		try {
			JPage<Dictionary> page = dictionaryService.getDictionaryByName(name,
					new SimplePageRequest(pageVo.getPage(), pageVo.getSize()));
			return InvokeResult.success(page);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	@RequestMapping("/getAllDictionarys")
	@ResponseBody
	public InvokeResult getAllDictionarys() {
		try {
			List<Dictionary> list = dictionaryService.getAllDictionarys();
			return InvokeResult.success(list);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	@RequestMapping("/saveDictionary")
	@ResponseBody
	public InvokeResult saveDictionary(DictionaryAddInVO vo) {
		try {
			Dictionary dict = dictionaryService.saveDictionary(Copy.simpleCopy(vo, Dictionary.class));
			return InvokeResult.success(dict);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	@RequestMapping("/getDictionaryById")
	@ResponseBody
	public InvokeResult getDictionaryById(String id) {
		try {
			Dictionary dict = dictionaryService.getDictionaryById(id);
			return InvokeResult.success(dict);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	@RequestMapping("/editDictionary")
	@ResponseBody
	public InvokeResult editDictionary(DictionaryEditInVO vo) {
		try {
			dictionaryService.editDictionary(Copy.simpleCopy(vo, Dictionary.class));
			return InvokeResult.success(vo.getId());
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	@RequestMapping("/DeleteDictionaryById")
	@ResponseBody
	public InvokeResult DeleteDictionaryById(String ids) {
		if (StringUtils.isBlank(ids)) {
			return InvokeResult.failure("未获取字典类型信息");
		}
		try {
			String[] arr = ids.split(",");
			dictionaryService.deleteDictionarys(arr);
			return InvokeResult.success(true);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	@RequestMapping("/getDictionaryDataPage")
	@ResponseBody
	public InvokeResult getDictionaryDataPage(String code, SimplePageRequestVO pageVo) {
		try {
			JPage<DictDataOutVO> page = dictionaryService.getDictionaryDataPage(code,
					new SimplePageRequest(pageVo.getPage(), pageVo.getSize()));
			return InvokeResult.success(page);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	@RequestMapping("/saveDictionaryData")
	@ResponseBody
	public InvokeResult saveDictionaryData(DictDataAddInVO vo) {
		try {
			dictionaryService.saveDictionaryData(Copy.simpleCopy(vo, DictionaryData.class));
			return InvokeResult.success(true);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	@RequestMapping("/editDictionaryData")
	@ResponseBody
	public InvokeResult editDictionaryData(DictDataEditInVO vo) {
		try {
			dictionaryService.editDictionaryData(Copy.simpleCopy(vo, DictionaryData.class));
			return InvokeResult.success(true);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	@RequestMapping("/getDictionaryDataById")
	@ResponseBody
	public InvokeResult getDictionaryDataById(String id) {
		try {
			List<DictDataOutVO> list = dictionaryService.getDictionaryDataById(id);
			return InvokeResult.success(list.get(0));
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	@RequestMapping("/deleteDictionaryData")
	@ResponseBody
	public InvokeResult deleteDictionaryData(String ids) {
		if (StringUtils.isBlank(ids)) {
			return InvokeResult.failure("未获取字典明细信息");
		}
		try {
			String[] arr = ids.split(",");
			dictionaryService.deleteDictionaryDatas(arr);
			return InvokeResult.success(true);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	/**
	 * 重新加载缓存
	 * 
	 * @return
	 */
	@RequestMapping("/loadCache")
	@ResponseBody
	public InvokeResult loadCache() {
		try {
			DictionaryCacheHelper.load();
			return InvokeResult.success(true);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}
	
	/**
	 * 根据code获取字典信息
	 * @param code
	 * @return
	 */
	@RequestMapping("/getDictionaryByCode")
	@ResponseBody
	public InvokeResult getDictionaryByCode(String code) {
		try {
			return InvokeResult.success(DictionaryCacheHelper.getDictData(code));
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}
}
