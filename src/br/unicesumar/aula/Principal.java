package br.unicesumar.aula;

import br.unicesumar.aula.controle.ProjetoDAO;
import br.unicesumar.aula.controle.ProjetoImpl;
import br.unicesumar.aula.exceptions.DadoConsultadoException;
import br.unicesumar.aula.modelo.Projeto;

import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
	public static void main(String[] args) {
		ProjetoDAO dao = new ProjetoImpl();
		int opcao = 1000;
		do {
			System.out.println("Registro de projetos");
			System.out.println("1 - Cadastrar");
			System.out.println("2 - Alterar");
			System.out.println("3 - Listar");
			System.out.println("4 - Localizar por nome");
			System.out.println("5 - Excluir");
			System.out.println("\n0 - Sair");
			Scanner scannerMenu = new Scanner(System.in);

			try {
				opcao = scannerMenu.nextInt();
				switch (opcao) {
				case 1:
					// adicionar projeto
					Projeto projeto = new Projeto();
					projeto.setNome(coletarTexto("Digite o nome do projeto"));
					projeto.setObjetivo(coletarTexto("Digite os objetivos do projeto"));
					projeto.setNecessidades(coletarTexto("Digite as necessidades do projeto"));
					projeto.setDataInicio(coletarTexto("Digite a data de início do projeto"));
					projeto.setDataFinal(coletarTexto("Digite a data final do projeto"));
					projeto.setStatus(coletarTexto("Digite o status do projeto"));
					dao.adicionar(projeto);
					break;
				case 2:
					// alterar projeto
					String nomeConsultaAlterar = coletarTexto(
							"Digite o nome do projeto que está buscando para alterar");
					Projeto projetoNovo = new Projeto();
					projetoNovo.setNome(coletarTexto("Digite o nome do projeto"));
					projetoNovo.setObjetivo(coletarTexto("Digite os objetivos do projeto"));
					projetoNovo.setNecessidades(coletarTexto("Digite as necessidades do projeto"));
					projetoNovo.setDataInicio(coletarTexto("Digite a data de início do projeto"));
					projetoNovo.setDataFinal(coletarTexto("Digite a data final do projeto"));
					projetoNovo.setStatus(coletarTexto("Digite o status do projeto"));
					dao.alterar(nomeConsultaAlterar, projetoNovo);
					break;
				case 3:
					// listar projetos
					System.out.println(dao.listar());
					break;
				case 4:
					// consultar projeto por nome
					String nomeConsultado = coletarTexto("Digite o nome do projeto que está buscando");
					Projeto projetoEncontrado = dao.consultarPorNome(nomeConsultado);
					System.out.println(projetoEncontrado);
					break;
				case 5:
					// excluir projeto por nome
					String nomeConsultaExcluir = coletarTexto(
							"Digite o nome do projeto que está buscando para excluir");
					dao.excluir(nomeConsultaExcluir);
					break;
				case 0:
					break;
				default:
					System.out.println("opção invalida!");
					break;
				}

			} catch (InputMismatchException e) {
				System.out.println("Não foi possível registrar corretamente a sua opção.");
			} catch (ParseException | DateTimeParseException e) {
				System.out.println("Não foi possível converter a data, tente informar dd/mm/aaaa");
			} catch (DadoConsultadoException e) {
				System.out.println(e.getMessage());
			} catch (NullPointerException e) {
				System.out.println("Arquivo não existe!");
			}
			coletarTexto("----");
		} while (opcao != 0);
	}

	private static String coletarTexto(String descricao) {
		Scanner scanner = new Scanner(System.in);
		System.out.println(descricao);
		return scanner.nextLine();
	}
}