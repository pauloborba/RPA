@i9n
Feature: Collecting citations

  As a member of a research group
  I want to import the history of citations to me at Google Scholar
  So that the system register the corresponding citations

  Scenario: Integrity of data between the system and Google Scholar

    Given the researcher "Paulo Borba" with cpf "11111111111" is stored by the system
    And the article with title "Implementing distribution and persistence aspects with AspectJ", issn "11111" from the journal "ACM" and with "376" citations at Google Scholar is stored by the system
    When I try to find citations to "Implementing distribution and persistence aspects with AspectJ" from researcher "Paulo Borba".
    Then the "376" citations from the article "Implementing distribution and persistence aspects with AspectJ" are stored by the system

  Scenario: Data not found

    Given the researcher "Joao da Silva" with cpf "222222222" is stored by the system
    And the article with title "Assessing fine-grained feature dependencies", issn "22222" from the journal "ACM" and with "0" citations at Google Scholar is stored by the system
    When I try to find citations to "Assessing fine-grained feature dependencies" from researcher "Joao da Silva".
    Then no citations from the article "Assessing fine-grained feature dependencies" are stored by the system