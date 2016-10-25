Feature: Cadastrar qualis em formato XLSX

  #Cadastro de qualis ainda não cadastrado
  GIVEN não existe um qualis de “2012” cadastrado no sistema
  WHEN o sistema recebe uma submissão para cadastrar um qualis de “2012” no sistema com o arquivo “qualis_2012.xlsx”
  THEN o sistema cadastra o qualis de “2012” no sistema

  #Cadastrar qualis que já havisa sido cadastrado
  GIVEN existe um qualis de “2012” cadastrado no sistema
  WHEN o sistema recebe uma submissão para cadastrar um qualis de “2012” no sistema
  AND a submissão inclui o arquivo .xlsx referente ao qualis “2012”
  THEN o sistema processa o arquivo submetido
  AND o sistema deleta o qualis de “2012” no sistema
  AND o sistema cadastra o novo qualis de “2012” no sistema