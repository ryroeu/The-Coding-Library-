# The Coding Library 📚

A comprehensive library management system designed to help beginner and intermediate programmers discover and learn from programming books across various languages.

## 🎯 Project Overview

The Coding Library is a Java-based library management system that allows users to browse, check out, and return programming books. The project aims to create an intuitive interface for managing a collection of educational programming resources.

### Current Features
- Book catalog management with title, author, and ISBN tracking
- Check-out and return functionality
- Real-time availability status for all books
- User-friendly command-line interface
- Search books by ISBN or title

### Future Goals
- Graphical User Interface (GUI) with book cover images
- Audio content integration
- Enhanced search and filtering capabilities
- User account management
- Book recommendations based on programming language

## 👥 Contributors

This project is a collaborative effort by:
- **Kenneth Elmore** - Book Object Architecture
- **Trinity Young** - Catalog & Collection Management  
- **Ryan Roberts** - User Interface & Main System Logic

## 🏗️ Project Architecture

### Core Classes

#### `Book.java` (Author: Kenneth Elmore)
Handles the creation and management of individual book objects.
- **Attributes**: title, author, ISBN, checkout status
- **Methods**: 
  - Getters for all book properties
  - `checkoutBook()` - Marks book as checked out
  - `checkinBook()` - Marks book as returned
  - `isCheckedOut()` / `isOut()` - Check availability status
  - `setCheckedOut()` - Update checkout status

#### `LibraryCatalog` (Author: Trinity Young)
Manages the collection of books using an ArrayList and provides status information.
- **Functionality**:
  - Add books to the catalog
  - List all books with their current status
  - Check out books by title
  - Return books by title
  - Query individual book status
  - Search and retrieval operations

#### `LibraryManager.java` (Author: Ryan Roberts)
Provides the main user interface and interaction logic.
- **Features**:
  - Interactive menu system
  - Check out books by ISBN
  - Check in (return) books by ISBN
  - View status of specific books
  - Display all books in the collection
  - User input validation

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- A Java IDE or text editor
- Terminal/Command prompt

### Installation

1. Clone the repository:
```bash
git clone https://github.com/kenelmor/The-Coding-Library-.git
cd The-Coding-Library-
```

2. Compile the Java files:
```bash
javac Book.java LibraryCatalog LibraryManager.java
```

3. Run the application:
```bash
java LibraryManager
```

## 📖 Usage

When you run the application, you'll be presented with a menu:

```
========== The Coding Library ==========
1. Check Out a Book
2. Check In a Book
3. Check Book Status
4. View All Books
5. Exit
========================================
```

- **Option 1**: Enter the ISBN of a book to check it out
- **Option 2**: Enter the ISBN of a book to return it
- **Option 3**: Check if a specific book is available or checked out
- **Option 4**: View the complete catalog with availability status
- **Option 5**: Exit the application

## 📝 Sample Books

The system comes pre-loaded with sample programming books:
- Java Programming (ISBN001)
- Python Basics (ISBN002)
- JavaScript Essentials (ISBN003)
- C++ Fundamentals (ISBN004)

## 🔧 Development Status

**Current Phase**: Core functionality implementation complete

**Completed**:
- ✅ Book object model
- ✅ ArrayList-based catalog system
- ✅ Check-out/check-in operations
- ✅ Status tracking and reporting
- ✅ Command-line interface

**In Progress**:
- 🔄 Integration of all three core classes
- 🔄 Enhanced error handling
- 🔄 Extended book metadata

**Planned**:
- 📋 GUI implementation
- 📋 Persistent data storage (file I/O or database)
- 📋 User authentication system
- 📋 Book search by multiple criteria
- 📋 Due dates and late fees
- 📋 Book reservations

## 🤝 Contributing

This is a student project for learning purposes. Team members contribute according to their assigned components. External contributions are welcome via pull requests.

## 📄 License

This project is created for educational purposes.

## 📧 Contact

For questions or suggestions, please contact the project contributors through their respective GitHub profiles. 
