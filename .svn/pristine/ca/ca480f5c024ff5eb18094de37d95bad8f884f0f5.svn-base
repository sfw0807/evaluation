/**
 * 
 */
package com.fykj.sample.dictionary.service;

import java.util.List;

import com.fykj.platform.kernel._c.model.JPage;
import com.fykj.platform.kernel._c.model.SimplePageRequest;
import com.fykj.sample.dictionary.model.Dictionary;
import com.fykj.sample.dictionary.model.DictionaryData;
import com.fykj.sample.dictionary.vo.DictDataOutVO;
import com.fykj.sample.dictionary.vo.DictionaryCacheVO;

/**
 * @author zhengzw
 *
 */
public interface DictionaryService {

	public JPage<Dictionary> getDictionaryByName(String name, SimplePageRequest page);

	public List<Dictionary> getAllDictionarys();

	public Dictionary saveDictionary(Dictionary dict);

	public boolean dictionaryExists(String code);

	public Dictionary getDictionaryById(String id);

	public void editDictionary(Dictionary dict);

	public void deleteDictionaryById(String id);

	public void deleteDictionarys(String[] ids);

	public JPage<DictDataOutVO> getDictionaryDataPage(String code, SimplePageRequest page);

	public void saveDictionaryData(DictionaryData dictData);

	public boolean dictDataExists(String code, String value);

	public DictionaryData editDictionaryData(DictionaryData dictData);

	public List<DictDataOutVO> getDictionaryDataById(String id);

	public void deleteDictionaryDataById(String id);

	public void deleteDictionaryDatas(String[] ids);

	public List<DictionaryCacheVO> loadDictonary();

	public List<DictionaryData> getDictionaryDataByCode(String code);
}
