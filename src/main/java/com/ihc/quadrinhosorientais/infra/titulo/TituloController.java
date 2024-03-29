package com.ihc.quadrinhosorientais.infra.titulo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/titulos")
@Controller
public class TituloController {

  @Autowired
  private TituloService tituloService;

  @GetMapping
  @CrossOrigin
  public ResponseEntity<List<Titulo>> listar() {

    final List<Titulo> titulos = this.tituloService.listar();
    return ResponseEntity.status(HttpStatus.OK).body(titulos);

  }

  @GetMapping("/id/{id}")
  @CrossOrigin
  public ResponseEntity<Titulo> buscarPorId(@PathVariable("id") final Integer id) {

    final Titulo tituloSalvo = this.tituloService.buscarPorId(id);
    return ResponseEntity.status(HttpStatus.OK).body(tituloSalvo);

  }

  @PostMapping
  @CrossOrigin
  public ResponseEntity<Titulo> criar(@RequestBody final Titulo titulo) {

    final Titulo tituloSalvo = this.tituloService.criar(titulo);
    return ResponseEntity.status(HttpStatus.CREATED).body(tituloSalvo);

  }

  @PutMapping("/id/{id}")
  @CrossOrigin
  public ResponseEntity<Object> atualizar(@PathVariable final Integer id,
      @RequestBody final Titulo titulo) {

    this.tituloService.atualizar(id, titulo);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

  }

  @PatchMapping("/id/{id}/avaliacao/{avaliacao}")
  @CrossOrigin
  public ResponseEntity<?> avaliar(@PathVariable final Integer id,
      @PathVariable final Integer avaliacao) {
    this.tituloService.avaliar(id, avaliacao);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

  }

  @DeleteMapping("/id/{id}")
  @CrossOrigin
  public ResponseEntity<?> excluir(@PathVariable final Integer id) {

    this.tituloService.excluirPorId(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

  }

}
