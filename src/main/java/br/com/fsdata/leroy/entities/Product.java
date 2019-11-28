package br.com.fsdata.leroy.entities;

public class Product {
	
	private Integer lm;
	private String name;
	private Integer free_shipping;
	private String description;
	private Double price;
	
	
	public Integer getLm() {
		return lm;
	}
	public void setLm(Integer lm) {
		this.lm = lm;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getFree_shipping() {
		return free_shipping;
	}
	public void setFree_shipping(Integer free_shipping) {
		this.free_shipping = free_shipping;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((free_shipping == null) ? 0 : free_shipping.hashCode());
		result = prime * result + ((lm == null) ? 0 : lm.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (free_shipping == null) {
			if (other.free_shipping != null)
				return false;
		} else if (!free_shipping.equals(other.free_shipping))
			return false;
		if (lm == null) {
			if (other.lm != null)
				return false;
		} else if (!lm.equals(other.lm))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "Product [lm=" + lm + ", name=" + name + ", free_shipping=" + free_shipping + ", description="
				+ description + ", price=" + price + "]";
	}
}
