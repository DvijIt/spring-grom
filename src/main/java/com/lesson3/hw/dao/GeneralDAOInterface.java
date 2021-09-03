package com.lesson3.hw.dao;

import com.lesson3.hw.exception.InternalServerError;
import com.lesson3.hw.model.Model;
import org.hibernate.SessionFactory;

public interface GeneralDAOInterface<T extends Model> {

    T findById(Long id) throws InternalServerError;
    SessionFactory createSessionFactory();

}
