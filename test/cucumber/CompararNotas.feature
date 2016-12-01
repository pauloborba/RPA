Feature: Comparar notas de um grupo de pesquisadores em relação a outro
         As a User
         I want to Compare groups
         So that I can see which group is best
  #GUI
    Scenario: Comparação e grupos com médias diferentes

      Given Os pesquisadores "Bruno" de cpf "00000000000" e "Matheus" de cpf "00000000001" estão cadastrados no sistema
      And "Cin UFPE" está cadastrado e tem o pesquisador "Bruno"
      And "UFRJ" está cadastrado e tem os pesquisadores "Bruno" e "Matheus"
      And "Cin UFPE" tem nota "3" no "A1" do Qualis "2012"
      And "UFRJ" tem nota "2" no "A1" do Qualis "2012"
      When Solicito a comparação usando Qualis "2012" entre "Cin UFPE" e "UFRJ"
      Then A nota "3" do grupo "Cin UFPE" fica verde e a nota "2" do grupo "UFRJ" fica vermelha no critério "A1" do Qualis "2012"
      And A nota media por pesquisador do grupo "Cin UFPE" fica verde e a nota media por pesquisador do grupo "UFRJ" fica vermelha no critério "A1" do Qualis "2012"

  #GUI
    Scenario: Comparação de grupos que têm a mesma média

      Given Os pesquisadores "Joao" de cpf "00000000002" e "Rafael" de cpf "00000000003" estão cadastrados no sistema
      And "Cin UFPE" está cadastrado e tem o pesquisador "Joao"
      And "UFRJ" está cadastrado e tem o pesquisador "Rafael"
      And "Cin UFPE" tem nota "4" no "A2" do Qualis "2013"
      And "Cin UFPE" tem nota "0" no "B2" do Qualis "2013"
      And "UFRJ" tem nota "4" no "A2" do Qualis "2013"
      And "UFRJ" tem nota "0" no "B2" do Qualis "2013"
      When Solicito a comparação usando Qualis "2013" entre "Cin UFPE" e "UFRJ"
      Then A nota "4" do grupo "Cin UFPE" e a nota "4" do grupo "UFRJ" ficam azuis no critério "A2" do Qualis "2013"
      And A nota "0" do grupo "Cin UFPE" e a nota "0" do grupo "UFRJ" ficam azuis no critério "B2" do Qualis "2013"

  #GUI
    Scenario: Comparação de grupos de mesmo nome
      Given O pesquisador "Antonio" de cpf "00000000004" está cadastrado no sistema
      And "Cin UFPE" está cadastrado e tem o pesquisador "Antonio"
      When Solicito a comparação usando Qualis "2014" entre "Cin UFPE" e "Cin UFPE"
      Then Aparece uma mensagem de erro dizendo que a comparação não pode ser realizada

  #Controller
    Scenario: comparação de grupos

      Given Os grupos "Cin UFPE" e "UFRJ" estão cadastrados no sistema
      And "Cin UFPE" tem nota "4" no "A2" do Qualis "2010"
      And "Cin UFPE" tem nota "5" no "B1" do Qualis "2010"
      And "UFRJ" tem nota "5" no "A2" do Qualis "2010"
      And "UFRJ" tem nota "5" no "B1" do Qualis "2010"
      When Faço a comparação usando Qualis "2010" entre "Cin UFPE" e "UFRJ"
      Then "Cin UFPE" continua com nota "5" no "B1" do Qualis "2010"
      And "UFRJ" continua com nota "5" no "B1" do Qualis "2010"
      And "Cin UFPE" continua com nota "4" no "A2" do Qualis "2010"
      And "UFRJ" continua com nota "5" no "A2" do Qualis "2010"
