package br.com.bruce.blog.service;

import java.util.List;

import br.com.bruce.blog.modelo.post;

public interface PostService {

	List<post> findAll(String titulo);

	post findById(long id);

	void save(post post);

	void deleteById(long id);
}
