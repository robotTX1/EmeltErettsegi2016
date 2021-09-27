package com.practise;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class otszaz {

    public static void main(String[] args) {
        List<Kosar> list = new ArrayList<>();

        // 1. feladat
        Path filePath = FileSystems.getDefault().getPath("penztar.txt");

        try(Scanner input = new Scanner(filePath)) {
            String line;
            Kosar kosar = new Kosar();
            while(input.hasNextLine()) {
                line = input.nextLine().strip();
                if(!line.equals("F")) {
                    kosar.addItem(line);
                } else {
                    list.add(kosar);
                    //kosar.print();
                    kosar = new Kosar();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 2. feladat
        System.out.printf("2.feladat\nA fizetések száma: %d\n", list.size());

        //3. feladat
        System.out.printf("\n3. feladat\nAz első vásárló %d darab árucikket vásárolt.\n", list.get(0).getBasketSize());

        //4. feladat
        Scanner input = new Scanner(System.in);
        System.out.println("\n4.feladat");
        System.out.println("Adja meg egy vásárlás sorszámát!");
        int sorszam = input.nextInt();
        input.nextLine();

        System.out.println("Adja meg egy árucikk nevét!");
        String arucikk = input.nextLine();

        System.out.println("Adja meg a vásárolt darabszámot!");
        int vasarolDarab = input.nextInt();
        input.nextLine();

        // 5. feladat
        // a)

        int first = 0;
        for(int i=0; i<list.size(); i++) {
            if(list.get(i).isInBasket(arucikk)) {
                first = i;
                break;
            }
        }

        int last = 0;

        for(int i=list.size()-1; i >= 0; i--) {
            if(list.get(i).isInBasket(arucikk)) {
                last = i;
                break;
            }
        }

        int count = 0;

        for(Kosar k : list) {
            if(k.isInBasket(arucikk)) {
                count++;
            }
        }

        System.out.printf("\n5. feladat\nAz első vásárlás sorszáma: %d\nAz utolsó vásárlás sorszáma: %d\n%d vásárlás során vettek belőle.\n", first+1, last+1, count);

        System.out.printf("\n6. feladat\n%d darab vételekor fizetendő: %d\n", vasarolDarab, ertek(vasarolDarab));

        System.out.println("\n7. feladat");

        Kosar currentKosar = list.get(sorszam-1);

        for(Map.Entry<String, Integer> entry : currentKosar.getItemsWithCount().entrySet()) {
            System.out.printf("%d %s\n", entry.getValue(), entry.getKey());
        }

        // 8. feladat

        Path outputFile = FileSystems.getDefault().getPath("osszeg.txt");

        try(BufferedWriter writer = Files.newBufferedWriter(outputFile)) {
            int sum;
            for(int i=0; i<list.size(); i++) {
                sum = 0;
                for(Map.Entry<String, Integer> entry : list.get(i).getItemsWithCount().entrySet()) {
                    sum += ertek(entry.getValue());
                }
                writer.write(String.format("%d: %d\r\n", i+1, sum));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static int ertek(int count) {
        int sum = 0;

        if(count >= 1) sum += 500; // 1. termék
        if(count >= 2) sum += 450; // 2. termék
        if(count > 2) sum += 400 * (count-2); // 3,4,5,6,7,8,9. termék

        return sum;
    }

}
