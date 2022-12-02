package org.parboiled.peg;

import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;
import org.parboiled.peg.util.ParseUtils;
import org.parboiled.support.ParseTreeUtils;
import org.parboiled.support.ParsingResult;

@BuildParseTree
public class PegjsCalcParser extends BasePegjsParser {

	private static Rule startRule;

	@Override
	public Rule start() {
		if (startRule == null) {
			String pegjs = ParseUtils.read(PegjsCalcParser.class.getClassLoader().getResource("calc.pegjs"));
			ParsingResult<?> result = ParseUtils.parse(pegjs, PegPegjsParser.class, false);
			startRule = start("Expression", result);
		}
		return startRule;
	}

	public static ParsingResult<?> parse(String expr) throws Exception {
		return ParseUtils.parse(expr, PegjsCalcParser.class, false);
//		return ParseUtils.parse(expr, PegjsCalcParser.class, true);
	}

	public static void main(String[] args) throws Exception {

		String expr;
//		expr = " 22 + 33 ";
//		expr = " 22 + 33 * 44  ";
//		expr = " 22 + ( 33 * 44 ) ";
		expr = " 22 + ( 33 * 44 ) / 55 ";

		System.out.println("--------------------------------------------");
		System.out.println(" expr : " + expr);
		System.out.println("--------------------------------------------");

		ParsingResult<?> result = parse(expr);
		String parseTreePrintOut = ParseTreeUtils.printNodeTree(result);
		System.out.println("tree : " + parseTreePrintOut);
	}

}