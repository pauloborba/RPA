Feature: Cadastrar qualis em formato XLSX

  Scenario: Cadastro de qualis ainda não cadastrado
  Given não existe um qualis chamado “2012” cadastrado no sistema
  When o sistema recebe uma submissão para cadastrar um qualis chamado “2012” com o arquivo “qualis_2012.xls”
  Then o sistema cadastra o qualis chamado “2012” com 2 avaliações
  And o qualis "2012" tem uma avaliação "A1" para o veículo "ABCDEFGHIJK" de ISSN "1234-4321" no tema "Ciência da Computação"
  And o qualis "2012" tem uma avaliação "B3" para o veículo "Ontem já se foi" de ISSN "7267-156X" no tema "Arte e Cultura"

  Scenario: Cadastrar qualis que já havisa sido cadastrado
  Given existe um qualis chamado “2012” cadastrado no sistema
  When o sistema recebe uma submissão para cadastrar um qualis de “2012” no sistema
  And a submissão inclui o arquivo .xlsx referente ao qualis “2012”
  Then o sistema processa o arquivo submetido
  And o sistema deleta o qualis de “2012” no sistema
  And o sistema cadastra o novo qualis de “2012” no sistema