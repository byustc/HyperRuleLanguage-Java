# urools-java

IG基于Antlar自研的规则引擎urools


## antlr 简介

ANTLR 语言识别的一个工具 (ANother Tool for Language Recognition ) 是一种语言工具，它提供了一个框架，可以通过包含 Java, C++, 或 C# 动作（action）的语法描述来构造语言识别器，编译器和解释器。 计算机语言的解析已经变成了一种非常普遍的工作，在这方面的理论和工具经过近 40 年的发展已经相当成熟，使用 Antlr 等识别工具来识别，解析，构造编译器比手工编程更加容易，同时开发的程序也更易于维护。

语言识别的工具有很多种，比如大名鼎鼎的 Lex 和 YACC，Linux 中有他们的开源版本，分别是 Flex 和 Bison。在 Java 社区里，除了 Antlr 外，语言识别工具还有 JavaCC 和 SableCC 等。

和大多数语言识别工具一样，Antlr 使用上下文无关文法描述语言。最新的 Antlr 是一个基于 LL(*) 的语言识别器。在 Antlr 中通过解析用户自定义的上下文无关文法，自动生成词法分析器 (Lexer)、语法分析器 (Parser) 和树分析器 (Tree Parser)。


## 安装

```shell

$ cd /usr/local/lib
$ curl -O http://www.antlr.org/download/antlr-4.7.2-complete.jar

$ vim ~/.zshrc # or vim ~/.bashrc

export CLASSPATH=".:/usr/local/lib/antlr-4.7.2-complete.jar:$CLASSPATH"
alias antlr4='java -Xmx500M -cp "/usr/local/lib/antlr-4.7.2-complete.jar:$CLASSPATH" org.antlr.v4.Tool'
alias grun='java org.antlr.v4.runtime.misc.TestRig'


$ . ~/.zshrc # or restart the terminal

```


## 生成代码

antlr 对Lex 跟 YACC 有很好的封装，可以基于规则文件直接生成代码项目，然后我们只需要修改项目，对规则进行实现即可

这里说的规则就是 词法文件(HyperRuleLex.g4) 跟语法文件(HyperRule.g4)

ANTLR 4 提供了 Visitor 和 Listener 两种模式，通过这两种模式可以很轻松地把 Parser 的结果做各种处理。
ANTLR 4 默认会生成 Listener 模式，如果不需要要加上 `-no-listener`，如果要生成 Visitor 模式要加上 `-visitor`

生成语句如下：

```shell

$ antlr4 -visitor HyperRule.g4

```

生成文件列表

```shell

➜  tmp ls
HyperRule.g4               HyperRule.tokens           HyperRuleBaseVisitor.java  HyperRuleLexer.interp      HyperRuleLexer.tokens      HyperRuleParser.java
HyperRule.interp           HyperRuleBaseListener.java HyperRuleLex.g4            HyperRuleLexer.java        HyperRuleListener.java     HyperRuleVisitor.java

```

- <Grammar>Lexer.java: Lexer
- <Grammar>Parser.java: Parser
- <Grammar>Listener.java: Listener 接口
- <Grammar>BaseListener.java: Listener 默认实现
- <Grammar>Visitor.java: Visitor 接口
- <Grammar>BaseVisitor.java: Visitor 默认实现
- <Grammar>[Lexer].tokens: 当语法被拆分成多个多个文件时用于同步编号

使用方法就是把 *.java 复制到项目中合适的位置，然后编写调用代码、Visitor及（或）Listener。

在本项目中, 生成的代码全部在 `com.unisound.urools.hyperBase` 中

`com.unisound.urools.hyperRule` 是重写规则跟重写一些错误、异常处理类

`com.unisound.urools.hyperWrapper` 是封装的调用入口



## 运行规则

运行代码需要自己实现，这里可以参看 `com.unisound.urools.hyperWrapper.HyperRuleStringWrapper`





## 参考链接

- [ANTLR4 笔记](https://abcdabcd987.com/notes-on-antlr4/)
- [ANTLR v4 学习笔记（一）－ANTLR 初体验](http://kyonhuang.top/ANTLR-learning-notes-1/)