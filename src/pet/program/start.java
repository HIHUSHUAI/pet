package pet.program;


public class start extends window{

	public static void main(String[] args) {
		start();
	}


	public static void start() {
		jframe();
		setTray();
		while(true) {
			play_Time();			
		}
	}
}
