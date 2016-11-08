Feature: desenvolver cenarios de avaliacao de notas de um pesquisador em relacao a um conjunto de qualis escolhido

  #GUI
  Scenario: busca de notas por pesquisador e um qualis
    Given eu estou na pagina de avaliacao de notas
    When eu seleciono a pesquisadora cadastrada "AnjolinaGrisi" e o qualis "2012"
    Then eh mostrado na tela uma lista com a quantidade de publicacoes que a pesquisadora "AnjolinaGrisi" tem por nota no qualis de "2012"

    #Controller
  Scenario: busca de notas por pesquisador e dois qualis
    Given o pesquisador cadastrado "FernandoCastor" possui publicacoes "M", "N" e "O" nos periodicos "X", "Y" e "Z" respectivamente
    And o sistema contem o qualis "2014" com a nota "A1" para a publicacao "X" e o qualis "2012" com a nota "A2" para as publicacoes "Y" e "Z"
    When o sistema recebe a solicitacao de avaliar o pesquisador "FernandoCastor" pelo qualis "2014" e "2012"
    Then o sistema retorna uma lista com a quantidade de publicacoes que o pesquisador "FernandoCastor" tem por nota "A1: 1; " no qualis "2014"
    And o sistema retorna outra lista com a quantidade de publicacoes que o pesquisador "FernandoCastor" tem por nota "A2: 2; " no qualis "2012"


