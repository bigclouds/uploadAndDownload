package com.store.model;

import java.util.Date;
import org.springframework.util.Assert;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Objects;

@Document(collection = "cluster") 
public class Cluster {
	
	@Id
	@Indexed(unique = true)
	private String id;
	private String name;
	private String type;
	private String path;
	private boolean enabled;
	private boolean isdefault;

	public Cluster(String name, String type, String path, boolean enabled, boolean isdefault) {
		Assert.hasText(name, "name must not be null or empty!");
		Assert.hasText(type, "type must not be null or empty!");
		Assert.hasText(path, "path must not be null or empty!");
		Assert.notNull(enabled, "enabled must not be null");
		Assert.notNull(isdefault, "enabled must not be null");
		this.name = name;
		this.type = type;
		this.path = path;
		this.enabled = enabled;
		this.isdefault = isdefault;
	}
	public Cluster() {
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public boolean getIsdefault() {
		return isdefault;
	}
	public void setIsdefault(boolean dft) {
		this.isdefault = dft;
	}

	@Override
	public String toString() {
		return "Cluster[name=" + name + ",type=" + type + ",enabled=" + enabled + ",isdefault=" + isdefault + "]";
	}

	@Override
        public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cluster user = (Cluster) o;
		return Objects.equals(this.type, user.getType()) &&
			Objects.equals(this.path, user.getPath());
       }
}
