# Java-Simple-Project

## Project Summary:
In this software system, admin can save multiple MCQ with 4 options with their answer key in a quiz.json file. And, when any student logs in to the system, system will tell the student to give MCQ exam from the quiz bank.
If a user logs in by admin credential, system will say the admin user to add new questions to question bank
and if any user logs in  by student credential, system will say the user to give quiz. When you login as a student to the system, system will generate 10 random questions from the quiz bank. Don't confuse if system will return you the duplicate questions. The more you add questions in quiz bank, probability of showing duplicate questions will be less. In this way, 10 questions will be shown and finally the system will show the result. You will not show the answer from the json. Just match the json file answer key with the user input key. If matches, score should be counted. Otherwise if user input wrong or invalid number from keyboard, it will be considered as 0 mark and generate the next question.

## Technology used:
- Intellij
- Jdk 11.0.18
  
