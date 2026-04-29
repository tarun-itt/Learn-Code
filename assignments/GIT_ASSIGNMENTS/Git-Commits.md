
## What does a commit contain?

A Git commit is an object that contains:

- A pointer to a **tree** (the snapshot of your project)
- Pointer(s) to **parent commit(s)**
- **Author** and timestamp
- **Commit message**

 A commit does NOT store file contents directly.

---

## Where are files stored?

Git stores data in three main object types:

- **Blob** → actual file content
- **Tree** → directory structure (maps filenames → blobs)
- **Commit** → points to a tree

### Structure

Commit → Tree → Blobs
```
Example:

Commit
↓
Tree
├── file1 → blob (content)
└── file2 → blob (content)

```
---

## How does Git store files in a commit?

When you run `git commit`:

1. Git takes the **staged files**
2. Creates new **blobs** for changed files
3. Reuses old blobs for unchanged files
4. Builds a new **tree** pointing to these blobs
5. Creates a **commit** pointing to that tree

---

## What is a "snapshot"?

A snapshot means:

 The complete state of your project at a specific moment

Even though only some files change:
- Git represents the **entire project**
- Unchanged files are reused (not duplicated)

---

## Are snapshots and commits the same?

They are related but not the same thing.

- **Snapshot** = the project state (stored in a tree)
- **Commit** = metadata + pointer to that snapshot

So, a commit *represents* a snapshot, but is not the snapshot itself

---