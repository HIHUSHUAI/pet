package pet.model;

public class pet1 extends pet{

	private final static String pet_name = "王看山";
	private static String pet_nickName = "小王";
	private static int pet_action_number = 5;
	private static String[] pet_image = {"pet\\click.gif","pet\\play1.gif",
			"pet\\play2.gif","pet\\play3.gif","pet\\run.gif"};
	
	public static String getPet_Name() {
		return pet_name;
	}
	public static String getPet_nickName() {
		return pet_nickName;
	}
	public static int getpet_Action_number() {
		return pet_action_number;
	}
	public static String getPet_image(int i) {
		return pet_image[i];
	}
	public void setPet_nickName(String pet_nickName) {
		this.pet_nickName = pet_nickName;
	}
}
