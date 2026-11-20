package App;

import java.util.HashMap;

import App.CustomExceptions.CustomExceptions;
import App.LoginPage.Login;
// Contains various kinds of validation methods
public class Validation {
    CustomExceptions customExceptions = new CustomExceptions();
    public boolean validateUsername(String username){
        return (Login.credentials.get(username) == null);
    }
    public boolean checkNullValue(String username){
        return (username.equals(""));
    }
    public boolean validatePasswordLength(String password){
        return (password.length() < 8);
    }
    public boolean checkSpaces(String username){
        return (username.contains(" "));
    }
    public boolean validatePassword(String password){
        boolean isNumber = false, isLowerLetter = false, isUpperLetter = false;

        for(int i=0; i<password.length(); i++){
            // System.out.println(isLowerLetter+" "+isUpperLetter+" "+isNumber);
            if(!isLowerLetter && (97 <= password.charAt(i) && password.charAt(i) <= 122)){
                isLowerLetter = true;
            }
            else if(!isUpperLetter && (65 <= password.charAt(i) && password.charAt(i) <= 90)){
                isUpperLetter = true;
            }
            else if(!isNumber && ((0 <= password.charAt(i) - '0') && (password.charAt(i) - '0' <= 9))){
                isNumber = true;
            }
            if(isLowerLetter && isUpperLetter && isNumber){
                return true;
            }
        }
        return false;
    }
    public boolean validateOrderFormat(String order){
        // {{,},{,},{,}}
        if((order.charAt(0) != '{') || (order.charAt(order.length()-1) != '}')){
            return false;
        }
        // order = order.substring(1,order.length()-1);
        
        HashMap<Character,Integer> charFrequency = new HashMap<>();
        charFrequency.put('n', 0);
        charFrequency.put('{', 0);
        charFrequency.put('}', 0);
        charFrequency.put(',', 0);
        boolean isNumber = false;
        for(int i=1; i<order.length()-1; i++){
            char c = order.charAt(i);
            if(c == '{' || c == '}' || c == ','){
                if(isNumber){
                    charFrequency.put('n', charFrequency.get('n')+1);
                }
                isNumber = false;
                charFrequency.put(c, charFrequency.get(c)+1);
            }
            else if(0 <= c - '0' && c -'0' <= 9){
                isNumber = true;
            }
            else{
                return false;
            }
        }
        int checkFrequency = charFrequency.get('{');
        if(charFrequency.get('}') != checkFrequency){
            return false;
        }
        else if(charFrequency.get('n') != 2*checkFrequency){
            return false;
        }
        else if(charFrequency.get(',') != (2*checkFrequency-1)){
            return false;
        }
        return true;
    }

}
