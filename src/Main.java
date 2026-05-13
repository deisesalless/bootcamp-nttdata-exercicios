import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        ContaBancaria conta = new ContaBancaria(new BigDecimal("600.00"));
        conta.menu();

        PetShop petShop = new PetShop();
        petShop.menu();

    }
}