package com.greenpineyu.fel.examples;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.parser.AbstFelNode;
import com.greenpineyu.fel.parser.FelNode;
import com.greenpineyu.fel.parser.VarAstNode;

public class Test {
	static public int[] sort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			int current = a[i];
			int j = i - 1;
			for (; j > -1 && current < a[j]; j--) {
				a[j + 1] = a[j];
			}
			a[j + 1] = current;
		}
		return a;
	}
	

	private static void sort() {
		int[] b = new int[10];
		for (int i = 0; i < b.length; i++) {
			b[i] = new Random().nextInt(10);
		}

		int[] a = sort(b);
		System.out.println(Arrays.toString(a));
	}

	static {
		PrintStream os = new PrintStream(System.out) {
			@Override
			public void print(String b) {
				super.print(b);
			};
		};
		System.setOut(os);
		System.setErr(os);
	}
	public static void main(String[] args) {
		System.out.println("abcd");
		// t();
		FelEngine e = FelEngine.instance;
		String exp = "(1+2)1";
		e.getParser().verify(exp);
		// FelNode node = e.parse(exp);
		// System.out.println(node);
		// Object eval = Fel.eval(exp);
		// System.out.println(eval);
	}

	private static void t() {
		String exp = "$a*3+b*5+c*4+$d*10";
		FelNode node = FelEngine.instance.parse(exp);
		List<FelNode> nodes = AbstFelNode.getNodes(node);
		List<FelNode> $expNodes = new ArrayList<FelNode>();
		for (FelNode n : nodes) {
			if (n.getText().equals("*")) {
				if (contains$Var(n)) {
					$expNodes.add(n);
				}
			}
		}
		System.out.println(node);
	}

	static public boolean contains$Var(FelNode n) {
		List<FelNode> children = n.getChildren();
		if (children != null) {
			for (FelNode c : children) {
				if (c instanceof VarAstNode) {
					if (c.getText().startsWith("$")) {
						return true;
					}
				}
			}
		}
		return false;
	}

}