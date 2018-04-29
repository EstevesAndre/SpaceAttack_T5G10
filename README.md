# Space Attack T5G10 LPOO

### Members
* André Filipe Pinto Esteves **201606673**, up201606673@fe.up.pt
* Luís Diogo dos Santos Teixeira da Silva, **201503730**, up201503730@fe.up.pt

### First Deliver

#### UML
![alt text](https://github.com/EstevesAndre/SpaceAttack_T5G10/blob/master/UML.png)

#### Design of behavioural aspects
![alt text](https://github.com/EstevesAndre/SpaceAttack_T5G10/blob/master/States.png)

#### Expected (if any) Design Patterns to use on your application
 * Uso de Singleton na classe Game e GameController.
 	Apenas faz sentido a utilização de uma instância da classe Game, pois apenas existe um jogo a decorrer de cada vez. Como tal, de 	 igual forma, também apenas faz sentido uma instância do respetivo GameController, o controlador desse jogo.

#### Identification/Listing of the main functionalities

* O User poderá circular pelos diversos menus, (Main Menu, Game Screen, Pause Menu, High Scores, Game End, Options e Exit).

* No SmartPhone (Android) pode-se circular ao tocar no ecrãs nos botões, etc.
* Já na versão de Desktop o mesmo é possível com o touchpad ou rato.

* Em Game Screen, onde a aplicação vai decorrer:

* Android
		
		-> O user ao clicar do lado direito do ecrã atira uma bala a partir da direcão e rotação atuais.
		
		-> Do lado direito existe um "joystick" onde se vai poder movimentar a nave pelo mapa (este sendo infinito).
		
		-> Clicando do lado esquerdo sem ser no joystick, a nave movimenta-se também mas sem rodar.

* Desktop 
		
		-> 1º opção: 
             
	    	Uso do rato, o posição do rato é para onde a nave está a apontar.		           
			
			Lado direito do rato movimenta a nave.		          
			  
			Lado esquerdo do rato atira uma bala.
	       
	       -> 2º opção: 
              	      
	      	Uso das setas do teclado, bem como das teclas "W", "A" e "D" para se movimentar.
		        	  
			E o espaço para atirar a bala.
		   
#### GUI Mock-up
![alt text](https://github.com/EstevesAndre/SpaceAttack_T5G10/blob/master/MockUP_GameView.png)

#### Listing of the expected final test cases
* Testes relativos ao package Model, testando diversos métodos, tais como sets e gets, assim como outras funções que afetam os atributos relativos aos elementos básicos do jogo
* Testes relativos ao package Controller, simulando o imput do utilizador e testando a resposta do controlador sobre a instância de Game

