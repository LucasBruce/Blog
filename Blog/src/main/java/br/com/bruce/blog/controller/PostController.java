package br.com.bruce.blog.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.bruce.blog.modelo.Comentario;
import br.com.bruce.blog.modelo.post;
import br.com.bruce.blog.repository.ComentarioRepository;
import br.com.bruce.blog.repository.PostRepository;
import br.com.bruce.blog.service.PostService;

@Controller
public class PostController {
    
	@Autowired
	private ComentarioRepository comentarioRep;
	
	@Autowired
	private PostService service;
	
	@Autowired
	private PostRepository repository;

	
	@GetMapping("/")
	public ModelAndView getArtigoPost() {
		ModelAndView mav = new ModelAndView("ArtigoPost");
		List<post> postes = this.service.findAll(null);
		mav.addObject("posts",postes);
		return mav;
	}
	
	@GetMapping("/post")
	public ModelAndView getPost() {
		ModelAndView mav = new ModelAndView("post");
		List<post> postes = this.service.findAll(null);
		mav.addObject("posts",postes);
		return mav;
	}

	@GetMapping("/novoPost")
	public String novoPost(Model model) {
		post post = new post();
		model.addAttribute("post", post);
		return "novoPost";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("post") post post) {		 
		post.setData(LocalDate.now());
		this.repository.save(post);
		return "redirect:/";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable(value = "id") long id, Model model) {
		post post = this.repository.findById(id).get();
		model.addAttribute("post", post);
		return "editarPost";
	}

	 @GetMapping("/post/{id}")
	    public ModelAndView getDetailsPost(@PathVariable("id") long id){
		    ModelAndView mav = new ModelAndView("detailsPost");
	        post post = this.repository.findById(id).get();
	        mav.addObject("post", post);
	        
	        Iterable<Comentario> comentarios = this.comentarioRep.findByPost(post);
	        mav.addObject("comentarios",comentarios);
	        return mav;
	    }
	 
	 @GetMapping("/posts/{id}")
	    public ModelAndView getDetailsPost1(@PathVariable("id") long id){
		    ModelAndView mav = new ModelAndView("detailsPost1");
	        post post = this.repository.findById(id).get();
	        mav.addObject("post", post);
	        
	        Iterable<Comentario> comentarios = this.comentarioRep.findByPost(post);
	        mav.addObject("comentarios",comentarios);
	        return mav;
	    }
	 @RequestMapping(value="/post/{id}", method=RequestMethod.POST)
	    public String DetailsPost(@PathVariable("id") long id, Comentario comentario){
		 post post = this.repository.findById(id).get();  
		    comentario.setPost(post);
		    comentario.setData(LocalDate.now());
		    this.comentarioRep.save(comentario);
	        return "redirect:/post/{id}";
	    }
	 
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(value = "id") long id) {
		this.repository.deleteById(id);
		return "redirect:/";
	}
	
	@PostMapping("/pesquisar")
	public ModelAndView pesquisar(@RequestParam("pesquisa") String pesquisa) {
		ModelAndView mav = new ModelAndView("ArtigoPost");
		mav.addObject("posts", this.repository.findPostByTitulo(pesquisa) );
		mav.addObject("post", new post());
		return mav;
	}
	
	@PostMapping("/pesquisar1")
	public ModelAndView pesquisarArtigo(@RequestParam("pesquisa") String pesquisa) {
		ModelAndView mav = new ModelAndView("post");
		mav.addObject("posts", this.repository.findPostByTitulo(pesquisa) );
		mav.addObject("post", new post());
		return mav;
	}
	
	@RequestMapping(value="/excluir/{id}", method=RequestMethod.GET)
	public String deleteComent(@PathVariable(value = "id") long id) {
		this.comentarioRep.deleteById(id);
		return "redirect:/post";
	}
	

}
