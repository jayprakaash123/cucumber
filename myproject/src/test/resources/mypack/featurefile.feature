Feature: Login


Scenario:Validate site launching
Given launch site using "chrome"
Then title contain "free SMS"
And close site

Scenario Outline: Validate login operation
Given launch site using "chrome"
When enter mobile number as"<x>"
And enter password as"<y>"
And click login
Then validate output for criteria"<z>"
And close site

Examples:
|     x    |     y    |      z     |
|9579594531|1122334455|all_valid   |
|					 |1122334455|mbno_blank  |
|9579594531|					|pwd_blank	 |
|9579594533|1122334455|mdno_invalid|
|9579594531|112233456	|pwd_invalid |