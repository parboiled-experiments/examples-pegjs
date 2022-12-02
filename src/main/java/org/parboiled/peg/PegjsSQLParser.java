package org.parboiled.peg;

import org.parboiled.Action;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;
import org.parboiled.peg.util.ParseUtils;
import org.parboiled.support.ParseTreeUtils;
import org.parboiled.support.ParsingResult;

@BuildParseTree
public class PegjsSQLParser extends BasePegjsParser {

	private static Rule startRule;

	@Override
	public Rule start() {
		if (startRule == null) {
			String pegjs = ParseUtils.read(PegjsSQLParser.class.getClassLoader().getResource("sql.pegjs"));
			ParsingResult<?> result = ParseUtils.parse(pegjs, PegPegjsParser.class, false);
			startRule = start("sql_stmt", result);
		}
		return startRule;
	}
	
	protected Rule activateRule(String label, Rule rule) {
		if("_tableName".equals(label)){
			rule = Sequence(rule, (Action<?>) context -> !context.getMatch().equalsIgnoreCase("where"));
		}
		return rule;
	}

	public static ParsingResult<?> parse(String script) throws Exception {
		System.out.println("script : " + script);
		ParsingResult<?> result = ParseUtils.parse(script, PegjsSQLParser.class, false);
//		ParsingResult<?> result = ParseUtils.parse(script, PegjsSQLParser.class, true);
		String parseTreePrintOut = ParseTreeUtils.printNodeTree(result);
		System.out.println("tree : " + parseTreePrintOut);
		return result;
	}

	public static void main(String[] args) throws Exception {

		parse("SELECT * FROM employee");
//		parse(" SELECT * FROM employee ");
		
//		parse(" select * from employee  ");
//		parse(" select * from Employee1 ");
//		parse(" Select * From Employee1 ");
//
//		parse(" Select a From employee ");
//		parse(" Select a,b From employee ");
//		parse(" Select a ,b From employee ");
//		parse(" Select a, b From employee ");
//		parse(" Select a , b From employee ");
		
//		parse(" Select a , b From employee worker ");
//		parse(" Select a , b , c  From employee as worker ");
//		
//		parse(" select * from employee where rank > 4");
//		parse(" select * from employee as toppers where rank > 4");
//		
//		parse(" select * from (select * from employee) ");
//		parse(" select * from ( select * from employee ) ");
//		parse(" select * from ( select * from employee ) where rank > 4 ");
//		parse(" select * from ( select * from employee ) worker where rank > 4 ");
//		parse(" select * from ( select * from employee ) as worker where rank > 4 ");
//		parse(" select * from ( select * from employee where rank < 4 )");
//		parse(" select * from ( select * from employee where rank < 4 ) toppers");
//		parse(" select * from ( select * from employee where rank < 4 ) as toppers");
	}

}
