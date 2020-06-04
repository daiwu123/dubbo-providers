package com.pro.sql.mapper;

import com.pro.pj.Money;
import org.springframework.stereotype.Component;

@Component
public interface MoneyMapper {
    /**
     * 描述:减
     * @param null
     * return
     * Author Dai Wu
     * Date 2020/5/24 12:19
     *
     * **/
    void lessMoney(Money money);

    /**
     * 描述:加
     * @param null
     * return
     * Author Dai Wu
     * Date 2020/5/24 12:19
     *
     * **/
    void plusMoney(Money money);
}
