#!/bin/bash
# Script to build the executable JAR file for The Coding Library

echo "Building The Coding Library JAR file..."
echo "======================================="

# Step 1: Clean up any existing .class files
echo "Step 1: Cleaning up old class files..."
rm -f *.class

# Step 2: Compile all Java source files
echo "Step 2: Compiling Java source files..."
javac --enable-preview --release 25 Book.java LibraryCatalog.java LibraryManager.java LibraryGUI.java

# Check if compilation was successful
if [ $? -ne 0 ]; then
    echo "ERROR: Compilation failed!"
    exit 1
fi

echo "Compilation successful!"

# Step 3: Create the JAR file
echo "Step 3: Creating JAR file..."
jar cfm TheCodingLibrary.jar manifest.txt *.class

# Check if JAR creation was successful
if [ $? -ne 0 ]; then
    echo "ERROR: JAR creation failed!"
    exit 1
fi

echo "======================================="
echo "✓ SUCCESS! JAR file created: TheCodingLibrary.jar"
echo ""
echo "To run the application, use:"
echo "  java -jar TheCodingLibrary.jar"
echo ""
