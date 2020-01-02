package zadania.anagram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Anagram {
    public static void main(String[] args) throws IOException {
        Anagram anagram = new Anagram();
        anagram.initializating();
    }

    private void initializating() throws IOException {
        List<String> listOfAnagrams;
        if (addFile().size() == 0){
            System.out.println("File is empty. Try again...");
            initializating();
        }
        else{
            listOfAnagrams = findAnagrams(addFile());
            System.out.println(String.format("Found %,d anagrams", listOfAnagrams.size()));
            for (String word : listOfAnagrams){
                System.out.println(word);
            }
        }
    }

    private List<String> addFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("anagram.txt"));
        Set<String> tmp = new HashSet<>();
        String line;
        try{
            while ((line = bufferedReader.readLine()) != null){
                if (!line.equals("")){
                    tmp.add(line);
                }
            }
            bufferedReader.close();
        }
        catch (IOException e){
            System.out.println("Error! Try again...");
        }
        return new ArrayList<>(tmp);
    }

    private List<String> findAnagrams(List<String> words){
        List<String> anagrams = new ArrayList<>();
        for (int i = 0; i < words.size(); i++){
            for (int j = i+1; j < words.size(); j++){
                if (checkWords(words.get(i), words.get(j))){
                    anagrams.add(words.get(i) + " - " + words.get(j));
                }
            }
        }
        return anagrams;
    }

    private boolean checkWords(String word_1, String word_2){
        char[] result_1 = word_1.toLowerCase().toCharArray();
        char[] result_2 = word_2.toLowerCase().toCharArray();
        Arrays.sort(result_1);
        Arrays.sort(result_2);
        String sorted_1 = new String(result_1);
        String sorted_2 = new String(result_2);
        return sorted_1.equals(sorted_2);
    }
}