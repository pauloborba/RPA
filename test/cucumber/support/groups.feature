Feature: Desenvolver cenarios de criacao de grupos de pesquisadores atraves da selecao de pesquisadores e ou outros grupos

Scenario: Criar grupo de pesquisa novo
  Given O sistema nao contem o grupo "CIn" cadastrado no seu database
  And O grupo "InteligenciaArtificialCIn" contem "TeresaLudermir" e "PauloAdeodato"
  When O sistema recebe uma submissao para adicionar o grupo "CIn" com uma lista de  participantes contendo "PauloBorba", "FernandoCastor" e o grupo de pesquisa "InteligenciaArtificialCIn"
  Then O sistema cria o grupo "CIn", com "PauloBorba", "FernandoCastor", "TeresaLudermir" e "PauloAdeodato" nele, no seu database

Scenario: Criar grupo de pesquisa a partir de grupos de pesquisa
  Given Eu estou na pagina de Criacao de Grupos
  And Eu vejo os grupos "RealidadeAumentada", "EngenhariaDeSoftware" e "BiologiaComputacional"
  And O pesquisador "VeronicaTeichrieb" pertence ao grupo "RealidadeAumentada"
  And O pesquisador "FernandoCastor" pertence ao grupo "EngenhariaDeSoftware"
  And O pesquisador "PauloSoares" pertence ao grupo "BiologiaComputacional"
  When Eu seleciono a opcao de criar para o grupo "CIn" com os grupos  "RealidadeAumentada", "EngenhariaDeSoftware" e "BiologiaComputacional"
  Then Eu vejo que o grupo "CIn" foi criado com os pesquisadores "VeronicaTeichrieb", "FernandoCastor" e "PauloSoares"