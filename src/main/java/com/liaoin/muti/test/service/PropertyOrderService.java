package com.liaoin.muti.test.service;

import com.liaoin.muti.test.project.entity.PropertyOrder;
import com.liaoin.muti.test.http.Response;

public interface PropertyOrderService {
    Response findById(String id);

    Response save(PropertyOrder propertyOrder);
}
