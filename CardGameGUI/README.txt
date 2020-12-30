NOTE: FURTHER DOCUMENTATION CAN BE FOUND WITHIN THE PROGRAM ITSELF.

ALSO NOTE: THAT MY CardGameGUI does not appear to output the correct text from its
	   GameEngineCallbackGUI even though I have set it up (Everything to INFO
	   instead of FINE) correctly. Though it will almost definitely work with 
	   your test CardGame.

VIEW:
- App
- CardPanel
- GameEngineCallbackGUI
- MainFrame
- PullDownMenu
- Table
- TopPanel

CONTROLLER:- AddPlayerPressed
- CloseProgram
- ClosePullDownMenu
- DealButtonPressed
- PlayerChanged
- RemovePlayerPressed
- ResetBetPressed
- SetBetPressed

SIMPLETESTCLIENTGUI:
- A2 Tester (RUN FROM HERE) <------------------------------------


SimpleTestClientGUI.A2Tester:

Responsible for creating the window and creating new App.

View.App:

Responsible for adding the GameEngineCallback's.

View.CardPanel:

Responsible for visually displaying all of the cards.

View.GameEngineCallbacGUI:

Deals Cards and gives results.

View.MainFrame:

Creates the main frame and puts all of the necessary elements in that frame.

View.PullDown:

Responsible for the file menu in the menuBar.

View.Table:

Responsible for everything that has anything to do with updating the table/summary panel.

View.TopPanel:

Responsible for everything to do with the toolBar and the statusBar.

Controller.AddPlayerPressed:

Responsible for everything to do with adding a new player when add player is pressed.

Controller.CloseProgram:

Responsible for closing the program when quit is pressed in the file menu.

Controller.ClosePullDownMenu:

Responsible for everything to do with closing the pulldown menu when close is pressed in the file menu.

Controller.DealButtonPressed:

Responsible for everything to do with dealing the player when the deal button is pressed.

Controller.PlayerChanged:

Responsible for everything to do with changing the player when a different player is selected in the comboBox.

Controller.RemovePlayerPressed:

Responsible for everything to do with removing the player when the remove player button is pressed.

Controller.ResetBetPressed:

Responsible for everything to do with reseting the players bet when the reset bet button is pressed.

Controller.SetBetPressed:

Responsible for everything to do with setting the players bet when the set bet button is pressed.



















