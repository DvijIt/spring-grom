CREATE TABLE STORAGE(
                        ID NUMERIC,
                        CONSTRAINT STORAGE_PK PRIMARY KEY(ID),
                        FORMATS_SUPPORTED VARCHAR(100) NOT NULL,
                        STORAGE_COUNTRY VARCHAR(50),
                        STORAGE_SIZE NUMERIC CHECK(STORAGE_SIZE > 0)
);