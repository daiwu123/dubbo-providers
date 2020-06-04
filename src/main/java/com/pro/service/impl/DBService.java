package com.pro.service.impl;

import com.pro.pj.Money;
import com.pro.sql.mapper.MoneyMapper;

public interface DBService {
   void plusMoney(Money money,String trasactionKey)throws Exception;
}
