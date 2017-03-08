Feature: Desenvolver cenarios de criacao de grupos de pesquisadores atraves da selecao de pesquisadores e ou outros grupos

Scenario: Criar grupo de pesquisa novo
  Given O sistema nao contem o grupo "CIn" cadastrado no seu database
  And O grupo "Inteligencia Artificial CIn" contem apenas "Teresa Ludermir" e "Paulo Adeodato"
  When O sistema recebe uma submissao para adicionar o grupo "CIn" com uma lista de  participantes contendo "Paulo Borba", "Fernando Castor" e o grupo de pesquisa "Inteligencia Artificial CIn"
  Then O sistema cria o grupo "CIn", com "Paulo Borba", "Fernando Castor", "Teresa Ludermir" e "Paulo Adeodato" nele, no seu database

Scenario: Adicionar grupo de pesquisa com nome inapropriado
  Given O sistema nao contem o grupo "&#@&" cadastrado no seu database
  When O sistema recebe uma submissao para adicionar o grupo "&#@&"
  Then O sistema nao cria um grupo "&#@&"

Scenario: Adicionar um novo pesquisador a um grupo de pesquisa
  Given O grupo "Matematica Computacional" nao tem o pesquisador "Sostenes Lins"
  When O sistema recebe uma submissao para adicionar "Sostenes Lins" ao grupo "Matematica Computacional"
  Then O grupo "Matematica Computacional" passa a ter "Sostenes Lins" entre seus integrantes

Scenario: Criar grupo de pesquisa a partir de pesquisadores em outros grupos
  Given Eu estou na pagina de Criacao de Grupos
  And O pesquisador "Veronica Teichrieb" criado com arquivo "CurriculoVT.xml" pertence ao grupo "Realidade Aumentada"
  And O pesquisador "Fernando Castor" criado com arquivo "CurriculoFernando.xml" pertence ao grupo "Engenharia De Software"
  And O pesquisador "Paulo Soares" criado com arquivo "CurriculoPaulo.xml" pertence ao grupo "Biologia Computacional"
  And Eu vejo os pesquisadores "Veronica Teichrieb", "Fernando Castor" e "Paulo Soares"
  When Eu seleciono a opcao de criar o grupo "CIn UFPE" com os pesquisadores  "Veronica Teichrieb", "Fernando Castor" e "Paulo Soares"
  Then Eu vejo que o grupo "CIn UFPE" foi criado com os pesquisadores "Veronica Teichrieb", "Fernando Castor" e "Paulo Soares"