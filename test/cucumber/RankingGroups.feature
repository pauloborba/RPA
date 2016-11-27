Feature: Criar cenários de ranking de um grupo de pesquisadores   As pesquisador  I want ver o ranking de grupos  So that  Eu posso consultar o grupo e o ranking dos pesquisadores pertencentes a ele

  #Cenario1
  #controller
  Scenario: Ver ranking de um grupo com dois pesquisadores
    Given O grupo "Computacao Distribuida" existe
    And Os pesquisadores "Joao" e "Arthur" esta cadastrado e esta no grupo "Computacao Distribuida"
    When E solicitado a listagem do grupo "Computacao Distribuida"
    Then E retornado o ranking do grupo "Computacao Distribuida"
    And Os pesquisadores "Joao" e "Arthur" sao listados

  #Cenario2
  #controller
  Scenario: Tentar ver ranking de um grupo inexistente
    Given O grupo "Computacao Distribuida" nao existe.
    When E solicitado a listagem do grupo "Computador Distribuida"
    Then E lancada uma excecao "Grupo nao foi encontrado!"

  #Cenario3
  #GUI
  Scenario: Pedir pra exibir ranking sem grupo selecionado
    Given Nao ha grupos cadastrados na base
    When E solicitado a listagem do ranking de grupos
    Then Aparece na tela uma mensagem de error

  #Cenario4
  #GUI
  Scenario: Pedir pra exibir ranking
    Given O grupo "Computacao Distribuida" existe
    And Os pesquisadores "Joao" e "Arthur" está cadastrado e está no grupo "Computacao Distribuida"
    When E solicitado a listagem do ranking do grupo "Computacao Distribuida"
    Then Aparece na tela uma lista com o ranking dos pesquisadores