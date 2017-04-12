/**
 * 
 */
package com.fykj.sample.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengzw
 *
 */
public class JTreeUtils {

	/**
	 * 根据指定节点生成树
	 * 
	 * @param root
	 * @param data
	 */
	public static List<JTreeNode> buildTree(String root, List<? extends JTreeTemplate> data,
			Class<? extends JTreeNode> clazz) {

		List<JTreeNode> list = new ArrayList<JTreeNode>();

		try {
			if (null == data || data.size() == 0) {
				return list;
			}

			JTreeNode node = null;

			for (JTreeTemplate template : data) {
				if (template.getPid().equals(root)) {
					node = clazz.newInstance();

					node.setId(template.getId());
					node.setPid(template.getPid());
					node.setText(template.getText());
					node.setEntity(template);

					list.add(node);
					// data.remove(template);

					addNode(node, data, clazz);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	private static void addNode(JTreeNode pnode, List<? extends JTreeTemplate> data, Class<? extends JTreeNode> clazz) {

		try {
			JTreeNode node = null;
			
			for (JTreeTemplate template : data) {
				node = clazz.newInstance();
				node.setId(template.getId());
				node.setPid(template.getPid());
				node.setText(template.getText());
				node.setEntity(template);
				
				if (pnode.isParent(template)) {
					pnode.addChildren(node);
					pnode.addNodes(node);
					addNode(node, data, clazz);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}