package ch.zhaw.iwi.devops.model.user.permission;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import ch.zhaw.iwi.devops.model.AbstractEntity;

@Entity
public class PermissionRole extends AbstractEntity {

	private String name;

	@ManyToMany
	@JoinTable(name = "PermissionRole_Functions", inverseJoinColumns = @JoinColumn(name = "permissionFunction_key"))
	private Set<PermissionFunction> permissionFunctions = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<PermissionFunction> getPermissionFunctions() {
		return permissionFunctions;
	}

	public void setPermissionFunctions(Set<PermissionFunction> permissionFunctions) {
		this.permissionFunctions = permissionFunctions;
	}

}
