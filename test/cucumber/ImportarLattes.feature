Feature: Importar curriculos lattes
  As um usuário
  I want to importar curriculos lattes
  So that eu possa visualiza-los e compara-los depois

#GUI
Scenario: Cadastrar um pesquisador com vários artigos com sucesso
Given O arquivo "CurriculoValidoComDoisArtigos.xml" contém um pesquisador de CPF "12345678911" e nome "Rafael".
And   O pesquisador de CPF "12345678911" não está cadastrado
And   O arquivo "CurriculoValidoComDoisArtigos.xml" contém o artigo "Implementation cow confident with BFS".
And   O arquivo "CurriculoValidoComDoisArtigos.xml" contém o artigo "Implementation sudoku with backtracking".
When  Eu tento importar um arquivo de currículo de nome "CurriculoValidoComDoisArtigos.xml".
Then  É exibida uma mensagem de confirmação do cadastro com o nome "Rafael" e o CPF "12345678911".
And   É exibida uma lista com os artigos "Implementation cow confident with BFS" e "Implementation sudoku with backtracking" avisando que eles foram adicionados

#Controller
Scenario: Cadastrar um pesquisador com vários artigos com sucesso
Given O arquivo "CurriculoValidoComDoisArtigos.xml" contém um pesquisador de CPF "12345678911" e nome "Rafael".
And   O pesquisador de CPF "12345678911" não está cadastrado
And   O arquivo "CurriculoValidoComDoisArtigos.xml" contém o artigo "Implementation cow confident with BFS".
And   O arquivo "CurriculoValidoComDoisArtigos.xml" contém o artigo "Implementation sudoku with backtracking".
When  Eu tento importar um arquivo de currículo de nome "CurriculoValidoComDoisArtigos.xml".
Then  O pesquisador de nome "Rafael" e CPF "12345678911" é cadastrado no sistema
And   O artigo "Implementation cow confident with BFS" é adicionado ao currículo do pesquisador de CPF "12345678911".
And   O artigo "Implementation sudoku with backtracking" é adicionado ao currículo do pesquisador de CPF "12345678911".

#GUI
Scenario: Cadastrar um pesquisador sem artigos com sucesso
Given O arquivo "CurriculoValidoSemArtigos.xml" contém um pesquisador de CPF "01234567890" e nome "Fulano".
And   O pesquisador de CPF "01234567890" não está cadastrado
When  Eu tento importar um arquivo de currículo de nome "CurriculoValidoSemArtigos.xml".
Then  É exibida uma mensagem de confirmação do cadastro com o nome "Fulano" e o CPF "01234567890".
And   É exibido um aviso de que o pesquisador não possui nenhum artigo

#Controller
Scenario: Cadastrar um pesquisador sem artigos com sucesso
Given O arquivo "CurriculoValidoSemArtigos.xml" contém um pesquisador de CPF "01234567890" e nome "Fulano".
And   O pesquisador de CPF "01234567890" não está cadastrado
When  Eu tento importar um arquivo de currículo de nome "CurriculoValidoSemArtigos.xml".
Then  O pesquisador de nome "Fulano" e CPF "01234567890" é cadastrado no sistema