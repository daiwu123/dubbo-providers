package com.pro.sql.mapper;

import com.m.model.Pop;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public interface PopMapper {
    List<Pop> queryAllPop();
}
