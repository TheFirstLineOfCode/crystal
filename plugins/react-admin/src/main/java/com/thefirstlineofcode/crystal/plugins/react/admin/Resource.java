package com.thefirstlineofcode.crystal.plugins.react.admin;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Resource {
	private String name;
	private String label;
	private boolean parentMenu;
	private String menuParent;
	private String listComponentName;
	private String createComponentName;
	private String editComponentName;
	
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

	public String getListComponentName() {
		return listComponentName;
	}

	public void setListComponentName(String listComponentName) {
		this.listComponentName = listComponentName;
	}

	public String getCreateComponentName() {
		return createComponentName;
	}

	public void setCreateComponentName(String createComponentName) {
		this.createComponentName = createComponentName;
	}

	public String getEditComponentName() {
		return editComponentName;
	}

	public void setEditComponentName(String editComponentName) {
		this.editComponentName = editComponentName;
	}
}
