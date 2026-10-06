# Viva Questions and Answers

**Q1: What is OOP?**
A1: Object-Oriented Programming is a paradigm based on the concept of "objects", which contain data (attributes) and code (methods).

**Q2: Why use Java for this project?**
A2: Java provides strong OOP features, platform independence, built-in Collections, and robust exception handling.

**Q3: What is Encapsulation?**
A3: Wrapping data and methods into a single unit (class) and hiding details using `private` modifiers and getters/setters.

**Q4: Where is Inheritance used in your project?**
A4: `Admin` and `Developer` classes inherit from the abstract `User` class.

**Q5: Where is Polymorphism used?**
A5: The `displayDetails()` method is overridden in both Admin and Developer classes.

**Q6: What is Abstraction?**
A6: Hiding complex implementation details. Used in the `User` abstract class and `RepositoryOperations` interface.

**Q7: Why use an Interface?**
A7: To define a contract for repository operations without dictating the implementation.

**Q8: Why ArrayList?**
A8: Used to store dynamic lists of AI Models and Versions because it allows easy traversal and size modification.

**Q9: Why HashMap?**
A9: Used to store users for O(1) average time complexity when searching by User ID.

**Q10: What is Exception Handling?**
A10: A mechanism to handle runtime errors so the normal flow of the application is maintained.

**Q11: Why Custom Exceptions?**
A11: To provide specific error messages related to the business logic, like `ModelNotFoundException`.

**Q12: How is File Handling implemented?**
A12: Using `BufferedReader`, `BufferedWriter`, `FileReader`, and `FileWriter` to save and load data from `data/` directory.

**Q13: What is a Constructor?**
A13: A special method used to initialize objects.

**Q14: What is Method Overriding?**
A14: Providing a specific implementation in a subclass for a method already defined in its parent class.

**Q15: Why Packages?**
A15: To organize classes based on functionality (model, service, exceptions).

**Q16: What is the purpose of ModelVersion?**
A16: To track historical changes and accuracy improvements of a single AI model over time.

**Q17: How does search work?**
A17: It iterates through the ArrayList and checks if the keyword matches names, IDs, or frameworks.

**Q18: How is data persisted?**
A18: Modified objects are written to `.txt` files as comma-separated values upon operation completion.

**Q19: What are the limitations of this project?**
A19: It runs on a console, uses text files instead of a database, and lacks networking capabilities.

**Q20: What future enhancements can be added?**
A20: GUI, SQL Database, API integrations, and cloud storage.
