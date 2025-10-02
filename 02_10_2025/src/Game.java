import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Game{
    public void loop(){
        Scanner input = new Scanner(System.in);

        int diceCount;
        do{
            System.out.println("Ile kostek chcesz rzucić?(3 - 10)");
            diceCount = input.nextInt();
        }while(diceCount < 3 || diceCount > 10);

        boolean stop = false;
        while(!stop) {
            rollDice();
            System.out.println("Jeszcze raz? (t/n)");
            if(input.nextLine().equals("n")) {
                stop = true;
            }
        }
    }

    public void rollDice(){
        AtomicInteger result = new AtomicInteger();
        ArrayList<Integer> rolledDices = new ArrayList<>();
        for(int i=0; i<5; i++){
            int randomValue = (int)(Math.random()*6+1);
            rolledDices.add(randomValue);
            System.out.printf("Kostka %d: %d%n", i+1, randomValue);
        }
        rolledDices.stream()
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
                .forEach((val, freq) -> {
                    if(freq > 1){
                        result.addAndGet((int) (val * freq));
                    }
                });
        System.out.printf("Liczba uzyskanych punktów: %d\n", result.intValue());
    }

}
