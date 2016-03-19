package com.timeron.NexusDatabaseLibrary.dto;

public class IdOrderDTO {

	private Integer id;
	private Long order;
	
	public IdOrderDTO() {
		super();
	}

	public IdOrderDTO(Integer typeId, Long order) {
		this.id = typeId;
		this.order = order;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getOrder() {
		return order;
	}
	public void setOrder(Long order) {
		this.order = order;
	}
	
	

}
