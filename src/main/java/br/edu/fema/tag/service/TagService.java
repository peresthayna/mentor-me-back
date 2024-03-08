package br.edu.fema.tag.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.edu.fema.generics.PaginationRequest;
import br.edu.fema.tag.domain.Tag;
import br.edu.fema.tag.repository.TagRepository;

@Service
public class TagService {
	@Autowired
	private TagRepository tagRepository;
	
	public Page<Tag> recuperarTodosPorData(PaginationRequest request) {
		return this.tagRepository.findAll(request.toPageable());
	}
	
	public Page<Tag> recuperarTodosPorOrdemAlfabetica(PaginationRequest request) {
		return this.tagRepository.findAllByOrderByNomeAsc(request.toPageable());
	}
	
	public Page<Tag> recuperarTodosPorOrdemDeQuantidade(PaginationRequest request) {
		return this.tagRepository.findAllByOrderByNumeroPublicacoesDesc(request.toPageable());
	}
	
	public List<Tag> buscarPorNome(String nome) {
		return this.tagRepository.findByNomeContaining(nome);
	}

	public Tag recuperarPorId(Long id) {
		return this.tagRepository.findById(id).orElse(null);
	}
	
	public Tag salvar(Tag tag) {
		if(tag.getId() != null) {
			if(this.tagRepository.findById(tag.getId()).isPresent()) {
				return this.atualizar(tag.getId(), tag);
			} else {
				tag.setNumeroPublicacoes(tag.getNumeroPublicacoes()+1);
				tag.setData(new Date());
				return this.tagRepository.save(tag);
			}
		} else {
			tag.setNumeroPublicacoes(tag.getNumeroPublicacoes()+1);
			tag.setData(new Date());
			return this.tagRepository.save(tag);
		}
	}
	
	public Tag atualizar(Long id, Tag tag) {
		tag.setId(id);
		tag.setData(new Date());
		tag.setNumeroPublicacoes(tag.getNumeroPublicacoes()+1);
		return this.tagRepository.save(tag);
	}
	
	public void deletar(Long id) {
		Optional<Tag> tag = this.tagRepository.findById(id);
		if(tag.isPresent()) {
			this.tagRepository.delete(tag.get());
		}
	}
}
