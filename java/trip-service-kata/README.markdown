Testing legacy code: Hard-wired dependencies
============================================

Code related to my [Testing legacy code: Hard-wired dependencies][1] blog post. Try not reading the blog post before doing the exercise yourself.

What is it about?
-----------------

Provides an example of existing code that needs to be unit tested. But there is one rule:

> We can't change any existing code if not covered by tests. The only exception is if we need to change the code to add unit tests, but in this case, just automated refactorings (via IDE) are allowed. 

Although this is a very small piece of code, it has a lot of the problems that we find in legacy code. 

TripService
-------

### Business Rules
Imagine a social networking website for travelers
* You need to be logged in to see the content
* You need to be a friend to see someone else's trips
* If you are not logged in, the code throws an exception

### Exercise Rules
* Our job is to write test for the TripService class until we have 100% test coverage.
* Once we have 100% test coverage, we need to refactor and make the code better.
* At the end of the refactoring, both test and production code should clearly describe the business rules

### Exercise Constraints
* We cannot manually change production code if not covered by test.
* We CANNOT change the public interface of TripService, that means:
  * We cannot change its constructor
  * We cannot change the method signature
  * Both changes above might cause other classes to change, which is not desirable now.
* We CANNOT introduce state in the TripService:
  * TripService is stateless. Introducing state may cause multi-thread issues.