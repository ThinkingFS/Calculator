1. App Overview
	1. User inputs one or more pots’ name and weight into app.
	2. In real life: user then uses a pot to make a meal and weighs the pot with the food in it.
	3. In app: user selects the pot they used and enters the combined weight of the pot plus the
	food. Tool subtracts pot’s weight (when empty) from combined weight to compute weight
	of just the food.
	4. Finally, user enters how many servings are in the food and tool divides the weight of food
	by number of servings, displaying weight of each serving.
2. Pot Class JUnit
	Using the skeleton online, implement the Pot class.
	Write JUnit tests for your Pot class.
	You must achieve 100% code coverage of Pot’s lines code.
	You must write at least 4 test methods (may be closer to 8+).
	All tests must pass, and be well named.
3. Required Application Features
	Implementing “Required Features” can earn a good grade; to get a great grade, you must
	also complete some “Optional” features (listed later). See marking guide for details.
3.1 General Requirements
	Create an Android application targeting the minimum SDK version API 15 or lower
	(Android 4.0.3, IceCreamSandwich)
	Change the name of your application (shown on all activities). Do this in strings.xml.
	All layout XML files may not have any hard-coded text displayed on the UI: it must come
	from strings.xml. Java code may still hard-code strings/outputs.
	Screen-shots in this document are for inspiration; as long as your application correctly
	implements the required features, any nice and usable UI is fine.
	None of the things listed as “hints” are required; you may choose to do them or not.
	Activity files (.java and .xml) must be well named, but need not match this document.
	Create a GitLab repo on gitlab.cs.sfu.ca.
	Commit your changes often (at least every 4 hours of work).
	Generated Jan 23, 2017, 10:29 PM Page 1/6 Dr. Fraser ©
	Assignment 2: Serving Size Calculator CMPT 276 with Dr. Fraser
3.2 Screen 1: Pot List
	This is the initial activity displayed when app
	starts.
	Display the pots which the user has already
	entered.
	Show each pot’s name and weight (in grams).
	Use the PotCollection class (provided on
	assignments webpage) to store the pots.
	Use the Pot class to store an individual pot.
	Allow the user to add a new pot to the collection
	by launching the Add Pot activity.
	User may tap on a pot in the list to launch the
	Calculate activity.
Hints
	Instantiate a PotCollection as a member of
	your Pot List activity class.
	You may edit the PotCollection class as
	needed.
	Use a ListView to show the list of pots.
	Populate the ListView using an
	ArrayAdapter. 
	PotCollection class has the
	getPotDescriptions() method which can give you the array of Strings needed to
	populate the ListView via an ArrayAdapter.
	For initial testing (before you have any other screens working), try putting some test data
	into your PotCollection object.
	After adding a new pot you’ll need to refresh your list of pots. Just create a new
	ArrayAdapter and call setAdapter() on the ListView.
	Put this code in its own method to avoid duplicate code.
	For how to pass data to and from other activities, see hints for the next two activities.
	

