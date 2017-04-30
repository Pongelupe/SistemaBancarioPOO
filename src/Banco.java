import java.util.Scanner;

public class Banco {

	private static double saldo = 0;
	private static double valor = 0;
	private static int cotas = 0;

	private static void menuContaCorrente(Pessoa p, ContaCorrente c) {
		System.out.println("\nBem vindo, " + p.getNome() + " ao sistema de sua Conta Corrente");
		System.out.println("\nMenu de op��es:");
		System.out.println("0 Sair\n1 Saldo\n2 Sacar\n3 Depositar\n4 Extrato\n"
				+ "5 Investimentos totais\n6 Opera��es com cheque\n"
				+ "9 Trocar de servi�os\n10 Adquirir novos servi�os\n11 Ver dados");
		System.out.print("Sua op��o: ");
		Scanner in = new Scanner(System.in);
		int opcao = in.nextInt();
		switch (opcao) {
		case 0:
			System.out.print("\nFim da aplica��o!");
			break;
		case 1:
			System.out.print("\nSeu saldo � de: " + c.getSaldo() + " reais");
			menuContaCorrente(p, c);
			break;
		case 2:
			try {
				System.out.print("\nInsira o valor a ser sacado: ");
				valor = in.nextDouble();
				c.sacar(valor);
			} catch (ExcecaoValorNegativo e) {

			} catch (ExcecaoSaldoInsuficiente e) {
			} finally {
				menuContaCorrente(p, c);
			}
			break;
		case 3:
			System.out.print("\nInsira o valor a ser depositado: ");
			valor = in.nextDouble();
			try {
				c.depositar(valor);
			} catch (ExcecaoValorNegativo e) {

			} finally {
				menuContaCorrente(p, c);
			}
			break;
		case 4:
			System.out.println("\nSeu extrato: ");
			c.imprimeExtrato();
			menuContaCorrente(p, c);
			break;
		case 5:
			System.out.println("\nSeus investimentos totais: " + c.investimentoTotal() + " reais");
			menuContaCorrente(p, c);
			break;
		case 6:
			System.out.println("\nEscolha sua opera��o com cheque: ");
			System.out.println("0 Solicitar novo talao\n1 Emitir um cheque\n2 Ver cheques emitidos");
			opcao = in.nextInt();
			switch (opcao) {
			case 0:
				try {
					if (c.solicitarNovoTalao()) {
						System.out.println("Mais um tal�o foi adicionado � Conta Corrente");
					} else {
						System.out.println("A transa��o foi negada");
					}
				} catch (ExcecaoSaldoInsuficiente e) {
				} finally {
					menuContaCorrente(p, c);
				}
				break;
			case 1:
				System.out.print("\nInsira o valor a ser pago com o cheque: ");
				valor = in.nextDouble();
				c.emitirCheque(valor);
				menuContaCorrente(p, c);
				break;
			case 2:
				System.out.print("\nSeus cheques j� emitidos: ");
				c.imprimeChequesUtilizados();
				menuContaCorrente(p, c);
				break;
			default:
				System.out.println("Op��o inv�lida");
				menuContaCorrente(p, c);
			}
			break;
		case 9:
			System.out.println("\nEscolha para qual servi�o deseja");
			System.out.println("0 Poupan�a\n1 Fundos de Investimentos");
			opcao = in.nextInt();
			switch (opcao) {
			case 0:
				if (p.getPoupanca() != null)
					menuPoupanca(p, p.getPoupanca());
				else {
					System.out.println("Contrate uma Poupan�a!");
					menuContaCorrente(p, c);
				}
				break;
			case 1:
				if (p.getFundosdeInvestimento() != null)
					menuFundos(p, p.getFundosdeInvestimento());
				else {
					System.out.println("Contrate um Fundo de investimento!");
					menuContaCorrente(p, c);
				}
				break;
			default:
				System.out.println("Op��o inv�lida");
				menuContaCorrente(p, c);
			}
			break;
		case 10:
			System.out.println("\nEscolha para qual servi�o deseja contratar");
			if (p.getPoupanca() != null && p.getFundosdeInvestimento() != null) {
				System.out.println("Todos servi�os contradados");
				menuContaCorrente(p, c);
			} else if (p.getPoupanca() != null && p.getFundosdeInvestimento() == null) {
				try {
					System.out.println("Fundo de investimentos:");
					System.out.print("\nInsira sua quantidade de cotas: ");
					cotas = in.nextInt();
					p.setFundosdeInvestimento(new FundosdeInvestimento(cotas, 80));
					System.out.println("Seu fundo de investimento foi criado");
				} catch (ExcecaoValorNegativo e) {

				} finally {
					menuContaCorrente(p, c);
				}
			} else if (p.getPoupanca() == null && p.getFundosdeInvestimento() != null) {
				try {
					System.out.println("\nPoupan�a: ");
					System.out.print("\nInsira seu saldo: ");
					saldo = in.nextDouble();
					p.setPoupanca(new Poupanca(saldo));
				} catch (ExcecaoValorNegativo e) {

				} finally {
					menuContaCorrente(p, c);
				}
			} else {
				System.out.println("0 Poupan�a\n1 Fundos de Investimentos");
				System.out.print("Sua op��o: ");
				opcao = in.nextInt();
				switch (opcao) {
				case 0:
					try {
						System.out.print("\nInsira seu saldo: ");
						saldo = in.nextDouble();
						p.setPoupanca(new Poupanca(saldo));
					} catch (ExcecaoValorNegativo e) {

					} finally {
						menuContaCorrente(p, c);
					}
					break;
				case 1:
					try {
						System.out.print("\nInsira sua quantidade de cotas: ");
						cotas = in.nextInt();
						p.setFundosdeInvestimento(new FundosdeInvestimento(cotas, 80));
					} catch (ExcecaoValorNegativo e) {

					} finally {
						menuContaCorrente(p, c);
					}
					break;
				default:
					System.out.println("Op��o inv�lida");
					menuContaCorrente(p, c);
				}
			}
		case 11:
			System.out.println(p);
			menuContaCorrente(p, c);
			break;
		default:
			System.out.println("Op��o inv�lida");
			menuContaCorrente(p, c);
		}
		in.close();
	};

