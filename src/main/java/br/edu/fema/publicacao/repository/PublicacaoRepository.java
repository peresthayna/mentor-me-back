package br.edu.fema.publicacao.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fema.publicacao.domain.Publicacao;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {
	Page<Publicacao> findByPublicacaoContaining(Pageable pageable, String publicacao);
	Page<Publicacao> findAllByOrderByDataInicioDesc(Pageable pageable);
	//mostrar as sem respostas
}
