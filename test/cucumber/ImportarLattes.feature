Feature: Importar curriculos lattes
As um usuário
I want to importar curriculos lattes
So that eu possa visualiza-los e compara-los depois

#Controller
Scenario: Cadastrar um pesquisador com vários artigos com sucesso
Given O arquivo "CurriculoValidoComDoisArtigos.xml" contém um pesquisador de CPF "12345678911" e nome "Rafael".
And   O arquivo "CurriculoValidoComDoisArtigos.xml" contém o artigo "Implementation cow confident with BFS".
And   O arquivo "CurriculoValidoComDoisArtigos.xml" contém o artigo "Implementation sudoku with backtracking".
And   O pesquisador de CPF "12345678911" não está cadastrado
When  Um arquivo de currículo de nome "CurriculoValidoComDoisArtigos.xml" é importado
Then  O pesquisador de nome "Rafael" e CPF "12345678911" é cadastrado no sistema
And   O artigo "Implementation cow confident with BFS" é adicionado ao currículo do pesquisador de CPF "12345678911".
And   O artigo "Implementation sudoku with backtracking" é adicionado ao currículo do pesquisador de CPF "12345678911".

#GUI
Scenario: Cadastrar um pesquisador com vários artigos com sucesso
Given Estou na página de cadastrar pesquisadores
And   O arquivo "CurriculoValidoComDoisArtigos.xml" contém um pesquisador de CPF "12345678911" e nome "Rafael".
And   O arquivo "CurriculoValidoComDoisArtigos.xml" contém o artigo "Implementation cow confident with BFS".
And   O arquivo "CurriculoValidoComDoisArtigos.xml" contém o artigo "Implementation sudoku with backtracking".
And   O pesquisador de CPF "12345678911" não está cadastrado
When  Eu tento importar um arquivo de currículo de nome "CurriculoValidoComDoisArtigos.xml".
Then  Estou na página de exibir pesquisadores
And   É exibido um aviso de que um pesquisador foi cadastrado
And   São exibidos o nome do pesquisador "Rafael" e o CPF "12345678911".
And   É exibido o artigo de título "Implementation cow confident with BFS", da revista "Journal" e issn "1".
And   É exibido o artigo de título "Implementation sudoku with backtracking", da revista "Journal" e issn "1".

#Controller
Scenario: Cadastrar um pesquisador sem artigos com sucesso
Given O arquivo "CurriculoValidoSemArtigos.xml" contém um pesquisador de CPF "01234567890" e nome "Fulano".
And   O arquivo "CurriculoValidoSemArtigos.xml" não contem artigos
And   O pesquisador de CPF "01234567890" não está cadastrado
When  Um arquivo de currículo de nome "CurriculoValidoSemArtigos.xml" é importado
Then  O pesquisador de nome "Fulano" e CPF "01234567890" é cadastrado no sistema
And   O pesquisador de CPF "01234567890" não possui artigos

#GUI
Scenario: Cadastrar um pesquisador sem artigos com sucesso
Given Estou na página de cadastrar pesquisadores
And   O arquivo "CurriculoValidoSemArtigos.xml" contém um pesquisador de CPF "01234567890" e nome "Fulano".
And   O arquivo "CurriculoValidoSemArtigos.xml" não contem artigos
And   O pesquisador de CPF "01234567890" não está cadastrado
When  Eu tento importar um arquivo de currículo de nome "CurriculoValidoSemArtigos.xml".
Then  Estou na página de exibir pesquisadores
And   É exibido um aviso de que um pesquisador foi cadastrado
And   São exibidos o nome do pesquisador "Fulano" e o CPF "01234567890".
And   Não é exibido nenhum artigo

#Controller
Scenario: Tentar importar algo que não é um arquivo de currículo
Given O arquivo "TestAndOperations.groovy" não é um arquivo de currículo
When  Um arquivo de currículo de nome "TestAndOperations.groovy" é importado
Then  Nenhum pesquisador é cadastrado

#GUI
Scenario: Tentar importar algo que não é um arquivo de currículo
Given Estou na página de cadastrar pesquisadores
And   O arquivo "TestAndOperations.groovy" não é um arquivo de currículo
When  Eu tento importar um arquivo de currículo de nome "TestAndOperations.groovy".
Then  Ainda estou na página de cadastrar pesquisadores
And   É exibido um aviso de que o arquivo selecionado é inválido

#Controller
Scenario: Tentar importar um arquivo vazio
Given O arquivo "ArquivoVazio.xml" está vazio
When  Um arquivo de currículo de nome "ArquivoVazio.xml" é importado
Then  Nenhum pesquisador é cadastrado

#GUI
Scenario: Tentar importar um arquivo vazio
Given Estou na página de cadastrar pesquisadores
And   O arquivo "ArquivoVazio.xml" está vazio
When  Eu tento importar um arquivo de currículo de nome "ArquivoVazio.xml".
Then  Ainda estou na página de cadastrar pesquisadores
And   É exibido um aviso de que o arquivo selecionado está vazio