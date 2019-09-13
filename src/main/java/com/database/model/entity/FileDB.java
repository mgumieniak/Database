package com.database.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
@Document
public class FileDB implements Serializable {
    @Id
    private final String id;
    private final Binary contentFile;
    private final String fileName;
    private final String fileFormat;
    private final String author;
    private final LocalDate createDate;
    private final LocalDate lastModificationData;

    public FileDB(Builder builder) {
        id = builder.id;
        contentFile = builder.contentFile;
        fileName = builder.fileName;
        fileFormat = builder.fileFormat;
        author = builder.author;
        createDate = builder.createDate;
        lastModificationData = builder.lastModificationDate;
    }

    public static class Builder {
        private final Binary contentFile;
        private final String fileName;
        private final String fileFormat;
        private final String author;
        private final LocalDate createDate;

        private String id = "";
        private LocalDate lastModificationDate = LocalDate.now();

        public Builder(Binary contentFile, String fileName, String fileFormat, String author, LocalDate createDate) {
            this.contentFile = contentFile;
            this.fileName = fileName;
            this.fileFormat = fileFormat;
            this.author = author;
            this.createDate = createDate;
        }

        public Builder lastModificationDate(LocalDate lastModificationDate) {
            this.lastModificationDate = lastModificationDate;
            return this;
        }

        public FileDB build() {
            return new FileDB(this);
        }
    }

}
