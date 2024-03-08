package br.edu.fema.tag.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fema.tag.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>{
	List<Tag> findByNomeContaining(String nome);
	Page<Tag> findAll(Pageable pageable);
	Page<Tag> findAllByOrderByNomeAsc(Pageable pageable);
	Page<Tag> findAllByOrderByNumeroPublicacoesDesc(Pageable pageable);
}
