package com.store.model;

import java.util.Date;
import org.springframework.util.Assert;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Objects;

@Document(collection = "userfiles")
@CompoundIndexes({
    @CompoundIndex(name = "userfile_idx", def = "{'id': -1, 'date': -1}")
})
public class UserFiles {
	@Id
        @Indexed(unique = true)
        private String id;
        private String name;
	private int size;
	private String type;
	private String depart;
	private String description;
	private Date date;
}
