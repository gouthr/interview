package numbers;

import java.util.ArrayList;
import java.util.List;

public class NestedInteger {
	
	NestedInteger() {
		lni = new ArrayList<NestedInteger>();
	}
	
	NestedInteger(Integer integer) {
		this.integer = integer;
		this.lni = new ArrayList<NestedInteger>();
	}
	
	NestedInteger(List<NestedInteger> lni) {
		this.lni = lni;
	}
	
	public void addNestedInteger(NestedInteger ni) {
		lni.add(ni);
	}
	
	public Integer getInteger() {
		return integer;
	}
	
	public List<NestedInteger> getListNestedInteger() {
		return lni;
	}
	
	public boolean isInteger() {
		return integer != null;
	}
	
	public String printListNestedInteger(NestedInteger ni, StringBuilder sb) {

        if (ni.isInteger()) {
            sb.append(ni.integer);
            sb.append(", ");
        }
        for (NestedInteger eni : ni.lni) {
            sb.append("[");
            printListNestedInteger(eni, sb);
            sb.append("]");
        }
        return sb.toString();
	}
	
	public int getWeightedSum(List<NestedInteger> ni, int depth) {
		if (ni == null || ni.size() == 0) {
			return 0;
		}
		int sum = 0;
        for (NestedInteger eni : ni) {
            if (eni.isInteger()) {
            	sum += eni.getInteger() * depth;
            }
            sum += getWeightedSum(eni.getListNestedInteger(), depth + 1);
        }
        return sum;
	}
	
	
	public static void main(String args[]) {
		NestedInteger ni1 = new NestedInteger(1);
		NestedInteger ni2 = new NestedInteger(2);
		NestedInteger ni3 = new NestedInteger(3);
		NestedInteger ni4 = new NestedInteger(4);
		NestedInteger ni5 = new NestedInteger(5);
		NestedInteger ni6 = new NestedInteger(6);
		ni1.addNestedInteger(ni2);
		ni2.addNestedInteger(ni3);
		ni3.addNestedInteger(ni4);
		ni2.addNestedInteger(ni5);
		ni1.addNestedInteger(ni6);
		List<NestedInteger> list = new ArrayList<NestedInteger>();
		list.add(ni1);
		System.out.println(ni1.printListNestedInteger(ni1, new StringBuilder()));
		System.out.println(ni1.getWeightedSum(list, 1));
	}

	private Integer integer;
	
	private List<NestedInteger> lni;

}
