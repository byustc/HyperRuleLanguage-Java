package bgdt.hyperRule;

import bgdt.hyperBase.HyperRuleLexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.LexerNoViableAltException;
import org.antlr.v4.runtime.misc.Interval;

public class HyperRuleBailLexer extends HyperRuleLexer {
    public HyperRuleBailLexer(CharStream input) {
        super(input);
    }

    public void recover(LexerNoViableAltException e) {
        Lexer recognition = (Lexer)e.getRecognizer();
        String text = this._input.getText(Interval.of(this._tokenStartCharIndex, this._input.index()));

        throw new HyperRuleSyntaxError("[lexer] token recognition error at: '" + this.getErrorDisplay(text) +
                "'. at line " + recognition._tokenStartLine + ", pos " + recognition._tokenStartCharPositionInLine);
    }
}