package br.com.postagem.Postagem.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.postagem.Postagem.models.Usuario;
import br.com.postagem.Postagem.repositories.UsuarioRepository;

@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public List<Usuario> buscar() {
		return usuarioRepository.findAll();
	}
	@PostMapping
	public Usuario cadastrar(@RequestBody Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}
	@PutMapping
	public ResponseEntity<Usuario> alterar(@RequestBody Usuario usuario) {
		Optional<Usuario> optionalUsuario = this.usuarioRepository.findById(usuario.getId());
		if(optionalUsuario.isEmpty()) {
			ResponseEntity.notFound().build();
		}
		usuarioRepository.save(usuario);
		return ResponseEntity.ok(usuario);
	}
	@PostMapping("/logar")
	public ResponseEntity<Usuario> logar(@RequestBody Usuario usuario) {
		Optional<Usuario> optionUsuario = this.usuarioRepository.findByLoginAndSenha(usuario.getLogin(), usuario.getSenha());
		if(optionUsuario.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(optionUsuario.get());
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> apagar(@PathVariable Long id) {
		Optional<Usuario> optionalUsuario = this.usuarioRepository.findById(id);
		if(optionalUsuario.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		usuarioRepository.delete(optionalUsuario.get());
		return ResponseEntity.ok(optionalUsuario.get());
	}
}
