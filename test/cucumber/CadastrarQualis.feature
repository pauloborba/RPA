Feature: Cadastrar qualis em formato XLSX

  Scenario: Cadastro de qualis ainda não cadastrado
  Given não existe um qualis de “2012” cadastrado no sistema
  When o sistema recebe uma submissão para cadastrar um qualis de “2012” no sistema com o arquivo “qualis_2012.xlsx”
  Then o sistema cadastra o qualis de “2012” no sistema

  Scenario: Cadastrar qualis que já havisa sido cadastrado
  Given existe um qualis de “2012” cadastrado no sistema
  When o sistema recebe uma submissão para cadastrar um qualis de “2012” no sistema
  And a submissão inclui o arquivo .xlsx referente ao qualis “2012”
  Then o sistema processa o arquivo submetido
  And o sistema deleta o qualis de “2012” no sistema
  And o sistema cadastra o novo qualis de “2012” no sistema