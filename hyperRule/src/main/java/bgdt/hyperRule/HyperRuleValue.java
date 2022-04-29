package bgdt.hyperRule;

import java.util.Date;
import java.util.List;


public class HyperRuleValue {
    public static final int NULL_TYPE = 0;
    public static final int INT_TYPE = 1;
    public static final int FLOAT_TYPE = 2;
    public static final int STRING_TYPE = 3;
    public static final int BOOLEAN_TYPE = 4;
    //    public static final int DOUBLE_TYPE = 5;
    public static final int DATE_TYPE = 6;
    public static final int LIST_TYPE = 7;
    //    public static int CONDITION_TYPE = 5;

    public int valueType;
    public Object value;

    public String toString() {
        String retStr;
        switch (valueType) {
            case NULL_TYPE:
                retStr = "Type: <NULL_TYPE>, Value: <null>";
                break;
            case INT_TYPE:
                retStr = "Type: <INT_TYPE>, Value: <" + (int) value + ">";
                break;
            case FLOAT_TYPE:
                retStr = "Type: <FLOAT_TYPE>, Value: <" + (double) value + ">";
                break;
            case LIST_TYPE:
                retStr = "Type: <LIST_TYPE>, Value: <" + (List) value + ">";
                break;
            case STRING_TYPE:
                retStr = "Type: <STRING_TYPE>, Value: <" + (String) value + ">";
                break;
            case BOOLEAN_TYPE:
                retStr = "Type: <BOOLEAN_TYPE>, Value: <" + (boolean) value + ">";
                break;
            case DATE_TYPE:
                retStr = "Type: <DATE_TYPE>, Value: <" + (Date) value + ">";
                break;
            default:
                retStr = "Type: <UNKONW_TYPE>, Value: <" + value.toString() + ">";
        }
        return retStr;
    }

    public String getType() {
        String retStr;
        switch (valueType) {
            case NULL_TYPE:
                retStr = "NULL_TYPE";
                break;
            case INT_TYPE:
                retStr = "INT_TYPE";
                break;
            case FLOAT_TYPE:
                retStr = "FLOAT_TYPE";
                break;
            case LIST_TYPE:
                retStr = "LIST_TYPE";
                break;
            case STRING_TYPE:
                retStr = "STRING_TYPE";
                break;
            case BOOLEAN_TYPE:
                retStr = "BOOLEAN_TYPE";
                break;
            case DATE_TYPE:
                retStr = "DATE_TYPE";
                break;
            default:
                retStr = "UNKNOWN_TYPE";
        }
        return retStr;
    }
}
