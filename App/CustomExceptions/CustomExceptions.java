package App.CustomExceptions;
// Class Comprising of custom exceptions
public class CustomExceptions {

    // Collection of Custom Exceptions

    public class InvalidLengthException extends Exception {
        public InvalidLengthException(String message) {
            super(message);
        }
    }
    
    public class InvalidCredentialsException extends Exception {
        public InvalidCredentialsException(String message) {
            super(message);
        }
    }
    
    public class InvalidUsernameException extends Exception {
        public InvalidUsernameException(String message) {
            super(message);
        }
    }
    
    public class InvalidPasswordException extends Exception {
        public InvalidPasswordException(String message) {
            super(message);
        }
    }

    public class InvalidOrderFormatException extends Exception {
        public InvalidOrderFormatException(String message) {
            super(message);
        }
    }
    public class InvalidFoodItemException extends Exception {
        public InvalidFoodItemException(String message) {
            super(message);
        }
    }
    public class NullUsernameException extends Exception {
        public NullUsernameException(String message) {
            super(message);
        }
    }
    public class UnwantedSpacesException extends Exception {
        public UnwantedSpacesException(String message) {
            super(message);
        }
    }
}