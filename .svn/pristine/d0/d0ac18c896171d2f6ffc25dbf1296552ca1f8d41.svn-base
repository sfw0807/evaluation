/**
 * 
 */
package com.fykj.sample.dictionary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fykj.platform.kernel._c.model.AbstractEntity;

/**
 * @author zhengzw
 *
 */
@Entity
@Table(name = "t_sys_dictionary_data")
public class DictionaryData extends AbstractEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1885611600013335415L;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "value")
	private String value;
	
	@Column(name = "sequence")
	private Integer sequence;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
}
