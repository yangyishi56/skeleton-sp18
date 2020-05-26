public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> chars = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            chars.addLast(word.charAt(i));
        }
        return chars;
    }

    public boolean isPalindrome(String word) {
        if (word == null || word.length() == 0) return true;
        int left = 0, right = word.length() - 1;

        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) return false;
            else {
                left++;
                right--;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        } else {
            for (int i = 0; i < word.length() / 2; i++) {
                if (!cc.equalChars(word.charAt(i), word.charAt(word.length() - i - 1))) {
                    return false;
                }
            }
            return true;
        }
    }
}
