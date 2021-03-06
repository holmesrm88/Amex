README.md
Coding Task for Software Engineer Candidates
The following task will allow you to demonstrate your ability to deliver readable, maintainable and testable code. As an agile organization, we are applying equal emphasis on assessing both your problem solving and testing abilities.

The exercise should take around 60-90 minutes; please don’t spend much longer than this.
Please complete the following task in Java Use git to version control the code and once complete, send us a link to the Github (or similar) repository via the recruitment agent
Instructions: Complete the steps in order. Don’t read ahead. At each step build the simplest possible solution which meets the requirement. Tag and git commit after each step so that your approach is clear.
Step 1: Build an Orders Service
Build a service that’s able to receive simple orders of shopping goods from the command line Apples cost 60 cents and oranges cost 25 cents The service should be able to calculate that: [ Apple, Apple, Orange, Apple ] => $2.05 Make reasonable assumptions about the inputs to your solution; for example, candidates may take a list of strings as input
Add unit tests that validate your code

Step 2: Simple offer
The shop decides to introduce two new offers buy one get one free on Apples 3 for the price of 2 on Oranges
Update your functions & unit tests accordingly

Step 3: Build a Customer Notification Service
Customers complained that they don’t know if their orders made it through or not as there is no notification of success Build a service that listens for when orders are complete and sends a notification to the customer regarding its status and estimated delivery time The Mail service subscribes to events from the Orders service and publishes an appropriate event that the customer (you) is able to read from the terminal

Step 4: Limited Stock Stock can now run out, this means that customers need to be notified that their order failed

Optional Step 5: Events via Kafka (or another appropriate message queue, ie. Rabbit MQ) When the customer submits an order, this data is published via Kafka as an ‘OrderSubmitted’ event over an ‘order-submitted’ topic
Add additional appropriate topics that enable the conversion of existing events to events submitted over Kafka https://kafka.apache.org/quickstart https://docs.confluent.io/current/clients/java.html

------------------------------------------------


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