3.3 Screen 2: Add Pot
Have entry boxes for the pot’s name and pot’s
weight.
If using EditText widgets for data input, each
must have a hint for what goes in it (such as
show in UI to the right).
May use other data entry widgets if desired.
The weight entry box must only allow nonnegative
integer values.
Have a way of either accepting (OK), or canceling
adding the pot (such as the OK / Cancel buttons).
Hints
Use a table layout for having the two EditText
views line up.
Likely use TextEdit boxes for data entry.
Convert a String to an int with:
int val = Integer.parseInt(“42”);
Note that if the String does not contain a
number it throws NumberFormatException.
You need-not (for the required features) handle
validating the user’s inputs (see Optional
Features section). So if user does not enter a
weight and clicks OK, your application may
crash; this is fine (for the core features!).
Hint on Passing Data back from an activity
Data is passed from one activity to another using an Intent. See video:
https://www.youtube.com/watch?v=SaXYFHYGLj4
Have the Add Pot activity “return” an Intent which stores the following in its Extra:
pot name (string)
pot weight in g (int)
Call this from List Pot’s onActivityResult(..) method.
Pot List activity calls startActivityForResult(...) to launch this activity.
Pot List activity overrides onActivityResult(...) to handle the “returned” data.
Rather than having Pot List activity know how to handle the “returned” intent from the Add
Pot activity, have the Add Pot activity expose the following method:
public static Pot getPotFromIntent(Intent data);
This encapsulates the details of how data is stored in the Intent to inside this activity.
Call this method and pass in the “returned” intent and it extracts the data about the pot
and puts it into a Pot object for us.
Generated Jan 23, 2017, 10:29 PM Page 3/6 Dr. Fraser ©
Figure 2: Possible look of add pot activity.
Assignment 2: Serving Size Calculator CMPT 276 with Dr. Fraser
3.4 Screen 3: Calculate Serving
Display the selected pot’s name and weight.
Allow user to enter the combined weight of the
pot with food in it.
UI must allow only a non-negative integer.
Calculate and display the weight of the food (in g)
Allow user to enter number of servings
Must be a non-negative integer.
Calculate and display the weight of each serving
(truncated to the nearest gram is fine).
Your activity must not crash if the user has not yet
entered a number of servings, or if the number of
servings is zero.
Have some mechanism to navigate back to the
previous activity.
As the user types data into, or changes a value in
either of the input boxes, recalculate the serving
weight.
Hints
When clicking the Back button (or the like), call
the finish() method to end the activity.
Pass data to the Calculate activity using an Intent.
Pass in the pot name and weight.
For good encapsulation, have the Calculate
activity expose a method which creates the
intent required to launch it. Pass this function the data to be encoded into the Intent. This
encapsulates how the pot’s information is stored in the Intent’s extras:
public static Intent makeLaunchIntent(Context context, Pot pot);
To recompute the serving-weight while the user is entering data, you’ll need to pass a
TextWatcher object to the addTextChangedListener(...) method of the
EditText. In this TextWatcher, put your code in afterTextChanged(...) which
recomputes the serving weight; other methods in TextWatcher can be left untouched.
If trying display a number in a TextView, note that
myTextView.setText(42);
will attempt to load the strings.xml resource number 42 into the TextView, which
likely does not exist and will crash your program. Instead, convert the into to a String:
myTextView.setText("" + 42);
Generated Jan 23, 2017, 10:29 PM Page 4/6 Dr. Fraser ©
Figure 3: Look of the main activity, part 1.
Assignment 2: Serving Size Calculator CMPT 276 with Dr. Fraser
4. Optional Features
By completing one or more of these features, you stand to move from “Good work” to
“Great work”. See marking guide.
You may only get marks for the optional features if the required parts of the application
work well.
If you attempt any of these features, your application’s first screen (Pot List activity) must
state the features you added. For example, have a TextView at the bottom of the screen
listing the features/directions.
Each feature have a reasonable user interface.
4.1 Edit Pot
Support editing a pot stored in the list of pots by long-pressing on the pot.
Hints:
Re-use the Add Pot activity and just pass it extra data (via an Intent). This extra data is
the current name and weight of the pot.
Have the Pot List activity track which pot is being edited and then when it gets back the
new pot data (via the Intent), have it change the pot in the PotCollection instead of
adding a new one.
4.2 Delete Pot
Support removing a pot from the list of pots.
Your choice how to do this; UI must be clear to user.
4.3 Error Checking Input
Error check all user input.
Enforce at least the following:
Pot name must be at least one character long.
Pot weight must be greater than or equal to zero.
When you detect an error, put up a toast with a helpful error message.
4.4 Save Data
Save between executions of your application all the pot data the user entered.
You may want to use SharedPreferences.
You may want to edit the PotCollection object to support working with a
SharedPreference.
4.5 Action Bar Buttons
Use the action bar at the top of the activities to give the following buttons:
Pot List activity: Add a pot
Add Pot activity: OK and Cancel
Calculate activity: Back to Pot List activity.
4.6 UI Improvements
Significantly enhance the user interface by adding icons/images which are associated with
each pot.
Change the Pot List display to feature complex a layout with icons and text.
See video https://www.youtube.com/watch?v=WRANgDgM2Zg
Generated Jan 23, 2017, 10:29 PM Page 5/6 Dr. Fraser ©
Assignment 2: Serving Size Calculator CMPT 276 with Dr. Fraser
5. Deliverables
To CourSys (https://courses.cs.sfu.ca/) you must submit:
1. Zip file of your project folder, as shown in this tutorial video:
https://youtu.be/238A85m45-w
2. git@csil-git1.cs.surrey.sfu.ca:.... URL to your git repository;
- you must add the TA for the course as a “Guest” member of your repo.
- you must submit the git@... URL
Please remember that all submissions will automatically be compared for unexplainable similar
submissions. Everyone's submissions will be quite similar, given the nature of this assignment,
but please make sure you do your own original work; we will still be checking.
Generated Jan 23, 2017, 10:29 PM Page 6/6 Dr. Fraser ©