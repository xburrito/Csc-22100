// Albert Chang 
// Csc 22100 - F

// Libraries Imported
import java.security.SecureRandom;
import java.util.Arrays;

class Main {
    public static int R1;
    public static int R2;
    public static char race[] = new char[100];
    public static final SecureRandom rand = new SecureRandom();

    // Overloading the toString() Method
    public String toString(){
      return "Race simulation class";
    }

    // Track Method
    public static void track(){

      //Fills array with 100 characters
      Arrays.fill(race, ' '); 
      String equal = "B "; 

      // This will check if R1 and R2 are equal to each other.
      if(R1 != R2){
        
        // If not, they will be set to zero at initialization.
        if(R1 <= 0){
          R1 = 0;
        }
        if(R2 <= 0){
          R2 = 0;
        }
        
        // Racers as indicated R1 = 1 & R2 = 2
            race[R1] = '1';
            race[R2] = '2';
      }

      // If R1 and R2, inserts B for beginning of race
      else if(R1 == R2){
          for(int i = R1; i < equal.length(); i++){
             race[i] = equal.charAt(i - R1);
          }
      }
      
        // Prints Racer's Position throughout the race
        for(char x: race){
            System.out.print(x);
        }
    }

    // R1 MOVEMENT METHOD
    public static void R1_movement(){

      // Makes sure the rand int generated is between 1-10
      int chance = 1 + rand.nextInt(10);
            
      switch(chance){
        // Case 1-5(JUMP): R1 will move 3 sqaures forward (50%)
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
          R1 += 3;
          break;

        // Case 6-8(SLIP): R1 will move 6 squares backward (30%)
        case 6:
        case 7:
        case 8:
          R1 -= 6;
          break;

        // Case 9-10(WALK): R1 will move 1 square forward (20%)
        case 9:
        case 10:
          R1 += 1;
        
        // Default Case not necessary here (DO NOTHING)
        default:
          break;
      }
    } // END OF R1 MOVEMENTS

    // R2 MOVEMENT METHOD
    public static void R2_movement(){

      // Makes sure the rand int generated is between 1-10
      int chance = 1 + rand.nextInt(10);
      
      switch(chance){
        // Case 1(SLEEP): R2 NO MOVEMENT (10%)
        case 1:
          break;

        // Case 2-3(JUMP): R2 will move 5 squares forward (20%)
        case 2:
        case 3:
          R2 += 5;
          break;

        // Case 4-5(SMALL SLIP): R2 will move 2 squares backward (20%)
        case 4:
        case 5:
          R2 -= 2;
          break;

        // Case 6(SLIP): R2 will move 10 squares backward (10%)
        case 6:
          R2 -= 10;
          break;

        // Case 7-10(WALK): R2 will move 1 square forward (40%)
        case 7:
        case 8:
        case 9:
        case 10:
          R2 ++;
          break;

        // Default Case not necessary here (DO NOTHING)
        default:
          break;
      }
    } // END OF R2 MOVEMENTS

  public static void main(String[] args) {
    
    // Initiates racers to start
    R1 = 0;
    R2 = 0;
    System.out.println("On Your Mark, Get Set, Go");
    
    //initiates timer to 0
    int timer = 0;
    
    // Produces iteration for the track race
    // Both racers must be less than 100 squares (not at the finish line)
    // in order for race to continue. 
    while(R1 < 100 && R2 < 100){
        System.out.println();
        System.out.printf("Time: %d%n", timer);
            
        track();
        R1_movement();
        R2_movement();

        // Creates track separator lines array[100]
        int[] x = new int[100];
        System.out.println();

        // Race Track Separator
        for (int i = 1; i < x.length; ++i){ 
            char line = '-';
            System.out.printf("%s", line);
        }

        // Timer incrementation for every loop
        timer++;

    } // End of While-loop
   System.out.println();

  // Cases at the end of the race
    if(R1 == R2){
      System.out.printf("%n%s%n","It's a tie!");
    }
    else if(R1 >= 100){
      System.out.printf("%n%s%n","Runner 1 Wins.");
    }
    else if(R2 >= 100){
      System.out.printf("%n%s%n","Runner 2 Wins.");
    }
      System.out.printf("%s%d%s%n%n", "Time Elapsed = ", timer-1," seconds");

    // Test calling the overloaded toString() method created earlier
    Main calling = new Main();
    System.out.printf("%s%s", "Calling the overloaded 'toString()' method: ", calling.toString());
  } 
}