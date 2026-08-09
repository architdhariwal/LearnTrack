# LearnTrack - Environment Setup & Execution Instructions

This document provides step-by-step instructions to compile and run the LearnTrack Core Java project using IntelliJ IDEA or via the command line terminal.

---

## 1. Environment & Prerequisites

- **JDK Version**: OpenJDK 17+ or Oracle JDK 17 / 21
- **IDE**: IntelliJ IDEA (Community or Ultimate edition)
- **OS**: Windows, macOS, or Linux

To verify your Java installation from terminal:
```bash
java -version
javac -version
```

---

## 2. Running in IntelliJ IDEA

1. **Open Project**:
   - Open IntelliJ IDEA.
   - Click **File -> Open...** and select the `LearnTrack` folder (`e:\airtribe projects\LearnTrack`).

2. **Configure Project SDK & Source Root**:
   - Go to **File -> Project Structure -> Project**.
   - Ensure the **SDK** is set to JDK 17 (or installed JDK version).
   - Go to **Project Structure -> Modules**. Ensure `src` directory is marked as **Sources** (colored blue).

3. **Run the Application**:
   - Navigate to `src/com/airtribe/learntrack/Main.java`.
   - Right-click on `Main.java` and select **Run 'Main.main()'** (or click the green Run arrow next to `public static void main`).
   - The interactive console dashboard will appear in the IntelliJ IDEA **Run** window at the bottom.

---

## 3. Compiling and Running via Terminal / CLI

You can also compile and run LearnTrack directly using standard JDK terminal utilities:

### Step 1: Open Terminal in Project Directory
```cmd
cd "e:\airtribe projects\LearnTrack"
```

### Step 2: Create Build Directory & Compile
Compile all Java source files under `src/` into a target directory `bin`:

**Windows Command Prompt / PowerShell**:
```cmd
mkdir bin
javac -d bin src\com\airtribe\learntrack\*.java src\com\airtribe\learntrack\entity\*.java src\com\airtribe\learntrack\service\*.java src\com\airtribe\learntrack\ui\*.java src\com\airtribe\learntrack\util\*.java src\com\airtribe\learntrack\exception\*.java
```

### Step 3: Run the Application
Run the compiled `Main` class specifying `bin` in the classpath (`-cp`):

```cmd
java -cp bin com.airtribe.learntrack.Main
```

---

## 4. Understanding "Hello World" Execution Flow

When executing Java programs:
1. `javac` reads high-level source code (`.java`) and translates it into platform-independent intermediate **Bytecode** (`.class`).
2. `java` launches the **Java Virtual Machine (JVM)**, loads `.class` bytecode files into memory, executes static blocks, and invokes the entry method signature:
   ```java
   public static void main(String[] args)
   ```
3. In LearnTrack, `Main.main()` creates an instance of `MenuUI`, populates initial seed data, and initiates the command-line interface loop.
