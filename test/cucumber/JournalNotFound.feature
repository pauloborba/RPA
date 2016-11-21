@i9n
  Feature: journal not found
    As a user of the system
    I want to see which journals of a reseacher weren't found in a qualis
    so I can check if there aren't any mistakes in the articles names

    Scenario: journal of a researcher article not found in qualis
      Given the qualis "2012" has avaliations for the journals entitled "Theoretical Computer Science", "NTI" and "ISS"
      And the researcher of cpf "12121212121" has only two articles: "Software Engineerig" published at "NTI" and "Software Development" published at "Nature"
      When I ask for the score of cpf "12121212121" in the qualis "2012"
      Then The system creates a string saying that "1" article wasn't scored because its jornal wasn't found

    Scenario: to list the journal of a researcher article that wasn't found in qualis
      Given The qualis "2013" has avaliations no avaliation for the journal "Nature"
      And The researcher of cpf "11111111111" has only one article: "The Future of Computers" published at "Nature"
      When The system create a score for the reseacher of cpf "11111111111" in the qualis "2013"
      Then The system also creates a list of articles not found containing only "The Future of Computers"

    Scenario: article of a researcher not avaliated
      Given I created the reseacher of cpf "11111141111" with just an article published at "Theoretical Computer Science"
      And I created the qualis "2014" with just an avaliation for "NTI"
      When I ask to create the avaliation of the researcher of cpf "11111141111" in qualis "2014"
      Then I should see that "1" article wasn't scored

    Scenario: show page of not avaliated articles
      Given I created the reseacher of cpf "11111151111" with just an article published at "Science"
      And I created the qualis "2015" with just an avaliation for "Atom"
      And I created the avaliation of the researcher of cpf "11111151111" in qualis "2015"
      And I am at the list of avaliations page
      When I click to show the avaliation of researcher "11111151111"
      Then I am at the description page for the avaliation
      And I can see that "Science" journal wasn't scored