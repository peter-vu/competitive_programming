package leetcode.daily.d230318;

public class DesignBrowserHistory {
}
class BrowserHistory {

    private Node head;
    private Node curr;

    public BrowserHistory(String homepage) {
        head = new Node(homepage);
        curr = head;
    }

    public void visit(String url) {
        // clear forward history
        if (curr.next != null) {
            Node next = curr.next;
            next.prev = null;
            curr.next = null;
        }
        Node page = new Node(url);
        curr.next = page;
        page.prev = curr;
        curr = curr.next;
    }

    public String back(int steps) {
        for(int i = 0; i < steps; i++) {
            if (curr.prev != null) {
                curr = curr.prev;
            }
            else {
                break;
            }
        }
        return curr.url;
    }

    public String forward(int steps) {
        for(int i = 0; i < steps; i++) {
            if (curr.next != null) {
                curr = curr.next;
            }
            else {
                break;
            }
        }
        return curr.url;
    }

    static class Node {
        String url;
        Node next;
        Node prev;
        public Node(String url) {
            this.url = url;
            next = null;
            prev = null;
        }
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */