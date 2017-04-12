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
public abstract class JTreeNode {

	private List<JTreeNode> children;
	
	private List<JTreeNode> nodes ;

	private Object entity;

	public abstract String getId();

	public abstract void setId(String id);

	public abstract String getPid();

	public abstract void setPid(String pid);

	public abstract String getText();

	public abstract void setText(String text);

	public List<JTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<JTreeNode> children) {
		this.children = children;
	}

	public Object getEntity() {
		return entity;
	}

	public void setEntity(Object entity) {
		this.entity = entity;
	}

	public boolean isParent(JTreeTemplate template) {
		return getId().equals(template.getPid());
	}
	
	public void addChildren(JTreeNode node) {
		if(children == null) {
			children = new ArrayList<JTreeNode>();
		}
		children.add(node);
	}

	public List<JTreeNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<JTreeNode> nodes) {
		this.nodes = nodes;
	}
	
	public void addNodes(JTreeNode node) {
		if(nodes == null) {
			nodes = new ArrayList<JTreeNode>();
		}
		nodes.add(node);
	}
}
