package br.edu.fema.publicacaoTag.domain;


import br.edu.fema.publicacao.domain.Publicacao;
import br.edu.fema.publicacaoTag.dto.PublicacaoTagCadastroDTO;
import br.edu.fema.tag.domain.Tag;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "publicacoes_tags")
public class PublicacaoTag {
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_publicacao")
    private Publicacao publicacao;
    
    @ManyToOne
    @JoinColumn(name = "id_tag")
    private Tag tag;
    
    public PublicacaoTag(PublicacaoTagCadastroDTO publicacaoTag) {
    	this.publicacao = new Publicacao();
    	this.publicacao = publicacaoTag.getPublicacao();
    	this.tag = new Tag();
    	if(publicacaoTag.getTag().getId() != null) {
    		this.tag.setId(publicacaoTag.getTag().getId());
    	} 
    	this.tag.setNome(publicacaoTag.getTag().getNome());
    }
}
