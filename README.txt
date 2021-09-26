Thoughts, findings, and considerations found while completing the assessment.

1) To run the command from the command line:
- navigate to the repository, and go into the src/main/java directory so that running the command 'ls'
  prints out AmexCodingTaskApplication.java
- run 'javac AmexCodingTaskApplication.java'
- run 'java AmexCodingTaskApplication Apple Orange Apple' with any combination of Apple and Orange

2) Assumptions taken while writing this application:
- Only Apple and Orange are available for use and thus will be sent through the command line
- Prices are set in the code
- Stock is set in the code
- When out of stock, the customer receives their original order - stock (ordered 9 - 3 stocked = 3 received).

3) While completing this, one line I had to keep reminding myself of is "build the simplest possible solution". This is
one of the reasons I chose to use Maps throughout the application as opposed to creating new Objects.

4) In the returnPrice() method, I had to make a spaceComplexity vs timeComplexity decision.  One thought I had was
to sort the array of groceries (Apple and Orange at this time) and use some sort of calculation (groceries[] - Apple)
to find the amount of oranges.  Instead I chose to cycle through the groceries[] and, with the use of a counter, put
the amount of each in a Map.  While a Map does have more overhead, the time to go through the list once O(n) and use a
map O(1) is less than the time required for Arrays.toSort() O(n*log(n)). Another thing to consider was the option to
scale. A Map would be much easier to scale as opposed to the (groceries - apple) calculation.

5) While working on Step 2, I found a couple dynamic algorithms that could have been used.
Buy One Get One: f(n) = f(n-2) + 1
Buy Three For Two: if n % 3 = 0 then f(n) = f(n-1)
                   if n % 3 != 0 then f(n) = f(n-1) + 1

Thank you for the opportunity to complete this coding assessment. I look forward to hearing any feedback.
Matt Holmes