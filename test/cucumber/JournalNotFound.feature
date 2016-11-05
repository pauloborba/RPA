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

    Scenario: article of a researcher not avaliated
      Given I created the reseacher of cpf "11111111111" with just an article published at "Theoretical Computer Science"
      And I created the qualis "2013" with just an avaliation for "NTI"
      When I ask to create the avaliation of the researcher of cpf "11111111111" in qualis "2013"
      Then I should see that "1" article wasn't scored