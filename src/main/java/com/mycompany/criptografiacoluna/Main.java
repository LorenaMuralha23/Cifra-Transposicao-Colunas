package com.mycompany.criptografiacoluna;

import java.io.File;
import java.util.Arrays;
import javax.swing.JFileChooser;

public class Main {

    public static void main(String[] args) {
        Cryptography cryptography = new Cryptography();

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setDialogTitle("Selecione um arquivo para encriptografar");

        // Define que pode selecionar apenas arquivos (pode ser modificado para pastas)
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Abre o FileChooser e captura a resposta do usuário (se clicou em "Abrir" ou "Cancelar")
        int result = fileChooser.showOpenDialog(null);

        // Se o usuário clicou em "Abrir", pegamos o arquivo selecionado
        if (result == JFileChooser.APPROVE_OPTION) {
            File fileToEncrypt = fileChooser.getSelectedFile();
            String key = "limao";
            cryptography.encrypt(fileToEncrypt, key);
        } else {
            System.out.println("Nenhum arquivo foi selecionado.");
        }
        
        
    }

    public void test() {
        StringBuilder builder = new StringBuilder();

        String key = "LIMAO";
        int[] lettersCode = {76, 73, 77, 65, 79};
        int[] codesSorted = Arrays.copyOf(lettersCode, lettersCode.length);
        Arrays.sort(codesSorted);
        String completeMessage = "defend the east wall";
        String completeMessageNoSpace = completeMessage.replaceAll("\\s", "");

        char[] charsToEncrypt = new char[key.length()];

        int cont = 0;
        boolean running = true;
        char[] encryptedMessageArray = new char[key.length()];
        while (running) {
            for (int i = 0; i < key.length(); i++) {
                if (cont < completeMessageNoSpace.length()) {
                    charsToEncrypt[i] = completeMessageNoSpace.charAt(cont);
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
            builder.append(encryptedChars);

        }
    }

}
