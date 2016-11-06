Feature: Atualizar currículo lattes e mostrar diff
  As pesquisador
  I want atualizar meu lattes
  So that  I posso importar meu lattes e visualizar o que foi atualizado

  #Cenario1
  #controller
  Scenario: Adicionar um novo artigo ao pequisador
    Given pesquisador  de nome "Rafael", cpf  "12345678911", só tem o artigo "Implementation cow confident with BFS" do journal "Journal" e issn "1" e não tem atualizações está cadastrado no sistema
    And  o arquivo "CurriculoValidoComDoisArtigos.xml" tem o Pesquisador "Rafael" com cpf "12345678911" e tem dois artigos "Implementation cow confident with BFS" e  "Implementation sudoku with backtracking" ambos com journal "Journal"e issn "1"
    When eu tento importar arquivo "CurriculoValidoComDoisArtigos.xml"
    Then sistema salva um diff no pesquisador  de nome "Rafael" e cpf "12345678911" com a mensagem que o artigo "Implementation sudoku with backtracking" foi adicionado
    And o pesquisador de cpf "12345678911", nome "Rafael" e tem dois artigos "Implementation cow confident with BFS" e  "Implementation sudoku with backtracking" ambos com journal "Journal" e issn "1" está cadastrado
