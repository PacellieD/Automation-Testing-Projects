Feature: Saisie du temps passe


  Background:
    Given user is logger
    And Max heures set to
    And user is on temps passe form

  @Nominal
  Scenario: Saisie du temps passe avec les donnees valides
    When user enters data and click creer button
    Then succes message is displayed
    And temps passe is deleted
    And logout

  Scenario: Saisie du temps passe avec les donnees valides combinaison2
    When user enters data and click creer button combinaison2
    Then succes message is displayed
    And temps passe is deleted
    And logout