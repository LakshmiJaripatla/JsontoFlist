// Generated from JSON.g4 by ANTLR 4.5.3
package com.subra.antlr.gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JSONParser}.
 */
public interface JSONListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JSONParser#json}.
	 * @param ctx the parse tree
	 */
	void enterJson(JSONParser.JsonContext ctx);
	/**
	 * Exit a parse tree produced by {@link JSONParser#json}.
	 * @param ctx the parse tree
	 */
	void exitJson(JSONParser.JsonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AnObject}
	 * labeled alternative in {@link JSONParser#object}.
	 * @param ctx the parse tree
	 */
	void enterAnObject(JSONParser.AnObjectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AnObject}
	 * labeled alternative in {@link JSONParser#object}.
	 * @param ctx the parse tree
	 */
	void exitAnObject(JSONParser.AnObjectContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EmptyObject}
	 * labeled alternative in {@link JSONParser#object}.
	 * @param ctx the parse tree
	 */
	void enterEmptyObject(JSONParser.EmptyObjectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EmptyObject}
	 * labeled alternative in {@link JSONParser#object}.
	 * @param ctx the parse tree
	 */
	void exitEmptyObject(JSONParser.EmptyObjectContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayOfObject}
	 * labeled alternative in {@link JSONParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArrayOfObject(JSONParser.ArrayOfObjectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayOfObject}
	 * labeled alternative in {@link JSONParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArrayOfObject(JSONParser.ArrayOfObjectContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EmptyArray}
	 * labeled alternative in {@link JSONParser#array}.
	 * @param ctx the parse tree
	 */
	void enterEmptyArray(JSONParser.EmptyArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EmptyArray}
	 * labeled alternative in {@link JSONParser#array}.
	 * @param ctx the parse tree
	 */
	void exitEmptyArray(JSONParser.EmptyArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link JSONParser#pair}.
	 * @param ctx the parse tree
	 */
	void enterPair(JSONParser.PairContext ctx);
	/**
	 * Exit a parse tree produced by {@link JSONParser#pair}.
	 * @param ctx the parse tree
	 */
	void exitPair(JSONParser.PairContext ctx);
	/**
	 * Enter a parse tree produced by the {@code String}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void enterString(JSONParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code String}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void exitString(JSONParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code number}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void enterNumber(JSONParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code number}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void exitNumber(JSONParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ObjectValue}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void enterObjectValue(JSONParser.ObjectValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ObjectValue}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void exitObjectValue(JSONParser.ObjectValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayValue}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void enterArrayValue(JSONParser.ArrayValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayValue}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void exitArrayValue(JSONParser.ArrayValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code btrue}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void enterBtrue(JSONParser.BtrueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code btrue}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void exitBtrue(JSONParser.BtrueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bfalse}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void enterBfalse(JSONParser.BfalseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bfalse}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void exitBfalse(JSONParser.BfalseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ptrNull}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void enterPtrNull(JSONParser.PtrNullContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ptrNull}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void exitPtrNull(JSONParser.PtrNullContext ctx);
}