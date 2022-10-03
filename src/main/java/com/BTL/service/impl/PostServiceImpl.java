package com.BTL.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BTL.entity.Post;
import com.BTL.repo.PostRepository;
import com.BTL.service.PostService;

@Service
public class PostServiceImpl  implements PostService{

	@Autowired
	PostRepository postRepository;

	@Override
	public <S extends Post> S save(S entity) {
		return postRepository.save(entity);
	}

	@Override
	public List<Post> findAll() {
		return postRepository.findAll();
	}

	@Override
	public long count() {
		return postRepository.count();
	}

	@Override
	public void delete(Post entity) {
		postRepository.delete(entity);
	}

	@Override
	public Post getOne(Integer id) {
		return postRepository.getOne(id);
	}

	@Override
	public void deleteAll() {
		postRepository.deleteAll();
	}

	@Override
	public Post getById(Integer id) {
		return postRepository.getById(id);
	}
	
	
}
