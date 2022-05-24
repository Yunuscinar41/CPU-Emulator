import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

public class CpuEmulator {
	public static int[] M = new int[256];
	public static int ac, fFlag = 0, pCount = 0;
	public static void main(String[] args) {
		
		Arrays.fill(M, 0);
		
		String filepath = args[0];
        File file = new File(filepath);
        
        String[] line;
        BufferedReader br;
        
		try {
			
	        String st;
	       A:while(true) {
	    	   br = new BufferedReader(new FileReader(file));

	        while ((st = br.readLine()) != null) {
	        	
	        	st = st.trim().replaceAll(" +", " ");
	            line = st.split(" ");
	        
	            if(pCount == Integer.parseInt(line[0])) {
	            	
	            switch (line[1]) {
	            	case "START":	           
	            		start();
	            		break;
					case "LOAD":		
						load(Integer.parseInt(line[2]));
						break;
					case "LOADM":						
						loadM(Integer.parseInt(line[2]));
						break;
					case "STORE":						
						store(Integer.parseInt(line[2]));
						break;
					case "ADD":						
						add(Integer.parseInt(line[2]));
						break;
					case "SUB":						
						sub(Integer.parseInt(line[2]));
						break;
					case "MUL":						
						mul(Integer.parseInt(line[2]));
						break;
					case "DISP":						
						disp();
						break;
					case "HALT":
						halt();
					case "MULM":						
						mulm(Integer.parseInt(line[2]));
						break;
					case "SUBM":						
						subm(Integer.parseInt(line[2]));
						break;
					case "ADDM":
						addm(Integer.parseInt(line[2]));
						break;
					case "CMPM":						
						cmpm(Integer.parseInt(line[2]));
						break;
					case "CJMP":
						cjmp(Integer.parseInt(line[2]));
						break;
					case "JMP":
						jmp(Integer.parseInt(line[2]));
						break;
						
	            }
	           
	            if(line.length == 0) pCount = 0;
	            }
	        }
		}
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        
	}
	public static boolean memoryCheck(int mLocation) {
		if(mLocation < M.length && mLocation >= 0) return true;
		else {
			System.out.println("The given memory location (" + mLocation + ") is not valid!");
            return false;
		}
	}
	
	//Start execution
	public static void start() {
		pCount++;
		
	}
	// Load the given value to ac
	public static void load(int x) {
		ac = x;
		pCount++;
	}
	
	// Load the memory value stored at M[x] to ac if the value is valid
	public static void loadM(int mLocation) {
		if(memoryCheck(mLocation)) 
			ac = M[mLocation];
		pCount++;
	}
	
	//store the value at ac to memory 
	public static void store(int mLocation) {
		if(memoryCheck(mLocation)) 
			M[mLocation] = ac;
		pCount++;
	}
	
	// compare the value ac and value at M[x] and return 1 if it's greater 
	// ,if it's not return -1 and if they're equal return 0
	public static void cmpm(int mLocation) {
		if(memoryCheck(mLocation))
			fFlag = Integer.compare(ac, M[mLocation]);
		pCount++;
	}
	
	// conditional jump
	// update pCount with x if the flag value is positive
	public static void cjmp(int x) {
		if(fFlag > 0) pCount = x;
		else pCount++;
	}
	
	// unconditional jump
	// updates pCount with the value x
	public static void jmp(int x) {
		pCount = x;
	}
	
	// add the value x to ac
	public static void add(int x) {
		ac += x;
		pCount++;
	}
	
	// add the value located at M[x] to ac if the value is valid
	public static void addm(int mLocation) {
		if(memoryCheck(mLocation)) ac += M[mLocation];
		pCount++;
	}
	
	// sub the value located at M[x] to ac if the value is valid
	public static void subm(int mLocation) {
		if(memoryCheck(mLocation)) ac -= M[mLocation];
		pCount++;
	}
	
	// sub the value x from ac
	public static void sub(int x) {
		ac -= x;
		pCount++;
	}
	
	// multiply ac with the value x
	public static void mul(int x) {
		ac *= x;
		pCount++;
	}
	
	// multiply ac with the value at the M[x] if the location is valid
	public static void mulm(int mLocation) {
		if(memoryCheck(mLocation)) ac*= M[mLocation];
		pCount++;
	}
	
	// display the ac
	public static void disp() {
		System.out.println("AC value -> " + ac);
		pCount++;
	}
	
	// finish the execution
	public static void halt() {
		System.exit(0);
	}
}
