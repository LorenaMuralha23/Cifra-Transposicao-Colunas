package com.mycompany.criptografiacoluna;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cryptography {

    public void encrypt(File fileToEncrypt, String key) {
        try {
            if (verifyKey(key)) {
                BufferedReader bufferReader = new BufferedReader(new FileReader(fileToEncrypt));
                StringBuilder srBuilder = new StringBuilder();
                String lineReadText;
                while ((lineReadText = bufferReader.readLine()) != null) {
                    srBuilder.append(lineReadText);
                }
                String message = srBuilder.toString();
                String completeMessage = message.replaceAll("\\s", "");
                srBuilder.setLength(0);

                int[] lettersCode = sortLetter(key.toCharArray());
                int[] codesSorted = Arrays.copyOf(lettersCode, lettersCode.length);
                Arrays.sort(codesSorted);

                char[] charsToEncrypt = new char[key.length()];

                int cont = 0;
                boolean running = true;
                char[] encryptedMessageArray = new char[key.length()];
                while (running) {
                    for (int i = 0; i < key.length(); i++) {
                        if (cont < completeMessage.length()) {
                            charsToEncrypt[i] = completeMessage.charAt(cont);
                            cont++;
                        } else {
                            if (i < key.length()) {
                                charsToEncrypt[i] = 'x';
                            }
                            running = false;
                        }

                    }

                    //tenhos os chars para mexer
                    for (int i = 0; i < key.length(); i++) {
                        for (int j = 0; j < key.length(); j++) {
                            if (lettersCode[i] == codesSorted[j]) {
                                encryptedMessageArray[j] = charsToEncrypt[i];
                            }
                        }
                    }
                    String encryptedChars = new String(encryptedMessageArray);
                    srBuilder.append(encryptedChars);

                }
                
                System.out.println("Encrypted message: " + srBuilder.toString());

            } else {

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Cryptography.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cryptography.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean verifyKey(String key) {
        boolean isRepeated = true;
        char[] letters = key.toCharArray();
        Set<Character> charList = new HashSet<>();
        for (int i = 0; i < letters.length; i++) {
            if (!(isRepeated = charList.add(letters[i]))) {
                //false = já foi add
                break;
            }
        }
        return isRepeated;
    }

    public int[] sortLetter(char[] keyLetters) {
        int[] lettersCode = new int[keyLetters.length];

        for (int i = 0; i < keyLetters.length; i++) {
            lettersCode[i] = keyLetters[i];
        }

        return lettersCode;
    }

}