	private static void menuPoupanca(Pessoa p, Poupanca poup) {
		System.out.println("\nBem vindo, " + p.getNome() + " ao sistema de sua Poupan�a");
		System.out.println("\nMenu de op��es:");
		System.out.println("0 Sair\n1 Saldo\n2 Sacar\n3 Depositar\n4 Extrato\n"
				+ "5 Investimentos totais\n6 Coeficiente de Rendimento\n"
				+ "9 Trocar de servi�os\n10 Adquirir novos servi�os\n11 Ver dados");
		System.out.print("Sua op��o: ");
		Scanner in = new Scanner(System.in);
		int opcao = in.nextInt();
		switch (opcao) {
		case 0:
			System.out.print("\nFim da aplica��o!");
			break;
		case 1:
			System.out.print("\nSeu saldo � de: " + poup.getSaldo() + " reais");
			menuPoupanca(p, poup);
			break;
		case 2:
			System.out.print("\nInsira o valor a ser sacado: ");
			valor = in.nextDouble();
			try {
				poup.sacar(valor);
			} catch (ExcecaoValorNegativo e) {

			} catch (ExcecaoSaldoInsuficiente e) {
			} finally {
				menuPoupanca(p, poup);
			}
			break;
		case 3:
			System.out.print("\nInsira o valor a ser depositado: ");
			valor = in.nextDouble();
			try {
				poup.depositar(valor);
			} catch (ExcecaoValorNegativo e) {

			} finally {
				menuPoupanca(p, poup);
			}
			break;
		case 4:
			System.out.println("\nSeu extrato: ");
			poup.imprimeExtrato();
			menuPoupanca(p, poup);
			break;
		case 5:
			System.out.println("\nSeus investimentos totais: " + poup.investimentoTotal() + " reais");
			menuPoupanca(p, poup);
			break;
		case 6:
			System.out.println("\nSeu coeficiente de rendimento: " + poup.getCoeficienteRendimento());
			menuPoupanca(p, poup);
			break;
		case 9:
			System.out.println("\nEscolha para qual servi�o deseja");
			System.out.println("0 Conta Corrente\n1 Fundos de Investimentos");
			opcao = in.nextInt();
			switch (opcao) {
			case 0:
				if (p.getContaCorrente() != null)
					menuPoupanca(p, p.getPoupanca());
				else {
					System.out.println("Contrate uma Conta Corrente!");
					menuPoupanca(p, poup);
				}
				break;
			case 1:
				if (p.getFundosdeInvestimento() != null)
					menuFundos(p, p.getFundosdeInvestimento());
				else {
					System.out.println("Contrate um Fundo de investimento!");
					menuPoupanca(p, poup);
				}
				break;
			default:
				System.out.println("Op��o inv�lida");
				menuPoupanca(p, poup);
			}
			break;
		case 10:
			System.out.println("\nEscolha para qual servi�o deseja contratar");
			if (p.getContaCorrente() != null && p.getFundosdeInvestimento() != null) {
				System.out.println("Todos servi�os contradados");
				menuPoupanca(p, poup);
			} else if (p.getContaCorrente() != null && p.getFundosdeInvestimento() == null) {
				try {
					System.out.println("Fundo de investimentos:");
					System.out.print("\nInsira sua quantidade de cotas: ");
					cotas = in.nextInt();
					p.setFundosdeInvestimento(new FundosdeInvestimento(cotas, 80));
					System.out.println("Seu fundo de investimento foi criado");
				} catch (ExcecaoValorNegativo e) {

				} finally {
					menuPoupanca(p, poup);
				}
			} else if (p.getContaCorrente() == null && p.getFundosdeInvestimento() != null) {
				try {
					System.out.println("\nConta Corrente: ");
					System.out.print("\nInsira seu saldo: ");
					saldo = in.nextDouble();
					p.setContaCorrente(new ContaCorrente(saldo));
					menuContaCorrente(p, p.getContaCorrente());
				} catch (ExcecaoValorNegativo e) {

					menuPoupanca(p, poup);
				}
			} else {
				System.out.println("0 Conta Corrente\n1 Fundos de Investimentos");
				System.out.print("Sua op��o: ");
				opcao = in.nextInt();
				switch (opcao) {
				case 0:
					try {
						System.out.print("\nInsira seu saldo: ");
						saldo = in.nextDouble();
						p.setContaCorrente(new ContaCorrente(saldo));
					} catch (ExcecaoValorNegativo e) {

					} finally {
						menuPoupanca(p, poup);
					}
					break;
				case 1:
					try {
						System.out.print("\nInsira sua quantidade de cotas: ");
						cotas = in.nextInt();
						p.setFundosdeInvestimento(new FundosdeInvestimento(cotas, 80));
					} catch (ExcecaoValorNegativo e) {

					} finally {
						menuPoupanca(p, poup);
					}
					break;
				case 11:
					System.out.println(p);
					menuPoupanca(p, poup);
					break;
				default:
					System.out.println("Op��o inv�lida");
					menuPoupanca(p, poup);
				}
			}

		default:
			System.out.println("Op��o inv�lida");
			menuPoupanca(p, poup);
		}
		in.close();
	};

