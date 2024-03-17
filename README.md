# Pattern Manager Assigment

This project implements a pattern manager application that handles information about pattern calls. It provides functionalities to store pattern call tuples, retrieve pattern calls based on various criteria, and perform operations such as listing all pattern calls with specific attributes.

## Project Folder Structure 

```
project-root
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── (Java packages and classes)
│   │   
│   └── test
│       ├── java
│       │   └── (test packages and classes)
│       └── resources
│           └── test_tuples.txt
└── pom.xml
```

## Features
- Add pattern call tuples consisting of an identifier, name, file path, and call flag.
- Retrieve pattern calls by identifier, name, or file path.
- List all pattern calls with specific attributes (e.g., skipped, not skipped).
- Store pattern call tuples to a file and read pattern calls from a file.
- Demo program to showcase the implemented functionalities.


## Usage
- To run the demo program, execute the main class PatternManagerDemo located in the com.example.patternmanager.demo package.

- Follow the instructions provided by the demo program to interact with the pattern manager application and test its functionalities.



# Questions to be answered:

## Benefits of the design:

- The design uses a PatternManager class to encapsulate operations related to managing pattern calls, promoting clean code organization and separation of concerns.
- Utilizes object-oriented principles with a PatternCall class representing individual pattern calls, making the code more modular and maintainable.
- Provides methods for various queries required by the problem statement, enhancing code readability and usability.
- Implements file I/O operations for storing and reading pattern calls from files, ensuring data persistence.

## Improvement potential:

- Error handling could be enhanced, such as adding more robust exception handling for file I/O operations.
- Performance optimizations could be considered for large datasets, although the problem specifies that the tuples are assumed to fit in-memory.
- Performace of the retrieving patterns can be improved by introducing Map instead of list. By using a Map, we can achieve constant-time lookup for pattern calls based on their name and path, significantly improving the retrieval performance, especially for large datasets.

## Assumptions and trade-offs:

- Assumed that the size of pattern calls fits in memory to simplify the design and avoid dealing with disk-based storage mechanisms.

- Opted for linear time complexity (O(n)) for retrieving pattern calls by identifier, name, or path. While this ensures simplicity in implementation, it may not be the most efficient approach for large datasets. However, since the problem specifies that the tuples are assumed to fit in memory, this trade-off was deemed acceptable.


## Complexity (Big-O notation) of queries:

Retrieving a pattern call by identifier, listing pattern calls by name or path, and separating skipped and not skipped pattern calls are all linear operations, O(n), where n is the number of pattern calls.

## Part of the solution that took the most time:

- Design and coding took the most time as they involved understanding the problem requirements, designing a suitable solution, and implementing it in Java. 
- Documentation also required significant attention to ensure clarity and completeness.