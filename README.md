# Bootcamp NTT Data Exercícios

1. Escreva um código onde temos uma conta bancaria que possa realizar as seguintes operações:
   1. Consultar saldo
   2. Consultar cheque especial
   3. Depositar dinheiro;
   4. Sacar dinheiro;
   5. Pagar um boleto.
   6. Verificar se a conta está usando cheque especial.

- Regras para implementar:
  - A conta bancária deve ter um limite de cheque especial somado ao saldo da conta;
  - O o valor do cheque especial é definido no momento da criação da conta, de acordo com 
  o valor depositado na conta em sua criação;
  - Se o valor depositado na criação da conta for de R$ 500,00 ou menos o cheque especial deve ser 
  de R$ 50,00
  - Para valores acima de R$500,00 o cheque especial deve ser de 50% do valor depositado;
  - Caso o limite de cheque especial seja usado, assim que possível a conta deve cobrar uma taxa 
  de 20% do valor usado do cheque especial.

- Resultado:
  - Utilizei BigDecimal para evitar problemas de precisão com valores monetários, usuário pode digitar
  padrão BR 50,00 ou 50.000,00
  - Acrescentei regras de atualização do cheque especial automaticamente na cobrança da taxa (caso esteja
  utilizando o cheque especial)
  - Tornei todos os métodos privados, o usuário só pode acessar o método 'menu' da classe ContaBancaria
  - Atualizo o novo saldo do cheque especial em caso de depósito na conta, caso o usuário esteja utilizando o 
  cheque especial e verifico se tem cobrança de taxa pendente e já debita com o novo saldo em conta

-----

2. Escreva um código onde controlamos as funções de um carro, ele deve ter as seguintes funções: 
   1. Ligar o carro;
   2. Desligar o carro;
   3. Acelerar;
   4. Diminuir velocidade;
   5. Virar para esquerda/direita
   6. Verificar velocidade;
   7. Trocar a marcha

- Regras para implementar:
    - Quando o carro for criado ele deve começar desligado, em ponto morto e com sua velocidade em 0
    - O carro desligado não pode realizar nenhuma função;
    - Quando o carro for acelerado ele deve incrementar 1km em sua velocidade (pode chegar no máximo a 120km);
    - Quando diminuir a velocidade do carro ele deve decrementar 1 km de sua velocidade (pode chegar no minimo a 0km);
    - O carro deve possuir 6 marchas, não deve ser permitido pular uma marcha no carro;
    - A velocidade do carro deve respeitar os seguintes limites para cada velocidade 
      - Se o carro estiver na marcha 0 (ponto morto) ele não pode acelerar
      - Se estiver na 1ª marcha sua velocidade pode estar entre 0km e 20km
      - Se estiver na 2ª marcha sua velocidade pode estar entre 21km e 40km
      - Se estiver na 3ª marcha sua velocidade pode estar entre 41km e 60km
      - Se estiver na 4ª marcha sua velocidade pode estar entre 61km e 80km
      - Se estiver na 5ª marcha sua velocidade pode estar entre 81km e 100km
      - Se estiver na 6ª marcha sua velocidade pode estar entre 101km e 120km
    - O carro podera ser desligado se estiver em ponto morto (marcha 0) e sua velocidade em 0 km
    - O carro só pode virar para esquerda/direita se sua velocidade for de no mínimi 1km e no máximo 40km;

- Resultado: