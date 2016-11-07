Feature: Importar curriculos lattes
  As um usuário
  I want to importar curriculos lattes
  So that eu possa visualiza-los e compara-los depois

#GUI
Scenario: Cadastrar pesquisador sem arquivo
Given Estou na tela de cadastrar pesquisadores
When  Eu tento importar um arquivo de currículo de nome ""
Then  É exibida uma mensagem de erro avisando que o nome do arquivo precisa ser inserido

#Controller
Scenario: Cadastrar pesquisador sem arquivo
Given Estou na tela de cadastrar pesquisadores
When  Eu tento importar um arquivo de currículo de nome ""
Then  O sistema não é alterado

#GUI
Scenario: Cadastrar pesquisador com arquivo inválido
Given Estou na tela de cadastrar pesquisadores
And   O arquivo de nome "CurriculoInvalido" não é um arquivo de currículo válido
When  Eu tento importar um arquivo de currículo de nome "CurriculoInvalido"
Then  É exibida uma mensagem de erro avisando que "CurriculoInvalido" não é um arquivo de currículo válido

#Controller
Scenario: Cadastrar pesquisador com arquivo inválido
Given Estou na tela de cadastrar pesquisadores
And   O arquivo de nome "CurriculoInvalido" não é um arquivo de currículo válido
When  Eu tento importar um arquivo de currículo de nome "CurriculoInvalido"
Then  O sistema não é alterado

#GUI
Scenario: Cadastrar pesquisador com nome inválido
Given Estou na tela de cadastrar pesquisadores
And   O arquivo "CurriculoSemNome" contém um pesquisador de CPF "01234567890" e nome ""
When  Eu tento importar um arquivo de currículo de nome "CurriculoSemNome"
Then  É exibida uma mensagem de erro avisando que "CurriculoSemNome" possui um nome inválido

#Controller
Scenario: Cadastrar pesquisador com nome inválido
Given Estou na tela de cadastrar pesquisadores
And   O arquivo "CurriculoSemNome" contém um pesquisador de CPF "01234567890" e nome ""
When  Eu tento importar um arquivo de currículo de nome "CurriculoSemNome"
Then  O sistema não é alterado

#GUI
Scenario: Cadastrar pesquisador com CPF inválido
Given Estou na tela de cadastrar pesquisadores
And   O arquivo "CurriculoSemCPF" contém um pesquisador de CPF "" e nome "Fulano"
When  Eu tento importar um arquivo de currículo de nome "CurriculoSemCPF"
Then  É exibida uma mensagem de erro avisando que "CurriculoSemCPF" possui um CPF inválido

#Controller
Scenario: Cadastrar pesquisador com CPF inválido
Given Estou na tela de cadastrar pesquisadores
And   O arquivo "CurriculoSemCPF" contém um pesquisador de CPF "" e nome "Fulano"
When  Eu tento importar um arquivo de currículo de nome "CurriculoSemCPF"
Then  O sistema não é alterado

#GUI
Scenario: Cadastrar pesquisador com um artigo inválido
Given Estou na tela de cadastrar pesquisadores
And   O arquivo "CurriculoArtigoInvalido" contém um pesquisador de CPF "01234567890" e nome "Fulano"
And   O arquivo "CurriculoArtigoInvalido" contém o artigo ""
When  Eu tento importar um arquivo de currículo de nome "CurriculoArtigoInvalido"
Then  É exibida uma mensagem de erro avisando que "CurriculoArtigoInvalido" possui um artigo de nome inválido

#Controller
Scenario: Cadastrar pesquisador com um artigo inválido
Given Estou na tela de cadastrar pesquisadores
And   O arquivo "CurriculoArtigoInvalido" contém um pesquisador de CPF "01234567890" e nome "Fulano"
And   O arquivo "CurriculoArtigoInvalido" contém o artigo ""
When  Eu tento importar um arquivo de currículo de nome "CurriculoArtigoInvalido"
Then  O sistema não é alterado

#GUI
Scenario: Cadastrar pesquisador com arquivo inexistente
Given Estou na tela de cadastrar pesquisadores
And   O arquivo de nome "Acre" não existe
When  Eu tento importar um arquivo de currículo de nome "Acre"
Then  É exibida uma mensagem de erro avisando que o arquivo "Acre" não foi encontrado

#Controller
Scenario: Cadastrar pesquisador com arquivo inexistente
Given Estou na tela de cadastrar pesquisadores
And   O arquivo de nome "Acre" não existe
When  Eu tento importar um arquivo de currículo de nome "Acre"
Then  O sistema não é alterado

#GUI
Scenario: Cadastrar um pesquisador com vários artigos com sucesso
Given Estou na tela de cadastrar pesquisadores
And   O pesquisador de CPF "12345678911" não está cadastrado
And   O arquivo "CurriculoValidoComDoisArtigos" contém um pesquisador de CPF "12345678911" e nome "Rafael"
And   O arquivo "CurriculoValidoComDoisArtigos" contém o artigo "Implementation cow confident with BFS"
And   O arquivo "CurriculoValidoComDoisArtigos" contém o artigo "Implementation sudoku with backtracking"
When  Eu tento importar um arquivo de currículo de nome "CurriculoValidoComDoisArtigos"
Then  É exibida uma mensagem de confirmação do cadastro com o nome "Rafael" e o CPF "01234567890"
And   É exibida uma lista com os artigos "Implementation cow confident with BFS" e "Implementation sudoku with backtracking" avisando que eles foram adicionados

#Controller
Scenario: Cadastrar um pesquisador com vários artigos com sucesso
Given Estou na tela de cadastrar pesquisadores
And   O pesquisador de CPF "12345678911" não está cadastrado
And   O arquivo "CurriculoValidoComDoisArtigos" contém um pesquisador de CPF "12345678911" e nome "Rafael"
And   O arquivo "CurriculoValidoComDoisArtigos" contém o artigo "Implementation cow confident with BFS"
And   O arquivo "CurriculoValidoComDoisArtigos" contém o artigo "Implementation sudoku with backtracking"
When  Eu tento importar um arquivo de currículo de nome "CurriculoValidoComDoisArtigos"
Then  O pesquisador "Rafael" é cadastrado no sistema
And   O artigo "Implementation cow confident with BFS" é adicionado ao currículo do pesquisador
And   O artigo "Implementation sudoku with backtracking" é adicionado ao currículo do pesquisador

#GUI
Scenario: Cadastrar um pesquisador sem artigos com sucesso
Given Estou na tela de cadastrar pesquisadores
And   O pesquisador de CPF "01234567890" não está cadastrado
And   O arquivo "CurriculoValidoSemArtigos" contém um pesquisador de CPF "01234567890" e nome "Fulano"
When  Eu tento importar um arquivo de currículo de nome "CurriculoValidoSemArtigos"
Then  É exibida uma mensagem de confirmação do cadastro com o nome "Fulano" e o CPF "01234567890"
And   É exibido um aviso de que o pesquisador não possui nenhum artigo

#Controller
Scenario: Cadastrar um pesquisador sem artigos com sucesso
Given Estou na tela de cadastrar pesquisadores
And   O pesquisador de CPF "01234567890" não está cadastrado
And   O arquivo "CurriculoValidoSemArtigos" contém um pesquisador de CPF "01234567890" e nome "Fulano"
When  Eu tento importar um arquivo de currículo de nome "CurriculoValidoSemArtigos"
Then  O pesquisador "Fulano" é cadastrado no sistema