package com.lesson3.hw.dao;

import com.lesson3.hw.exception.InternalServerError;
import com.lesson3.hw.model.File;
import com.lesson3.hw.model.Storage;

import java.util.List;

public interface FileDAOInterface extends GeneralDAOInterface<File>{

    File put(Storage storage, File file) throws InternalServerError;
    File delete(Storage storage, File file) throws InternalServerError;
    void transferFiles(Storage storageFrom, Storage storageTo, long filesSize) throws InternalServerError;
    void transferFile(Storage storageFrom, Storage storageTo, File file) throws InternalServerError;
    List<File> getFilesByStorageId(Long id) throws InternalServerError;

}
