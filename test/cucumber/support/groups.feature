Feature: Desenvolver cenarios de criacao de grupos de pesquisadores atraves da selecao de pesquisadores e ou outros grupos

Scenario: Criar grupo de pesquisa novo
  Given O sistema nao contem o grupo "CIn" cadastrado no seu database
  And O grupo "InteligenciaArtificialCIn" contem "TeresaLudermir" e "PauloAdeodato"
  When O sistema recebe uma submissao para adicionar o grupo "CIn" com uma lista de  participantes contendo "PauloBorba", "FernandoCastor" e o grupo de pesquisa "InteligenciaArtificialCIn"
  Then O sistema cria o grupo "CIn", com "PauloBorba", "FernandoCastor", "TeresaLudermir" e "PauloAdeodato" nele, no seu database

Scenario: Criar grupo de pesquisa a partir de pesquisadores em outros grupos
  Given Eu estou na pagina de Criacao de Grupos
  And O pesquisador "VeronicaTeichrieb" com o cpf "51111111111" pertence ao grupo "RealidadeAumentada"
  And O pesquisador "FernandoCastor" com o cpf "51111111112" pertence ao grupo "EngenhariaDeSoftware"
  And O pesquisador "PauloSoares" com o cpf "51111111113" pertence ao grupo "BiologiaComputacional"
  And Eu vejo os pesquisadores "VeronicaTeichrieb", "FernandoCastor" e "PauloSoares"
  When Eu seleciono a opcao de criar para o grupo "CInUFPE" com os pesquisadores  "VeronicaTeichrieb", "FernandoCastor" e "PauloSoares"
  Then Eu vejo que o grupo "CInUFPE" foi criado com os pesquisadores "VeronicaTeichrieb", "FernandoCastor" e "PauloSoares"