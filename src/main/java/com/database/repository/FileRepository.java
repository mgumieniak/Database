package com.database.repository;

import com.database.model.entity.FileDB;
import org.springframework.data.repository.CrudRepository;

public interface FileRepository extends CrudRepository<FileDB, String> {
}
