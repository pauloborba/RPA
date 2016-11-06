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
    Then sistema salva um diff no pesquisador  de nome "Rafael" e cpf "12345678911" informando que o artigo "Implementation sudoku with backtracking" foi adicionado
    And o pesquisador de cpf "12345678911", nome "Rafael" e tem dois artigos "Implementation cow confident with BFS" e  "Implementation sudoku with backtracking" ambos com journal "Journal" e issn "1" está cadastrado

  #Cenario2
  #controller
  Scenario: Remover artigos do pesquisador
    Given o sistema não tem nenhum artigo com o titulo "Implementation cow confident with BFS" cadastrado
    And pesquisador  de nome "Rafael", cpf "12345678911", tem dois artigos "Implementation cow confident with BFS" e "Implementation sudoku with backtracking" ambos com journal "Journal" e issn "1" e não tem diff está cadastrado no sistema
    And  o arquivo "CurriculoValidoComUmArtigo.xml" tem o Pesquisador "Rafael", cpf "12345678911" e só tem o artigo "Implementation sudoku with backtracking" com journal "Journal" e issn "1"
    When eu tento importar arquivo "CurriculoValidoComUmArtigo.xml"
    And sistema salva um diff no pesquisador  de nome "Rafael" e cpf "12345678911" informando que o artigo "Implementation cow confident with BFS" foi removido
    And o artigo "Implementation cow confident with BFS" foi removido do sistema
    And o pesquisador de cpf "12345678911", nome "Rafael" e só tem o artigo "Implementation sudoku with backtracking" com journal "Journal" e issn "1" está cadastrado

  #Cenario3
  #controller
  Scenario: Tentar adicionar artigo com dados inválidos
    Given pesquisador  de nome "Rafael", cpf  "12345678911", só tem o artigo "Implementation sudoku with backtracking" do journal "Journal" e issn "1" e não tem atualizações está cadastrado no sistema
    And  o arquivo "CurriculoInvalido.xml" tem o Pesquisador "", cpf "12345678911" e só tem o artigo "Implementation sudoku with backtracking" com journal "Journal" e issn "1"
    When eu tento importar arquivo "CurriculoInvalido.xml"
    Then Sistema não armazena nenhum novo diff no pesquisador de cpf "12345678911".
    And o pesquisador de cpf "12345678911", nome "Rafael" e só tem o artigo "Implementation sudoku with backtracking" com journal "Journal" e issn "1" está cadastrado

  #Cenario4
  #controller