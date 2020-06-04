package com.pro.service.impl;

import com.pro.pj.Money;
import com.pro.redis.JedisUtils;
import com.pro.sql.mapper.MoneyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DBServiceImpl implements DBService {
    @Autowired
    private  MoneyMapper moneyMapper;
    @Autowired
    private JedisUtils jedisUtils;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void plusMoney(Money money,String transactionId) throws Exception{
        moneyMapper.plusMoney(money);
        jedisUtils.delKey(transactionId);
        //jedisUtils.
    }
}
