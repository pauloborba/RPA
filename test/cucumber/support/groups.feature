Feature: Desenvolver cenários de criação de grupos de pesquisadores através da seleção de pesquisadores e/ou outros grupos

  #Controle
Scenario Criar grupo de pesquisa novo
  Given O sistema não contém o grupo "CIn" cadastrado no seu database.
  And O grupo "Inteligência Artificial CIn" contém "Teresa Ludermir" e "Paulo Adeodato"
  When O sistema recebe uma submissão para adicionar o grupo "CIn" com uma lista de  participantes contendo "Paulo Borba", “Fernando Castor” e o grupo de pesquisa “Inteligência Artificial CIn”.
  Then O sistema cria o grupo "CIn", com "Paulo Borba", "Fernando Castor", "Teresa Ludermir" e "Paulo Adeodato" nele, no seu database.

  #GUI
Scenario: Criar grupo de pesquisa a partir de grupos de pesquisa
  Given Eu estou na página de Criação de Grupos
  And Eu vejo os grupos "Realidade Aumentada", "Engenharia de Software" e "Biologia Computacional"
  And O pesquisador "Verônica Teichrieb" pertence ao grupo "Realidade Aumentada"
  And O pesquisador "Fernando Castor" pertence ao grupo "Engenharia de Software"
  And O pesquisador "Paulo Soares" pertence ao grupo "Biologia Computacional"
  When Eu seleciono a opção de criar para o grupo "CIn" com os grupos  "Realidade Aumentada", "Engenharia de Software" e "Biologia Computacional"
  Then Eu vejo que o grupo "CIn" foi criado com os pesquisadores "Verônica Teichrieb", "Fernando Castor" e "Paulo Soares"