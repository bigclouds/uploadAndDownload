package com.store.model;

import java.util.Date;
import org.springframework.util.Assert;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Objects;

@Document(collection = "upload")
@CompoundIndexes({
    @CompoundIndex(name = "file_idx", def = "{'id': -1, 'date': -1}")
})
public class Upload {
	@Id
        @Indexed(unique = true)
        private String id;
        private String name;
	private int nodeid;
        private String path;
	private Date date;
}
