package br.com.fourstore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.fourstore.enums.CategoryEnum;
import br.com.fourstore.enums.ColorEnum;
import br.com.fourstore.enums.DepartmentEnum;
import br.com.fourstore.enums.SizeEnum;
import br.com.fourstore.enums.TypeEnum;

@Document(collection = "inventory")
public class Product {
	
	@Id
	private String id;
	
	private String sku;
	
	private String description;
	
	private Integer theAmount;
	
	private Double price;
	
	private TypeEnum type;
	
	private ColorEnum color;
	
	private DepartmentEnum department;
	
	private SizeEnum size;
	
	private CategoryEnum category;


	public Product() {
		super();
	}
	
	public Product(String sku, String description, Integer theAmount, Double price) {
		super();
		this.sku = sku;
		this.description = description;
		this.theAmount = theAmount;
		this.price = price;
		
		transferringData();
	}
	
	
	public String getId() {
		return id;
	}

	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
		
		transferringData();
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getTheAmount() {
		return theAmount;
	}
	public void setTheAmount(Integer theAmount) {
		this.theAmount = theAmount;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public TypeEnum getType() {
		return type;
	}
	
	public ColorEnum getColor() {
		return color;
	}
	
	public DepartmentEnum getDepartment() {
		return department;
	}
	
	public SizeEnum getSize() {
		return size;
	}
	
	public CategoryEnum getCategory() {
		return category;
	}
	
	public void transferringData() {
		try {
			this.type = TypeEnum.getTypeEnum(sku.substring(0, 3));
			this.color = ColorEnum.getColorEnum(Integer.parseInt(sku.substring(3, 4)));
			this.department = DepartmentEnum.getDepartmentEnum(sku.substring(4, 6));
			this.size = SizeEnum.getSizeEnum(Integer.parseInt(sku.substring(6, 7)));
			this.category = CategoryEnum.getCategoryEnum(sku.substring(7));
		} catch (Exception e) {
			this.type = null;
			this.color = null;
			this.department = null;
			this.size = null;
			this.category = null;
		}
	}

	@Override
	public String toString() {
		return "\nSKU: "+ sku + "\nDescricao: " + description + "\nPreco: R$" + price + "\nQuantidade: " + theAmount
				+ "\nDetalhes: " + type + ", " + color + ", " + department + ", " + size + ", " + category
				+ "\n-----------------------------------------------------";
	}
	
}
