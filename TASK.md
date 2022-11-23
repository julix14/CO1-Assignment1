# CO 1 Code Red

## Assignment 1

Winter Semester 2022/2023 

Prof. Dr. Mohammed AbuJarour 22.11.2022 

Assignment 1 — Personal Expenses


## 1.  Overview

 

In the context of this assignment, you will develop a program to manage personal expenses over a specific period of time among four categories: food, transport, shopping, and others. The user provides the expenses of each day for each category. Also, the user can set a target budget for the specified period. 

## 2. Requirements

The program has to ask the user for their name and use it in all subsequent interactions.  For example: 

    What is your name? John 
    
    Welcome, John! 
    ...
    
    What is your choice, John?
    ...

The program has to let the user manage their expenses over a period of time specified by the user in number of days. 

For example: 

    For how many days you want to manage expenses? 5
    Thank you, John! 

The program enables the user to enter their budget for the specified period For example: 

    What is your budget for the 5 days? 100 
    
    Thank you, John! 100 Euro for 5 days.

The program manages the expenses over four categories: food, transport, shopping, and others. So, the user is asked to provide their expenses of each day and for each category.

The program then displays a menu that the user can select from:

    (01) Display daily expenses 
    (02) Display relative and absolute remaining budget  
    (03) Display expenses per category
    (04) Display relative expenses per category
    (05) Display the day with highest expense
    (06) Display the category with the lowest expenses
    (07) Exit 

**The semantics of the options are as follows:**

01) Displays the sum of all expenses across the four categories per day. All daily sums are displayed in a user-friendly format. 
02) Displays the absolute and the relative value of the remaining budget (if any). For example, if the budget is set to 100 and the total expenses of all days is 90, the absolute and relative remaining budget are 10 and 10% respectively. 
03) Displays the total expenses among each category. 
04) Displays the relative expenses of each category with respect to the total expenses. For instance, if the user spent 30 on food, then the relative expense of food is 30%. 
05) Displays the day with the highest total expenses. You can also display the sum. 
06) Displays the category on which the user spent the least.
07) Quits the program.  

**The program exits only if the user selects to do so, otherwise, the menu is shown again.** 

## 3. Hints

 Assignment deadline: 04.12.2022 23:59 CEST. No late submissions. 
- Using any Java feature that we did not cover will result in a penalty 
- This is individual work. Your code will be checked for plagiarism.
- Split your code into methods to make it modular 
- Submit your code as an Eclipse project 
- All project artifacts have to be encoded in UTF-8 
- Export your Eclipse project as a single archive (.zip) and upload it through Teams 
- Use this naming scheme for your project AND archive file: Lastname-co1-assignment-01, where Lastname is replaced by your actual last name.
- Pay attention to the quality of your code, e.g., following the conventions and using expressive names of methods and variables, etc. 
