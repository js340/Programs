Student name: Jaskaran Singh  SKYPE NAME: live:.cid.b1fb16d15326d554
Student number: 190226901
LEVEL YOU WISH THE MINI-PROJECT TO BE MARKED FOR: 2
Now complete the statements below for each level you wish to be marked. Replace all text in square brackets.

LEVEL ONE
My code demonstrates inheritance in the following way...
I have a superclass called [Entity and Organism]
This superclass is extended into at least three subclasses called [Entity extends to Cell, Weapon and Heal and Organism extends to Player, 
Reptilian and Hopkinsville]
For each of the named subclasses complete the following...

Subclass 1.

Subclass [Reptilian] extends the superclass by adding at least one property and its getters and setters. The name(s) of the added properties 
are [Weapon weapon]
These/this new properties/property are used by the subclass in the following way: [Insert justification for adding the property and give the 
line numbers in the code of where the property is used.]
Overview:
The alien uses a weapon to attack the player. The weapon stores the weapons name and the damage it does. Alien objects are made from 
Reptilian class, Hopskinsville class and Organism class. The aliens made from Reptilians class have any weapon assigned to them which 
is passed to them through the constructor, Hopkinsville always have the same weapon so the weapon object is created in its constructor, 
and aliens created from the Organism class are called Greys and they deal the same amount of damage as their health so a weapon and any 
other variables are not needed so I didn’t make a subclass for it. 
Weapon weapon:
The variable weapon is of type Weapon which inherits the name variable from its superclass Entity and a integer called damage. 
Class Reptilian, Line 24 – returns a string which has the aliens information including the weapon they are holding, overrides the 
superclass method. 
Class Reptilian, Line 31 – returns the weapons damage, overrides the superclass method. 
Class Program, Line 231 to 252 – this is where the object arrayList is made, and in it we create alien objects, some of which are of 
type Reptilian and have a weapon object passed to them.

Subclass [Reptilian] extends the superclass by overriding the following methods (there must be at least one): [Insert names of overridden 
methods and their line numbers in the code.]
Class Reptilian, Line 17 overrides method in Class Organism, Line 49.
Class Reptilian, Line 23 overrides method in Class Organism, Line 81.
Class Reptilian, Line 30 overrides method in Class Organism, Line 29.

These overridden methods are used in the working code in the following places: [list the file names and line numbers where the overridden 
methods are called.]
getRandIntRange() – this method dictates how likely an alien is to block a players attack. This method overrides the one in the superclass 
Organism on line 49. We get a random integer between 1 and the value this method returns. If the integer returned was 1, the alien would 
block the attack. Originally I had the entire method getAttacked() (line 33 of superclass Organism) duplicated in this subclass but it 
was unnecessary as the only difference was the random number range (line 40), thus I kept that method just in the superclass and had 
another smaller method which got the upper variable of the range.
getPrintingInfo() - this method returns a string of information about the reptilian object to be printed to the terminal. This is 
specific information just about the object reptilian and wouldn’t apply to the other objects. The method it overrides is in Class 
Organism on line 81. 
getDamageAmmount() - Like I stated previously, the object that is created from the Organism class is called a Grey and they do the 
same damage as their health. So I have this method in the Organism class which returns the health of the alien as that would be its 
damage it deals to the player. In this case we return the damage of the weapon the alien has.
 

Subclass 2.

