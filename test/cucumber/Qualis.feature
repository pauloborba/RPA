Feature: Cadastrar qualis em formato XLSX

  Scenario: Cadastro de qualis ainda não cadastrado
    Given não existe um qualis chamado "2012" cadastrado no sistema
    When o sistema recebe uma submissão para cadastrar um qualis chamado "2012" com o arquivo "qualis_2012.xls"
    Then o sistema cadastra o qualis chamado "2012" com 2 avaliações
    And o qualis "2012" tem uma avaliação "A1" para o veículo "ABCDEFGHIJK" de ISSN "1234-4321" no tema "Ciência da Computação"
    And o qualis "2012" tem uma avaliação "B3" para o veículo "Ontem já se foi" de ISSN "7267-156X" no tema "Arte e Cultura"

  Scenario: Cadastrar qualis que já havia sido cadastrado
    Given existe um qualis chamado "2012" cadastrado no sistema
    When o sistema recebe uma submissão para cadastrar um qualis chamado "2012" com o arquivo "qualis_2012-2.xls"
    Then o sistema cadastra o qualis chamado "2012" com 3 avaliações
    And o qualis "2012" tem uma avaliação "A1" para o veículo "Veículo teste" de ISSN "1526-0980" no tema "Ciência da Computação"
    And o qualis "2012" tem uma avaliação "A3" para o veículo "Commit da perdição" de ISSN "1776-097X" no tema "Culinária"
    And o qualis "2012" tem uma avaliação "C5" para o veículo "Veja esse commit" de ISSN "0987-1345" no tema "Aviação"

  Scenario: Deletar qualis que já havia sido cadastrado
    Given existe um qualis chamado "2012" que foi cadastrado com o arquivo "qualis_2012-2.xls"
    And o qualis "2012" tem uma avaliação "A1" para o veículo "Veículo teste" de ISSN "1526-0980" no tema "Ciência da Computação"
    And o qualis "2012" tem uma avaliação "A3" para o veículo "Commit da perdição" de ISSN "1776-097X" no tema "Culinária"
    And o qualis "2012" tem uma avaliação "C5" para o veículo "Veja esse commit" de ISSN "0987-1345" no tema "Aviação"
    When o sistema recebe uma requisição para deletar o qualis cujo título é "2012" contendo o seu id
    Then não existe um qualis chamado "2012" cadastrado no sistema

  Scenario: Deletar qualis web
    Given eu estou na página de detalhe do qualis "2012" cadastrado com o arquivo "qualis_2012-2.xls"
    When eu clicar em deletar na pagina de detalhes
    Then eu estou na página de listagem de qualis e não existe um qualis chamado "2012"

  Scenario: Editar qualis web
    Given eu estou na página de detalhe do qualis "2012" cadastrado com o arquivo "qualis_2012.xls"
    When eu clico em editar na pagina de detalhes
    And eu preencho o formulário de editar com título "2012.2" com o arquivo "qualis_2012-2.xls"
    And eu clico em atualizar
    Then o título do página é "2012.2"
    And o veículo "Veículo teste" está presente na página
    And o veículo "Commit da perdição" está presente na página
    And o veículo "Veja esse commit" está presente na página