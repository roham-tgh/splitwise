package models;
/*
Explanation:
- In our app, we have groups that have some information.
- Group is something that we need to make it an object because it looks like an object (:
- put those information here and use them in your code.
 */

import java.util.ArrayList;

public class Group {
    private static int groupCount = 0;
    public static int getGroupCount() {
        return groupCount;
    }

    private final String groupName;
    private final int groupID;
    private final String groupType;
    private final User groupCreator;
    public ArrayList<User> groupMembers = null;

    public Group(String groupName, String groupType, User groupCreator) {
        groupCount++;
        this.groupName = groupName;
        this.groupID = groupCount;
        this.groupType = groupType;
        this.groupCreator = groupCreator;
        this.groupMembers = new ArrayList<>();
        this.groupMembers.add(groupCreator);
    }

    @Override
    public String toString() {
        return ("group name : " + groupName + "\n" +
                "id : " + groupID + "\n" +
                "type : " + groupType + "\n" +
                "creator : " + groupCreator.getName() + "\n" +
                "members : " + this.membersString() + "\n");
    }
    
    public String membersString() {
        StringBuilder memberListString = new StringBuilder();
        for (User member : groupMembers) {
            memberListString.append(member.getName()).append("\n");
        }
        return memberListString.toString();
    }

    public boolean isUserInGroup(User user) {
        return groupMembers.contains(user);
    }

    public User getGroupCreator() {
        return groupCreator;
    }

    public int getGroupID() {
        return groupID;
    }

    public void addMember(User user) {
        groupMembers.add(user);
    }

    public String getName() {
        return groupName;
    }
}
