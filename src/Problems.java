import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

//Questions picked at random on LeetCode.com
//By: Ryan Dabir
public class Problems {

    public static int[] twoSum(int[] nums, int target) {

        int[] answer = new int[2];

        for(int i = 0; i < nums.length; i++) {
            for(int j = i+1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target) {
                    answer[0] = i;
                    answer[1] = j;
                    return answer;
                }
            }
        }

        return answer;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode counterNode = head;
        ListNode current = head;
        ListNode afterNode;
        int counter = 0;

        if (head == null || head.next == null) {
            return null;
        }

        while (counterNode != null) {
            counter++;
            counterNode = counterNode.next;
        }

        int target = (counter - n) - 1;

        if (target < 0) {
            return head.next;
        }

        for (int i = 0; i < target; i++) {
            current = current.next;
        }

        afterNode = current.next.next;
        current.next = afterNode;

        return head;
    }

    public static ListNode reverseList(ListNode head) {

        ListNode prev;
        ListNode current;

        if (head == null || head.next == null) {
            return head;
        }

        prev = head;
        current = head;
        head = head.next;
        prev.next = null;

        while(head.next != null) {
            current = head;
            head = head.next;
            current.next = prev;
            prev = current;
        }
        ListNode last = new ListNode(head.val);
        last.next = current;
        return last;
    }

    public static boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        if (s.length()%2 != 0) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {

            char bracket = s.charAt(i);

            if (bracket == '(' || bracket == '{' || bracket == '[' ) {
                stack.push(bracket);
            }
            else if (bracket == ')') {
                if (stack.empty() || stack.pop() != '(') {
                    return false;
                }
            }
            else if (bracket == ']') {
                if (stack.empty() || stack.pop() != '[') {
                    return false;
                }
            }
            else if (bracket == '}') {
                if (stack.empty() || stack.pop() != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static boolean isPalindrome(int x) {

        if (x < 0) {
            return false;
        }

        Stack<Character> stack = new Stack<>();

        char[] chars = ("" + x).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            stack.push(chars[i]);
        }

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != stack.pop()) {
                return false;
            }
        }

        return true;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        Stack<Integer> num1 = new Stack<>();
        Stack<Integer> num2 = new Stack<>();
        int count1 = 0;
        int count2 = 0;

        while (l1 != null) {
            num1.push(l1.val);
            l1 = l1.next;
            count1++;
        }

        while (l2 != null) {
            num2.push(l2.val);
            l2 = l2.next;
            count2++;
        }

        Integer[] array1 = new Integer[count1];
        Integer[] array2 = new Integer[count2];

        for(int i = 0; i < count1; i++) {
            array1[i] = num1.pop();
        }

        for(int i = 0; i < count2; i++) {
            array2[i] = num2.pop();
        }

        StringBuilder str1 = new StringBuilder();
        for (int num : array1) {
            str1.append(num);
        }
        int final1 = Integer.parseInt(str1.toString());

        StringBuilder str2 = new StringBuilder();
        for (int num : array2) {
            str2.append(num);
        }
        int final2 = Integer.parseInt(str2.toString());

        int answer = final1 + final2;

        String answerString = Integer.toString(answer);
        int[] answerArray = new int[answerString.length()];
        for (int i = 0; i < answerString.length(); i++) {
            answerArray[i] = answerString.charAt(i) - '0';
        }

        return null;
    }

    public static int lengthOfLongestSubstring(String s) {

        if (s.length() == 1) {
            return 1;
        }

        HashSet<Character> set = new HashSet<>();
        int length = 0;
        int answer = 0;

        for(int i = 0; i < s.length(); i++) {

            if (length > answer) {
                answer = length;
            }

            length = 1;
            set.clear();
            set.add(s.charAt(i));
            for(int j = i + 1; j < s.length(); j++) {
                if (set.contains(s.charAt(j))) {
                    break;
                }
                else {
                    set.add(s.charAt(j));
                    length++;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter question you want to run: ");
        int num = in.nextInt();

        if (num == 1) {
            //https://leetcode.com/problems/two-sum/description/
            int[] nums = {2, 9, 11, 7};
            System.out.println(Arrays.toString(twoSum(nums, 9)));
        }

        if (num == 2) {
            //https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
            ListNode a = new ListNode(1);

            ListNode b = new ListNode(2);
            a.next = b;

            ListNode c = new ListNode(3);
            b.next = c;

            ListNode d = new ListNode(4);
            c.next = d;

            ListNode e = new ListNode(5);
            d.next = e;

            removeNthFromEnd(a, 2);
        }

        if (num == 3) {
            //https://leetcode.com/problems/reverse-linked-list/description/
            ListNode a = new ListNode(-1);

            ListNode b = new ListNode(4);
            a.next = b;

            ListNode c = new ListNode(4);
            b.next = c;

            ListNode d = new ListNode(3);
            c.next = d;

            ListNode e = new ListNode(1);
            d.next = e;

            reverseList(a);
        }

        if (num == 4) {
            //https://leetcode.com/problems/valid-parentheses/description/

            String testString = "))";
            System.out.println(isValid(testString));
        }

        if (num == 5) {
            //https://leetcode.com/problems/palindrome-number/description/

            int testInt = -121;
            System.out.println(isPalindrome(testInt));
        }

        if (num == 6) {
            //https://leetcode.com/problems/add-two-numbers/description/
            //uncompleted

            ListNode a = new ListNode(2);
            ListNode b = new ListNode(4);
            a.next = b;
            ListNode c = new ListNode(3);
            b.next = c;

            ListNode d = new ListNode(5);
            ListNode e = new ListNode(6);
            d.next = e;
            ListNode f = new ListNode(4);
            e.next = f;

            addTwoNumbers(a, d);
        }

        if (num == 7) {
            //https://leetcode.com/problems/longest-substring-without-repeating-characters/description/

            String testString = "";
            System.out.println(lengthOfLongestSubstring(testString));
        }
    }
}
