package com.ihc.quadrinhosorientais.infra.emprestimo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihc.quadrinhosorientais.infra.emprestimo.exceptions.EmprestimoNaoEncontradoException;
import com.ihc.quadrinhosorientais.infra.emprestimotemquadrinho.EmprestimoTemQuadrinho;

@Service
public class EmprestimoService {

	@Autowired
	private EmprestimoRepository emprestimoRepository;

	public List<Emprestimo> listar() {
		return this.emprestimoRepository.findAll();
	}

	public Emprestimo buscarPorId(final Integer id) {
		final Optional<Emprestimo> emprestimoOptional = this.emprestimoRepository.findById(id);
		if (!emprestimoOptional.isPresent()) {
			throw new EmprestimoNaoEncontradoException();
		}
		return emprestimoOptional.get();
	}

	public Emprestimo criar(final Emprestimo emprestimo) {
		
		final List<EmprestimoTemQuadrinho> quadrinhos = emprestimo.getQuadrinhos();
		emprestimo.setQuadrinhos(Arrays.asList());
		final Emprestimo emprestimoSalvo = this.emprestimoRepository.save(emprestimo);
		
		final List<EmprestimoTemQuadrinho> quadrinhosEmprestados = new ArrayList<EmprestimoTemQuadrinho>();
		
		for (EmprestimoTemQuadrinho quadrinho : quadrinhos) {
			
			EmprestimoTemQuadrinho quadrinhoEmprestado = new EmprestimoTemQuadrinho(emprestimoSalvo, quadrinho.getQuadrinho());
			quadrinhosEmprestados.add(quadrinhoEmprestado);
			
		}

		emprestimoSalvo.setQuadrinhos(quadrinhosEmprestados);
		return this.emprestimoRepository.save(emprestimoSalvo);
	}

	public Emprestimo atualizar(final Integer id, final Emprestimo emprestimo) {
		final Emprestimo emprestimoSalvo = this.buscarPorId(id);
		BeanUtils.copyProperties(emprestimo, emprestimoSalvo, "id");
		return this.emprestimoRepository.save(emprestimoSalvo);
	}

	public void excluirPorId(final Integer id) {
		final Emprestimo emprestimoSalvo = this.buscarPorId(id);
		this.emprestimoRepository.delete(emprestimoSalvo);
	}

}