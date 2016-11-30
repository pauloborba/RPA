@i9n
Feature: Collecting citations

  As a member of a research group
  I want to import the history of citations to me at Google Scholar
  So that the system register the corresponding citations

  Scenario:  Integrity of data between the system and Google Scholar

    Given the researcher "Paulo Borba" with cpf "11111111111" is stored by the system
    And the article with title "PDC: Persistent data collections pattern", issn "11111", journal "ACM" from "Paulo Borba" is stored by the system
    And the system has no information about the citation amount to the article with title "PDC: Persistent data collections pattern".
    And the article with title "PDC: Persistent data collections pattern" has "43" citations at Google Scholar
    When I try to find citations to "PDC: Persistent data collections pattern" from researcher "Paulo Borba".
    Then the "43" citations to the article "PDC: Persistent data collections pattern" are stored by the system

  Scenario: Data not found

    Given the researcher "Joao da Silva" with cpf "22222222222" is stored by the system
    And the article with title "Assessing fine-grained feature dependencies", issn "22222", journal "ACM" from "Joao da Silva" is stored by the system
    And the system has no information about the citation amount to the article with title "Assessing fine-grained feature dependencies".
    And the article with title "Assessing fine-grained feature dependencies" has "0" citations at Google Scholar
    When I try to find citations to "Assessing fine-grained feature dependencies" from researcher "Joao da Silva".
    Then no citations to the article "Assessing fine-grained feature dependencies" are stored by the system

  Scenario: Request for number of citations of a researcher

    Given the researcher "Uirá Kulesza" with cpf "33333333333" is stored by the system
    And the article with title "Jat: A test automation framework for multi-agent systems", issn "44444", journal "ACM" from "Uirá Kulesza" is stored by the system
    And the article with title "Jat: A test automation framework for multi-agent systems" has "27" citations at Google Scholar
    And the article with title "Design patterns as aspects: A quantitative assessment", issn "55555", journal "ACM" from "Uirá Kulesza" is stored by the system
    And the article with title "Design patterns as aspects: A quantitative assessment" has "24" citations at Google Scholar
    And the researcher "Uirá Kulesza" has only "2" articles stored by the system: "Jat: A test automation framework for multi-agent systems" and "Design patterns as aspects: A quantitative assessment".
    When I try to find citations to "Uirá Kulesza"
    Then the "51" citations to the researcher "Uirá Kulesza" are stored by the system

  Scenario: Regular importing citations to article

    Given I am at the ArticleCitations Menu
    When I select the article "PDC: Persistent data collections pattern".
    And I ask to find citations to article
    Then the number "43" of citations to the article "PDC: Persistent data collections pattern" are shown

  Scenario: Irregular importing citations to article

    Given I am at the ArticleCitations Menu
    When I select the article "Any article out of the system".
    And I ask to find citations to article
    Then the message "Article not found" is displayed

  Scenario: Irregular importing citations to researcher

    Given I am at the ResearcherCitations Menu
    When I select the researcher "Andre Matos".
    And I ask to find citations to researcher
    Then the message "Researcher not found" is displayed
