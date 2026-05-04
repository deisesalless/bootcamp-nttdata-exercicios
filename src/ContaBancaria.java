import java.math.BigDecimal;
import java.util.Scanner;

public class ContaBancaria {
    private BigDecimal saldoConta;
    private BigDecimal limiteChequeEspecial;
    private BigDecimal saldoEmUsoChequeEspecial;
    private BigDecimal taxaChequeEspecial;

    public ContaBancaria(BigDecimal depositoInicial) {
        this.saldoConta = depositoInicial;
        this.limiteChequeEspecial = implantarLimiteChequeEspecial(depositoInicial);
        saldoConta = saldoConta.add(limiteChequeEspecial);
        saldoEmUsoChequeEspecial = BigDecimal.ZERO;
    }

    public void menu() {
        Scanner entrada = new Scanner(System.in);
        byte opcao;
        do {
            System.out.println("\nBem vindo ao Menu de Conta Bancária");
            System.out.println("1 - Consultar Saldo");
            System.out.println("2 - Consultar Cheque Especial");
            System.out.println("3 - Depositar Dinheiro");
            System.out.println("4 - Sacar Dinheiro");
            System.out.println("5 - Pagar um Boleto");
            System.out.println("6 - Verificar se está usando Cheque Especial");
            System.out.println("7 - Sair");
            System.out.println("Digite a opção desejada: ");
            opcao = entrada.nextByte();

            switch (opcao) {
                case 1:
                    consultarSaldo();
                    break;
                case 2:
                    consultarChequeEspecial();
                    break;
                case 3:
                    depositarDinheiro(entrada);
                    break;
                case 4:
                    sacarDinheiro(entrada);
                    break;
                case 5:
                    pagarUmBoleto(entrada);
                    break;
                case 6:
                    var retorno = isUsandoChequeEspecial();
                    System.out.println("Você " + (retorno ? "sim" : "não") + " está usando o Cheque Especial");
                    break;
                case 7:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 7);
    }

    private void consultarSaldo() {
        System.out.println("Saldo da conta: " + saldoConta);
    }

    private void consultarChequeEspecial() {
        System.out.println("Limite do Cheque Especial: " + limiteChequeEspecial);
        System.out.println("Saldo em uso do Cheque Especial: " + saldoEmUsoChequeEspecial);
        System.out.println("Saldo disponível do Cheque Especial: " +
                limiteChequeEspecial.subtract(saldoEmUsoChequeEspecial).abs());
    }

    private void depositarDinheiro(Scanner entrada) {
        System.out.println("Digite o valor a ser depositado no seguinte formato 50,00: ");
        BigDecimal valorDeposito = entrada.nextBigDecimal();
        if (isUsandoChequeEspecial()) {
            saldoEmUsoChequeEspecial = saldoEmUsoChequeEspecial.subtract(valorDeposito);
            saldoConta = saldoConta.add(valorDeposito);

            if(taxaChequeEspecial.compareTo(BigDecimal.ZERO) > 0) cobrarTaxaChequeEspecial();
            System.out.println("Depósito realizado com sucesso no valor de: " + valorDeposito);
        } else {
            saldoConta = saldoConta.add(valorDeposito);
            System.out.println("Depósito realizado com sucesso no valor de: " + valorDeposito);
        }
    }

    private void sacarDinheiro(Scanner entrada) {
        System.out.println("Digite o valor a ser sacado no seguinte formato 10,00: ");
        BigDecimal valorSaque = entrada.nextBigDecimal();
        if (saldoConta.subtract(valorSaque).compareTo(BigDecimal.ZERO) >= 0) {

            // separar valores: conta e cheque especial
            var podeDebitarDoChequeEspecial = !isUsandoChequeEspecial();

            if (podeDebitarDoChequeEspecial) {
                processarOperacao(valorSaque);
                System.out.println("Saque realizado com sucesso no valor de: " + valorSaque);
            } else {
                saldoConta = saldoConta.subtract(valorSaque);
                System.out.println("Saque realizado com sucesso no valor de: " + valorSaque);
            }

        } else {
            System.out.println("Saldo insuficiente para realizar o saque.");
        }
    }

    private void pagarUmBoleto(Scanner entrada) {
        System.out.println("Digite o valor do boleto a ser pago: ");
        BigDecimal valorBoleto = entrada.nextBigDecimal();
        if (saldoConta.subtract(valorBoleto).compareTo(BigDecimal.ZERO) >= 0) {

            // separar valores: conta e cheque especial
            var podeDebitarDoChequeEspecial = !isUsandoChequeEspecial();

            if (podeDebitarDoChequeEspecial) {
                processarOperacao(valorBoleto);
                System.out.println("valorBoleto com sucesso no valor de: " + valorBoleto);
            } else {
                saldoConta = saldoConta.subtract(valorBoleto);
                System.out.println("valorBoleto com sucesso no valor de: " + valorBoleto);
            }
        } else {
            System.out.println("Saldo insuficiente para pagar o boleto.");
        }
    }

    private void processarOperacao(BigDecimal valorOperacao) {
        var saldoChequeDisponivel = limiteChequeEspecial.subtract(saldoEmUsoChequeEspecial).abs();
        var saldoRealDaConta = saldoConta.subtract(saldoChequeDisponivel).abs();
        var valorQueFaltaParaEfetivarOperacao = saldoRealDaConta.subtract(valorOperacao).abs();
        var saldoRestanteChequeEspecial = saldoChequeDisponivel.subtract(valorQueFaltaParaEfetivarOperacao).abs();

        // atualizar o saldo do cheque especial em uso
        atualizarSaldoChequeEspecial(saldoRestanteChequeEspecial);

        // atualizar o saldo da conta
        saldoConta = saldoConta.subtract(valorOperacao);

        // calcular a taxa do cheque especial
        if (valorQueFaltaParaEfetivarOperacao.abs().compareTo(BigDecimal.ZERO) > 0) {
            calcularTaxaChequeEspecial();
            cobrarTaxaChequeEspecial();
        }
    }

    private boolean isUsandoChequeEspecial() {
        if (saldoEmUsoChequeEspecial == BigDecimal.ZERO) return false;
        else return true;
    }

    private void atualizarSaldoChequeEspecial(BigDecimal saldoRestanteChequeEspecial) {
        saldoEmUsoChequeEspecial = limiteChequeEspecial.subtract(saldoRestanteChequeEspecial).abs();
    }

    private void calcularTaxaChequeEspecial() {
        taxaChequeEspecial = saldoEmUsoChequeEspecial.multiply(new BigDecimal("0.20"));
    }

    private void cobrarTaxaChequeEspecial() {
        if (saldoConta.subtract(taxaChequeEspecial).compareTo(BigDecimal.ZERO) > 0) {
            System.out.println("Taxa do Cheque Especial cobrada no valor de: " + taxaChequeEspecial);
            saldoConta = saldoConta.subtract(taxaChequeEspecial);
            System.out.println("Saldo da conta após cobrança da taxa do Cheque Especial: " + saldoConta);
            saldoEmUsoChequeEspecial = limiteChequeEspecial.subtract(saldoConta).abs();
            taxaChequeEspecial = BigDecimal.ZERO;
        } else {
            System.out.println("Saldo insuficiente para cobrar a taxa do Cheque Especial.");
        }
    }

    private BigDecimal implantarLimiteChequeEspecial(BigDecimal depositoInicial) {
        if (depositoInicial.compareTo(new BigDecimal("500.00")) <= 0) {
            return new BigDecimal("50.00");
        } else {
            return depositoInicial.multiply(new BigDecimal("0.5"));
        }
    }

}
