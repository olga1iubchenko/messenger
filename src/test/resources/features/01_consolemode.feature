Feature: Console mode testing

  //@setUpConsoleMockAndConsoleReaderSpy
  Scenario Outline: 01.01. Check valid input filtering
  Given console up and running
  When user enter valid '<input>' to filter it
  Then filtered input are the same as '<expectedInput>'
    Examples:
      |input                                                                                        | expectedInput|
      | Test #{TestSubject} and  #{TestReceiverName} and #{TestSenderName} and #{TestSenderPosition}| "TestSubject","TestReceiverName", "TestSenderName", "TestSenderPosition"|

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

