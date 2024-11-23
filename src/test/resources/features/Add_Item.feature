# BElow is an example of the manual test case for the gherkin
#1.	Navigate to Crater application (You are able to login successfully)
#2.	Navigate to the Items page.
#3.	Click on the Add Item button.
#4.	Enter the item name.
#5.	Enter the item description.
#6.	Enter the item price “12.99.
#7.	Select the item unit “grams”
#8.	Click the Save Item button.
#9.	Verify that the new item is listed in the items table.


# we add @crater so when we execute the crater app only scenarios with @crater will run
@crater
Feature: Add Item
  As a user
  I want to add an item to the list of item
  So i can see it as part of the list

  Scenario: Successfully add a new item
    Given user is logged in successfully
    And the user is on the item page
    When the user clicks on the Add Item button
    And the user enters the item name
    And the user enters the item description
    And the user enters the item price "12.99"
    And the user enters the item unit "Dollars"
    And the user clicks on the Save Item button
    Then the item should be listed in the items table
    And the item should be added to the database

# in line 26 we put "12.99" because we want to add it as a parameter so we can add any amount we want later so
# this will be translated in the step definition as a {double}, so anything you want to be treated as a parameter
# just put it in between " " and the same thing with "grams"

