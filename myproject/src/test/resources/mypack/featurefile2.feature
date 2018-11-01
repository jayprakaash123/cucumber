Feature:Logout

Scenario:Validate logout operation
Given launch site using "chrome"
When do login with valid data
|   mbno   |   pwd   |
|9579594531|1122334455|
And do logout
Then home page is reopen
And close site