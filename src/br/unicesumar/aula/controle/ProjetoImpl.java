package br.unicesumar.aula.controle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.unicesumar.aula.exceptions.DadoConsultadoException;
import br.unicesumar.aula.modelo.Projeto;

public class ProjetoImpl implements ProjetoDAO {
	// Collection que irá armazenar todos os projetos
	private static Set<Projeto> projetos = new HashSet<>();

	@Override
	public void adicionar(Projeto projeto) {
		projetos.add(projeto);
		System.out.println("projeto arquivado com sucesso!");
	}

	@Override
	public List<Projeto> listar() {
		List<Projeto> lista = new ArrayList<>();
		for (Projeto p : projetos) {
			lista.add(p);
		}
		return lista;
	}

	@Override
	public Projeto consultarPorNome(String nome) throws DadoConsultadoException, NullPointerException {
		Projeto projeto = new Projeto();
		for (Projeto p : projetos) {
			if (p.getNome().equals(nome)) {
				projeto = p;

			}
		}

		return projeto;
	}

	@Override
	public Projeto alterar(String nome, Projeto projeto) throws DadoConsultadoException {
		for (Projeto p : projetos) {
			if (p.getNome().equals(nome)) {
				p.substituir(projeto);
			}
		}

		return projeto;
	}

	@Override
	public void excluir(Projeto projeto) throws DadoConsultadoException, UnsupportedOperationException {
		projetos.remove(projeto);

	}

	@Override
	public void excluir(String nome) throws DadoConsultadoException, UnsupportedOperationException {

		for (Projeto p : projetos) {
			if (p.getNome().equals(nome)) {
				excluir(p);
				System.out.println("Projeto removido com sucesso!");
			} else {
				System.out.println("Não existe projeto com este nome.");
			}
		}

	}

}