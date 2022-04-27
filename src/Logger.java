
public class Logger {
	static enum Level {
		TRACE (3),
		DEBUG (2),
		INFO (1);
		
		public final int LogLevel;
		Level(int i) {
			// TODO Auto-generated constructor stub
			this.LogLevel = i;
		}
		
		public int getValue() {
			return LogLevel;
		}
		
		public static void log(Level l, String msg) {
			if(l.getValue() <= loglevel.getValue()) {
				System.out.println(msg);
			}
		}
	}
	
	public static Level loglevel = Level.INFO;
}
