package bgdt.hyperRule;

public class HyperRuleException extends RuntimeException{
    public HyperRuleException(){}
    public HyperRuleException(String msg){
        super(msg);
    }
    public HyperRuleException(Throwable cause) {
        super(cause);
    }
}

