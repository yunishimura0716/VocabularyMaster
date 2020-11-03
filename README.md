# Vocabulary Master

## *create your original vocabulary book*

A *proposal*:
- What will the application do?
    - This application save **English** **words** and **idioms** which users add. 
    Users can refer the list and enjoy some practice game to reinforce their vocabulary. 
    (option: through attaching tag to each word and idiom, users can group their vocabulary by purpose.)
- Who will use it?
    - Anyone who worry about his or her vocabulary of English such as international students or 
    who want to learn terminologies of specific field like biology.
- Why is this project of interest?
    - As an international student whom English is a second language, I have difficulties to face unknown words and idioms 
    many times when I read or listen to. Additionally, such weak vocabulary affect my variety of speaking and writing words
    So, increasing vocabulary is necessary and significant for my living and academic studying.

## How to start this application
This is GUI app built in Java. Please make sure you have installed java on your environment.

Just run main method in Main class
#### Home Screen
![Screen Shot 2020-11-03 at 8 53 44 AM](https://user-images.githubusercontent.com/38684796/98016099-69b84400-1db2-11eb-865f-85597f4f0144.png)
#### list of vaocaburary
![Screen Shot 2020-11-03 at 8 54 10 AM](https://user-images.githubusercontent.com/38684796/98016107-6de46180-1db2-11eb-816d-efa6a62aa419.png)
#### quiz
![Screen Shot 2020-11-03 at 8 54 42 AM](https://user-images.githubusercontent.com/38684796/98016112-6e7cf800-1db2-11eb-90eb-481dee4b467a.png)
![Screen Shot 2020-11-03 at 8 54 58 AM](https://user-images.githubusercontent.com/38684796/98016114-6f158e80-1db2-11eb-87f7-8c3aefe2c0d9.png)

## User Stories
In the context of *Vocabulary Master* application:
- As a user, I want to be able to add a word, or an idiom with its meaning to my vocabulary list.
- As a user, I want to be able to add multiple words and idioms to my vocabulary list.
- As a user, I want to be able to view the list of my vocabulary.
- As a user, I want to be able to mark a word and idiom as remembered on my vocabulary list.
- As a user, I want to be able to delete a word and idiom from my vocabulary list
- As a user, I want to be able to play vocabulary test game among unremembered words and idiom.
- As a user, I want to be able to save my vocabulary list
- As a user, I want to be able to load my vocabulary list from a file when the program starts
- As a user, when I select the quit option from the application menu, I want the option to save my vocabulary list to file. 
- (if possible) As a user, I want to be able to search a word or an idiom among the list of my vocabulary.

## Instructions for Grader
- You can generate to add vocabulary by clicking "add" button on home page.<br>
Next, you write your vocabulary and its meaning then press "register" button to add them to your list.<br>
Also, you can add multiple words and idioms divided by comma.
- You can generate to see vocabulary list by clicking "list" button on home page.<br>
Then, you can see detail of a word or idiom if you select one and push "detail" button.<br>
On detail page, you can choose to delete or mark as remeber(or forget) to the vocabulary.
- You can do vocabulary quiz by clicking "quiz" button on home page.
- You can trigger my audio component by pressing any command button. (eg: "add", "list", "home", "detail", etc)
- You can save the state of your vocabulary list by clicking "save" button on list page or<br>
choosing "yes" when you exit from application.
- You can load the file data to vocabulary list by pushing "load" button on home page.

## Phase4: Task2
- I test and design "VocabularyList" class to be robust. I use own defined "VocabularyListBoundsException" exception class<br>
 for "view" and "delete" method. In "VocabularyListTest", I have a case to test throwing VocabularyListBoundsException <br>
properly and a case to test not throwing the exception.
- I have bi-directional relation between "GraphicalUI" class and "GraphicalAssist" class, also "ConsoleUI" class and <br>
"ConsoleUiAssist" class.

## Phase4: Task3
- In the first development, we made "GraphicalUI" class and "ConsoleUI" class individually. However, there are some method <br>
having the same signature or the same implementation. So, I made new abstract class "UserInterface" to make low coupling.<br> 
- Also, there is implementation of background music and button effect methods in "GraphicalUI" class. However, these methods <br>
are not responsible for "GraphicalUI" class, so I made new abstract class "Music" and its subclass "BackGroundMusic" and <br>
"ButtonEventMusic" to achieve high cohesion.
