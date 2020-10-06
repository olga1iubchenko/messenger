Feature: Console mode testing

  @setUpConsoleMockAndConsoleReaderSpy
  Scenario: 01.01. Check valid input filtering
  Given console up and running
  When user enter valid input
  Then input filtered and returned list of attributes

  @setUpConsoleMockAndConsoleReaderSpy
  Scenario: 01.02. Check invalid input. Empty attribute
    Given console up and running
    When user enter empty required attribute
    Then NullPointerException occurs

  @setUpConsoleMockAndConsoleReaderSpy
  Scenario: 01.02. Check invalid input. Redundant attribute
    Given console up and running
    When user enter empty required attribute
    Then IndexOutOfBound occurs