Subclass [Heal] extends the superclass by adding at least one property and its getters and setters. The name(s) of the added properties 
are [int regen, boolean used]
These/this new properties/property are used by the subclass in the following way: [Insert justification for adding the property and give 
the line numbers in the code of where the property is used.]
Overview:
This class Heal stores all the items that are used to restore the players health, the regen determines how much health the player will 
regen and the Boolean used will keep track if the item has been used yet or not, this way the player cannot keep drinking the same 
healing item over and over again. Once a item has been used it also gets a [used] next to its name to indicate to the player that they 
cannot use it anymore. 
int regen:
Class Heal, Line 36 – this line returns the string which is printed to show the healing items info, its name and its regen value. 
Class Program, Line 78 to 91 – these lines work together to add the health to the player and make it so that the item is no longer useable.  
Subclass [Heal] extends the superclass by overriding the following methods (there must be at least one): [Insert names of overridden 
methods and their line numbers in the code.] 
Class Heal, Line 35 overrides method in Class Entity, Line 16.
Class Heal, Line 41 overrides method in Class Entity, Line 21.
These overridden methods are used in the working code in the following places: [list the file names and line numbers where the overridden 
methods are called.]
getPrintingInfo() – this method returns what should be printed to the terminal where the cells item info should be and where the players 
healing item should be. The method in the superclass shouldn’t need to be run because all the cells have a item in them, either a weapon 
or item would override that method. If for whatever reason it doesn’t override, then we print out a empty string where the cells items is 
suppose to go. 
swapItem() – this method swaps the item in the cell with the corresponding one in the player object. If the cell contained a healing item 
then this method would override the method in the superclass. This method uses a temp object to hold the contents of the players healing 
item, it sets the cells heal item to the players and sets the temp object to the one in the cell. Then it prints out a unique message 
only applicable to the Heal class. 


Subclass 3.

Subclass [Weapon] extends the superclass by adding at least one property and its getters and setters. The name(s) of the added properties 
are [int damage]
These/this new properties/property are used by the subclass in the following way: [Insert justification for adding the property and give 
the line numbers in the code of where the property is used.]
Overview:
This variable keeps track of the damage a weapon does. The weapon damage can only be set once and that’s in the constructor when the 
object was created. There is only a getter which is used to see how much damage should be dealt to the recipient of an attack, either 
a alien or a player. 
Int damage:
Class Weapon, Line 19 – this line returns the weapons info, including the weapons damage. 
Class Program, Line 54 and 57 – line 54 attacks the alien by calling a method which gets the players damage from the weapon they 
currently have in their possession, and line 57 gets the damage from the alien, as long as the alien is not a grey as they don’t use 
weapons and their damage is equivalent to their health. 
Subclass [Weapon] extends the superclass by overriding the following methods (there must be at least one): [Insert names of overridden 
methods and their line numbers in the code.] 
Class Weapon, Line 19 overrides method in Class Entity, Line 16.
Class Weapon, Line 24 overrides method in Class Entity, Line 21.
These overridden methods are used in the working code in the following places: [list the file names and line numbers where the overridden 
methods are called.]
getPrintingInfo() – this method returns a string that is printed to the terminal to show the weapons information, its name and the damage 
it does.
swapItem() – this method swaps the items in the players inventory with the one in the cell. It also prints a unique message specific to 
the weapon class to show tell the player that the weapon has been swapped. The original method in the superclass returns an error if it 
is not overrun in order to show there is something wrong with what called the method. 


Subclass 4.

Subclass [Hopkinsville] extends the superclass by adding at least one property and its getters and setters. The name(s) of the added 
properties are [Weapon weapon]
These/this new properties/property are used by the subclass in the following way: [Insert justification for adding the property and 
give the line numbers in the code of where the property is used.]
Overview:
I searched for types of aliens and came across people seeing goblins in the city called Hopkinsville, so I named the class Hopkinsville 
and in it I create little goblins. All Hopkinsvilles have the same weapon, which is a goblin gun, it does little damage so they have a 
1/3 chance of blocking the players attack to compensate for their lack of damage. They all also have the same amount of health. The 
only difference between each Hopkinsville is their name. This class is similar to the Reptilian class but has some major differences 
which would be difficult/impossible to implement without having a class for Hopkinsville, I will explain why below.
Weapon weapon:
Class Hopkinsville, Line 23 – returns a string which has the aliens information including the weapon they are holding, overrides the 
superclass method. 
Class Hopkinsville, Line 30 – returns the weapons damage, overrides the superclass method. 
Class Program, Line 231 to 252 – this is where the object arrayList is made, and in it we create alien objects, some of which are of 
type Hopkinsville and we have their health and weapon set in the constructor so we only need to pass in their name. 
Subclass [Insert name of subclass] extends the superclass by overriding the following methods (there must be at least one): [Insert 
names of overridden methods and their line numbers in the code.] 
Class Hopkinsville, Line 16 overrides method in Class Organism, Line 49.
Class Hopkinsville, Line 22 overrides method in Class Organism, Line 81.
Class Hopkinsville, Line 29 overrides method in Class Organism, Line 29.
These overridden methods are used in the working code in the following places: [list the file names and line numbers where the overridden 
methods are called.]
getRandIntRange() – this method dictates how likely an alien is to block a players attack. This method overrides the one in the 
superclass Organism on line 49. We get a random integer between 1 and the value this method returns. If the integer returned was 1, 
the alien would block the attack. Originally I had the entire method getAttacked() (line 33 of superclass Organism) duplicated in 
this subclass but it was unnecessary as the only difference was the random number range (line 40), thus I kept that method just in 
the superclass and had another smaller method (which is this method) that got the upper variable of the range.
getPrintingInfo() - this method returns a string of information about the Hopkinsville object to be printed to the terminal. This is 
specific information just about the object reptilian and wouldn’t apply to the other objects. The method it overrides is in Class 
Organism on line 81. 
getDamageAmmount() - if the alien this was called on was of type Organism, a Grey, then the method in the Organism class would be 
called, it returns the health as that’s the amount of damage the Greys do. In this case the Hopkinsville has a weapon, all have the 
same weapon btw, and this method returns the values for the aliens weapon

