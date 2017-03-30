package com.mf.stock.model;


public class Item {

    private int code;
    private String description;
    private String barcode;
    private int quantity;
    private int progress;


    public Item(int code, String description, String barcode, int quantity) {
        this.code = code;
        this.description = description;
        this.barcode = barcode;
        this.quantity = quantity;
        this.progress = 0;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void subtractItem(){
        if(progress > 0){
            progress--;
        }
    }

    public void addItem(){
        if(progress < quantity){
            progress++;
        }
    }

    public void setProgress(int progress){
        if(progress > quantity){
           this.progress = quantity;
        }
        this.progress = progress;

    }

    public int getProgress() {
        return progress;
    }
}

