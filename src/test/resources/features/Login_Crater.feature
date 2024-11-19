# 1. test case: Login Functionality
# steps:
# 1. navigate to login page
# 2. enter valid email address "entityadmin@primetechschool.com"
# 3. enter valid password "primetech@school"
# 4. click on the login button
# 5. verify the user is redirected to the dashboard
# 6. verify a success message is displayed


# we will use the (tag) @crater to run it by itself if we want to just by put the tags = "@crater" in the
# (TestRunner) class
@crater
Feature: User Access Management
  As a user I want to login to my crater application
# we will use the key word Background to keep all the repeated steps in the other scenarios, in it so we don't
# have to repeat them in the other scenarios anymore
  Background:
    Given user is navigated to Crater login page

  @smoke @regression
  Scenario: User should be able to login with valid credentials
    When user enters valid username and valid password
    And user clicks on login button
    Then user should be logged in successfully