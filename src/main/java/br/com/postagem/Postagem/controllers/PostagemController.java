package br.com.postagem.Postagem.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.postagem.Postagem.models.Postagem;
import br.com.postagem.Postagem.repositories.PostagemRepository;

@RestController
@RequestMapping("/postagem")
public class PostagemController {

	@Autowired
	PostagemRepository postagemRepository;
	
	@GetMapping
	public List<Postagem> buscar() {
		return postagemRepository.findAll(); 
	}
	@PostMapping
	public Postagem cadastrar(@RequestBody Postagem postagem) {
		return postagemRepository.save(postagem);
	}
	@PutMapping
	public ResponseEntity<Postagem> alterar(@RequestBody Postagem postagem) {
		Optional<Postagem> optionalPostagem = postagemRepository.findById(postagem.getId());
		if(optionalPostagem.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		postagemRepository.save(postagem);
		return ResponseEntity.ok(postagem);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Postagem> apagar(@PathVariable Long id) {
		Optional<Postagem> optionalPostagem = postagemRepository.findById(id);
		if(optionalPostagem.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		postagemRepository.delete(optionalPostagem.get());
		return ResponseEntity.ok(optionalPostagem.get());
	}
}
