Feature: Automation Login

  Scenario: Validate login operation of automation site
    Given launch site using"chrome"
    Then title contain"http://practice.automationtesting.in/my-account/"
    When do login with valid data
      | userid                             | password      |
      | jai.jaiviru.choudhary101@gmail.com | 9579594531jay |
    Then do logout
    Then close site
