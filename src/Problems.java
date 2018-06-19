import java.util.*;

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

        Stack<Integer> numStack1 = new Stack<>();
        Stack<Integer> numStack2 = new Stack<>();

        while (l1 != null) {
            numStack1.push(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            numStack2.push(l2.val);
            l2 = l2.next;
        }

        long num1 = 0;
        for (int i = numStack1.size(); i > 0; i--) {
            num1 += numStack1.pop() * Math.pow(10, i-1);
        }

        long num2 = 0;
        for (int i = numStack2.size(); i > 0; i--) {
            num2 += numStack2.pop() * Math.pow(10, i-1);
        }

        long answer = num1 + num2;

        String answerStr = String.valueOf(answer);

        ListNode prevNode = null;
        for (int i = 0; i < answerStr.length(); i++) {
            ListNode node = new ListNode(Integer.valueOf(answerStr.substring(i,i+1)));

            node.next = prevNode;
            prevNode = node;
        }

        return prevNode;
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

    public static String longestPalindrome(String s) {

        if (s.length() == 1 || s.length() == 0) {
            return s;
        }

        int length = s.length();
        int upperLimit = 0;

        int start = -1;
        int end = -1;
        int bestStart = -1;
        int bestEnd = -1;

        for (int i = 0; i < length; i++) {
            start = i;
            int lowerLimit = i;
            upperLimit = 0;
            for (int j = length-1; j >= 0; j--) {
                if (lowerLimit == j || lowerLimit > j) {
                    if(end-start > bestEnd - bestStart) {
                        bestStart = start;
                        bestEnd = end;
                        break;
                    }
                }
                else if(s.charAt(lowerLimit++) == s.charAt(j)) {
                    if (end == -1) {
                        end = j;
                    }
                }
                else {
                    end = -1;
                    lowerLimit = i;
                    j = length-1-upperLimit++;
                }
            }

        }

        if(bestEnd == -1) {
            return s.substring(0,1);
        }

        return s.substring(bestStart,bestEnd+1);
    }

    public static int strStr(String haystack, String needle) {

        if (needle.length() == 0) {
            return 0;
        }

        if(needle.length() > haystack.length()) {
            return -1;
        }

        for (int i = 0; i+needle.length() <= haystack.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                String subStr = haystack.substring(i, i + needle.length());
                if (subStr.equals(needle)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int singleNumber(int[] nums) {

        ArrayList<Integer> list = new ArrayList<>();
        int index;

        for (int num: nums) {
            if (!list.contains(num)) {
                list.add(num);
            }
            else if (list.contains(num)) {
                index = list.indexOf(num);
                list.remove(index);
            }
        }

        return list.get(0);
    }

    public static int maxSubArray(int[] nums) {

        int largestSum = 0;
        int largestSumCurrent = 0;
        boolean first = true;

        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            largestSumCurrent = nums[i];
            for (int j = i+1; j < nums.length; j++) {
                sum += nums[j];
                if (sum > largestSumCurrent) {
                    largestSumCurrent = sum;
                }
            }
            if (largestSumCurrent > largestSum) {
                largestSum = largestSumCurrent;
                first = false;
            }
            else if (first) {
                largestSum = largestSumCurrent;
                first = false;
            }
        }

        return largestSum;
    }

    public static int maxDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return Math.max(leftDepth,rightDepth) + 1;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter question you want to run: ");
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

            ListNode a = new ListNode(9);

            ListNode d = new ListNode(1);
            ListNode e = new ListNode(9);
            d.next = e;
            ListNode f = new ListNode(9);
            e.next = f;
            ListNode g = new ListNode(9);
            f.next = g;
            ListNode h = new ListNode(9);
            g.next = h;
            ListNode i = new ListNode(9);
            h.next = i;
            ListNode j = new ListNode(9);
            i.next = j;
            ListNode k = new ListNode(9);
            j.next = k;
            ListNode l = new ListNode(9);
            k.next = l;
            ListNode m = new ListNode(9);
            l.next = m;

            addTwoNumbers(a, d);
        }

        if (num == 7) {
            //https://leetcode.com/problems/longest-substring-without-repeating-characters/description/

            String testString = "";
            System.out.println(lengthOfLongestSubstring(testString));
        }

        if (num == 8) {
            //https://leetcode.com/problems/longest-palindromic-substring/description/
            //NOT COMPLETED

            String testString = "aaabaaaa";
            System.out.println(longestPalindrome(testString));
        }

        if (num == 9) {
            //https://leetcode.com/problems/implement-strstr/description/

            String testString = "mis";
            String testString2 = "ll";
            System.out.println(strStr(testString, testString2));
        }

        if (num == 10) {
            //https://leetcode.com/problems/single-number/description/

            int[] nums = {4,1,2,1,2};
            System.out.println(singleNumber(nums));
        }

        if (num == 11) {
            //https://leetcode.com/problems/maximum-subarray/description/

            int [] nums = {1,2};
            System.out.println(maxSubArray(nums));
        }

        if (num == 12) {
            //https://leetcode.com/problems/maximum-depth-of-binary-tree/description/

            TreeNode root = new TreeNode(3);
            TreeNode node1 = new TreeNode(9);
            root.left = node1;
            TreeNode node2 = new TreeNode(20);
            root.right = node2;
            TreeNode node3 = new TreeNode(15);
            node2.left = node3;
            TreeNode node4 = new TreeNode(7);
            node2.right = node4;

            System.out.println(maxDepth(root));
        }
    }
}
