package com.thefirstlineofcode.crystal.plugins.react.admin;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Resource {
	private String name;
	private String label;
	private boolean parentMenu;
	private String menuParent;
	private String listViewName;
	private String createViewName;
	private String editViewName;
	
	public Resource(String name, String label, boolean parentMenu, String menuParent) {
		this.name = name;
		this.label = label;
		this.parentMenu = parentMenu;
		this.menuParent = menuParent; 
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public boolean isParentMenu() {
		return parentMenu;
	}
	
	public void setParentMenu(boolean parentMenu) {
		this.parentMenu = parentMenu;
	}
	
	public String getMenuParent() {
		return menuParent;
	}
	
	public void setMenuParent(String menuParent) {
		this.menuParent = menuParent;
	}

	public String getListViewName() {
		return listViewName;
	}

	public void setListViewName(String listViewName) {
		this.listViewName = listViewName;
	}

	public String getCreateViewName() {
		return createViewName;
	}

	public void setCreateViewName(String createViewName) {
		this.createViewName = createViewName;
	}

	public String getEditViewName() {
		return editViewName;
	}

	public void setEditViewName(String editViewName) {
		this.editViewName = editViewName;
	}
}
