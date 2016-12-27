package com.subra.app;

import java.io.FileInputStream;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.subra.antlr.gen.JSONLexer;
import com.subra.antlr.gen.JSONParser;
import com.subra.antlr.listner.JsonTooFlist;


public class ParserApp {

	/*
	public static void main(String[] args) throws Exception {
        String inputFile = null;
        if ( args.length>0 ) inputFile = args[0];
        InputStream is = System.in;
        if ( inputFile!=null ) is = new FileInputStream(inputFile);
        JSONLexer lexer = new JSONLexer(new ANTLRInputStream(is));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JSONParser parser = new JSONParser(tokens);
        parser.setBuildParseTree(true); // tell ANTLR to build a parse tree
        ParseTree tree = parser.json();

        ParseTreeWalker walker = new ParseTreeWalker();
        JSON2XML loader = new JSON2XML();
        walker.walk(loader, tree);
    }
	*/
	  public static void main(String[] args) throws Exception {
        String inputFile = null;
        if ( args.length>0 ) inputFile = args[0];
        InputStream is = System.in;
        if ( inputFile!=null ) {
            is = new FileInputStream(inputFile);
        }
        ANTLRInputStream input = new ANTLRInputStream(is);
        JSONLexer lexer = new JSONLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JSONParser parser = new JSONParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.json();
        // show tree in text form
        //System.out.println(tree.toStringTree(parser));

        ParseTreeWalker walker = new ParseTreeWalker();
        JsonTooFlist converter = new JsonTooFlist();
        walker.walk(converter, tree);
        //System.out.println(converter.getXML(tree));
    }

	 
}
