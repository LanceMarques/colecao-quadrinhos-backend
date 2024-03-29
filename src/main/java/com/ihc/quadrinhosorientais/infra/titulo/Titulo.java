package com.ihc.quadrinhosorientais.infra.titulo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ihc.quadrinhosorientais.infra.quadrinho.Quadrinho;
import com.ihc.quadrinhosorientais.infra.quadrinho.enums.TipoQuadrinho;
import com.ihc.quadrinhosorientais.infra.titulo.enums.EstadoColecao;

@Entity
@Table(name = "TITULO")
public class Titulo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Size(min = 3, max = 20)
  @Column(name = "NOME")
  @NotNull
  private String titulo;
  
  @NotNull
  @Column(name = "EDITORA")
  private String editora;

  @Size(min = 10, max = 300)
  @Column(name = "URL_IMAGEM")
  private String urlImagem;

  @Column(name = "AVALIACAO")
  private Integer avaliacao;

  @Column(name = "ESTADO_COLECAO")
  @Enumerated(EnumType.STRING)
  
  private EstadoColecao estadoColecao;

  @Column(name = "TIPO")
  @NotNull
  @Enumerated(EnumType.STRING)
  private TipoQuadrinho tipoQuadrinho;

  @OneToMany(mappedBy = "titulo")
  @JsonIgnore
  private List<Quadrinho> quadrinhos = new ArrayList<Quadrinho>();

  public Titulo() {
    super();
  }

  public Titulo(Integer id, String nome, String editora, String urlImagem, Integer avaliacao,
      EstadoColecao estadoColecao, TipoQuadrinho tipoQuadrinho) {
    super();
    this.id = id;
    this.titulo = nome;
    this.editora = editora;
    this.urlImagem = urlImagem;
    this.avaliacao = avaliacao;
    this.estadoColecao = estadoColecao;
    this.tipoQuadrinho = tipoQuadrinho;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }



  public String getUrlImagem() {
    return urlImagem;
  }

  public void setUrlImagem(String urlImagem) {
    this.urlImagem = urlImagem;
  }

  public Integer getAvaliacao() {
    return avaliacao;
  }

  public void setAvaliacao(Integer avaliacao) {
    this.avaliacao = avaliacao;
  }

  public EstadoColecao getEstadoColecao() {
    return estadoColecao;
  }

  public void setEstadoColecao(EstadoColecao estadoColecao) {
    this.estadoColecao = estadoColecao;
  }

  public TipoQuadrinho getTipoQuadrinho() {
    return tipoQuadrinho;
  }

  public void setTipoQuadrinho(TipoQuadrinho tipoQuadrinho) {
    this.tipoQuadrinho = tipoQuadrinho;
  }

  public String getEditora() {
    return editora;
  }

  public void setEditora(String editora) {
    this.editora = editora;
  }

  public List<Quadrinho> getQuadrinhos() {
    return quadrinhos;
  }

  public void setQuadrinhos(List<Quadrinho> quadrinhos) {
    this.quadrinhos = quadrinhos;
  }

  @JsonIgnore
  public boolean temQuadrinhoAssociado() {

    return !this.quadrinhos.isEmpty();

  }

}
