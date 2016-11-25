Feature: Atualizar currículo lattes e mostrar diff
  As pesquisador
  I want atualizar meu lattes
  So that  I posso importar meu lattes e visualizar o que foi atualizado

  #Cenario1
  #controller
  Scenario: Adicionar um novo artigo ao pequisador
    Given pesquisador  de nome "Rafael", cpf  "12345678911", só tem o artigo "Implementation cow confident with BFS" do journal "Journal" e issn "1" e não tem atualizações está cadastrado no sistema importando o arquivo "CurriculoValidoComUmArtigoCow.xml"
    And  o arquivo "CurriculoValidoComDoisArtigos.xml" tem o Pesquisador "Rafael" com cpf "12345678911" e tem dois artigos "Implementation cow confident with BFS" e  "Implementation sudoku with backtracking" ambos com journal "Journal"e issn "1"
    When eu tento importar arquivo "CurriculoValidoComDoisArtigos.xml"
    Then sistema salva uma atualização no pesquisador de nome "Rafael" e cpf "12345678911" informando que o artigo "Implementation sudoku with backtracking" foi adicionado
    And o pesquisador de cpf "12345678911", nome "Rafael" e tem dois artigos "Implementation cow confident with BFS" e  "Implementation sudoku with backtracking" ambos com journal "Journal" e issn "1" está cadastrado

  #Cenario2
  #controller
  Scenario: Remover artigos do pesquisador
    Given o sistema não tem nenhum artigo com o titulo "Implementation cow confident with BFS" cadastrado
    And pesquisador  de nome "Rafael", cpf "12345678911", tem dois artigos "Implementation cow confident with BFS" e "Implementation sudoku with backtracking" ambos com journal "Journal" e issn "1" e não tem atualizações está cadastrado no sistema importando o arquivo "CurriculoValidoComDoisArtigos.xml"
    And  o arquivo "CurriculoValidoComUmArtigo.xml" tem o Pesquisador "Rafael", cpf "12345678911" e só tem o artigo "Implementation sudoku with backtracking" com journal "Journal" e issn "1"
    When eu tento importar arquivo "CurriculoValidoComUmArtigo.xml"
    Then sistema salva uma atualização no pesquisador de nome "Rafael" e cpf "12345678911" informando que o artigo "Implementation cow confident with BFS" foi removido
    And o artigo "Implementation cow confident with BFS" foi removido do sistema
    And o pesquisador de cpf "12345678911", nome "Rafael" e só tem o artigo "Implementation sudoku with backtracking" com journal "Journal" e issn "1" está cadastrado

  #Cenario3
  #controller
  Scenario: Tentar adicionar artigo com dados inválidos
    Given pesquisador  de nome "Rafael", cpf  "12345678911", só tem o artigo "Implementation sudoku with backtracking" do journal "Journal" e issn "1" e não tem atualizações está cadastrado no sistema importando o arquivo "CurriculoValidoComUmArtigo.xml"
    And  o arquivo "CurriculoInvalido.xml" tem o Pesquisador "", cpf "12345678911" e só tem o artigo "Implementation sudoku with backtracking" com journal "Journal" e issn "1"
    When eu tento importar arquivo "CurriculoInvalido.xml"
    Then Sistema não armazena nenhuma nova atualização no pesquisador de cpf "12345678911".
    And o pesquisador de cpf "12345678911", nome "Rafael" e só tem o artigo "Implementation sudoku with backtracking" com journal "Journal" e issn "1" está cadastrado

  #Cenario4
  #controller
  Scenario: Tentar adicionar e remover um artigo
    Given pesquisador  de nome "Rafael", cpf  "12345678911", só tem o artigo "Implementation cow confident with BFS" do journal "Journal" e issn "1" e não tem atualizações está cadastrado no sistema importando o arquivo "CurriculoValidoComUmArtigoCow.xml"
    And o arquivo "CurriculoValidoComUmArtigo.xml" tem o Pesquisador "Rafael", cpf "12345678911" e só tem o artigo "Implementation sudoku with backtracking" com journal "Journal" e issn "1"
    When eu tento importar arquivo "CurriculoValidoComUmArtigo.xml"
    And sistema salva duas atualizações no pesquisador de nome "Rafael" e cpf "12345678911" informando que o artigo "Implementation cow confident with BFS" foi removido e o artigo "Implementation sudoku with backtracking" foi adicionado
    And o pesquisador de cpf "12345678911", nome "Rafael" e só tem o artigo "Implementation sudoku with backtracking" com journal "Journal" e issn "1" está cadastrado

  #Cenario5
  #GUI
  Scenario: Adicionar um novo artigo ao pequisador e ver gui
    Given  pesquisador  de nome "Rafael", cpf  "12345678911", só tem o artigo "Implementation sudoku with backtracking" do journal "Journal" e issn "1" e não tem atualizações foi cadastrado no sistema com o arquivo "CurriculoValidoComUmArtigo.xml"
    And  Estou na página de importação de arquivo de pesquisador
    When importo arquivo "CurriculoValidoComDoisArtigos.xml"
    Then Eu estou na pagina de visualização
    And Eu vejo uma mensagem de confirmação
    And É possível ver o nome do artigo "Implementation cow confident with BFS" informando que ele foi adicionado.
    And São exibidas as informações do pesquisador "Rafael" e cpf "12345678911",
    And É exibido o artigo "Implementation cow confident with BFS" com journal "Journal" e issn "1"
    And É exibido o artigo "Implementation sudoku with backtracking" com journal "Journal" e issn "1"

  #Cenario5
  #GUI
  Scenario: Adicionar 4 novos artigo ao pequisador, ver diff e atualizações
    Given  pesquisador  de nome "Rafael", cpf  "12345678911", só tem o artigo "Implementation sudoku with backtracking" do journal "Journal" e issn "1" e não tem atualizações foi cadastrado no sistema com o arquivo "CurriculoValidoComUmArtigo.xml"
    And  Estou na página de importação de arquivo de pesquisador
    When importo arquivo "CurriculoValidoComQuatroArtigos.xml"
    Then Eu estou na pagina de visualização
    And Eu vejo uma mensagem de confirmação
    And É possível ver o nome do artigo "Implementation cow confident with BFS" informando que ele foi adicionado.
    And É possível ver o nome do artigo "Abordagem de LVQ para dados simbólicos" informando que ele foi adicionado.
    And É possível ver o nome do artigo "Curvas de bezier interativas" informando que ele foi adicionado.
    And São exibidas as informações do pesquisador "Rafael" e cpf "12345678911",
    And É exibido o artigo "Implementation cow confident with BFS" com journal "Journal" e issn "1"
    And É exibido o artigo "Implementation sudoku with backtracking" com journal "Journal" e issn "1"
    And É exibido o artigo "Abordagem de LVQ para dados simbólicos" com journal "Journal" e issn "1"
    And É exibido o artigo "Curvas de bezier interativas" com journal "Journal" e issn "1"