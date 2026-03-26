import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Logic {
    public static StringBuilder Vijner(String encrypt, String key) {
        String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя ";
        StringBuilder keyAll = new StringBuilder();

        StringBuilder megaRes = new StringBuilder();
        for(int i = 0; i < encrypt.length(); i++){
            char keyChar = key.charAt(i % key.length());
            keyAll.append(keyChar);
        }

        for(int i = 0; i < encrypt.length(); i++){
            int index_encr = -1;
            int index_key = -1;
            for(int j = 0; j < alphabet.length(); j++){
                if(encrypt.charAt(i) == alphabet.charAt(j)){
                    index_encr = j;
                }
                if(keyAll.charAt(i) == alphabet.charAt(j)){
                    index_key = j;
                }
            }
            if(index_encr != -1 && index_key != -1){
                int resIndex = index_encr + index_key;


                if(resIndex >= alphabet.length()){
                    resIndex = resIndex - alphabet.length();

                }
                char char_word = alphabet.charAt(resIndex);
                megaRes.append(char_word);
            }
        }

        return megaRes;
    }
    public static StringBuilder dencrypt(String encrypt, String key) {
        String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя ";
        StringBuilder keyAll = new StringBuilder();

        StringBuilder megaRes = new StringBuilder();
        for(int i = 0; i < encrypt.length(); i++){
            char keyChar = key.charAt(i % key.length());
            keyAll.append(keyChar);
        }

        for(int i = 0; i < encrypt.length(); i++){
            int index_encr = -1;
            int index_key = -1;
            for(int j = 0; j < alphabet.length(); j++){
                if(encrypt.charAt(i) == alphabet.charAt(j)){
                    index_encr = j;
                }
                if(keyAll.charAt(i) == alphabet.charAt(j)){
                    index_key = j;
                }
            }
            if(index_encr != -1 && index_key != -1){
                int resIndex = index_encr - index_key;


                if(resIndex < 0){
                    resIndex = resIndex + alphabet.length();

                }
                char char_word = alphabet.charAt(resIndex);
                megaRes.append(char_word);
            }
        }

        return megaRes;
    }


    public static String deshifrator(String encrypt, Integer lengthKey) {
    StringBuilder hz = new StringBuilder();
    String alphabet = "абвгдежзийклмнопрстуфхцчшщъыьэюя ";

    ArrayList<Character> result = new ArrayList<>();


    for (int col = 0; col < lengthKey; col++) {
        hz.setLength(0);
        for (int row = 0; row < Math.ceil((double) encrypt.length() / lengthKey); row++) {
            int index = col + row * lengthKey;
            if (index < encrypt.length()) {
                hz.append(encrypt.charAt(index));
            }
        }
        
        
        // Считаем частоту букв в этом столбце
        int[] freq = new int[alphabet.length()];
        
        for (int i = 0; i < hz.length(); i++) {
            char c = hz.charAt(i);
            int index = alphabet.indexOf(c);
            if (index >= 0) {
                freq[index]++;
            }
        }

        // Находим максимальную частоту для масштабирования гистограммы
        int maxFreq = 0;
        int ind = 0;
        for (int i = 0; i < alphabet.length(); i++) {
            if (freq[i] > maxFreq) {
                maxFreq = freq[i];
                ind = i;
            }
        }
        
        result.add(alphabet.charAt((ind - 32 + 33) % 33));
    }

    StringBuilder sb = new StringBuilder();
    for (char c : result) {
        sb.append(c);
    }
    System.out.println(sb.toString());
    return sb.toString();
}


    public static int sdvig(String encrypt) {
        ArrayList<Double> arr = new ArrayList<Double>();
        
        for (int z = 1; z < 20; z++) {
            double totalIC = 0;
            int validColumns = 0;  // считаем только непустые столбцы
            
            for (int i = 0; i < z; i++) {
                StringBuilder column = new StringBuilder();
                for (int j = i; j < encrypt.length(); j += z) {
                    column.append(encrypt.charAt(j));
                }
                
                // IC требует минимум 2 символа для расчета
                if (column.length() >= 2) {
                    totalIC += chastota(column.toString());
                    validColumns++;
                }
            }
            
            // Усредняем только по реальным столбцам
            double res = validColumns > 0 ? totalIC / validColumns : 0;
            arr.add(res);
            
        }
        double maxIC = 0;
        int keyLength = 1;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) > maxIC) {
                maxIC = arr.get(i);
                keyLength = i + 1;
            }
        }
        return keyLength;
    }

    public static double chastota(String text) {
        if (text.length() < 2) return 0;  // защита от коротких строк
        
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : text.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        
        double sum = 0;
        for (int count : freq.values()) {
            sum += count * (count - 1);
        }
        
        return sum / (text.length() * (text.length() - 1));
    }
}
