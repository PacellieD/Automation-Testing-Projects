@LoginPage
  Feature: La fonction de connexion sur Redmine

    @Nominal
    Scenario: Check Login is Valid
      Given  user is on login page
      When  user enter username
      And  user enter password
      And user click login button
      Then user is navigated to the home page
      And user click logout button