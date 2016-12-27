package com.subra.antlr.listner;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.portal.pcm.FList;
import com.portal.pcm.SparseArray;
import com.subra.antlr.gen.JSONBaseListener;
import com.subra.antlr.gen.JSONParser;
import com.subra.antlr.gen.JSONParser.ValueContext;
import com.subra.pcm.PcmAdaptor;

public class JsonTooFlist extends JSONBaseListener {
	public ParseTreeProperty<SparseArray> sparseArrayptrs = new ParseTreeProperty<SparseArray>(); 
	public ParseTreeProperty<FList> flistPtrs = new ParseTreeProperty<FList>(); 
	public ParseTreeProperty<String> objType = new ParseTreeProperty<String>(); 

	private void pushParentFlistToMap(ParserRuleContext ctx){
		ParserRuleContext  parentCtx = ctx.getParent();
		FList flist = flistPtrs.get(parentCtx);
		if(flist == null){
			//System.out.println("Flist is null for parent node. This is not expected");
			throw new IllegalStateException("Flist is null for parent node. This is not expected");
		}
		flistPtrs.put(ctx, flist);
	}

	private void pushChildFlistToMap(ParserRuleContext ctx){
		ParseTree  childCtx = ctx.getChild(0);
		FList flist = flistPtrs.get(childCtx);
		if(flist == null){
			//System.out.println("Flist is null for child node. This is not expected");
			throw new IllegalStateException("Flist is null for child node. This is not expected");
		}
		flistPtrs.put(ctx, flist);
	}
	
	private void pushChildSparseTreeToMap(ParserRuleContext ctx){
		ParseTree  childCtx = ctx.getChild(0);
		SparseArray sparseTree = sparseArrayptrs.get(childCtx);
		if(sparseTree == null){
			//System.out.println("sparseTree is null for child node. This is not expected");
			throw new IllegalStateException("sparseTree is null for child node. This is not expected");
		}
		sparseArrayptrs.put(ctx, sparseTree);
	}
	
	public static String stripQuotes(String s) {
		if ( s==null || s.charAt(0)!='"' ) return s;
		return s.substring(1, s.length() - 1);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void enterJson(JSONParser.JsonContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void exitJson(JSONParser.JsonContext ctx) { 
		FList flist = flistPtrs.get(ctx.object());
		flist.dump();
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void enterAnObject(JSONParser.AnObjectContext ctx) {
		//System.out.println("enterAnObject");
		FList flist = new FList();
		flistPtrs.put(ctx, flist);

	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void exitAnObject(JSONParser.AnObjectContext ctx) { 
		//System.out.println("exitAnObject");
		//FList self = flistPtrs.get(ctx);
		//System.out.println("Dumping flist");
		//self.dump();
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void enterEmptyObject(JSONParser.EmptyObjectContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void exitEmptyObject(JSONParser.EmptyObjectContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void enterArrayOfObject(JSONParser.ArrayOfObjectContext ctx) { 
		//System.out.println("enterArrayOfObject");

		SparseArray sparseTree = new SparseArray();
		sparseArrayptrs.put(ctx, sparseTree);

		this.pushParentFlistToMap(ctx);

	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void exitArrayOfObject(JSONParser.ArrayOfObjectContext ctx) {
		//System.out.println("exitArrayOfObject");

		SparseArray sparseTree = sparseArrayptrs.get(ctx);
		if(sparseTree == null){
			//System.out.println("sparseTree is null. This is not expected");
			throw new IllegalStateException("sparseTree is null. This is not expected");
		}

		int childCount = ctx.getChildCount();
		for(int i=0;i<childCount;i++){
			FList flist = flistPtrs.get(ctx.getChild(i));
			if(flist!=null){
				sparseTree.add(flist);
			}
		}
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void enterEmptyArray(JSONParser.EmptyArrayContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void exitEmptyArray(JSONParser.EmptyArrayContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void enterPair(JSONParser.PairContext ctx) { 
		//System.out.println("enterPair");
		this.pushParentFlistToMap(ctx);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void exitPair(JSONParser.PairContext ctx) {
		//System.out.println("exitPair");
		String fieldName = ctx.STRING().getText();
		ValueContext  val = ctx.value();
		//System.out.println("exitPair String="+fieldName+" Val="+val.getText());
		FList parentFlist = flistPtrs.get(ctx);
		
		PcmAdaptor.updateFlistWithNewFields( parentFlist,stripQuotes(fieldName),val,this);

	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void enterString(JSONParser.StringContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void exitString(JSONParser.StringContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void enterNumber(JSONParser.NumberContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void exitNumber(JSONParser.NumberContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void enterObjectValue(JSONParser.ObjectValueContext ctx) { 
		//System.out.println("enterObjectValue");
		this.pushParentFlistToMap(ctx);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void exitObjectValue(JSONParser.ObjectValueContext ctx) { 
		//System.out.println("exitObjectValue");
		this.pushChildFlistToMap(ctx);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void enterArrayValue(JSONParser.ArrayValueContext ctx) { 
		//System.out.println("enterArrayValue");
		this.pushParentFlistToMap(ctx);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void exitArrayValue(JSONParser.ArrayValueContext ctx) { 
		//System.out.println("exitArrayValue");
		this.pushChildSparseTreeToMap(ctx);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void enterBtrue(JSONParser.BtrueContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void exitBtrue(JSONParser.BtrueContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void enterBfalse(JSONParser.BfalseContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void exitBfalse(JSONParser.BfalseContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void enterPtrNull(JSONParser.PtrNullContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void exitPtrNull(JSONParser.PtrNullContext ctx) { }

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void enterEveryRule(ParserRuleContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void exitEveryRule(ParserRuleContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void visitTerminal(TerminalNode node) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	public void visitErrorNode(ErrorNode node) { }
}
