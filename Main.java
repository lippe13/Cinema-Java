import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcaoMenu = 0;

        while (opcaoMenu != 3) {
            System.out.println("");
            System.out.println("APP INGRESSO.COM");
            System.out.println("[1] CADASTRO");
            System.out.println("[2] LOGIN");
            System.out.println("[3] SAIR");
            System.out.print("Escolha uma opção: ");
            opcaoMenu = sc.nextInt();
            sc.nextLine();  

            switch (opcaoMenu) {
                case 1: // Cadastro
                    int opcaoCadastro = 0;
                    System.out.println("");
                    System.out.println("CADASTRO");
                    System.out.println("[1] CADASTRO ADM");
                    System.out.println("[2] CADASTRO Cliente");
                    System.out.print("Escolha uma opção: ");
                    opcaoCadastro = sc.nextInt();
                    sc.nextLine();  

                    switch (opcaoCadastro) {
                        case 1: // Cadastrar ADM
                            System.out.print("NOME: ");
                            String nomeADM = sc.nextLine();
                            System.out.print("CPF: ");
                            String cpfADM = sc.nextLine();
                            System.out.print("SENHA: ");
                            String senhaADM = sc.nextLine();

                            ADM adm = new ADM(0, nomeADM, cpfADM, senhaADM);
                            if (ADMDAO.cadastrarADM(adm)) {
                                System.out.println("ADM cadastrado com sucesso!");
                            } else {
                                System.out.println("Erro");
                            }
                            break;

                        case 2: // Cadastrar Cliente
                            System.out.print("NOME: ");
                            String nomeCliente = sc.nextLine();
                            System.out.print("CPF: ");
                            String cpfCliente = sc.nextLine();
                            System.out.print("SENHA: ");
                            String senhaCliente = sc.nextLine();

                            Cliente cliente = new Cliente(0, nomeCliente, cpfCliente, senhaCliente);
                            if (ClienteDAO.cadastrarCliente(cliente)) {
                                System.out.println("Cliente cadastrado com sucesso!");
                            } else {
                                System.out.println("Erro");
                            }
                            break;

                        default:
                            System.out.println("Opção invalida, tente novamente");
                            break;
                    }
                    break;

                case 2: // Login
                    int opcaoLogin = 0;
                    System.out.println("");
                    System.out.println("LOGIN");
                    System.out.println("[1] LOGIN ADM");
                    System.out.println("[2] LOGIN Cliente");
                    System.out.print("Escolha uma opção: ");
                    opcaoLogin = sc.nextInt();
                    sc.nextLine();  

                    switch (opcaoLogin) {
                        case 1: // Logar como ADM
                            System.out.println("");
                            System.out.print("CPF: ");
                            String cpfLoginADM = sc.nextLine();
                            System.out.print("SENHA: ");
                            String senhaLoginADM = sc.nextLine();
                        
                            ADM admLogado = ADMDAO.logarADM(cpfLoginADM, senhaLoginADM);
                            if (admLogado != null) {
                                System.out.println("");
                                System.out.println("Login bem-sucedido! Bem-vindo, " + admLogado.getNome());
                                DashboardADM.exibirDashboard(admLogado);
                            } else {
                                System.out.println("");
                                System.out.println("CARGO/CPF/SENHA ERRADA");
                            }
                            break;

                        case 2: // Logar como Cliente
                            System.out.println("");
                            System.out.print("CPF: ");
                            String cpfLoginCliente = sc.nextLine();
                            System.out.print("SENHA ");
                            String senhaLoginCliente = sc.nextLine();

                            Cliente clienteLogado = ClienteDAO.logarCliente(cpfLoginCliente, senhaLoginCliente);
                            if (clienteLogado != null) {
                                System.out.println("");
                                System.out.println("Login bem-sucedido! Bem-vindo, " + clienteLogado.getNome());
                                DashboardCliente.exibirDashboardCliente(clienteLogado);
                            } else {
                                System.out.println("");
                                System.out.println("CARGO/CPF/SENHA ERRADA");
                            }
                            break;

                        default:
                            System.out.println("");
                            System.out.println("Opção invalida, tente novamente");
                    }
                    break;

                case 3:
                    System.out.println("");
                    System.out.println(":-)");
                    break;

                default:
                System.out.println("");
                System.out.println("Opção invalida, tente novamente");
                    break;
            }
        }
        sc.close();
    }

}
