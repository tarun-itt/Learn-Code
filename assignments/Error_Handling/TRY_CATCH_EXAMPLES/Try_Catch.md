## 1. Try-Catch-Finally

This is our standard way to handle checked exceptions or recover from known runtime errors.

*   `try`: Code that might throw an exception.
*   `catch`: Contains our error handling logic. We can use multiple blocks for specific exceptions.
*   `finally`: Code that executes no matter what. Mainly used before Java 7 for resource cleanup.

### When to use
*   When dealing with checked exceptions.
*   To gracefully recover from expected errors like invalid inputs or API timeouts.
*   In older legacy codebases where `try-with-resources` isn't available.

## 2. Try-With-Resources

Introduced in Java 7, providing a much cleaner way to manage resources. 

*   Resources declared in the `try(...)` parens are closed automatically when the block completes.
*   The resources must implement `AutoCloseable` or `Closeable`.

### When to use
*   Whenever we're working with streams, database connections, sockets, etc.
*   To avoid nested `finally` blocks and null checks just to call `.close()`.

## 3. Advanced Try-With-Resources

Some extra patterns we often leverage:

### Custom Resources
We can implement `AutoCloseable` on our own classes if we have custom cleanup logic (e.g., rolling back DB transactions).

### Multiple Resources
We can define multiple resources separated by a semicolon (`;`).
```java
try (ResourceA a = new ResourceA(); ResourceB b = new ResourceB()) { ... }
```
Java ensures these are closed in reverse order (`b` then `a`). This is super handy when an outer stream shouldn't close until the inner stream finishes writing/flushing.

### Execution Flow
If an exception occurs within our try block, the managed resources are closed *before* we enter the catch or finally logic.

### Suppressed Exceptions
If an exception happens in the try block, and another exception happens while `.close()` is called automatically, the first exception is the one that propagates up. The closing Exception is attached as a "suppressed" exception, which we can inspect via `e.getSuppressed()`.

### Java 9+ Syntax
If we already have an "effectively final" reference to a resource, we don't need to define a new variable in the `try` block:
```java
Scanner scanner = new Scanner(new File("test.txt"));
try (scanner) { /* ... */ }
```

## 4. Try-Finally

A variant where we omit the `catch` block entirely.

*   Exceptions aren't caught here—they propagate up to the caller. But the `finally` block still completely executes.

### When to use
*   **Concurrency:** To ensure we unlock an acquired `Lock` or release a `Semaphore` even if our critical section throws an exception.
*   **Restoring Context:** For reverting temporary state changes (like ThreadLocals) before exiting the method.
*   When we want the caller to handle the exception, but we need to run local cleanup steps.
