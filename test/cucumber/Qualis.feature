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
