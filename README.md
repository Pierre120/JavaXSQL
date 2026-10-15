# JavaXSQL

Repository for LSCS Event: **"Zero to Query: Integrating Java and SQL"** (Oct. 2026)

## Cloning repository

Run in CMD/Terminal/Shell:
```
git clone https://github.com/Pierre120/JavaXSQL.git
```

## Switching or changing branch

Run in CMD/Terminal/Shell:
```
# Branch is already existing (ex. git checkout feat-branch-existing)
git checkout <branch>

# Create a new branch and switch/change to it (ex. git checkout -b feat-branch-new)
git checkout -b <branch>
```

## Pull changes from specific remote branch in current branch

Run in CMD/Terminal/Shell:
```
# Pulling a Remote Branch to (ex. git pull origin feat-branch-1)
git pull origin <remote-branch>

# Pulling and Creating a New Branch from a Remote Branch (ex. git checkout -b feature-branch origin/feature-branch)
# NOTE: This also tracks any changes from remote branch
git checkout -b <local-branch> origin/<remote-branch>
```

## Commiting changes in current branch

Run in CMD/Terminal/Shell:
```
# --- Typical flow
# Add/stage all changes
git add .

# Commits all staged changes
git commit -m "<commit_msg>"


# --- Shortcut (effective only if there are changes in previously committed changes/files)
# Add/stage and commit all changes
git commit -am "<commit_msg>"
```

## Pushing changes in current branch to remote branch

Run in CMD/Terminal/Shell:
```
# --- Typical Flow
# Push changes in current branch to remote branch (ex. git push origin feat-branch-1)
git push origin <remote-branch>
```
