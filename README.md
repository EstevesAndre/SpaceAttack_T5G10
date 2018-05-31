# Space Attack T5G10 LPOO

### Members
* André Filipe Pinto Esteves **201606673**, up201606673@fe.up.pt
* Luís Diogo dos Santos Teixeira da Silva, **201503730**, up201503730@fe.up.pt

### User Guide

#### Main Menu

![alt text](https://github.com/EstevesAndre/SpaceAttack_T5G10/blob/master/Screenshots/MainMenu.png)

The initial screen upon starting the game.  
  
Options:  
  
1 - Start playing the game  
2 - Open the options menu  
3 - Check the top previosly achieved scores  
4 - Exit the game  

#### Play Screen

![alt text](https://github.com/EstevesAndre/SpaceAttack_T5G10/blob/master/Screenshots/Play.png)

The objective of the game is to survive as long as possible the continuous waves of hostile spaceships, obtaining the highest score possible.  
  
  
Important screen elements:  
  
1 - The current score. Score automatically increases with the passing of the time, rewarding players for remaining alive. Also, destroying enemy ships will give significant score boosts. The tougher the ship destroyed, the higher is the score increase.  
  
2 - The amount of hits remaining. The players start with five hit points. Whenever a player is hit by an enemy shot, it loses a hit point. If a player loses all of his hit points, the game is over. It is possible for players to gain extra hit points during the game.  
  
3 - The throttle button. This button allows players to thrust their spaceship foward.  
  
4 - The fire button. This button allows players to fire shots at enemy spaceships.  
  
5 - The player spaceship. The spaceship controlled by the player, whose survival is the objective of the game. It will allways remain in the center of the screen, unless the player aproaches the edges of the map.  
  
6 - Enemy ships. These are the opponents of the game. They will try to shoot down your spaceship. Enemy spaceships will have different colors representing different models. It is possible that, upon destruction, an enemy spaceship leaves a power-up behind, that can be picked up by the player to increase his surviving chances.  
  
7 - Enemy shots. Contant between theese shots and the player spaceship will cause it to lose hit points.  
  
8 - Spawning gate. There are several spawning gates spread through the arena. They will periodically warp in enemy spaceships.  
  
  
Controls for Desktop version:  
  
Thrust Foward: Up Key/Throttle Button  
Thrust Backwards: Down Key  
Rotate Right: Right Key  
Rotate Left: Left Key  
Fire: Space Bar/Fire Button  
Pause: Click anywhere on screen/Esc Button  
  
Controls for Android version:  
  
Thrust Foward: Throttle Button  
Rotate Right/Left: Android Accelerometer  
Fire: Fire Button  
Pause: Click anywhere on the screen  
  
#### Pause Screen

![alt text](https://github.com/EstevesAndre/SpaceAttack_T5G10/blob/master/Screenshots/Pause.png)

Appears after the player pauses the game  
  
Options:  
  
1 - Continues the game immediatly, from whatever state it was when it was paused  
2 - Returns to main menu. No progress will be saved and it will not be possible to resume the game  
  
#### Game Over Screen

![alt text](https://github.com/EstevesAndre/SpaceAttack_T5G10/blob/master/Screenshots/GameOver.png)

Appears after a player loses their last hit point.  
  
Important Screen Elements:  
  
1 - Achieved score. If the score is high enough, it will be saved and can be viewd later in the Leaderboards screen.  
  
Options:  
  
2 - Play again  
3 - Returns to main menu  
4 - Exits the game  
  
#### Options Menu

![alt text](https://github.com/EstevesAndre/SpaceAttack_T5G10/blob/master/Screenshots/Options.png)

Allows the player to customize some options regarding the game. The preferences will be saves for future use of the game  
  
Options:  
  
1 - Change music volume  
2 - Enable/disable music  
3 - Change sound effects volume  
4 - Enable/disable sound effects  
5 - Returns to main menu  
  
#### Leaderboards Menu

![alt text](https://github.com/EstevesAndre/SpaceAttack_T5G10/blob/master/Screenshots/Leaderboard.png)

Allows player to view the top 10 scores achieved in previous games.  
  
Score information:  
  
1 - Position in the leader board  
2 - Score achieved  
3 - Date in which the game was played  
  
Options:  
  
4 - Return to main menu  

#### UML
![alt text](https://github.com/EstevesAndre/SpaceAttack_T5G10/blob/master/UML_2Deliver.png)

#### Design of behavioural aspects
![alt text](https://github.com/EstevesAndre/SpaceAttack_T5G10/blob/master/States.png)

#### Expected (if any) Design Patterns to use on your application
 * Uso de Singleton na classe Game e GameController.
 	Apenas faz sentido a utilização de uma instância da classe Game, pois apenas existe um jogo a decorrer de cada vez. Como tal, de 	 igual forma, também apenas faz sentido uma instância do respetivo GameController, o controlador desse jogo.

 * Uso de MVC.
 
 #### Overall time spent developing
 * We estimate a total of 100 hours (evenly distributed between the two developers).
