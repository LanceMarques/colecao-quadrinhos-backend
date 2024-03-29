package com.ihc.quadrinhosorientais.infra.quadrinho;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ihc.quadrinhosorientais.infra.quadrinho.exceptions.QuadrinhoEmprestimoVinculadoException;
import com.ihc.quadrinhosorientais.infra.quadrinho.exceptions.QuadrinhoNaoEncontradoException;

@Service
public class QuadrinhoService {

  @Autowired
  private QuadrinhoRepository quadrinhoRepository;

  public List<Quadrinho> listarPorTitulo(final Integer id) {
    return this.quadrinhoRepository.findAllByTitulo_Id(id);
  }

  public Quadrinho buscarPorId(final Integer id) {
    final Optional<Quadrinho> quadrinhoOptional = this.quadrinhoRepository.findById(id);
    if (!quadrinhoOptional.isPresent()) {
      throw new QuadrinhoNaoEncontradoException();
    }
    return quadrinhoOptional.get();
  }

  public Quadrinho criar(final Quadrinho quadrinho) {
    return this.quadrinhoRepository.save(quadrinho);
  }

  public Quadrinho atualizar(final Integer id, final Quadrinho quadrinho) {
    final Quadrinho quadrinhoSalvo = this.buscarPorId(id);
    BeanUtils.copyProperties(quadrinho, quadrinhoSalvo, "id");
    return this.quadrinhoRepository.save(quadrinhoSalvo);
  }

  public void excluirPorId(final Integer id) {
    final Quadrinho quadrinhoSalvo = this.buscarPorId(id);

    if (quadrinhoSalvo.temEmprestimoVinculado()) {

      throw new QuadrinhoEmprestimoVinculadoException();

    }

    this.quadrinhoRepository.delete(quadrinhoSalvo);
  }

}
