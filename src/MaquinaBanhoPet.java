public class MaquinaBanhoPet {
    private boolean isLivre;
    private int agua;
    private int shampoo;
    private Pet pet;

    public MaquinaBanhoPet() {
        this.isLivre = true;
        this.agua = 30;
        this.shampoo = 10;
        this.pet = null;
    }

    public void darBanhoNoPet(Pet pet) {
        if (this.agua < 10) {
            System.out.println("\nNível de água insuficiente para dar banho no pet " + pet.getNome() + ". Por favor, abasteça a água.");
            return;
        }

        if (this.shampoo < 2) {
            System.out.println("\nNível de shampoo insuficiente para dar banho no pet " + pet.getNome() + ". Por favor, abasteça o shampoo.");
            return;
        }

        if (this.isLivre) {
            System.out.println("\nO pet " + pet.getNome() + " foi colocado na máquina de banho.");
            this.isLivre = false;
            this.agua -= 10;
            this.shampoo -= 2;
            this.pet = pet;
            this.pet.setLimpo(true);
            System.out.println("Banho no pet " + pet.getNome() + " concluído!");
        } else {
            System.out.println("\nA máquina de banho está ocupada. Retire o pet atual antes de colocar outro pet.");
        }
    }

    public void abastecerAgua() {
        if (this.agua <= 28) {
            this.agua += 2;
            System.out.println("\nÁgua abastecida. Nível atual: " + this.agua);
        } else {
            System.out.println("\nO nível de água já está no máximo.");
        }
    }

    public void abastecerShampoo() {
        if (this.shampoo <= 8) {
            this.agua += 2;
            System.out.println("\nShampoo abastecido. Nível atual: " + this.agua);
        } else {
            System.out.println("\nO nível de shampoo já está no máximo.");
        }
    }

    public void verificarNivelAgua() {
        System.out.println("\nNível de água atual: " + this.agua);
    }

    public void verificarNivelShampoo() {
        System.out.println("\nNível de shampoo atual: " + this.shampoo);
    }

    public void verificarPetNaMaquina() {
        if (!this.isLivre) {
            System.out.println("\nO pet " + this.pet.getNome() + " está na máquina de banho.");
        } else {
            System.out.println("\nNão há nenhum pet na máquina de banho.");
        }
    }

    public void retirarPet() {
        if (!this.isLivre) {
            System.out.println("\nRetirando o pet " + this.pet.getNome() + " da máquina de banho...");
            System.out.println("Pet retirado da máquina de banho.");
            limparMaquina();
            this.isLivre = true;
            System.out.println("Máquina de banho está livre para o próximo pet.");
        } else {
            System.out.println("\nNão há nenhum pet para retirar da máquina de banho.");
        }
    }

    private void limparMaquina() {
        System.out.println("\nLimpeza da máquina de banho em andamento...");
        this.agua -= 3;
        this.shampoo -= 1;
        System.out.println("Máquina de banho limpa!");
    }
}
