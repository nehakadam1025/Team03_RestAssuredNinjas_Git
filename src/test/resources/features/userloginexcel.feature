Feature: User Module - Admin Management
   

Scenario Outline: Admin login using Excel data
  Given Admin logs in using row <row>
  When Admin calls "postLoginApi" with "POST" http request
  Then Admin receives status code from row 1

Examples:
  | row | status |
  | 1   | 200    |