package com.BTL.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.BTL.entity.Post;


public interface PostService {

	Post getById(Integer id);

	void deleteAll();

	Post getOne(Integer id);

	void delete(Post entity);

	long count();

	List<Post> findAll();

	<S extends Post> S save(S entity);

}
