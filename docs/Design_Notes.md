# LearnTrack - Technical Design Notes

This document details key architectural decisions, OOP principles, and data structure choices implemented in LearnTrack.

---

## 1. Why `ArrayList` was chosen over fixed-size arrays (`T[]`)

In Java, fixed-size arrays (e.g. `Student[] students = new Student[100];`) require allocating a fixed memory capacity up front.

### Advantages of `java.util.ArrayList`:
1. **Dynamic Resizing**: `ArrayList` automatically resizes its internal array buffer as new elements (Students, Courses, Enrollments) are added or removed.
2. **Built-in Methods**: Provides rich, built-in methods for traversal (`for-each`), search, index lookup, and size queries (`.size()`, `.add()`, `.get()`).
3. **Memory Efficiency**: Avoids reserving arbitrary large memory arrays or handling empty index gaps (`null` slots) when elements are deleted.

---

## 2. Where static members were used and why

Static members belong to the class itself rather than individual instances. In LearnTrack:

### Usage: `com.airtribe.learntrack.util.IdGenerator`
- **Static Counter Fields**: `private static int studentIdCounter = 101;`, `courseIdCounter`, `enrollmentIdCounter`.
- **Static Generator Methods**: `public static synchronized int getNextStudentId()`.

### Rationale:
- **Global ID State**: Auto-incrementing IDs must be globally shared across all instances of students or courses. Storing the counter on an instance level would reset or duplicate IDs upon every new object creation.
- **Utility Convenience**: Static methods allow calling `IdGenerator.getNextStudentId()` directly without needing to instantiate an `IdGenerator` object.

---

## 3. Where inheritance was used and what was gained

### Hierarchy Structure:
- Base Parent Class: `com.airtribe.learntrack.entity.Person`
- Subclasses: `Student extends Person`, `Trainer extends Person`

### Benefits & Gains:
1. **Code Reusability**: Common personal attributes (`id`, `firstName`, `lastName`, `email`) and methods (`getFullName()`, getters/setters) are defined once in `Person`. `Student` and `Trainer` inherit these fields, eliminating duplicate code.
2. **Constructors with `super(...)`**: Subclasses use `super(...)` to delegate core initialization to `Person`, focusing subclass constructors on specialized fields like `batch` or `specialization`.
3. **Polymorphism via Method Overriding**: `Person` defines a default `getDisplayName()` method. `Student` overrides `getDisplayName()` to append batch and active status, allowing dynamic runtime dispatch when treating entities generically.

---

## 4. Clean Code Principles & Naming Conventions

### Meaningful & Intent-Revealing Names:
- Methods are named strictly after their business domain action:
  - `addStudent()` (not `insert1()` or `doWork()`)
  - `getStudentById()` (not `find()`)
  - `deactivateStudent()` (not `flag()`)
  - `addCourse()`, `getCourseById()`, `toggleCourseStatus()`
  - `enrollStudent()`, `getEnrollmentsByStudent()`, `updateEnrollmentStatus()`

### Small & Single-Responsibility Methods:
- Each method performs a single task (e.g. `IdGenerator.getNextStudentId()` only handles ID counter increments).
- Separation of Concerns: Entity classes handle data structure state, Service classes handle business logic & storage, and UI classes handle console rendering.

