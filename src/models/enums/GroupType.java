package models.enums;
import java.util.regex.Pattern;
/*
Explanation:
- In our app, groups have some types that are constants.
- In these cases, we use enums to define them and use them in our code.
- put those types here and use them in your code.
 */
public enum GroupType {
    GROUP_TYPE("Home|Trip|Zan-o-Bache|Other");
    String groupType;
    
    GroupType(String groupType) {
        this.groupType = groupType;
    }

    public boolean isTypeValid(String inputType) {
        return Pattern.compile(groupType).matcher(inputType).matches();
    }
}
