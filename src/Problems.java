import java.util.Arrays;
import java.util.Scanner;

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

    public static ListNode reverseList(ListNode head) {

        ListNode prev;
        ListNode current;

        if (head == null) {
            return null;
        }

        if (head.next == null) {
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
        }

        if (num ==3) {
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
    }
}
