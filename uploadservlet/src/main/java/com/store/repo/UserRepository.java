package com.store.repo;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;

import com.mongodb.DB;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import com.store.model.User;

@Repository
public class UserRepository {

	@Resource(name = "mongoTemplate")
	private MongoTemplate mongoTemplate;

	private static Logger logger = Logger.getLogger(UserRepository.class);

	@PostConstruct
	public void init() throws Exception {
		createCollection();
		logger.info("UserRepository init");
	}

	public String test() {
		StringBuilder sb = new StringBuilder();
		Set<String> colls = this.mongoTemplate.getCollectionNames();
		for (String coll : colls) {
			sb.append("CollectionName=" + coll + "<br>");
		}
		DB db = this.mongoTemplate.getDb();
		sb.append("db=" + db.toString() + "<br>");
		return sb.toString();
	}

	public void createCollection() {
		if (!this.mongoTemplate.collectionExists(User.class)) {
			this.mongoTemplate.createCollection(User.class);
		}
	}

	public List<User> findList(int skip, int limit) {
		Query query = new Query();
		query.with(new Sort(new Order(Direction.ASC, "id")));
		query.skip(skip).limit(limit);
		return this.mongoTemplate.find(query, User.class);
	}

	public List<User> findListByName(String name) {
		Query query = new Query();
		query.addCriteria(new Criteria("name").is(name));
		return this.mongoTemplate.find(query, User.class);
	}

	public List<User> findListByEmail(String email) {
		Query query = new Query();
		query.addCriteria(new Criteria("email").is(email));
		return this.mongoTemplate.find(query, User.class);
	}

	public User findOne(String email, String password) {
		Query query = new Query();
		query.addCriteria(new Criteria("email").is(email).and("password").is(password));
		return this.mongoTemplate.findOne(query, User.class);
	}

	public User findOne(String id) {
		Query query = new Query();
		query.addCriteria(new Criteria("_id").is(id));
		return this.mongoTemplate.findOne(query, User.class);
	}

	public void insert(User entity) {
		this.mongoTemplate.insert(entity);
	}
	public void save(User entity) {
		this.mongoTemplate.save(entity);
	}

	public void update(User entity) {
		Query query = new Query();
		query.addCriteria(new Criteria("_id").is(entity.getId()));
		Update update = new Update();
		update.set("birthday", entity.getBirthday());
		update.set("name", entity.getName());
		update.set("isAdmin", entity.getIsAdmin());
		update.set("password", entity.getPassword());
		update.set("confirmed", entity.getConfirmed());
		this.mongoTemplate.updateFirst(query, update, User.class);
	}
}
