# Knowledge Siege 🎮
Advanced Programming Project – Java Swing Game

## Overview
Knowledge Siege is a Java-based 2D educational arcade game developed as part of COMP132: Advanced Programming (Spring 2025).
The player progresses through multiple levels, collecting information and question shotboxes while avoiding enemies.

The project emphasizes object-oriented design, Java Swing GUI development, file I/O, and clean separation of game logic and interface.

---

## Features
- User login and registration system with validation
- Educational gameplay using question and information shotboxes
- Persistent scoreboard system
- Multi-level gameplay with increasing difficulty
- Java Swing–based graphical user interface
- File-based data storage (no external database)
- Game event logging
- Custom exception handling

---

## Gameplay
- Player is controlled using left and right arrow keys
- Enemies move and shoot shotboxes:
  - Information shotbox → increases score
  - Question shotbox → decreases health
- Player advances levels by reaching score thresholds
- Game ends when health reaches zero or winning score is achieved

---

## Levels
- Level 1:
  - Section Leaders (SL)
  - Slow movement, basic shotboxes
- Level 2:
  - New Section Leaders + Teaching Assistants (TA)
  - Faster movement, stronger shotboxes
- Win condition: Reach 150 score
- Lose condition: Health reaches 0

---

## Project Structure
src/
├── game/ # Core game logic and controllers<br>
├── frames/ # GUI frames and panels<br>
├── database/ # Text-based data storage<br>
├── exceptions/ # Custom exception classes<br>
├── photos/ # Player and enemy images<br>


---

## Key Classes
- GameController – Manages levels, timers, collisions
- LoginController – Handles user authentication
- ScoreBoardManager – Loads and saves game scores
- GameSession – Stores and compares game session data
- Player – Represents the player
- Enemy (abstract) – Base class for all enemies
- ShotBoxes – Handles question and information logic

---

## Data Storage
All data is stored using `.txt` files:
- `usersdb.txt` – Registered users
- `scoretable.txt` – Game scores
- `question.txt` – Question content
- `information.txt` – Information content
- `log_game.txt` – Game event logs

---

## Validation Rules
- Username:
  - Starts with a letter
  - 3–20 characters
  - No special characters
- Password:
  - Minimum 8 characters
  - At least one digit
  - At least one special character
  - No spaces

---

## Error Handling
- File I/O exceptions handled gracefully
- Custom InvalidUserDataException used for:
  - Invalid login attempts
  - Regex validation failures
- Missing files are recreated when possible

---

## Technologies Used
- Java
- Java Swing
- Object-Oriented Programming
- File I/O

---

## Author
Elnur Ayyubov  
Computer Engineering Student  
Spring 2025
