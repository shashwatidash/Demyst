# Demyst :
Assignment done for demyst screening.

# Objective : Design a Loan Application system
It takes Business Details information from User, and provide him with Balance Sheet. Further the user reviews the balance sheet and request for Loan Approval. Based on the rules mentioned in the coding assignment, 
a preAssessment value is assigned which decides what percentage of the requested loan will be approved. 

# Rules to be applied before sending to Decision Engine
If a business has made a profit in the last 12 months. The final value to be sent with a field "preAssessment": "60" which means the Loan is favored to be approved 60% of the requested value. If the average asset value across 12 months is greater than the loan amount then "preAssessment": "100"
Default value to be used 20

# Sequence Diagram:
![image](https://github.com/shashwatidash/demyst/assets/42328420/8bab3d32-6249-4762-8632-dc6f4e0cb6a3)


# Technologies:
Used HTML/CSS and JavaScript for Frontend development, that you can directly run with the main file index.html
and Spring Boot for hosting backend server with REST API's.


# To run the java server:
Open the demyst-backend folder with IntelliJIdea, select the Java 20 SDK version in configurations.
Build the maven and run DemystApplication.java file.

# Screenshots:
![image](https://github.com/shashwatidash/demyst/assets/42328420/96b7d29e-2860-43af-8fbc-a3c27fd90ffa)
![image](https://github.com/shashwatidash/demyst/assets/42328420/eda29591-7c8d-4bfe-bb36-2e0b85979bd6)
![image](https://github.com/shashwatidash/demyst/assets/42328420/4c8fa64a-f915-427f-b3d4-1fd900b9ff4f)

