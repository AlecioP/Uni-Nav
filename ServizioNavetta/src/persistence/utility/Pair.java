package persistence.utility;

public class Pair<L,R> {

	private L left;
	private R right;
	
	public Pair(L l,R r) {
		setLeft(l);
		setRight(r);
	}

	public L getLeft() {
		return left;
	}

	public void setLeft(L left) {
		this.left = left;
	}

	public R getRight() {
		return right;
	}

	public void setRight(R right) {
		this.right = right;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		if(!(obj instanceof Pair))
			return false;
		Pair<?,?> tmp = (Pair<?,?>) obj;
		
		if(left ==null && tmp.left==null) {
			if(right==null && tmp.right==null)
				return true;
			else if(right==null || tmp.right==null)
				return false;
			else
				return right.getClass().equals(tmp.right.getClass());
		}else if(left == null || tmp.left==null)
			return false;
		else {
			if(right==null && tmp.right==null)
				return true;
			else if(right==null || tmp.right==null)
				return false;
			else
				return right.getClass().equals(tmp.right.getClass());
		}
	}
	
	@Override
	public String toString() {
		return "persistence.utility.Pair : <"+left.toString()+"|"+right.toString()+">";
	}

}
