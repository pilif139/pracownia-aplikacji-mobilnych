import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Odpowiedzi {
    private ArrayList<Integer> liczby = new ArrayList<>();

    public Odpowiedzi() {
        File file = new File("src/Dane_2205/liczby.txt");
        try(Scanner sc = new Scanner(file)){
            while(sc.hasNextInt()){
                liczby.add(sc.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void WriteAnswer(String answer){
        try (FileWriter fw = new FileWriter("wyniki4.txt")) {
            fw.write(answer);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void Zadanie1(){
        int counter = 0;
        Integer firstNumber = null;
        for(Integer liczba : liczby){
            String stringliczba = liczba.toString();
            boolean hasTheSameFirstAndLastDigit = stringliczba.charAt(0) == stringliczba.charAt(stringliczba.length()-1);
            if(hasTheSameFirstAndLastDigit){
                counter++;
            }
            if(hasTheSameFirstAndLastDigit && firstNumber == null){
                firstNumber = liczba;
            }
        }
        WriteAnswer(String.format("%d %d", counter, firstNumber));
    }
}
