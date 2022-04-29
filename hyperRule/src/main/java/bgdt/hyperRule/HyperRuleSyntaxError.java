package bgdt.hyperRule;

public class HyperRuleSyntaxError extends HyperRuleException {
    public HyperRuleSyntaxError(){}
    public HyperRuleSyntaxError(String msg){
        super(msg);
    }
    public HyperRuleSyntaxError(Throwable cause) {
        super(cause);
    }
}
