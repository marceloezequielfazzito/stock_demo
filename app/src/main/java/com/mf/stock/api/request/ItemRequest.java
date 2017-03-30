package com.mf.stock.api.request;


import com.google.gson.annotations.SerializedName;

public class ItemRequest {

    @SerializedName("id")
    private int id;
    @SerializedName("description")
    private String description;
    @SerializedName("barcode")
    private String barcode;
    @SerializedName("quantity")
    private int quantity;

    public ItemRequest(int id, String description, String barcode, int quantity) {
        this.id = id;
        this.description = description;
        this.barcode = barcode;
        this.quantity = quantity;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

