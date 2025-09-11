import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
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
        try (FileWriter fw = new FileWriter("wyniki4.txt", true)) {
            fw.write(answer+"\n");
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
        WriteAnswer(String.format("1. %d %d", counter, firstNumber));
    }

    private ArrayList<Integer> rozkladNaCzynnikiPierwsze(int number){
        ArrayList<Integer> rozkladNaCzynnikiPierwsze = new ArrayList<>();
        if(number < 1){
            return rozkladNaCzynnikiPierwsze;
        }
        while(number % 2 == 0){
            rozkladNaCzynnikiPierwsze.add(2);
            number /=2;
        }

        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            while (number % i == 0) {
                rozkladNaCzynnikiPierwsze.add(i);
                number /= i;
            }
        }

        if(number > 2){
            rozkladNaCzynnikiPierwsze.add(number);
        }
        return rozkladNaCzynnikiPierwsze;
    }

    public void Zadanie2(){
        int najwiecejCzynnikow = 0;
        Integer najwiecejCzynnikowLiczba = null;
        int najwiecejRoznychCzynnikow = 0;
        Integer najwiecejRoznychCzynnikowLiczba = null;
        for(Integer liczba : liczby){
            ArrayList<Integer> rozklad = rozkladNaCzynnikiPierwsze(liczba);
            if(rozklad.size() > najwiecejCzynnikow){
                najwiecejCzynnikow = rozklad.size();
                najwiecejCzynnikowLiczba = liczba;
                HashSet<Integer> rozneCzynniki = new HashSet<>(rozklad);
                if(rozneCzynniki.size() > najwiecejRoznychCzynnikow){
                    najwiecejRoznychCzynnikow = rozneCzynniki.size();
                    najwiecejRoznychCzynnikowLiczba = liczba;
                }
            }
        }
        WriteAnswer(String.format("2. %d %d %d %d", najwiecejCzynnikowLiczba, najwiecejCzynnikow, najwiecejRoznychCzynnikowLiczba, najwiecejRoznychCzynnikow));
    }
}
