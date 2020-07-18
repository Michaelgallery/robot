package com.bjlemon.entity;
/**
 * excel解析商品信息类
 * @author apple
 *
 */
public class Product {
	// 商品名称
	private String name;
	// 商品图片
	private String image;
	// 商品价格
	private String price;
	// 商品优惠券金额
	private String coupanPrice;
	// 商品优惠券短链接
	private String coupanPath;
	// 商品优惠券淘口令
	private String coupanSearchPassword;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCoupanPrice() {
		return coupanPrice;
	}
	public void setCoupanPrice(String coupanPrice) {
		this.coupanPrice = coupanPrice;
	}
	public String getCoupanPath() {
		return coupanPath;
	}
	public void setCoupanPath(String coupanPath) {
		this.coupanPath = coupanPath;
	}
	public String getCoupanSearchPassword() {
		return coupanSearchPassword;
	}
	public void setCoupanSearchPassword(String coupanSearchPassword) {
		this.coupanSearchPassword = coupanSearchPassword;
	}
	public Product(String name, String image, String price, String coupanPrice, String coupanPath,
			String coupanSearchPassword) {
		super();
		this.name = name;
		this.image = image;
		this.price = price;
		this.coupanPrice = coupanPrice;
		this.coupanPath = coupanPath;
		this.coupanSearchPassword = coupanSearchPassword;
	}
	public Product() {
		super();
	}
	
}
