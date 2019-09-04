package com.store.exceptions;

public class ProductNotFoundExeption extends Exception{

    public ProductNotFoundExeption(String typeProduct) {
        super("Product not found: " + typeProduct);
    }
}
