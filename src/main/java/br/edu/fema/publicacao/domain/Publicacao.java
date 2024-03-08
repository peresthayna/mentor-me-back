package br.edu.fema.publicacao.domain;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.fema.publicacao.dto.PublicacaoCadastroDTO;
import br.edu.fema.publicacaoTag.domain.PublicacaoTag;
import br.edu.fema.usuario.domain.Usuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "publicacoes")
public class Publicacao {
	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "titulo")
    private String titulo;
    
    @Column(name = "publicacao", columnDefinition = "TEXT")
    private String publicacao;
    
    @Column(name = "data_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;
    
    @Column(name = "data_fim")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFim;
   
    @Column(name = "visualizacoes")
    private Integer visualizacoes;
    
    @OneToMany(mappedBy = "publicacao", cascade = CascadeType.ALL)
    private List<PublicacaoTag> publicacaoTags;
    
    public Publicacao(PublicacaoCadastroDTO publicacaoCadastroDTO) {
    	if(publicacaoCadastroDTO == null) {
    		return;
    	}
    	this.id = publicacaoCadastroDTO.getId();
    	this.usuario = new Usuario();
    	this.usuario.setId(publicacaoCadastroDTO.getIdUsuario());
    	this.usuario.setTotalPublicacoes(this.usuario.getTotalPublicacoes()+1);
    	this.titulo = publicacaoCadastroDTO.getTitulo();
    	this.publicacao = publicacaoCadastroDTO.getPublicacao();
    	this.dataInicio = Date.from(LocalDateTime.now().toInstant(ZoneOffset.of("-03:00")));
    	this.visualizacoes = publicacaoCadastroDTO.getVisualizacoes();
    	this.publicacaoTags = new ArrayList<>();
    	publicacaoCadastroDTO.getPublicacaoTags().forEach(pt -> this.publicacaoTags.add(new PublicacaoTag(pt)));
    }
}
