import java.util.Scanner;

public class PetShop {
    private MaquinaBanhoPet maquinaBanhoPet;

    public PetShop() {
        this.maquinaBanhoPet = new MaquinaBanhoPet();
    }

    public void menu() {
        Scanner entrada = new Scanner(System.in);
        byte opcao;
        do {
            System.out.println("\nBem vindo ao Menu do PetShop");
            System.out.println("1 - Dar banho no pet");
            System.out.println("2 - Abastecer a agua da máquina de banho");
            System.out.println("3 - Abastecer o shampoo da máquina de banho");
            System.out.println("4 - Verificar o nivel de água da máquina de banho");
            System.out.println("5 - Verificar o nivel de shampoo da máquina de banho");
            System.out.println("6 - Verificar se há um pet na máquina de banho");
            System.out.println("7 - Retirar pet da máquina de banho");
            System.out.println("8 - Sair");
            System.out.println("Digite a opção desejada: ");
            opcao = entrada.nextByte();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do pet para dar banho: ");
                    String nomePet = entrada.next();
                    maquinaBanhoPet.darBanhoNoPet(new Pet(nomePet));
                    break;
                case 2:
                    System.out.println("Abastecendo a água da máquina de banho...");
                    maquinaBanhoPet.abastecerAgua();
                    break;
                case 3:
                    System.out.println("Abastecendo o shampoo da máquina de banho...");
                    maquinaBanhoPet.abastecerShampoo();
                    break;
                case 4:
                    System.out.println("Verificando o nível de água da máquina de banho...");
                    maquinaBanhoPet.verificarNivelAgua();
                    break;
                case 5:
                    System.out.println("Verificando o nível de shampoo da máquina de banho...");
                    maquinaBanhoPet.verificarNivelShampoo();
                    break;
                case 6:
                    System.out.println("Verificando se há um pet na máquina de banho...");
                    maquinaBanhoPet.verificarPetNaMaquina();
                    break;
                case 7:
                    System.out.println("Retirando o pet da máquina de banho...");
                    maquinaBanhoPet.retirarPet();
                    break;
                case 8:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 8);
    }
}