	private static void menuFundos(Pessoa p, FundosdeInvestimento f) {
		System.out.println("\nBem vindo, " + p.getNome() + " ao sistema de seu Fundos de Investimentos");
		System.out.println("\nMenu de op��es:");
		System.out.println("0 Sair\n1 Saldo\n2 Sacar\n3 Depositar\n4 Extrato\n" + "5 Investimentos totais\n"
				+ "9 Trocar de servi�os\n10 Adquirir novos servi�os\n11 Ver dados");
		System.out.print("Sua op��o: ");
		Scanner in = new Scanner(System.in);
		int opcao = in.nextInt();
		switch (opcao) {
		case 0:
			System.out.print("\nFim da aplica��o!");
			break;
		case 1:
			System.out.print("\nSeu saldo � de: " + f.getSaldo() + " reais");
			menuFundos(p, f);
			break;
		case 2:
			try {
				System.out.print("\nInsira o valor a ser sacado: ");
				valor = in.nextDouble();
				f.sacar(valor);
			} catch (ExcecaoValorNegativo e) {

			} catch (ExcecaoSaldoInsuficiente e) {
			} finally {
				menuFundos(p, f);
			}
			break;
		case 3:
			try {
				System.out.print("\nInsira o valor a ser depositado: ");
				valor = in.nextDouble();
				f.depositarFundos(valor);
			} catch (ExcecaoValorNegativo e) {

			} catch (ExcecaoSaldoInsuficiente e) {
			} finally {
				menuFundos(p, f);
			}
			break;
		case 4:
			System.out.println("\nSeu extrato: ");
			f.imprimeExtrato();
			menuFundos(p, f);
			break;
		case 5:
			System.out.println("\nSeus investimentos totais: " + f.investimentoTotal() + " reais");
			menuFundos(p, f);
			break;
		case 9:
			System.out.println("\nEscolha para qual servi�o deseja");
			System.out.println("0 Conta Corrente\n1 Poupan�a");
			opcao = in.nextInt();
			switch (opcao) {
			case 0:
				if (p.getContaCorrente() != null)
					menuContaCorrente(p, p.getContaCorrente());
				else {
					System.out.println("Contrate uma Conta Corrente!");
					menuFundos(p, f);
				}
				break;
			case 1:
				if (p.getPoupanca() != null)
					menuPoupanca(p, p.getPoupanca());
				else {
					System.out.println("Contrate uma Poupan�a!");
					menuFundos(p, f);
				}
				break;
			default:
				System.out.println("Op��o inv�lida");
				menuFundos(p, f);
			}
			break;
		case 10:
			System.out.println("\nEscolha para qual servi�o deseja contratar");
			if (p.getContaCorrente() != null && p.getPoupanca() != null) {
				System.out.println("Todos servi�os contradados");
				menuFundos(p, f);
			} else if (p.getContaCorrente() != null && p.getPoupanca() == null) {
				try {
					System.out.println("Poupan�a:");
					System.out.print("\nInsira seu saldo: ");
					saldo = in.nextDouble();
					p.setPoupanca(new Poupanca(saldo));
				} catch (ExcecaoValorNegativo e) {

				} finally {
					menuFundos(p, f);
				}
			} else if (p.getContaCorrente() == null && p.getPoupanca() != null) {
				try {
					System.out.println("\nConta Corrente: ");
					System.out.print("\nInsira seu saldo: ");
					saldo = in.nextDouble();
					p.setContaCorrente(new ContaCorrente(saldo));
				} catch (ExcecaoValorNegativo e) {

				} finally {
					menuFundos(p, f);
				}
			} else {
				System.out.println("0 Conta Corrente\n1 Poupan�a");
				System.out.print("Sua op��o: ");
				opcao = in.nextInt();
				switch (opcao) {
				case 0:
					try {
						System.out.print("\nInsira seu saldo: ");
						saldo = in.nextDouble();
						p.setContaCorrente(new ContaCorrente(saldo));
					} catch (ExcecaoValorNegativo e) {

					} finally {
						menuFundos(p, f);
					}
					break;
				case 1:
					try {
						System.out.print("\nInsira seu saldo: ");
						saldo = in.nextDouble();
						p.setPoupanca(new Poupanca(saldo));
					} catch (ExcecaoValorNegativo e) {

					} finally {
						menuFundos(p, f);
					}
					break;
				default:
					System.out.println("Op��o inv�lida");
					menuFundos(p, f);
				}
			}

		case 11:
			System.out.println(p);
			menuFundos(p, f);
			break;
		default:
			System.out.println("Op��o inv�lida");
			menuFundos(p, f);
		}
		in.close();
	};

