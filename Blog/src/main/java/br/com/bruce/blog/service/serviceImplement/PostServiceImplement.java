package br.com.bruce.blog.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bruce.blog.modelo.post;
import br.com.bruce.blog.repository.PostRepository;
import br.com.bruce.blog.service.PostService;

@Service
public class PostServiceImplement implements PostService {

	@Autowired
	private PostRepository repository;

	@Override
	public List<post> findAll(String titulo) {
		return this.repository.findAll();
	}

	@Override
	public post findById(long id) {
		return this.repository.findById(id).get();
	}

	@Override
	public void save(post post) {
		this.repository.save(post);
	}

	@Override
	public void deleteById(long id) {
		this.repository.deleteById(id);
	}
}
