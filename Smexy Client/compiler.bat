@echo off
"C:\Program Files\Java\jdk1.7.0_09/bin/javac" -cp lib/*; -d bin -sourcepath src src/client.java
"C:\Program Files\Java\jdk1.7.0_09/bin/javac" -cp lib/*; -d bin -sourcepath src src/Loader.java
@pause