package com.store;

import com.store.enums.TypeProduct;
import com.store.exceptions.ProductNotFoundExeption;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class StockStore {
    private List<Product> products;

    public StockStore() {
        products = new ArrayList<>();
    }

    public StockStore addProduct(Product product) {
        products.add(product);
        return this;
    }

    public Product removeProductByType(TypeProduct typeProduct) throws ProductNotFoundExeption {
        for (int i = 0; i < products.size(); i++) {
            Product productByType = products.get(i);
            if (productByType.getTypeProduct().equals(typeProduct)) {
                return products.remove(i);
            }
        }
        throw new ProductNotFoundExeption(typeProduct.name());
    }

    public boolean aviableTypeProduct(TypeProduct typeProduct) {
        return products.stream().filter(product -> product.getTypeProduct().equals(typeProduct)).count() > 0;
    }
}
