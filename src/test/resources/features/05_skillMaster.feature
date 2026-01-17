Feature: SkillMaster Functionality

    Scenario: Check if admin able to create a new Skill Master with valid endpoint
    Given Admin creates POST Request for the LMS API endpoint for skill
    When Admin calls "createSkill" with "POST" http request for skill master
    Then Admin receives 201 Status
        
    Scenario: Check if admin able to get all  Skill Master with valid endpoint
    Given Admin creates GET Request for the LMS API endpoint
    When Admin calls "getallSkillMaster" with "GET" http request for skill master
    Then Admin receives 200 Status
    
    Scenario: Check if admin able to create a new Skill Master with  existing skillname
    Given Admin creates POST Request for the LMS API endpoint with existing skill name
    When Admin calls "createSkill" with "POST" http request for skill master
    Then Admin receives 400 Status
    
    Scenario: Check if admin able to create a new Skill Master with empty body
    Given Admin creates POST Request for the LMS API endpoint with empty body
    When Admin calls "createSkill" with "POST" http request for skill master
    Then Admin receives 500 Status
    
    Scenario: Check if admin able to get Skills with invalid endpoint
    Given Admin creates GET Request for the LMS API endpoint
    When Admin calls "getbyskillname" with "GET" http request for skill master
    Then Admin receives 200 Status
    
    Scenario: Check if admin able to get Skills with invalid endpoint
    Given Admin creates GET Request for the LMS API endpoint
    When Admin calls "getbyinvalidskillname" with "GET" http request for skill master
    Then Admin receives 404 Status
    
    Scenario: Check if admin able to update Skills with valid payload
    Given Admin creates PUT Request for the LMS API endpoint with valid body
    When Admin calls "updateskill" with "PUT" http request for skill master
    Then Admin receives 200 Status code
    
     Scenario: Check if admin able to update Skills with invalid endpoint
    Given Admin creates PUT Request for the LMS API endpoint with invalid endpoint
    When Admin calls "updateskillwithinvalid" with "PUT" http request for skill master
    Then Admin receives 404 Status code
    
     Scenario: Check if admin able to delete Skills with valid endpoint
    Given Admin creates DELETE Request for the LMS API endpoint with valid endpoint
    When Admin calls "deleteskill" with "DELETE" http request for skill master
    Then Admin receives 200 Status code
    
     Scenario: Check if admin able to delete Skills with invalid endpoint
    Given Admin creates DELETE Request for the LMS API endpoint with invalid endpoint
    When Admin calls "deleteskillinvalid" with "DELETE" http request for skill master
    Then Admin receives 404 Status code
    
    
    
    
    
    

