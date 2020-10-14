import java.util.*;
import edu.duke.*;
import java.io.*;
public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder mess = new StringBuilder();
        for(int i = whichSlice ; i < message.length() ; i = i + totalSlices){
            char currChar = message.charAt(i);
            if(Character.isLetter(currChar)){
                mess.append(currChar);
                
            }
        }
        return mess.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int i = 0 ; i < klength ; i++){
            String slice_message = sliceString(encrypted , i , klength);
            int k = cc.getKey(slice_message);
            key[i] = k;
            
        }
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        /*
        FileResource fr = new FileResource();
        String message = fr.asString();
        
        int[] keys = tryKeyLength(message , 4 , 'e');
        VigenereCipher VC = new VigenereCipher(keys);
        
        String decrypt = VC.decrypt(message);
        System.out.println(decrypt); */
        /*
        FileResource fr = new FileResource();
        String decrypt = fr.asString();
        
        FileResource fy = new FileResource();
        HashSet<String> dict = readDictionary(fy);
        
        String decrypted = breakForLanguage(decrypt , dict);
        System.out.println(decrypted); */
        
        HashMap <String , HashSet<String>> languages = new HashMap<String ,HashSet<String>>();
        FileResource fr = new FileResource();
        String decrypt = fr.asString();
        
        breakForAllLangs(decrypt , languages);
        
        
    }
    
    public HashSet<String> readDictionary (FileResource fr){
        HashSet<String> dict = new HashSet<String>();
        for(String word : fr.lines()){
            dict.add(word.toLowerCase());
            
        }
        return dict;
    }
    
    public int countWords(String message , HashSet<String> dict){
        int count = 0;
        for(String word : message.split("\\W+")){
                String w = word.toLowerCase();
                if(dict.contains(w)){
                    count = count + 1;
                }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted  , HashSet<String> dict){
        int count = 0;
        String decrypted = "";
        char commomChar = mostCommonCharIn(dict);
        //int keylength = 0;
        for(int i = 1 ; i <= 100 ; i++){
            int[] keys = tryKeyLength(encrypted , i , 'e');
            VigenereCipher VC = new VigenereCipher(keys);
        
            String decrypt = VC.decrypt(encrypted);
            int count_V = countWords(decrypt , dict);
            if(count_V > count){
                count = count_V;
                decrypted = decrypt;
                //keylength = keys.length;
                
            }
        }
        //System.out.println("KeyLength : " + keylength);
        //System.out.println("Valid Words :" + count);
        return decrypted;
    }
    
    public char mostCommonCharIn(HashSet<String> dict){
        HashMap<Character , Integer> charNo = new HashMap<Character , Integer>();
        for(String i : dict){
            StringBuilder i_char = new StringBuilder(i);
            for(int k = 0 ; k < i.length() ; k++){
                char currChar = i_char.charAt(k);
                if(!charNo.containsKey(currChar)){
                    charNo.put(currChar,1);
                }
                else{
                    charNo.put(currChar,charNo.get(currChar) + 1);
                }
            }
            
        }
        int max = 0;
        char c = ' ';
        for(Map.Entry<Character , Integer > entry : charNo.entrySet()){
            if(entry.getValue() > max){
                max = entry.getValue();
                c = entry.getKey();
            }
        }
        
        return c;
    }
    
    
    public void breakForAllLangs(String encrypted , HashMap<String , HashSet<String>> languages){
        DirectoryResource dr = new DirectoryResource();
        for(File F : dr.selectedFiles()){
            FileResource fr = new FileResource(F);
            String FileName = F.getName();
            HashSet<String> dict = readDictionary(fr);
            languages.put(FileName , dict);
        }
        int count_V = 0;
        String Decrypted = "";
        String File = "";
        for(Map.Entry<String , HashSet<String>> entry : languages.entrySet()){
            String decrypt = breakForLanguage(encrypted , entry.getValue());
            int count = countWords(decrypt , entry.getValue());
            if(count > count_V){
                count_V = count;
                Decrypted = decrypt;
                File = entry.getKey();
            }
            
        }
        System.out.println(Decrypted);
        System.out.println("Decrypted With Language  : " + File);
    }
}
