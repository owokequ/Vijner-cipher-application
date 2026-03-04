public class Logic {
    public StringBuilder Vijner(String encrypt, String key) {
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

    public StringBuilder VijnerDecryption(String encrypt, String key) {
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
}
