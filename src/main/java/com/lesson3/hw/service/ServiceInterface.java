package com.lesson3.hw.service;

import com.lesson3.hw.exception.InternalServerError;
import com.lesson3.hw.model.File;
import com.lesson3.hw.model.Storage;

public interface ServiceInterface {

    File put(Storage storage, File file) throws InternalServerError;
    File delete(Storage storage, File file) throws InternalServerError;
    void transferAll(Storage storageFrom, Storage storageTo) throws InternalServerError;
    void transferFile(Storage storageFrom, Storage storageTo, Long id) throws InternalServerError;

}