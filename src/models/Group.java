package models;
/*
Explanation:
- In our app, we have groups that have some information.
- Group is something that we need to make it an object because it looks like an object (:
- put those information here and use them in your code.
 */

public class Group {
    private static int groupCount = 0;
    public static int getGroupCount() {
        return groupCount;
    }

    private String groupName;
    private int groupID;
    private String groupType;
    private User groupCreator;
    private ArrayList<User> groupMembers;

    Group(String groupName, String groupType, User groupCreator) {
        groupCount++;
        this.groupName = groupName;
        this.groupID = groupCount;
        this.groupType = groupType;
        this.groupCreatorID = groupCreatorID;
        this.groupCreatorName = groupCreatorName;
        groupMembers = new ArrayList<>();
        groupMembers.add(groupCreator);
    }
}
