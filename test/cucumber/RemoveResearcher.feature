Feature: Remove Researcher
  As a User
  I want to Remove Researchers
  So that I can delete data from unwanted researchers

  #CONTROLLER
  Scenario: Remover vários pesquisadores
  Given Os pesquisadores de CPF "01234567890" e nome "Antonio" e CPF "98765432100" e nome "Augusto" estão cadastrados
  When Eu recebo a solicitação para remover os pesquisadores de CPF "01234567890" e "98765432100"
  Then Os pesquisadores de CPF "01234567890" e "98765432100" são removidos da lista de pesquisadores
    
  #GUI
  Scenario: Tentar remover pesquisador sem selecionar ninguém
  Given Estou na tela de remoção de pesquisador
  And Eu não selecionei nenhum pesquisador da lista
  When Eu seleciono a opção de remover
  Then A remoção é rejeitada e uma mensagem é exibida dizendo que eu preciso escolher pelo menos um pesquisador para poder remover alguém

  #GUI
  Scenario: Tentar remover pesquisador informando um CPF inválido
  Given Estou na tela de remoção de pesquisador
  And Eu informei no campo de busca o CPF "something"
  When Eu seleciono a opção de remover
  Then A busca retorna null para o CPF "something" e uma mensagem é exibida dizendo que o pesquisador que eu pretendo remover não foi encontrado.