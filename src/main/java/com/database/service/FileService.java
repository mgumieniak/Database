package com.database.service;

import com.database.model.entity.FileDB;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Service
public class FileService {

    private GridFsTemplate gridFsTemplate;
    private GridFsOperations operations;

    @Autowired
    public FileService(GridFsTemplate gridFsTemplate, GridFsOperations operations) {
        this.gridFsTemplate = gridFsTemplate;
        this.operations = operations;
    }

    public String uploadFile(MultipartFile file) throws IOException {

//        FileDB fileToSave = new FileDB.Builder(new Binary(BsonBinarySubType.BINARY, file.getBytes()),
//                file.getName(),"pdf","Jan", LocalDate.now()).build();


        ObjectId id = gridFsTemplate.store(
                file.getInputStream(), file.getOriginalFilename(), file.getContentType(), getMetaData(file));

        return id.toString();
    }

    private DBObject getMetaData( MultipartFile file){
        DBObject metaData = new BasicDBObject();
        metaData.put("title", file.getOriginalFilename());
        metaData.put("type",getFileType(file).orElse("Can't read extension file"));
        return metaData;
    }

    private Optional<String> getFileType(MultipartFile file){
        return Optional.ofNullable(file.getOriginalFilename().split("\\.")[1]);
    }





}
