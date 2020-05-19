/******************************************************************************

FINISHED - Jaskaran Singh - FINISHED ON THE 02/111/2019

Purpose:
You take care of 3 aliens, making sure they survive for as long as
possible. The game is over once one of the aliens die, then the final score is
printed to the terminal, with the name of all the aliens and how long they
survived for. Once a name for each of the aliens is chosen, the program shows
all the aliens, their randomly generated health as well as sorting them from
least total health to most total health.

******************************************************************************/
import java.util.*;
import java.io.*;

public class alien{

  public static void main (String[] args){

    instructions();  //prints instructions

    //print out the highscore
    try {
      String compareingString = readFile("HighScore.txt");
      System.out.println("Your previous score is " + compareingString);
    } catch (IOException e) {
      e.printStackTrace();
    }


    // makes the 3 new aliens
    MakeAlien alien1 = new MakeAlien();
    MakeAlien alien2 = new MakeAlien();
    MakeAlien alien3 = new MakeAlien();

    //sets days lived for each alien to 0
    setDaysLived(alien1, 0);
    setDaysLived(alien2, 0);
    setDaysLived(alien3, 0);

    // sets the name of the 3 aliens
    setName(alien1, getInput("What do you want alien 1 to be called?"));
    setName(alien2, getInput("What do you want alien 2 to be called?"));
    setName(alien3, getInput("What do you want alien 3 to be called?"));

    //create array to store each aliens stats
    int [] arrayAlien1 = new int [3];
    int [] arrayAlien2 = new int [3];
    int [] arrayAlien3 = new int [3];

    //set random numbers in the arrays
    setRandomValuesInArray(arrayAlien1);
    setRandomValuesInArray(arrayAlien2);
    setRandomValuesInArray(arrayAlien3);

    //hacks
    if (getName(alien1).equals("jass")){
      for(int i = 0; i < 3; i++){
        arrayAlien1[i] = 10;
        arrayAlien2[i] = 10;
        arrayAlien3[i] = 10;
      }
    }
    if (getName(alien1).equals("manjit")){
      for(int i = 0; i < 3; i++){
        arrayAlien1[i] = 1;
        arrayAlien2[i] = 1;
        arrayAlien3[i] = 1;
      }
    }

    //sets records values to the ones randomly set in the array
    setAlienStatsFromArray(alien1, arrayAlien1);
    setAlienStatsFromArray(alien2, arrayAlien2);
    setAlienStatsFromArray(alien3, arrayAlien3);

    //sums up the happiness, hunger and sleep of a alien
    int totalHealthAlien1 = calculateTotalHealth(alien1);
    int totalHealthAlien2 = calculateTotalHealth(alien2);
    int totalHealthAlien3 = calculateTotalHealth(alien3);

    //new array for bubblesort
    int[] bubbleSortArray = new int[3];
    bubbleSortArray[0] = totalHealthAlien1;
    bubbleSortArray[1] = totalHealthAlien2;
    bubbleSortArray[2] = totalHealthAlien3;

    //array of names of the aliens, this will be changed the same as the bubbleSortArray above, so I know what order the aliens are in
    String[] namesArray = new String[3];
    namesArray[0] = getName(alien1);
    namesArray[1] = getName(alien2);
    namesArray[2] = getName(alien3);

    //does bubble sort on the bubbleSortArray and copies those changes to the namesArray so I know what changes have been made
    int[] sortedArray = bubbleSort(bubbleSortArray, namesArray);

    //print out 3 aliens
    printOut("Here are your 3 new pets");
    printAlien1(alien1);
    printAlienStats(alien1);
    printAlien2(alien2);
    printAlienStats(alien2);
    printAlien3(alien3);
    printAlienStats(alien3);

    //prints out ordered aliens names
    printOut("\nHere are your aliens sorted in order of weakest to strongest: \n" + namesArray[0] + "\n" + namesArray[1] + "\n" + namesArray[2]);

    while (true){

      String input = getInput("What alien do you want to go to? "  + "\n" + getName(alien1) + "\n" + getName(alien2) + "\n" + getName(alien3));
      if (input.equals(getName(alien1))){
        doAlien1Action(alien1);
        if (getHappiness(alien1) == 0 || getHunger(alien1) == 0 || getSleep(alien1) == 0){  //if the aliens were to have a stat that equals 0, they would die and end the game
          break;
        }
      } else if (input.equals(getName(alien2))){
        doAlien2Action(alien2);
        if (getHappiness(alien2) == 0 || getHunger(alien2) == 0 || getSleep(alien2) == 0){  //if the aliens were to have a stat that equals 0, they would die and end the game
          break;
        }
      } else if (input.equals(getName(alien3))){
        doAlien3Action(alien3);
        if (getHappiness(alien3) == 0 || getHunger(alien3) == 0 || getSleep(alien3) == 0){  //if the aliens were to have a stat that equals 0, they would die and end the game
          break;
        }
      } else {
        printOut("Invalid response, please try again.");  //if the user does not type the name of a alien, it would ask them to input another response
      }

    }

    gameOver();  //prints game over art

    //gets all the days lived by each alien
    int alien1DaysLived = getDaysLived(alien1);
    int alien2DaysLived = getDaysLived(alien2);
    int alien3DaysLived = getDaysLived(alien3);

    //calculates the total score for the user
    int totalHighScore = alien1DaysLived + alien2DaysLived + alien3DaysLived;
    String printHighScore = String.valueOf(totalHighScore);  //convert int to string

    //prints out how many days each alien lived for
    printOut(getName(alien1) + " lasted a total of " + alien1DaysLived + " days");
    printOut(getName(alien2) + " lasted a total of " + alien2DaysLived + " days");
    printOut(getName(alien3) + " lasted a total of " + alien3DaysLived + " days");
    printOut("Your total score is " + totalHighScore);


    try {
      String compareingString = readFile("HighScore.txt");  //gets whats stored in highscore file

      int i = Integer.parseInt(compareingString);  //converts to integer to be compared to current score

      if (totalHighScore > i){  //if the current score is bigger than the previous high score, it gets overwritten
        printOut("You beat your previous highscore!");
        File file = new File("HighScore.txt");

        try (Writer writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(printHighScore);
        } catch (IOException e) {
            e.printStackTrace();
        }
      } else {
        printOut("You did not beat the highscore :(");
      }
    } catch (IOException e){
      e.printStackTrace();
    }





  }

