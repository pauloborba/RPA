Feature: Comparar notas de um grupo de pesquisadores em relação a outro
         As a User
         I want to Compare groups
         So that I can see which group is best
  #GUI
    Scenario: Comparação e grupos com médias diferentes

      Given	Os grupos "UFRJ" e "Cin UFPE" estão cadastrados no sistema
      And "Cin UFPE" tem nota "3" no "A1" do Qualis "2012"
      And "UFRJ"  tem nota "2" no "A1" do Qualis "2012"
      When Solicito a comparação usando Qualis "2012" entre "Cin UFPE" e "UFRJ"
      Then A nota "3" do grupo "Cin UFPE" fica verde e a nota "2" do grupo "UFRJ" fica vermelha no critério "A1" do Qualis "2012"

  #GUI
    Scenario: Comparação de grupos que têm a mesma média

      Given	Os grupos "UFRJ" e "Cin UFPE" estão cadastrados dentro do sistema
      And "Cin UFPE" possui nota "4" no "A2" do Qualis "2013"
      And "UFRJ" possui a nota "4" no "A2" do Qualis "2013"
      When Solicito a comparação usando  Qualis "2013" entre "Cin UFPE" e "UFRJ"
      Then A nota "4" do grupo "Cin UFPE" e a nota "4" do grupo "UFRJ" ficam azuis no critério "A2" do Qualis "2013"

  #GUI
    Scenario: Comparação de grupos de mesmo nome

      Given	O grupo "Cin UFPE"  está cadastrado como um grupo no sistema
      When Solicito a comparação utilizando Qualis "2014" entre "Cin UFPE" e "Cin UFPE"
      Then Aparece uma mensagem de erro dizendo que a comparação não pode ser realizada

  #Controller
    Scenario: comparação de grupos

      Given	Os grupos "Cin UFPE" e  "UFRJ" estão cadastrados no sistema
      And "Cin UFPE" tem nota "5" no "B1" do Qualis  "2010"
      And "UFRJ" tem nota "5" no "B1" do  Qualis "2010"
      When Eu solicito a comparação usando Qualis "2010" entre "Cin UFPE" e "UFRJ"
      Then "Cin UFPE" continua com nota "5" no "B1" do Qualis "2010"
      And "UFRJ" continua com  nota "5" no "B1" do Qualis "2010"
