package com.lesson3.hw.dao;

import com.lesson3.hw.exception.InternalServerError;
import com.lesson3.hw.model.File;
import com.lesson3.hw.model.Storage;
import org.hibernate.Session;

public interface StorageDAOInterface extends GeneralDAOInterface<Storage>{
    enum sizeActions {INCREASE, DECREASE}

    void changeSize(Long id, Long size, Session session, sizeActions act) throws InternalServerError;
    int checkStorageOnExistingFiles(File file) throws InternalServerError;
    int checkStorageOnExistingFiles(Storage storageFrom, Storage storageTo) throws InternalServerError;

}
