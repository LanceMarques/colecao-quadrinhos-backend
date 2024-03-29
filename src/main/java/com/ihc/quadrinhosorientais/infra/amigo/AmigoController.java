package com.ihc.quadrinhosorientais.infra.amigo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/amigos")
public class AmigoController {

  @Autowired
  private AmigoService amigoService;

  @GetMapping
  @CrossOrigin
  public ResponseEntity<List<Amigo>> listar() {

    final List<Amigo> amigos = this.amigoService.listar();
    return ResponseEntity.status(HttpStatus.OK).body(amigos);

  }

  @GetMapping("/id/{id}")
  @CrossOrigin
  public ResponseEntity<Amigo> buscarPorId(@PathVariable("id") final Integer id) {

    final Amigo amigoSalvo = this.amigoService.buscarPorId(id);
    return ResponseEntity.status(HttpStatus.OK).body(amigoSalvo);

  }

  @PostMapping
  @CrossOrigin
  public ResponseEntity<Amigo> criar(@RequestBody final Amigo amigo) {

    final Amigo amigoSalvo = this.amigoService.criar(amigo);
    return ResponseEntity.status(HttpStatus.CREATED).body(amigoSalvo);

  }

  @PutMapping("/id/{id}")
  @CrossOrigin
  public ResponseEntity<Object> atualizar(@PathVariable final Integer id,
      @RequestBody final Amigo amigo) {

    this.amigoService.atualizar(id, amigo);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

  }

  @DeleteMapping("/id/{id}")
  @CrossOrigin
  public ResponseEntity<Object> excluir(@PathVariable final Integer id) {

    this.amigoService.excluirPorId(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

  }

}
