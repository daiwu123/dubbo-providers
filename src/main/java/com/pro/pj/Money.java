package com.pro.pj;

import java.io.Serializable;

public class Money implements Serializable {
    private int id;
    private int money;
    public Money(){}
    public Money(int id, int money) {
        this.id = id;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
