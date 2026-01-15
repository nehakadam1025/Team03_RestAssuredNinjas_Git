Feature: SkillMaster Functionality
 Scenario: Check if admin able to get all  Skill Master with valid endpoint
    Given Admin creates GET Request for the LMS API endpoint
    When Admin calls "getallSkillMaster" with "GET" http request for skill master
    Then Admin receives 200 Status
    #Scenario: Check if admin able to get Skill Master Name with valid endpoint
    #Given Admin able to get Skill Master Name with valid endpoint
    #When Admin calls "getSkillMaster" with "GET" http request for skill master
    #Then Admin receives 200 Status