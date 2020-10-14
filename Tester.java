import java.util.*;
import edu.duke.*;
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tester {
    public void testsliceString(){
        VigenereBreaker ss = new VigenereBreaker();
        String slice = ss.sliceString("abcdefghijklm", 0, 3);
        System.out.println(slice);
    }
    
    public void testtryKeyLength(){
        VigenereBreaker ss = new VigenereBreaker();
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int[] keys = ss.tryKeyLength(encrypted , 4 , 'e');
        for(int i = 0 ; i < keys.length ; i++){
            System.out.println(keys[i]);
        }
    }
    
    public void testreadDictionary(){
        VigenereBreaker ss = new VigenereBreaker();
        FileResource fr = new FileResource();
        HashSet<String> dict = ss.readDictionary(fr);
        
        System.out.println(dict);
    }
    
    public void testcountWords(){
        VigenereBreaker ss = new VigenereBreaker();
        FileResource fr = new FileResource();
        HashSet<String> dict = ss.readDictionary(fr);
        
        int valid_words = ss.countWords("bus cry" , dict);
        System.out.println(valid_words);
        
    }
    
    public void testbreakForLanguage(){
        VigenereBreaker ss = new VigenereBreaker();
        FileResource fr = new FileResource();
        String decrypt = fr.asString();
        
        FileResource fy = new FileResource();
        HashSet<String> dict = ss.readDictionary(fy);
        
        String decrypted = ss.breakForLanguage(decrypt , dict);
        //System.out.println(decrypted);
    }
    
    public void testmostCommonCharIn(){
        VigenereBreaker ss = new VigenereBreaker();
        FileResource fy = new FileResource();
        HashSet<String> dict = ss.readDictionary(fy);
        
        char commonchar = ss.mostCommonCharIn(dict);
        System.out.println(commonchar);
        
    }
    
}
