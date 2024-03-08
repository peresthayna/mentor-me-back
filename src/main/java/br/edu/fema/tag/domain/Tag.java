package br.edu.fema.tag.domain;

import java.util.Date;
import java.util.List;

import br.edu.fema.publicacaoTag.domain.PublicacaoTag;
import br.edu.fema.tag.dto.TagCadastroDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tags")
public class Tag {
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;
    
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    
    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private List<PublicacaoTag> publicacaoTags;
    
    @Column(name = "numero_publicacoes")
    private int numeroPublicacoes;
    
    public Tag(TagCadastroDTO tagCadastro) {
    	this.nome = tagCadastro.getNome();
    }

}
