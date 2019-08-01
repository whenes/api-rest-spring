package br.com.postagem.Postagem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.postagem.Postagem.models.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {

}
