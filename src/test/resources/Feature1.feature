Feature: Get Post Put Delete  operations
Scenario: Perform get request and verify response
    Given I call get request to the API
    Then I Should see the response body

Scenario: Perform get request and verfiy response
    Given I call get request to the API
    Then I should see the response headers

Scenario: Perform post request and verify reponse
    Given I call post request to the API
    Then I Should see the response body

Scenario: Perform put request and verify the response
  Given I call the put request and verify the response

Scenario: Perform delete request and verify the response
  Given I call the delete request "http://dummy.restapiexample.com/api/v1" and verify the response

