package mall.util;
public enum ProductImageType {

	BRIEF("brief"),
	DETAIL("detail");
	

	private String type;
	
	ProductImageType(String type){
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type;
	}
}
