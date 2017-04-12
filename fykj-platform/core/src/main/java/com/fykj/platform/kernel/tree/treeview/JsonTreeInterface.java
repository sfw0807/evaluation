package com.fykj.platform.kernel.tree.treeview;

import java.util.List;

import com.fykj.platform.kernel.tree.model.JTreeNode;
import com.fykj.platform.kernel.tree.vo.JTreeTemplate;

public interface JsonTreeInterface<T extends JTreeTemplate> {

	/** 
	 * 获得多个 T 类型的 根节点 tree 根据  {@link JTreeNode}   
	 * @param treeNodes
	 * @return
	 */
	List<T> generateJTreeTemplates(List<JTreeNode> treeNodes);

	/**
	 * 获取当个节点T 节点 tree 根据 {@link JTreeNode}
	 * @param treeNodes
	 * @return
	 */
	T generateJTreeTemplate(List<JTreeNode> treeNodes);

}
