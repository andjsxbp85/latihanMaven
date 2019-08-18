Meta:

Narrative:
As a user
I want to login to Reqres
So that I can see my Dashboard

Scenario: Login Reqres
Given I'm on https://Reqres.in
When I click button "login"
Then I Will go to dashboard

Scenario: Success Register Reqres and Get Recommended Friend
Given i'm on https://Reqres.in lol
When I click button register
Then user will showed about recommended friend
