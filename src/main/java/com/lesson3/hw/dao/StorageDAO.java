package com.lesson3.hw.dao;

import com.lesson3.hw.exception.InternalServerError;
import com.lesson3.hw.model.File;
import com.lesson3.hw.model.Storage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.type.IntegerType;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class StorageDAO extends GeneralDAO<Storage> implements StorageDAOInterface {

    private static final String SQL_INCREASE_SIZE = "UPDATE STORAGE SET STORAGE_SIZE = STORAGE_SIZE+ :size WHERE ID = :storageId";
    private static final String SQL_DECREASE_SIZE = "UPDATE STORAGE SET STORAGE_SIZE = STORAGE_SIZE- :size WHERE ID = :storageId";
    private static final String SQL_CHECK_IF_EXISTS_BY_FILE_ID =
            "SELECT  COUNT(*) cnt \n" +
                    "FROM    FILES \n" +
                    "WHERE   STORAGE_ID = :storageId \n" +
                    "    AND NAME = :fileName\n" +
                    "    AND FORMAT = :fileFormat\n" +
                    "    AND FILE_SIZE = :fileSize";

    private static final String SQL_CHECK_IF_EXISTS_BY_STORAGE_ID =
            "SELECT  COUNT(*) cnt \n" +
                    "FROM    FILES \n" +
                    "WHERE   STORAGE_ID = :storageToId \n" +
                    "    AND EXISTS (\n" +
                    "        SELECT  * \n" +
                    "        FROM    FILES Checked  \n" +
                    "        WHERE   Checked.STORAGE_ID = :storageFromId \n" +
                    "            AND Checked.NAME = FILES.NAME \n" +
                    "            AND Checked.FORMAT = FILES.FORMAT \n" +
                    "            AND Checked.FILE_SIZE = FILES.FILE_SIZE\n" +
                    ")";

    public StorageDAO() {
        setClazz(Storage.class);
    }

    @Override
    public void changeSize(Long id, Long size, Session session, sizeActions act) throws InternalServerError {
        try {
            Query query = session.createSQLQuery(act == sizeActions.INCREASE ? SQL_INCREASE_SIZE : SQL_DECREASE_SIZE);
            query.setParameter("size", size);
            query.setParameter("storageId", id);
            if (query.executeUpdate() == 0)
                throw new InternalServerError(getClass().getName() + "-" + act.toString() + ". Storage size with id " + id + " was not updated");
        } catch (HibernateException e) {
            throw new InternalServerError(getClass().getName() + "-" + act.toString() + ". Storage size with id " + id + " was not updated. " + e.getMessage());
        }
    }

    @Override
    public int checkStorageOnExistingFiles(File file) throws InternalServerError {
        try (Session session = createSessionFactory().openSession()) {
            return (Integer) session.createSQLQuery(SQL_CHECK_IF_EXISTS_BY_FILE_ID)
                    .setParameter("storageId", file.getStorage().getId())
                    .setParameter("fileName", file.getName())
                    .setParameter("fileFormat", file.getFormat())
                    .setParameter("fileSize", file.getSize())
                    .addScalar("cnt", IntegerType.INSTANCE)
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new InternalServerError(getClass().getName() + "-checkStorageOnExistingFiles. " + e.getMessage());
        }
    }

    @Override
    public int checkStorageOnExistingFiles(Storage storageFrom, Storage storageTo) throws InternalServerError {
        try (Session session = createSessionFactory().openSession()) {
            return (Integer) session.createSQLQuery(SQL_CHECK_IF_EXISTS_BY_STORAGE_ID)
                    .setParameter("storageToId", storageTo.getId())
                    .setParameter("storageFromId", storageFrom.getId())
                    .addScalar("cnt", IntegerType.INSTANCE)
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new InternalServerError(getClass().getName() + "-checkStorageOnExistingFiles. " + e.getMessage());
        }
    }
}