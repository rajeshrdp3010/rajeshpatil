package com.product.order.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;




@Entity
@Table(name = "order_table")
public class Order extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4329954804450826581L;

	private String customerName;
	
	private String customerAddress;
	
	@OneToOne(cascade = CascadeType.MERGE , orphanRemoval = true)
	@JoinColumn(name = "order_detail_id", referencedColumnName = "id")
	private OrderDetail orderDetail;

	protected Order() {}
	
	public Order(String customerName, String customerAddress,OrderDetail orderDetail) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.orderDetail = orderDetail;
        this.orderDetail.setOrder(this);
    }
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}
	
	@Override
	public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof Order))
            return false;
 
        final Order a = (Order)object;
 
        if (this.getId() != null && a.getId() != null && this.getCustomerName() != null && a.getCustomerName() != null) {
			return getId().equals(a.getId()) && getCustomerName().equals(a.getCustomerName());
		}
        return false;
    }
	
	@Override
    public int hashCode() {
        return this.getCustomerName().hashCode();
    }
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Order{");
		sb.append("id=").append(getId());
		sb.append(", customerName=").append(customerName);
		sb.append(", customerAddress=").append(customerAddress);
		sb.append(", quantity=").append(orderDetail.getQuantity());
		sb.append(", productName=").append(orderDetail.getProduct().getName());
		sb.append("}");
		return sb.toString();
	}

}
