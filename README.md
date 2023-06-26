# Java-Simple-Project

## Project Summary:
In this software system, the admin can save multiple MCQs with four options with their answer key in a quiz.json file. And when any student logs in to the system, the system will tell the student to give MCQ exam from the quiz bank.
If a user logs in with an admin credential, the system will tell the admin user to add new questions to the question bank
and if any user logs in  with a student credential, the system will tell the user to give a quiz. When anyone logs in as a student to the system, the system will generate ten random questions from the quiz bank. Make sure the system will return the duplicate questions. The more you add questions to the quiz bank, the less likely you will show the same questions. This way, 10 questions will be directed, and finally, the system will show the result. Unfortunately, you won't be able to show the answer from the JSON. Just match the JSON file answer key with the user input key. If it matches, a score should be counted. Otherwise, if the user inputs a wrong or invalid number from the keyboard, it will be considered a 0 mark and generate the next question.
Here, in the source code, the FullSystem is the entire project, and the quiz bank is in the Quizlist.json file.

## Technology used:
- IntelliJij
- Jdk 11.0.18
  
