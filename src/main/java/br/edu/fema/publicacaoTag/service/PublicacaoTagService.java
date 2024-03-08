package br.edu.fema.publicacaoTag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fema.publicacaoTag.domain.PublicacaoTag;
import br.edu.fema.publicacaoTag.dto.PublicacaoTagCadastroDTO;
import br.edu.fema.publicacaoTag.repository.PublicacaoTagRepository;

@Service
public class PublicacaoTagService {
	@Autowired
	private PublicacaoTagRepository publicacaoTagRepository;
	
	public List<PublicacaoTag> recuperarTodosPaginados() {
		return this.publicacaoTagRepository.findAll();
	}
	
	public PublicacaoTag recuperarPorId(Long id) {
		return this.publicacaoTagRepository.findById(id).orElse(null);
	}
	
	public PublicacaoTag salvar(PublicacaoTagCadastroDTO publicacaoTag) {
		return this.publicacaoTagRepository.save(new PublicacaoTag(publicacaoTag));
	}
}
