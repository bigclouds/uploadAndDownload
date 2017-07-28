package com.store.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.store.model.User;

public interface UserDao extends PagingAndSortingRepository<User, String> {
        User findByEmail(String email);
}