[If you have more than three subclasses you want to tell us about, copy and paste the above text for as many subclasses as you want.]


LEVEL TWO

[In no more than three sentences and in your own words, define polymorphism.]
Polymorphism is a combination of dynamic binding, which leads to method overriding, and substitution principle. An example of the 
substitution principle is Organism alien = new Reptilian(); (Reptilian is a subclass of Organism, so it is fine to declare the object 
alien as the type Organism when thats not actually its type). Dynamic binding is when the compiler doesn’t know what method will be 
called upon an object when it compiles the code, it decides this at run time, thus the name 'dynamic'.


[List the places in your code where polymorphism is implemented, listing file names and line numbers.]
Program.java - line 232 to 252    (Substitution principle, new Cell takes in Organism and Entity, but I'm passing in the subclasses of 
those superclasses)
Program.java - line 157    (Dynamic binding, dont know which method will be called)
Program.java - line 155    (Dynamic binding, dont know which method will be called)
Program.java - line 101    (Dynamic binding, this methid is located within Heal and Weapon class)
Program.java - line 54    (Dynamic binding, dont know what method will be called because alien could be any type)

Weapon.java - line 18    (Dynamic binding, this method overrides the one in the superclass if applicable)
Weapon.java - line 24    (Dynamic binding, this method overrides the one in the superclass if applicable)

Heal.java - line 35    (Dynamic binding, this method overrides the one in the superclass if applicable)
Heal.java - line 41    (Dynamic binding, this method overrides the one in the superclass if applicable)

Hopkinsville.java - line 16    (Dynamic binding, this method overrides the one in the superclass if applicable)
Hopkinsville.java - line 22    (Dynamic binding, this method overrides the one in the superclass if applicable)
Hopkinsville.java - line 29    (Dynamic binding, this method overrides the one in the superclass if applicable)

Reptilian.java - line 17    (Dynamic binding, this method overrides the one in the superclass if applicable)
Reptilian.java - line 23    (Dynamic binding, this method overrides the one in the superclass if applicable)
Reptilian.java - line 30    (Dynamic binding, this method overrides the one in the superclass if applicable)


[Explain briefly (no more than four sentences), why polymorphism is necessary in your code.]
Polymorphism is necessary in my code for the following reasons; Cells contain either a weapon or healing item, so when the player wants 
to pick up an item from the cell the program does not know what that item is, therefore we NEED substitution principle in order to call
the correct method which would swap the cells weapon with the players weapon or the cells healing item with the players healing item. 
When we attack a alien, it will attack us back, each alien has a different chance of blocking an attack so we would need a way to differientiate
how the alien takes damage based on their type, thus we use dynamic binding. When an alien attacks us back we would need to know 
what alien it is as aliens made from the Organism class dont use weapons and attack the same as their health.



main method is in class Main.java, this code should work on mac but I use windows to make this and dont have a mac to check.

