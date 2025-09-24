Feature: Saisie du temps passe

  Background:
    Given user is logger
    And Max heures set to
    And user is on temps passe form

  Scenario Outline: Saisie du temps passe avec le datapool pour donnees valides
    When user enters data <Projet> and <Demande> and <Date> and <NbHeures> and <Commentaire> and <Activite> and click creer button
    Then succes message is displayed
    And temps passe is deleted
    And logout

    Examples:
      | Projet | Demande | Date | NbHeures | Commentaire | Activite |
      | projetpacellie | 958 | 16/07/2024 | 5 | testonsensemble | Analyse |
      | projetpacellie | 958 | 15/07/2024 | 6 | testons ensemble | Design |
      | projetpacellie | 958 | 15/07/2024 | 7 | testons ensemble | Test |

