package algorithms.programmers.highscorekit.hash;

import java.util.Arrays;

public class PhoneBook {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book);

        for(int i = 1; i < phone_book.length; i++) {
            if (phone_book[i].startsWith(phone_book[i-1])) {
                answer = false;
                break;
            }
        }

        return answer;
    }
}
