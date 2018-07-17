1 App Overview
1.1 User inputs one or more pots’ name and weight into app.
1.2 In real life, user uses a pot to make a meal and weighs the pot with food in it.
1.3 In this app, user selects the pot they are going to use and enters the combined weight of the pot plus the food. The app subtracts the pot’s weight (when empty) from combined weight to compute weight of just the food.
1.4 Finally, user enters how many servings are in the food, and the app divides the weight of food by number of servings, displaying weight of each serving.

2. Pot Class JUnit
Using the skeleton online, implement the Pot class.
Write JUnit tests for the Pot class.
Achieve 100% code coverage of Pot’s lines code.
Write at least 4 test methods (may be closer to 8+).
All tests must pass, and be well named.

3. Required Application Features
3.1 General Requirements
Create an Android application targeting the minimum SDK version API 15 or lower (Android 4.0.3, IceCreamSandwich).
Change the name of the application (shown on all activities). This can be done in strings.xml.
All layout XML files cannot contain any hard-coded text displayed on the UI. Texts must come from strings.xml. Java code may still hard-code strings/outputs.
Screen-shots in this document are for inspiration; as long as the application correctly implements the required features, any nice and usable UI is acceptable.
None of the things listed as “hints” are required. You may choose to do them or not.
Activity files (.java and .xml) must be well named, but they need not match with this document.

3.2 Screen 1: Pot List
This is the initial activity displayed when app starts.
Display the pots which the user has already entered.
Show each pot’s name and weight (in grams).
Use the PotCollection class (provided on assignments webpage) to store the pots.
Use the Pot class to store an individual pot.
Allow the user to add a new pot to the collection by launching the Add Pot activity.
User may tap on a pot in the list to launch the Calculate activity.

3.3 Screen 2: Add Pot
Have entry boxes for the pot’s name and pot’s weight.
If using EditText widgets for data input, each widget must have a hint for what goes in it (such as
shown in the UI to the right).
May use other data entry widgets if desired.
The weight entry box must only allow a non-negative integer.
Have a way of either accepting (OK), or canceling adding the pot (such as the OK / Cancel buttons).

3.4 Screen 3: Calculate Serving
Display the selected pot’s name and weight.
Allow user to enter the combined weight of the pot with food in it.
UI must allow only a non-negative integer.
Calculate and display the weight of the food (in g).
Allow user to enter number of servings, which must be a non-negative integer.
Calculate and display the weight of each serving.
The activity must not crash if the user has not yet entered a number of servings, or if the number of servings is zero.
Have some mechanism to navigate back to the previous activity.
As the user types data into, or changes a value in either of the input boxes, recalculate the serving weight.

4. Optional Features

4.1 Edit Pot
Support editing a pot stored in the list of pots by long-pressing on the pot.
Hints:
Re-use the Add Pot activity and pass it extra data (via an Intent). This extra data is the current name and weight of the pot.
Have the Pot List activity track which pot is being edited, and when it gets back the new pot data (via the Intent), change the pot in the PotCollection instead of adding a new one.

4.2 Delete Pot
Support removing a pot from the list of pots.
UI must be clear to user.

4.3 Error Checking Input
Error check all user inputs.
Enforce at least the following:
1) pot name must be at least one character long, 
2) pot weight must be greater than or equal to zero, 
3) when an error is detected, put up a toast message with a helpful error message.

4.4 Save Data
Save between executions of the application all the pot data the user entered.
You may want to use SharedPreferences.
You may want to edit the PotCollection object to support working with a SharedPreference.

4.5 Action Bar Buttons
Use the action bar at the top of the activities to give the following buttons:
1) an "add a pot" button in Pot List activity, 
2) OK and Cancel buttons in Add Pot activity,
3) a back button to Pot List activity from Calculate activity.

4.6 UI Improvements
Significantly enhance the user interface by adding icons/images which are associated with each pot.
Change the Pot List display to feature complex a layout with icons and text.
