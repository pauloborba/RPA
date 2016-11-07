Feature: desenvolver cenários de avaliação de notas de um pesquisador em relação a um conjunto de qualis escolhido

  #GUI
  Scenario busca de notas por pesquisador e um qualis
    Given eu estou na página de avaliação de notas
    When eu seleciono a pesquisadora cadastrada "AnjolinaGrisi" e o qualis "2012"
    Then é mostrado na tela uma lista com a quantidade de publicações que a pesquisadora "AnjolinaGrisi" tem por nota no qualis de "2012"

    #Controller
  Scenario busca de notas por pesquisador e dois qualis
    Given o sistema contém informações sobre o pesquisador cadastrado "FernandoCastor"
    And o pesquisador cadastrado "FernandoCastor" possui publicações "M", "N" e "O" nos periódicos "X", "Y" e "Z" respectivamente
    And o sistema contém o qualis "2014" com a nota "A1" para as publicações "M" e o qualis "2012" com a nota "A2" para a publicação “N”
    When o sistema recebe a solicitação de avaliar o pesquisador "FernandoCastor" pelo qualis "2014" e "2012"
    Then o sistema retorna uma lista com a quantidade de publicações que o pesquisador "FernandoCastor" tem por nota "A1-1,A2-1,B1 - 0 … D - 0, NP - 1"
    And o sistema não é modificado
