package br.com.bruce.blog.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.bruce.blog.modelo.post;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<post, Long> {

	@Query("SELECT p FROM post p WHERE p.titulo LIKE %?1%")
	List<post> findPostByTitulo(String titulo);
}
