# Shinchan Action Figure Collection Game

This is a Shinchan-themed game developed in Scala using the MVC (Model-View-Controller) architecture. The game features two exciting modes where players take on the role of Shinchan, competing either against time or his mother, to collect as many action figures as possible.

## Game Modes

The game offers two modes:

1. **Single Player Mode:**
   - In this mode, the player controls Shinchan, who must race against the clock to collect all the scattered action figures before time runs out.

2. **Multiplayer Mode (Shinchan vs His Mother):**
   - Shinchan competes against his mother. Both characters aim to collect as many action figures as possible within a given timeframe. The one who collects more action figures wins the game.
  
   - ## Architecture

This game is built using the MVC (Model-View-Controller) design pattern, which organizes the codebase as follows:

- **Model:** Handles the game logic and data. This includes the player's score, time, and collection of action figures.
  
- **View:** Manages the user interface (UI) and rendering of the game screen. It displays the game status, including the collected action figures and remaining time.

- **Controller:** Acts as the intermediary between the View and Model. It processes user inputs (e.g., movements and interactions) and updates the Model accordingly.

- ### Model
- Manages game states, rules, and logic.
- Keeps track of player progress in both game modes.
- Handles collision detection and player actions.

### View
- Displays the game world, including characters (Shinchan and his mother), action figures, and timers.
- Renders the game using simple graphical elements.

### Controller
- Receives user input (keyboard or gamepad).
- Updates the Model based on player actions.
- Ensures smooth interaction between the player and game mechanics.

### Graphics 
![image](https://github.com/user-attachments/assets/f1c082b7-6f22-4553-b493-42a38aae8834)

![image](https://github.com/user-attachments/assets/ab8dc677-85cb-464c-92a4-670af9e746a7)

![image](https://github.com/user-attachments/assets/48e27c3c-6310-4e5f-96fd-8d20731026fa)



