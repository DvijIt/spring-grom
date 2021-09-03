package com.lesson3.hw.controller;

import com.lesson3.hw.exception.InternalServerError;
import com.lesson3.hw.model.File;
import com.lesson3.hw.model.Storage;
import com.lesson3.hw.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

public class Controller {
    private Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    public void put(Storage storage, File file) throws InternalServerError {
        service.put(storage, file);
        System.out.println("put done");
    }

    public void delete(Storage storage, File file) throws InternalServerError {
        service.delete(storage, file);
        System.out.println("delete done");
    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws InternalServerError {
        service.transferAll(storageFrom, storageTo);
        System.out.println("transferAll done");
    }

    public void transferFile(Storage storageFrom, Storage storageTo, Long id) throws InternalServerError {
        service.transferFile(storageFrom, storageTo, id);
        System.out.println("transferFile done");
    }
}
