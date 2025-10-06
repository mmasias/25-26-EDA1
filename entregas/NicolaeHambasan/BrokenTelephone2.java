import java.util.Scanner;

public class BrokenTelephone2 {
    static class Child {
        String name;
        int age;
        Child next;
        Child(String n, int a) { name = n; age = a; next = null; }
    }

    private Child lydiaHead = null;
    private Child aishaHead = null;
    private Child dalsyHead = null;
    private Child aishaBackupHead = null;

    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new BrokenTelephone2().run();
    }

    private void run() {
        int option;
        do {
            printMenu();
            option = readInt("Select an option: ");
            processOption(option);
            if (option != 0) {
                readString("Press ENTER to continue...");
            }
        } while (option != 0);
    }

    private void printMenu() {
        System.out.println("========================================");
        System.out.println("        PLAYROOM SIMULATION");
        System.out.println("========================================");
        System.out.println("1.  Simulate child arrival");
        System.out.println("2.  Simulate game start attempt");
        System.out.println("3.  Aisha introduces herself and asks children to introduce themselves");
        System.out.println("4.  Aisha asks children over 5 years old to introduce themselves");
        System.out.println("5.  Aisha asks children whose name starts with a letter to introduce themselves");
        System.out.println("6.  Aisha asks first five children to introduce themselves");
        System.out.println("7.  Aisha asks last five children to introduce themselves");
        System.out.println("8.  Aisha and Lydia report how many children are in queue");
        System.out.println("9.  Aisha reports average age of children in her queue");
        System.out.println("10. Simulate attempt to start frog game");
        System.out.println("11. Move children under 5 years to Dalsy");
        System.out.println("12. Fire alarm and emergency protocol");
        System.out.println("13. Show monitors and children");
        System.out.println("0.  Exit\n");
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print(prompt);
        }
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private void processOption(int option) {
        switch (option) {
            case 1: simulateChildArrival(); break;
            case 2: simulateGameStartAttempt(); break;
            case 3: generalIntroductions(); break;
            case 4: introductionsByMinimumAge(); break;
            case 5: introductionsByInitialLetter(); break;
            case 6: firstFiveIntroductions(); break;
            case 7: lastFiveIntroductions(); break;
            case 8: attendanceCount(); break;
            case 9: averageAgeAisha(); break;
            case 10: tryFrogGame(); break;
            case 11: separateChildrenForFrogGame(); break;
            case 12: fireAlarmProtocol(); break;
            case 13: showMonitorsAndChildren(); break;
            default: System.out.println("Invalid option\n"); break;
        }
    }

    private void simulateChildArrival() {
        String name = readString("Enter child's name: ");
        int age = readInt("Enter child's age: ");
        Child c = new Child(name, age);
        System.out.println("Arrives " + name + " (" + age + " years old)");
        lydiaHead = appendChild(lydiaHead, c);
        System.out.println(name + " goes to Lydia's queue\n");
    }

    private void simulateGameStartAttempt() {
        int lydiaCount = countChildren(lydiaHead);
        if (lydiaCount >= 5) {
            System.out.println("Lydia transfers her children to Aisha");
            while (lydiaHead != null) {
                Child c = lydiaHead;
                lydiaHead = lydiaHead.next;
                c.next = null;
                aishaHead = appendChild(aishaHead, c);
                System.out.println(c.name);
            }
            System.out.println();
        } else {
            System.out.println("Not enough children to start the game");
            System.out.println("At least 5 children are needed\n");
        }
    }

    private void generalIntroductions() {
        if (aishaHead == null) {
            System.out.println("No children in Aisha's queue\n");
            return;
        }
        System.out.println("Aisha: Hello, I am Aisha, monitor of this playroom");
        Child cur = aishaHead;
        while (cur != null) {
            System.out.println(cur.name + ": Hello, I am " + cur.name + " and I am " + cur.age + " years old");
            cur = cur.next;
        }
        System.out.println();
    }

    private void introductionsByMinimumAge() {
        if (aishaHead == null) {
            System.out.println("No children in Aisha's queue\n");
            return;
        }
        int minAge = readInt("Enter minimum age: ");
        System.out.println("Aisha asks children over " + minAge + " years old to introduce themselves:");
        Child cur = aishaHead;
        while (cur != null) {
            if (cur.age > minAge) {
                System.out.println(cur.name + ": Hello, I am " + cur.name + " and I am " + cur.age + " years old");
            }
            cur = cur.next;
        }
        System.out.println();
    }

    private void introductionsByInitialLetter() {
        if (aishaHead == null) {
            System.out.println("No children in Aisha's queue\n");
            return;
        }
        String input = readString("Enter initial letter: ");
        if (input.isEmpty()) {
            System.out.println("No letter entered\n");
            return;
        }
        char letter = Character.toUpperCase(input.charAt(0));
        System.out.println("Aisha asks children whose name starts with '" + letter + "' to introduce themselves:");
        Child cur = aishaHead;
        while (cur != null) {
            if (!cur.name.isEmpty() && Character.toUpperCase(cur.name.charAt(0)) == letter) {
                System.out.println(cur.name + ": Hello, I am " + cur.name);
            }
            cur = cur.next;
        }
        System.out.println();
    }

    private void firstFiveIntroductions() {
        if (aishaHead == null) {
            System.out.println("No children in Aisha's queue\n");
            return;
        }
        System.out.println("Aisha asks first five children to introduce themselves:");
        Child cur = aishaHead;
        int count = 0;
        while (cur != null && count < 5) {
            System.out.println(cur.name + ": Hello, I am " + cur.name);
            cur = cur.next;
            count++;
        }
        System.out.println();
    }

    private void lastFiveIntroductions() {
        if (aishaHead == null) {
            System.out.println("No children in Aisha's queue\n");
            return;
        }
        int size = countChildren(aishaHead);
        int skip = size > 5 ? size - 5 : 0;
        System.out.println("Aisha asks last five children to introduce themselves:");
        Child cur = aishaHead;
        for (int i = 0; i < skip; i++) cur = cur.next;
        while (cur != null) {
            System.out.println(cur.name + ": Hello, I am " + cur.name);
            cur = cur.next;
        }
        System.out.println();
    }

    private void attendanceCount() {
        int lydiaCount = countChildren(lydiaHead);
        int aishaCount = countChildren(aishaHead);
        int dalsyCount = countChildren(dalsyHead);
        int total = lydiaCount + aishaCount + dalsyCount;
        System.out.println("ATTENDANCE COUNT:");
        System.out.println("Lydia has " + lydiaCount + " children in queue");
        System.out.println("Aisha has " + aishaCount + " children in queue");
        System.out.println("Dalsy has " + dalsyCount + " children in queue");
        System.out.println("Total: " + total + " children\n");
    }

    private void averageAgeAisha() {
        if (aishaHead == null) {
            System.out.println("No children in Aisha's queue\n");
            return;
        }
        int sum = 0, count = 0;
        Child cur = aishaHead;
        while (cur != null) {
            sum += cur.age;
            count++;
            cur = cur.next;
        }
        double avg = (double) sum / count;
        System.out.printf("Average age of children in Aisha's queue: %.1f years\n\n", avg);
    }

    private void tryFrogGame() {
        if (aishaHead == null) {
            System.out.println("No children in Aisha's queue\n");
            return;
        }
        System.out.println("Checking conditions for the frog game...");
        int total = 0, eligible = 0;
        Child cur = aishaHead;
        while (cur != null) {
            if (cur.age >= 5) eligible++;
            total++;
            cur = cur.next;
        }
        System.out.println("Total children: " + total);
        System.out.println("Children aged 5 or more: " + eligible);
        if (eligible > total / 2) {
            System.out.println("More than half meet the condition!");
            System.out.println("They can play the frog game!\n");
        } else {
            System.out.println("Not enough children aged 5 or more");
            System.out.println("They cannot play yet\n");
        }
    }

    private Child appendList(Child to, Child from) {
        if (to == null) return from;
        Child cur = to;
        while (cur.next != null) cur = cur.next;
        cur.next = from;
        return to;
    }
    

    private void separateChildrenForFrogGame() {
        aishaBackupHead = copyList(aishaHead);
        Child eligible = null, ineligible = null;
        Child cur = aishaHead;
        while (cur != null) {
            Child next = cur.next;
            cur.next = null;
            if (cur.age < 5) ineligible = appendChild(ineligible, cur);
            else eligible = appendChild(eligible, cur);
            cur = next;
        }
        dalsyHead = appendChild(dalsyHead, ineligible);
        aishaHead = eligible;
        System.out.println("Separating children for the frog game...");
        System.out.println("Children under 5 years moved to Dalsy:");
        printChildren(ineligible);
        System.out.println("\nChildren staying with Aisha to play:");
        printChildren(eligible);
        System.out.println("\nNOTE: After the game, children will return to their original positions\n");
    }

    private void fireAlarmProtocol() {
        System.out.println("FIRE ALARM!");
        System.out.println("EMERGENCY PROTOCOL ACTIVATED\n");
        int transferred = 0;
        transferred += countChildren(dalsyHead);
        lydiaHead = appendList(lydiaHead, dalsyHead);
        dalsyHead = null;

        transferred += countChildren(aishaHead);
        lydiaHead = appendList(lydiaHead, aishaHead);
        aishaHead = null;
        System.out.println(transferred + " children transferred\n");
        System.out.println("Lydia now has " + countChildren(lydiaHead) + " children ready to evacuate in order\n");
    }

    private void showMonitorsAndChildren() {
        System.out.println("========================================");
        System.out.println("        CURRENT STATUS");
        System.out.println("========================================");
        System.out.println("LYDIA:");
        printChildren(lydiaHead);
        System.out.println("AISHA:");
        printChildren(aishaHead);
        System.out.println("DALSY:");
        printChildren(dalsyHead);
        System.out.println("========================================\n");
    }

    private Child appendChild(Child head, Child toAdd) {
        if (head == null) return toAdd;
        Child cur = head;
        while (cur.next != null) cur = cur.next;
        cur.next = toAdd;
        return head;
    }

    private int countChildren(Child head) {
        int count = 0;
        Child cur = head;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    private void printChildren(Child head) {
        if (head == null) {
            System.out.println("Queue is empty");
            return;
        }
        Child cur = head;
        while (cur != null) {
            System.out.println("- " + cur.name + " (" + cur.age + " years old)");
            cur = cur.next;
        }
    }

    private Child copyList(Child head) {
        Child newHead = null;
        Child tail = null;
        Child cur = head;
        while (cur != null) {
            Child copy = new Child(cur.name, cur.age);
            if (newHead == null) newHead = copy;
            else tail.next = copy;
            tail = copy;
            cur = cur.next;
        }
        return newHead;
    }

    private int transferAll(Child[] from, Child[] to) {
        int count = 0;
        Child cur = from[0];
        while (cur != null) {
            Child next = cur.next;
            cur.next = null;
            to[0] = appendChild(to[0], cur);
            count++;
            cur = next;
        }
        from[0] = null;
        return count;
    }
}