	private static void menu(Pessoa p) {
		System.out.println("Bem vindo, " + p.getNome());
		System.out.println("\nMenu de op��es:");
		System.out.println("0 Conta Corrente\n1 Poupan�a \n2 Fundos de Investimentos");
		System.out.print("Sua op��o: ");
		Scanner in = new Scanner(System.in);
		int opcao = in.nextInt();
		switch (opcao) {
		case 0:
			try {
				System.out.print("\nInsira seu saldo: ");
				saldo = in.nextDouble();
				p.setContaCorrente(new ContaCorrente(saldo));
				menuContaCorrente(p, p.getContaCorrente());
			} catch (ExcecaoValorNegativo e) {

				menu(p);
			}
			break;
		case 1:
			System.out.print("\nInsira seu saldo: ");
			saldo = in.nextDouble();
			try {
				p.setPoupanca(new Poupanca(saldo));
				menuPoupanca(p, p.getPoupanca());
			} catch (ExcecaoValorNegativo e) {

				menu(p);
			}
			break;
		case 2:
			try {
				System.out.print("\nInsira sua quantidade de cotas: ");
				cotas = in.nextInt();
				p.setFundosdeInvestimento(new FundosdeInvestimento(cotas, 80));
				menuFundos(p, p.getFundosdeInvestimento());
			} catch (ExcecaoValorNegativo e) {

				menu(p);
			}
			break;
		default:
			System.out.println("Op��o inv�lida");
			menu(p);
		}
		in.close();
	};

	public static void menu() {
		System.out.println("\nMenu de op��es do Cart�rio:\nRegistre:");
		System.out.println("0 Pessoa f�sica\n1 Pessoa jur�dica");
		System.out.print("Sua op��o: ");
		Scanner in = new Scanner(System.in);
		int opcao = in.nextInt();
		switch (opcao) {
		case 0:
			Fisica f = new Fisica("shulambs");
			in.nextLine();
			System.out.print("\nInsira o CPF: ");
			f.setCPF(in.nextLine());
			System.out.print("\nInsira o nome: ");
			f.setNome(in.nextLine());
			System.out.print("\nInsira o telefone: ");
			f.setTelefone(in.nextLine());
			System.out.print("\nInsira o endere�o: ");
			f.setEndereco(in.nextLine());
			menu(f);
			break;
		case 1:
			Juridica j = new Juridica("shulambs");
			in.nextLine();
			System.out.print("\nInsira o CNPJ: ");
			j.setCNPJ(in.nextLine());
			System.out.print("\nInsira o nome: ");
			j.setNome(in.nextLine());
			System.out.print("\nInsira o telefone: ");
			j.setTelefone(in.nextLine());
			System.out.print("\nInsira o endere�o: ");
			j.setEndereco(in.nextLine());
			menu(j);
			break;
		default:
			System.out.println("Op��o inv�lida");
			menu();
			break;
		}
		in.close();
	};

	public static void main(String[] args) {
		menu();
	}

}