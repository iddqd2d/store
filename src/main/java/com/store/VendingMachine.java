package com.store;

import com.store.enums.Bill;
import com.store.enums.TypeProduct;
import com.store.exceptions.NotEnoughMoneyExeption;
import com.store.exceptions.ProductNotFoundExeption;
import lombok.Getter;
import lombok.extern.log4j.Log4j;

@Log4j
@Getter
public class VendingMachine {
    private int cashOfVendingMachine;
    private int cashOfTransaction;
    private StockStore stockStore;

    public VendingMachine(int cashOfVendingMachine, StockStore stockStore) {
        this.stockStore = stockStore;
        this.cashOfVendingMachine = cashOfVendingMachine;
    }

    public VendingMachine addCashOfTransaction(Bill bill) {
        cashOfTransaction += bill.getCost();
        return this;
    }

    public Product sellProduct(TypeProduct typeProduct) throws NotEnoughMoneyExeption, ProductNotFoundExeption {
        Product product = stockStore.removeProductByType(typeProduct);
        int productCost = product.getPrice();
        if (cashOfTransaction > productCost) {
            cashOfVendingMachine += productCost;
            cashChange(product);
            cashOfTransaction = 0;
            return product;
        } else throw new NotEnoughMoneyExeption(product.getPrice() - cashOfTransaction);
    }

    private int cashChange(Product product) {
        return cashOfTransaction - product.getPrice();
    }

    public int cashBack() {
        int cash = cashOfTransaction;
        reset();
        return cash;
    }

    public int rollback(Product product) {
        stockStore.addProduct(product);
        return product.getPrice();
    }

    private void reset() {
        cashOfTransaction = 0;
    }

    public void stockProducts() {
        log.info(stockStore.toString());
    }
}
