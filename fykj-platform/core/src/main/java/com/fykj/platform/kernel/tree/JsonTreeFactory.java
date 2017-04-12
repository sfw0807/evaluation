package com.fykj.platform.kernel.tree;

import java.util.List;

import com.fykj.platform.kernel.tree.model.JEntity;
import com.fykj.platform.kernel.tree.model.JTree;
import com.fykj.platform.kernel.tree.model.JTreeNode;
import com.fykj.platform.kernel.tree.treeview.JTreeNodeInterface;
import com.fykj.platform.kernel.tree.treeview.JTreeNodeInterfaceImpl;
import com.fykj.platform.kernel.tree.treeview.JsonTreeInterface;
import com.fykj.platform.kernel.tree.treeview.JsonTreeInterfaceImpl;
import com.fykj.platform.kernel.tree.vo.JTreeTemplate;

public class JsonTreeFactory {

	private static JsonTreeFactory INSTANCE = new JsonTreeFactory();

	private JsonTreeInterface<JTreeTemplate> jsonTreeInterface;
	
	/**
	 * 私有的
	 */
	private  JTreeNodeInterface jTreeNodeInterface = new JTreeNodeInterfaceImpl();
	
	private void init(){
		if(null==jsonTreeInterface){
			jsonTreeInterface = new JsonTreeInterfaceImpl();
		}
		
	
	}
	
	public static JsonTreeFactory getInstance() {
			INSTANCE.init();
		return INSTANCE;
	}
	
	/**
	 * 
	 * @return
	 */
	public JTreeTemplate generateJTreeTemplates(List<? extends JTree> dataList,List<? extends  JEntity> entitys){
		List<JTreeNode> generateTreeNode = jTreeNodeInterface.generateTreeNode(dataList, entitys);
		return jsonTreeInterface.generateJTreeTemplate(generateTreeNode);
	}
	
	/**
	 * 
	 * @return
	 */
	public JTreeTemplate generateLimitJTreeTemplates(List<? extends JTree> dataList,List<? extends  JEntity> entitys){
		List<JTreeNode> generateTreeNode = jTreeNodeInterface.generateLimitTreeNode(dataList, entitys);
		return jsonTreeInterface.generateJTreeTemplate(generateTreeNode);
	}
	
	public static void main(String[] args) {
		System.out.println("i am here");
		
	}
	
	

}
