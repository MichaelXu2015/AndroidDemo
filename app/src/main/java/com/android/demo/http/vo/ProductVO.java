package com.android.demo.http.vo;

public class ProductVO {

    private String productId;
    private String productName;
    private String categoryCode;
    private Float price;
    private String descript;
    private Integer stock;


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "ProductVO{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", categoryCode='" + categoryCode + '\'' +
                ", price=" + price +
                ", descript='" + descript + '\'' +
                ", stock=" + stock +
                '}';
    }
}
