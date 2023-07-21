import java.util.Stack;

public class ParenthesesChecker {

    public boolean checkParentheses(String test){

        //Test if empty
        if (test.isEmpty()){
            return true;
        }

        //Stacks brackets
        Stack<Character> brackets = new Stack<>();

        for (int i = 0; i < test.length(); i++) {
            switch (test.charAt(i)) {
                case '(', ')', '[', ']', '{', '}', '<', '>'
                        -> brackets.push(test.charAt(i));
            }
        }

        //Tests for odd amount of brackets
        if (brackets.size() %2 !=0){
            return false;
        }

        //Tests if same number of each bracket
        int countOpenNorm = 0;
        int countCloseNorm = 0;
        int countOpenCorn = 0; 
        int countCloseCorn = 0;
        int countOpenCurl = 0;
        int countCloseCurl = 0;
        int countOpenSqr = 0;
        int countCloseSqr = 0;


        while (!brackets.isEmpty()){
            switch (brackets.pop()) {
                case '(' -> {
                    if (countCloseNorm == 0) { //these test if there are wrong way brackets
                        return false;        // e.g. ")(" or "(><)"
                    }
                    countOpenNorm += 1;
                }
                case ')' -> {
                    if (brackets.peek() == '<' || brackets.peek() == '{' || brackets.peek() == '['){
                        return false;
                    }
                    countCloseNorm += 1;
                }
                case '[' -> {
                    if (countCloseSqr == 0) {
                        return false;
                    }
                    countOpenSqr += 1;
                }
                case ']' -> {
                    if (brackets.peek() == '<' || brackets.peek() == '(' || brackets.peek() == '{'){
                        return false;
                    }
                    countCloseSqr += 1;
                }
                case '{' -> {
                    if (countCloseCurl == 0) {
                        return false;
                    }
                    countOpenCurl += 1;
                }
                case '}' -> {
                    if (brackets.peek() == '<' || brackets.peek() == '(' || brackets.peek() == '['){
                        return false;
                    }
                    countCloseCurl += 1;
                }
                case '<' -> {
                    if (countCloseCorn == 0) {
                        return false;
                    }
                    countOpenCorn += 1;
                }
                case '>' -> {
                    if (brackets.peek() == '(' || brackets.peek() == '{' || brackets.peek() == '['){
                        return false;
                    }
                    countCloseCorn += 1;
                }
            }
        }

        if (countOpenNorm != countCloseNorm
                || countOpenCorn != countCloseCorn
                || countOpenCurl != countCloseCurl
                || countOpenSqr != countCloseSqr){
            return false;
        }

        //Otherwise true
        return true;
    }
}
