package bgdt.hyperWrapper;

import bgdt.hyperBase.HyperRuleLexer;
import bgdt.hyperBase.HyperRuleParser;
import bgdt.hyperRule.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;


public class HyperRuleStringWrapper {

    /**
     * 根据输入文本后去解析树
     * @param text
     * @return
     */
    private static ParseTree getParseTree(String text) {
        CharStream charStream = CharStreams.fromString(text);
        HyperRuleLexer lexer = new HyperRuleBailLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        HyperRuleParser parser = new HyperRuleParser(tokenStream);
        parser.setErrorHandler(new HyperRuleBailErrorStrategy());
        ParseTree tree = parser.statements();
        return tree;
    }

    /**
     * 解析输入
     * @param rule
     * @return
     */
    public static HyperRuleValue parseString(String rule) {
        ParseTree tree = HyperRuleStringWrapper.getParseTree(rule);
        HyperRuleVisitorImpl visitor = new HyperRuleVisitorImpl();
        HyperRuleValue value = visitor.visit(tree);
        return value;
    }

    /**
     * 解析输入，支持预置字典
     * @param rule 输入
     * @param init 预置字典
     * @return
     */
    public static HyperRuleValue parseString(String rule, Map<String, HyperRuleValue> init) {
        ParseTree tree = HyperRuleStringWrapper.getParseTree(rule);
        HyperRuleVisitorImpl visitor = new HyperRuleVisitorImpl(init);
        HyperRuleValue value = visitor.visit(tree);
        return value;
    }

    /**
     * multi statement compose rule
     *
     * @return
     */
    public static HyperRuleValue multiLineParseString() {

        HyperRuleVisitorImpl visitor = new HyperRuleVisitorImpl();

        while (true) {
            try {
                BufferedReader strIn = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("calc > : ");
                String str = strIn.readLine();

                ParseTree tree = HyperRuleStringWrapper.getParseTree(str);

                HyperRuleValue value = visitor.visit(tree);

                System.out.println("result：" + value);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (HyperRuleException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
