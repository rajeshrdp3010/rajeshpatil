package com.product.order.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "orderdetail_table")
public class OrderDetail extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7591185645075925752L;


	private Long quantity;
	
	
	@OneToOne(mappedBy = "orderDetail")
    private Order order;
	
	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id" ,referencedColumnName = "id")
	private Product product;
	
	protected OrderDetail() {}
	
	public OrderDetail(Long quantity,Product product) {
        this.quantity = quantity;
        this.product = product;
    }
	
	@JsonIgnore
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Override
	public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof OrderDetail))
            return false;
 
        final OrderDetail a = (OrderDetail)object;
 
        if (getId() != null && a.getId() != null) {
            return getId().equals(a.getId());
        }
        return false;
    }
	
	@Override
    public int hashCode() {
        return this.getId().hashCode();
    }
}
