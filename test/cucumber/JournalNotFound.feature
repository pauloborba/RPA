@i9n
  Feature: journal not found
    As a user of the system
    I want to see which journals of a reseacher weren't found in a qualis
    so I can check if there aren't any mistakes in the articles names

    Scenario: journal of a researcher article not found in qualis
      Given the qualis "2012" has avaliations for the journals entitled "Theoretical Computer Science", "NTI" and "ISS"
      And the researcher of cpf "12121212121" has only two articles: "Software Engineerig" published at "NTI" and "Software Development" published at "Nature"
      When I ask for the score of cpf "12121212121" in the qualis "2012"
      Then The system creates a string saying that "1" article wasn't scored because it's jornal wasn't found

    Scenario: to list the articles of a reseacher that weren’t found in qualis
      Given I am at the score page for the researcher “Higor Botelho” in “Qualis 2012”
      And I can see that “1” article wasn’t scored because it’s journal wasn’t found
      When I ask to show the articles that weren’t scored
      Then I should see a list containing the name of the article that wasn’t scored