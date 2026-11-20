package App.LoginPage;

import java.util.HashMap;
// Checks whether the credentials are valid
public class Login {
        public static HashMap<String, String> credentials = new HashMap<>();

        public boolean correctCredentials(String username, String password){
            if(credentials.get(username) == null){
                return false;
            }
            return (credentials.get(username).equals(password));
        }
}
