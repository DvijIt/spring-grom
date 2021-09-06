package com.lesson3.hw.service;

import com.lesson3.hw.dao.FileDAO;
import com.lesson3.hw.dao.StorageDAO;
import com.lesson3.hw.exception.BadRequestException;
import com.lesson3.hw.exception.InternalServerError;
import com.lesson3.hw.model.File;
import com.lesson3.hw.model.Storage;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class Service implements ServiceInterface {
    private FileDAO fileDAO;
    private StorageDAO storageDAO;

    @Autowired
    public Service(FileDAO fileDAO, StorageDAO storageDAO) {
        this.fileDAO = fileDAO;
        this.storageDAO = storageDAO;
    }

    @Override
    public File put(Storage storage, File file) throws InternalServerError, BadRequestException {
        file.setStorage(storage);
        validateFile(storage, file);

        return fileDAO.put(storage, file);
    }

    @Override
    public File delete(Storage storage, File file) throws InternalServerError, BadRequestException {
        file.setStorage(storage);
        if(storageDAO.checkStorageOnExistingFiles(file) == 0)
            throw new BadRequestException(getClass().getName()+"-checkStorageOnExistingFiles. There is no file id: "+file.getId()+" on storage id: "+storage.getId());
        return fileDAO.delete(storage, file);
    }

    @Override
    public void transferAll(Storage storageFrom, Storage storageTo) throws InternalServerError, BadRequestException {
        //check if exists
        if(storageDAO.checkStorageOnExistingFiles(storageFrom, storageTo) > 0)
            throw new BadRequestException(getClass().getName()+"-checkStorageOnExistingFiles. There is existing file from Storage id:"+storageFrom.getId()+" in Storage id:"+storageTo.getId());
        //check size and format
        long filesSize = 0;
        long storageSize = storageTo.getStorageSize();
        for (File file : fileDAO.getFilesByStorageId(storageFrom.getId())) {
            filesSize += file.getSize();
            storageSize -= file.getSize();

            checkFileFormat(storageTo, file);
            if (storageSize < 0)
                throw new BadRequestException(getClass().getName() + "-transferAll. Storage is full. storage id:" + storageTo.getId() + (file.getId() == 0 ? "" : " file id:" + file.getId()));
        }

        fileDAO.transferFiles(storageFrom, storageTo, filesSize);
    }

    @Override
    public void transferFile(Storage storageFrom, Storage storageTo, Long id) throws InternalServerError, BadRequestException {
        File file = fileDAO.findById(id);
        file.setStorage(storageTo);

        validateFile(storageTo, file);

        fileDAO.transferFile(storageFrom, storageTo, file);
    }

    private void validateFile(Storage storage, File file) throws InternalServerError, BadRequestException {
        //check if exists
        file.setStorage(storage);
        if(storageDAO.checkStorageOnExistingFiles(file) > 0)
            throw new BadRequestException(getClass().getName()+"-checkStorageOnExistingFiles. There is existing file: "+file.toString()+" in Storage id:"+file.getStorage().getId());
        //check size
        if(storage.getStorageSize() < file.getSize())
            throw new BadRequestException(getClass().getName()+"-checkInputFileSize. There is no enough free space in storage id:"+storage.getId()+" file id:"+file.getId());
        //check format
        checkFileFormat(storage, file);
    }

    private void checkFileFormat(Storage storage, File file) throws BadRequestException {
        for(String format : storage.getFormatsSupported().split(","))
            if(format.equals(file.getFormat()))
                return;
        throw new BadRequestException(getClass().getName()+"-checkInputFileFormat. File format is not accepted. storage id:"+storage.getId()+" file id:"+file.getId());
    }
}
