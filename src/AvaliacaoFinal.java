import java.util.Locale;
import java.util.Scanner;

public class AvaliacaoFinal {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

//		Quantidade de clientes
		System.out.print("Informe a quantidade de clientes: ");
		int N = sc.nextInt();
		System.out.println();

//		Leitura dos dados da tabela

		String[] nome = new String[N];
		String[] telefone = new String[N];
		int[] tipo = new int[N];
		int[] minutos = new int[N];
		double[] valorConta = new double[N];
		int ordemCliente = 0;
		for (int i = 0; i < N; i++) {
			ordemCliente = i + 1;
			System.out.println("Dados do " + ordemCliente + "o. cliente:");
			sc.nextLine();
			System.out.print("Nome: ");
			nome[i] = sc.nextLine();
			System.out.print("Telefone: ");
			telefone[i] = sc.next();
			System.out.print("Tipo: ");
			tipo[i] = sc.nextInt();
			System.out.print("Minutos: ");
			minutos[i] = sc.nextInt();
			System.out.println();
		}
//		leitura dos precos básicos de assinatura tipo 0, 1 e 2

		System.out.println("Informe o preco basico e excedente de cada tipo de conta: ");
		double[][] valorAssinatura = new double[3][2];
		for (int i = 0; i < 3; i++) {
			System.out.print("Tipo " + i + ": ");
			for (int j = 0; j < 2; j++) {
				valorAssinatura[i][j] = sc.nextDouble();
			}
//				
		}
//		Calculo do valor da conta
		System.out.println();
		for (int i = 0; i < N; i++) {
			if (minutos[i] <= 90) {
				if (tipo[i] == 0) {
					valorConta[i] = valorAssinatura[0][0];
				} else if (tipo[i] == 1) {
					valorConta[i] = valorAssinatura[1][0];
				} else {
					valorConta[i] = valorAssinatura[2][0];
				}
			} else {
				if (tipo[i] == 0) {
					valorConta[i] = valorAssinatura[0][0] + (minutos[i] - 90) * valorAssinatura[0][1];
				} else if (tipo[i] == 1) {
					valorConta[i] = valorAssinatura[1][0] + (minutos[i] - 90) * valorAssinatura[1][1];
				} else {
					valorConta[i] = valorAssinatura[2][0] + (minutos[i] - 90) * valorAssinatura[2][1];
				}
			}
		}
//		Calculo da receita total
		double receita = 0;
		for (int i = 0; i < N; i++) {
			receita += valorConta[i];
		}

//		Calculo consumo Tipo 1
		int media = 0, somaMinutos = 0;
		int contagem1 = 0;
		for (int i = 0; i < N; i++) {
			if (tipo[i] == 1) {
				contagem1++;
				somaMinutos += minutos[i];
				media = somaMinutos / contagem1;
			}
		}

//		Calculo de número de clientes que consumiu acima de 120 minutos
		int contagemAcima = 0;
		for (int i = 0; i < N; i++) {
			if (minutos[i] > 120) {
				contagemAcima++;
			}
		}

//		Calculo da porcentagem de clientes Tipo 2
		double porcentTipo2 = 0;
		double contagemTipo2 = 0;
		for (int i = 0; i < N; i++) {
			if (tipo[i] == 2) {
				contagemTipo2++;
			}
			porcentTipo2 = (contagemTipo2 * 100) / N;
		}
//		Menu
		int opcao = 0;
		while (opcao != 7) {
			System.out.println("MENU DE OPCOES:");
			System.out.println("1) Relatorio de clientes");
			System.out.println("2) A receita total");
			System.out.println("3) Conta foi mais barata");
			System.out.println("4) Consumo medio de clientes tipo 1.");
			System.out.println("5) Clientes que consumiram acima de 120 minutos.");
			System.out.println("6) A porcentagem de clientes tipo 2");
			System.out.println("7) Sair");
			System.out.print("Informe uma opcao: ");
			opcao = sc.nextInt();
			System.out.println();

			if (opcao == 1) {
				for (int i = 0; i < N; i++) {
					System.out.print(nome[i] + ", ");
					System.out.print(telefone[i] + ", ");
					System.out.print("Tipo " + tipo[i] + ", ");
					System.out.print("Minutos: " + minutos[i] + ", ");
					System.out.printf("Conta = R$ %.2f%n", valorConta[i]);
				}
				System.out.println();
			} else if (opcao == 2) {
				System.out.printf("Receita da Empresa Telefônica: R$ %.2f%n", receita);
				System.out.println();

			} else if (opcao == 3) {
				double menorConta = Integer.MAX_VALUE;
				String nomeUsuario = null, numeroUsuario = null;
				for (int i = 0; i < N; i++) {
					if (valorConta[i] < menorConta) {
						menorConta = valorConta[i];
						nomeUsuario = nome[i];
						numeroUsuario = telefone[i];
					}
				}
				System.out.println("Usuário que menos consumiu: " + nomeUsuario + ", " + numeroUsuario);
				System.out.println();
			} else if (opcao == 4) {
				System.out.println("Media de minutos consumidos por clientes do plano Tipo 1: " + media);
				System.out.println();
			} else if (opcao == 5) {
				System.out.println("Quantidade de clientes que consumiram acima de 120 minutos: " + contagemAcima);
				System.out.println();
			} else if (opcao == 6) {
				System.out.printf("Porcentagem de clientes tipo 2: %.1f%%%n", porcentTipo2);
				System.out.println();
			} else {
				System.out.println("FIM DO PROGRAMA! ");
			}

		}

		sc.close();

	}

}
