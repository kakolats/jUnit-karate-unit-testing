Feature: UserController Api Tests
  Background:
    * url baseUrl = 'http://localhost:8080/users'
  Scenario: Get All Users
    Given url baseUrl
    When method GET
    Then status 200
    And match each response == { id: '#number', name: '#string', adresse: '#string', password: '#string' }

  Scenario: Create new User
    Given url baseUrl
    And request { id: null, name: 'VoidEmperor', adresse : 'dakar', password: '234567890'}
    When method POST
    Then status 200
    And match response == { id: '#notnull', name: 'VoidEmperor', adresse : 'dakar', password: '234567890'}

  Scenario: Get User by ID
    Given path '1'
    When method GET
    Then status 200
    And match response == { id: 1, name: '#string', adresse: '#string', password: '#string' }

  Scenario: Update User
    Given url baseUrl
    And request { id: 1, name: 'Updated User', adresse : 'Updated Adresse', password: '987654321'}
    When method PUT
    Then status 200
    And match response == { id: 1, name: 'Updated User', adresse: 'Updated Adresse', password: '987654321'}

  Scenario: Delete User
    Given path '1'
    When method DELETE
    Then status 200
    And match response == 'Deletion successful'