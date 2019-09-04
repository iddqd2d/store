package com.store;

import com.store.enums.Bill;
import com.store.enums.TypeProduct;
import com.store.exceptions.NotEnoughMoneyExeption;
import com.store.exceptions.ProductNotFoundExeption;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Log4j
public class VendingMachineTest {
    private StockStore stockStore;
    private VendingMachine vendingMachine;

    @Before
    public void setUp() throws Exception {
        stockStore = new StockStore()
                .addProduct(new Product(TypeProduct.BLUETOOTH_HEADSET, 65))
                .addProduct(new Product(TypeProduct.PORTABLE_SPEAKER, 35))
                .addProduct(new Product(TypeProduct.SIX_DVDS, 45));
        vendingMachine = new VendingMachine(200, stockStore);
        vendingMachine
                .addCashOfTransaction(Bill.ONE)
                .addCashOfTransaction(Bill.FIVE)
                .addCashOfTransaction(Bill.TEN)
                .addCashOfTransaction(Bill.TWENTY);
    }

    @Test
    public void addCashOfTransaction() {
        vendingMachine
                .addCashOfTransaction(Bill.ONE)
                .addCashOfTransaction(Bill.FIVE)
                .addCashOfTransaction(Bill.TEN)
                .addCashOfTransaction(Bill.TWENTY);
        assertEquals(vendingMachine.getCashOfTransaction(), 72);
    }

    @Test
    public void buyProduct() {
        Product product = null;
        try {
            product = vendingMachine.sellProduct(TypeProduct.PORTABLE_SPEAKER);
        } catch (ProductNotFoundExeption productNotFoundExeption) {
            log.error(productNotFoundExeption.getMessage());
        } catch (NotEnoughMoneyExeption notEnoughMoneyExeption) {
            log.error(notEnoughMoneyExeption.getMessage());
        }
        assertFalse(vendingMachine.getStockStore().getProducts().contains(product));
        assertNotNull(product);
    }

    @Test
    public void cashBack() {
        int cash = vendingMachine.cashBack();
        assertEquals(cash, 36);
    }

    @Test
    @SneakyThrows
    public void rollback() {
        Product product = vendingMachine.sellProduct(TypeProduct.PORTABLE_SPEAKER);
        assertFalse(stockStore.getProducts().contains(product));
        assertEquals(vendingMachine.rollback(product), 35);
        assertTrue(stockStore.getProducts().contains(product));
    }
}
