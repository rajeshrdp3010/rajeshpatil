package practise;

public class SoupInfo {
	private int index;
	private int ingredientCount;
	private int maxServingSize;
	
	public SoupInfo (int index,int ingredientCount,int maxServingSize) {
		this.index = index;
		this.ingredientCount = ingredientCount;
		this.maxServingSize = maxServingSize;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getIngredientCount() {
		return ingredientCount;
	}
	public void setIngredientCount(int ingredientCount) {
		this.ingredientCount = ingredientCount;
	}
	public int getMaxServingSize() {
		return maxServingSize;
	}
	public void setMaxServingSize(int maxServingSize) {
		this.maxServingSize = maxServingSize;
	}
    public String toString() {
        return new StringBuilder().append(" index ").append(index).append(" ingredientCount ").append(ingredientCount)
                .append(" maxServingSize ").append(maxServingSize).toString();
    }
}
