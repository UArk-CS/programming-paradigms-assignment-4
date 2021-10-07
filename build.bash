#!/bin/bash
set -u -e
echo "Compiling code..."
echo ""
javac -d out src/main/java/*.java
echo "Compilation successful."
echo "Running application..."
echo ""
java -classpath out Game
