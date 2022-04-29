package bgdt.hyperRule;

import bgdt.hyperBase.*;

import java.text.*;
import java.util.*;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;


public class HyperRuleVisitorImpl extends HyperRuleBaseVisitor<HyperRuleValue> {
    private HashMap<String, HyperRuleValue> vars;

    static Map<String, String> blank_str_dict = new HashMap<String, String>() {{
        put("\\n", "\n");
        put("\\f", "\f");
        put("\\t", "\t");
        put("\\b", "\b");
        put("\\r", "\r");
    }};

    static Map<String, String> real_str_dict = new HashMap<String, String>() {{
        put("\\\\", "\\");
        put("\\\"", "\"");
    }};

    public HyperRuleVisitorImpl() {
        vars = new HashMap<>();
    }

    public HyperRuleVisitorImpl(Map<String, HyperRuleValue> init) {
        vars = new HashMap<>();
        for (Map.Entry<String, HyperRuleValue> entry : init.entrySet()) {
            vars.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public HyperRuleValue visitStatements(HyperRuleParser.StatementsContext ctx) {
        HyperRuleValue value = null;
        for (HyperRuleParser.StatementContext stmtCtx : ctx.statement()) {
            value = visit(stmtCtx);
        }
        if (value == null) {
            value = new HyperRuleValue();
            value.valueType = HyperRuleValue.NULL_TYPE;
            value.value = null;
        }
        return value;
    }

    @Override
    public HyperRuleValue visitStatements_block(HyperRuleParser.Statements_blockContext ctx) {
        HyperRuleParser.StatementsContext stmts_ctx = ctx.statements();
        return visit(stmts_ctx);
    }

    @Override
    public HyperRuleValue visitIf_statement(HyperRuleParser.If_statementContext ctx) {
        List<HyperRuleParser.ConditionContext> condtions = ctx.condition();
        int branchIndex = -1;
        for (int i = 0; i < condtions.size(); i++) {
            HyperRuleValue condtion = visit(condtions.get(i));
            if ((boolean) condtion.value) {
                branchIndex = i;
                break;
            }
        }
        if (branchIndex == -1) branchIndex = condtions.size();
        HyperRuleParser.Condition_branchContext branchContext = ctx.condition_branch(branchIndex);
        return visit(branchContext);
    }

    private HyperRuleValue hyperRuleValue2Boolean(HyperRuleValue h) {
        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.BOOLEAN_TYPE;
        switch (h.valueType) {
            case HyperRuleValue.BOOLEAN_TYPE:
                value.value = h.value;
                break;
            case HyperRuleValue.INT_TYPE: // HyperRuleValue.INT_TYPE
                int intValue = (int) h.value;
                value.value = intValue != 0;
                break;
            case HyperRuleValue.FLOAT_TYPE:
                double doubleValue = (double) h.value;
                value.value = Math.abs(doubleValue) > 1e-10;
                break;
            case HyperRuleValue.STRING_TYPE:
                String strValue = (String) h.value;
                value.value = !strValue.isEmpty();
                break;
            case HyperRuleValue.NULL_TYPE:
                value.value = false;
                break;
            default:
                throw new HyperRuleSyntaxError("[hyperRuleValue2Boolean] Error Value type :" + h.valueType);
        }

        return value;
    }

    @Override
    public HyperRuleValue visitCondition(HyperRuleParser.ConditionContext ctx) {
        HyperRuleValue expr = visit(ctx.expression());
        return hyperRuleValue2Boolean(expr);
    }

    @Override
    public HyperRuleValue visitExprStmt(HyperRuleParser.ExprStmtContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public HyperRuleValue visitAssignStmt(HyperRuleParser.AssignStmtContext ctx) {
        return visit(ctx.assign_statement());
    }

    @Override
    public HyperRuleValue visitIfStmt(HyperRuleParser.IfStmtContext ctx) {
        return visit(ctx.if_statement());
    }

    @Override
    public HyperRuleValue visitAssignLocal(HyperRuleParser.AssignLocalContext ctx) {
        String varName = ctx.LOCAL_VAR().getText();
        HyperRuleValue value = visit(ctx.expression());
        vars.put(varName, value);
        return value;
    }


    @Override
    public HyperRuleValue visitAssignRef(HyperRuleParser.AssignRefContext ctx) {
        String varName = ctx.REF_VAR().getText();
        HyperRuleValue value = visit(ctx.expression());
        vars.put(varName, value);
        return value;
    }

    @Override
    public HyperRuleValue visitBranchStmt(HyperRuleParser.BranchStmtContext ctx) {
        return visit(ctx.statement());
    }

    @Override
    public HyperRuleValue visitBranchBlock(HyperRuleParser.BranchBlockContext ctx) {
        return visit(ctx.statements_block());
    }

    @Override
    public HyperRuleValue visitDiv(HyperRuleParser.DivContext ctx) {
        HyperRuleValue left = visit(ctx.expression(0));
        HyperRuleValue right = visit(ctx.expression(1));

        Token left_token = ctx.expression(0).getStart();
        Token right_token = ctx.expression(1).getStart();

        if (left.valueType == HyperRuleValue.INT_TYPE && right.valueType == HyperRuleValue.INT_TYPE) {
            int leftOperand = (int) left.value;
            int rightOperand = (int) right.value;
            if (Math.abs(rightOperand) < 1e-10) {
                throw new HyperRuleSyntaxError("[div] The divider should not be 0!" +
                        " at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
            }
            double retValue = leftOperand * 1.0 / rightOperand;
            HyperRuleValue value = new HyperRuleValue();
            value.valueType = HyperRuleValue.FLOAT_TYPE;
            value.value = retValue;
            return value;
        }

        double leftOperand;
        switch (left.valueType) {
            case HyperRuleValue.FLOAT_TYPE: // HyperRuleValue.FLOAT_TYPE
                leftOperand = (double) left.value;
                break;
            case HyperRuleValue.INT_TYPE: // HyperRuleValue.INT_TYPE
                leftOperand = (int) left.value;
                break;
            default:
                throw new HyperRuleSyntaxError("[div] Left operand should be a number, but it is type: "
                        + left.getType()+ ". at line " + left_token.getLine()+ ", pos "+ (left_token.getCharPositionInLine()+1));
        }

        double rightOperand;
        switch (right.valueType) {
            case HyperRuleValue.FLOAT_TYPE: // HyperRuleValue.FLOAT_TYPE
                rightOperand = (double) right.value;
                break;
            case HyperRuleValue.INT_TYPE: // HyperRuleValue.INT_TYPE
                rightOperand = (int) right.value;
                break;
            default:
                throw new HyperRuleSyntaxError("[div] Right operand should be a number, but it is type: "
                        + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
        }

        if (Math.abs(rightOperand) < 1e-10) {
            throw new HyperRuleSyntaxError("[div] The divider should not be 0!" +
                    " at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
        }

        double retValue = leftOperand / rightOperand;
        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.FLOAT_TYPE;
        value.value = retValue;
        return value;
    }

    @Override
    public HyperRuleValue visitFloor_Div(HyperRuleParser.Floor_DivContext ctx) {
        Token left_token = ctx.expression(0).getStart();
        Token right_token = ctx.expression(1).getStart();

        HyperRuleValue left = visit(ctx.expression(0));
        HyperRuleValue right = visit(ctx.expression(1));

        if (left.valueType == HyperRuleValue.INT_TYPE && right.valueType == HyperRuleValue.INT_TYPE) {
            int leftOperand = (int) left.value;
            int rightOperand = (int) right.value;
            if (Math.abs(rightOperand) < 1e-10) {
                throw new HyperRuleSyntaxError("[floor_div] The divider should not be 0!" +
                        " at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
            }
            int retValue = leftOperand / rightOperand;
            HyperRuleValue value = new HyperRuleValue();
            value.valueType = HyperRuleValue.INT_TYPE;
            value.value = retValue;
            return value;
        }

        double leftOperand;
        switch (left.valueType) {
            case HyperRuleValue.FLOAT_TYPE: // HyperRuleValue.FLOAT_TYPE
                leftOperand = (double) left.value;
                break;
            case HyperRuleValue.INT_TYPE: // HyperRuleValue.INT_TYPE
                leftOperand = (int) left.value;
                break;
            default:
                throw new HyperRuleSyntaxError("[floor_div] Left operand should be a number, but it is type: "
                        + left.getType()+ ". at line " + left_token.getLine()+ ", pos "+ (left_token.getCharPositionInLine()+1));
        }

        double rightOperand;
        switch (right.valueType) {
            case HyperRuleValue.FLOAT_TYPE: // HyperRuleValue.FLOAT_TYPE
                rightOperand = (double) right.value;
                break;
            case HyperRuleValue.INT_TYPE: // HyperRuleValue.INT_TYPE
                rightOperand = (int) right.value;
                break;
            default:
                throw new HyperRuleSyntaxError("[floor_div] Right operand should be a number, but it is type: "
                        + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
        }

        if (Math.abs(rightOperand) < 1e-10) {
            throw new HyperRuleSyntaxError("[floor_div] The divider should not be 0!" +
                    " at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
        }

        double retValue = leftOperand / rightOperand;
        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.FLOAT_TYPE;
        value.value = retValue;
        return value;
    }

    @Override
    public HyperRuleValue visitAdd(HyperRuleParser.AddContext ctx) {
        HyperRuleValue left = visit(ctx.expression(0));
        HyperRuleValue right = visit(ctx.expression(1));
        Token left_token = ctx.expression(0).getStart();
        Token right_token = ctx.expression(1).getStart();

        if (left.valueType == HyperRuleValue.STRING_TYPE || right.valueType == HyperRuleValue.STRING_TYPE) {
            String leftOperand;
            switch (left.valueType) {
                case HyperRuleValue.STRING_TYPE: // HyperRuleValue.STRING_TYPE
                    leftOperand = (String) left.value;
                    break;
                case HyperRuleValue.INT_TYPE: // HyperRuleValue.INT_TYPE
                    leftOperand = String.valueOf((int) left.value);
                    break;
                case HyperRuleValue.FLOAT_TYPE: // HyperRuleValue.FLOAT_TYPE
                    leftOperand = String.valueOf((double) left.value);
                    break;
                case HyperRuleValue.BOOLEAN_TYPE: // HyperRuleValue.BOOLEAN_TYPE
                    leftOperand = String.valueOf((boolean) left.value);
                    break;
                default:
                    throw new HyperRuleSyntaxError("[add] Left operand should be a number, a string or a boolean, but now it is type: "
                            + left.getType()+ ". at line " + left_token.getLine()+ ", pos "+ (left_token.getCharPositionInLine()+1));
            }
            String rightOperand;
            switch (right.valueType) {
                case HyperRuleValue.STRING_TYPE: // HyperRuleValue.STRING_TYPE
                    rightOperand = (String) right.value;
                    break;
                case HyperRuleValue.INT_TYPE: // HyperRuleValue.INT_TYPE
                    rightOperand = String.valueOf((int) right.value);
                    break;
                case HyperRuleValue.FLOAT_TYPE: // HyperRuleValue.FLOAT_TYPE
                    rightOperand = String.valueOf((double) right.value);
                    break;
                case HyperRuleValue.BOOLEAN_TYPE: // HyperRuleValue.BOOLEAN_TYPE
                    rightOperand = String.valueOf((boolean) right.value);
                    break;
                default:
                    throw new HyperRuleSyntaxError("[add] Right operand should be a number, a string or a boolean, but now it is type: "
                            + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
            }

            String retValue = leftOperand + rightOperand;
            HyperRuleValue value = new HyperRuleValue();
            value.valueType = HyperRuleValue.STRING_TYPE;
            value.value = retValue;
            return value;
        }

        if (left.valueType == HyperRuleValue.INT_TYPE && right.valueType == HyperRuleValue.INT_TYPE) {
            int leftOperand = (int) left.value;
            int rightOperand = (int) right.value;

            int retValue = leftOperand + rightOperand;
            HyperRuleValue value = new HyperRuleValue();
            value.valueType = HyperRuleValue.INT_TYPE;
            value.value = retValue;
            return value;
        }

        double leftOperand;
        switch (left.valueType) {
            case HyperRuleValue.FLOAT_TYPE: // HyperRuleValue.FLOAT_TYPE
                leftOperand = (double) left.value;
                break;
            case HyperRuleValue.INT_TYPE: // HyperRuleValue.INT_TYPE
                leftOperand = (int) left.value;
                break;
            default:
                throw new HyperRuleSyntaxError("[add] Left operand should be a number, but it is type: "
                        + left.getType()+ ". at line " + left_token.getLine()+ ", pos "+ (left_token.getCharPositionInLine()+1));
        }

        double rightOperand;
        switch (right.valueType) {
            case HyperRuleValue.FLOAT_TYPE: // HyperRuleValue.FLOAT_TYPE
                rightOperand = (double) right.value;
                break;
            case HyperRuleValue.INT_TYPE: // HyperRuleValue.INT_TYPE
                rightOperand = (int) right.value;
                break;
            default:
                throw new HyperRuleSyntaxError("[add] Right operand should be a number, but it is type: "
                        + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
        }

        double retValue = leftOperand + rightOperand;
        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.FLOAT_TYPE;
        value.value = retValue;
        return value;
    }

    @Override
    public HyperRuleValue visitSub(HyperRuleParser.SubContext ctx) {
        HyperRuleValue left = visit(ctx.expression(0));
        HyperRuleValue right = visit(ctx.expression(1));

        Token left_token = ctx.expression(0).getStart();
        Token right_token = ctx.expression(1).getStart();

        if (left.valueType == HyperRuleValue.INT_TYPE && right.valueType == HyperRuleValue.INT_TYPE) {
            int leftOperand = (int) left.value;
            int rightOperand = (int) right.value;
            int retValue = leftOperand - rightOperand;
            HyperRuleValue value = new HyperRuleValue();
            value.valueType = HyperRuleValue.INT_TYPE;
            value.value = retValue;
            return value;
        }

        double leftOperand;
        switch (left.valueType) {
            case HyperRuleValue.FLOAT_TYPE: // HyperRuleValue.FLOAT_TYPE
                leftOperand = (double) left.value;
                break;
            case HyperRuleValue.INT_TYPE: // HyperRuleValue.INT_TYPE
                leftOperand = (int) left.value;
                break;
            default:
                throw new HyperRuleSyntaxError("[sub] Left operand should be a number, but it is type: "
                        + left.getType()+ ". at line " + left_token.getLine()+ ", pos "+ (left_token.getCharPositionInLine()+1));
        }

        double rightOperand;
        switch (right.valueType) {
            case HyperRuleValue.FLOAT_TYPE: // HyperRuleValue.FLOAT_TYPE
                rightOperand = (double) right.value;
                break;
            case HyperRuleValue.INT_TYPE: // HyperRuleValue.INT_TYPE
                rightOperand = (int) right.value;
                break;
            default:
                throw new HyperRuleSyntaxError("[sub] Right operand should be a number, but it is type: "
                        + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
        }

        double retValue = leftOperand - rightOperand;
        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.FLOAT_TYPE;
        value.value = retValue;
        return value;
    }

    @Override
    public HyperRuleValue visitMod(HyperRuleParser.ModContext ctx) {
        HyperRuleValue left = visit(ctx.expression(0));
        HyperRuleValue right = visit(ctx.expression(1));

        Token left_token = ctx.expression(0).getStart();
        Token right_token = ctx.expression(1).getStart();

        if (left.valueType != HyperRuleValue.INT_TYPE) {
            throw new HyperRuleSyntaxError("[mod] Left operand should be a int, but it is type: "
                    + left.getType()+ ". at line " + left_token.getLine()+ ", pos "+ (left_token.getCharPositionInLine()+1));
        }

        if (right.valueType != HyperRuleValue.INT_TYPE) {
            throw new HyperRuleSyntaxError("[mod] Right operand should be a int, but it is type: "
                    + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
        }

        int leftOperand = (int) left.value;
        int rightOperand = (int) right.value;
        int retValue = leftOperand % rightOperand;
        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.INT_TYPE;
        value.value = retValue;
        return value;

    }

    /**
     * 处理字符串转义符号
     * @param str
     * @return
     */
    public static String handler_str(String str) {
        for (String key : HyperRuleVisitorImpl.blank_str_dict.keySet()) {
            String value = HyperRuleVisitorImpl.blank_str_dict.get(key);
            str = str.replace(key, value);
        }
        for (String key : HyperRuleVisitorImpl.real_str_dict.keySet()) {
            String value = HyperRuleVisitorImpl.real_str_dict.get(key);
            str = str.replace(key, value);
        }
        return str;
    }

    @Override
    public HyperRuleValue visitStringLiteral(HyperRuleParser.StringLiteralContext ctx) {
        // "a\"sdfa\ns\"dsf\fa\r\ns \tdfasd\bf\\a"

        String originStr = ctx.STRING_LITERAL().getText();
        String quoteRemovedStr = originStr.substring(1, originStr.length() - 1);

        HyperRuleVisitorImpl.handler_str(quoteRemovedStr);
        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.STRING_TYPE;
        value.value = HyperRuleVisitorImpl.handler_str(quoteRemovedStr);
        return value;
    }

    @Override
    public HyperRuleValue visitMul(HyperRuleParser.MulContext ctx) {
        HyperRuleValue left = visit(ctx.expression(0));
        HyperRuleValue right = visit(ctx.expression(1));

        Token left_token = ctx.expression(0).getStart();
        Token right_token = ctx.expression(1).getStart();

        if (left.valueType == HyperRuleValue.INT_TYPE && right.valueType == HyperRuleValue.INT_TYPE) {
            int leftOperand = (int) left.value;
            int rightOperand = (int) right.value;
            int retValue = leftOperand * rightOperand;
            HyperRuleValue value = new HyperRuleValue();
            value.valueType = HyperRuleValue.INT_TYPE;
            value.value = retValue;
            return value;
        }

        double leftOperand;
        switch (left.valueType) {
            case HyperRuleValue.FLOAT_TYPE: // HyperRuleValue.FLOAT_TYPE
                leftOperand = (double) left.value;
                break;
            case HyperRuleValue.INT_TYPE: // HyperRuleValue.INT_TYPE
                leftOperand = (int) left.value;
                break;
            default:
                throw new HyperRuleSyntaxError("[mul] Left operand should be a number, but it is type: "
                        + left.getType()+ ". at line " + left_token.getLine()+ ", pos "+ (left_token.getCharPositionInLine()+1));
        }

        double rightOperand;
        switch (right.valueType) {
            case HyperRuleValue.FLOAT_TYPE: // HyperRuleValue.FLOAT_TYPE
                rightOperand = (double) right.value;
                break;
            case HyperRuleValue.INT_TYPE: // HyperRuleValue.INT_TYPE
                rightOperand = (int) right.value;
                break;
            default:
                throw new HyperRuleSyntaxError("[mul] Right operand should be a number, but it is type: "
                        + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
        }

        double retValue = leftOperand * rightOperand;
        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.FLOAT_TYPE;
        value.value = retValue;
        return value;
    }

    @Override
    public HyperRuleValue visitFloatLiteral(HyperRuleParser.FloatLiteralContext ctx) {
        double doubleValue = Double.parseDouble(ctx.FLOAT_LITERAL().getText());
        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.FLOAT_TYPE;
        value.value = doubleValue;
        return value;
    }

    @Override
    public HyperRuleValue visitRefVar(HyperRuleParser.RefVarContext ctx) {
        String var_name = ctx.REF_VAR().getText().replace("$", "");
        HyperRuleValue value = vars.getOrDefault(var_name, null);
        if (value == null) {
            Token token = ctx.REF_VAR().getSymbol();
            throw new HyperRuleSyntaxError("Reference Variable " + var_name + " is referenced but not initialized. " +
                    "at line "+ token.getLine() + ", pos " + (token.getCharPositionInLine()+1) );
        }
        return value;
    }

    @Override
    public HyperRuleValue visitStringLiteralRefVar(HyperRuleParser.StringLiteralRefVarContext ctx) {
        String var_name = ctx.REF_VAR().getText().replace("$", "");
        HyperRuleValue ref_value = vars.getOrDefault(var_name, null);
        if (ref_value == null) {
            Token token = ctx.REF_VAR().getSymbol();
            throw new HyperRuleSyntaxError("Reference Variable " + var_name + " is referenced but not initialized. " +
                    "at line "+ token.getLine() + ", pos " + (token.getCharPositionInLine()+1) );
        }

        String originStr_left = ctx.STRING_LITERAL(0).getText();

        String quoteRemovedStr = "";
        String str_left = originStr_left.substring(1, originStr_left.length() - 1);
        str_left = HyperRuleVisitorImpl.handler_str(str_left);

        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.STRING_TYPE;

        if (ctx.STRING_LITERAL().size() == 1) {
            quoteRemovedStr = str_left + String.valueOf(ref_value.value);
        } else {
            String originStr_right = ctx.STRING_LITERAL(1).getText();
            String str_right = originStr_right.substring(1, originStr_right.length() - 1);
            str_right = HyperRuleVisitorImpl.handler_str(str_right);

            quoteRemovedStr = str_left + String.valueOf(ref_value.value) + str_right;
        }

        value.value = quoteRemovedStr;

        return value;

    }

    @Override
    public HyperRuleValue visitPow(HyperRuleParser.PowContext ctx) {
        Token left_token = ctx.expression(0).getStart();
        Token right_token = ctx.expression(1).getStart();
        HyperRuleValue left = visit(ctx.expression(0));
        HyperRuleValue right = visit(ctx.expression(1));
        double leftOperand;
        switch (left.valueType) {
            case HyperRuleValue.FLOAT_TYPE: // HyperRuleValue.FLOAT_TYPE
                leftOperand = (double) left.value;
                break;
            case HyperRuleValue.INT_TYPE: // HyperRuleValue.INT_TYPE
                leftOperand = (int) left.value;
                break;
            default:
                throw new HyperRuleSyntaxError("[pow] Left operand should be a number, but it is type: "
                        + left.getType()+ ". at line " + left_token.getLine()+ ", pos "+ (left_token.getCharPositionInLine()+1));
        }

        double rightOperand;
        switch (right.valueType) {
            case HyperRuleValue.FLOAT_TYPE: // HyperRuleValue.FLOAT_TYPE
                rightOperand = (double) right.value;
                break;
            case HyperRuleValue.INT_TYPE: // HyperRuleValue.INT_TYPE
                rightOperand = (int) right.value;
                break;
            default:
                throw new HyperRuleSyntaxError("[pow] Right operand should be a number, but it is type: "
                        + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
        }

        double retValue = Math.pow(leftOperand, rightOperand);
        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.FLOAT_TYPE;
        value.value = retValue;
        return value;
    }

    @Override
    public HyperRuleValue visitUminus(HyperRuleParser.UminusContext ctx){
        HyperRuleValue value = new HyperRuleValue();
        HyperRuleValue left = visit(ctx.expression());
        if(left.valueType == HyperRuleValue.INT_TYPE ){
            value.value = - (int)(left.value);
            value.valueType = HyperRuleValue.INT_TYPE;
        }else if(left.valueType==HyperRuleValue.FLOAT_TYPE){
            value.value = - (double)(left.value);
            value.valueType = HyperRuleValue.FLOAT_TYPE;
        }else {
            Token exp_token = ctx.expression().getStart();
            throw new HyperRuleSyntaxError("[uminus] The operand should be a number, but it is type: "
                    + left.getType()+ ". at line " + exp_token.getLine()+ ", pos "+ (exp_token.getCharPositionInLine()+1));
        }
        return value;
    }

    @Override
    public HyperRuleValue visitBooleanLiteral(HyperRuleParser.BooleanLiteralContext ctx) {
        String strValue = ctx.getText();
        boolean boolValue = strValue.equalsIgnoreCase("true");
        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.BOOLEAN_TYPE;
        value.value = boolValue;
        return value;
    }

    @Override
    public HyperRuleValue visitNullLiteral(HyperRuleParser.NullLiteralContext ctx) {
        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.NULL_TYPE;
        value.value = null;
        return value;
    }

    @Override
    public HyperRuleValue visitLocalVar(HyperRuleParser.LocalVarContext ctx) {
        String var_name = ctx.LOCAL_VAR().getText();
        HyperRuleValue value = vars.getOrDefault(var_name, null);
        if (value == null) {
            Token token = ctx.LOCAL_VAR().getSymbol();
            throw new HyperRuleSyntaxError("Local Variable " + var_name + " is referenced but not initialized. " +
                    "at line "+ token.getLine() + ", pos " + (token.getCharPositionInLine()+1) );
        }
        return value;
    }

    @Override
    public HyperRuleValue visitIntLiteral(HyperRuleParser.IntLiteralContext ctx) {
        int intValue = Integer.parseInt(ctx.INT_LITERAL().getText());
        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.INT_TYPE;
        value.value = intValue;
        return value;
    }

    @Override
    public HyperRuleValue visitParen(HyperRuleParser.ParenContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public HyperRuleValue visitNot(HyperRuleParser.NotContext ctx) {
        HyperRuleValue right = visit(ctx.expression());

        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.BOOLEAN_TYPE;
        value.value = false;

        switch (right.valueType) {
            case HyperRuleValue.NULL_TYPE:
                value.value = true;
                break;
            case HyperRuleValue.INT_TYPE:
                value.value = (int) right.value == 0;
                break;
            case HyperRuleValue.FLOAT_TYPE:
                value.value = Math.abs((double) right.value) <= 1e-10;
                break;
            case HyperRuleValue.STRING_TYPE:
                value.value = !StringUtils.isBlank(right.value.toString());
                break;
            case HyperRuleValue.BOOLEAN_TYPE:
                value.value = !(boolean) right.value;
                break;
            default:
                Token token = ctx.expression().getStart();
                throw new HyperRuleSyntaxError("[not] Expression type error, it is type: "
                        + right.getType()+ ". at line " + token.getLine()+ ", pos "+ (token.getCharPositionInLine()+1));
        }
        return value;
    }

    @Override
    public HyperRuleValue visitAnd(HyperRuleParser.AndContext ctx) {
        HyperRuleValue left = visit(ctx.expression(0));
        HyperRuleValue right = visit(ctx.expression(1));

        Token left_token = ctx.expression(0).getStart();
        Token right_token = ctx.expression(1).getStart();

        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.BOOLEAN_TYPE;
        value.value = false;

        switch (left.valueType) {
            case HyperRuleValue.NULL_TYPE:
                break;
            case HyperRuleValue.INT_TYPE:
                if (right.valueType == HyperRuleValue.NULL_TYPE) {
                    value.value = false;
                } else if (right.valueType == HyperRuleValue.INT_TYPE) {
                    value.value = (int) left.value != 0 && (int) right.value != 0;
                } else if (right.valueType == HyperRuleValue.FLOAT_TYPE) {
                    value.value = (int) left.value != 0 && Math.abs((double) right.value) > 1e-10;
                } else if (right.valueType == HyperRuleValue.STRING_TYPE) {
                    value.value = (int) left.value != 0 && StringUtils.isNotBlank(right.value.toString());
                } else if (right.valueType == HyperRuleValue.BOOLEAN_TYPE) {
                    value.value = (int) left.value != 0 && (boolean) right.value;
                } else {
                    throw new HyperRuleSyntaxError("[and] Right expression types error, it is type: "
                            + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
                }
                break;
            case HyperRuleValue.FLOAT_TYPE:
                if (right.valueType == HyperRuleValue.NULL_TYPE) {
                    value.value = false;
                } else if (right.valueType == HyperRuleValue.INT_TYPE) {
                    value.value = Math.abs((double) left.value) > 1e-10 && (int) right.value != 0;
                } else if (right.valueType == HyperRuleValue.FLOAT_TYPE) {
                    value.value = Math.abs((double) left.value) > 1e-10 && Math.abs((double) right.value) > 1e-10;
                } else if (right.valueType == HyperRuleValue.STRING_TYPE) {
                    value.value = Math.abs((double) left.value) > 1e-10 && StringUtils.isNotBlank(right.value.toString());
                } else if (right.valueType == HyperRuleValue.BOOLEAN_TYPE) {
                    value.value = Math.abs((double) left.value) > 1e-10 && (boolean) right.value;
                } else {
                    throw new HyperRuleSyntaxError("[and] Right expression types error, it is type: "
                            + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
                }
                break;
            case HyperRuleValue.STRING_TYPE:
                if (right.valueType == HyperRuleValue.NULL_TYPE) {
                    value.value = false;
                } else if (right.valueType == HyperRuleValue.INT_TYPE) {
                    value.value = StringUtils.isNotBlank(left.value.toString()) && (int) right.value != 0;
                } else if (right.valueType == HyperRuleValue.FLOAT_TYPE) {
                    value.value = StringUtils.isNotBlank(left.value.toString()) && Math.abs((double) right.value) > 1e-10;
                } else if (right.valueType == HyperRuleValue.STRING_TYPE) {
                    value.value = StringUtils.isNotBlank(left.value.toString()) && StringUtils.isNotBlank(right.value.toString());
                } else if (right.valueType == HyperRuleValue.BOOLEAN_TYPE) {
                    value.value = StringUtils.isNotBlank(left.value.toString()) && (boolean) right.value;
                } else {
                    throw new HyperRuleSyntaxError("[and] Right expression types error, it is type: "
                            + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
                }
                break;
            case HyperRuleValue.BOOLEAN_TYPE:
                if (right.valueType == HyperRuleValue.NULL_TYPE) {
                    value.value = false;
                } else if (right.valueType == HyperRuleValue.INT_TYPE) {
                    value.value = (boolean) left.value && (int) right.value != 0;
                } else if (right.valueType == HyperRuleValue.FLOAT_TYPE) {
                    value.value = (boolean) left.value && Math.abs((double) right.value) > 1e-10;
                } else if (right.valueType == HyperRuleValue.STRING_TYPE) {
                    value.value = (boolean) left.value && StringUtils.isNotBlank(right.value.toString());
                } else if (right.valueType == HyperRuleValue.BOOLEAN_TYPE) {
                    value.value = (boolean) left.value && (boolean) right.value;
                } else {
                    throw new HyperRuleSyntaxError("[and] Right expression types error, it is type: "
                            + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
                }
                break;
            default:
                throw new HyperRuleSyntaxError("[and] Left expression types error, but it is type: "
                        + left.getType()+ ". at line " + left_token.getLine()+ ", pos "+ (left_token.getCharPositionInLine()+1));
        }
        return value;
    }

    @Override
    public HyperRuleValue visitOr(HyperRuleParser.OrContext ctx) {
        HyperRuleValue left = visit(ctx.expression(0));
        HyperRuleValue right = visit(ctx.expression(1));
        Token left_token = ctx.expression(0).getStart();
        Token right_token = ctx.expression(1).getStart();

        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.BOOLEAN_TYPE;
        value.value = false;

        switch (left.valueType) {
            case HyperRuleValue.NULL_TYPE:
                break;
            case HyperRuleValue.INT_TYPE:
                if (right.valueType == HyperRuleValue.NULL_TYPE) {
                    value.value = false;
                } else if (right.valueType == HyperRuleValue.INT_TYPE) {
                    value.value = (int) left.value != 0 || (int) right.value != 0;
                } else if (right.valueType == HyperRuleValue.FLOAT_TYPE) {
                    value.value = (int) left.value != 0 || Math.abs((double) right.value) > 1e-10;
                } else if (right.valueType == HyperRuleValue.STRING_TYPE) {
                    value.value = (int) left.value != 0 || StringUtils.isNotBlank(right.value.toString());
                } else if (right.valueType == HyperRuleValue.BOOLEAN_TYPE) {
                    value.value = (int) left.value != 0 || (boolean) right.value;
                } else {
                    throw new HyperRuleSyntaxError("[or] Right expression types error, it is type: "
                            + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
                }
                break;
            case HyperRuleValue.FLOAT_TYPE:
                if (right.valueType == HyperRuleValue.NULL_TYPE) {
                    value.value = false;
                } else if (right.valueType == HyperRuleValue.INT_TYPE) {
                    value.value = Math.abs((double) left.value) > 1e-10 || (int) right.value != 0;
                } else if (right.valueType == HyperRuleValue.FLOAT_TYPE) {
                    value.value = Math.abs((double) left.value) > 1e-10 || Math.abs((double) right.value) > 1e-10;
                } else if (right.valueType == HyperRuleValue.STRING_TYPE) {
                    value.value = Math.abs((double) left.value) > 1e-10 || StringUtils.isNotBlank(right.value.toString());
                } else if (right.valueType == HyperRuleValue.BOOLEAN_TYPE) {
                    value.value = Math.abs((double) left.value) > 1e-10 || (boolean) right.value;
                } else {
                    throw new HyperRuleSyntaxError("[or] Right expression types error, it is type: "
                            + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
                }
                break;
            case HyperRuleValue.STRING_TYPE:
                if (right.valueType == HyperRuleValue.NULL_TYPE) {
                    value.value = false;
                } else if (right.valueType == HyperRuleValue.INT_TYPE) {
                    value.value = StringUtils.isNotBlank(left.value.toString()) || (int) right.value != 0;
                } else if (right.valueType == HyperRuleValue.FLOAT_TYPE) {
                    value.value = StringUtils.isNotBlank(left.value.toString()) || Math.abs((double) right.value) > 1e-10;
                } else if (right.valueType == HyperRuleValue.STRING_TYPE) {
                    value.value = StringUtils.isNotBlank(left.value.toString()) || StringUtils.isNotBlank(right.value.toString());
                } else if (right.valueType == HyperRuleValue.BOOLEAN_TYPE) {
                    value.value = StringUtils.isNotBlank(left.value.toString()) || (boolean) right.value;
                } else {
                    throw new HyperRuleSyntaxError("[or] Right expression types error, it is type: "
                            + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
                }
                break;
            case HyperRuleValue.BOOLEAN_TYPE:
                if (right.valueType == HyperRuleValue.NULL_TYPE) {
                    value.value = false;
                } else if (right.valueType == HyperRuleValue.INT_TYPE) {
                    value.value = (boolean) left.value || (int) right.value != 0;
                } else if (right.valueType == HyperRuleValue.FLOAT_TYPE) {
                    value.value = (boolean) left.value || Math.abs((double) right.value) > 1e-10;
                } else if (right.valueType == HyperRuleValue.STRING_TYPE) {
                    value.value = (boolean) left.value || StringUtils.isNotBlank(right.value.toString());
                } else if (right.valueType == HyperRuleValue.BOOLEAN_TYPE) {
                    value.value = (boolean) left.value || (boolean) right.value;
                } else {
                    throw new HyperRuleSyntaxError("[or] Right expression types error, it is type: "
                            + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
                }
                break;
            default:
                throw new HyperRuleSyntaxError("[or] Left expression types error, it is type: "
                        + left.getType()+ ". at line " + left_token.getLine()+ ", pos "+ (left_token.getCharPositionInLine()+1));
        }
        return value;
    }

    @Override
    public HyperRuleValue visitEq(HyperRuleParser.EqContext ctx) {
        HyperRuleValue left = visit(ctx.expression(0));
        HyperRuleValue right = visit(ctx.expression(1));

        Token left_token = ctx.expression(0).getStart();
        Token right_token = ctx.expression(1).getStart();

        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.BOOLEAN_TYPE;
        value.value = false;

        switch (left.valueType) {
            case HyperRuleValue.NULL_TYPE:
                if (right.valueType == HyperRuleValue.NULL_TYPE) {
                    value.value = true;
                }
                break;
            case HyperRuleValue.INT_TYPE:
                if (right.valueType == HyperRuleValue.NULL_TYPE) {
                    value.value = false;
                } else if (right.valueType == HyperRuleValue.INT_TYPE) {
                    value.value = (int) left.value == (int) right.value;
                } else if (right.valueType == HyperRuleValue.FLOAT_TYPE) {
                    value.value = Math.abs((int) left.value - (double) right.value) <= 1e-10;
                } else if (right.valueType == HyperRuleValue.STRING_TYPE) {
                    value.value = right.value.equals(left.value.toString());
                } else {
                    throw new HyperRuleSyntaxError("[eq] Right expression types error, it is type: "
                            + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
                }
                break;
            case HyperRuleValue.FLOAT_TYPE:
                if (right.valueType == HyperRuleValue.NULL_TYPE) {
                    value.value = false;
                } else if (right.valueType == HyperRuleValue.INT_TYPE) {
                    value.value = Math.abs((double) left.value - (int) right.value) <= 1e-10;
                } else if (right.valueType == HyperRuleValue.FLOAT_TYPE) {
                    value.value = Math.abs((double) left.value - (double) right.value) <= 1e-10;
                } else if (right.valueType == HyperRuleValue.STRING_TYPE) {
                    value.value = right.value.equals(left.value);
                } else {
                    throw new HyperRuleSyntaxError("[eq] Right expression types error, it is type: "
                            + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
                }
                break;
            case HyperRuleValue.STRING_TYPE:
                if (right.valueType == HyperRuleValue.NULL_TYPE) {
                    value.value = false;
                } else if (right.valueType == HyperRuleValue.INT_TYPE) {
                    value.value = left.value.equals(right.value.toString());
                } else if (right.valueType == HyperRuleValue.FLOAT_TYPE) {
                    value.value = left.value.equals(right.value.toString());
                } else if (right.valueType == HyperRuleValue.STRING_TYPE) {
                    value.value = left.value.equals(right.value);
                } else {
                    throw new HyperRuleSyntaxError("[eq] Right expression types error, it is type: "
                            + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
                }
                break;
            case HyperRuleValue.BOOLEAN_TYPE:
                if (right.valueType == HyperRuleValue.BOOLEAN_TYPE) {
                    value.value = (boolean) left.value == (boolean) right.value;
                } else {
                    throw new HyperRuleSyntaxError("[eq] Right expression types error, it is type: "
                            + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
                }
                break;
            default:
                throw new HyperRuleSyntaxError("[eq] Left expression types error, it is type: "
                        + left.getType()+ ". at line " + left_token.getLine()+ ", pos "+ (left_token.getCharPositionInLine()+1));
        }
        return value;
    }

    @Override
    public HyperRuleValue visitNe(HyperRuleParser.NeContext ctx) {
        HyperRuleValue left = visit(ctx.expression(0));
        HyperRuleValue right = visit(ctx.expression(1));

        Token left_token = ctx.expression(0).getStart();
        Token right_token = ctx.expression(1).getStart();

        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.BOOLEAN_TYPE;
        value.value = true;

        switch (left.valueType) {
            case HyperRuleValue.NULL_TYPE:
                if (right.valueType == HyperRuleValue.NULL_TYPE) {
                    value.value = false;
                }
                break;
            case HyperRuleValue.INT_TYPE:
                if (right.valueType == HyperRuleValue.NULL_TYPE) {
                    value.value = true;
                } else if (right.valueType == HyperRuleValue.INT_TYPE) {
                    value.value = (int) left.value != (int) right.value;
                } else if (right.valueType == HyperRuleValue.FLOAT_TYPE) {
                    value.value = Math.abs((int) left.value - (double) right.value) > 1e-10;
                } else if (right.valueType == HyperRuleValue.STRING_TYPE) {
                    value.value = !right.value.equals(left.value.toString());
                } else {
                    throw new HyperRuleSyntaxError("[ne] Right expression types error, it is type: "
                            + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
                }
                break;
            case HyperRuleValue.FLOAT_TYPE:
                if (right.valueType == HyperRuleValue.NULL_TYPE) {
                    value.value = true;
                } else if (right.valueType == HyperRuleValue.INT_TYPE) {
                    value.value = Math.abs((double) left.value - (int) right.value) > 1e-10;
                } else if (right.valueType == HyperRuleValue.FLOAT_TYPE) {
                    value.value = Math.abs((double) left.value - (double) right.value) > 1e-10;
                } else if (right.valueType == HyperRuleValue.STRING_TYPE) {
                    value.value = !right.value.equals(left.value.toString());
                } else {
                    throw new HyperRuleSyntaxError("[ne] Right expression types error, it is type: "
                            + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
                }
                break;
            case HyperRuleValue.STRING_TYPE:
                if (right.valueType == HyperRuleValue.NULL_TYPE) {
                    value.value = true;
                } else if (right.valueType == HyperRuleValue.INT_TYPE) {
                    value.value = !left.value.toString().equals(right.value);
                } else if (right.valueType == HyperRuleValue.FLOAT_TYPE) {
                    value.value = !left.value.toString().equals(right.value);
                } else if (right.valueType == HyperRuleValue.STRING_TYPE) {
                    value.value = !left.value.equals(right.value);
                } else {
                    throw new HyperRuleSyntaxError("[ne] Right expression types error, it is type: "
                            + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
                }
                break;
            case HyperRuleValue.BOOLEAN_TYPE:
                if (right.valueType == HyperRuleValue.BOOLEAN_TYPE) {
                    value.value = (boolean) left.value != (boolean) right.value;
                } else {
                    throw new HyperRuleSyntaxError("[ne] Right expression types error, it is type: "
                            + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
                }
                break;
            default:
                throw new HyperRuleSyntaxError("[ne] Left expression types error, it is type: "
                        + left.getType()+ ". at line " + left_token.getLine()+ ", pos "+ (left_token.getCharPositionInLine()+1));
        }
        return value;
    }

    @Override
    public HyperRuleValue visitGt(HyperRuleParser.GtContext ctx) {
        HyperRuleValue left = visit(ctx.expression(0));
        HyperRuleValue right = visit(ctx.expression(1));

        Token left_token = ctx.expression(0).getStart();
        Token right_token = ctx.expression(1).getStart();

        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.BOOLEAN_TYPE;
        value.value = false;

        switch (left.valueType) {
            case HyperRuleValue.INT_TYPE:
                if (right.valueType == HyperRuleValue.INT_TYPE) {
                    value.value = (int) left.value > (int) right.value;
                } else if (right.valueType == HyperRuleValue.FLOAT_TYPE) {
                    value.value = (int) left.value > (double) right.value;
                } else {
                    throw new HyperRuleSyntaxError("[gt] Right expression types error, it is type: "
                            + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
                }
                break;
            case HyperRuleValue.FLOAT_TYPE:
                if (right.valueType == HyperRuleValue.INT_TYPE) {
                    value.value = (double) left.value > (int) right.value;
                } else if (right.valueType == HyperRuleValue.FLOAT_TYPE) {
                    value.value = (double) left.value > (double) right.value;
                } else {
                    throw new HyperRuleSyntaxError("[gt] Right expression types error, it is type: "
                            + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
                }
                break;
            default:
                throw new HyperRuleSyntaxError("[gt] Left expression types error, it is type: "
                        + left.getType()+ ". at line " + left_token.getLine()+ ", pos "+ (left_token.getCharPositionInLine()+1));
        }
        return value;
    }

    @Override
    public HyperRuleValue visitLt(HyperRuleParser.LtContext ctx) {
        HyperRuleValue left = visit(ctx.expression(0));
        HyperRuleValue right = visit(ctx.expression(1));

        Token left_token = ctx.expression(0).getStart();
        Token right_token = ctx.expression(1).getStart();

        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.BOOLEAN_TYPE;
        value.value = false;

        switch (left.valueType) {
            case HyperRuleValue.INT_TYPE:
                if (right.valueType == HyperRuleValue.INT_TYPE) {
                    value.value = (int) left.value < (int) right.value;
                } else if (right.valueType == HyperRuleValue.FLOAT_TYPE) {
                    value.value = (int) left.value < (double) right.value;
                } else {
                    throw new HyperRuleSyntaxError("[lt] Right expression types error, it is type: "
                            + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
                }
                break;
            case HyperRuleValue.FLOAT_TYPE:
                if (right.valueType == HyperRuleValue.INT_TYPE) {
                    value.value = (double) left.value < (int) right.value;
                } else if (right.valueType == HyperRuleValue.FLOAT_TYPE) {
                    value.value = (double) left.value < (double) right.value;
                } else {
                    throw new HyperRuleSyntaxError("[lt] Right expression types error, it is type: "
                            + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
                }
                break;
            default:
                throw new HyperRuleSyntaxError("[lt] Left expression types error, it is type: "
                        + left.getType()+ ". at line " + left_token.getLine()+ ", pos "+ (left_token.getCharPositionInLine()+1));
        }
        return value;
    }

    @Override
    public HyperRuleValue visitGe(HyperRuleParser.GeContext ctx) {
        HyperRuleValue left = visit(ctx.expression(0));
        HyperRuleValue right = visit(ctx.expression(1));

        Token left_token = ctx.expression(0).getStart();
        Token right_token = ctx.expression(1).getStart();

        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.BOOLEAN_TYPE;
        value.value = false;

        switch (left.valueType) {
            case HyperRuleValue.INT_TYPE:
                if (right.valueType == HyperRuleValue.INT_TYPE) {
                    value.value = (int) left.value >= (int) right.value;
                } else if (right.valueType == HyperRuleValue.FLOAT_TYPE) {
                    value.value = (int) left.value >= (double) right.value;
                } else {
                    throw new HyperRuleSyntaxError("[ge] Right expression types error, it is type: "
                            + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
                }
                break;
            case HyperRuleValue.FLOAT_TYPE:
                if (right.valueType == HyperRuleValue.INT_TYPE) {
                    value.value = (double) left.value >= (int) right.value;
                } else if (right.valueType == HyperRuleValue.FLOAT_TYPE) {
                    value.value = (double) left.value >= (double) right.value;
                } else {
                    throw new HyperRuleSyntaxError("[ge] Right expression types error, it is type: "
                            + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
                }
                break;
            default:
                throw new HyperRuleSyntaxError("[ge] Left expression types error, it is type: "
                        + left.getType()+ ". at line " + left_token.getLine()+ ", pos "+ (left_token.getCharPositionInLine()+1));
        }
        return value;
    }

    @Override
    public HyperRuleValue visitLe(HyperRuleParser.LeContext ctx) {
        HyperRuleValue left = visit(ctx.expression(0));
        HyperRuleValue right = visit(ctx.expression(1));

        Token left_token = ctx.expression(0).getStart();
        Token right_token = ctx.expression(1).getStart();

        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.BOOLEAN_TYPE;
        value.value = false;

        switch (left.valueType) {
            case HyperRuleValue.INT_TYPE:
                if (right.valueType == HyperRuleValue.INT_TYPE) {
                    value.value = (int) left.value <= (int) right.value;
                } else if (right.valueType == HyperRuleValue.FLOAT_TYPE) {
                    value.value = (int) left.value <= (double) right.value;
                } else {
                    throw new HyperRuleSyntaxError("[le] Right expression types error, it is type: "
                            + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
                }
                break;
            case HyperRuleValue.FLOAT_TYPE:
                if (right.valueType == HyperRuleValue.INT_TYPE) {
                    value.value = (double) left.value <= (int) right.value;
                } else if (right.valueType == HyperRuleValue.FLOAT_TYPE) {
                    value.value = (double) left.value <= (double) right.value;
                } else {
                    throw new HyperRuleSyntaxError("[le] Right expression types error, it is type: "
                            + right.getType()+ ". at line " + right_token.getLine()+ ", pos "+ (right_token.getCharPositionInLine()+1));
                }
                break;
            default:
                throw new HyperRuleSyntaxError("[le] Left expression types error, it is type: "
                        + left.getType()+ ". at line " + left_token.getLine()+ ", pos "+ (left_token.getCharPositionInLine()+1));
        }
        return value;
    }

    /**
     * sum(1,2,3,4,-5)
     * sum(1,2,3,4,5.5)
     *
     * @param ctx
     * @return
     */
    @Override
    public HyperRuleValue visitSum(HyperRuleParser.SumContext ctx) {

        HyperRuleValue rsp = visit(ctx.paralist());
        ArrayList params = (ArrayList) rsp.value;
        Token token = ctx.paralist().getStart();

        double sum;
        int int_sum = 0;
        double double_sum = 0;

        for (int i = 0; i < params.size(); i++) {
            switch ((((HyperRuleValue) (params.get(i))).valueType)) {
                case HyperRuleValue.INT_TYPE:
                    int_sum += (int) (((HyperRuleValue) (params.get(i))).value);
                    break;
                case HyperRuleValue.FLOAT_TYPE:
                    double_sum += (double) (((HyperRuleValue) (params.get(i))).value);
                    break;
                default:
                    throw new HyperRuleParameterError("[sum] " +(i+1)+ "th parameter types error, it is type: "
                            + ((HyperRuleValue) (params.get(i))).getType()+ ". at line " + token.getLine()+ ", " +
                            "pos "+ (token.getCharPositionInLine()+1));
            }
        }

        sum = int_sum + double_sum;

        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.FLOAT_TYPE;
        value.value = sum;

        return value;
    }

    /**
     * avg(1,2,3,4,5.5)
     * avg(1,2,3,4,-5)
     *
     * @param ctx
     * @return
     */
    @Override
    public HyperRuleValue visitAvg(HyperRuleParser.AvgContext ctx) {
        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.FLOAT_TYPE;

        HyperRuleValue rsp = visit(ctx.paralist());
        ArrayList params = (ArrayList) rsp.value;

        double sum;
        int int_sum = 0;
        double double_sum = 0;
        for (int i = 0; i < params.size(); i++) {
            switch ((((HyperRuleValue) (params.get(i))).valueType)) {
                case HyperRuleValue.INT_TYPE:
                    int_sum += (int) (((HyperRuleValue) (params.get(i))).value);
                    break;
                case HyperRuleValue.FLOAT_TYPE:
                    double_sum += (double) (((HyperRuleValue) (params.get(i))).value);
                    break;
                default:
                    Token token = ctx.paralist().getStart();
                    throw new HyperRuleParameterError("[avg] " +(i+1)+ "th parameter types error, it is type: "
                            + ((HyperRuleValue) (params.get(i))).getType()+ ". at line " + token.getLine()+ ", " +
                            "pos "+ (token.getCharPositionInLine()+1));
            }
        }

        sum = int_sum + double_sum;

        int cnt = params.size();
        value.value = sum / cnt;

        return value;
    }

    /**
     * sqrt(9)
     * sqrt(-9)
     * sqrt(9.9)
     *
     * @param ctx
     * @return
     */
    @Override
    public HyperRuleValue visitSqrt(HyperRuleParser.SqrtContext ctx) {
        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.FLOAT_TYPE;

        HyperRuleValue rsp = visit(ctx.paralist());
        ArrayList params = (ArrayList) rsp.value;
        Token token = ctx.paralist().getStart();

        if (params.size() > 1) {
            token = ctx.paralist().getStop();
            throw new HyperRuleParameterError("sqrt function takes exactly one argument, but it was given " +
                    params.size() + ". at line " + token.getLine() + ", pos " + (token.getCharPositionInLine()+1));
        }

        double num = 0;
        HyperRuleValue param = (HyperRuleValue) (params.get(0));

        switch (param.valueType) {
            case HyperRuleValue.INT_TYPE:
                num = (int) param.value;
                break;
            case HyperRuleValue.FLOAT_TYPE:
                num = (double) param.value;
                break;
            default:
                throw new HyperRuleParameterError("[sqrt] The parameter types error, it is type: "
                        + param.getType()+ ". at line " + token.getLine()+ ", " +
                        "pos "+ (token.getCharPositionInLine()+1) );
        }
        if(num < 0){
            throw new HyperRuleParameterError("[sqrt] The parameter must be a number greater than or " +
                    "equal to 0. at line " + token.getLine() + ", pos " + (token.getCharPositionInLine()+1) );
        }
        value.value = Math.sqrt(num);
        return value;
    }

    /**
     * log(9);
     * log(-9);
     * log(9, 3);
     * log(9, -3);
     *
     * @param ctx
     * @return
     */
    @Override
    public HyperRuleValue visitLog(HyperRuleParser.LogContext ctx) {
        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.FLOAT_TYPE;

        HyperRuleValue rsp = visit(ctx.paralist());
        ArrayList params = (ArrayList) rsp.value;
        Token token = ctx.paralist().getStart();

        if (params.size() > 2) {
            token = ctx.paralist().getStop();
            throw new HyperRuleParameterError("log function takes exactly one or two argument, but it was given " +
                    params.size() + ". at line " + token.getLine() + ", pos " + (token.getCharPositionInLine()+1));
        }

        double first_num = 0;
        HyperRuleValue first_param = (HyperRuleValue) (params.get(0));

        switch (first_param.valueType) {
            case HyperRuleValue.INT_TYPE:
                first_num = (int) first_param.value;
                break;
            case HyperRuleValue.FLOAT_TYPE:
                first_num = (double) first_param.value;
                break;
            default:
                throw new HyperRuleParameterError("[log] The first parameter types error, it is type: "
                        + first_param.getType()+ ". at line " + token.getLine()+ ", " +
                        "pos "+ (token.getCharPositionInLine()+1));
        }
        if(first_num < 0){
            throw new HyperRuleParameterError("[log] The first parameter must be a number greater than or " +
                    "equal to 0. at line " + token.getLine() + ", pos " + (token.getCharPositionInLine()+1));
        }

        if(params.size() == 1){
            value.value = Math.log(first_num);
            return value;
        }

        token = ctx.paralist().getStop();
        double second_num = 0;
        HyperRuleValue second_param = (HyperRuleValue) (params.get(1));

        switch (second_param.valueType) {
            case HyperRuleValue.INT_TYPE:
                second_num = (int) second_param.value;
                break;
            case HyperRuleValue.FLOAT_TYPE:
                second_num = (double) second_param.value;
                break;
            default:
                throw new HyperRuleParameterError("[log] The second parameter types error, it is type: "
                        + second_param.getType()+ ". at line " + token.getLine()+ ", " +
                        "pos "+ (token.getCharPositionInLine()+1));
        }
        if(second_num < 0){
            throw new HyperRuleParameterError("[log] The second parameter must be a number greater than or " +
                    "equal to 0. at line " + token.getLine() + ", pos " + (token.getCharPositionInLine()+1) );
        }
        value.value = Math.log(first_num) / Math.log(second_num);

        return value;
    }

    /**
     * count(1,2,3,4,3)
     *
     * @param ctx
     * @return
     */
    @Override
    public HyperRuleValue visitCount(HyperRuleParser.CountContext ctx) {
        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.INT_TYPE;

        HyperRuleValue rsp = visit(ctx.paralist());
        ArrayList params = (ArrayList) rsp.value;
        value.value = params.size();

        return value;
    }

    /**
     * len("fadsfasd");
     *
     * @param ctx
     * @return string length
     */
    @Override
    public HyperRuleValue visitLen(HyperRuleParser.LenContext ctx) {
        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.INT_TYPE;

        HyperRuleValue rsp = visit(ctx.paralist());
        ArrayList params = (ArrayList) rsp.value;

        Token token = ctx.paralist().getStop();

        if (params.size() > 1) {
            throw new HyperRuleParameterError("len function takes exactly one argument, but it was given " +
                    params.size() + ". at line " + token.getLine() + ", pos " + (token.getCharPositionInLine()+1));
        }
        HyperRuleValue param = ((HyperRuleValue) (params.get(0)));
        if(param.valueType != HyperRuleValue.STRING_TYPE){
            throw new HyperRuleParameterError("[len] The parameter types error, it is type: "
                    + param.getType()+ ". at line " + token.getLine()+ ", " +
                    "pos "+ (token.getCharPositionInLine()+1));
        }
        String str = (String) param.value;
        value.value = str.length();

        return value;
    }

    /**
     * max(1,7,-3,90801,0)
     *
     * max(1, 7.6, -3.3, 90801, 0)
     * max(111,76.2,3.3,90801,10)
     *
     * @param ctx
     * @return
     */
    @Override
    public HyperRuleValue visitMax(HyperRuleParser.MaxContext ctx) {

        HyperRuleValue value = new HyperRuleValue();

        HyperRuleValue rsp = visit(ctx.paralist());
        ArrayList params = (ArrayList) rsp.value;

        List doubleParams = new ArrayList();
        List intParams = new ArrayList();

        for (int i = 0; i < params.size(); i++) {
            switch ((((HyperRuleValue) (params.get(i))).valueType)) {
                case HyperRuleValue.INT_TYPE:
                    intParams.add(((HyperRuleValue) (params.get(i))).value);
                    break;
                case HyperRuleValue.FLOAT_TYPE:
                    doubleParams.add(((HyperRuleValue) (params.get(i))).value);
                    break;
                default:
                    Token token = ctx.paralist().getStart();
                    throw new HyperRuleParameterError("[max] " +(i+1)+ "th parameter types error, it is type: "
                            + ((HyperRuleValue) (params.get(i))).getType()+ ". at line " + token.getLine()+ ", " +
                            "pos "+ (token.getCharPositionInLine()+1));
            }
        }
        if(intParams.size() == 0 && doubleParams.size() > 0){
            double float_max = (double) Collections.max(doubleParams);
            value.value = float_max;
            value.valueType = HyperRuleValue.FLOAT_TYPE;
            return value;
        }
        if (doubleParams.size() ==0 && intParams.size() > 0){
            int int_max = (int) Collections.max(intParams);
            value.value = int_max;
            value.valueType = HyperRuleValue.INT_TYPE;
            return value;
        }

        int int_max = (int) Collections.max(intParams);
        double float_max = (double) Collections.max(doubleParams);

        if (int_max > float_max) {
            value.value = int_max;
            value.valueType = HyperRuleValue.INT_TYPE;
        } else {
            value.value = float_max;
            value.valueType = HyperRuleValue.FLOAT_TYPE;
        }
        return value;
    }

    /**
     * min(1,-76,3,90801,0)
     * min(111,76.2,-3.3,90801,10)
     *
     * @param ctx
     * @return
     */
    @Override
    public HyperRuleValue visitMin(HyperRuleParser.MinContext ctx) {

        HyperRuleValue value = new HyperRuleValue();

        HyperRuleValue rsp = visit(ctx.paralist());
        ArrayList params = (ArrayList) rsp.value;

        List doubleParams = new ArrayList();
        List intParams = new ArrayList();
        Token token = ctx.paralist().getStart();

        for (int i = 0; i < params.size(); i++) {
            switch ((((HyperRuleValue) (params.get(i))).valueType)) {
                case HyperRuleValue.INT_TYPE:
                    intParams.add(((HyperRuleValue) (params.get(i))).value);
                    break;
                case HyperRuleValue.FLOAT_TYPE:
                    doubleParams.add(((HyperRuleValue) (params.get(i))).value);
                    break;
                default:
                    throw new HyperRuleParameterError("[min] " +(i+1)+ "th parameter types error, it is type: "
                            + ((HyperRuleValue) (params.get(i))).getType()+ ". at line " + token.getLine()+ ", " +
                            "pos "+ (token.getCharPositionInLine()+1));
            }
        }

        if(intParams.size() == 0 && doubleParams.size() > 0){
            double float_max = (double) Collections.min(doubleParams);
            value.value = float_max;
            value.valueType = HyperRuleValue.FLOAT_TYPE;
            return value;
        }
        if (doubleParams.size() ==0 && intParams.size() > 0){
            int int_max = (int) Collections.min(intParams);
            value.value = int_max;
            value.valueType = HyperRuleValue.INT_TYPE;
            return value;
        }

        int int_min = (int) Collections.min(intParams);
        double float_min = (double) Collections.min(doubleParams);

        if (int_min < float_min) {
            value.value = int_min;
            value.valueType = HyperRuleValue.INT_TYPE;
        } else {
            value.value = float_min;
            value.valueType = HyperRuleValue.FLOAT_TYPE;
        }
        return value;
    }

    /**
     * substr("abcdefght")
     * substr("abcdefght", 1)
     * substr("abcdefght", 1, 3)
     *
     * @param ctx
     * @return
     */
    @Override
    public HyperRuleValue visitSubstr(HyperRuleParser.SubstrContext ctx) {

        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.STRING_TYPE;

        HyperRuleValue rsp = visit(ctx.paralist());
        ArrayList params = (ArrayList) rsp.value;
        Token token = ctx.paralist().getStop();

        if (params.size() > 3) {
            throw new HyperRuleParameterError("substr function takes exactly one, two or three argument, but it was given " +
                    params.size() + ". at line " + token.getLine() + ", pos " + (token.getCharPositionInLine()+1));
        }

        HyperRuleValue param = ((HyperRuleValue) (params.get(0)));
        if(param.valueType != HyperRuleValue.STRING_TYPE){
            token = ctx.paralist().getStart();
            throw new HyperRuleParameterError("[substr] The first parameter types error, it is type: "
                    + param.getType()+ ". at line " + token.getLine()+ ", " +
                    "pos "+ (token.getCharPositionInLine()+1));
        }

        String str = (String) param.value;

        if (params.size() == 1) {
            value.value = str;
            return value;
        }

        HyperRuleValue first = ((HyperRuleValue) (params.get(1)));
        if(first.valueType != HyperRuleValue.INT_TYPE){
            throw new HyperRuleParameterError("[substr] The second parameter types error, it is type: "
                    + first.getType()+ ". at line " + token.getLine()+ ", " +
                    "pos "+ (token.getCharPositionInLine()+1));
        }

        int first_val = (int) first.value;

        if (params.size() == 2) {
            value.value = StringUtils.substring(str, first_val);
        } else if (params.size() == 3) {
            HyperRuleValue second = ((HyperRuleValue) (params.get(2)));
            if(second.valueType != HyperRuleValue.INT_TYPE){
                throw new HyperRuleParameterError("[substr] The third parameter types error, it is type: "
                        + second.getType()+ ". at line " + token.getLine()+ ", " +
                        "pos "+ (token.getCharPositionInLine()+1));
            }
            int second_value = (int) second.value;
            value.value = StringUtils.substring(str, first_val, second_value);
        }

        return value;
    }

    /**
     * trim(" sdfasd 8712 ")
     *
     * @param ctx
     * @return
     */
    @Override
    public HyperRuleValue visitTrim(HyperRuleParser.TrimContext ctx) {
        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.STRING_TYPE;

        HyperRuleValue rsp = visit(ctx.paralist());
        ArrayList params = (ArrayList) rsp.value;
        Token token = ctx.paralist().getStop();

        if (params.size() > 1) {
            throw new HyperRuleParameterError("trim function takes exactly one argument, but it was given " +
                    params.size() + ". at line " + token.getLine() + ", pos " + (token.getCharPositionInLine()+1));
        } else {
            HyperRuleValue param = (HyperRuleValue) (params.get(0));
            if(param.valueType != HyperRuleValue.STRING_TYPE){
                throw new HyperRuleParameterError("[trim] The parameter types error, it is type: "
                        + param.getType()+ ". at line " + token.getLine()+ ", " +
                        "pos "+ (token.getCharPositionInLine()+1));
            }
            value.value = StringUtils.trim((String) param.value);
        }

        return value;
    }

    /**
     * is_number("889.1231")
     * is_number("889.1231a")
     * is_number("889.1231e5")
     * is_number("889.1231E-5")
     *
     * @param ctx
     * @return
     */
    @Override
    public HyperRuleValue visitIs_number(HyperRuleParser.Is_numberContext ctx) {
        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.BOOLEAN_TYPE;

        HyperRuleValue rsp = visit(ctx.paralist());
        ArrayList params = (ArrayList) rsp.value;
        Token token = ctx.paralist().getStop();

        if (params.size() > 1) {
            throw new HyperRuleParameterError("is_number function takes exactly one argument, but it was given " +
                    params.size() + ". at line " + token.getLine() + ", pos " + (token.getCharPositionInLine()+1));
        } else {
            try {
                HyperRuleValue param = ((HyperRuleValue) (params.get(0)));
                if(param.valueType != HyperRuleValue.STRING_TYPE){
                    throw new HyperRuleParameterError("[is_number] The parameter types error, it is type: "
                            + param.getType()+ ". at line " + token.getLine()+ ", " +
                            "pos "+ (token.getCharPositionInLine()+1));
                }
                String str = (String) param.value;
                Double.parseDouble(str);
                value.value = true;
            } catch (NumberFormatException e) {
                value.value = false;
            }
        }

        return value;
    }

    /**
     * is_int("889.1231")
     * is_int("-889")
     *
     * @param ctx
     * @return
     */
    @Override
    public HyperRuleValue visitIs_int(HyperRuleParser.Is_intContext ctx) {
        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.BOOLEAN_TYPE;

        HyperRuleValue rsp = visit(ctx.paralist());
        ArrayList params = (ArrayList) rsp.value;

        Token token = ctx.paralist().getStop();

        if (params.size() > 1) {
            throw new HyperRuleParameterError("is_int function takes exactly one argument, but it was given " +
                    params.size() + ". at line " + token.getLine() + ", pos " + (token.getCharPositionInLine()+1));
        } else {
            try {
                HyperRuleValue param = ((HyperRuleValue) (params.get(0)));
                if(param.valueType != HyperRuleValue.STRING_TYPE){
                    throw new HyperRuleParameterError("[is_int] The parameter types error, it is type: "
                            + param.getType()+ ". at line " + token.getLine()+ ", " +
                            "pos "+ (token.getCharPositionInLine()+1));
                }
                String str = (String) param.value;
                Integer.parseInt(str);
                value.value = true;
            } catch (NumberFormatException e) {
                value.value = false;
            }
        }

        return value;
    }

    /**
     * is_null(null)   true
     * is_null("null") false
     * is_null("")     true
     * is_null(" ")    false
     *
     * @param ctx
     * @return
     */
    @Override
    public HyperRuleValue visitIs_null(HyperRuleParser.Is_nullContext ctx) {

        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.BOOLEAN_TYPE;

        HyperRuleValue rsp = visit(ctx.paralist());
        ArrayList params = (ArrayList) rsp.value;

        if (params.size() > 1) {
            Token token = ctx.paralist().getStop();
            throw new HyperRuleParameterError("is_null function takes exactly one argument, but it was given " +
                    params.size() + ". at line " + token.getLine() + ", pos " + (token.getCharPositionInLine()+1));
        }

        HyperRuleValue param = ((HyperRuleValue) (params.get(0)));
        String str;
        switch (param.valueType){
            case HyperRuleValue.INT_TYPE:
                str = String.valueOf((int) param.value);
                break;
            case HyperRuleValue.FLOAT_TYPE:
                str = String.valueOf((double) param.value);
                break;
            case HyperRuleValue.BOOLEAN_TYPE:
                str = String.valueOf((boolean) param.value);
                break;
            case HyperRuleValue.STRING_TYPE: case HyperRuleValue.NULL_TYPE:
                str = (String) param.value;
                break;
            case HyperRuleValue.DATE_TYPE:
                str = String.valueOf((Date) param.value);
                break;
            default:
                str = "";
        }
        value.value = StringUtils.isEmpty(str);


        return value;
    }

    /**
     * to_str(12312)
     * to_str(sysdate())
     *
     * @param ctx
     * @return
     */
    @Override
    public HyperRuleValue visitTo_str(HyperRuleParser.To_strContext ctx) {

        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.STRING_TYPE;

        HyperRuleValue rsp = visit(ctx.paralist());
        ArrayList params = (ArrayList) rsp.value;

        if (params.size() > 1) {
            Token token = ctx.paralist().getStop();
            throw new HyperRuleParameterError("to_str function takes exactly one argument, but it was given " +
                    params.size() + ". at line " + token.getLine() + ", pos " + (token.getCharPositionInLine()+1));
        } else {
            int type = ((HyperRuleValue) (params.get(0))).valueType;
            switch (type){
                case HyperRuleValue.DATE_TYPE:
                    Date date_val = (Date)((HyperRuleValue) (params.get(0))).value;
                    String str_date=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date_val);
                    value.value = str_date;
                    return value;
            }
            value.value = String.valueOf(((HyperRuleValue) (params.get(0))).value);
        }

        return value;
    }

    /**
     * to_int("-1123")
     * to_int("1123.343")
     * to_int("1123.343s")
     * to_int("0")
     *
     * @param ctx
     * @return
     */
    @Override
    public HyperRuleValue visitTo_int(HyperRuleParser.To_intContext ctx) {

        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.INT_TYPE;

        HyperRuleValue rsp = visit(ctx.paralist());
        ArrayList params = (ArrayList) rsp.value;
        Token token = ctx.paralist().getStop();

        if (params.size() > 1) {
            throw new HyperRuleParameterError("to_int function takes exactly one argument, but it was given " +
                    params.size() + ". at line " + token.getLine() + ", pos " + (token.getCharPositionInLine()+1));
        } else {
            String str = (String) ((HyperRuleValue) (params.get(0))).value;
            try {
                value.value = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                throw new HyperRuleSyntaxError("[to_int] NumberFormatException: For input string: " + str + ". " +
                        "at line " + token.getLine() + ", pos " + (token.getCharPositionInLine()+1));
            }

        }

        return value;
    }

    /**
     * to_float("1123")
     * to_float("1123.343")
     * to_float("-1123.343e2")
     * to_float("-1123.343E-2")
     * to_float("1123.343a")
     *
     * @param ctx
     * @return
     */
    @Override
    public HyperRuleValue visitTo_float(HyperRuleParser.To_floatContext ctx) {
        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.FLOAT_TYPE;

        HyperRuleValue rsp = visit(ctx.paralist());
        ArrayList params = (ArrayList) rsp.value;
        Token token = ctx.paralist().getStop();

        if (params.size() > 1) {
            throw new HyperRuleParameterError("to_float function takes exactly one argument, but it was given " +
                    params.size() + ". at line " + token.getLine() + ", pos " + (token.getCharPositionInLine()+1));
        } else {
            String str = (String) ((HyperRuleValue) (params.get(0))).value;
            try {
                value.value = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                throw new HyperRuleSyntaxError("[to_float] NumberFormatException: For input string: " + str + ". " +
                        "at line " + token.getLine() + ", pos " + (token.getCharPositionInLine()+1));
            }
        }
        return value;
    }

    /**
     * to_date("2017-10-09")
     * to_date("2017/10/09", "yyyy/MM/dd")
     * to_date("2017/10/09 10:04:20", "yyyy/MM/dd HH:mm:ss")
     *
     * @param ctx
     * @return
     */
    @Override
    public HyperRuleValue visitTo_date(HyperRuleParser.To_dateContext ctx) {

        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.DATE_TYPE;

        HyperRuleValue rsp = visit(ctx.paralist());
        ArrayList params = (ArrayList) rsp.value;

        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        if (params.size() > 2) {
            Token token = ctx.paralist().getStop();
            throw new HyperRuleParameterError("to_date function takes exactly one or two argument, but it was given " +
                    params.size() + ". at line " + token.getLine() + ", pos " + (token.getCharPositionInLine()+1));
        }

        String date_str = (String) ((HyperRuleValue) (params.get(0))).value;

        if (params.size() == 1) {
            if (date_str.contains(":")) {
                fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }

        } else if (params.size() == 2) {
            String fmt_str = (String) ((HyperRuleValue) (params.get(1))).value;
            fmt = new SimpleDateFormat(fmt_str);
        }

        try {
            value.value = fmt.parse(date_str);
        } catch (ParseException e) {
            Token token = ctx.paralist().getStart();
            throw new HyperRuleParameterError("[to_date] format error. at line " + token.getLine() +
                    ", pos " + (token.getCharPositionInLine()+1));
        }
        return value;
    }

    /**
     * example 2: add_to_date(to_date("2017-09-01"), "year", 1)
     * example 2: add_to_date(to_date("2017-09-01"), "month", 5)
     * example 2: add_to_date(to_date("2017-09-01"), "month", 15)
     * example 2: add_to_date(to_date("2017-09-01"), "month", 24)
     * example 2: add_to_date(to_date("2000-01-31"), "month", 1)
     * example 2: add_to_date(to_date("2000-03-31"), "month", -1)
     *
     * example 2: add_to_date(to_date("2001-03-31"), "month", -1)
     *
     * example 2: add_to_date(to_date("2017-09-01"), "month", 24)
     * example 2: add_to_date(to_date("2017-09-01"), "week", 10)
     * example 2: add_to_date(to_date("2017-09-01"), "week", 50)
     * example 2: add_to_date(to_date("2017-09-01"), "day", 1)
     * example 2: add_to_date(to_date("2017-03-01"), "day", -1)
     * example 2: add_to_date(to_date("2017-09-01"), "second", -1)
     * example 2: add_to_date(to_date("2017-09-01"), "minute", 2)
     * example 2: add_to_date(to_date("2017-09-01"), "hour", 3)
     *
     * @param ctx
     * @return
     */
    @Override
    public HyperRuleValue visitAdd_to_date(HyperRuleParser.Add_to_dateContext ctx) {

        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.DATE_TYPE;

        HyperRuleValue rsp = visit(ctx.paralist());
        ArrayList params = (ArrayList) rsp.value;
        Token token = ctx.paralist().getStop();
        if (params.size() != 3) {
            throw new HyperRuleParameterError("add_to_date function takes exactly three argument, but it was given " +
                    params.size() + ". at line " + token.getLine() + ", pos " + (token.getCharPositionInLine()+1));
        }
        HyperRuleValue date_value = ((HyperRuleValue) (params.get(0)));
        HyperRuleValue type_value = ((HyperRuleValue) (params.get(1)));
        HyperRuleValue timedelta_value = ((HyperRuleValue) (params.get(2)));

        if(date_value.valueType != HyperRuleValue.DATE_TYPE){
            token = ctx.paralist().getStart();
            throw new HyperRuleParameterError("[add_to_date] The first parameter types error, it is type: "
                    + date_value.getType()+ ". at line " + token.getLine()+ ", " +
                    "pos "+ (token.getCharPositionInLine()+1));
        }
        if(type_value.valueType != HyperRuleValue.STRING_TYPE){
            throw new HyperRuleParameterError("[add_to_date] The second parameter types error, it is type: "
                    + type_value.getType()+ ". at line " + token.getLine()+ ", " +
                    "pos "+ (token.getCharPositionInLine()+1));
        }
        if(timedelta_value.valueType != HyperRuleValue.INT_TYPE){
            throw new HyperRuleParameterError("[add_to_date] The third parameter types error, it is type: "
                    + timedelta_value.getType()+ ". at line " + token.getLine()+ ", " +
                    "pos "+ (token.getCharPositionInLine()+1));
        }

        Date date = (Date) date_value.value;
        String type = (String) type_value.value;
        int timedelta = (Integer) timedelta_value.value;

        switch (type) {
            case "year":
                date = DateUtils.addYears(date, timedelta);
                break;
            case "month":
                date = DateUtils.addMonths(date, timedelta);
                break;
            case "week":
                date = DateUtils.addWeeks(date, timedelta);
                break;
            case "day":
                date = DateUtils.addDays(date, timedelta);
                break;
            case "hour":
                date = DateUtils.addHours(date, timedelta);
                break;
            case "minute":
                date = DateUtils.addMinutes(date, timedelta);
                break;
            case "second":
                date = DateUtils.addSeconds(date, timedelta);
                break;
            default:
                throw new HyperRuleParameterError("[add_to_date] The value of the second parameter is illegal"
                        +". at line " + token.getLine()+ ", " +
                        "pos "+ (token.getCharPositionInLine()+1));
        }
        value.value = date;
        return value;
    }

    /**
     * date_diff(to_date("2017/10/09", "yyyy/MM/dd"), to_date("2007/12/09", "yyyy/MM/dd"), "year")
     * date_diff(to_date("2018/01/10", "yyyy/MM/dd"), to_date("2017/10/09", "yyyy/MM/dd"), "month")
     * date_diff(to_date("2018/10/10", "yyyy/MM/dd"), to_date("2017/10/09", "yyyy/MM/dd"), "month")
     * date_diff(to_date("2018/01/10", "yyyy/MM\dd"), to_date("2017/10/09", "yyyy/MM/dd"), "day")
     * date_diff(to_date("2017/10/17", "yyyy/MM/dd"), sysdate(), "hour")
     * date_diff(to_date("2017/10/17", "yyyy/MM/dd"), sysdate(), "minute")
     * date_diff(to_date("2017/10/17", "yyyy/MM/dd"), sysdate(), "second")
     *
     * @param ctx
     * @return
     */
    @Override
    public HyperRuleValue visitDate_diff(HyperRuleParser.Date_diffContext ctx) {

        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.INT_TYPE;

        HyperRuleValue rsp = visit(ctx.paralist());
        ArrayList params = (ArrayList) rsp.value;

        Token token = ctx.paralist().getStop();
        if (params.size() != 3) {

            throw new HyperRuleParameterError("date_diff function takes exactly three argument, but it was given " +
                    params.size() + ". at line " + token.getLine() + ", pos " + (token.getCharPositionInLine()+1));
        }

        HyperRuleValue first_value = ((HyperRuleValue) (params.get(0)));
        HyperRuleValue second_value = ((HyperRuleValue) (params.get(1)));
        HyperRuleValue third_value = ((HyperRuleValue) (params.get(2)));

        if(first_value.valueType != HyperRuleValue.DATE_TYPE){
            token = ctx.paralist().getStart();
            throw new HyperRuleParameterError("[date_diff] The first parameter types error, it is type: "
                    + first_value.getType()+ ". at line " + token.getLine()+ ", " +
                    "pos "+ (token.getCharPositionInLine()+1));
        }
        if(second_value.valueType != HyperRuleValue.DATE_TYPE){
            token = ctx.paralist().getStart();
            throw new HyperRuleParameterError("[date_diff] The second parameter types error, it is type: "
                    + second_value.getType()+ ". at line " + token.getLine()+ ", " +
                    "pos "+ (token.getCharPositionInLine()+1));
        }
        if(third_value.valueType != HyperRuleValue.STRING_TYPE){
            throw new HyperRuleParameterError("[date_diff] The third parameter types error, it is type: "
                    + third_value.getType()+ ". at line " + token.getLine()+ ", " +
                    "pos "+ (token.getCharPositionInLine()+1));
        }

        Date first_date = (Date) first_value.value;
        Date second_date = (Date) second_value.value;
        String type = (String) third_value.value;

        switch (type){
            case "year": case "month": case "day": case "hour":case "minute":case "second":
                break;
            default:
                throw new HyperRuleParameterError("[date_diff] The value of the third parameter is illegal"
                        +". at line " + token.getLine()+ ", " +
                        "pos "+ (token.getCharPositionInLine()+1));
        }

        value.value = (int) dateDiff(first_date, second_date, type);

        return value;
    }

    /**
     * sysdate()
     *
     * @param ctx
     * @return
     */
    @Override
    public HyperRuleValue visitSysdate(HyperRuleParser.SysdateContext ctx) {
        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.DATE_TYPE;

        value.value = new Date();

        return value;
    }

    @Override
    public HyperRuleValue visitParalist(HyperRuleParser.ParalistContext ctx) {
        List param_list = new ArrayList();
        for (int i = 0; i < ctx.para().size(); i++) {
            param_list.add(visit(ctx.para(i)));
//            param_list.add(visit(ctx.para(i)).value);
        }

        HyperRuleValue value = new HyperRuleValue();
        value.valueType = HyperRuleValue.LIST_TYPE;
        value.value = param_list;
        return value;
    }

    @Override
    public HyperRuleValue visitPara(HyperRuleParser.ParaContext ctx) {
        return visit(ctx.expression());
    }

    public HyperRuleValue visitErrorNode(ErrorNode node) {
        Token token = node.getSymbol();
        throw new HyperRuleSyntaxError("[ErrorNode] token recognition error at: " + token.getText() +
                ". at line " + token.getLine() + ", pos " + (token.getCharPositionInLine()+1));
    }

    public static long dateDiff(Date date1, Date date2, String timeInterval) {

        if (timeInterval.equals("year")) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date1);
            int time = calendar.get(Calendar.YEAR);
            calendar.setTime(date2);
            return time - calendar.get(Calendar.YEAR);
        }


        if (timeInterval.equals("month")) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date1);
            int time = calendar.get(Calendar.YEAR) * 12;
            calendar.setTime(date2);
            time -= calendar.get(Calendar.YEAR) * 12;
            calendar.setTime(date1);
            time += calendar.get(Calendar.MONTH);
            calendar.setTime(date2);
            return time - calendar.get(Calendar.MONTH);
        }

//        if (timeInterval.equals("week")) {
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(date1);
//            int time = calendar.get(Calendar.YEAR) * 52;
//            calendar.setTime(date2);
//            time -= calendar.get(Calendar.YEAR) * 52;
//            calendar.setTime(date1);
//            time += calendar.get(Calendar.WEEK_OF_YEAR);
//            calendar.setTime(date2);
//            return time - calendar.get(Calendar.WEEK_OF_YEAR);
//        }

        if (timeInterval.equals("day")) {
            return (date1.getTime() - date2.getTime()) / 1000 / 60 / 60 / 24;
        }

        if (timeInterval.equals("hour")) {
            return (date1.getTime() - date2.getTime()) / 1000 / 60 / 60;
        }

        if (timeInterval.equals("minute")) {
            return (date1.getTime() - date2.getTime()) / 1000 / 60;
        }

        if (timeInterval.equals("second")) {
            return (date1.getTime() - date2.getTime()) / 1000;
        }

        return date1.getTime() - date2.getTime();
    }
}
