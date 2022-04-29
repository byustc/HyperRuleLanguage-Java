package bgdt.hyperRule;

import org.antlr.v4.runtime.*;


public class HyperRuleBailErrorStrategy extends BailErrorStrategy {
    public HyperRuleBailErrorStrategy() {
    }

    public void recover(Parser recognizer, RecognitionException e) {
        Token token = recognizer.getContext().getStart();

        if (!this.inErrorRecoveryMode(recognizer)) {
            this.beginErrorCondition(recognizer);
            if (e instanceof NoViableAltException) {
                this.reportNoViableAlternative(recognizer, (NoViableAltException) e);
            } else if (e instanceof InputMismatchException) {
                this.reportInputMismatch(recognizer, (InputMismatchException) e);
            } else if (e instanceof FailedPredicateException) {
                this.reportFailedPredicate(recognizer, (FailedPredicateException) e);
            } else {
                System.err.println();
                String msg = "unknown recognition error type: " + e.getClass().getName();
                throw new HyperRuleSyntaxError("[parser] " + msg +
                        ". at line " + token.getLine() + ", pos " + (token.getCharPositionInLine() + 1));
            }

        }

    }

    public Token recoverInline(Parser recognizer) throws RecognitionException {
        InputMismatchException e = new InputMismatchException(recognizer);
        this.recover(recognizer, e);

        Token token = recognizer.getContext().getStart();
        return token;

    }

    public void sync(Parser recognizer) {
    }

    protected void reportNoViableAlternative(Parser recognizer, NoViableAltException e) {
        TokenStream tokens = recognizer.getInputStream();
        String input;
        if (tokens != null) {
            if (e.getStartToken().getType() == -1) {
                input = "<EOF>";
            } else {
                input = tokens.getText(e.getStartToken(), e.getOffendingToken());
            }
        } else {
            input = "<unknown input>";
        }

        String msg = "no viable alternative at input " + this.escapeWSAndQuote(input);

        Token offendingToken = e.getOffendingToken();
        int line = offendingToken.getLine();
        int charPositionInLine = offendingToken.getCharPositionInLine();

        throw new HyperRuleSyntaxError("[parser] " + msg + ". at line " + line + ", pos " + charPositionInLine);
    }

    protected void reportInputMismatch(Parser recognizer, InputMismatchException e) {
        String msg = "mismatched input " + this.getTokenErrorDisplay(e.getOffendingToken()) + " expecting " + e.getExpectedTokens().toString(recognizer.getVocabulary());
        Token offendingToken = e.getOffendingToken();
        int line = offendingToken.getLine();
        int charPositionInLine = offendingToken.getCharPositionInLine();

        throw new HyperRuleSyntaxError("[parser] " + msg + ". at line " + line + ", pos " + charPositionInLine);
    }

    protected void reportFailedPredicate(Parser recognizer, FailedPredicateException e) {
        String ruleName = recognizer.getRuleNames()[recognizer.getContext().getRuleIndex()];
        String msg = "rule " + ruleName + " " + e.getMessage();
        Token offendingToken = e.getOffendingToken();
        int line = offendingToken.getLine();
        int charPositionInLine = offendingToken.getCharPositionInLine();

        throw new HyperRuleSyntaxError("[parser] " + msg + ". at line " + line + ", pos " + charPositionInLine);
    }

}
