package br.com.bruce.blog.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.bruce.blog.modelo.Comentario;
import br.com.bruce.blog.modelo.post;

public interface ComentarioRepository extends CrudRepository<Comentario, Long>{

	  Iterable<Comentario> findByPost(post post);
}
