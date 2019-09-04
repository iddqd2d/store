package com.store;

import com.store.enums.TypeProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class Product {
    private TypeProduct typeProduct;
    private int price;
}
