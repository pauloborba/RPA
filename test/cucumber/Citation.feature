@i9n
Feature: Collecting citations

  As a member of a research group
  I want to import the history of citations to me at Google Scholar
  So that the system register the corresponding citations

  Scenario:  Integrity of data between the system and Google Scholar

    Given the researcher "Paulo Borba" with cpf "11111111111" is stored by the system
    And the article with title "Implementing distribution and persistence aspects with AspectJ", issn "11111", journal "ACM" from "Paulo Borba" is stored by the system
    And the system has no information about the citation amount to the article with title "Implementing distribution and persistence aspects with AspectJ".
    And the article with title "Implementing distribution and persistence aspects with AspectJ" has "377" citations at Google Scholar
    When I try to find citations to "Implementing distribution and persistence aspects with AspectJ" from researcher "Paulo Borba".
    Then the "377" citations to the article "Implementing distribution and persistence aspects with AspectJ" are stored by the system

  Scenario: Data not found

    Given the researcher "Joao da Silva" with cpf "22222222222" is stored by the system
    And the article with title "Assessing fine-grained feature dependencies", issn "22222", journal "ACM" from "Joao da Silva" is stored by the system
    And the system has no information about the citation amount to the article with title "Assessing fine-grained feature dependencies".
    And the article with title "Assessing fine-grained feature dependencies" has "0" citations at Google Scholar
    When I try to find citations to "Assessing fine-grained feature dependencies" from researcher "Joao da Silva".
    Then no citations to the article "Assessing fine-grained feature dependencies" are stored by the system

  Scenario: Request for number of citations of a researcher

    Given the researcher "Luiz Silveira" with cpf "33333333333" is stored by the system
    And the article with title "Projects on DB", issn "44444", journal "ACM" from "Luiz Silveira" is stored by the system
    And the article with title "Projects on DB" has "520" citations at Google Scholar
    And the article with title "Projects on BD", issn "55555", journal "ACM" from "Luiz Silveira" is stored by the system
    And the article with title "Projects on BD" has "105" citations at Google Scholar
    And the researcher "Luiz Silveira" has only "2" articles stored by the system: "Projects on DB" and "Projects on BD".
    When I try to find citations to "Luiz Silveira"
    Then the "625" citations to the researcher "Luiz Silveira" are stored by the system