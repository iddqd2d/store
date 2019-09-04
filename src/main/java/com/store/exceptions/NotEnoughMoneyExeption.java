package com.store.exceptions;

public class NotEnoughMoneyExeption extends Exception {

    public NotEnoughMoneyExeption(int money) {
        super("Not enough money: " + money);
    }
}
