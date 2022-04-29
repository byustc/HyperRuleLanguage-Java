package bgdt.hyperRule;

public class HyperRuleParameterError extends HyperRuleException{
    public HyperRuleParameterError(){}
    public HyperRuleParameterError(String msg){
        super(msg);
    }
    public HyperRuleParameterError(Throwable cause) {
        super(cause);
    }
}