  //reads file
  public static String readFile(String fileName) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    try {
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            //sb.append("\n");
            line = br.readLine();
        }
        return sb.toString();
    } finally {
        br.close();
    }
  }


  //does happiness
  public static void doHappiness(MakeAlien alien) {
    int n = getHappiness(alien);
    setHappiness(alien, n+1);
    n = getHunger(alien);
    setHunger(alien, n-1);
    n = getSleep(alien);
    setSleep(alien, n-1);
    int temp = getDaysLived(alien);
    temp++;
    setDaysLived(alien, temp);
  }

  //does hunger
  public static void doHunger(MakeAlien alien) {
    int n = getHappiness(alien);
    setHappiness(alien, n-1);
    n = getHunger(alien);
    setHunger(alien, n+1);
    n = getSleep(alien);
    setSleep(alien, n-1);
    int temp = getDaysLived(alien);
    temp++;
    setDaysLived(alien, temp);
  }

  //does sleep
  public static void doSleep(MakeAlien alien) {
    int n = getHappiness(alien);
    setHappiness(alien, n-1);
    n = getHunger(alien);
    setHunger(alien, n-1);
    n = getSleep(alien);
    setSleep(alien, n+1);
    int temp = getDaysLived(alien);
    temp++;
    setDaysLived(alien, temp);
  }


  //does alien1 actions
  public static void doAlien1Action (MakeAlien alien){
    while(true){
      printAlien1(alien);
      printAlienStats(alien);

      printOptions();

      String input = getInput("What do you want to do?");

      if (input.equals("1")){
        doHappiness(alien);
      }else if (input.equals("2")){
        doHunger(alien);
      }else if (input.equals("3")){
        doSleep(alien);
      }else if (input.equals("c")){
        return;
      }else {
        printOut("Thats not a option, please try again");
      }

      if (getHappiness(alien) == 0 || getHunger(alien) == 0 || getSleep(alien) == 0){
        return;
      }


    }
  }

  //does alien2 actions
  public static void doAlien2Action (MakeAlien alien){
    while(true){
      printAlien2(alien);
      printAlienStats(alien);

      printOptions();

      String input = getInput("What do you want to do?");

      if (input.equals("1")){
        doHappiness(alien);
      }else if (input.equals("2")){
        doHunger(alien);
      }else if (input.equals("3")){
        doSleep(alien);
      }else if (input.equals("c")){
        return;
      }else {
        printOut("Thats not a option, please try again");
      }

      if (getHappiness(alien) == 0 || getHunger(alien) == 0 || getSleep(alien) == 0){
        return;
      }
    }

  }
  //does alien3 actions
  public static void doAlien3Action (MakeAlien alien){
    while(true){
      printAlien3(alien);
      printAlienStats(alien);

      printOptions();

      String input = getInput("What do you want to do?");

      if (input.equals("1")){
        doHappiness(alien);
      }else if (input.equals("2")){
        doHunger(alien);
      }else if (input.equals("3")){
        doSleep(alien);
      }else if (input.equals("c")){
        return;
      }else {
        printOut("Thats not a option, please try again");
      }

      if (getHappiness(alien) == 0 || getHunger(alien) == 0 || getSleep(alien) == 0){
        return;
      }

    }

  }

  //prints out options for the user
  public static void printOptions(){
    printOut("1 = Give Happiness \n2 = Give Hunger \n3 = Give Sleep \nC = Change Alien");
  }

  //orders aliens from least health to most health using bubble sort
  public static int[] bubbleSort(int[] array, String[] namesArray){
    int n = array.length;
    for (int i = 0; i < n-1; i++){
      for (int j = 0; j < n-i-1; j++){
        if (array[j] > array[j+1]){
          //swap numbers using temp
          int temp = array[j];
          array[j] = array[j+1];
          array[j+1] = temp;

          String nameTemp = namesArray[j];
          namesArray[j] = namesArray[j+1];
          namesArray[j+1] = nameTemp;

        }
      }
    }
    return array;
  }

  //it calculate sthe total health for a alien
  public static int calculateTotalHealth (MakeAlien a){
    int sum = a.happiness + a.hunger + a.sleep;
    return sum;
  }

  //sets the record alien with the correct stats from the random numbers from the array
  public static void setAlienStatsFromArray (MakeAlien alien, int[] array){
    setHappiness(alien, array[0]);
    setHunger(alien, array[1]);
    setSleep(alien, array[2]);
  }

  //sets random values in the array
  public static void setRandomValuesInArray(int[] a){
    for (int i = 0; i<3; i++){
      Random rand = new Random();
      int rand1 = rand.nextInt(8) + 3;  //chooses number between 3-10
      a[i] = rand1;
    }
  }

  //prints out the stats of an alien
  public static void printAlienStats(MakeAlien alien){
    int[] a = new int[3];
    a[0] = getHappiness(alien);
    a[1] = getHunger(alien);
    a[2] = getSleep(alien);

    System.out.print("Happiness: ");
    for (int i=0; i<a[0]; i++){
      printOutNoNextLine("♡");
    }
    System.out.print("\nHunger:    ");
    for (int i=0; i<a[1]; i++){
      printOutNoNextLine("♡");
    }
    System.out.print("\nSleep:     ");
    for (int i=0; i<a[2]; i++){
      printOutNoNextLine("♡");
    }
      printOutNoNextLine("\n");
  }

  //print out instructions for the game
  public static void instructions(){
    printOut("This is the ALIEN PET PROGRAM created by Jass. The aliens happiness, " +
            "hunger and sleep will be \"randomly\" generated. You will then look after " +
            "the pets for as long as possible before one of them inevitably dies. " +
            "Then the game is over. ");
  }

  //prints out the alien1
  public static void printAlien1(MakeAlien alien){
    printOut("\n" + getName(alien));
    printOut( "░▄▀▄▀▀▀▀▄▀▄░░░░░░░░░\n" +
              "░█░░░░░░░░▀▄░░░░░░▄░\n" +
              "█░░▀░░▀░░░░░▀▄▄░░█░█\n" +
              "█░▄░█▀░▄░░░░░░░▀▀░░█\n" +
              "█░░▀▀▀▀░░░███░░░░░░█\n" +
              "█░░░░░░░░█░░░█░░░░░█\n" +
              "█░░░░░░░░░███░░░░░░█\n" +
              "░█░░▄▄░░▄▄▄▄░░▄▄░░█░\n" +
              "░█░▄▀█░▄▀░░█░▄▀█░▄▀░\n" +
              "░░▀░░░▀░░░░░▀░░░▀░░░");
  }

  //prints out the alien2
  public static void printAlien2(MakeAlien alien){
    printOut("\n" + getName(alien));
    printOut( "░▄▀▄▀▀▀▀▄▀▄░░░░░░░░░\n" +
              "░█░░░░░░░░▀▄░░░░░░▄░\n" +
              "█░░▀░░▀░░░░░▀▄▄░░█░█\n" +
              "█░▄░█▀░▄░░░░░░░▀▀░░█\n" +
              "█░░▀▀▀▀░░░████░░░░░█\n" +
              "█░░░░░░░░░█░░█░░░░░█\n" +
              "█░░░░░░░░░████░░░░░█\n" +
              "░█░░▄▄░░▄▄▄▄░░▄▄░░█░\n" +
              "░█░▄▀█░▄▀░░█░▄▀█░▄▀░\n" +
              "░░▀░░░▀░░░░░▀░░░▀░░░");
  }

  //prints out the alien3
  public static void printAlien3(MakeAlien alien){
    printOut("\n" + getName(alien));
    printOut( "░▄▀▄▀▀▀▀▄▀▄░░░░░░░░░\n" +
              "░█░░░░░░░░▀▄░░░░░░▄░\n" +
              "█░░▀░░▀░░░░░▀▄▄░░█░█\n" +
              "█░▄░█▀░▄░░░░▄░░▀▀░░█\n" +
              "█░░▀▀▀▀░░░░█░█░░░░░█\n" +
              "█░░░░░░░░░█░░░█░░░░█\n" +
              "█░░░░░░░░░▀▀▀▀▀░░░░█\n" +
              "░█░░▄▄░░▄▄▄▄░░▄▄░░█░\n" +
              "░█░▄▀█░▄▀░░█░▄▀█░▄▀░\n" +
              "░░▀░░░▀░░░░░▀░░░▀░░░");
  }

  //procedure that prints game over
  public static void gameOver(){

      printOut("   ____      _      __  __   _____      ___   __     __  _____   ____    \n" +
              "  / ___|    / \\    |  \\/  | | ____|    / _ \\  \\ \\   / / | ____| |  _ \\   \n" +
              " | |  _    / _ \\   | |\\/| | |  _|     | | | |  \\ \\ / /  |  _|   | |_) |  \n" +
              " | |_| |  / ___ \\  | |  | | | |___    | |_| |   \\ V /   | |___  |  _ <   \n" +
              "  \\____| /_/   \\_\\ |_|  |_| |_____|    \\___/     \\_/    |_____| |_| \\_\\  \n" +
              "                                                                         ");

  }

  //procedure to print out text to the terminal letter by letter to look like a typewriter
  public static void printOut(String printMessage) {  // method that prints message like a typewriter
      String text = printMessage + "\n";
      for (int i = 0; i < text.length(); i++) {
          System.out.print(text.charAt(i));
          try {
              Thread.sleep(1);  //change millis to however long the delay should be between each letter printed
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
  }
  public static void printOutNoNextLine(String printMessage) {  // method that prints message like a typewriter
      for (int i = 0; i < printMessage.length(); i++) {
          System.out.print(printMessage.charAt(i));
          try {
              Thread.sleep(10);  //change millis to however long the delay should be between each letter printed
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
  }

  public static String getInput(String message) {  //gets user input and returns it
      printOut(message);
      Scanner scanner = new Scanner(System.in);
      return scanner.nextLine();
  }

  public static void setName (MakeAlien a, String nameGiven){  //changes aliens name
    a.name = nameGiven;
  }
  public static String getName (MakeAlien a){  // returns aliens name
    return a.name;
  }

  public static void setHappiness (MakeAlien a, int happinessGiven){
    a.happiness = happinessGiven;
  }
  public static int getHappiness (MakeAlien a){  // returns aliens name
    return a.happiness;
  }

  public static void setHunger (MakeAlien a, int hungerGiven){
    a.hunger = hungerGiven;
  }
  public static int getHunger (MakeAlien a){  // returns aliens name
    return a.hunger;
  }

  public static void setSleep (MakeAlien a, int sleepGiven){
    a.sleep = sleepGiven;
  }
  public static int getSleep (MakeAlien a){  // returns aliens name
    return a.sleep;
  }

  public static void setDaysLived (MakeAlien a, int daysLivedGiven){
    a.daysLived = daysLivedGiven;
  }
  public static int getDaysLived (MakeAlien a){  // returns aliens name
    return a.daysLived;
  }

}

//record for making an alien
class MakeAlien {
  String name;
  int happiness;
  int hunger;
  int sleep;
  int daysLived;
}
